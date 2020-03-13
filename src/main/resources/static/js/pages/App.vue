<template>
    <post-list :posts="posts" />
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
                posts: []
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