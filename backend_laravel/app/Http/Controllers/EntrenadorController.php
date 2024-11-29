<?php

namespace App\Http\Controllers;
use App\Models\Entrenador;
use Illuminate\Http\Request;
use App\Http\Resources\EntrenadoresResources;
use Illuminate\Support\Facades\Hash;
class EntrenadorController extends Controller
{
    public function index()
    {
        return EntrenadoresResources::collection(Entrenador::all());
    }
    public function store(Request $request)
    {
        
        try {
            
            $request->validate([
                'nombre' => 'required|string|max:255',
                'apellidos' => 'required|string|max:255',
                'DNI' => 'required|string|max:15|unique:entrenadores,DNI',
                'email' => 'required|email|unique:entrenadores,email',
                'password' => 'required|string|min:8',
                'deporte_id' => 'required|integer|exists:deportes,id',
                'edad' => 'required|integer|min:18',
                'imagenes' => 'nullable|array', 
                'imagenes.*' => 'string|max:255',
            ]);
    
           
            $entrenador = Entrenador::create([
                'nombre' => $request->nombre,
                'apellidos' => $request->apellidos,
                'DNI' => $request->DNI,
                'email' => $request->email,
                'password' => Hash::make($request->password),
                'deporte_id' => $request->deporte_id,
                'edad' => $request->edad,
            ]);
            if ($request->has('imagenes')) {
                foreach ($request->input('imagenes') as $imageUrl) {
                   
                    $entrenador->images()->create([
                        'image_url' => $imageUrl, 
                    ]);
                }
            }
    
            // Retornar el entrenador creado
            return new EntrenadoresResources($entrenador);
    
        } catch (\Illuminate\Validation\ValidationException $e) {
            
            return response()->json([
                'error' => 'Validación fallida',
                'messages' => $e->errors()
            ], 422); // Código de error 422 para validación fallida
    
        } catch (\Exception $e) {
            // Si ocurre cualquier otro error, devolver un mensaje genérico
            return response()->json([
                'error' => 'Ha ocurrido un error',
                'message' => $e->getMessage()
            ], 500); // Código de error 500 para errores internos
        }

    }


    public function show($DNI)
    {
        $entrenador = Entrenador::where('DNI', $DNI)->firstOrFail();
        if (!$entrenador) {
            return response()->json(['error' => 'entrenador no encontrado'], 404);
        }

        return new EntrenadoresResources($entrenador);
    }

    public function update(Request $request, $DNI)
    {
        $entrenador = Entrenador::where('DNI', $DNI)->first();
        if (!$entrenador) {
            return response()->json(['error' => 'entrenador no encontrado'], 404);
        }
        $request->validate([
            'nombre' => 'nullable|string|max:255',
            'apellidos' => 'nullable|string|max:255',
            'DNI' => 'nullable|string|max:15|unique:entrenadores,DNI,' . $DNI . ',DNI',
            'email' => 'nullable|email|unique:entrenadores,email,' . $DNI . ',DNI',
            'password' => 'nullable|string|min:8',
            'deporte_id' => 'nullable|integer|exists:deportes,id',
            'edad' => 'nullable|integer|min:18',
            'imagenes' => 'nullable|array',
            'imagenes.*' => 'string|max:255',
        ]);

       
        $entrenador->update($request->only([
            'nombre', 
            'apellidos', 
            'DNI', 
            'email', 
            'password', 
            'deporte_id', 
            'edad'
        ]));

      
        if ($request->has('password')) {
            $entrenador->password = Hash::make($request->password);
            $entrenador->save();
        }
        
        if ($request->has('imagenes')) {
           
             $entrenador->images()->delete();
    
          
                
                $entrenador->images()->create([
                    'image_url' => $imageUrl,
                ]);
            
        }

        
        return new EntrenadoresResources($entrenador);
    }
    public function destroy($DNI)
    {
        $entrenador = Entrenador::where('DNI', $DNI)->firstOrFail();
        if (!$entrenador) {
            return response()->json(['error' => 'Entrenador no encontrado'], 404);
        }
       
        $entrenador->delete();
        return response()->json(['message' => 'entrenador eliminado correctamente.']);
    }

   
    public function restore($DNI)
    {
            
        $entrenador = Entrenador::onlyTrashed()->where('DNI', $DNI)->first();

        if (!$entrenador) {
            return response()->json(['error' => 'Entrenador no encontrado'], 404);
        }
    
        // Restaurar el entrenador
        $entrenador->restore();
    
       return response()->json(['message' => 'Entrenador restaurado correctamente.']);
    }
}
