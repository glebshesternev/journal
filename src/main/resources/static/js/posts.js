const URL = 'http://localhost:8080';

Vue.component('post', {
    props: ['post'],
    data: function() {
        return {
            id: ''
        }
    },
    template:
        '<div>' +
            '{{ post }}' +
        '</div>',
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
            '<post v-for="post in posts" :key="post.id" :post="post" />' +
            '<input type="button" value="Add Post" @click="newPost" />' +
        '</div>',
    methods: {
        newPost: function() {
            window.location.href=`${URL}/newPost`
        },
    },
});

let app = new Vue({
    el: '#app',
    template:
        '<post-list :posts="posts" />',
    data: {
        posts: [],
    },
    created: function () {
        allPosts.forEach(item => this.posts.push(item.data));
    }
});