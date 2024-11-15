<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Pistas extends Model
{
    protected $fillable = [
        'nombre',
        'slug',
    ];
    public function setNombreAttribute(string $nombre): void
    {
        $this->attributes['nombre'] = $nombre;

        $this->attributes['slug'] = Str::slug($nombre);
    }
    public function Deportes(): BelongsToMany
    {
        return $this->belongsToMany(Deportes::class, 'deportes_pistas', 'deportes_id', 'pistas_id');
    }
}
