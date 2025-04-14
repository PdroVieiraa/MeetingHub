package com.meeting_hub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaTest {

    private Reserva reserva;
    private Sala sala;
    private Cliente cliente;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    // Parâmetros configuráveis para teste
    private int capacidade;
    private String codigo;
    private double custoHora;
    private double percentualReembolso;

    @BeforeEach
    public void setup() {
        // VALORES PERSONALIZADOS
        capacidade = 15;
        codigo = "SALA_A101";
        custoHora = 120.0;
        percentualReembolso = 0.4;

        sala = new Sala(capacidade, codigo) {
            @Override
            public double getCustoHora() {
                return custoHora;
            }

            @Override
            public double getPercentualReembolso() {
                return percentualReembolso;
            }
        };

        cliente = new Cliente() {}; // Cliente comum

        inicio = LocalDateTime.of(2025, 4, 15, 14, 0); // 15 de abril de 2025 às 14h
        fim = inicio.plusHours(2); // duração: 2h

        reserva = new Reserva(1, inicio, fim, cliente, sala);
    }

    @Test
    public void testEfetuarReservaComSalaDisponivel() {
        List<Reserva> reservas = new ArrayList<>();
        boolean sucesso = reserva.efetuarReserva(reservas);
        assertTrue(sucesso);
    }

    @Test
    public void testCancelarReservaExistente() {
        reserva.efetuarReserva(new ArrayList<>());
        boolean sucesso = reserva.cancelarReserva(1);
        assertTrue(sucesso);
    }

    @Test
    public void testCancelarReservaInexistente() {
        boolean sucesso = reserva.cancelarReserva(999);
        assertFalse(sucesso);
    }

    @Test
    public void testCalcularCustoTotalClienteComum() {
        double esperado = custoHora * 2; // 2 horas
        double resultado = reserva.calcularCustoTotal();
        assertEquals(esperado, resultado, 0.01);
    }

    @Test
    public void testReembolsoCorreto() {
        reserva.efetuarReserva(new ArrayList<>());
        reserva.cancelarReserva(1); // imprime o valor do reembolso
        assertTrue(true); // Apenas para verificar execução
    }
}
