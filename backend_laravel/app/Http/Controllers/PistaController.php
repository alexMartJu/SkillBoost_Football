<?php

// app/Http/Controllers/PistaController.php
namespace App\Http\Controllers;

use App\Models\Pista;
use Illuminate\Http\Request;

class PistaController extends Controller
{
    // Obtener todas las pistas
    public function index()
    {
        $pistas = Pista::all();
        return response()->json($pistas);
    }

    // Obtener una pista específica por su ID
    public function show($id)
    {
        $pista = Pista::find($id);
        if (!$pista) {
            return response()->json(['error' => 'Pista no encontrada'], 404);
        }
        return response()->json($pista);
    }

    // Crear una nueva pista
    public function store(Request $request)
    {
        $request->validate([
            'nombre' => 'required|string|max:255',
        ]);

        $pista = Pista::create([
            'nombre' => $request->nombre,
        ]);

        return response()->json($pista, 201);  // 201: Recurso creado correctamente
    }

    // Actualizar una pista existente
    public function update(Request $request, $id)
    {
        $pista = Pista::find($id);
        if (!$pista) {
            return response()->json(['error' => 'Pista no encontrada'], 404);
        }

        $request->validate([
            'nombre' => 'required|string|max:255',
        ]);

        $pista->update($request->all());
        return response()->json($pista);
    }

    // Eliminar una pista
    public function destroy($id)
    {
        $pista = Pista::find($id);
        if (!$pista) {
            return response()->json(['error' => 'Pista no encontrada'], 404);
        }

        $pista->delete();
        return response()->json(['message' => 'Pista eliminada']);
    }
}
