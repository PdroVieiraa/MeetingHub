package com.meeting_hub;

import com.meeting_hub.Salas.Standard;
import com.meeting_hub.Salas.Vip;
import com.meeting_hub.Salas.Premium;

import java.io.*;
import java.util.*;


public class SalaManager {
    private static final String AQUIVO_SALAS = "salas.txt";

    public static void salvarSalas(List<Sala> salas){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(AQUIVO_SALAS))){
            for(Sala sala: salas){
                String tipo = sala.getClass().getSimpleName();
                String codigo = sala.getCodigoAtual();
                int capacidade = sala.getCapacidade();
                String recursos = String.join(",",sala.listarRecursos());

                String linha = tipo + " | " + codigo + " | " + capacidade + " | " + recursos;
                writer.write(linha);
                writer.newLine();
                
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar salas: " + e.getMessage());
        }
    }


    public static List<Sala> carregarSalas(){
        List<Sala> salas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader (new FileReader(AQUIVO_SALAS))){
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(" \\| ");
                if(partes.length < 4) continue;

            String tipo = partes[0];
            String codigo = partes[1];
            int capacidade = Integer.parseInt(partes[2]);
            String[] recursosArray = partes[3].split(",");
            
            Sala sala = null;

            switch (tipo) {
                case "Standart":
                    sala = new Standard(codigo, capacidade);
                    break;
                case "Vip":
                    sala = new Vip(codigo, capacidade);
                    break;
                case "Premium":
                    sala = new Premium(codigo, capacidade);
                    break;
            }

            if(sala != null){
                for(String recurso : recursosArray){
                    if(!recurso.trim().isEmpty()){
                        sala.addRecurso(recurso.trim());
                    }
                }
                salas.add(sala);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar salas: " + e.getMessage());
        }
        return salas;
    }

}

