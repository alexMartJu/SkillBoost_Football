<?php

namespace App\Http\Controllers;

use App\Models\Deporte;
use App\Models\Pista;
use Illuminate\Http\Request;

class DeportePistaController extends Controller
{
    // Asignar una pista a un deporte
    public function assignPistaToDeporte(Request $request, $deporteId)
    {
        $deporte = Deporte::findOrFail($deporteId);
        // return response()->json($deporte);
        $deporte->pistas()->attach($request->pista_id); // Asignar pista

        return response()->json(['message' => 'Pista asignada correctamente.']);
    }

    // Eliminar una pista de un deporte
    public function removePistaFromDeporte(Request $request, $deporteId)
    {
        $deporte = Deporte::findOrFail($deporteId);
        $deporte->pistas()->detach($request->pista_id); // Eliminar pista

        return response()->json(['message' => 'Pista eliminada correctamente.']);
    }

    // Obtener todas las pistas de un deporte
    public function getPistasFromDeporte($deporteId)
    {
        $deporte = Deporte::findOrFail($deporteId);

        return response()->json($deporte->pistas);
    }
}
