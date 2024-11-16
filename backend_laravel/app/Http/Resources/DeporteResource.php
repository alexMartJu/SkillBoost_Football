<?php

namespace App\Http\Resources;

use Illuminate\Http\Resources\Json\JsonResource;

class DeporteResource extends JsonResource
{
    public function toArray($request)
    {
        $pistas = [];
        foreach ($this->pistas as $p) {
            $pista = [
                "id" => $p->id,
                "nombre_pista" => $p->nombre,
            ];
            array_push($pistas, $pista);
        }

        return [
            'id_deporte' => $this->id,
            'deporte' => $this->nombre,
            'pista' => $pistas
        ];
    }
}
