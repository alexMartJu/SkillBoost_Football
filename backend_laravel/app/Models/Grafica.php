<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Grafica extends Model
{
    use HasFactory;

   
    protected $table = 'graficas';

   
    protected $fillable = [
        'año',
        'mes',
        'seccion',
        'nivel',
        'profile_id',
    ];

    
    public function profile()
    {
        return $this->belongsTo(Profile::class);
    }
}
