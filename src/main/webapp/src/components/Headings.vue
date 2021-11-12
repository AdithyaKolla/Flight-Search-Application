<template>
  <div class="table-headings">
    <table>
      <thead>
        <tr>
          <th>Airline</th>
          <th>Flight Date</th>
          <th>Departing Time</th>
          <th>Arrival Time</th>
          <th>Number of Passengers</th>
          <th>Cabin Class</th>
        </tr>
      </thead>
      <tbody v-if="flightSearchResponse != null">
        <tr
          v-for="(flight, index) in flightSearchResponse"
          :key="index"
        >
          <td>
            {{ flight.airlines }}
          </td>
          <td>
            {{ flight.departure_date }}
          </td>
          <td>
            {{ flight.departure_time }}
          </td>
          <td>
            {{ flight.arrival_time }}
          </td>
          <td>
            {{ "" }}
          </td>
          <td>
            {{ flight.cabin_class }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>


<script>
export default {
  computed: {
    flightSearchResponse() {
      return this.$store.getters.getFilteredFlightSearchResponse;
    },
  },
};
</script>

<style scoped>
table,
tr,
th {
  border: 1px solid rgb(207, 190, 190);
  border-radius: 5%;
}
table {
  width: 100%;
  font-size: 1rem;
  text-align: center;
}
</style>