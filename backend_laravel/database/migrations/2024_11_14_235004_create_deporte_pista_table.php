<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    /**
     * Run the migrations.
     */
    public function up()
    {
        Schema::create('deporte_pista', function (Blueprint $table) {
            $table->id();
            $table->foreignId('deporte_id')->constrained()->onDelete('cascade');
            $table->foreignId('pista_id')->constrained()->onDelete('cascade');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('deporte_pista');
    }
};
