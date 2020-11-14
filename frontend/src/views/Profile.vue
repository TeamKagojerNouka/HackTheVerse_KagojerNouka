<template>
    <v-row justify="center" class="mt-5">
        <v-col cols="5">
            <profile-card></profile-card>
            <delivery-list></delivery-list>
        </v-col>
        <v-col offset="1" cols="3">
            <delivery-service-card></delivery-service-card>
        </v-col>
    </v-row>
</template> 

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import ProfileCard from "@/components/profile/ProfileCard.vue";
import DeliveryServiceCard from "@/components/profile/DeliveryServiceCard.vue";
import DeliveryList from "@/components/profile/DeliveryList.vue";

@Component({
    components: {
        ProfileCard,
        DeliveryServiceCard,
        DeliveryList,
    },
    name: "profile",
})
export default class Profile extends Vue {}
</script>

<style>
</style>