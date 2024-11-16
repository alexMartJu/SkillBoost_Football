import { createApp } from 'vue';
import App from './App.vue';
import router from './router/router';
import store from './store';
import VueLazyLoad from 'vue3-lazyload';
import Swal from 'sweetalert2';
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import './assets/main.scss';

// Iconos
import { faHeart, faComment } from '@fortawesome/free-solid-svg-icons';
library.add(faHeart);
library.add(faComment);

const app = createApp(App);

// Plugins
app.use(router);
app.use(store);
app.use(VueLazyLoad, {
    loading: '/path/to/loading-image.png',
    error: '/path/to/error-image.png',
});

// Componentes
app.component('font-awesome-icon', FontAwesomeIcon);

// Montar la app
app.mount('#app');
