<template>
    <v-container>
        <v-input v-model="title">Enter post title</v-input>
        <v-input v-model="text">Type something</v-input>
        <v-btn @click="post">Post</v-btn>
    </v-container>
</template>

<script>
    import {sendPost} from "../util/ws";

    export default {
        name: "NewPost",
        data() {
            return {
                postAttr: {
                    title: '',
                    text: '',
                }
            }
        },
        watch: {
            postAttr(newVal) {
                this.postAttr.title = newVal.title;
                this.postAttr.text = newVal.text;
            }
        },
        methods: {
            post() {
                sendPost({data: this.postAttr.text, name: this.postAttr.title});
                this.postAttr.title = this.postAttr.text = '';
                /*                let post = {
                                    data: this.text,
                                    name: this.title
                                };
                                axios.post(`${URL}`, post).then(() => {
                                    this.title = '';
                                    this.text = '';
                                })*/
            }
        }
    }
</script>

<style scoped>

</style>