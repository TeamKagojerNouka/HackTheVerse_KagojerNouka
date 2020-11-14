<template>
    <v-app>
        <v-app-bar app color="primary" dark>
            <div class="d-flex align-center">
                <router-link to="/" exact>
                    <v-avatar size="30" color="white" class="mr-2"></v-avatar>
                    <span class="white--text">Kagojer Nouka</span>
                </router-link>
            </div>

            <v-spacer></v-spacer>

            <v-tabs icons-and-text right style="width: 60%" optional>
                <v-tab to="/qrcode" exact>
                    <span class="tab-text">QR Code</span>
                    <v-icon>mdi-qrcode</v-icon>
                </v-tab>

                <v-tab to="/business-profile" exact>
                    <span class="tab-text">Profile</span>
                    <v-icon>mdi-account-circle</v-icon>
                </v-tab>

                <v-tab to="/delivery-services" exact>
                    <span class="tab-text">services</span>
                    <v-icon>mdi-view-dashboard</v-icon>
                </v-tab>
            </v-tabs>
        </v-app-bar>

        <v-main>
            <router-view></router-view>
        </v-main>
    </v-app>
</template>

<script lang="ts">
import Vue from "vue";
import { testWorkingApi } from "@/api/test.api";

export default Vue.extend({
    name: "App",

    data: () => ({
        //
    }),
    created() {
        testWorkingApi()
            .then((value) => console.log(value))
            .catch((error) => console.log(error));
    },
});
</script>

<style lang="stylus" scoped>
.tab-text {
    font-size: 0.7rem;
}

.theme--dark.v-tabs > .v-tabs-bar .v-tab:not(.v-tab--active) > .v-icon {
    color: rgba(255, 255, 255, 0.8) !important;
}

.theme--dark.v-tabs > .v-tabs-bar .v-tab:not(.v-tab--active) {
    color: rgba(255, 255, 255, 0.8) !important;
}
</style>