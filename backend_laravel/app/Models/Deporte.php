<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Support\Str;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;

class Deporte extends Model
{
    use HasFactory;

    protected $hidden = ['created_at', 'updated_at'];
    protected $fillable = [
        'nombre',
        'slug',
    ];
    public function getRouteKeyName(): string
    {
        return 'slug';
    }
    public function setNombreAttribute(string $nombre): void
    {
        $this->attributes['nombre'] = $nombre;

        $this->attributes['slug'] = Str::slug($nombre);
    }

    public function pistas(): BelongsToMany
    {
        return $this->belongsToMany(Pista::class, 'deporte_pista');
    }
    public function entrenamientos(): HasMany
    {
        return $this->hasMany(Entrenamiento::class);
    }
}