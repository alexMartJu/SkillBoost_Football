
 
 <template>
    <div>
        <h2>{{ formMode === 'create' ? 'Crear Deporte' : 'Editar Deporte' }}</h2>
        <form @submit.prevent="formMode === 'create' ? crearDeporte() : actualizarDeporte()">
        <input v-model="nuevoDeporte.nombre" placeholder="Nombre del deporte" required />
        <input v-model="nuevoDeporte.images" placeholder="URL de la imagen" />
        <button type="submit">{{ formMode === 'create' ? 'Crear Deporte' : 'Actualizar Deporte' }}</button>
        </form>
    </div>
    </template>

    <script>
    export default {
    name: 'FormDeportes.vue',
    props: {
        formMode: {
        type: String,
        required: true
        },
        currentDeporteSlug: {
        type: String,
        required: false
        },
        deporteData: { 
        type: Object,
        required: false
        }

    },
    watch: {
        deporteData: {  
        immediate: true,
        handler(newData){
            if (newData) {
                console.log("newdata"+JSON.stringify(newData));
                this.nuevoDeporte.nombre = newData.nombre
                this.nuevoDeporte.images = newData.images && newData.images.length > 0 ? newData.images[0].image_url : '';
            }

        }
        }
    },
    data() {
        return {
        nuevoDeporte: { nombre: '' }
        };
    },
    methods: {
        async crearDeporte() {
        if (this.nuevoDeporte.nombre) {
            try {
            await this.$store.dispatch('adminDashboard/CreateOneDeporte', this.nuevoDeporte);
            this.nuevoDeporte.nombre = ''; // Reset form after submission
            } catch (error) {
            console.error('Error creando deporte:', error);
            }
        }
        },

        async actualizarDeporte() {
        if (this.nuevoDeporte.nombre && this.currentDeporteSlug) {
            try {
            await this.$store.dispatch('adminDashboard/UpdateOneDeporte', { slug: this.currentDeporteSlug, data: this.nuevoDeporte });
            this.nuevoDeporte.nombre = ''; 
            this.$router.push('/admin');

            } catch (error) {
            console.error('Error actualizando deporte:', error);
            }
        }
        }
    }
    };
    </script>
    
    <style scoped>
  
    div {
      max-width: 500px;
      margin: 0 auto;
      padding: 20px;
      margin-bottom: 30px;
      background-color: #f9f9f9;
      border-radius: 8px;
      box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
      display: flex;
      flex-direction: column;
      align-items: center;
    }
    
    h2 {
      font-size: 1.8em;
      color: #333;
      margin-bottom: 15px;
      text-align: center;
      font-weight: 600;
    }
    
    
    form {
      width: 100%;
      display: flex;
      flex-direction: column;
      align-items: center;
    }
    
    input {
      width: 100%;
      padding: 10px;
      margin: 10px 0;
      font-size: 1em;
      border: 1px solid #ddd;
      border-radius: 4px;
      box-sizing: border-box;
    }
    
    button[type="submit"] {
      padding: 12px 20px;
      font-size: 1em;
      background-color: #007bff;
      color: #fff;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      transition: background-color 0.3s ease;
      width: 100%;
      max-width: 200px;
      margin-top: 20px;
    }
    
    button[type="submit"]:hover {
      background-color: #0056b3;
    }
    
   
    .image-preview {
      display: flex;
      justify-content: center;
      margin-top: 15px;
    }
    
    .image-preview img {
      max-width: 100%;
      max-height: 200px;
      border-radius: 8px;
      box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    }
    </style>