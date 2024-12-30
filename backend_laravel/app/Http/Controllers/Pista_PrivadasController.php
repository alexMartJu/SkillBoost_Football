<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Pista_privada;
use App\Http\Resources\PistaPrivadasResource;
use Illuminate\Support\Facades\Log;
class Pista_PrivadasController extends Controller
{
    public function index()
    {
        return PistaPrivadasResource::collection(Pista_privada::all());
    }

    public function store(Request $request)
    {
        $admin = auth('admin')->user();

        if (!$admin) {
            return response()->json(['error' => 'Admin no encontrado'], 404);
        }
        
        $validatedData = $request->validate([
            'nombre' => 'required|string|max:255',
            'deportes' => 'required|array',
            'deportes.*' => 'exists:deportes,id', 
            'imagenes' => 'nullable|array',
            'imagenes.*' => 'url', 
        ]);
    
        try {
           
            $pistas = Pista_privada::create([
                'nombre' => $validatedData['nombre'],
            ]);
    
            if ($request->has('imagenes')) {
                foreach ($request->input('imagenes') as $imageUrl) {
                   
                    $pistas->images()->create([
                        'image_url' => $imageUrl, 
                    ]);
                }
            }
               
            $pistas->deportes()->attach($validatedData['deportes']);
           
            return new PistaPrivadasResource($pistas);
    
        } catch (\Exception $e) {
            
            Log::error('Error al crear la pista: ' . $e->getMessage(), [
                'trace' => $e->getTraceAsString(),
                'request_data' => $request->all()  
            ]);
    
           
            return response()->json([
                'error' => 'Error al crear la pista',
                'message' => $e->getMessage(),  
                'trace' => config('app.debug') ? $e->getTrace() : null 
            ], 500);
        }
    }
    public function show($slug)
    {
        $pista = Pista_privada::where('slug', $slug)->firstOrFail();
        return new PistaPrivadasResource($pista);
    }

    public function update(Request $request, $slug)
{
    $admin = auth('admin')->user();
        if (!$admin) {
            return response()->json(['error' => 'Admin no encontrado'], 404);
        }
    // Validar los datos de la solicitud
    $validatedData = $request->validate([
        'nombre' => 'required|string|max:255',
        'deportes' => 'required|array',
        'deportes.*' => 'exists:deportes,id', 
        'imagenes' => 'nullable|array',
        'imagenes.*' => 'url', 
    ]);


    try {
        // Buscar la pista por el slug
        $pista = Pista_privada::where('slug', $slug)->firstOrFail();

        
        if (isset($validatedData['nombre'])) {
            $pista->update([
                'nombre' => $validatedData['nombre'],
            ]);
        }
       
        if (isset($validatedData['deportes'])) {
            $pista->deportes()->sync($validatedData['deportes']); 
        }

        
        if ($request->has('imagenes')) {
            foreach ($validatedData['imagenes'] as $imageUrl) {
                // Agregar cada nueva imagen a la pista
                $pista->images()->create([
                    'image_url' => $imageUrl,
                ]);
            }
        }

        // Devolver la pista actualizada
        return new PistaPrivadasResource($pista);

    } catch (\Exception $e) {
       
        Log::error('Error al actualizar la pista: ' . $e->getMessage());
        return response()->json(['error' => 'Error al actualizar la pista'], 500);
    }
}

    public function destroy($slug)
    {
        $admin = auth('admin')->user();
        if (!$admin) {
            return response()->json(['error' => 'Admin no encontrado'], 404);
        }
        try {
            $pista = Pista_privada::where('slug', $slug)->firstOrFail();
            $pista->images()->delete();
            $pista->deportes()->detach();
            $pista->delete();

            return response()->json(['message' => 'Pista eliminada correctamente.']);
        } catch (\Exception $e) {
            return response()->json(['error' => 'Error al eliminar la pista'], 500);
        }
    }

    public function destroyImage(Request $request, $slug)
{
    // Validar la URL de la imagen
    $validatedData = $request->validate([
        'image_url' => 'required|string|max:255',
    ]);

    try {
        
        $pista = Pista_privada::where('slug', $slug)->firstOrFail();

       
        $image = $pista->images()->where('image_url', $validatedData['image_url'])->first();

        if (!$image) {
            return response()->json(['error' => 'Imagen no encontrada.'], 404);
        }

        
        $image->delete();

        return response()->json(['message' => 'Imagen eliminada correctamente.']);
    } catch (\Exception $e) {
        return response()->json(['error' => 'Error al eliminar la imagen'], 500);
    }
}
}
