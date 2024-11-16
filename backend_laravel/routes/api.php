<?php
use Illuminate\Http\Request;
use App\Http\Controllers\DeportesController;
use App\Http\Controllers\PistasController;
use Illuminate\Support\Facades\Route;
use App\Models\Deportes;
Route::bind('Deportes', function ($value) {
    return Deportes::where('slug', $value)->firstOrFail();
});
Route::bind('Pistas', function ($value) {
    return Pistas::where('slug', $value)->firstOrFail();
});
Route::apiResource('Deportes', DeportesController::class);
Route::apiResource('Pistas', PistasController::class);
