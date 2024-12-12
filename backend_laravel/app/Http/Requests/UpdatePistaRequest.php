<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class UpdatePistaRequest extends FormRequest
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
        $slug = $this->route('slug');
        return [
           'nombre' => 'nullable|string|max:191|unique:pistas,nombre,' . $slug . ',slug',
            'deportes' => 'nullable|array',
            'deportes.*' => 'exists:deportes,id',
            'imagenes' => 'nullable|array',
            'imagenes.*' => 'string|max:255',
        ];
    }
}
