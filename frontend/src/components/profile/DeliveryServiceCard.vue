<template>
    <v-card color="accent" dark>
        <v-card-subtitle class="pb-0">Default Delivery Service</v-card-subtitle>
        <v-card-title class="py-2">{{ serviceName }}</v-card-title>
        <v-divider light class="my-2"></v-divider>
        <v-card-text>
            <span class="mr-5 text-body-2">Active Since</span>
            <span class="text-overline">{{ activeSince }}</span>
        </v-card-text>
    </v-card>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";

@Component
export default class DeliveryServiceCard extends Vue {
    serviceName = "Pathao";
    activeSince = new Date().toDateString();
}
</script>

<style lang="stylus" scoped>
.v-card__text {
    padding: 8px 16px;
}

.v-card__subtitle, .v-card__text {
    color: white !important;
}
</style>