<template>
  <div class="row justify-content-center align-items-center text-center">
    <div class="col-md-12">
      <div class="card shadow-lg border-0 rounded-4 p-4 mx-4 my-3 hover-card">
        <div class="card-body">
          <div class="mb-4">
            <div class="profile-container position-relative mx-auto">
              <img v-if="entrenador.images[0]" :src="`/assets/entrenadores/${entrenador.images[0].imageUrl}`"
                class="rounded-circle custom-border border-primary shadow-sm w-100 h-100 object-fit-cover img-hover"
                alt="Perfil">
              <img v-else src="https://static.productionready.io/images/smiley-cyrus.jpg"
                class="rounded-circle custom-border border-primary shadow-sm w-100 h-100 object-fit-cover img-hover"
                alt="Perfil">
            </div>
          </div>

          <h1 class="fw-bold text-dark mb-2">{{ entrenador.nombre }} {{ entrenador.apellidos }}</h1>

          <span class="badge bg-primary px-3 py-2 rounded-pill fs-6 my-3">
            {{ entrenador.edad }} años
          </span>

          <p class="text-secondary mb-4 px-3">{{ entrenador.bio }}</p>

          <div class="d-flex align-items-center justify-content-center gap-2 bg-light p-3 rounded-3">
            <i class="bi bi-envelope text-primary"></i>
            <span>{{ entrenador.email }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import router from '@/router/router';
import { useRouter } from 'vue-router';

export default {
  props: {
    entrenador: {
      type: Object,
      required: true,
    },
  },

  setup(props) {
    const router = useRouter();

    const redirects = {
      profileEntrenador: () => router.push({ name: 'profileEntrenador', params: { numeroentrenador: props.entrenador.numeroentrenador } }),
    };

    return { redirects }
  }
}
</script>

<style scoped>
.profile-container {
  width: 150px;
  height: 150px;
}

.hover-card {
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.hover-card:hover {
  transform: translateY(-5px);
}

.img-hover {
  transition: transform 0.3s ease;
}

.img-hover:hover {
  transform: scale(1.1);
}

.custom-border {
  border: 4px solid;
}
</style>