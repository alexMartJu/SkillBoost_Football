<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;
use Illuminate\Support\Str;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Deportes>
 */
class DeportesFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $nombre = $this->faker->word. ' ' . $this->faker->word;
        $slug = Str::slug($nombre);

        // Ensure the slug is unique
        while (\App\Models\Deportes::where('slug', $slug)->exists()) {
            $nombre = $this->faker->word . ' ' . $this->faker->word;  // Regenerar el nombre y el slug si el actual ya existe
            $slug = Str::slug($nombre);  
        }
        return [
            'nombre' => $nombre,
            'slug' => $slug,
        ];
    }
}
