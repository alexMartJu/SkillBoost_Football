import express from 'express';
import routes from './routes/routes';
import './jobs/cronJobs'; 
import dotenv from 'dotenv';

dotenv.config();
const app = express();
const port = process.env.PORT || 3030;

//Middleware
app.use(express.json());

//Rutas
app.use('/api', routes);

//Ruta de health check
app.get('/health', (req, res) => {
  res.status(200).json({ status: 'Servidor funcionando correctamente' });
});

//Iniciar el servidor
app.listen(port, () => {
  console.log(`Servidor de verificación ejecutándose en http://localhost:${port}`);
});