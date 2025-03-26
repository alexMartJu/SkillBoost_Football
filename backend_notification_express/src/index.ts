import express, { Request, Response, NextFunction } from 'express';
import cors from 'cors';
import dotenv from 'dotenv'; 
import routes from './routes';
import { errorHandler } from './middlewares/errorMiddleware';
import { AppError } from './utils/AppError';

(BigInt.prototype as any).toJSON = function() {
    return this.toString();
};

dotenv.config();

const corsOptions = {
    origin: process.env.CORSURL, 
    methods: ['GET', 'POST', 'PUT', 'DELETE', 'PATCH', 'OPTIONS'],
    optionsSuccessStatus: 200
};

const app = express();
app.use(cors(corsOptions));
app.use(express.json());
app.options('*', cors(corsOptions));   

//Rutas de la API
app.use('/api', routes);

//Middleware para rutas no encontradas
app.all('*', (req: Request, res: Response, next: NextFunction) => {
    next(new AppError(`No se puede encontrar ${req.originalUrl} en este servidor`, 404));
});

//Middleware de manejo de errores
app.use(errorHandler);

const PORT = parseInt(process.env.PORT as string, 10) || 3001;

app.listen(PORT, "0.0.0.0", () => {
    console.log(`Servidor de notificaciones ejecutándose en el puerto ${PORT}`);
});