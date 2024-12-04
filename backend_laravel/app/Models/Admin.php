<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\MorphMany;
use Illuminate\Support\Str;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;
use Illuminate\Database\Eloquent\SoftDeletes;

class Admin extends Model
{
    use HasFactory, SoftDeletes;
    protected $hidden = ['created_at', 'updated_at'];
    public $timestamps = true;
    
    protected $fillable = [
        'nombre',
        'apellidos',
        'email',
        'password'
    ];

}
