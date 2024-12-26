<?php

namespace App\Http\Controllers;

use App\Models\Grafica;
use App\Models\Profile;
use Illuminate\Http\Request;

class GraficasController extends Controller
{
    
    public function obtenerGraficas($profileId)
    {
       
        $profile = Profile::findOrFail($profileId);
        
       
        $graficas = $profile->graficas()->orderBy('año', 'desc')->orderBy('mes', 'desc')->get();

        return response()->json($graficas, 200);
    }

    
    public function actualizarGrafica(Request $request, $profileId)
    {
        $año = now()->year;
        $mes = now()->month;

        // Validar el request
        $request->validate([
            'seccion' => 'required|string',
            'nivel' => 'required|integer',
        ]);

        
        $graficaExistente = Grafica::where('profile_id', $profileId)
                                   ->where('año', $año)
                                   ->where('mes', $mes)
                                   ->where('seccion', $request->seccion)
                                   ->first();

        if ($graficaExistente) {
           
            $graficaExistente->update([
                'nivel' => $request->nivel,
            ]);
            return response()->json($graficaExistente, 200);
        } else {
            // Si no existe, se crea una nueva entrada
            $graficaNueva = Grafica::create([
                'profile_id' => $profileId,
                'año' => $año,
                'mes' => $mes,
                'seccion' => $request->seccion,
                'nivel' => $request->nivel,
            ]);
            return response()->json($graficaNueva, 201);
        }
    }
}
