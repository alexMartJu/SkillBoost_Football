<?php

// app/Http/Controllers/DeporteController.php
namespace App\Http\Controllers;

use App\Http\Resources\DeporteResource;
use App\Models\Deporte;
use Illuminate\Http\Request;

class DeporteController extends Controller
{
    // Obtener todos los deportes
    public function index()
    {
        return DeporteResource::collection(Deporte::all());
    }

    // Obtener un deporte específico por su ID
    public function show($id)
    {
        $deporte = Deporte::find($id);
        if (!$deporte) {
            return response()->json(['error' => 'Deporte no encontrado'], 404);
        }
        return response()->json($deporte);
    }

    // Crear un nuevo deporte
    public function store(Request $request)
    {
        $request->validate([
            'nombre' => 'required|string|max:255',
        ]);

        $deporte = Deporte::create($request->all());

        return response()->json($deporte, 201);  // 201: recurso creado exitosamente
    }

    // Actualizar un deporte
    public function update(Request $request, $id)
    {
        $deporte = Deporte::find($id);
        if (!$deporte) {
            return response()->json(['error' => 'Deporte no encontrado'], 404);
        }

        $deporte->update($request->all());
        return response()->json($deporte);
    }

    // Eliminar un deporte
    public function destroy($id)
    {
        $deporte = Deporte::find($id);
        if (!$deporte) {
            return response()->json(['error' => 'Deporte no encontrado'], 404);
        }

        $deporte->delete();
        return response()->json(['message' => 'Deporte eliminado']);
    }
}
