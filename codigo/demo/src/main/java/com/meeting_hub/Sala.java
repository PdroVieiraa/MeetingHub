package com.meeting_hub;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Sala {
 private String codigo;
 private int capacidade;
 private ArrayList<String> recursos = new ArrayList<>();    

 public Sala(String codigo, int capacidade) {
    this.codigo = codigo;
    this.capacidade = capacidade;
}
// Metodos Abstratos
public abstract double getCustoHora();
public abstract double getPercentualReembolso();

 
public boolean verificarDisponibilidade(LocalDateTime dataInicio, LocalDateTime dataFim) {
    // Verifica se a sala está disponível entre dataInicio e dataFim
        return true; 
    }

    // Adiciona recurso à sala
    public void addRecurso(String recurso) {
        recursos.add(recurso);
    }

    // Retorna lista de recursos
    public List<String> listarRecursos() {
        return new ArrayList<>(recursos); // Retorna cópia para evitar alterações externas
    }
    public double getCapacidade() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCapacidade'");
    }
    public void reservar(LocalDateTime dataInicio, LocalDateTime dataFim) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reservar'");
    }
}

