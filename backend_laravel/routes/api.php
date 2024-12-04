<?php
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\EntrenamientoController;
use App\Http\Controllers\DeportesController;
use App\Http\Controllers\PistasController;
use App\Http\Controllers\AdminsController;
use App\Http\Controllers\UsuariosController;
use App\Http\Controllers\EntrenadorController;
use App\Http\Controllers\SalasController;
use App\Http\Controllers\ReservasController;
use App\Http\Controllers\horariosController;
use App\Models\Entrenamiento;
use App\Models\Pista;
use App\Models\Deporte;


Route::bind('deportes', function ($value) {
    return Deporte::where('slug', $value)->firstOrFail();
});
Route::bind('pistas', function ($value) {
    return Pista::where('slug', $value)->firstOrFail();
});
Route::bind('entrenamientos', function ($value) {
    return Entrenamiento::where('slug', $value)->firstOrFail();
});
//deportes
Route::apiResource('deportes', DeportesController::class);
Route::delete('/deportes/{slug}/imagenes', [DeportesController::class, 'destroyImage']);
//pistas
Route::apiResource('pistas', PistasController::class);
Route::delete('/pistas/{slug}/imagenes', [PistasController::class, 'destroyImage']);
//entrenamientos
Route::apiResource('entrenamientos', EntrenamientoController::class);
Route::apiResource('admins', AdminsController::class);
Route::apiResource('entrenadores', EntrenadorController::class);
Route::apiResource('horarios', HorariosController::class);
Route::apiResource('reservas', ReservasController::class);
Route::apiResource('usuarios', UsuariosController::class);
Route::apiResource('salas', SalasController::class);

//
Route::post('/entrenador/register', [EntrenadorController::class, 'register']);

Route::post('/entrenador/login', [EntrenadorController::class, 'login']);

Route::middleware('auth:entrenador')->group(function () {
    Route::get('/entrenador/me', [EntrenadorController::class, 'me']);
    Route::post('/entrenador/logout', [EntrenadorController::class, 'logout']);
});


 Route::middleware('auth:entrenador')->get('/entrenador/{email}/entrenamientos', [EntrenamientoController::class, 'getEntrenamientosByEntrenador']);
//Route::get('/entrenador/{DNI}/entrenamientos', [EntrenamientoController::class, 'getEntrenamientosByEntrenador']);
Route::post('/admin/login', [AdminsController::class, 'login']);

Route::middleware('auth:admin')->group(function () {
    Route::get('/admin/me', [AdminsController::class, 'me']);
    Route::post('/adminr/logout', [AdminsController::class, 'logout']);
});