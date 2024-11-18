<?php

// app/Http/Controllers/ClaseController.php
namespace App\Http\Controllers;

use App\Http\Resources\ClaseResource;
use App\Models\Clase;
use App\Models\Deporte;
use Illuminate\Http\Request;

class ClaseController extends Controller
{
    // Obtener todas las clases
    public function index()
    {
        return ClaseResource::collection(Clase::all());
    }

    // Obtener una clase específica por su ID
    public function show($id)
    {
        $clase = Clase::with('deporte')->find($id);
        if (!$clase) {
            return response()->json(['error' => 'Clase no encontrada'], 404);
        }
        return response()->json($clase);
    }

    // Crear una nueva clase
    public function store(Request $request)
    {
        $request->validate([
            'nombre' => 'required|string|max:255',
            'deporte_id' => 'required|exists:deportes,id',  // Asegura que el deporte exista
        ]);

        $clase = Clase::create([
            'nombre' => $request->nombre,
            'deporte_id' => $request->deporte_id,
        ]);

        return response()->json($clase, 201);  // 201: Recurso creado correctamente
    }

    // Actualizar una clase existente
    public function update(Request $request, $id)
    {
        $clase = Clase::find($id);
        if (!$clase) {
            return response()->json(['error' => 'Clase no encontrada'], 404);
        }

        $request->validate([
            'nombre' => 'required|string|max:255',
            'deporte_id' => 'required|exists:deportes,id',  // Asegura que el deporte exista
        ]);

        $clase->update($request->all());
        return response()->json($clase);
    }

    // Eliminar una clase
    public function destroy($id)
    {
        $clase = Clase::find($id);
        if (!$clase) {
            return response()->json(['error' => 'Clase no encontrada'], 404);
        }

        $clase->delete();
        return response()->json(['message' => 'Clase eliminada']);
    }
}
