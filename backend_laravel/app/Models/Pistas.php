<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Support\Str;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;

class Pistas extends Model
{
    use HasFactory;
    protected $table = 'pistas';
    protected $primaryKey = 'pistas_id';
    protected $keyType = 'int';
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
        return $this->belongsToMany(Deportes::class, 'deportes_pistas', 'deportes_id', 'pistas_id');
    }
}
