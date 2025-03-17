<template>
  <div class="container mt-4">
    <ListSubtiposTecnificacion :slug="slug" />
  </div>
</template>

<script>
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import ListSubtiposTecnificacion from '../../components/shared/ListSubtiposTecnificacion.vue';

export default {
  name: 'recursosSubtipos',
  components: {
    ListSubtiposTecnificacion
  },
  setup() {
    const route = useRoute();

    const slug = computed(() => route.params.slug);

    return {
      slug
    };
  }
};
</script>

<style scoped></style>