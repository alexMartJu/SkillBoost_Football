<template>
    <HeroSection />
    <MiddleSection />
        <!-- <ListPistas />
        <ListClases /> -->
</template>

<script>
import ListClases from '../../components/ListEntrenamientos.vue';
import ListPistas from '../../components/ListPistas.vue';
import HeroSection from '../../components/home/HeroSection.vue';
import MiddleSection from '@/components/home/MiddleSection.vue';

export default {
    components: {
        HeroSection,
        MiddleSection,
        ListClases,
        ListPistas
    }
};
</script>

<style>
body {
    margin-top: 80px;
    background-color: rgb(10, 10, 10);
}

</style>