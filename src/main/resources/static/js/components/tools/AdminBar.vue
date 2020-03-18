<template>
    <v-navigation-drawer drawer color="blue" expand-on-hover right absolute width="10%">
        <v-list dense nav class="py-0">
            <v-list-item two-line>
                <v-list-item-content>
                    <v-list-item-title>Admin Bar</v-list-item-title>
                </v-list-item-content>
            </v-list-item>
            <v-divider></v-divider>
            <v-text-field label="Speed" outlined clearable :disabled="isStarted" v-model="speed"/>
            <v-list-item @click="artificialLoad">
                <v-list-item-icon>
                    <v-icon>{{ items[isStarted ? 1 : 0].icon }}</v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                    <v-list-item-title>{{ items[isStarted ? 1 : 0].title }}</v-list-item-title>
                </v-list-item-content>
            </v-list-item>
        </v-list>
    </v-navigation-drawer>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "AdminBar",
        data () {
            return {
                isStarted: false,
                speed: null,
                items: [
                    { title: 'Start', icon: 'fa-plane-departure' },
                    { title: 'Stop', icon: 'fa-plane-arrival' },
                ],
                index: 0,
            }
        },
        methods: {
            artificialLoad() {
                this.isStarted = !this.isStarted;
                axios.post('/load', {isStarted: this.isStarted, speed: this.speed});
                this.speed = null;
            }
        }
    }
</script>

<style scoped>

</style>