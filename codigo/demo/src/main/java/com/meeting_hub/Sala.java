package com.meeting_hub;


import java.util.ArrayList;


public abstract class Sala {
 private String codigoAtual = "SALA_A101";
 private int capacidade;
 private ArrayList<String> recursos = new ArrayList<>();    

 public Sala( int capacidade, String codigoAtual) {
    this.codigoAtual = codigoAtual;
    this.capacidade = capacidade;
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
   
}

