<?php

namespace App\Http\Resources;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

class ReservasResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @return array<string, mixed>
     */
    public function toArray(Request $request): array
    {
        // return parent::toArray($request);
        return [
            'id' => $this->id,
            'profile' => [
                'id' => $this->profile->id,
                'nombre' => $this->profile->nombre,
                'apellidos' => $this->profile->apellidos,
            ],
            'pista' => [
                'id' => $this->pista->id,
                'nombre' => $this->pista->nombre,
            ],
            'horario' => [
                'id' => $this->horario->id,
                'hora' => $this->horario->hora,
            ],
            'fecha' => $this->fecha,
        ];
    }
}
