<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Horario;
use App\Http\Resources\HorariosResources;
class HorariosController extends Controller
{
    public function index()
    {
        return HorariosResources::collection(Horario::all());
    }
    public function store(Request $request)
    {
        
        return "store";

    }


    public function show($id)
    {

        $horario = horario::where('id', $id)->firstOrFail();
        if (!$horario) {
            return response()->json(['error' => 'horario no encontrado'], 404);
        }

        return new HorariosResources($horario);
    }

    public function update(Request $request, $slug)
    {
       
        
        return "update";
    }
    public function destroy($slug)
    {
        $horario = Horario::where('slug', $slug)->firstOrFail();
       
        $horario->delete();
        return response()->json(['message' => 'horario eliminado correctamente.']);
    }

   
    public function restore($slug)
{
    
    

    return "restaurado";
}
}
