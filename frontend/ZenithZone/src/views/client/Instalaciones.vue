<template>
    <main class="main-instalaciones">
        <ListPistas />
    </main>
</template>

<script>
import { usePistas } from '@/composables/client/usePistas.js';
import { onMounted } from 'vue';
import { useRoute } from 'vue-router';
import ListPistas from '../../components/ListPistas.vue';

export default {
    components: {
        ListPistas
    },

    setup() {
        const route = useRoute();
        const slug = route.params.slug || undefined;

        if (slug) {
            const { pistas, fetchPistas } = usePistas(slug);
            console.log(pistas);

            onMounted(fetchPistas);

            return {
                pistas,
                slug
            }
        }
    }
};
</script>

<style>
.main-instalaciones {
    margin-top: 50px;
}
</style>