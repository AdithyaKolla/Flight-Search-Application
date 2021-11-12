<template>
<Header class="header" />
<div class="wrapper container" v-if="isLoaded">
    <div>
        <router-view />
    </div>
</div>
<div class="vue-template" v-else>
    Loading...
</div>
<Footer class="footer" />
</template>

<script>
import Header from './components/Header/Header.vue'
import Footer from './components/Footer/Footer.vue'


export default {
    name: "App",
    computed: {
        isLoaded() {
            return this.$store.getters.isLoaded
        }
    },
    components: {
        Header,
        Footer,
    },
    beforeCreate() {
        var self = this;
        this.axios.get("http://localhost:8080/user-details", {
            withCredentials: true
        }).then(function (response) {
            self.$store.commit("setAuthenticated", true);
            self.$store.commit("setFirstName", response.data.firstName);
            self.$store.commit("setLastName", response.data.lastName);
            self.$store.commit("setEmailAddress", response.data.emailAddress);
            self.$store.commit("setLoaded", true);
        }).catch(function () {
            self.$store.commit("setLoaded", true);
        })
    }
};
</script>