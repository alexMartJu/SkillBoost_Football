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
use App\Http\Controllers\GraficasController;
use App\Http\Controllers\ProfilesController;       
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

Route::middleware([AdminAuthenticate::class])->group(function () {
    // Solo accesibles por Admin
    Route::put('/entrenamientos/status/{slug}',[EntrenamientoController::class, 'status']);
    Route::post('/entrenador/register', [EntrenadorController::class, 'register']);
    Route::delete('/entrenador/{entrenador}', [EntrenadorController::class, 'destroy']);
    Route::apiResource('deportes', DeportesController::class)->except(['index', 'show']);
    Route::apiResource('pistas', PistasController::class)->except(['index', 'show']);
    Route::get('/currentAdmin', [AdminsController::class, 'me']);
    Route::post('/admin/logout', [AdminsController::class, 'logout']);
    Route::apiResource('salas', SalasController::class)->except(['index', 'show']);
});
Route::middleware([EntrenadorAuthenticate::class])->group(function () {
    // Solo accesibles por Entrenador
    Route::get('profile/{id}', [ProfilesController::class, 'show']);
    Route::get('/graficas/profile/{profileId}', [GraficasController::class, 'obtenerGraficas']);
    Route::post('/graficas/profile/{profileId}', [GraficasController::class, 'actualizarGrafica']);
    Route::get('/entrenador/{email}/entrenamientos', [EntrenamientoController::class, 'getEntrenamientosByEntrenador']);
    Route::get('/entrenador/salas', [SalasController::class, 'getSalasByEntrenador']);
    Route::get('/currentEntrenador', [EntrenadorController::class, 'me']);
    Route::put('/currentEntrenador', [EntrenadorController::class, 'update']);
    Route::post('/entrenador/logout', [EntrenadorController::class, 'logout']);
    Route::post('/entrenamientos', [EntrenamientoController::class, 'store']);
    Route::put('/entrenamientos/{slug}', [EntrenamientoController::class, 'update']);
    Route::delete('/entrenamientos/{slug}', [EntrenamientoController::class, 'destroy']);
});

Route::apiResource('horarios', HorariosController::class);
Route::apiResource('reservas', ReservasController::class);
Route::apiResource('usuarios', UsuariosController::class);

