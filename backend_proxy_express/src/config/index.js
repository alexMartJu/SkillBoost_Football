const dotenv = require('dotenv');

//Cargar variables de entorno desde .env
dotenv.config();

module.exports = {
  URL_PROXY: process.env.URL_PROXY,
  PORT_PROXY: process.env.PORT_PROXY || 4000,
  URL_SPRING_MAIN: process.env.URL_SPRING_MAIN,
  PORT_SPRING_MAIN: process.env.PORT_SPRING_MAIN,
  URL_SPRING_PAYMENTS: process.env.URL_SPRING_PAYMENTS,
  PORT_SPRING_PAYMENTS: process.env.PORT_SPRING_PAYMENTS,
  URL_EXPRESS_NOTIFICATIONS: process.env.URL_EXPRESS_NOTIFICATIONS,
  PORT_EXPRESS_NOTIFICATIONS: process.env.PORT_EXPRESS_NOTIFICATIONS
};