package com.meeting_hub;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class ReservaArquivoDAO {
    private static final String ARQUIVO = "reservasDB.txt";

    public static void salvarReserva(Reserva reserva){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))){
            writer.write(reserva.getId() + " | " + 
            reserva.getCliente().getId() + " | " + 
            reserva.getDataInicio() + " | " + 
            reserva.getDataFim() + " | " + 
            reserva.getSala().getCodigoAtual() + " | " + 
            reserva.getCustoTotal() + " | " + 
            reserva.getReembolso());
        } catch (IOException e){
            System.out.println("Erro ao Salvar reserva: " + e.getMessage());
        }
    }

    public static List<Reserva> carregReservas(List<Cliente> clientes, List<Sala> salas){
        List<Reserva> reservas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))){
            String linha;
            while ((linha = reader.readLine()) != null) {
                String [] partes = linha.split("\\| ");
                if(partes.length < 7) continue;

                int id = Integer.parseInt(partes[0].trim());
                int idCliente = Integer.parseInt(partes[1].trim());
                LocalDateTime dataInicio = LocalDateTime.parse(partes[2].trim());
                LocalDateTime dataFim = LocalDateTime.parse(partes[3].trim());
                String codigoSala = partes[4].trim();
                double custoTotal = Double.parseDouble(partes[5].trim());
                double reembolso = Double.parseDouble(partes[6].trim());

                Cliente cliente = buscarClientePorId(clientes, idCliente);
                Sala sala = buscarSalaPorCodigo(salas, codigoSala);

                if (cliente != null && sala != null) {
                    Reserva reserva = new Reserva(id, dataInicio, dataFim, cliente, sala, custoTotal, reembolso);
                    reservas.add(reserva);
                }else{
                    System.out.println("Cliente ou Sala não encontrada para a reserva ID: " + id);
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar reservas: " + e.getMessage());
        }

        return reservas;
    }



    private static Cliente buscarClientePorId(List<Cliente> clientes, int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    
    private static Sala buscarSalaPorCodigo(List<Sala> salas, String codigo) {
        for (Sala s : salas) {
            if (s.getCodigoAtual().equals(codigo)) return s;
        }
        return null;
    }
    

}
