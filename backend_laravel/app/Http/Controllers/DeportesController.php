<?php

namespace App\Http\Controllers;

use App\Models\Deporte;
use Illuminate\Http\Request;
use App\Models\Deportes;
use Illuminate\Support\Facades\Log;
use App\Http\Resources\DeportesResources;

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
        ]);

        $deporte = Deporte::create([
            'nombre' => $validatedData['nombre'],
        ]);
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
            'nombre' => 'required|string|max:191|unique:deportes',
        ]);

        $deporte = Deporte::where('slug', $slug)->firstOrFail();

        $deporte = Deporte::create([
            'nombre' => $validatedData['nombre'],
        ]);

        return new DeportesResources($deporte);
    }
    public function destroy($slug)
    {
        $deporte = Deporte::where('slug', $slug)->firstOrFail();
        $deporte->delete();
        return response()->json(['message' => 'Pista eliminada correctamente.']);
    }







}
