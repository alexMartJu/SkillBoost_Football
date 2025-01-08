<?php

namespace App\Http\Resources;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;
use App\Http\Resources\ProfilesResource;

class EntrenamientoResource extends JsonResource
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
            'deporte' => $this->deporte,
            'horario' => $this->horario ? $this->horario->hora : null,
            'status' => $this->status,
            'entrenador' => [
                'nombre' => $this->entrenador->nombre,
                'apellidos' => $this->entrenador->apellidos,
                'id' => $this->entrenador->id,
            ],
            'profiles' => ProfilesResource::collection($this->whenLoaded('profiles')),
        ];
    }
}