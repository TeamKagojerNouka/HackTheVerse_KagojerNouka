<template>
    <div v-if="loading"></div>
    <v-card v-else color="accent" dark>
        <v-card-subtitle class="pb-0">Default Delivery Service</v-card-subtitle>
        <v-card-title class="py-2">{{ serviceName }}</v-card-title>
        <v-card-subtitle>{{ category }}</v-card-subtitle>
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
import { getServiceDetails } from "@/api/service.api";

@Component({
    name: "delivery-service-card",
})
export default class DeliveryServiceCard extends Vue {
    serviceName: string | undefined;
    category: string | undefined;
    activeSince: string | undefined;
    loading = true;

    async loadDetails(): Promise<void> {
        const response = await getServiceDetails(1);
        const service = response.data[0];
        this.serviceName = service["service_name"];
        this.category = service["category"];
        const date = new Date(service["active_since"]);
        this.activeSince = date.toDateString();
        this.loading = false;
    }

    created() {
        this.loadDetails();
    }
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