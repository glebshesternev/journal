<template>
    <v-container>

    </v-container>
</template>

<script>
    import Comment from "../components/comments/Comment";

    export default {
        name: "PostInfo",
        props: ['post'],
        components: {
            Comment
        },
        data() {
            return {
                id: '',
                postsId: this.post.id,
                title: this.post.name,
                text: this.post.data,
                comments: new Map(),
            }
        }
    }
</script>

<style scoped>

</style>