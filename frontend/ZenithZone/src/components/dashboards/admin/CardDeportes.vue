    <template>
        <div class="card-deporte">
            <img v-if="deporte.images && deporte.images.length > 0" 
            :src="'/assets/deportes/' + deporte.images[0].image_url" 
            alt="Imagen de Deporte" class="card-deporte__image" />
        <h3>{{ deporte.nombre }}</h3>
        <button @click="editarDeporte(deporte)">Editar</button>
        <button @click="confirmarEliminar(deporte.slug)">Eliminar</button>
        </div>
    </template>
    
    <script>
    export default {
        name: 'CardDeportes',
        props: {
        deporte: Object
        },
        methods: {
        editarDeporte(deporte) {
            this.$emit('editarDeporte', deporte);
            this.$router.push({ name: 'adminEditar', params: { slug: deporte.slug, type:'deporte'} });
        },
        confirmarEliminar(deporteSlug) {
        if (confirm("¿Estás seguro de que deseas eliminar este deporte?")) {
            this.eliminarDeporte(deporteSlug);
        }
        },
        eliminarDeporte(deporteSlug) {
            this.$store.dispatch('adminDashboard/DeteleOneDeporte', deporteSlug   )
            .then(() => console.log("Deporte eliminado correctamente"))
            .catch(error => console.error("Error al eliminar el deporte:", error));
        }
        }
    };
    </script>
    
  <style scoped>
.card-deporte {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
  cursor: pointer;
}

.card-deporte:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.card-deporte button {
  margin-top: 10px;
  padding: 5px 10px;
  border: none;
  background-color: #007bff;
  color: white;
  border-radius: 4px;
  cursor: pointer;
}

.card-deporte button:hover {
  background-color: #0056b3;
}
</style>

<style scoped>
.card-deporte {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
  cursor: pointer;
  text-align: center;
}

.card-deporte:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.card-deporte__image {
  width: 100%;
  height: 200px; /* Tamaño fijo para la altura */
  object-fit: cover; /* Asegura que las imágenes mantengan la proporción sin distorsionarse */
  border-radius: 8px;
  margin-bottom: 15px;
}

.card-deporte button {
  margin-top: 10px;
  padding: 5px 10px;
  border: none;
  background-color: #007bff;
  color: white;
  border-radius: 4px;
  cursor: pointer;
}

.card-deporte button:hover {
  background-color: #0056b3;
}
</style>