<template>
    <v-navigation-drawer drawer color="blue" expand-on-hover right absolute width="10%">
        <v-list dense nav @click="!isStarted"  class="py-0">
            <v-list-item two-line>
                <v-list-item-content>
                    <v-list-item-title>Admin Bar</v-list-item-title>
                </v-list-item-content>
            </v-list-item>
            <v-divider></v-divider>
            <v-text-field label="Speed" clearable outlined/>
            <v-list-item v-if="!isStarted" @click="isStarted = !isStarted">
                <v-list-item-icon>
                    <v-icon>{{ items[0].icon }}</v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                    <v-list-item-title>{{ items[0].title }}</v-list-item-title>
                </v-list-item-content>
            </v-list-item>
            <v-list-item v-else @click="isStarted = !isStarted">
                <v-list-item-icon>
                    <v-icon>{{ items[1].icon }}</v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                    <v-list-item-title>{{ items[1].title }}</v-list-item-title>
                </v-list-item-content>
            </v-list-item>
        </v-list>
    </v-navigation-drawer>
</template>

<script>
    export default {
        name: "AdminBar",
        data () {
            return {
                isStarted: false,
                items: [
                    { title: 'Start', icon: 'fa-plane-departure' },
                    { title: 'Stop', icon: 'fa-plane-arrival' },
                ],
                index: 0,
            }
        },
    }
</script>

<style scoped>

</style>