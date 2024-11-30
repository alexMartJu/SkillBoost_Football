<template>
<!-- <h1>hola</h1> -->
<div class="search-container">
    <input type="text" v-model="searchTerm" @input="updateSearch" placeholder="Buscar por nombre..." />
</div>
</template>


<script>
export default {
    props: {
        filters: Object
    },
    emits: ['search'],
    data() {
        return {
            searchTerm: this.filters.nombre || ''
        };
    },
    methods: {
        updateSearch() {
            this.filters.nombre = this.searchTerm;
            this.$emit('search', this.filters);
        },
    }
};
</script>


<style>
.search-container {
    display: flex;
    align-items: center;
    margin: 10px 0;
}

.search-container input {
    padding: 10px;
    margin-right: 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
    flex: 1;
}

.search-container button {
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}
</style>
