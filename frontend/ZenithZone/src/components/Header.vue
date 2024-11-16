<template>
<header class="bg-primary shadow-sm">
    <div class="container-fluid">
        <div class="d-flex justify-content-between align-items-center p-3">
            <!-- Logo or App Name -->
            <div class="text-white h4 mb-0">
                ZenithZone
            </div>

            <!-- Navigation Links -->
            <nav>
                <ul class="nav">
                    <li class="nav-item">
                        <router-link to="/home" class="nav-link text-white hover:text-light">
                            Home
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link to="/instalaciones" class="nav-link text-white hover:text-light">
                            Instalaciones
                        </router-link>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
</template>

<script>
export default {
    name: "Header",
};
</script>
 
<style scoped>
/* Optional: Custom styles if needed */
</style>
