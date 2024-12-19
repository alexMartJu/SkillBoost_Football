<?php

namespace App\Http\Controllers;
use App\Models\Entrenador;
use Illuminate\Http\Request;
use App\Http\Resources\EntrenadoresResource;
use Illuminate\Support\Facades\Hash;
use PHPOpenSourceSaver\JWTAuth\Facades\JWTAuth;
use Illuminate\Validation\ValidationException;
use App\Http\Requests\LoginEntrenadorRequest;
use App\Http\Requests\RegisterEntrenadorRequest;
use App\Http\Requests\UpdateEntrenadorRequest;
use Illuminate\Support\Facades\Log;

class EntrenadorController extends Controller

{
    public function index()
    {
        return EntrenadoresResource::collection(Entrenador::all());
    }

    public function login(LoginEntrenadorRequest $request)
    {
          // Validación de las credenciales del usuario
          $validatedData = $request->validated();

        
        $credentials = $request->only('email', 'password');

       
        if (!$token = auth('entrenador')->attempt($credentials)) {
            return response()->json([
                'error' => 'Contraseña incorrecta'
            ], 401); 
        }
        $entrenador=auth('entrenador')->user();
        $entrenador->makeHidden(['password']);

        $entrenador->entrenadorToken = $token;
        return response()->json([
            'usuario'=>$entrenador,
        ]);
    }
    public function me()
    {
        $entrenador=auth('entrenador')->user();
        $entrenador->makeHidden(['password']);

        return response()->json([
            'usuario'=>$entrenador,
        ]);
    }
    public function logout()
    {
        JWTAuth::invalidate(JWTAuth::getToken());

        return response()->json(['message' => 'Cierre de sesión exitoso']);
    }

    public function test(Request $request)
    {
        Log::info('Método test ejecutado');
        return response()->json(['message' => 'Test exitoso']);
    }

    // public function register(RegisterEntrenadorRequest $request)
    // {
    //     $admin = auth('admin')->user();
    //     if (!$admin) {
    //         return response()->json(['error' => 'Admin no encontrado'], 404);
    //     }
    //     $validatedData = $request->validated();
    //     do {
    //         $numero = str_pad(random_int(1000, 9999), 4, '0', STR_PAD_LEFT);
    //         $nombre = $request->nombre;
    //         $numeroEntrenador=$nombre . '-' . $numero;
    //     } while (Entrenador::where('numeroEntrenador', $numeroEntrenador)->exists());
    
    //     // Crear el entrenador
    //     $entrenador = Entrenador::create([
    //         'nombre' => $request->nombre,
    //         'apellidos' => $request->apellidos,
    //         'numeroEntrenador' => $numeroEntrenador,
    //         'email' => $request->email,
    //         'password' => Hash::make($request->password),
    //         'deporte_id' => $request->deporte_id,
    //         // 'edad' => $request->edad,
    //     ]);
    
    //     return response()->json([
    //         'message' => 'Registro exitoso',
    //         'entrenador' => $entrenador,
    //     ], 201);
    // }
    public function register(RegisterEntrenadorRequest $request)
    {
     
        Log::debug('Iniciando registro de entrenador');
    
        // Verificar si el admin está autenticado
        $admin = auth('admin')->user();
        if (!$admin) {
            Log::error('Admin no encontrado o no autenticado');
            return response()->json(['error' => 'Admin no encontrado'], 404);
        }
        Log::debug('Admin autenticado:', ['admin_id' => $admin->id]);
    
        // Validar los datos del request
        try {
            $validatedData = $request->validated();
            Log::debug('Datos validados correctamente', $validatedData);
        } catch (\Exception $e) {
            Log::debug('Error al validar los datos del request', ['error' => $e->getMessage()]);
            return response()->json(['error' => 'Datos inválidos'], 422);
        }
    
        // Generar número único para el entrenador
        try {
            do {
                $numero = str_pad(random_int(1000, 9999), 4, '0', STR_PAD_LEFT);
                $nombre = $request->nombre;
                $numeroEntrenador = $nombre . '-' . $numero;
    
                Log::debug('Intentando generar numeroEntrenador', ['numeroEntrenador' => $numeroEntrenador]);
            } while (Entrenador::where('numeroEntrenador', $numeroEntrenador)->exists());
    
        } catch (\Exception $e) {
            Log::error('Error al generar numeroEntrenador', ['error' => $e->getMessage()]);
            return response()->json(['error' => 'Error al generar número de entrenador'], 500);
        }
    
        // Crear el entrenador
        try {
            $entrenador = Entrenador::create([
                'nombre' => $request->nombre,
                'apellidos' => $request->apellidos,
                'numeroEntrenador' => $numeroEntrenador,
                'email' => $request->email,
                'password' => Hash::make($request->password),
                'deporte_id' => $request->deporte_id,
                'edad' => $request->edad,
            ]);
    
            Log::debug('Entrenador registrado correctamente', ['entrenador_id' => $entrenador->id]);
    
            return response()->json([
                'message' => 'Registro exitoso',
                'entrenador' => $entrenador,
            ], 201);
        } catch (\Exception $e) {
            Log::debug('Error al crear el entrenador', ['error' => $e->getMessage()]);
            return response()->json(['error' => 'Error al registrar entrenador'], 500);
        }
    }










//____________________________________________________________________________________


    


    public function show($numeroEntrenador)
    {
        $entrenador = Entrenador::where('numeroEntrenador', $numeroEntrenador)->firstOrFail();
        if (!$entrenador) {
            return response()->json(['error' => 'entrenador no encontrado'], 404);
        }

        return new EntrenadoresResource($entrenador);
    }

    public function update(Request $request)
    {
        $entrenador = auth('entrenador')->user();

        if (!$entrenador) {
            return response()->json(['error' => 'entrenador no encontrado'], 404);
        }
       
        $entrenador->update($request->only([
            'nombre', 
            'apellidos', 
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
    public function destroy($numeroEntrenador)
    {
        $entrenador = Entrenador::where('numeroEntrenador', $numeroEntrenador)->firstOrFail();
        if (!$entrenador) {
            return response()->json(['error' => 'Entrenador no encontrado'], 404);
        }
       
        $entrenador->delete();
        return response()->json(['message' => 'entrenador eliminado correctamente.']);
    }

   
    public function restore($numeroEntrenador)
    {
            
        $entrenador = Entrenador::onlyTrashed()->where('numeroEntrenador', $numeroEntrenador)->first();

        if (!$entrenador) {
            return response()->json(['error' => 'Entrenador no encontrado'], 404);
        }
    
        // Restaurar el entrenador
        $entrenador->restore();
    
       return response()->json(['message' => 'Entrenador restaurado correctamente.']);
    }
}
