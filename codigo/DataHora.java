import java.time.LocalDateTime;
public class DataHora {
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public DataHora(LocalDateTime inicio, LocalDateTime fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public double calcularDuracaoHoras() {
        return java.time.Duration.between(inicio, fim).toHours();
    }

    public String formatar(String padrao) {
        return inicio.format(java.time.format.DateTimeFormatter.ofPattern(padrao));
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }
}
