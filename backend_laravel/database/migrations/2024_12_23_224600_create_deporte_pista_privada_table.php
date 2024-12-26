<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('deporte_pista_privada', function (Blueprint $table) {
            $table->foreignId('deporte_id')->constrained()->onDelete('cascade');
            $table->foreignId('pista_privada_id')->constrained()->onDelete('cascade');
            $table->timestamps();
            $table->primary(['deporte_id', 'pista_privada_id']);
            $table->engine = 'InnoDB';
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('deporte_pista_privada');
    }
};
