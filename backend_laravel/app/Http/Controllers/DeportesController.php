<?php

namespace App\Http\Controllers;

use App\Models\Deporte;
use Illuminate\Http\Request;
use App\Models\Deportes;
use Illuminate\Support\Facades\Log; 
use App\Http\Resources\DeportesResources;

class DeportesController extends Controller
{
    public function index(){

         //return DeportesResources::collection($Deportes);
        return DeportesResources::collection(Deporte::all());
        
    }
        public function store(Request $request){
            
            $validatedData = $request->validate([
                'nombre' => 'required|string|max:191|unique:deportes',
        ]);
            
            $deporte = Deporte::create([
                'nombre' => $validatedData['nombre'],
            ]);
            return new DeportesResources($deporte);

        }


    public function show($slug){

        $deporte = Deporte::where('slug', $slug)->firstOrFail();

        return new DeportesResources($deporte);
    }

    public function update(Request $request, $slug)
    {
        $deporte = Deporte::where('slug', $slug)->firstOrFail();
    
    }
    public function destroy($slug){

        return "hola";

    }







}
