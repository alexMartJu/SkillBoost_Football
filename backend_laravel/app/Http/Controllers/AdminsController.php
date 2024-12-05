<?php

namespace App\Http\Controllers;
use App\Models\Admin;
use App\Http\Resources\AdminsResource;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use PHPOpenSourceSaver\JWTAuth\Facades\JWTAuth;
use Illuminate\Validation\ValidationException;

class AdminsController extends Controller
{
    public function index()
    {
        return AdminsResource::collection(Admin::all());
    }
    public function login(Request $request)
    {
          // Validación de las credenciales del usuario
          $request->validate([
            'email' => 'required|email',
            'password' => 'required|string|min:8',
        ]);

        
        $credentials = $request->only('email', 'password');

       
        if (!$token = auth('admin')->attempt($credentials)) {
            
            throw ValidationException::withMessages([
                'email' => ['Credenciales inválidas.'],
            ]);
        }

        // Si la autenticación es exitosa, devolver el token
        return response()->json([
            'message' => 'Inicio de sesión exitoso',
            'tokenAdmin' => $token,
            'token_type' => 'Bearer',
            'expires_in' => auth('admin')->factory()->getTTL() * 60, 
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

    
    public function store(Request $request)
    {
        
        $request->validate([
            'nombre' => 'required|string|max:255',
            'email' => 'required|email|unique:admins,email',
            'password' => 'required|string|min:8',
        ]);

        $admin = Admin::create([
            'nombre' => $request->nombre,
            'email' => $request->email,
            'password' => Hash::make($request->password),
        ]);
        return new AdminsResource($admin);
    }


    public function show($nombre)
    {
        $admin = Admin::where('nombre', $nombre)->firstOrFail();
        if (!$admin) {
            return response()->json(['error' => 'admin no encontrado'], 404);
        }
        return new Admins($admin);
    }

    public function update(Request $request, $slug)
    {
       
        
        return "update";
    }
    public function destroy($slug)
    {
        $admin = Admin::where('slug', $slug)->firstOrFail();
       
        $admin->delete();
        return response()->json(['message' => 'admin eliminado correctamente.']);
    }

   
    public function restore($slug)
{
    
    

    return "restaurado";
}

}
