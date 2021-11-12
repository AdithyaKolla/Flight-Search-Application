<template>
<form class="inner-block" id="signup" @submit.prevent="checkForm" method="POST">

    <p v-if="form.errors.length">
        <b>Please correct the following error(s):</b>
        <ul>
            <li v-for="(error) in form.errors" :key="error">
                {{error}}
            </li>
        </ul>
    </p>

    <div class="form-group mb-2 mt-3">
        <label>Username</label>
        <input id="userName" v-model="form.userName" type="text" name="form.userName" class="form-control form-control-sm">
    </div>

    <div class="form-group mb-2">
        <label>Password</label>
        <input id="password" v-model="form.password" type="password" name="form.password" class="form-control form-control-sm">
    </div>

    <div class="form-group mb-2">
        <label>Firstname</label>
        <input id="firstName" v-model="form.firstName" type="text" name="form.firstName" class="form-control form-control-sm">
    </div>

    <div class="form-group mb-2">
        <label>Lastname</label>
        <input id="lastName" v-model="form.lastName" type="text" name="form.lastName" class="form-control form-control-sm">
    </div>

    <div class="form-group mb-2">
        <label>Email address</label>
        <input id="emailAddress" v-model="form.emailAddress" type="email" name="form.emailAddress" class="form-control form-control-sm">
    </div>

    <button type="submit" class="btn btn-dark">Submit</button>
    <br />

    <p class="mt-3">Already have an account? <span>
            <router-link class="pr-3" to="/login">Login</router-link>
        </span></p>

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
                firstName: null,
                lastName: null,
                emailAddress: null,
            }
        };
    },
    methods: {
        checkForm(e) {
            this.form.errors = [];

            if (!this.form.userName) {
                this.form.errors.push("Username is required.");
            }
            if (!this.form.password) {
                this.form.errors.push("Password is required.");
            }
            if (!this.form.firstName) {
                this.form.errors.push("First name is required.");
            }
            if (!this.form.lastName) {
                this.form.errors.push("Last name is required.");
            }
            if (!this.form.emailAddress) {
                this.form.errors.push("Email Address is required.");
            }

            if (this.form.errors.length == 0) {
                var self = this;
                this.axios.post("http://localhost:8080/signup", this.form).then(function (response) {
                    self.$store.commit("setAuthenticated", true);
                    self.$store.commit("setFirstName", response.data.firstName);
                    self.$store.commit("setLastName", response.data.lastName);
                    self.$store.commit("setEmailAddress", response.data.emailAddress);
                    self.$router.push({
                        name: "home"
                    });
                }).catch(
                    function (error) {
                        self.form.errors.push("Signup failed");
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