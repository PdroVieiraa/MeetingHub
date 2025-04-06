package com.meeting_hub.Salas;

import com.meeting_hub.Sala;

public class Standard extends Sala{
    public Standard(String codigoAtual, int capacidade) {
        super(capacidade, codigoAtual);
        // Recursos padrão
    }

    @Override
    public double getCustoHora() {
        return 50.0; // 50/hora
    }

    @Override
    public double getPercentualReembolso() {
        return 0.60; // 60% de reembolso
    }
}
