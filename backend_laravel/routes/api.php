<?php
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\EntrenamientoController;
use App\Http\Controllers\DeportesController;
use App\Http\Controllers\PistasController;
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