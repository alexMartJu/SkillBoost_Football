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
        return true;
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
            'email' => [
                'nullable',
                'email',
                'max:255',
                Rule::unique('entrenadores', 'email')->ignore($entrenadorId), 
                Rule::unique('usuarios', 'email'),
                Rule::unique('admins', 'email'),
            ],
            'nombre' => 'nullable|string|max:255',
            'apellidos' => 'nullable|string|max:255',
            'password' => 'nullable|string|min:8',
            'deporte_id' => 'nullable|integer|exists:deportes,id',
            'edad' => 'nullable|integer|min:18',
            'imagenes' => 'nullable|array',
            'imagenes.*' => 'string|max:255',
        
        ];
    }
    public function messages(): array
    {
        return [
            'email.unique' => 'El correo electrónico ya está registrado en otra cuenta, por favor use otro.',
        ];
    }
}
