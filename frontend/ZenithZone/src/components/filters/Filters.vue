<template>
    <div class="card shadow-sm mb-4">
        <button class="btn btn-primary p-3" @click="toggleCollapse">
            <div class="d-flex align-items-center justify-content-center gap-3">
                <span class="spinner-grow spinner-grow-sm"></span>
                <span>{{ isCollapsed ? 'Descubre Nuevas Experiencias' : 'Ajustar Filtros' }}</span>
                <i :class="isCollapsed ? 'bi bi-chevron-down' : 'bi bi-chevron-up'"></i>
            </div>
        </button>
        
        <div class="collapse" :class="{ 'show': !isCollapsed }">
            <div class="card-body">
                <filters @filters="ApplyFilters" @deleteFilters="resetFilters" :filters="filters_url" :meta="meta" />
            </div>
        </div>
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

<style scoped>
/* Custom animations if needed */
.spinner-grow {
    animation: pulse 2s infinite;
}

@keyframes pulse {
    0% { transform: scale(1); opacity: 1; }
    50% { transform: scale(1.5); opacity: 0.5; }
    100% { transform: scale(1); opacity: 1; }
}

/* Custom brand colors */
.btn-primary {
    background: linear-gradient(45deg, #ff6600, #ff8533);
    border: none;
}

.btn-primary:hover {
    transform: translateY(-2px);
    background: linear-gradient(45deg, #ff8533, #ff6600);
}
</style> 

