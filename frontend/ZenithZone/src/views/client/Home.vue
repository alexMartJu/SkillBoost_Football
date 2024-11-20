<template>
    <HeroSection />
    <main class="container">

        <ListPistas />
        <ListClases />
    </main>
</template>

<script>
import ListClases from '../../components/ListClases.vue';
import ListPistas from '../../components/ListPistas.vue';
import HeroSection from '../../components/home/HeroSection.vue';

export default {
    components: {
        HeroSection,
        ListClases,
        ListPistas
    }
};
</script>

<style>
body {
    margin-top: 120px;
}
</style>