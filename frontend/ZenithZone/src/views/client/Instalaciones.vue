<template>
    <main>
        <section>
            <div v-if="slug">
                <ul>
                    <li v-for="pista in pistas" :key="pista.id">{{ pista.nombre }}</li>
                </ul>
            </div>

            <div v-else>
                <p>Selecciona un deporte</p>
            </div>
        </section>
    </main>
</template>

<script>
import usePistas from '@/composables/client/usePistas';
import { onMounted } from 'vue';
import { useRoute } from 'vue-router';

export default {
    setup() {
        const route = useRoute();
        const slug = route.params.slug || undefined;

        if (slug) {
            const { pistas, fetchPistas } = usePistas(slug);

            onMounted(fetchPistas);

            return {
                pistas
            }
        }
    }
};
</script>

<style>
main {
    margin-top: 150px;
}
</style>