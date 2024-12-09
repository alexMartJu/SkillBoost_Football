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


class Usuario extends Model
{
    use HasFactory, SoftDeletes;
    protected $hidden = ['created_at', 'updated_at'];
    public $timestamps = true;
    protected $fillable = [
        'email',
        'password'
    ];

    public function profile()
    {
        return $this->hasOne(Profile::class, 'id', 'id');
    }
    
}
