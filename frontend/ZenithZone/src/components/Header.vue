<template>
    <header class="header fixed-top shadow-sm">
        <div class="container-fluid">
            <div class="d-flex justify-content-between align-items-center p-3">
                <!-- Logo or App Name -->
                <router-link to="/home" class="text-color fw-bold h2 mb-0">
                    <img src="../assets/logo_2.png" alt="" class="logo">
                </router-link>

                <!-- Navigation Links -->
                <nav>
                    <ul class="nav me-5">
                        <li class="nav-item me-2">
                            <router-link to="/home" class="nav-link text-color fw-bold fs-3"
                                active-class="isActive text-black">
                                Home
                            </router-link>
                        </li>
                        <li class="nav-item me-2">
                            <router-link to="/instalaciones" class="nav-link text-color fw-bold fs-3"
                                active-class="isActive text-black">
                                Instalaciones
                            </router-link>
                        </li>
                        <li class="nav-item me-2">
                            <router-link to="/Servicios" class="nav-link text-color fw-bold fs-3"
                                active-class="isActive text-black">
                                Servicios
                            </router-link>
                        </li>
                        <li class="nav-item me-5">
                            <router-link to="/entrenadores" class="nav-link text-color fw-bold fs-3"
                                active-class="isActive text-black">
                                Entrenadores
                            </router-link>
                        </li>
                        <li class="nav-item">
                            <router-link to="/auth" class="nav-link auth fw-bold fs-3"
                                active-class="isActive text-black">
                                Unirse al club
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
.isActive {
    background-color: #ff6600;
    border-radius: 5px;
}

.text-color {
    color: #ff6600;
    transition: all 0.3s;

    &:hover {
        color: white;
    }
}

.auth {
    background-color: white;
    border-radius: 10px;
    color: #ff6600;
    padding: 10px 25px;
    transition: all 0.3s;

    &:hover {
        color: black;
        background-color: #ff6600;
    }
}

.header {
    background-color: rgb(20, 20, 20);
}

.logo {
    max-height: 90px;
    width: auto;
    transition: transform 0.3s;

    &:hover {
        transform: scale(1.1);
    }
}
</style>
