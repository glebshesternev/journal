<template>
    <v-app>
        <v-app-bar app dense>
            <v-btn depressed @click="all">all</v-btn>
            <v-btn depressed @click="none">none</v-btn>
            <v-spacer/>
            <v-toolbar-title>JOURNAL</v-toolbar-title>
            <v-spacer/>
            <v-toolbar-items class="hidden-sm-and-down">
                <!--                {{ profile }}-->
                <v-btn depressed href="/logout">
                    <v-icon left color="grey darken-3">
                        fa-sign-out-alt
                    </v-icon>EXIT</v-btn>
            </v-toolbar-items>
        </v-app-bar>
        <v-content>
            <v-container>
                <admin-bar v-if="profile === 'test'"/>
                <post-list :posts="posts"/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import PostList from "../components/posts/PostList.vue";
    import {addHandler} from "../util/ws";
    import AdminBar from "../components/tools/AdminBar.vue";

    export default {
        name: "App",
        components: {
            AdminBar,
            PostList,
        },
        data() {
            return{
                panel: [],
                posts: [],
                profile: profile.login,
                isEnabled: profile.enabled,
            }
        },
        created() {
            addHandler(data => {
                let index = this.posts.find(data.id);
                if(typeof(index) !== 'undefined') {
                    this.posts.splice(index, 1, data)
                } else {
                    this.posts.push(data)
                }
            });
            postsPage.forEach(item => {
                this.posts.push(item);
            });
        },
        methods: {
            all() {
                this.panel = [...Array(this.items).keys()].map((k, i) => i)
            },
            none() {
                this.panel = []
            },
        }
    }
</script>

<style scoped>

</style>