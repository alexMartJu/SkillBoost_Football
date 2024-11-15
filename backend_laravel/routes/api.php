<?php
use Illuminate\Http\Request;
use App\Http\Controllers\DeportesController;
use Illuminate\Support\Facades\Route;
use App\Models\Deportes;
Route::bind('Deportes', function ($value) {
    return Deportes::where('slug', $value)->firstOrFail();
});
Route::apiResource('Deportes', DeportesController::class);