
import java.time.LocalDate;

/*
 * Filtro
 * Atributos:
 * tipoFiltro (Setor, Período, Funcionário, Número do Pedido)
 * valorFiltro (ex.: nome do setor, data inicial/final, matrícula do funcionário, número do pedido)
 * Métodos:
 * aplicarFiltro(List<Pedido> pedidos)
 */
public class Filtro <T> {
    private String tipoFiltro;
    private T valorFiltro;
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public Filtro(String tipoFiltro, T valorFiltro) {
        this.tipoFiltro = tipoFiltro;
        this.valorFiltro = valorFiltro;
    }

    // Getters e Setters
    public String getTipoFiltro() {
        return tipoFiltro;
    }

    public T getValorFiltro() {
        return valorFiltro;
    }

    public void setTipoFiltro(String tipoFiltro) {
        this.tipoFiltro = tipoFiltro;
    }

    public void setValorFiltro(T valorFiltro) {
        this.valorFiltro = valorFiltro;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

}
