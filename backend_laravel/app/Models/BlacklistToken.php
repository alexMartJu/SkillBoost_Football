<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class BlacklistToken extends Model
{
    protected $table = 'blacklist_token';
    
    protected $fillable = [
        'usuario_id', 
        'refresh_token',
        'revoke_time'
    ];

    public function usuario()
    {
        return $this->belongsTo(Usuario::class, 'usuario_id'); 
    }
}
