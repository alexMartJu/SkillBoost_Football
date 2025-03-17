<template>
    <main class="main-recursos">
        <h1 class="visually-hidden">Recursos Deportivos</h1>
        <v-tabs v-model="activeTab" bg-color="primary" fixed-tabs stacked @update:model-value="changeTab">
            <v-tab value="subtipos">
                <v-icon>mdi-format-list-bulleted</v-icon>
                Subtipos de Tecnificación
            </v-tab>
            <v-tab value="entrenadores">
                <v-icon>mdi-account-group</v-icon>
                Entrenadores
            </v-tab>
            <v-tab value="pistas">
                <v-icon>mdi-stadium</v-icon>
                Pistas
            </v-tab>
        </v-tabs>

        <router-view></router-view>
    </main>
</template>

<script>
import { ref, computed, watch, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

export default {
    setup() {
        const route = useRoute();
        const router = useRouter();
        
        //Determinar la pestaña activa basada en la ruta actual
        const activeTab = computed(() => {
            //Extraer la primera parte del path después de /recursos/
            const path = route.path.split('/');
            if (path.length > 2) {
                const tab = path[2];
                if (['subtipos', 'entrenadores', 'pistas'].includes(tab)) {
                    return tab;
                }
            }
            return 'subtipos'; // Valor por defecto
        });

        //Función para cambiar de pestaña
        const changeTab = (newTab) => {
            const routeMap = {
                'subtipos': { name: 'recursosSubtipos' },
                'entrenadores': { name: 'recursosEntrenadores' },
                'pistas': { name: 'recursosPistas' }
            };
            
            router.push(routeMap[newTab]);
        };

        return {
            activeTab,
            changeTab
        };
    }
};
</script>

<style scoped>
.bg-primary {
    color: #000 !important;
}

.visually-hidden {
    position: absolute;
    width: 1px;
    height: 1px;
    padding: 0;
    margin: -1px;
    overflow: hidden;
    clip: rect(0, 0, 0, 0);
    white-space: nowrap;
    border: 0;
}
</style>
