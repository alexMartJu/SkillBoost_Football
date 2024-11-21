<template>
    <HeroSection />
    <MiddleSection />
    <Olympics />
</template>

<script>
import Olympics from '../../components/home/Olympics.vue';
import ListClases from '../../components/ListEntrenamientos.vue';
import ListPistas from '../../components/ListPistas.vue';
import HeroSection from '../../components/home/HeroSection.vue';
import MiddleSection from '@/components/home/MiddleSection.vue';

export default {
    components: {
        HeroSection,
        MiddleSection,
        Olympics,
    }
};
</script>

<style>
body {
    margin-top: 80px;
    background-color: rgb(10, 10, 10);
}

</style>