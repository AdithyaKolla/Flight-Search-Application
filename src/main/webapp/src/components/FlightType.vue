<template>
    <div class="flight-type">
        <input type="radio" name="flightType" value="one-way" v-on:click="radiobuttonhandler"> 
        <label>One way flight</label>
        <br>
        <input type="radio" name="flightType" value="round" v-on:click="radiobuttonhandler">
        <label>Round Trip</label>
    </div>
</template>
<script>
    export default {
        name:"FlightType",
        methods:{
            radiobuttonhandler: function(event){
                console.log(event.target.value)
            }
        }
    }
</script>
