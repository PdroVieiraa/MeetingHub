

import java.util.List;

public class Reserva {
    private Cliente cliente;
    private int id;
    private Sala sala;
    private double custoTotal;
    private static final double PRECO_POR_HORA = 50.0;
    private static final double DESCONTO_CORPORATIVO = 0.10;
    private DataHora periodo;

    public Reserva(int id, Cliente cliente, Sala sala, DataHora periodo) {
        this.id = id;
        this.cliente = cliente;
        this.sala = sala;
        this.periodo = periodo;
        this.custoTotal = calcularCusto();
    }

    public boolean verificarDisponibilidade(List<Reserva> reservas) {
        for (Reserva r : reservas) {
            if (r.getSala().equals(this.sala) &&
                    !(r.getPeriodo().getFim().isBefore(this.periodo.getInicio()) ||
                            r.getPeriodo().getInicio().isAfter(this.periodo.getFim()))) {
                return false;
            }
        }
        return true;
    }

    public double calcularCusto() {
        double custo = periodo.calcularDuracaoHoras() * PRECO_POR_HORA;
        if (cliente.getTipoCliente().getDescricao().equalsIgnoreCase("Corporativo")) {
            custo -= custo * DESCONTO_CORPORATIVO;
        }
        return custo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Sala getSala() {
        return sala;
    }

    public DataHora getPeriodo() {
        return periodo;
    }

    public double getCustoTotal() {
        return custoTotal;
    }
}
