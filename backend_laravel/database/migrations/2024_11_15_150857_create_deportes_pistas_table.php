<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up(): void
    {
        Schema::create('deporte_pista', function (Blueprint $table) {
            $table->foreignId('deporte_id')->constrained()->onDelete('cascade');
            $table->foreignId('pista_id')->constrained()->onDelete('cascade');
            $table->primary(['deporte_id', 'pista_id']);
            $table->timestamps();
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('deporte_pista');
    }
};
