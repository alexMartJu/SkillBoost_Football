<template>
    <div class="text-center">
        <button @click="toggleCollapse">
            {{ isCollapsed ? 'Mostrar filtros' : 'Ocultar filtros' }}
        </button>
        <transition name="collapse">
            <div v-show="!isCollapsed">
                <filters @filters="ApplyFilters" @deleteFilters="resetFilters" :filters="filters_url" :meta="meta" />
            </div>
        </transition>
    </div>
</template>


<script>
import Filters from './FiltersMeta.vue';

export default {
    components: {
        Filters
    },
    props: {
        filters_url: Object,
        meta: Object,
    },

    data() {
        return {
            isCollapsed: false
        };
    },

    methods: {
        ApplyFilters(filters) {
            this.$emit('apply-filters', filters);
        },
        resetFilters() {
            this.$emit('reset-filters');
        },
        toggleCollapse() {
            this.isCollapsed = !this.isCollapsed;
        }
    }
};
</script>

<style>
.collapse-enter-active,
.collapse-leave-active {
    transition: max-height 0.3s ease, opacity 0.3s ease;
    overflow: hidden;
}

.collapse-enter, .collapse-leave-to /* .collapse-leave-active en versiones anteriores */ {
    max-height: 0;
    opacity: 0;
}

.collapse-enter-from {
    max-height: 0;
    opacity: 0;
}

.collapse-leave-from {
    max-height: 1200px; /* Ajusta según el contenido */
    opacity: 1;
}

</style>