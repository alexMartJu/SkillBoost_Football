<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Clase extends Model
{
    use HasFactory;

    protected $fillable = ['nombre', 'deporte_id'];

    public function deporte()
    {
        return $this->belongsTo(Deporte::class);
    }
}
