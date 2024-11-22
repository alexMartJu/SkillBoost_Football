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
        Schema::create('horario_pista_reserva', function (Blueprint $table) {
            $table->foreignId('horario_id')->constrained()->onDelete('cascade');
            $table->foreignId('pista_id')->constrained()->onDelete('cascade');
            $table->foreignId('reserva_id')->constrained()->onDelete('cascade');
            $table->timestamps();
            $table->softDeletes();
            $table->primary(['horario_id', 'pista_id']);
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('horario_pista_reserva');
    }
};
