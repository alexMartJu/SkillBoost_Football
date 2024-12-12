<?php

namespace App\Http\Controllers;

use App\Models\Deporte;
use Illuminate\Http\Request;
use App\Models\Deportes;
use Illuminate\Support\Facades\Log;
use App\Http\Resources\DeportesResource;
use Illuminate\Database\Eloquent\SoftDeletes;
use App\Http\Requests\StoreDeporteRequest; 
use App\Http\Requests\UpdateDeporteRequest;

class DeportesController extends Controller
{
    public function index()
    {
        return DeportesResource::collection(Deporte::all());
    }
    public function store(StoreDeporteRequest $request)
    {
        $admin = auth('admin')->user();
        if (!$admin) {
            return response()->json(['error' => 'Admin no encontrado'], 404);
        }
        $validatedData = $request->validated();

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
        return new DeportesResource($deporte);

    }


    public function show($slug)
    {
        $deporte = Deporte::where('slug', $slug)->firstOrFail();
        if (!$deporte) {
            return response()->json(['error' => 'deporte no encontrado'], 404);
        }

        return new DeportesResource($deporte);
    }

    public function update(UpdateDeporteRequest $request, $slug)
    {
        $admin = auth('admin')->user();
        if (!$admin) {
            return response()->json(['error' => 'Admin no encontrado'], 404);
        }
        $validatedData = $request->validated();
    
       
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
    
        
        return new DeportesResource($deporte);
    }
    public function destroy($slug)
    {
        $admin = auth('admin')->user();
        if (!$admin) {
            return response()->json(['error' => 'Admin no encontrado'], 404);
        }
        $deporte = Deporte::where('slug', $slug)->firstOrFail();
        $deporte->images()->delete();

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
