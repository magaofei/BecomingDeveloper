Vue.config.devtools = true

const NotFound = { template: '<p>Page not found</p>' }
const Home = { template: '<p>home page</p>' }
const About = { template: '<p>about page</p>' }

const routes = {
    '/': home,
    '/about': About
}

var app = new Vue({
    el: '#app',
    data: {

        message: 'Hello Vue!',
        currentRoute: window.location.pathname
    },
    computed: {
        ViewComponent () {
            return routes[this.currentRoute] || NotFound
        }
    },
    render (h) { return h(this.ViewComponent) }
})

var app2 = new Vue({
    el: '#app-2',
    data: {
        message: '页面加载于' + new Date()
    }
})

var app3 = new Vue({
    el: '#app-3',
    data: {
        seen: false
    }
})

var app4 = new Vue({
    el: '#app-4',
    data: {
        todos: [
            {text: '整个牛项目'},
            {text: '23123123'},
            {text: '12312312'}

        ]
    }
})

var app5 = new Vue({
    el: '#app-5',
    data: {
        message: 'Hello Vue.js!'
    },
    methods: {
        reverseMessage: function () {
            // body...
            this.message= this.message.split('').reverse().join('')
        }
    }
})

var app6 = new Vue({
    el: '#app-6',
    data: {
        message: 'Hello Vue'
    }
})


Vue.component('todo-item', {
    props: ['todo'],
    template: '<li>{{ todo.text }}</li>'
})
var app7 = new Vue({
    el: '#app-7',
    data: {
        groceryList: [
            { id: 0, text: '蔬菜'},
            { id: 1, text: '奶酪'},
            { id: 2, text: '随便其他什么人吃的东西'},

        ]
    }
})