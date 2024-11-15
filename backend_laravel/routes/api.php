<?php

// routes/api.php
use App\Http\Controllers\ClaseController;
use App\Http\Controllers\DeporteController;
use App\Http\Controllers\PistaController;
use App\Http\Controllers\DeportePistaController;

Route::resource('deportes', DeporteController::class);
Route::resource('clases', ClaseController::class);
Route::resource('pistas', PistaController::class);

// Asignar una pista a un deporte
Route::post('deportes/{deporteId}/pistas', [DeportePistaController::class, 'assignPistaToDeporte']);

// Eliminar una pista de un deporte
Route::delete('deportes/{deporteId}/pistas', [DeportePistaController::class, 'removePistaFromDeporte']);

// Obtener todas las pistas de un deporte
Route::get('deportes/{deporteId}/pistas', [DeportePistaController::class, 'getPistasFromDeporte']);
