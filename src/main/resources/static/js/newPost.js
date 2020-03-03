function getIndex(list, id) {
    for (let i=0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

const URL = 'http://localhost:8080/newPost';

Vue.component('post-form', {
    props: ['postAttr'],
    data: function() {
        return {
            title: '',
            text: '',
        }
    },
    watch: {
        postAttr: function(newVal) {
            this.title = newVal.title;
            this.text = newVal.text;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Enter post title" v-model="title" />' +
        '<input type="text" placeholder="Type something" v-model="text" />' +
        '<input type="button" value="Post" @click="post" />' +
        '</div>',
    methods: {
        post: function() {
            let post = {
                data: this.text,
                name: this.title
            };
            console.log(post);
            axios.post(`${URL}`, post).then( () => {
                    this.title = '';
                    this.text = '';
                }
            )
        }
    }
});

let app = new Vue({
    el: '#app',
    template:
        '<post-form />',
});