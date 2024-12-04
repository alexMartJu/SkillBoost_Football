<?php

namespace App\Http\Resources;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;
use App\Http\Resources\EntrenadoresResources;

class SalasResources extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @return array<string, mixed>
     */
    public function toArray(Request $request): array
    {
        return [
            'id' => $this->id,
            'nombre' => $this->nombre,
            'tamaño' => $this->tamaño,
            'ubicacion' => $this->ubicacion,
            'slug' => $this->slug,
            'entrenador_id' => $this->entrenador_id,
            'entrenador' => new EntrenadoresResources($this->whenLoaded('entrenador')),
            'imagenes' => $this->images
        ];
    }
}
