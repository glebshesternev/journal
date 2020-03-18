import Vue from 'vue'
import App from "./pages/App.vue";
import {connect} from "./util/ws";
import vuetify from "./util/vuetify";
import router from "./util/router";

connect();

new Vue({
    el: '#app',
    vuetify,
    router,
    render: a => a(App)
});