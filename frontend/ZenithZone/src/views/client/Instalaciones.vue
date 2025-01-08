<template>
    <main class="main-instalaciones">
        <ListPistas />
    </main>
</template>

<script>
import ListPistas from '../../components/shared/ListPistas.vue';

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

body {
        margin-top: 80px;
        background-color: rgb(245, 245, 245);
        font-family: 'Roboto', sans-serif;
    }
</style>