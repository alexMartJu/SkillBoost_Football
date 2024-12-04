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
class Pista extends Model
{
    use HasFactory, SoftDeletes;

    protected $hidden = ['created_at', 'updated_at'];
    public $timestamps = true;
    protected $fillable = [
        'nombre',
        'slug',
    ];
    public function setNombreAttribute(string $nombre): void
    {
        $this->attributes['nombre'] = $nombre;

        $this->attributes['slug'] = Str::slug($nombre);
    }
    public function deportes(): BelongsToMany
    {
        return $this->belongsToMany(Deporte::class, 'deporte_pista');
    }

    public function images(): MorphMany
    {
        return $this->morphMany(Image::class, 'imageable');
    }
}
