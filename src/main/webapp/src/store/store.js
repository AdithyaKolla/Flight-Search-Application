import Vuex from 'vuex'
// import Vue from 'vue';

const mutations = {
  setAuthenticated(state, authenticated) {
    state.authenticated = authenticated;
  },
  setFirstName(state, firstName) {
    state.firstName = firstName;
  },
  setLastName(state, lastName) {
    state.lastName = lastName;
  },
  setEmailAddress(state, emailAddress) {
    state.emailAddress = emailAddress;
  },
  setLoaded(state, isLoaded) {
    state.isLoaded = isLoaded;
  },
  setFlightSearchResponse(state, flightSearchResponse) {
    state.flightSearchResponse = flightSearchResponse;
  },
  setFilteredFlightSearchResponse(state, filteredFlightSearchResponse) {
    state.filteredFlightSearchResponse = filteredFlightSearchResponse;
  }
}

const store = new Vuex.Store({
  state: {
    authenticated: false,
    firstName: null,
    lastName: null,
    emailAddress: null,
    isLoaded: false,
    flightSearchResponse: null,
    filteredFlightSearchResponse: null
  },
  mutations: mutations,
  getters: {
    isAuthenticated(state) {
      return state.authenticated;
    },
    getFirstName(state) {
      return state.firstName;
    },
    getLastName(state) {
      return state.lastName;
    },
    getEmailAddress(state) {
      return state.emailAddress;
    },
    isLoaded(state) {
      return state.isLoaded;
    },
    getFlightSearchResponse(state) {
      return state != null ?  state.flightSearchResponse : null;
    },
    getFilteredFlightSearchResponse(state) {
      return state != null ?  state.filteredFlightSearchResponse : null;
    }
  }
})

export default store