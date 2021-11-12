<template>
  <form class="form-inline">
    <div>
      <div class="form-group">
        <label for="points" class="form-label">Max No of Stops</label>
        <select
          name="noOfStops"
          id="noOfStops"
          class="form-select"
          @change="onChange($event)"
        >
          <option
            v-for="(stop, index) in validNumberOfStops"
            :key="index"
            :value="stop"
          >
            {{ stop }}
          </option>
        </select>
      </div>
      <br />

      <div class="form-group">
        <label for="points" class="form-label">Departure Time</label>
        <div class="flex-container">
          <span>{{ departureTimeMin }}:00</span>
          <div class="range-slider">
            <input
              type="range"
              min="0"
              max="23"
              step="1"
              v-model="departureTimeMin"
            />
            <input
              type="range"
              min="0"
              max="23"
              step="1"
              v-model="departureTimeMax"
            />
          </div>
          <span>{{ departureTimeMax }}:59</span>
        </div>
      </div>

      <br />

      <div>
        <label for="points" class="form-label">Max Trip Duration</label>
        <div class="flex-container">
          <div class="range-slider">
            <input
              type="range"
              min="0"
              max="24"
              step="1"
              v-model="tripDurationMax"
            />
          </div>
          <span>
            {{ tripDurationMax == 24 ? "any" : tripDurationMax + ":00" }}
            hours</span
          >
        </div>
      </div>
    </div>

    <button
      v-on:click="applyFilters"
      class="btn btn-sm btn-primary"
      style="margin: 20px 10px 10px 10px; float: right"
    >
      Apply
    </button>
  </form>
</template>


<script>
export default {
  data() {
    return {
      numberOfStops: undefined,
      minPrice: "500",
      maxPrice: "50000",
      minValue: "25000",
      departureTimeMin: 0,
      departureTimeMax: 23,
      tripDurationMax: 24,
    };
  },
  computed: {
    validNumberOfStops() {
      var stops = 0;
      var flights = this.$store.getters.getFlightSearchResponse;
      if (flights != null) {
        flights.forEach((flight) => {
          if (flight.num_stops > stops) {
            stops = flight.num_stops;
          }
        });
      }

      var stopArray = [];
      for (var i = 0; i <= stops; i++) {
        stopArray.push(i);
      }
      return stopArray;
    },
  },
  methods: {
    applyFilters: function (e) {
      e.preventDefault();
      var self = this;
      var flightSearchResponse = this.$store.getters.getFlightSearchResponse;
      if (flightSearchResponse != null) {
        flightSearchResponse = flightSearchResponse.filter((flight) => {
          var result = true;
          var isAM = flight.departure_time.split(" ")[1] == "AM";
          var flightDepartureTime =
            parseInt(flight.departure_time.split(":")[0]) + (isAM ? 0 : 12);

          if (self.departureTimeMin != 0) {
            result = flightDepartureTime >= self.departureTimeMin;
          }
          if (result && self.departureTimeMax != 23) {
            result = flightDepartureTime <= self.departureTimeMax;
          }

          if (result && self.numberOfStops != undefined) {
            result = parseInt(flight.num_stops) <= self.numberOfStops;
          }

          if (result && self.tripDurationMax < 24) {
            var hours = 0;
            if (flight.trip_duration.indexOf("hr") > 0) {
              hours = flight.trip_duration.split(" ")[0];
            }
            result = hours <= self.tripDurationMax;
          }

          return result;
        });
      }

      this.$store.commit(
        "setFilteredFlightSearchResponse",
        flightSearchResponse
      );

      this.$emit("refresh", "");
    },

    slider: function () {
      if (this.minPrice > this.maxPrice) {
        var tmp = this.maxPrice;
        this.maxPrice = this.minPrice;
        this.minPrice = tmp;
      }
    },

    onChange(event) {
      this.numberOfStops = event.target.value;
    },
  },
};
</script>

<style scoped>
.flex-container {
  display: flex;
  flex-wrap: nowrap;
}

.flex-container > div {
  background-color: #f1f1f1;
  text-align: center;
}

.range-slider {
  width: 70%;
  margin: auto;
  text-align: center;
  position: relative;
}

.range-slider input[type="range"] {
  position: absolute;
  left: 0;
  bottom: 0;
}

input[type="number"] {
  border: 1px solid #ddd;
  text-align: center;
  font-size: 1.6em;
  -moz-appearance: textfield;
}

input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
  -webkit-appearance: none;
}

input[type="number"]:invalid,
input[type="number"]:out-of-range {
  border: 2px solid #ff6347;
}

input[type="range"] {
  -webkit-appearance: none;
  width: 100%;
  background-color: #9a905d;
}

input[type="range"]:focus {
  outline: none;
}

/* input[type="range"]:focus::-webkit-slider-runnable-track {
  background: #2497e3;
} */

input[type="range"]::-ms-fill-lower {
  background: #2497e3;
}

input[type="range"]:focus::-ms-fill-upper {
  background: #2497e3;
}

input[type="range"]::-webkit-slider-runnable-track {
  width: 100%;
  height: 5px;
  cursor: pointer;
  background: #c4c9cc;
  border-radius: 1px;
  box-shadow: none;
  border: 0;
}

input[type="range"]::-webkit-slider-thumb {
  z-index: 2;
  position: relative;
  box-shadow: 0px 0px 0px #000;
  border: 1px solid #2497e3;
  height: 18px;
  width: 18px;
  border-radius: 25px;
  background: #2497e3;
  cursor: pointer;
  -webkit-appearance: none;
  margin-top: -7px;
}

.range-wrap {
  position: relative;
  margin: 0 auto 3rem;
}
.range {
  width: 100%;
}
.bubble {
  background: red;
  color: white;
  padding: 4px 12px;
  position: absolute;
  border-radius: 4px;
  left: 50%;
  margin-top: 20px;
  transform: translateX(-50%);
}
.bubble::after {
  content: "";
  position: absolute;
  width: 2px;
  height: 2px;
  background: red;
  top: -1px;
  left: 50%;
}

body {
  margin: 2rem;
}
</style>