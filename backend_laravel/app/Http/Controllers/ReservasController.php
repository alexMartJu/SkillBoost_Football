<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Reserva;
use App\Http\Resources\ReservasResource;

class ReservasController extends Controller
{
    public function index()
    {
        return ReservasResource::collection(Reserva::all());
    }
    public function store(Request $request)
    {
        
        return "store";

    }


    public function show($id)
    {

        $reserva = Reserva::where('id', $id)->firstOrFail();
        if (!$reserva) {
            return response()->json(['error' => 'reserva no encontrada'], 404);
        }

        return new ReservasResource($reserva);
    }

    public function update(Request $request, $id)
    {
       
        
        return "update";
    }
    public function destroy($id)
    {
        $reserva = Reserva::where('id', $id)->firstOrFail();
       
        $reserva->delete();
        return response()->json(['message' => 'reserva eliminada correctamente.']);
    }

   
//     public function restore($id)
// {
//     $reserva = Reserva::onlyTrashed()->where('id', $id)->first();

//     if (!$reserva) {
//         return response()->json(['error' => 'Reserva no encontrada'], 404);
//     }

   
//     $reserva->restore();

//    return response()->json(['message' => 'Reserva restaurada correctamente.']);
// }
}
