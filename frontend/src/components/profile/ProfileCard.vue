<template>
    <v-card>
        <v-card-title>{{ businessName }}</v-card-title>
        <v-card-subtitle>{{ category }}</v-card-subtitle>
        <v-divider class="my-2"></v-divider>
        <v-card-text>
            <span class="mr-5 text-body-2">Location</span>
            <span class="text-overline">{{ address }}</span>
        </v-card-text>
        <v-card-text>
            <span class="mr-5 text-body-2">Active Since</span>
            <span class="text-overline">{{ activeSince }}</span>
        </v-card-text>
    </v-card>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";

enum BusinessCategory {
    clothing = "Clothing",
    foodAndDrinks = "Food & Drinks",
    accessories = "Accessories",
}

@Component
export default class ProfileCard extends Vue {
    businessName = "Nestle";
    category = BusinessCategory.foodAndDrinks;
    activeSince = new Date().toDateString();
    address = "Kakoli, Dhaka, Bangladesh";
}
</script>

<style lang="stylus" scoped>
.v-card__text {
    padding: 8px 16px;
}
</style>