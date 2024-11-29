<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\MorphMany;
use Illuminate\Support\Str;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;
use Illuminate\Database\Eloquent\SoftDeletes;
use Illuminate\Database\Eloquent\Relations\BelongsTo;


class Reserva extends Model
{
    use HasFactory, SoftDeletes;
    protected $hidden = ['created_at', 'updated_at'];
    public $timestamps = true;
    protected $fillable = [
        'usuario_id',
        'entrenamiento_id',
        'fecha_reserva',
        'plazas_reservadas',
        'estado'
    ];
    public function usuario()
    {
        return $this->belongsTo(Usuario::class, 'usuario_id');
    }

  
    public function entrenamiento()
    {
        return $this->belongsTo(Entrenamiento::class, 'entrenamiento_id');
    }

}
