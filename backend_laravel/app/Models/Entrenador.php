<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\MorphMany;
use Illuminate\Support\Str;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;
use Illuminate\Database\Eloquent\SoftDeletes;


class Entrenador extends Model
{
    use HasFactory, SoftDeletes;
    protected $hidden = ['created_at', 'updated_at'];
    public $timestamps = true;
     protected $table = 'entrenadores'; 
    protected $fillable = [
        'nombre', 
        'apellidos', 
        'DNI',
        'email',
        'password',
        'deporte_id',
        'edad'
    ];

    
    public function deporte()
    {
        return $this->belongsTo(Deporte::class, 'deporte_id');
    }

    
    public function entrenamientos()
    {
        return $this->hasMany(Entrenamiento::class, 'entrenador_id');
    }
    public function images(): MorphMany
    {
        return $this->morphMany(Image::class, 'imageable');
    }
}
