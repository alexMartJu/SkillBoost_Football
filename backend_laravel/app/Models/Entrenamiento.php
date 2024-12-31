<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Support\Str;
use Illuminate\Database\Eloquent\SoftDeletes;

class Entrenamiento extends Model
{
    use HasFactory, SoftDeletes;

    protected $hidden = ['created_at', 'updated_at'];
    public $timestamps = true;

    protected $fillable = [
        'nombre',
        'slug',
        'descripcion',
        'dia',
        'duracion',
        'max_plazas',
        'precio',
        'status',
        'deporte_id',
        'horario_id',
        'entrenador_id',
        'pista_privada_id'
    ];
    protected static function boot()
{
    parent::boot();

    static::creating(function ($entrenamiento) {
        if (is_null($entrenamiento->status)) {
            $entrenamiento->status = 'pending';
        }
    });
}

    public function getRouteKeyName(): string
    {
        return 'slug';
    }

    public function setNombreAttribute(string $nombre): void
    {
        $this->attributes['nombre'] = $nombre;

        $this->attributes['slug'] = Str::slug($nombre);
    }

    public function deporte(): BelongsTo
    {
        return $this->belongsTo(Deporte::class);
    }
    public function horario()
    {
        return $this->belongsTo(Horario::class, 'horario_id');
    }   

    public function entrenador()
    {
        return $this->belongsTo(Entrenador::class, 'entrenador_id');
    }
    public function profiles()
    {
        return $this->belongsToMany(Profile::class, 'entrenamiento_profile', 'entrenamiento_id', 'profile_id');
    }
    public function pistaPrivada(): BelongsTo
    {
        return $this->belongsTo(Pista_privada::class, 'pista_privada_id');
    }
}
