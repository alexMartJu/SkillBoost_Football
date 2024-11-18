<template>
    <main class="container">
        <ListPistas />
        <ListClases />        
    </main>
</template>

<script>
import ListClases from '../../components/ListClases.vue';
import ListPistas from '../../components/ListPistas.vue';

export default {
    components: {
        ListClases,
        ListPistas
    }
};
</script>

<style>
main {
    margin-top: 50px;
}
</style>