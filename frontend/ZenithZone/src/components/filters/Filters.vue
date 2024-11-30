<template>
<div class="text-center">
    <filters @filters="ApplyFilters" @deleteFilters="resetFilters" :filters="filters_url" :meta="meta" />
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
    methods: {
        ApplyFilters(filters) {
            this.$emit('apply-filters', filters);
        },
        resetFilters() {
            this.$emit('reset-filters');
        }
    }
};
</script>
