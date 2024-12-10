<?php

namespace App\Http\Controllers;
use App\Models\Entrenador;
use Illuminate\Http\Request;
use App\Http\Resources\EntrenadoresResource;
use Illuminate\Support\Facades\Hash;
use PHPOpenSourceSaver\JWTAuth\Facades\JWTAuth;
use Illuminate\Validation\ValidationException;
class EntrenadorController extends Controller

{
    public function index()
    {
        return EntrenadoresResource::collection(Entrenador::all());
    }

    public function login(Request $request)
    {
          // Validación de las credenciales del usuario
          $request->validate([
            'email' => 'required|email',
            'password' => 'required|string|min:8',
        ]);

        
        $credentials = $request->only('email', 'password');

       
        if (!$token = auth('entrenador')->attempt($credentials)) {
            
            throw ValidationException::withMessages([
                'email' => ['Credenciales inválidas.'],
            ]);
        }

        // Si la autenticación es exitosa, devolver el token
        return response()->json([
            'message' => 'Inicio de sesión exitoso',
            'tokenEntrenador' => $token,
            'token_type' => 'Bearer',
            'expires_in' => auth('entrenador')->factory()->getTTL() * 60, 
        ]);
    }
    public function me()
    {
        return response()->json(auth()->user());
    }
    public function logout()
    {
        JWTAuth::invalidate(JWTAuth::getToken());

        return response()->json(['message' => 'Cierre de sesión exitoso']);
    }



    public function register(Request $request)
    {
        $request->validate([
            'nombre' => 'required|string|max:255',
            'apellidos' => 'required|string|max:255',
            'email' => 'required|email|unique:entrenadores,email',
            'password' => 'required|string|min:8|confirmed', // password_confirmation también requerido
            'deporte_id' => 'required|integer|exists:deportes,id',
            'edad' => 'required|integer|min:18',
        ]);

        do {
            $numero = str_pad(random_int(1000, 9999), 4, '0', STR_PAD_LEFT);
            $nombre = $request->nombre;
            $numeroEntrenador=$nombre . '-' . $numero;
        } while (Entrenador::where('numeroEntrenador', $numeroEntrenador)->exists());
    
        // Crear el entrenador
        $entrenador = Entrenador::create([
            'nombre' => $request->nombre,
            'apellidos' => $request->apellidos,
            'numeroEntrenador' => $numeroEntrenador,
            'email' => $request->email,
            'password' => Hash::make($request->password),
            'deporte_id' => $request->deporte_id,
            'edad' => $request->edad,
        ]);
    
        return response()->json([
            'message' => 'Registro exitoso',
            'entrenador' => $entrenador,
        ], 201);
    }











//____________________________________________________________________________________


    
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
            return new EntrenadoresResource($entrenador);
    
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

        return new EntrenadoresResource($entrenador);
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

        
        return new EntrenadoresResource($entrenador);
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
