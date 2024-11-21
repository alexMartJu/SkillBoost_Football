<template>
    <main class="container">
        <h1>Dashboard</h1>
        <DashboardAdmin />
    </main>
</template>

<script>
import DashboardAdmin from '../../components/dashboards/admin/AdminDashboard.vue';

export default {
    components: {
        DashboardAdmin
    }
};
</script>

<style>
main {
    margin-top: 50px;
}
</style>