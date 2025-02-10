import { createApp } from 'vue';
import App from './App.vue';
import router from './router/router';
import store from './store';
import VueLazyLoad from 'vue3-lazyload';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../styles/custom.scss';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';


const app = createApp(App);

// Plugins
app.use(router);
app.use(store);
app.use(VueLazyLoad, {
    loading: '/path/to/loading-image.png',
    error: '/path/to/error-image.png',
});

// Montar la app
app.mount('#app');
