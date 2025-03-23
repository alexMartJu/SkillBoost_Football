<template>
    <UserListBase title="Jugadores" subtitle="Administra los jugadores de la plataforma" userType="jugador"
        getterName="getJugadores" :actionName="actionName" searchPlaceholder="Buscar jugador..."
        buttonColor="primary" loadingMessage="Cargando jugadores..." emptyIcon="person"
        emptyMessage="No se encontraron jugadores que coincidan con tu búsqueda." />
</template>

<script>
import { ref } from 'vue';
import UserListBase from './UserListBase.vue';
import Constant from '@/Constant';

export default {
    name: 'ListJugadores',
    components: {
        UserListBase
    },
    setup() {
        const actionName = ref(Constant.FETCH_JUGADORES);
        
        return {
            actionName
        };
    }
};
</script>