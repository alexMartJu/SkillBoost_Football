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
        Schema::create('clases', function (Blueprint $table) {
            $table->unsignedBigInteger('deportes_id');

            $table->id();
            $table->string('nombre');
            $table->string('slug')->unique();
            $table->string('descripcion');
            $table->integer('duracion');
            $table->integer('max_plazas');
            $table->integer('precio');
            $table->foreign('deportes_id')->references('deportes_id')->on('deportes')->onDelete('cascade');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('clases');
    }
};
