import Vue from 'vue'
import App from "./pages/App.vue";
import {connect} from "./util/ws";
import vuetify from "./util/vuetify";
import NewPost from "./pages/NewPost.vue";

connect();

new Vue({
    el: '#app',
    vuetify,
    NewPost,
    render: a => a(App)
});