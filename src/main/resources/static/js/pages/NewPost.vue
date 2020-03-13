<template>
    <div>
        <label>
            <input type="text" placeholder="Enter post title" v-model="title" />
        </label>
        <label>
            <input type="text" placeholder="Type something" v-model="text" />
        </label>
        <input type="button" value="Post" @click="post" />
    </div>
</template>

<script>
    import NewPost from "../components/posts/NewPost.vue";

    export default {
        name: "NewPost",
        components: {
            NewPost,
        }
    }
</script>

<style scoped>

</style>