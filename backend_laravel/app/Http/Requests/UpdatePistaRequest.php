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
        return true;
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
            'nombre' => 'string|max:255|nullable',
            'deportes' => 'array|nullable',
            'deportes.*' => 'integer|exists:deportes,id',
            'imagenes' => 'array|nullable',
            'imagenes.*' => 'string|url',
        ];
    }
}
