<?php

namespace App\Http\Resources;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

class DeportesResources extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @return array<string, mixed>
     */
    public function toArray(Request $request): array
    {
        $pistas = [];
        foreach ($this->pistas as $p) {
            $pista = [
                "id" => $p->pistas_id,
                "nombre_pista" => $p->nombre,
            ];
            array_push($pistas, $pista);
        }
        return [
            'id' => $this->deportes_id,
            'nombre' => $this->nombre,
            'slug' => $this->slug,
            'pista' => $pistas
        ];
    }
}
