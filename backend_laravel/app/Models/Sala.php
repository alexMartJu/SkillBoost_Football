<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\SoftDeletes;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Relations\MorphMany;
class Sala extends Model
{
    use HasFactory;
    protected $hidden = ['created_at', 'updated_at'];
    
    protected $fillable = [
        'nombre',
        'tamaño',
        'ubicacion',
        'entrenador_id',
    ];

    public function entrenador()
    {
        return $this->belongsTo(Entrenador::class, 'entrenador_id');
    }
    public function images(): MorphMany
    {
        return $this->morphMany(Image::class, 'imageable');
    }
}
