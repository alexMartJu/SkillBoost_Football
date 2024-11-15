<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Deporte extends Model
{
    use HasFactory;

    protected $fillable = ['nombre'];

    public function clases()
    {
        return $this->hasMany(Clase::class);
    }

    public function pistas()
    {
        return $this->belongsToMany(Pista::class, 'deporte_pista');
    }
}
