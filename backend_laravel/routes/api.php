<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\EntrenamientoController;
use App\Http\Controllers\DeportesController;
use App\Http\Controllers\PistasController;
use App\Http\Controllers\AdminsController;
use App\Http\Controllers\UsuariosController;
use App\Http\Controllers\EntrenadorController;
use App\Http\Controllers\SalasController;
use App\Http\Controllers\ReservasController;
use App\Http\Controllers\HorariosController;
use App\Models\Entrenamiento;
use App\Models\Pista;
use App\Models\Deporte;
use App\Http\Middleware\AdminAuthenticate;
use App\Http\Middleware\EntrenadorAuthenticate;

// Define las rutas de recursos para cada controlador
Route::apiResource('deportes', DeportesController::class)->only(['index', 'show']);  
Route::apiResource('pistas', PistasController::class)->only(['index', 'show']);  
Route::apiResource('salas', SalasController::class)->only(['index', 'show']); 
Route::apiResource('entrenador', EntrenadorController::class)->only(['index', 'show']); 
Route::apiResource('entrenamientos', EntrenamientoController::class)->only(['index', 'show']); 
Route::bind('deportes', function ($value) {
    return Deporte::where('slug', $value)->firstOrFail();
});
Route::bind('pistas', function ($value) {
    return Pista::where('slug', $value)->firstOrFail();
});
Route::bind('entrenamientos', function ($value) {
    return Entrenamiento::where('slug', $value)->firstOrFail();
});
Route::post('/entrenador/login', [EntrenadorController::class, 'login']);
Route::post('/admin/login', [AdminsController::class, 'login']);

// Route::middleware('auth:admin')->group(function () {
//     // Crear y eliminar entrenadores (solo admin puede hacer esto)
//     Route::post('/entrenadores/register', [EntrenadorController::class, 'register']);  
//     Route::delete('/entrenadores/{entrenador}', [EntrenadorController::class, 'destroy']);
//     Route::apiResource('deportes', DeportesController::class)->except(['index', 'show']);
//     Route::apiResource('pistas', PistasController::class)->except(['index', 'show']);
//     Route::get('/admin/me', [AdminsController::class, 'me']);  
//     Route::post('/admin/logout', [AdminsController::class, 'logout']); 
//     Route::apiResource('salas', SalasController::class)->except(['index', 'show']); 
// });
Route::middleware([AdminAuthenticate::class])->group(function () {
    // Solo accesibles por Admin
    Route::post('/entrenador/register', [EntrenadorController::class, 'register']);
    Route::delete('/entrenador/{entrenador}', [EntrenadorController::class, 'destroy']);
    Route::apiResource('deportes', DeportesController::class)->except(['index', 'show']);
    Route::apiResource('pistas', PistasController::class)->except(['index', 'show']);
    Route::get('/admin/me', [AdminsController::class, 'me']);
    Route::post('/admin/logout', [AdminsController::class, 'logout']);
    Route::apiResource('salas', SalasController::class)->except(['index', 'show']);
});
Route::middleware([EntrenadorAuthenticate::class])->group(function () {
    // Solo accesibles por Entrenador
    Route::get('/entrenador/{email}/entrenamientos', [EntrenamientoController::class, 'getEntrenamientosByEntrenador']);
    Route::get('/entrenador/salas', [SalasController::class, 'getSalasByEntrenador']);
    Route::get('/currententrenador', [EntrenadorController::class, 'me']);
    Route::put('/entrenador/me', [EntrenadorController::class, 'update']);
    Route::post('/entrenador/logout', [EntrenadorController::class, 'logout']);
    Route::post('/entrenamientos', [EntrenamientoController::class, 'store']);
    Route::put('/entrenamientos/{slug}', [EntrenamientoController::class, 'update']);
    Route::delete('/entrenamientos/{slug}', [EntrenamientoController::class, 'destroy']);
});
// Route::middleware('auth:entrenador')->group(function () {
//     Route::get('/entrenador/{email}/entrenamientos', [EntrenamientoController::class, 'getEntrenamientosByEntrenador']);
//     Route::get('/entrenador/salas', [SalasController::class, 'getSalasByEntrenador']); 
//     Route::get('/entrenador/me', [EntrenadorController::class, 'me']);
//     Route::put('/entrenador/me', [EntrenadorController::class, 'update']);
//     Route::post('/entrenador/logout', [EntrenadorController::class, 'logout']);
//     Route::post('/entrenamientos', [EntrenamientoController::class, 'store']);
//     Route::put('/entrenamientos/{slug}', [EntrenamientoController::class, 'update']);
//     Route::delete('/entrenamientos/{slug}', [EntrenamientoController::class, 'destroy']);
// });
Route::apiResource('horarios', HorariosController::class);
Route::apiResource('reservas', ReservasController::class);
Route::apiResource('usuarios', UsuariosController::class);

