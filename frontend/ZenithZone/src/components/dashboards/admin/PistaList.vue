<!-- <template>
    <div>
      <ul>
        <li v-for="pista in pistas" :key="pista.id">
          <p>{{ pista.nombre }}</p>
        </li>
      </ul>
    </div>
  </template>
   -->
   <template>
    <div>
      <ul class="pista-list">
        <li v-for="pista in pistas" :key="pista.id">
          <p>{{ pista.nombre }}</p>
  
          <!-- Mostrar deportes asociados -->
          <div v-if="pista.deportes && pista.deportes.length">
            <div class="deportes-asociados">
              <p><strong>Deportes Asociados:</strong></p>
              <ul>
                <li v-for="deporte in pista.deportes" :key="deporte.id">
                  {{ deporte.nombre }}
                </li>
              </ul>
            </div>
          </div>
          <div v-else>
            <p>No hay deportes asociados.</p>
          </div>
        </li>
      </ul>
    </div>
  </template>
  <script>
  export default {
    name: 'PistaList',
    props: {
      pistas: Array
    }
  };
  </script>
  <style scoped>
  /* Estilos para la lista de pistas */
  .pista-list {
    padding: 0;
    list-style-type: none;
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    margin-top: 20px;
    max-height: 500px; /* Limitar la altura máxima */
    overflow-y: auto;  /* Permitir desplazamiento si la lista es muy larga */
  }
  
  .pista-list li {
    background-color: #fff;
    padding: 15px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    width: calc(33% - 20px); /* Tres elementos por fila */
    transition: transform 0.2s ease, box-shadow 0.2s ease;
    cursor: pointer;
  }
  
  .pista-list li:hover {
    transform: translateY(-5px);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  }
  
  .pista-name {
    font-size: 1.2em;
    font-weight: bold;
    color: #333;
  }
  
  .deportes-asociados {
    margin-top: 10px;
    font-size: 1em;
    color: #666;
    padding-left: 20px;
  }
  
  .deportes-asociados ul {
    padding-left: 20px;
    list-style-type: none;
  }
  
  .deportes-asociados li {
    font-size: 0.9em;
    color: #333;
  }
  
  .pista-list li p {
    margin: 0;
    padding: 0;
  }
  
  
  @media (max-width: 768px) {
    .pista-list li {
      width: calc(50% - 20px); 
    }
  }
  
  
  @media (max-width: 480px) {
    .pista-list li {
      width: 100%; 
    }
  }
  </style>
  