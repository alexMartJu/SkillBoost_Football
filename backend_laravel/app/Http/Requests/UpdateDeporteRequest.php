<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class UpdateDeporteRequest extends FormRequest
{
    /**
     * Determina si el usuario está autorizado para realizar esta solicitud.
     *
     * @return bool
     */
    public function authorize()
    {
        return true; // Cambia esto si necesitas control de permisos
    }

    /**
     * Obtén las reglas de validación que se aplican a la solicitud.
     *
     * @return array
     */
    public function rules()
    {
        $slug = $this->route('slug');  // Obtén el 'slug' de la URL, utilizado para encontrar el deporte a actualizar

        return [
            'nombre' => 'nullable|string|max:191|unique:deportes,nombre,' . $slug . ',slug', 
            'imagenes' => 'nullable|array',
            'imagenes.*' => 'string|max:255', 
        ];
    }
}
