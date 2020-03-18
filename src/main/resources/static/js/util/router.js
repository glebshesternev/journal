import Vue from 'vue'
import VueRouter from 'vue-router'
import App from "../pages/App.vue"
import NewPost from '../pages/NewPost.vue'


Vue.use(VueRouter);

const routes = [
    // { path: '/posts', component: App },
    { path: '/new-post', component: NewPost },
    { path: '*', component: App}
];

export default new VueRouter({
    mode: 'history',
    routes
})