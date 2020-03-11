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
    import axios from 'axios';

    export default {
        name: "NewPost",
        props: ['postAttr'],
        data() {
            return {
                title: '',
                text: '',
            }
        },
        watch: {
            postAttr(newVal) {
                this.title = newVal.title;
                this.text = newVal.text;
            }
        },
        methods: {
            post() {
                let post = {
                    data: this.text,
                    name: this.title
                };
                axios.post(`${URL}`, post).then(() => {
                    this.title = '';
                    this.text = '';
                })
            }
        }
    }
</script>

<style scoped>

</style>