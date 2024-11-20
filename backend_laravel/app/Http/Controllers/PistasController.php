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
        Log::info('Accediendo al método index de PistasController');

        try {
            return PistasResources::collection(Pista::all());
        } catch (\Exception $e) {
            Log::error('Error al obtener todas las pistas: ' . $e->getMessage());
            return response()->json(['error' => 'Error al obtener las pistas'], 500);
        }
    }

    public function store(Request $request)
    {
        Log::info('Accediendo al método store de PistasController', ['data' => $request->all()]);

        $validatedData = $request->validate([
            'nombre' => 'required|string|max:191|unique:pistas',
            'deportes' => 'required|array',
            'deportes.*' => 'exists:deportes,deportes_id'
        ]);

        try {
            $pistas = Pista::create([
                'nombre' => $validatedData['nombre'],
            ]);
            Log::info('Pista creada', ['pista' => $pistas]);

            $pistas->deportes()->attach($validatedData['deportes']);
            Log::info('Relación deportes asignada a la pista', ['deportes' => $validatedData['deportes']]);

            return new PistasResources($pistas);

        } catch (\Exception $e) {
            Log::error('Error al crear pista: ' . $e->getMessage());
            return response()->json(['error' => 'Error al crear la pista'], 500);
        }
    }

    public function show($slug)
    {
        Log::info('Accediendo al método show de PistasController', ['slug' => $slug]);

        try {
            $pista = Pista::where('slug', $slug)->firstOrFail();
            Log::info('Pista encontrada', ['pista' => $pista]);
            return new PistasResources($pista);

        } catch (\Exception $e) {
            Log::error('Error al obtener la pista: ' . $e->getMessage());
            return response()->json(['error' => 'Pista no encontrada'], 404);
        }
    }

    public function update(Request $request, $slug)
    {
        Log::info('Accediendo al método update de PistasController', ['slug' => $slug, 'data' => $request->all()]);

        $validatedData = $request->validate([
            'nombre' => 'required|string|max:191|unique:pistas,nombre,' . $slug . ',slug',
            'deportes' => 'nullable|array',
            'deportes.*' => 'exists:deportes,id'
        ]);

        try {
            $pista = Pista::where('slug', $slug)->firstOrFail();
            Log::info('Pista encontrada para actualizar', ['pista' => $pista]);

            $pista->update(['nombre' => $validatedData['nombre']]);
            Log::info('Nombre de la pista actualizado', ['nombre' => $validatedData['nombre']]);

            if (isset($validatedData['deportes'])) {
                $pista->deportes()->sync($validatedData['deportes']);
                Log::info('Relación deportes actualizada', ['deportes' => $validatedData['deportes']]);
            }

            return new PistasResources($pista);

        } catch (\Exception $e) {
            Log::error('Error al actualizar la pista: ' . $e->getMessage());
            return response()->json(['error' => 'Error al actualizar la pista'], 500);
        }
    }

    public function destroy($slug)
    {
        Log::info('Accediendo al método destroy de PistasController', ['slug' => $slug]);

        try {
            $pista = Pista::where('slug', $slug)->firstOrFail();
            Log::info('Pista encontrada para eliminar', ['pista' => $pista]);

            $pista->delete();
            Log::info('Pista eliminada correctamente', ['pista' => $pista]);

            return response()->json(['message' => 'Pista eliminada correctamente.']);
        } catch (\Exception $e) {
            Log::error('Error al eliminar la pista: ' . $e->getMessage());
            return response()->json(['error' => 'Error al eliminar la pista'], 500);
        }
    }
}
