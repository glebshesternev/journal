import Vue from 'vue'
import App from "./pages/App.vue";
import {connect} from "./util/ws";
import 'vuetify/dist/vuetify.min.css'

connect();

new Vue({
    el: '#app',
    render: a => a(App)
});