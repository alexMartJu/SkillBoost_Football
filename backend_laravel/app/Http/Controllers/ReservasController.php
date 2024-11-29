<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Reserva;
use App\Http\Resources\ReservasResources;

class ReservasController extends Controller
{
    public function index()
    {
        return ReservasResources::collection(Reserva::all());
    }
    public function store(Request $request)
    {
        
        return "store";

    }


    public function show($slug)
    {

        $reserva = Entrenador::where('slug', $slug)->firstOrFail();
        if (!$reserva) {
            return response()->json(['error' => 'reserva no encontrada'], 404);
        }

        return new ReservasResources($reserva);
    }

    public function update(Request $request, $slug)
    {
       
        
        return "update";
    }
    public function destroy($slug)
    {
        $reserva = Reservas::where('slug', $slug)->firstOrFail();
       
        $reserva->delete();
        return response()->json(['message' => 'reserva eliminada correctamente.']);
    }

   
    public function restore($slug)
{
    $reserva = Reserva::onlyTrashed()->where('slug', $slug)->first();

    if (!$reserva) {
        return response()->json(['error' => 'Reserva no encontrada'], 404);
    }

   
    $reserva->restore();

   return response()->json(['message' => 'Reserva restaurada correctamente.']);
}
}
