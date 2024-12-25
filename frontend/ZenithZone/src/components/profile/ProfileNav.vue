<template>
    <main class="ProfileNav-main">

    </main>
    <nav class="btn-group justify-content-center align-items-center shadow bg-light col-12 mb-4">
        <router-link
            v-for="tab in tabs"
            :to="tab.route"
            class="btn fw-bold p-3"
            :class="{ active: route.name === tab.route.name }"
            :key="tab.name"
        >
            {{ tab.label }}
        </router-link>
    </nav> 

    <section class="container">
        <router-view />
    </section>
</template>

<script>
import { useRoute } from 'vue-router';

export default {
    props: {
        profile: {
            type: Object,
            required: true,
        },
        isCurrentUser: {
            type: Boolean,
            required: true
        }
    },

    setup() {
        const route = useRoute();

        /**
         *  Estas tabs son para moverse a los componentes del perfil
         */
        const tabs = [
            { name: 'graficas', label: 'Mis gráficas', route: { name: 'profileGraficas' } },
            { name: 'reservas', label: 'Mis Reservas', route: { name: 'profileReservas' } },
            { name: 'entrenamientos', label: 'Mis Entrenamientos', route: { name: 'profileEntrenamientos' } },
        ];

        return { tabs, route };
    }
}
</script> 

<style>
.ProfileNav-main{
    margin-top: 50px;
}

nav .btn {
    border: none;
    transition: all 0.3s ease;
}

nav .btn:hover {
    background-color: rgb(20, 20, 20);
    color: white;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

nav .btn.active {
    background-color: #ff6600;
    color: #fff;
}
</style>
