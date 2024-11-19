<?php

namespace App\Http\Resources;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

class ClaseResource extends JsonResource
{
    public function toArray($request)
    {
        return [
            'id' => $this->id,
            'nombre' => $this->nombre,
            'slug' => $this->slug,
            'descripcion' => $this->descripcion,
            'duracion' => $this->duracion,
            'max_plazas' => $this->max_plazas,
            'precio' => $this->precio,
            'deporte' => $this->deporte
        ];
    }
}