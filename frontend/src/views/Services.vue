<template>
    <v-row class="mt-5">
        <v-col offset="1" cols="8">
            <service-suggestions
                v-on:suggest="updateServiceList"
            ></service-suggestions>
            <v-list v-if="!loading">
                <service-card
                    v-for="(service, index) in serviceList"
                    :key="index"
                    :service="service"
                ></service-card>
            </v-list>
        </v-col>
    </v-row>
</template>

<script lang="ts">
import Vue from "vue";
import Component from "vue-class-component";
import ServiceCard from "@/components/service/ServiceCard.vue";
import ServiceSuggestions from "@/components/service/ServiceSuggestions.vue";
import { getAllServices } from "@/api/service.api";

@Component({
    components: {
        ServiceCard,
        ServiceSuggestions,
    },
    name: "services",
})
export default class Services extends Vue {
    serviceList: object[] | undefined;
    service: undefined;
    loading = true;

    updateServiceList(list: object[]) {
        this.loading = true;
        this.serviceList = list;
        this.loading = false;
    }

    async loadServiceList() {
        const response = await getAllServices();
        this.serviceList = response.data;
        this.loading = false;
    }

    created() {
        this.loadServiceList();
    }
}
</script>
