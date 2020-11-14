<template>
    <v-row justify="center" class="mt-5">
        <v-col cols="4">
            <delivery-form></delivery-form>
        </v-col>
    </v-row>
</template>

<script>
export default {
    components: {
        "delivery-form": () => import("@/components/qrcode/DeliveryForm.vue"),
    },
};
</script>

<style>
</style>