import { Request, Response, NextFunction } from 'express';
import { AppError } from '../utils/AppError';

// Middleware para manejar errores de forma centralizada
// Este middleware captura todos los errores lanzados en la aplicación,
// los formatea adecuadamente y envía una respuesta estructurada al cliente.
export const errorHandler = (err: any, req: Request, res: Response, next: NextFunction) => {
  console.error('Error:', err);
  
  // Si es un error de Prisma, formatearlo adecuadamente
  if (err.code && (err.code.startsWith('P') || err.name === 'PrismaClientKnownRequestError')) {
    let message = 'Error en la base de datos';
    let statusCode = 500;
    
    // Manejar errores específicos de Prisma
    switch (err.code) {
      case 'P2002':
        message = 'Ya existe un registro con este valor único';
        statusCode = 409;
        break;
      case 'P2025':
        message = 'No se encontró el registro solicitado';
        statusCode = 404;
        break;
      case 'P2003':
        message = 'Violación de restricción de clave foránea';
        statusCode = 400;
        break;
      case 'P2001':
        message = 'No se encontró el registro para actualizar';
        statusCode = 404;
        break;
      default:
        message = `Error en la operación de base de datos: ${err.message}`;
    }
    
    err = new AppError(message, statusCode);
  }
  
  //Errores de validación
  if (err.name === 'ValidationError') {
    const message = Object.values(err.errors).map((val: any) => val.message).join(', ');
    err = new AppError(message, 400);
  }
  
  //Errores de JWT
  if (err.name === 'JsonWebTokenError') {
    err = new AppError('Token inválido. Por favor inicie sesión nuevamente', 401);
  }
  
  if (err.name === 'TokenExpiredError') {
    err = new AppError('Su sesión ha expirado. Por favor inicie sesión nuevamente', 401);
  }
  
  //Determinar el código de estado y el tipo de error
  const statusCode = err.statusCode || 500;
  const status = `${statusCode}`.startsWith('4') ? 'fail' : 'error';
  
  //Preparar la respuesta
  const errorResponse = {
    status,
    message: err.message || 'Algo salió mal en el servidor',
    ...(process.env.NODE_ENV === 'development' && { 
      stack: err.stack,
      ...(err.errors && { errors: err.errors })
    })
  };
  
  //Enviar respuesta de error
  res.status(statusCode).json(errorResponse);
};