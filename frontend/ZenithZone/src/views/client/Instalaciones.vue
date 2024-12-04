<template>
    <main class="main-instalaciones">
        <ListPistas />
    </main>
</template>

<script>
import ListPistas from '../../components/ListPistas.vue';

export default {
    components: {
        ListPistas
    }
};
</script>

<style>
.main-instalaciones {
    margin-top: 50px;
}
</style>