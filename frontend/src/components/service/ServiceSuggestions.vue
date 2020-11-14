<template>
    <div>
        <span class="text-h6 ml-4">Registered Delivery Services</span>
        <v-form class="mt-5">
            <v-toolbar flat class="align-center">
                <v-select
                    class="mr-5"
                    dense
                    hide-details
                    label="delivery location"
                    v-model="location"
                    :items="deliveryLocations"
                    solo-inverted
                ></v-select>
                <v-select
                    class="mr-5"
                    dense
                    hide-details
                    label="category"
                    v-model="category"
                    :items="serviceCategories"
                    solo-inverted
                ></v-select>
                <v-text-field
                    class="mr-5"
                    dense
                    hide-details
                    label="time (in minutes)"
                    v-model="time"
                    solo-inverted
                ></v-text-field>
                <v-btn color="accent" dark @click="submit">filter</v-btn>
            </v-toolbar>
        </v-form>
    </div>
</template>

<script lang="ts">
import Vue from "vue";
import { VForm } from "@/types";
import { Component, Ref } from "vue-property-decorator";
import { getServiceSuggestions } from "@/api/suggest.api";
import { getServiceDetails } from "@/api/service.api";
import categories from "@/constants/category";
import locations from "@/constants/location";

@Component({
    name: "service-suggestions",
})
export default class ServiceSuggestions extends Vue {
    @Ref("form") readonly form!: VForm;

    private location: string | undefined;
    private category: string | undefined;
    private time: number | undefined;
    private serviceCategories = categories;
    private deliveryLocations = locations;

    async submit() {
        const response = await getServiceSuggestions(
            this.location ?? "",
            this.category ?? "",
            this.time ?? 0
        );

        console.log(response.data.length);
        const serviceList = [];
        for (let i = 0; i < response.data.length; i++) {
            const serviceResponse = await getServiceDetails(
                response.data[i]["id"]
            );
            serviceList.push(serviceResponse.data[0]);
        }
        console.log(serviceList);
        this.$emit("suggest", serviceList);
    }
}
</script>

<style>
</style>