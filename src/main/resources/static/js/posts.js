import Vue from "vue-resource/src";

let postApi = Vue.resource('/post{/id}');

Vue.component('post', {
    props: ['post', 'editMethod', 'posts'],
    template: '<div>' +
        '<i>({{ post.id }})</i> {{ post.text }}' +
        '<span style="position: absolute; right: 0">' +
            '<input type="button" value="Edit" @click="edit" />' +
            '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.post);
        },
        del: function() {
            postApi.remove({id: this.post.id}).then(result => {
                if (result.ok) {
                    this.posts.splice(this.posts.indexOf(this.post), 1)
                }
            })
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
        '<div style="position: relative ; width: 300px;">' +
            '<post-form :posts="posts" :postAttr="post" />' +
            '<post v-for="post in posts" :key="post.id" :post="post" :editMethod="editMethod" ' +
            ':posts="posts" />' +
        '</div>',

    methods: {
        editMethod: function(post) {
            this.post = post;
        }
    }
});

let app = new Vue({
    el: '#app',
    template:
        '<div>' +
        '<div v-if="!profile">Необходимо <a href="/login">авторизоваться</a></div>' +
        '<post-list v-else:posts="posts" />' +
        '</div>',
    data: {
        posts: frontendData.posts,
        profile: frontendData.profile
    },
    created: function() {
        postApi.get().then(result =>
            result.json().then(data =>
                data.forEach(post => this.posts.push(post))
            )
        )
    }
});