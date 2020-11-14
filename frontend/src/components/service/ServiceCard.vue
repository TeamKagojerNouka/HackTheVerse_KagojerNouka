<template>
    <v-card class="ma-5">
        <v-card-title>{{ service["service_name"] }}</v-card-title>
        <v-card-subtitle>{{ service["category"] }}</v-card-subtitle>
        <v-divider class="my-2"></v-divider>
        <v-card-text>
            <span class="mr-5 text-body-2">Active Since</span>
            <span class="text-overline">{{ service["active_since"] }}</span>
        </v-card-text>
    </v-card>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component({
    name: "service-card",
})
export default class ServiceCard extends Vue {
    @Prop({ required: true })
    readonly service!: object;

    created() {
        console.log(this.service);
    }
}
</script>

<style lang="stylus" scoped>
.v-card__text {
    padding: 8px 16px;
}
</style>