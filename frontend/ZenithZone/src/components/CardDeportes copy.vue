<template>
    <main>
        <div class="card mb-4">
            <div class="card-header">
                <h1>{{ deporte.nombre }}</h1>
            </div>
            <div class="card-body row shadow">
                <!-- Pistas Section -->
                <section class="mb-3">
                    <strong>Pistas</strong>
                    <div class="row g-3">
                        <div v-for="pista in deporte.pistas" :key="pista.id" class="col-md-4">
                            <div class="card fw-bold">
                                <div class="card-body">
                                    {{ pista.nombre }}
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <!-- Clases Section -->
                <section class="mb-3">
                    <strong>Clases</strong>
                    <div class="row g-3">
                        <section class="col-md-12">
                            <div v-for="clase in deporte.clases" :key="clase.id" class="col-md-6">
                                <div class="card">
                                    <div class="card-body ">
                                        <p><strong>Nombre:</strong> {{ clase.nombre }}</p>
                                        <p><strong>Descripción:</strong> {{ clase.descripcion }}</p>
                                        <p><strong>Duración:</strong> {{ clase.duracion }} minutos</p>
                                        <p><strong>Plazas totales:</strong> {{ clase.maxPlazas }}</p>
                                        <p><strong>Precio:</strong> {{ clase.precio }}€</p>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                </section>
            </div>
        </div>
    </main>
</template>

<script>
export default {
    props: {
        deporte: {
            type: Object,
            required: true
        }
    }
};
</script>

<style scoped>
.card {
    padding: 0;
    margin: 0;
    border-radius: 10px;
    border: 2px solid rgb(100, 100, 100);
}

.card-body {
    margin: 0;
}

.mb-3 {
    margin-bottom: 1rem;
}

.img-deporte {
    margin: 0;

    border-left: 2px solid black;
}
</style>
