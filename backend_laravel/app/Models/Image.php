<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Relations\MorphTo;
use Illuminate\Support\Str;
use Illuminate\Database\Eloquent\Relations\BelongsToMany;
use Illuminate\Database\Eloquent\SoftDeletes;
class Image extends Model
{
    use HasFactory, SoftDeletes;

    protected $hidden = ['created_at', 'updated_at'];
    public $timestamps = true;
    protected $fillable = [
        'image_url',
        'imageable_type',
        'imageable_id',
    ];
    public function imageable(): MorphTo
    {
        return $this->morphTo();
    }
}
