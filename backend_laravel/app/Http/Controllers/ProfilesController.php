<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Profile;
use App\Http\Resources\ProfilesResource;
class ProfilesController extends Controller
{

    public function show($id)
    {
        $profile = Profile::where('id', $id)->firstOrFail();
        if (!$profile) {
            return response()->json(['error' => 'perfil no encontrado'], 404);
        }

        return new ProfilesResource($profile);
    }
    
}
