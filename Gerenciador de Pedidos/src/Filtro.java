/*
 * Filtro
 * Atributos:
 * tipoFiltro (Setor, Período, Funcionário, Número do Pedido)
 * valorFiltro (ex.: nome do setor, data inicial/final, matrícula do funcionário, número do pedido)
 * Métodos:
 * aplicarFiltro(List<Pedido> pedidos)
 */
import java.util.List;

public class Filtro {
    private String tipoFiltro;
    private String valorFiltro;

    public Filtro(String tipoFiltro, String valorFiltro) {
        this.tipoFiltro = tipoFiltro;
        this.valorFiltro = valorFiltro;
    }

    // Getters e Setters
    public String getTipoFiltro() {
        return tipoFiltro;
    }

    public String getValorFiltro() {
        return valorFiltro;
    }

    public void setTipoFiltro(String tipoFiltro) {
        this.tipoFiltro = tipoFiltro;
    }

    public void setValorFiltro(String valorFiltro) {
        this.valorFiltro = valorFiltro;
    }

    public List<Pedido> aplicarFiltro(List<Pedido> pedidos) {

        return pedidos;
    }
}
