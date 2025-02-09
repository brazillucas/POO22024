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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {
    
    // Salvar um pedido no banco de dados
    public static void salvarPedido(Pedido pedido) {
        String sql = "INSERT INTO Pedidos (tipo_pedido, data_pedido) VALUES (?, ?)";
        ConexaoBD.executarUpdate(sql, pedido.getTipoPedido().toString(), pedido.getDataPedido());

        // Recuperar o ID do pedido recém-criado
        int numeroPedido = obterUltimoNumeroPedido();

        // Salvar os itens do pedido
        for (ItemPedido itemPedido : pedido.getItensPedido()) {
            sql = "INSERT INTO Itens_Pedido (pedido_id, item_id, quantidade, setor_id, funcionario_matricula) VALUES (?, ?, ?, ?, ?)";
            ConexaoBD.executarUpdate(sql,
                    numeroPedido,
                    itemPedido.getItemId(),
                    itemPedido.getQuantidade(),
                    itemPedido.getSetorDestino() != 0 ? itemPedido.getSetorDestino() : 0, // Usar 0 para "nulo"
                    itemPedido.getFuncionarioDestino() != 0 ? itemPedido.getFuncionarioDestino() : 0); // Usar 0 para "nulo"
        }
    }

  // Buscar o último número de pedido inserido
    private static int obterUltimoNumeroPedido() {
        String sql = "SELECT MAX(numero_pedido) AS ultimo_pedido FROM Pedidos";
        ResultSet resultado = ConexaoBD.executarQuery(sql);

        try {
            if (resultado.next()) {
                return resultado.getInt("ultimo_pedido");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter o último número de pedido: " + e.getMessage());
        }

        return -1; // Retorna -1 em caso de erro
    }

    public void atualizarItem(Item item) {
        if (item instanceof Uniforme uniforme) {
            String sql = "UPDATE Itens SET nome = ?, tipo = ?, tamanho = ?, setor_id = ? WHERE id = ?";
            ConexaoBD.executarUpdate(sql, uniforme.getNome(), uniforme.getTipo(), uniforme.getTamanho(), uniforme.getSetorDestino(), uniforme.getCodigo());
        } else if (item instanceof EPI epi) {
            String sql = "UPDATE Itens SET nome = ?, tipo = ?, tamanho = ?, ca = ? WHERE id = ?";
            ConexaoBD.executarUpdate(sql, epi.getNome(), epi.getTipo(), epi.getTamanho(), epi.getCa(), epi.getCodigo());
        } else {
            String sql = "UPDATE Itens SET nome = ?, tipo = ?, tamanho = ? WHERE id = ?";
            ConexaoBD.executarUpdate(sql, item.getNome(), item.getTipo(), null, item.getCodigo());
        }
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

    // Buscar um item pelo ID
    public static Item buscarItemPorId(int itemId) {
        String sql = "SELECT * FROM Itens WHERE id = ?";
        ResultSet resultado = ConexaoBD.executarQuery(sql, itemId);

        try {
            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String tipo = resultado.getString("tipo");
                String tamanho = resultado.getString("tamanho");
                String ca = resultado.getString("ca");

                // Criar o objeto Item com base no tipo
                if ("Uniforme".equalsIgnoreCase(tipo)) {
                    int setorDestino = resultado.getInt("setor_id"); // Obter setor_id da consulta
                    return new Uniforme(itemId, nome, setorDestino, tamanho); // Passar o setor destino
                } else if ("EPI".equalsIgnoreCase(tipo)) {

                    return new EPI(itemId, nome, tamanho, ca);
                } else if ("Almoxarifado".equalsIgnoreCase(tipo)) {
                    return new Almoxarifado(itemId, nome);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o item: " + e.getMessage());
        }

        return null; // Retorna null se o item não for encontrado
    }
}
