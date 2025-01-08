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
        Schema::create('graficas', function (Blueprint $table) {
            $table->id();
            $table->string('seccion');
            $table->integer('nivel');
            $table->foreignId('profile_id')->constrained()->onDelete('cascade');
            $table->integer('Año');
            $table->integer('Mes');
            $table->timestamps();
            $table->softDeletes();
            $table->engine = 'InnoDB';
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('graficas');
    }
};
