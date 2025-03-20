<template>
    <HeroSection />
    <MissionSection />
    <SocialImpactSection />
    <SubscriptionSection />
    <GraphicSection />
    <ListTecnificacionesHome />
</template>

<script>
import HeroSection from '../../components/home/HeroSection.vue';
import GraphicSection from '@/components/home/GraphicSection.vue';
import ListTecnificacionesHome from '@/components/home/ListTecnificacionesHome.vue';
import SocialImpactSection from '@/components/home/SocialImpactSection.vue';
import SubscriptionSection from '@/components/home/SubscriptionSection.vue';
import MissionSection from '@/components/home/MissionSection.vue';

export default {
    components: {
        HeroSection,
        MissionSection,
        GraphicSection,
        ListTecnificacionesHome,
        SocialImpactSection,
        SubscriptionSection
    }
};
</script>

<style scoped>
body {
    margin-top: 87px;
    background-color: rgb(245, 245, 245);
    font-family: 'Roboto', sans-serif;
}
</style>