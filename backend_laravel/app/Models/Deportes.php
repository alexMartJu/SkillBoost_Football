<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Support\Str;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;

class Deportes extends Model
{
    use HasFactory;
    protected $table = 'deportes';
    protected $primaryKey = 'deportes_id';
    protected $keyType = 'int';
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
        return $this->belongsToMany(Pistas::class, 'deportes_pistas', 'deportes_id', 'pistas_id');
    }
    public function clases(): HasMany
    {
        return $this->hasMany(Clases::class);
    }
}