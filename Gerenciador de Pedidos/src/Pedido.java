/*
 * Atributos:
 * numeroPedido (gerado automaticamente)
 * tipoPedido (Enum | Uniforme, EPI, Almoxarifado)
 * itens (lista de ItemPedido)
 * quantidade
 * setorDestino (código do setor | para almoxarifado)
 * funcionarioDestino (a matrícula do funcionário | para uniformes e EPI)
 * dataPedido
 * 
 * Métodos:
 * adicionarItem(ItemPedido itemPedido)
 * removerItem(int itemId)
 * salvarNoBancoDados()
 * exportarPlanilha(String formato)
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numeroPedido;
    private TipoPedido tipoPedido;
    private List<ItemPedido> itensPedido;
    private LocalDate dataPedido;

    // Construtores
    public Pedido(int numeroPedido, TipoPedido tipoPedido) {
        this.numeroPedido = numeroPedido;
        this.tipoPedido = tipoPedido;
        this.itensPedido = new ArrayList<>();
        this.dataPedido = LocalDate.now();
    }

    public Pedido(int numeroPedido, TipoPedido tipoPedido, LocalDate dataPedido) {
        this.numeroPedido = numeroPedido;
        this.tipoPedido = tipoPedido;
        this.itensPedido = new ArrayList<>();
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

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItens(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    // Métodos

    // Adicionar item ao pedido
    public void adicionarItem(int itemId, int quantidade, int numeroPedido, int setorDestino, int funcionarioDestino) {
        ItemPedido itemPedido = new ItemPedido(itemId, quantidade, numeroPedido, setorDestino, funcionarioDestino);
        itensPedido.add(itemPedido);
    }

    // Adiciona uma relação de itens ao pedido
    public void adicionarItemPedido(ItemPedido itemPedido) {
        itensPedido.add(itemPedido);
    }

    // Remover item do pedido
    public void removerItem(int itemId) {
        itensPedido.removeIf(ip -> ip.getItemId() == itemId);
    }

    // Atualizar itemPedido
    public void atualizarItemPedido(ItemPedido itemPedido) {
        for (ItemPedido item : itensPedido) {
            if (item.getItemId() == itemPedido.getItemId() && item.getNumeroPedido() == itemPedido.getNumeroPedido() && item.getSetorDestino() == itemPedido.getSetorDestino() && item.getFuncionarioDestino() == itemPedido.getFuncionarioDestino()) {
                item.setQuantidade(itemPedido.getQuantidade());
                item.setSetorDestino(itemPedido.getSetorDestino());
                item.setFuncionarioDestino(itemPedido.getFuncionarioDestino());
            }
        }
    }

    // Remover itemPedido
    public void removerItemPedido(ItemPedido itemPedido) {
        itensPedido.remove(itemPedido);
    }

    // Recuperar detalhes completos dos itens do pedido
    public List<Item> getDetalhesItensPedido() {
        BancoDeDados bancoDados = new BancoDeDados();
        List<Item> detalhesItens = new ArrayList<>();
        for (ItemPedido itemPedido : itensPedido) {
            Item item = bancoDados.buscarItemPorId(itemPedido.getItemId());
            if (item != null) {
                detalhesItens.add(item);
            }
        }
        return detalhesItens;
    }

    // Exibir detalhes do pedido
    public void exibirDetalhesPedido() {
        System.out.printf("Nº Pedido: %d | Tipo: %s | Data: %s%n", 
                        this.getNumeroPedido(), 
                        this.getTipoPedido(), 
                        this.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    // Exportar pedido para planilha
    public void exportarPlanilha(String caminhoArquivo, int numeroLoja) {
        if (null != tipoPedido) switch (tipoPedido) {
            case UNIFORME:
                // PlanilhaHandler.exportarUniformes(this.itensPedido, caminhoArquivo, numeroLoja);
                break;
            case EPI:
                // PlanilhaHandler.exportarEPI(this.itensPedido, caminhoArquivo, numeroLoja);
                break;
            case ALMOXARIFADO:
                // PlanilhaHandler.exportarAlmoxarifado(this, caminhoArquivo, numeroLoja);
                break;
            default:
                break;
        }
    }

}
