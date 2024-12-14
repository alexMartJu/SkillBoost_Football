<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class Blacklist extends Model
{
    protected $fillable = [
        'usuario_id', 
        'token',
    ];

    public function usuario()
    {
        return $this->belongsTo(Usuario::class, 'usuario_id'); 
    }
}
