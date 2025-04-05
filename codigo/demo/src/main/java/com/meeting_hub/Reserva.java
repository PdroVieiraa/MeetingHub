package com.meeting_hub;

import java.time.LocalDateTime;

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

}
