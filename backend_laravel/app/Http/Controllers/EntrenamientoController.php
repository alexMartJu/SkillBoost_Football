<?php

// app/Http/Controllers/ClaseController.php
namespace App\Http\Controllers;

use App\Http\Resources\EntrenamientoResource;
use App\Models\Clase;
use App\Models\Deporte;
use App\Models\Entrenamiento;
use Illuminate\Http\Request;
use Illuminate\Database\Eloquent\SoftDeletes;

class EntrenamientoController extends Controller
{
    // Obtener todas las clases
    public function index()
    {
        return EntrenamientoResource::collection(Entrenamiento::all());
    }

    // Obtener una clase específica por su ID
    public function show($id)
    {
        $entrenamiento = Entrenamiento::with('deporte')->find($id);
        if (!$entrenamiento) {
            return response()->json(['error' => 'Entrenamiento no encontrado'], 404);
        }
        return response()->json($entrenamiento);
    }

    // Crear una nueva clase
    public function store(Request $request)
    {
        $request->validate([
            'nombre' => 'required|string|max:255',
            'descripcion' => 'required|string|max:500',
            'duracion' => 'required|integer|min:1',
            'max_plazas' => 'required|integer|min:1',
            'precio' => 'required|numeric|min:0',
            'deporte_id' => 'required|exists:deportes,id', 
        ]);

        $entrenamiento = Entrenamiento::create([
            'nombre' => $request->nombre,
            'descripcion' => $request->descripcion,
            'duracion' => $request->duracion,
            'max_plazas' => $request->max_plazas,
            'precio' => $request->precio,
            'deporte_id' => $request->deporte_id,
        ]);

        return response()->json($entrenamiento, 201);
    }

    // Actualizar una clase existente
    public function update(Request $request, $id)
    {
        $entrenamiento = Entrenamiento::find($id);
        if (!$entrenamiento) {
            return response()->json(['error' => 'entrenamiento no encontrada'], 404);
        }

        $request->validate([
            'nombre' => 'required|string|max:255',
            'deportes_id' => 'required|exists:deportes,deportes_id',  
        ]);

        $entrenamiento->update($request->all());
        return response()->json($entrenamiento);
    }

    // Eliminar una clase
    public function destroy($id)
    {
        $entrenamiento = Entrenamiento::find($id);
        if (!$entrenamiento) {
            return response()->json(['error' => 'Clase no encontrada'], 404);
        }

        $entrenamiento->delete();
        return response()->json(['message' => 'Clase eliminada']);
    }
}
