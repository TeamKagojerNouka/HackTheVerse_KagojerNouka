<template>
    <v-row justify="center" class="mt-5">
        <v-col cols="5">
            <profile-card></profile-card>
        </v-col>
        <v-col offset="1" cols="3">
            <delivery-service-card></delivery-service-card>
        </v-col>
    </v-row>
</template> 

<script>
export default {
    components: {
        "profile-card": () => import("@/components/profile/ProfileCard.vue"),
        "delivery-service-card": () =>
            import("@/components/profile/DeliveryServiceCard.vue"),
    },
};
</script>

<style>
</style>