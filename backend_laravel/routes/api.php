<?php
use App\Http\Controllers\ClaseController;
use App\Models\Clase;
use App\Models\Pistas;
use Illuminate\Http\Request;
use App\Http\Controllers\DeportesController;
use App\Http\Controllers\PistasController;
use Illuminate\Support\Facades\Route;
use App\Models\Deportes;

Route::bind('deportes', function ($value) {
    return Deportes::where('slug', $value)->firstOrFail();
});
Route::bind('pistas', function ($value) {
    return Pistas::where('slug', $value)->firstOrFail();
});
Route::bind('clases', function ($value) {
    return Clase::where('slug', $value)->firstOrFail();
});

Route::apiResource('deportes', DeportesController::class);
Route::apiResource('pistas', PistasController::class);
Route::apiResource('clases', ClaseController::class);