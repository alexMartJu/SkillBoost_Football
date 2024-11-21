<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Pista;
use Illuminate\Support\Facades\Log;
use App\Http\Resources\PistasResources;

class PistasController extends Controller
{
    public function index()
    {
        return PistasResources::collection(Pista::all());
    }

    public function store(Request $request)
    {
        $validatedData = $request->validate([
            'nombre' => 'required|string|max:191|unique:pistas',
            'deportes' => 'required|array',
            'deportes.*' => 'exists:deportes,id',
            'image' => 'nullable|string',
        ]);

        try {
            $pistas = Pista::create([
                'nombre' => $validatedData['nombre'],
                'images' => $validatedData['image']
            ]);

            $pistas->deportes()->attach($validatedData['deportes']);

            return new PistasResources($pistas);

        } catch (\Exception $e) {
            return response()->json(['error' => 'Error al crear la pista'], 500);
        }
    }

    public function show($slug)
    {
        $pista = Pista::where('slug', $slug)->firstOrFail();
        return new PistasResources($pista);
    }

    public function update(Request $request, $slug)
    {
        $validatedData = $request->validate([
            'nombre' => 'required|string|max:191|unique:pistas,nombre,' . $slug . ',slug',
            'deportes' => 'nullable|array',
            'deportes.*' => 'exists:deportes,id',
            'image' => 'nullable|string',
        ]);

        try {
            $pista = Pista::where('slug', $slug)->firstOrFail();

            $pista->update([
                'nombre' => $validatedData['nombre'],
                'image' => $validatedData['image']
            ]);

            if (isset($validatedData['deportes'])) {
                $pista->deportes()->sync($validatedData['deportes']);
            }

            return new PistasResources($pista);

        } catch (\Exception $e) {
            Log::error('Error al actualizar la pista: ' . $e->getMessage());
            return response()->json(['error' => 'Error al actualizar la pista'], 500);
        }
    }

    public function destroy($slug)
    {
        try {
            $pista = Pista::where('slug', $slug)->firstOrFail();
            $pista->delete();

            return response()->json(['message' => 'Pista eliminada correctamente.']);
        } catch (\Exception $e) {
            return response()->json(['error' => 'Error al eliminar la pista'], 500);
        }
    }
}
