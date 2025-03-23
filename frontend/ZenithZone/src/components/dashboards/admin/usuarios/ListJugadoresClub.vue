<template>
    <UserListBase title="Jugadores de Club" subtitle="Administra los jugadores de club de la plataforma"
        userType="jugadorClub" getterName="getJugadoresClub" :actionName="actionName"
        searchPlaceholder="Buscar jugador de club..." buttonColor="success"
        loadingMessage="Cargando jugadores de club..." emptyIcon="trophy"
        emptyMessage="No se encontraron jugadores de club que coincidan con tu búsqueda."
        :searchFields="searchFields" />
</template>

<script>
import { ref } from 'vue';
import UserListBase from './UserListBase.vue';
import Constant from '@/Constant';

export default {
    name: 'ListJugadoresClub',
    components: {
        UserListBase
    },
    setup() {
        const actionName = ref(Constant.FETCH_JUGADORES_CLUB);
        const searchFields = ref(['nombre', 'apellidos', 'numeroSocio', 'clubOrigen', 'posicionPreferida']);
        
        return {
            actionName,
            searchFields
        };
    }
};
</script>
