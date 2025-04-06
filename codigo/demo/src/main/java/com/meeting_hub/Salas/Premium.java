package com.meeting_hub.Salas;

import com.meeting_hub.Sala;

public class Premium extends Sala {

    public Premium(String codigoAtual, int capacidade) {
        super(capacidade, codigoAtual);
        // Recursos padrão
        addRecurso("Projetor");
        addRecurso("Ar-condicionado");
    }

    @Override
    public double getCustoHora() {
        return 57.5; // R$57.50/hora
    }

    @Override
    public double getPercentualReembolso() {
        return 0.40; // 40% de reembolso
    }

}
