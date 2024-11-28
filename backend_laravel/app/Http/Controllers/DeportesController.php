<?php

namespace App\Http\Controllers;

use App\Models\Deporte;
use Illuminate\Http\Request;
use App\Models\Deportes;
use Illuminate\Support\Facades\Log;
use App\Http\Resources\DeportesResources;
use Illuminate\Database\Eloquent\SoftDeletes;

class DeportesController extends Controller
{
    public function index()
    {
        return DeportesResources::collection(Deporte::all());
    }
    public function store(Request $request)
    {
        $validatedData = $request->validate([
            'nombre' => 'required|string|max:191|unique:deportes',
            'imagenes' => 'nullable|array', 
            'imagenes.*' => 'string|max:255', 
        ]);

        $deporte = Deporte::create([
            'nombre' => $validatedData['nombre'],
        ]);

        if ($request->has('imagenes')) {
            foreach ($request->input('imagenes') as $imageUrl) {
               
                $deporte->images()->create([
                    'image_url' => $imageUrl, 
                ]);
            }
        }
        return new DeportesResources($deporte);

    }


    public function show($slug)
    {
        $deporte = Deporte::where('slug', $slug)->firstOrFail();

        return new DeportesResources($deporte);
    }

    public function update(Request $request, $slug)
    {
       
        $validatedData = $request->validate([
            'nombre' => 'nullable|string|max:191|unique:deportes,nombre,' . $slug . ',slug', 
            'imagenes' => 'nullable|array',
            'imagenes.*' => 'string|max:255', 
        ]);
    
       
        $deporte = Deporte::where('slug', $slug)->firstOrFail();
    
       
        if (isset($validatedData['nombre'])) {
            $deporte->update([
                'nombre' => $validatedData['nombre'],
            ]);
        }
    
       
        if ($request->has('imagenes')) {
           
            // $deporte->images()->delete();
    
            foreach ($request->input('imagenes') as $imageUrl) {
                
                $deporte->images()->create([
                    'image_url' => $imageUrl,
                ]);
            }
        }
    
        
        return new DeportesResources($deporte);
    }
    public function destroy($slug)
    {
        $deporte = Deporte::where('slug', $slug)->firstOrFail();
        $deporte->images()->delete();
        // $deporte->pistas()->updateExistingPivot(
        //     $deporte->pistas->pluck('id')->toArray(), 
        //     ['deleted_at' => now()] 
        // );
        $deporte->pistas()->detach();
        $deporte->delete();
        return response()->json(['message' => 'Deporte eliminado correctamente.']);
    }

    public function destroyImage(Request $request, $slug)
    {
        
        $validatedData = $request->validate([
            'image_url' => 'required|string|max:255',
        ]);
    
        
        $deporte = Deporte::where('slug', $slug)->firstOrFail();
    
        
        $image = $deporte->images()->where('image_url', $validatedData['image_url'])->first();
    
        if (!$image) {
            return response()->json(['error' => 'Imagen no encontrada.'], 404);
        }
    
        // Eliminar la imagen
        $image->delete();
    
        return response()->json(['message' => 'Imagen eliminada correctamente.']);
    }

    public function restore($slug)
{
    
    $deporte = Deporte::withTrashed()->where('slug', $slug)->firstOrFail();

    // Restaurar el deporte
    $deporte->restore();

    return response()->json(['message' => 'Deporte restaurado correctamente.'], 200);
}




}
