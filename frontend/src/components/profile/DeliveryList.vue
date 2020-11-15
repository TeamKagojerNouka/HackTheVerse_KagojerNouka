<template>
    <v-list v-if="!loading">
        <div class="mt-5 text-body-1">Past Deliveries</div>
        <v-list-item
            v-for="(delivery, index) in deliveryList"
            :key="index"
            class="my-2"
            width="100%"
        >
            <v-list-item-content>
                <v-list-item-title
                    >{{ delivery["address"] }}
                </v-list-item-title>
                <v-list-item-subtitle>{{
                    new Date(Date.parse(delivery["datetime"])).toDateString()
                }}</v-list-item-subtitle>
            </v-list-item-content>
            <v-list-item-action>
                <v-icon v-if="delivery['success']">mdi-check-bold</v-icon>
                <v-icon v-else>mdi-close-thick</v-icon>
            </v-list-item-action>
        </v-list-item>
    </v-list>
</template>

<script lang="ts">
import Vue from "vue";
import { getDeliveryList } from "@/api/delivery.api";
import Component from "vue-class-component";

@Component({
    name: "delivery-list",
})
export default class DeliveryList extends Vue {
    deliveryList: object[] | undefined;
    loading = true;

    async loadDeliveryList() {
        const response = await getDeliveryList();
        this.deliveryList = response.data;
        this.loading = false;
        console.log(this.deliveryList);
    }

    created() {
        this.loadDeliveryList();
    }
}
</script>

<style lang="stylus" scoped>
.v-card__subtitle {
    padding: 8px 16px;
}
</style>