<template>
    <div v-if="loading"></div>
    <v-card v-else>
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
import { getBusinessDetails } from "@/api/business.api";

@Component({
    name: "profile-card",
})
export default class ProfileCard extends Vue {
    businessName: string | undefined;
    category: string | undefined;
    activeSince: string | undefined;
    address: string | undefined;
    loading = true;

    async loadDetails(): Promise<void> {
        const response = await getBusinessDetails(1);
        const business = response.data[0];
        this.businessName = business["business_name"];
        this.category = business["category"];
        const date = new Date(business["active_since"]);
        this.activeSince = date.toDateString();
        this.address = business["address"];
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
</style>