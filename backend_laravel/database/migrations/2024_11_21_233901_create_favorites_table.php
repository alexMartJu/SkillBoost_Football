<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('favorites', function (Blueprint $table) {
            $table->foreignId('usuario_id')->constrained()->onDelete('cascade');
            $table->foreignId('entrenamiento_id')->constrained()->onDelete('cascade');
            $table->timestamps();

            $table->unique(['usuario_id', 'entrenamiento_id']);
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('favorites');
    }
};
