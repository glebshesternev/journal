<template>
    <v-app>
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
    }
</script>

<style scoped>

</style>