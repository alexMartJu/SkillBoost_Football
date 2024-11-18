<template>
    <main>
        <section>
            <ListDeportes />
        </section>
    </main>
</template>

<script>
import ListDeportes from '../../components/ListDeportes.vue';

export default {
    components: {
        ListDeportes
    }
};
</script>

<style>
main {
    margin-top: 50px;
}
</style>