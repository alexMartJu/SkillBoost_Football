<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class RegisterEntrenadorRequest extends FormRequest
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
        return [
            'nombre' => 'required|string|max:255',
            'apellidos' => 'required|string|max:255',
            'email' => [
                'required',
                'email',
                'max:255',
                Rule::unique('entrenadores', 'email'),
                Rule::unique('usuarios', 'email'),
                Rule::unique('admins', 'email'),
            ],
            'password' => 'required|string|min:8|confirmed',
            'deporte_id' => 'required|integer|exists:deportes,id',
            'edad' => 'required|integer|min:18',
        ];
    }

    public function messages(): array
    {
        return [
            'email.unique' => 'El correo electrónico ya está registrado en otra cuenta.',
        ];
    }


}
