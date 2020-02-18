import Vue from "vue-resource/src";

//function getIndex

let postApi = Vue.resource('/post/{id}');

Vue.component('post-form', {
    props: ['posts', 'postAttr'],
    data: function() {
        return {
            text: '',
            id: ''
        }
    },
    watch: {
        postAttr: function(newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder="Type something" v-model="text"/>' +
            '<input type="button" value="Post" @click="post" />' +
        '</div>',
    methods: {
        click: function () {
            let post = { text: this.text };

            if (this.id) {
                postApi.update({id: this.id}, post).then(result =>
                    result.json().then(data => {
                        this.post.push(data);
                    })
                )
            } else {
                postApi.save({}, post).then(result =>
                    result.json().then(data => {
                        this.posts.push(data);
                    })
                )
            }
        }
    }
});

Vue.component('post', {
    props: ['post', 'editMethod'],
    template: '<div>' +
        '<i>({{ post.id }})</i> {{ post.text }}' +
        '<span>' +
            '<input type="button" value="Edit" @click="edit" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.post);
        }
    }
});

Vue.component('post-list', {
    props: ['posts'],
    data: function() {
        return {
            post: null
        }
    },
    template:
        '<div>' +
            '<post-form :posts="posts" :postAttr="post" />' +
            '<post v-for="post in posts" :key="post.id" :post="post" :editMethod="" />' +
        '</div>',
    created: function () {
        postApi.get().then(result =>
            result.json().then(data =>
                data.forEach(post => this.posts.push(post))
            )
        )
    },
    methods: {
        editMethod: function () {
            this.post = post;
        }
    }
});

let app = new Vue({
    el: '#app',
    template: '<post-list :posts="posts" />',
    data: {
        posts: []
    }
});