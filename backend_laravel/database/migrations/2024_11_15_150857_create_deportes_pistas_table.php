<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('deportes_pistas', function (Blueprint $table) {
            $table->unsignedBigInteger('deportes_id');  
            $table->unsignedBigInteger('pistas_id');    

            // Claves foráneas
            $table->foreign('deportes_id')->references('deportes_id')->on('deportes')->onDelete('cascade');  
            $table->foreign('pistas_id')->references('pistas_id')->on('pistas')->onDelete('cascade');

            // Clave primaria compuesta para evitar duplicados
            $table->primary(['deportes_id', 'pistas_id']);

            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('deportes_pistas');
    }
};
