package com.meeting_hub;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;


public class ReservaManager {
    private static final String ARQUIVO_RESERVAS = "reservas.txt";

    public static void salvarReserva(Reserva reserva){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_RESERVAS))){
            String linha = reserva.getId() + " | " + 
                reserva.getCliente() + " | " + 
                reserva.getSala().getCodigoAtual() + " | " + 
                reserva.getSala().getCapacidade() + " | " + 
                reserva.getDataInicio() + " | " + 
                reserva.getDataFim() + " | " + 
                reserva.getCustoTotal() + " | " + 
                reserva.getReembolso();

            writer.write(linha);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Erro ao salvar reserva: " + e.getMessage());
        }
    }

    public static List<Reserva> carregReservas() {
        List<Reserva> reservas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_RESERVAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(" \\| ");

                int id = Integer.parseInt(dados[0]);
                String cliente = dados[1];
                String codigoSala = dados[2];
                int capacidadeSala = Integer.parseInt(dados[3]); 
                LocalDateTime dataInicio = LocalDateTime.parse(dados[4]);
                LocalDateTime dataFim = LocalDateTime.parse(dados[5]);
                double custoTotal = Double.parseDouble(dados[6]);
                double reembolso = Double.parseDouble(dados[7]);

                // Buscar ou criar a sala
                Sala sala = new Sala(codigoSala);// Exemplo de método auxiliar
                
                // Criar a reserva e adicioná-la à lista
                
                Reserva reserva = new Reserva(id, dataInicio, dataFim, cliente, sala, custoTotal, reembolso);
                reservas.add(reserva);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar reservas: " + e.getMessage());
        }
        return reservas;
    }

}
