<?php

namespace App\Policies;

use App\Models\Entrenamiento;
use App\Models\Entrenador;
use Illuminate\Auth\Access\Response;
use Illuminate\Support\Facades\Log;

class EntrenamientoPolicy
{
    /**
     * Determine whether the user can view any models.
     */
    public function viewAny(User $user): bool
    {
        //
    }

    /**
     * Determine whether the user can view the model.
     */
    public function view(User $user, Entrenamiento $entrenamiento): bool
    {
        //
    }

    /**
     * Determine whether the user can create models.
     */
    public function create(User $user): bool
    {
        //
    }

    /**
     * Determine whether the user can update the model.
     */
    public function update(Entrenador $entrenador, Entrenamiento $entrenamiento): bool
    {
        Log::debug('Entrenador ID: ' . $entrenador->id); 
        Log::debug('Entrenamiento Entrenador ID: ' . $entrenamiento->entrenador_id);
         return $entrenador->id === $entrenamiento->entrenador_id;
        // return true;
    }

    /**
     * Determine whether the user can delete the model.
     */
    public function delete(Entrenador $entrenador, Entrenamiento $entrenamiento): bool
    {
        Log::debug('Evaluando autorización delete:');
        Log::debug('Entrenador ID: ' . $entrenador->id);
        Log::debug('Entrenamiento Entrenador ID: ' . $entrenamiento->entrenador_id);
        return $entrenador->id === $entrenamiento->entrenador_id;
    }

    /**
     * Determine whether the user can restore the model.
     */
    public function restore(User $user, Entrenamiento $entrenamiento): bool
    {
        //
    }

    /**
     * Determine whether the user can permanently delete the model.
     */
    public function forceDelete(User $user, Entrenamiento $entrenamiento): bool
    {
        //
    }
}
