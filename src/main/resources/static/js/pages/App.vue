<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Journal</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items class="hidden-sm-and-down">
                {{ profile }}
                <v-btn href="/logout"><v-icon large color="blue">fa-sign-out-alt</v-icon></v-btn>
            </v-toolbar-items>
        </v-app-bar>
        <v-content>
            <post-list :posts="posts" />
        </v-content>
    </v-app>
</template>

<script>
    import PostList from "../components/posts/PostList.vue";
    import {addHandler} from "../util/ws";

    export default {
        name: "App",
        components: {
            PostList,
        },
        data() {
            return{
                posts: [],
                profile: profile.login,
                isEnabled: profile.enabled,
            }
        },
        created() {
            addHandler(data => {
                let index = this.posts.find(data.id);
                if(index !== undefined) {
                    this.posts.splice(index, 1, data)
                } else {
                    this.posts.push(data)
                }
            });
            postsPage.forEach(item => {
                this.posts.push(item)
            });
        },
    }
</script>

<style scoped>

</style>