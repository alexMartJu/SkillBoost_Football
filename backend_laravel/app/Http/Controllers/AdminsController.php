<?php

namespace App\Http\Controllers;
use App\Models\Admin;
use App\Http\Resources\AdminsResources;
use Illuminate\Http\Request;

class AdminsController extends Controller
{
    public function index()
    {
        return AdminsResources::collection(Admin::all());
    }
    public function store(Request $request)
    {
        
        return "store";

    }


    public function show($nombre)
    {
        $admin = Admin::where('nombre', $nombre)->firstOrFail();
        if (!$admin) {
            return response()->json(['error' => 'admin no encontrado'], 404);
        }
        return new AdminsResources($admin);
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
