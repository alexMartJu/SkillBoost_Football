<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class UpdateEntrenadorRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     */
    public function authorize(): bool
    {
        return false;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, \Illuminate\Contracts\Validation\ValidationRule|array<mixed>|string>
     */
    public function rules(): array
    {
        $entrenadorId = $this->user()->id;

        return [
            'nombre' => 'nullable|string|max:255',
            'apellidos' => 'nullable|string|max:255',
            'email' => 'nullable|email|unique:entrenadores,email,' . $entrenadorId, 
            'password' => 'nullable|string|min:8',
            'deporte_id' => 'nullable|integer|exists:deportes,id',
            'edad' => 'nullable|integer|min:18',
            'imagenes' => 'nullable|array',
            'imagenes.*' => 'string|max:255',
        ];
    }
}
