<template>
    <v-form ref="form" v-model="valid" lazy-validation>
        <v-menu
            v-model="menu"
            transition="scale-transition"
            offset-y
            min-width="290px"
        >
            <template v-slot:activator="{ on, attrs }">
                <v-text-field
                    v-model="date"
                    label="Delivery date"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                    :rules="[(v) => !!v || 'This field is required']"
                    required
                ></v-text-field>
            </template>
            <v-date-picker
                ref="picker"
                v-model="date"
                :min="new Date().toISOString().substr(0, 10)"
                @change="save"
            ></v-date-picker>
        </v-menu>

        <v-text-field
            v-model="address"
            label="Address"
            prepend-icon="mdi-map-marker"
            :rules="[(v) => !!v || 'This field is required']"
            required
        ></v-text-field>

        <v-text-field
            v-model="payment"
            label="Payment"
            prepend-icon="mdi-cash"
            :rules="[(v) => !!v || 'This field is required']"
            required
        ></v-text-field>

        <v-text-field
            v-model="customerPhone"
            label="Customer Phone No."
            prepend-icon="mdi-phone"
            :rules="[(v) => !!v || 'This field is required']"
            required
        ></v-text-field>

        <v-textarea
            rows="2"
            v-model="deliveryNote"
            label="Delivery Note"
            prepend-icon="mdi-note"
            :rules="[(v) => !!v || 'This field is required']"
            required
        ></v-textarea>

        <v-btn class="my-5" block color="secondary" @click="submit">
            Generate QR Code
        </v-btn>
    </v-form>
</template>

<script lang="ts">
import Vue from "vue";
import { VForm } from "@/types";
import { Component, Ref } from "vue-property-decorator";

@Component
export default class DeliveryForm extends Vue {
    @Ref("form") readonly form!: VForm;
    private date: string | undefined;
    private address: string | undefined;
    private payment: number | undefined;
    private customerPhone: number | undefined;
    private deliveryNote: string | undefined;
    private menu = false;
    private valid = false;

    private save(date: string) {
        this.date = date;
    }

    private submit() {
        if (this.form.validate()) {
            // TODO : call generate qr code api
        }
    }
}
</script>

<style>
</style>