<template>
<form class="inner-block" id="login" @submit.prevent="checkForm" method="POST">

    <p v-if="form.errors.length">
        <b>Please correct the following error(s):</b>
        <ul>

            <li v-for="(error) in form.errors" :key="error">
                {{error}}
            </li>

        </ul>
    </p>

    <h3 style="text-align:center">Sign In</h3>

    <div class="form-group mb-3 mt-3">
        <label>Username</label>
        <input id="userName" v-model="form.userName" type="text" name="form.userName" class="form-control form-control-sm">
    </div>

    <div class="form-group mb-3">
        <label>Password</label>
        <input id="password" v-model="form.password" type="password" name="form.password" class="form-control form-control-sm">
    </div>

    <button type="submit" class="btn btn-dark">Submit</button>

    <br />

    <p class="mt-3">Need an account? <span><router-link class="pr-3" to="/signup">Sign Up</router-link></span></p>

</form>
</template>

<script>
export default {
    data() {
        return {
            form: {
                errors: [],
                userName: null,
                password: null,
            }
        };
    },
    methods: {
        checkForm(e) {
            this.form.errors = [];

            if (!this.form.userName) {
                this.form.errors.push("userName required.");
            }
            if (!this.form.password) {
                this.form.errors.push("password required.");
            }

            if (this.form.errors.length == 0) {
                var self = this;
                this.axios.post("http://localhost:8080/login", this.form).then(function (response) {
                    self.$store.commit("setAuthenticated", true);
                    self.$store.commit("setFirstName", response.data.firstName);
                    self.$store.commit("setLastName", response.data.lastName);
                    self.$store.commit("setEmailAddress", response.data.emailAddress);
                    self.$router.push({
                        name: "home"
                    });
                }).catch(
                    function (error) {
                        self.form.errors.push("Login failed");
                        self.form.password = null;
                        return Promise.reject(error)
                    }
                );
            }

            e.preventDefault();
        },
    },
};
</script>
