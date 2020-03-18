<template>
    <v-layout align-start justify-start column>
        <v-app-bar app dense>
            <v-btn depressed @click="all()">all</v-btn>
            <v-btn depressed @click="none()">none</v-btn>
            <v-spacer/>
            <v-toolbar-title>JOURNAL</v-toolbar-title>
            <v-spacer/>
            <v-tooltip bottom>
                <template v-slot:activator="{ on }">
                    <span id="profile" class="font-italic font-weight-bold" v-on="on">{{ profile }}</span>
                </template>
                <span>username</span>
            </v-tooltip>
            <v-toolbar-items class="hidden-sm-and-down">
                <v-btn depressed href="/logout">
                    <v-icon left color="grey darken-3">
                        fa-sign-out-alt
                    </v-icon>EXIT</v-btn>
            </v-toolbar-items>
        </v-app-bar>
        <v-expansion-panels v-model="panel" multiple popout focusable>
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
        <v-btn id="add-post" min-width="98%" depressed color="blue" to="/new-post">
            Add post
        </v-btn>
    </v-layout>
</template>

<script>
    export default {
        name: "PostList",
        props: ['posts'],
        data() {
            return {
                panel: [],
                profile: profile.login,
            }
        },
        methods: {
            getAuthorId(post) {
                if(post.author !== null) {
                    return `User's login : ` + post.author.substr(17, 4)
                }
            },
            all() {
                this.panel = [...Array(this.posts.length).keys()].map((k, i) => i)
            },
            none() {
                this.panel = []
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
    #profile {
        margin-right: 0.2%
    }
</style>