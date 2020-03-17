<template>
    <div>
        {{ title }} : {{ text }}
        <v-toolbar flat height="40">
            <v-toolbar-items>
                <v-btn value="View comments" @click="() => { showComments = !showComments; viewComments() }">
                    <v-badge top overlap color="blue" :content=commentsCount>
                        <v-icon style="clear: both">
                            far fa-comments
                        </v-icon>
                    </v-badge>
                </v-btn>
                <transition name="fade">
                    <div v-if="showComments">
                        <comment v-for="(comment, index) in currentComments" :key="index" :comment="comment" />
                    </div>
                </transition>
            </v-toolbar-items>
        </v-toolbar>
    </div>
</template>

<script>
    import axios from 'axios';
    import Comment from "../comments/Comment.vue";

    export default {
        name: "Post",
        components: {
            Comment
        },
        props: ['post', 'commentsCount'],
        data() {
            return {
                id: '',
                postsId: this.post.id,
                title: this.post.name,
                text: this.post.data,
                showComments: false,
                comments: new Map(),
                currentComments: null,
            }
        },
        methods: {
            viewComments() {
                if(this.comments.get(this.postsId) === undefined) {
                    axios.get(`http://localhost:8080/posts/${this.postsId}`).then((response) => {
                        this.comments.set(this.postsId, response.data.map((obj) => {
                            return obj.data
                        }));
                        this.currentComments = this.comments.get(this.postsId);
                    });
                }
                else{
                    this.currentComments = this.comments.get(this.postsId);
                }
            }
        }
    }
</script>

<style scoped>
    .fade-enter-active, .fade-leave-active {
        transition: opacity .5s;
    }
    .fade-enter, .fade-leave-to {
        opacity: 0;
    }
</style>