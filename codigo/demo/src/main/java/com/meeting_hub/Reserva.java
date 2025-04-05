package com.meeting_hub;

import java.time.LocalDateTime;

import com.meeting_hub.Clientes.Corporativo;


public class Reserva {
    private Long id;
    private double custoTotal;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private static final double _PRECO_POR_HORA;
    private static final double _DESCONTO_CORPORATIVO;
    private Cliente cliente;
    private Sala sala;
    
    static{
        _PRECO_POR_HORA = 50.0;
        _DESCONTO_CORPORATIVO=0.10;
    } 

    public Reserva(Cliente cliente, Sala sala, LocalDateTime dataInicio, LocalDateTime dataFim) {
        this.cliente = cliente;
        this.sala = sala;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    // this.custoTotal = calcularCustoTotal();
    }
    
   // public double calcularCustoTotal() {}

    //public boolean verificarDisponibilidade() { }

    // public double cancelarReserva() {}



}
