import Vue from "vue-resource/src";

function getIndex(list, id) {
    for (let i=0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

let postApi = Vue.resource('/post{/id}');

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
        '<input type="text" placeholder="Type something" v-model="text" />' +
        '<input type="button" value="Post" @click="post" />' +
        '</div>',
    methods: {
        post: function() {
            let post = { text: this.text };

            if (this.id) {
                postApi.update({id: this.id}, post).then(result =>
                    result.json().then(data => {
                        let index = getIndex(this.posts, data.id);
                        this.posts.splice(index, 1, data);
                        this.text = '';
                        this.id = ''
                    })
                )
            } else {
                postApi.save({}, post).then(result =>
                    result.json().then(data => {
                        this.posts.push(data);
                        this.text = '';
                    })
                )
            }
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