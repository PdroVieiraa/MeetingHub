import java.util.ArrayList;
import java.util.List;
public class Cliente {
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private TipoCliente tipoCliente;
    private List<Reserva> reservas = new ArrayList<>();

    public Cliente(int id, String nome, String cpf, String telefone, TipoCliente tipoCliente) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.tipoCliente = tipoCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }
}
