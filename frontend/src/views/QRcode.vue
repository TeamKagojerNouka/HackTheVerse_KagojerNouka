<template>
    <v-row justify="center" class="mt-5">
        <v-col cols="4">
            <delivery-form></delivery-form>
        </v-col>
    </v-row>
</template>

<script lang="ts">
import Vue from "vue";
import DeliveryForm from "@/components/qrcode/DeliveryForm.vue";
import Component from "vue-class-component";

@Component({
    components: {
        DeliveryForm,
    },
    name: "qrcode",
})
export default class QRcode extends Vue {}
</script>

<style>
</style>