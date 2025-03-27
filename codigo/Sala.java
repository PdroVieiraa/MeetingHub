import java.util.ArrayList;
import java.util.List;
public class Sala {
    private String codigo;
    private int capacidade;
    private List<Recurso> recursos = new ArrayList<>();

    public Sala(String codigo, int capacidade) {
        this.codigo = codigo;
        this.capacidade = capacidade;
    }

    public void addRecurso(Recurso recurso) {
        recursos.add(recurso);
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }
}
