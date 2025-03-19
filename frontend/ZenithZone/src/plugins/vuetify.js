// Styles
// import 'vuetify/styles'
import '@mdi/font/css/materialdesignicons.css'

// Vuetify
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

export default createVuetify({
  components,
  directives,
  theme: {
    defaultTheme: 'customTheme',
    themes: {
      customTheme: {
        dark: false,
        colors: {
          primary: '#ff6600', //Color bootstrap
          success: '#0066CC'
        }
      }
    }
  },
  //Configuración para que los componentes no apliquen colores por defecto
  defaults: {
    VBtn: {
      variant: 'text',
    },
    VCard: {
      variant: 'text',
    },
  },
  //Iconos
  icons: {
    defaultSet: 'mdi',
  },
})