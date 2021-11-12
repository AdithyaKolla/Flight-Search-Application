<template>
  <div class="searchflight">
    <label>Departure From</label>
    <input type="text" v-model="departureInput" />
    <label>Fly to</label>
    <input type="text" v-model="destinationInput" />
    <button v-on:click="searchFlightHandler">Search</button>
  </div>
</template>
<script>
export default {
  name: "FlightType",
  data: function () {
    return {
      departureInput: "",
      destinationInput: "",
    };
  },
  methods: {
    searchFlightHandler: function () {
      var self = this;
      this.axios
        .get(
            // "https://google-flights-search.p.rapidapi.com/search?departure_airport_code=" +
            //   this.departureInput +
            //   "&arrival_airport_code=" +
            //   this.destinationInput +
            //   "&flight_class=Economy",
          "http://localhost:8080/api/search",
          {
            withCredentials: true,
            headers: {
              "Access-Control-Allow-Origin":
                "https://google-flights-search.p.rapidapi.com",
              "x-rapidapi-host": "google-flights-search.p.rapidapi.com",
              "x-rapidapi-key":
                "8f0213f6c4msh95ed04b8adf4eedp12310ejsn0c0109d43027",
              "x-rapidapi-ua": "RapidAPI-Playground",
            },
          }
        )
        .then(function (response) {
          self.$store.commit("setFlightSearchResponse", response.data.flights);
          self.$store.commit("setFilteredFlightSearchResponse", response.data.flights);
          self.$emit('refresh', "");
        });
    },
  },
};
</script>
