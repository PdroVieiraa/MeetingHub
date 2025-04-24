package com.meeting_hub;

import com.meeting_hub.Clientes.Corporativo;
import com.meeting_hub.Sala.*;
import com.meeting_hub.Salas.Premium;
import com.meeting_hub.Salas.Standard;
import com.meeting_hub.Salas.Vip;

import java.nio.file.ClosedWatchServiceException;
import java.rmi.server.ExportException;
import java.time.LocalDateTime;
import java.util.*;

import javax.print.attribute.standard.CopiesSupported;



public class Main{

    private static List<Sala> salas = SalaManager.carregarSalas(); //Carrega as salas salvas no txt.
    static List<Reserva> reservas = new ArrayList<>();
    static List<Cliente> clientes = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int reservaId = 1;


    public static void main(String[] args){
        int opcao;

        do{
            System.out.println("----------- MeetingHUB -------------");
            System.out.println("1 - Cadastrar sala");
            System.out.println("2 - Listar Salas");
            System.out.println("3 - Cadastrar cliente corporativo");
            System.out.println("4 - Realizar reserva");
            System.out.println("5 - Cancelar reserva");
            System.out.println("6 - Listar reservas");
            System.out.println("0 - Sair");
            System.out.println("------------------------");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarSala();
                case 2 -> listarSalas();
                case 3 -> cadastrarClienteCorporativo();
                case 4 -> realizarReserva();
                case 5 -> cancelarReserva();
                case 6 -> listarReservas();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        }while (opcao != 0);
    }

    private static void cadastrarSala(){
        System.out.println("------------------------");
        System.out.print("Selecione o tipo de sala:\n1-Standart\n2-Premium\n3-Vip\n");
        System.out.println("------------------------");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Capacidade da sala: ");
        int capacidade = scanner.nextInt();
        scanner.nextLine();

        String ultimoCodigo = salas.isEmpty() ? "SALA_A100" : salas.get(salas.size() - 1).getCodigoAtual();
        String novoCodigo = Sala.gerarProximoCodigo(ultimoCodigo);
        Sala novaSala = switch (tipo) {
            case 1 -> new Standard(novoCodigo, capacidade);
            case 2 -> new Premium(novoCodigo, capacidade);
            case 3 -> new Vip(novoCodigo, capacidade);
            default -> null;
        };

        if(novaSala != null) {
            salas.add(novaSala);
            System.out.println("Sala cadastrada com codigo: " + novoCodigo);
            SalaManager.salvarSalas(salas);
        } else {
            System.out.println("Tipo de sala inválido.");
        }
        System.out.println("------------------------");
    }

    private static void listarSalas(){
        if(salas.isEmpty()) {
            System.out.println("Nenhuma sala cadastrada.");
            return;
        }

        System.out.println("--- Salas Disponíveis ---");
        for(Sala sala: salas){
            System.out.println("Codigo: " + sala.getCodigoAtual() + " | Capacidade: " + sala.getCapacidade() + " | Recursos: " + sala.listarRecursos());
        }
    }

    private static void cadastrarClienteCorporativo() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        Corporativo cliente = new Corporativo(nome, telefone, cnpj); // Usando o construtor

        clientes.add(cliente);
        System.out.println("Cliente coportavito cadastrado com sucesso!");
    }

    private static void realizarReserva() {
        System.out.print("Cliente convidado (S/N)? ");
        String resposta = scanner.nextLine();
        boolean ehConvidado = resposta.equalsIgnoreCase("S"); 
    
        Cliente cliente;
        if (ehConvidado) {
            cliente = new Cliente() {{
                nome = "Convidado";
                telefone = "00000000000";
            }};
        } else {
            System.out.print("Digite o nome do cliente: ");
            String nome = scanner.nextLine();
            cliente = clientes.stream()
                              .filter(c -> c.getNome().equalsIgnoreCase(nome))
                              .findFirst()
                              .orElse(null);
    
            if (cliente == null) {
                System.out.println("Cliente não encontrado.");
                return;
            }
        }
    
        System.out.print("Código de sala: ");
        String codigoSala = scanner.nextLine();
        Sala sala = salas.stream()
                         .filter(s -> s.getCodigoAtual().equalsIgnoreCase(codigoSala))
                         .findFirst()
                         .orElse(null);
    
        if (sala == null) {
            System.out.println("Sala não encontrada.");
            return;
        }
    
        try {
            System.out.print("Data e hora início (AAAA-MM-DDTHH:MM): ");
            LocalDateTime inicio = LocalDateTime.parse(scanner.nextLine());
    
            System.out.print("Data e hora fim (AAAA-MM-DDTHH:MM): ");
            LocalDateTime fim = LocalDateTime.parse(scanner.nextLine());
    
            Reserva reserva = new Reserva(reservaId++, inicio, fim, cliente, sala);
            if (reserva.efetuarReserva(reservas)) {
                reservas.add(reserva);
                System.out.println("Reserva realizada com sucesso! Custo total: R$" + reserva.calcularCustoTotal());
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar datas. Use o formato correto.");
        }
    }
    

    private static void cancelarReserva() { 
        System.out.print("ID da reserva para cancelamento: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Reserva reserva = reservas.stream().filter(r -> r.getId() == id).findFirst().orElse(null);

        if (reserva != null && reserva.cancelarReserva(id)) {
            reservas.remove(reserva);
        }
    }

    private static void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva registrada.");
            return;
        }

        System.out.println("--- Reservas ---");
        for (Reserva r : reservas) {
            System.out.println("ID: " + r.getId() + " | Cliente: " + r.getCliente().getNome() + " | Sala: " + r.getSala().getCodigoAtual() + " | Custo: R$" + r.calcularCustoTotal());
        }
    }
}