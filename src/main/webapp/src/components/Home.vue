<template>
  <div v-if="isAuthenticated">
    <p>Welcome {{ firstName }}</p>
    <div id="page-head">
      <img src="../assets/AirplaneLogo.jpg" id="AirplaneLogo" />
    </div>
    <div>
      <SearchFlight v-on:refresh="refresh"></SearchFlight>
      <FlightType></FlightType>
    </div>
    <div class="container1">
      <div class="fixed">
        <Filters v-on:refresh="refresh"></Filters>
      </div>
      <div class="flex-item">
        <Headings></Headings>
      </div>
    </div>
  </div>
</template>

<script>
import FlightType from "./FlightType.vue";
import SearchFlight from "./SearchFlight.vue";
import Headings from "./Headings.vue";
import Filters from "./Filters.vue";

export default {
  components: {
    FlightType,
    SearchFlight,
    Headings,
    Filters,
  },
  data() {
    return {
      authenticated: this.$store.getters.isAuthenticated,
      firstName: this.$store.getters.getFirstName,
    };
  },
  computed: {
    isAuthenticated() {
      return this.$store.getters.isAuthenticated;
    },
  },
  methods: {
    refresh: function () {
      this.$forceUpdate();
    },
  },
};
</script>

<style>
.container1 {
  display: flex;
  border: 1px solid rgb(207, 190, 190);
  padding: 7px 0px;
  margin-top: 25px;
}
.fixed {
  width: 350px;
  margin: 10px 20px;
  border: 1px solid rgb(207, 190, 190);
  border-radius: 3%;
  padding: 10px;
}
.flex-item {
  flex-grow: 1;
  margin: 10px 20px;
}
#AirplaneLogo {
  height: 300px;
  width: 800px;
}
#page-head {
  width: fit-content;
  margin: 0 auto;
}
</style>
