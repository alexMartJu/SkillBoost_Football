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

Route::apiResource('deportes', DeportesController::class);
Route::apiResource('pistas', PistasController::class);
Route::apiResource('entrenamientos', EntrenamientoController::class);