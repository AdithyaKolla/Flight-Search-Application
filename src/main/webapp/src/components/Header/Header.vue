<template>
<div style="padding-left:20px; display: flex; justify-content: space-between;">
    <h3 style="color: rgba(240,242,231,255);   text-align: center; font-family: 'Oswald', sans-serif">Flight Finder</h3>
    <div v-if="isAuthenticated">
        <button class="btn btn-light btn-md btn-block" v-on:click="signout">Sign out</button>
    </div>
    <div v-else-if="currentRouteName != 'login' && currentRouteName != 'signup'">
        <button class="btn btn-light btn-md btn-block" v-on:click="signin">Sign In</button>
    </div>
</div>
</template>

<script>
export default {
    computed: {
        isAuthenticated() {
            return this.$store.getters.isAuthenticated
        },
        currentRouteName() {
            return this.$route.name;
        }
    },
    methods: {
        signout: function () {
            var self = this;
            this.axios.post("http://localhost:8080/signout").then(function () {
                self.$store.commit("setAuthenticated", false);
                self.$store.commit("setFirstName", null);
                self.$store.commit("setLastName", null);
                self.$store.commit("setEmailAddress", null);
                self.$router.push({
                    name: "home"
                });
            })
        },
        signin: function () {
            this.$router.push({
                name: "login"
            });
        }
    }
}
</script>
