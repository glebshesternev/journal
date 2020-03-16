<template>
    <v-layout align-start justify-start column>
        <v-expansion-panels multiple popout focusable>
            <v-expansion-panel v-for="post in posts" :key="post.id">
                <v-expansion-panel-header>
                    <v-container>
                        <v-row>
                            <v-col>
                                {{ getAuthorId(post) }}
                            </v-col>
                            <v-col>
                                {{ post.name }}
                            </v-col>
                            <v-col>
                                <v-badge top overlap color="blue" :content=post.commentsCount.toString()>
                                    <v-icon id="comments">
                                        far fa-comments
                                    </v-icon>
                                </v-badge>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-expansion-panel-header>
                <v-expansion-panel-content>{{ post.data }}</v-expansion-panel-content>
            </v-expansion-panel>
        </v-expansion-panels>
        <!--        <post v-for="(post,index) in posts[0]" :key="post.id" :post="post" :commentsCount="posts[1][index].toString()"/>-->
        <v-btn id="add-post" min-width="98%" depressed color="blue" href="http://localhost:8080/newPost">
            Add post
        </v-btn>
    </v-layout>
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
            getAuthorId(post) {
                if(post.author !== null) {
                    return `User's login : ` + post.author.substr(17, 4)
                }
            }
        }
    }
</script>

<style scoped>
    #add-post {
        margin: 1% 1%
    }
    #comments {
        clear: both
    }
</style>