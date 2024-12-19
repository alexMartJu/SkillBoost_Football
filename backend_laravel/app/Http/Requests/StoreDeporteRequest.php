<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Validation\Rule;
class StoreDeporteRequest extends FormRequest
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
        return [
            'nombre' => 'required|string|max:191|unique:deportes',
            'imagenes' => 'nullable|array', 
            'imagenes.*' => 'string|max:255', 
        ];
    }
}
