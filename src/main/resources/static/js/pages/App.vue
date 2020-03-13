<template>
    <post-list :posts="posts" />
</template>

<script>
    import PostList from "../components/posts/PostList.vue";

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
            postsPage.forEach(item => {
                this.posts.push(item)
            });
        },
    }
</script>

<style scoped>

</style>