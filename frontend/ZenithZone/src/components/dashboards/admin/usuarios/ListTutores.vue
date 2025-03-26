<template>
    <UserListBase title="Tutores" subtitle="Administra los tutores de la plataforma" userType="tutor"
        getterName="getTutores" :actionName="actionName" searchPlaceholder="Buscar tutor..."
        buttonColor="info" loadingMessage="Cargando tutores..." emptyIcon="people"
        emptyMessage="No se encontraron tutores que coincidan con tu búsqueda." />
</template>

<script>
import { ref } from 'vue';
import UserListBase from './UserListBase.vue';
import Constant from '@/Constant';

export default {
    name: 'ListTutores',
    components: {
        UserListBase
    },
    setup() {
        const actionName = ref(Constant.FETCH_TUTORES);
        
        return {
            actionName
        };
    }
};
</script>
