<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Pista_privada extends Model
{
    use HasFactory, SoftDeletes;

    protected $hidden = ['created_at', 'updated_at'];
    public $timestamps = true;
    protected $fillable = [
        'nombre',
        'slug',
    ];
    public function setNombreAttribute(string $nombre): void
    {
        $this->attributes['nombre'] = $nombre;

        $this->attributes['slug'] = Str::slug($nombre);
    }
    public function deportes(): BelongsToMany
    {
        return $this->belongsToMany(Deporte::class, 'deporte_pista_privada');
    }

    public function images(): MorphMany
    {
        return $this->morphMany(Image::class, 'imageable');
    }
}
