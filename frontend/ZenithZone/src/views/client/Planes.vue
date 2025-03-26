<template>
    <div class="planes-container">
        <div class="container py-5">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
export default {
    name: 'PlanesContainer'
}
</script>

<style scoped>
.planes-container {
    background-color: #f8f9fa;
    min-height: 100vh;
}
</style>