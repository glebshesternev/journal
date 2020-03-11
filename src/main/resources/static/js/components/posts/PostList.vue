<template>
    <div style="position: relative ; width: 300px;">
        <post v-for="post in posts" :key="post.id" :post="post" />
        <input type="button" value="Add Post" @click="newPost" />
    </div>
</template>

<script>
    import Post from "./Post.vue";

    export default {
        name: "PostList",
        components: {
            Post,
        },
        props: ['posts'],
        methods: {
            newPost() {
                window.location.href=`http://localhost:8080/newPost`
            },
        },
    }
</script>

<style scoped>

</style>