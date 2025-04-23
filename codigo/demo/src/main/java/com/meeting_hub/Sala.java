package com.meeting_hub;


import java.util.ArrayList;


public abstract class Sala {
 private String codigoAtual = "SALA_A101";
 private int capacidade;
 private ArrayList<String> recursos = new ArrayList<>();    

 public Sala(int capacidade, String codigoAtual) {
    this.codigoAtual = codigoAtual;
    this.capacidade = capacidade;
}

public String getCodigoAtual(){
    return codigoAtual;
}

public int getCapacidade() {
    return capacidade;
}

// Metodos Abstratos
public abstract double getCustoHora();
public abstract double getPercentualReembolso();


    // Adiciona recurso à sala
    public void addRecurso(String recurso) {
        recursos.add(recurso);
    }

    // Retorna lista de recursos
    public ArrayList<String> listarRecursos() {
        return new ArrayList<>(recursos); // Retorna cópia para evitar alterações externas
    }
    
    public static String gerarProximoCodigo(String codigoAtual) {
        String prefixo = codigoAtual.substring(0, 5); 
        char letra = codigoAtual.charAt(5); 
        int numero = Integer.parseInt(codigoAtual.substring(6)); 
    
        numero++;
    
        if (numero > 999) {
            numero = 100;
            letra++;
    
            if (letra > 'Z') {
                throw new IllegalStateException("Limite de códigos atingido");
            }
        }
    
        return String.format("%s%c%03d", prefixo, letra, numero);
    }

    public static void main(String[] args) {
        Sala sala = new Sala(50, "SALA_A101") {
            @Override
            public double getCustoHora() {
                return 100.0;
            }

            @Override
            public double getPercentualReembolso() {
                return 0.5;
            }
        };

        System.out.println("Codigo: " + sala.getCodigoAtual() + " | Capacidade: " + sala.getCapacidade() + " | Recursos: " + sala.listarRecursos());
    }
}

