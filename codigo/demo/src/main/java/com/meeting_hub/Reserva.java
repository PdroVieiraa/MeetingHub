package com.meeting_hub;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;

import com.meeting_hub.Clientes.Corporativo;
import com.meeting_hub.Salas.Premium;
import com.meeting_hub.Salas.Standard;
import com.meeting_hub.Salas.Vip;


public class Reserva {
    private int id;
    private double custoTotal;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private static final double DESCONTO_CORPORATIVO;
    private Cliente cliente;
    private Sala sala;
    private static List<Reserva> todasAsReservas = new ArrayList<>();
    
    static{
        DESCONTO_CORPORATIVO=0.10;
    } 
    public Reserva(int id, LocalDateTime dataInicio, LocalDateTime dataFim, Cliente cliente, Sala sala) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cliente = cliente;
        this.sala = sala;
    }
    

    //GET e SETT NECESSARIOS
    public Sala getSala(){
        return sala;
    }
    public int getId(){
        return id;
    }
    public Cliente getCliente() {
        return cliente;
    }

    //Funçoes auxilires da classe
    private long calcularDuracao(){
        Duration duracao = Duration.between(this.dataInicio, this.dataFim);
        return duracao.toHours();
    }

    private double calcularCustoSala(){
        double custoSala = 0.0;
        if (sala instanceof Vip){
            custoSala = (sala.getCustoHora() * calcularDuracao());
        }
        if (sala instanceof Premium) {
            custoSala = (sala.getCustoHora() * calcularDuracao());
        }
        if (sala instanceof Standard) {
            custoSala =  (sala.getCustoHora() * calcularDuracao());
        }
        return custoSala;
    }

    public double calcularCustoTotal() {
        // Calcula a duração da reserva em horas
        long horas = java.time.Duration.between(dataInicio, dataFim).toHours();

        // Obtém o custo por hora da sala associada
        double custoHora = sala.getCustoHora();

        // Calcula o custo total
        return horas * custoHora;
    }

    private boolean horasConflitam(LocalDateTime inicio2, LocalDateTime fim2){
        return (this.dataInicio.isBefore(inicio2)  || this.dataFim.isAfter(fim2));
   }

    private boolean verificarDisponibilidade(List<Reserva> todasReservas) {
    for (Reserva reserva : todasReservas) {
        if (!reserva.getSala().getCodigoAtual().equals(this.sala.getCodigoAtual())) {
            continue;
        }
        if (horasConflitam(reserva.dataInicio, reserva.dataFim)) {
            return false;
        }
    }
    return true; 
}

    private double retornarValorReembolso(){
        double reembolso = 0.0;
        if (sala instanceof Premium) {
            reembolso =  (calcularCustoSala() * sala.getPercentualReembolso());
        }
        if (sala instanceof Vip) {
            reembolso =  (calcularCustoSala() * sala.getPercentualReembolso());
        }
        if (sala instanceof Standard) {
            reembolso =  (calcularCustoSala() * sala.getPercentualReembolso());
        }
        return reembolso;
    }
    //Principais Funçoes
    public boolean efetuarReserva(List<Reserva> todasReservas){
        if (!verificarDisponibilidade(todasReservas)) {
            System.out.println("Sala indiponivel no momento: ");
            return false;
        }
        todasAsReservas.add(this);
        System.out.println("Reserva cadastrada com sucesso");
        return true;
    }
    
    public boolean cancelarReserva(int id) {
        int index = 0;
        for (Reserva reserva : todasAsReservas) {
            if (reserva.getId()==this.id) {
                System.out.println("Reserva cancelada com sucesso, valor reembolsado: "+retornarValorReembolso());
                todasAsReservas.remove(index);
                return true;
            }
            index++;
        }
        System.out.println("Reserva não encontrada! ");
        return false;
    }



}
