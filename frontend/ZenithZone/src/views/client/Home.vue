<template>
    <HeroSection />
    <MiddleSection />
    <Olympics />
    <GraphicSection />
</template>

<script>
import Olympics from '../../components/home/Olympics.vue';
import ListClases from '../../components/ListEntrenamientos.vue';
import ListPistas from '../../components/ListPistas.vue';
import HeroSection from '../../components/home/HeroSection.vue';
import MiddleSection from '@/components/home/MiddleSection.vue';
import GraphicSection from '@/components/home/GraphicSection.vue';

export default {
    components: {
        HeroSection,
        MiddleSection,
        Olympics,
        GraphicSection
    }
};
</script>

<style>
body {
    margin-top: 80px;
    background-color: rgb(245, 245, 245);
}

</style>