<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Pistas; 
use Illuminate\Support\Facades\Log; 
use App\Http\Resources\PistasResources;

class PistasController extends Controller
{
    public function index(){

        //return DeportesResources::collection($Deportes);
       return PistasResources::collection(Pistas::all());
       
   }
       public function store(Request $request){
           
           $validatedData = $request->validate([
                'nombre' => 'required|string|max:191|unique:pistas',
                'deportes' => 'required|array', 
                'deportes.*' => 'exists:deportes,id' 
       ]);
           
           $pistas = Pistas::create([
               'nombre' => $validatedData['nombre'],
           ]);

           $pistas->deportes()->attach($validatedData['deportes']);
           return new DeportesResources($pistas);

       }


   public function show($slug){

       $pista = Pistas::where('slug', $slug)->firstOrFail();

       return new PistasResources($pista);
   }

   public function update(Request $request, $slug)
   {
      // Validación de datos
      $validatedData = $request->validate([
        'nombre' => 'required|string|max:191|unique:pistas,nombre,' . $slug . ',slug', 
        'deportes' => 'nullable|array', 
        'deportes.*' => 'exists:deportes,id' 
    ]);
    
    // Buscar la pista por su slug
    $pista = Pistas::where('slug', $slug)->firstOrFail();
    
    // Actualizar los datos de la pista
    $pista->update([
        'nombre' => $validatedData['nombre'],
    ]);

    // Si se pasan deportes, actualizar la relación many-to-many
    if (isset($validatedData['deportes'])) {
        $pista->deportes()->sync($validatedData['deportes']);
    }

    return new PistasResources($pista);
   
   }
   public function destroy($slug){

    $pista = Pistas::where('slug', $slug)->firstOrFail();
    $pista->delete();
    return response()->json(['message' => 'Pista eliminada correctamente.']);

   }







}
