<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Pista extends Model
{
    use HasFactory;

    protected $fillable = ['nombre'];

    public function deportes()
    {
        return $this->belongsToMany(Deporte::class, 'deporte_pista');
    }
}
