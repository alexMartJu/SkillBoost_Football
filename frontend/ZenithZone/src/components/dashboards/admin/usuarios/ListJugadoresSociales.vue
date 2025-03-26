<template>
    <UserListBase title="Jugadores Sociales" subtitle="Administra los jugadores sociales de la plataforma"
        userType="jugadorSocial" getterName="getJugadoresSociales" :actionName="actionName"
        searchPlaceholder="Buscar jugador social..." buttonColor="warning"
        loadingMessage="Cargando jugadores sociales..." emptyIcon="heart"
        emptyMessage="No se encontraron jugadores sociales que coincidan con tu búsqueda."
        :searchFields="searchFields" />
</template>

<script>
import { ref } from 'vue';
import UserListBase from './UserListBase.vue';
import Constant from '@/Constant';

export default {
    name: 'ListJugadoresSociales',
    components: {
        UserListBase
    },
    setup() {
        const actionName = ref(Constant.FETCH_JUGADORES_SOCIALES);
        const searchFields = ref(['nombre', 'apellidos', 'numeroSocio', 'organizacionOrigen']);
        
        return {
            actionName,
            searchFields
        };
    }
};
</script>
