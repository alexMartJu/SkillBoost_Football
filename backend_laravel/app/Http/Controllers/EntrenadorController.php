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



    public function register(RegisterEntrenadorRequest $request)
    {
        $admin = auth('admin')->user();
        if (!$admin) {
            return response()->json(['error' => 'Admin no encontrado'], 404);
        }
        $validatedData = $request->validated();
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
