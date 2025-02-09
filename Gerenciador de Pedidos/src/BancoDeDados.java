/*
 * BancoDeDados
 * Métodos:
 * salvarPedido(Pedido pedido)
 * buscarPedidos(Filtro filtro)
 * atualizarItem(Item item)
 * carregarItens()
 * carregarSetores()
 * carregarFuncionarios()
 */

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {
    
    public static void salvarPedido(Pedido pedido) {
        // Salva o pedido no banco de dados
    }

    public List<Pedido> buscarPedidos(Filtro filtro) {
        // Busca os pedidos no banco de dados
        return new ArrayList<>();
    }

    public void atualizarItem(Item item) {
        // Atualiza o item no banco de dados
    }

    public List<Item> carregarItens() {
        // Carrega os itens do banco de dados
        return new ArrayList<>();
    }

    public List<Setor> carregarSetores() {
        // Carrega os setores do banco de dados
        return new ArrayList<>();
    }

    public List<Funcionario> carregarFuncionarios() {
        // Carrega os funcionários do banco de dados
        return new ArrayList<>();
    }

    public static Item buscarItemPorId(int itemId) {
        throw new UnsupportedOperationException("Unimplemented method 'buscarItemPorId'");
    }
}
