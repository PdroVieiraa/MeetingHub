package com.meeting_hub.Salas;

import com.meeting_hub.Sala;

public class Vip extends Sala{
    public Vip(String codigoAtual, int capacidade) {
        super(capacidade, codigoAtual);
        // Recursos padrão
        addRecurso("Projetor");
        addRecurso("Ar-condicionado");
        addRecurso("Catering");
    }

    @Override
    public double getCustoHora() {
        return 65.0; // R$65.00/hora
    }

    @Override
    public double getPercentualReembolso() {
        return 0.30; // 30% de reembolso
    }

}
