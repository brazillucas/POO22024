/*
 * Atributos:
 * numeroPedido (gerado automaticamente)
 * tipoPedido (Enum | Uniforme, EPI, Almoxarifado)
 * itens (lista de IDs dos itens pedidos)
 * quantidade
 * setorDestino (código do setor | para almoxarifado)
 * funcionarioDestino (a matrícula do funcionário | para uniformes e EPI)
 * dataPedido
 * 
 * Métodos:
 * adicionarItem(int idItem, int quantidade)
 * removerItem(int idItem)
 * salvarNoBancoDados()
 * exportarPlanilha(String formato)
 */

import java.time.LocalDate;
import java.util.List;

public class Pedido {
    private int numeroPedido;
    private TipoPedido tipoPedido;
    private List<Integer> itens;
    private int quantidade;
    private int setorDestino;
    private int  funcionarioDestino;
    private LocalDate dataPedido;

    public Pedido(TipoPedido tipoPedido, List<Integer> itens, int quantidade, int setorDestino, int funcionarioDestino, LocalDate dataPedido) {
        this.tipoPedido = tipoPedido;
        this.itens = itens;
        this.quantidade = quantidade;
        this.setorDestino = setorDestino;
        this.funcionarioDestino = funcionarioDestino;
        this.dataPedido = dataPedido;
    }

    // Getters e Setters
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public TipoPedido getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(TipoPedido tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public List<Integer> getItens() {
        return itens;
    }

    public void setItens(List<Integer> itens) {
        this.itens = itens;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getSetorDestino() {
        return setorDestino;
    }

    public void setSetorDestino(int setorDestino) {
        this.setorDestino = setorDestino;
    }

    public int getFuncionarioDestino() {
        return funcionarioDestino;
    }

    public void setFuncionarioDestino(int funcionarioDestino) {
        this.funcionarioDestino = funcionarioDestino;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    // Métodos

    public void adicionarItem(int idItem, int quantidade) {
        this.itens.add(idItem);
        this.quantidade += quantidade;
    }

    public void removerItem(int item) {
        this.itens.remove(item);
    }

    public void salvarNoBancoDados() {
        // Salva o pedido no banco de dados
    }

    public void exportarPlanilha(String formato) {
        // Exporta o pedido em formato de planilha
    }

}
