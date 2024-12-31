<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\SoftDeletes;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class Reserva extends Model
{
    use HasFactory, SoftDeletes;

    
    protected $hidden = ['created_at', 'updated_at', 'deleted_at'];

   
    public $timestamps = true;

    
    protected $fillable = [
        'profile_id',
        'pista_id',
        'horario_id',
        'dia',
        'status',
    ];

    public function profile(): BelongsTo
    {
        return $this->belongsTo(Profile::class, 'profile_id');
    }

    public function pista(): BelongsTo
    {
        return $this->belongsTo(Pista::class, 'pista_id');
    }
    public function horario(): BelongsTo
    {
        return $this->belongsTo(Horario::class, 'horario_id');
    }
}
