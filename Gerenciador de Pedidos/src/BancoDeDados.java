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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {
    
    // Salvar um pedido no banco de dados
    public void salvarPedido(Pedido pedido) {
        String sql = "INSERT INTO Pedidos (tipo_pedido, data_pedido) VALUES (?, ?)";
        ConexaoBD.executarUpdate(sql, pedido.getTipoPedido().toString(), pedido.getDataPedido());

        // Recuperar o ID do pedido recém-criado
        int numeroPedido = obterUltimoNumeroPedido();

        if (numeroPedido == -1) {
            System.err.println("Erro ao salvar pedido: não foi possível obter o número do pedido.");
            return;
        }

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
    private int obterUltimoNumeroPedido() {
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

    // Atualizar um item no banco de dados
    public void atualizarItem(Item item) {
        if (item instanceof Uniforme uniforme) {
            String sql = "UPDATE Itens SET nome = ?, tipo = ?, tamanho = ?, setor_id = ? WHERE id = ?";
            ConexaoBD.executarUpdate(sql, uniforme.getNome(), uniforme.getTipo(), uniforme.getTamanho(), uniforme.getSetorDestino(), uniforme.getCodigo());
        } else if (item instanceof EPI epi) {
            String sql = "UPDATE Itens SET nome = ?, tipo = ?, tamanho = ?, ca = ? WHERE id = ?";
            ConexaoBD.executarUpdate(sql, epi.getNome(), epi.getTipo(), epi.getTamanho(), epi.getCa(), epi.getCodigo());
        } else if (item instanceof Almoxarifado almoxarifado) {
            String sql = "UPDATE Itens SET nome = ?, tipo = ?, tamanho = ? WHERE id = ?";
            ConexaoBD.executarUpdate(sql, almoxarifado.getNome(), almoxarifado.getTipo(), null, almoxarifado.getCodigo());
        }
    }
    
    // Cadastrar um item no banco de dados
    public void cadastrarItem(Item item) {
        String sql = "INSERT INTO Itens (nome, tipo, tamanho, setor_id, ca) VALUES (?, ?, ?, ?, ?)";
        if(item instanceof Uniforme uniforme) {
            ConexaoBD.executarUpdate(sql, uniforme.getNome(), uniforme.getTipo(), uniforme.getTamanho(), uniforme.getSetorDestino(), null);
        } else if(item instanceof EPI epi) {
            ConexaoBD.executarUpdate(sql, epi.getNome(), epi.getTipo(), epi.getTamanho(), epi.getSetorDestino(), epi.getCa());
        } else if(item instanceof Almoxarifado almoxarifado) {
            ConexaoBD.executarUpdate(sql, almoxarifado.getNome(), almoxarifado.getTipo(), null, null);
        }

    }

    // Carrega os itens do banco de dados
    public List<Item> carregarItens() {
        String sql = "SELECT id, nome, tipo, tamanho, ca, setor_id FROM Itens"; // Seleciona apenas as colunas necessárias
        ResultSet resultado = ConexaoBD.executarQuery(sql);
        List<Item> itens = new ArrayList<>();
        
        try {
            while (resultado.next()) {
                String tipo = resultado.getString("tipo");
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String tamanho = resultado.getString("tamanho");
                
                // Verificar valores nulos
                String ca = resultado.getObject("ca") != null ? resultado.getString("ca") : null;
                int setorId = resultado.getObject("setor_id") != null ? resultado.getInt("setor_id") : 0;
    
                // Criar o objeto Item com base no tipo
                if ("Uniforme".equalsIgnoreCase(tipo)) {
                    itens.add(new Uniforme(id, nome, setorId, tamanho)); // Passar o setorId corretamente
                } else if ("EPI".equalsIgnoreCase(tipo)) {
                    itens.add(new EPI(id, nome, setorId, tamanho, ca));
                } else if ("Almoxarifado".equalsIgnoreCase(tipo)) {
                    itens.add(new Almoxarifado(id, nome));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar os itens: " + e.getMessage());
        }
        
        return itens;
    }

    // Cadastrar um setor no banco de dados
    public void cadastrarSetor(Setor setor) {
        String sql = "INSERT INTO Setores (id, nome) VALUES (?, ?)";
        ConexaoBD.executarUpdate(sql, setor.getId(), setor.getNome());
    }
    
    // Carregar setores do banco de dados
    public List<Setor> carregarSetores() {
        String sql = "SELECT * FROM Setores";
        ResultSet resultado = ConexaoBD.executarQuery(sql);

        List<Setor> setores = new ArrayList<>();

        try {
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");

                Setor setor = new Setor(id, nome);
                setores.add(setor);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar os setores: " + e.getMessage());
        }

        return setores;
    }

    // Cadastrar uma função no banco de dados
    public void cadastrarFuncao(Funcoes funcao) {
        String sql = "INSERT INTO Funcoes (id, nome) VALUES (?, ?)";
        ConexaoBD.executarUpdate(sql, funcao.getCodigo(), funcao.getNome());
    }

    // Carregar funções do banco de dados
    public List<Funcoes> carregarFuncoes() {
        String sql = "SELECT * FROM Funcoes";
        ResultSet resultado = ConexaoBD.executarQuery(sql);

        List<Funcoes> funcoes = new ArrayList<>();

        try {
            while (resultado.next()) {
                int funcaoId = resultado.getInt("id");
                String nome = resultado.getString("nomeFuncao");
                Funcoes funcao = new Funcoes(funcaoId, nome);
                funcoes.add(funcao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar as funções: " + e.getMessage());
        }

        return funcoes;
    }

    // Cadastrar um novo funcionário no banco de dados
    public void cadastrarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO Funcionarios (matricula, nome, setor_id, funcao, data_admissao, loja_trabalho, tamanho_uniforme) VALUES (?, ?, ?, ?, ?, ?, ?)";
        ConexaoBD.executarUpdate(sql, funcionario.getMatricula(), funcionario.getNome(), funcionario.getSetor(), funcionario.getFuncao(), funcionario.getDataAdmissao(), funcionario.getLojaTrabalho(), funcionario.getTamanhoUniforme());
    }

    // Atualizar um funcionário no banco de dados
    public void atualizarFuncionario(Funcionario funcionario) {
        String sql = "UPDATE Funcionarios SET nome = ?, setor_id = ?, funcao = ?, data_admissao = ?, loja_trabalho = ?, tamanho_uniforme = ? WHERE matricula = ?";
        ConexaoBD.executarUpdate(sql, funcionario.getNome(), funcionario.getSetor(), funcionario.getFuncao(), funcionario.getDataAdmissao(), funcionario.getLojaTrabalho(), funcionario.getTamanhoUniforme(), funcionario.getMatricula());
    }

    // Excluir um funcionário do banco de dados
    public void excluirFuncionario(int matricula) {
        String sql = "UPDATE Funcionarios SET ativo = 0 WHERE matricula = ?";
        ConexaoBD.executarUpdate(sql, matricula);
    }

    // Carrega os funcionários do banco de dados
    public List<Funcionario> carregarFuncionarios() {
        String sql = "SELECT * FROM Funcionarios WHERE ativo = 1";
        ResultSet resultado = ConexaoBD.executarQuery(sql);

        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            while (resultado.next()) {
                int matricula = resultado.getInt("matricula");
                String nome = resultado.getString("nome");
                int setor = resultado.getInt("setor_id");
                int funcao = resultado.getInt("funcao");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataAdmissao = LocalDate.parse(resultado.getString("data_admissao"), formatter);
                int lojaTrabalho = resultado.getInt("loja_trabalho");
                String tamanhoUniforme = resultado.getString("tamanho_uniforme");

                Funcionario funcionario = new Funcionario(matricula, nome, setor, funcao, dataAdmissao, lojaTrabalho, tamanhoUniforme);
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar os funcionários: " + e.getMessage());
        }

        return funcionarios;
    }

    // Carregar administradores do banco de dados
    public List<Administrador> carregarAdministradores() {
        String sql = "SELECT * FROM Administradores";
        ResultSet resultado = null;
        Connection conexao = null;

        List<Administrador> administradores = new ArrayList<>();

        try {
            // Obter a conexão e executar a query
            conexao = ConexaoBD.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            resultado = stmt.executeQuery();

            if (resultado == null || !resultado.isBeforeFirst()) {
                System.err.println("Nenhum administrador encontrado na tabela Administradores.");
                return administradores;
            }

            while (resultado.next()) {
                int matricula = resultado.getInt("matricula");
                String senha = resultado.getString("senha_hash");

                // Buscar informações adicionais do funcionário
                String sqlFuncionario = "SELECT * FROM Funcionarios WHERE matricula = ?";
                ResultSet resultadoFuncionario = null;

                try (PreparedStatement stmtFuncionario = conexao.prepareStatement(sqlFuncionario)) {
                    stmtFuncionario.setInt(1, matricula);
                    resultadoFuncionario = stmtFuncionario.executeQuery();

                    while (resultadoFuncionario.next()) {
                        String nome = resultadoFuncionario.getString("nome");
                        int setor = resultadoFuncionario.getInt("setor_id");
                        int funcao = resultadoFuncionario.getInt("funcao");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dataAdmissao = LocalDate.parse(resultadoFuncionario.getString("data_admissao"), formatter);
                        int lojaTrabalho = resultadoFuncionario.getInt("loja_trabalho");
                        String tamanhoUniforme = resultadoFuncionario.getString("tamanho_uniforme");

                        // Buscar pedidos associados ao funcionário
                        List<Integer> pedidosLista = new ArrayList<>();
                        String sqlPedidos = "SELECT numero_pedido FROM Itens_Pedido WHERE funcionario_matricula = ?";
                        ResultSet resultadoPedidos = null;

                        try (PreparedStatement stmtPedidos = conexao.prepareStatement(sqlPedidos)) {
                            stmtPedidos.setInt(1, matricula);
                            resultadoPedidos = stmtPedidos.executeQuery();

                            while (resultadoPedidos.next()) {
                                pedidosLista.add(resultadoPedidos.getInt("numero_pedido"));
                            }
                        } finally {
                            if (resultadoPedidos != null) {
                                resultadoPedidos.close();
                            }
                        }

                        // Criar o objeto Administrador
                        Administrador admin = new Administrador(
                            matricula, nome, setor, funcao, dataAdmissao, lojaTrabalho, tamanhoUniforme, pedidosLista, senha, true
                        );
                        administradores.add(admin);
                    }
                } finally {
                    if (resultadoFuncionario != null) {
                        resultadoFuncionario.close();
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar os administradores: " + e.getMessage());
        } finally {
            // Fechar a conexão e o ResultSet principal
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar o ResultSet: " + e.getMessage());
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }

        return administradores;
    }

    // Atualizar senha de um administrador
    public void atualizarSenhaAdministrador(Administrador admin) {
        String sql = "UPDATE Administradores SET senha_hash = ? WHERE matricula = ?";
        ConexaoBD.executarUpdate(sql, admin.getSenha(), admin.getMatricula());
    }

    // Carregar funcionário por matrícula
    public Funcionario buscarFuncionarioPorMatricula(int matricula) {
        String sql = "SELECT * FROM Funcionarios WHERE matricula = ?";
        ResultSet resultado = ConexaoBD.executarQuery(sql, matricula);

        try {
            if (resultado.next()) {
                String nome = resultado.getString("nome");
                int setor = resultado.getInt("setor_id");
                int funcao = resultado.getInt("funcao");
                LocalDate dataAdmissao = resultado.getDate("data_admissao").toLocalDate();
                int lojaTrabalho = resultado.getInt("loja_trabalho");
                String tamanhoUniforme = resultado.getString("tamanho_uniforme");

                return new Funcionario(matricula, nome, setor, funcao, dataAdmissao, lojaTrabalho, tamanhoUniforme);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar o funcionário: " + e.getMessage());
        }

        return null; // Retorna null se o funcionário não for encontrado
    }

    // Cadastrar ItemPedido
    public void cadastrarItemPedido(ItemPedido itemPedido) {
        String sql = "INSERT INTO Itens_Pedido (pedido_id, item_id, quantidade, setor_id, funcionario_matricula) VALUES (?, ?, ?, ?, ?)";
        ConexaoBD.executarUpdate(sql, itemPedido.getNumeroPedido(), itemPedido.getItemId(), itemPedido.getQuantidade(), itemPedido.getSetorDestino(), itemPedido.getFuncionarioDestino());
    }

    // Atualizar ItemPedido
    public void atualizarItemPedido(ItemPedido itemPedido) {
        String sql = "UPDATE Itens_Pedido SET quantidade = ?, setor_id = ?, funcionario_matricula = ? WHERE pedido_id = ? AND item_id = ?";
        ConexaoBD.executarUpdate(sql, itemPedido.getQuantidade(), itemPedido.getSetorDestino(), itemPedido.getFuncionarioDestino(), itemPedido.getNumeroPedido(), itemPedido.getItemId());
    }

    // Excluir ItemPedido
    public void excluirItemPedido(ItemPedido itemPedido) {
        String sql = "DELETE FROM Itens_Pedido WHERE pedido_id = ? AND item_id = ? AND setor_id = ? AND funcionario_matricula = ?";
        ConexaoBD.executarUpdate(sql, itemPedido.getNumeroPedido(), itemPedido.getItemId(), itemPedido.getSetorDestino(), itemPedido.getFuncionarioDestino());
    }

    // Buscar um item pelo ID
    public Item buscarItemPorId(int itemId) {
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

    // Buscar um pedido pelo número
    public Pedido buscarPedidoPorNumero(int numeroPedido) {
        // Buscar os dados do pedido
        // Seleciona os dados do pedido, procura pelos dados dos itens a partir da relação Itens_Pedido
        String sql = "SELECT numero_pedido, tipo_pedido, data_pedido FROM Pedidos WHERE numero_pedido = ?";

        ResultSet resultado = ConexaoBD.executarQuery(sql, numeroPedido);

        try {
            if (resultado.next()) {
                String tipoPedido = resultado.getString("tipo_pedido");
                LocalDate dataPedido = resultado.getDate("data_pedido").toLocalDate();

                Pedido pedido = new Pedido(numeroPedido, TipoPedido.valueOf(tipoPedido), dataPedido);

                // Buscar os itens do pedido
                sql = "SELECT item_id, quantidade, setor_id, funcionario_matricula FROM Itens_Pedido WHERE numero_pedido = ?";
                ResultSet itensResultado = ConexaoBD.executarQuery(sql, numeroPedido);

                while (itensResultado.next()) {
                    int itemId = itensResultado.getInt("item_id");
                    int quantidade = itensResultado.getInt("quantidade");
                    int numero_pedido = itensResultado.getInt("numero_pedido");
                    int setorId = itensResultado.getInt("setor_id");
                    int funcionarioMatricula = itensResultado.getInt("funcionario_matricula");

                    pedido.adicionarItem(itemId, quantidade, numero_pedido, setorId, funcionarioMatricula);
                }

                return pedido;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar o pedido: " + e.getMessage());
        }

        return null; // Retorna null se o pedido não for encontrado
    }

    // Listar todos os pedidos
    public List<Pedido> listarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT numero_pedido, tipo_pedido, data_pedido FROM Pedidos";
        ResultSet resultado = ConexaoBD.executarQuery(sql);

        try {
            while (resultado.next()) {
                int numeroPedido = resultado.getInt("numero_pedido");
                String tipoPedido = resultado.getString("tipo_pedido");
                LocalDate dataPedido = resultado.getDate("data_pedido").toLocalDate();

                // Criar o objeto Pedido
                Pedido pedido = new Pedido(numeroPedido, TipoPedido.valueOf(tipoPedido), dataPedido);

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    // Listar pedidos por número do pedido
    public List<Pedido> listarPedidosPorNumero(int numeroPedido) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT numero_pedido, tipo_pedido, data_pedido FROM Pedidos WHERE numero_pedido = ?";
        ResultSet resultado = ConexaoBD.executarQuery(sql, numeroPedido);

        try {
            while (resultado.next()) {
                int numero = resultado.getInt("numero_pedido");
                String tipo = resultado.getString("tipo_pedido");
                LocalDate dataPedido = resultado.getDate("data_pedido").toLocalDate();

                // Criar o objeto Pedido
                Pedido pedido = new Pedido(numero, TipoPedido.valueOf(tipo), dataPedido);

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    // Listar pedidos por matrícula do funcionário
    public List<Pedido> listarPedidosPorFuncionario(int matriculaFuncionario) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT p.numero_pedido, p.tipo_pedido, p.data_pedido " +
                    "FROM Pedidos p " +
                    "JOIN Itens_Pedido ip ON p.numero_pedido = ip.numero_pedido " +
                    "WHERE ip.funcionario_matricula = ?";
        ResultSet resultado = ConexaoBD.executarQuery(sql, matriculaFuncionario);

        try {
            while (resultado.next()) {
                int numeroPedido = resultado.getInt("numero_pedido");
                String tipoPedido = resultado.getString("tipo_pedido");
                LocalDate dataPedido = resultado.getDate("data_pedido").toLocalDate();

                // Criar o objeto Pedido
                Pedido pedido = new Pedido(numeroPedido, TipoPedido.valueOf(tipoPedido), dataPedido);

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    // Listar pedidos por setor
    public List<Pedido> listarPedidosPorSetor(int idSetor) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT p.numero_pedido, p.tipo_pedido, p.data_pedido " +
                    "FROM Pedidos p " +
                    "JOIN Itens_Pedido ip ON p.numero_pedido = ip.numero_pedido " +
                    "WHERE ip.setor_id = ?";
        ResultSet resultado = ConexaoBD.executarQuery(sql, idSetor);

        try {
            while (resultado.next()) {
                int numeroPedido = resultado.getInt("numero_pedido");
                String tipoPedido = resultado.getString("tipo_pedido");
                LocalDate dataPedido = resultado.getDate("data_pedido").toLocalDate();

                // Criar o objeto Pedido
                Pedido pedido = new Pedido(numeroPedido, TipoPedido.valueOf(tipoPedido), dataPedido);

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    // Listar pedidos por período
    public List<Pedido> listarPedidosPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT numero_pedido, tipo_pedido, data_pedido " +
                    "FROM Pedidos " +
                    "WHERE data_pedido BETWEEN ? AND ?";
        ResultSet resultado = ConexaoBD.executarQuery(sql, dataInicio, dataFim);

        try {
            while (resultado.next()) {
                int numeroPedido = resultado.getInt("numero_pedido");
                String tipoPedido = resultado.getString("tipo_pedido");
                LocalDate dataPedido = resultado.getDate("data_pedido").toLocalDate();

                // Criar o objeto Pedido
                Pedido pedido = new Pedido(numeroPedido, TipoPedido.valueOf(tipoPedido), dataPedido);

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    // Listar pedidos por tipo
    public List<Pedido> listarPedidosPorTipo(TipoPedido tipoPedido) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT numero_pedido, tipo_pedido, data_pedido " +
                    "FROM Pedidos " +
                    "WHERE tipo_pedido = ?";
        ResultSet resultado = ConexaoBD.executarQuery(sql, tipoPedido.toString());

        try {
            while (resultado.next()) {
                int numeroPedido = resultado.getInt("numero_pedido");
                String tipo = resultado.getString("tipo_pedido");
                LocalDate dataPedido = resultado.getDate("data_pedido").toLocalDate();

                // Criar o objeto Pedido
                Pedido pedido = new Pedido(numeroPedido, TipoPedido.valueOf(tipo), dataPedido);

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    // Listar pedidos por tipo e setor
    public List<Pedido> listarPedidosPorTipoESetor(TipoPedido tipoPedido, int idSetor) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT p.numero_pedido, p.tipo_pedido, p.data_pedido " +
                    "FROM Pedidos p " +
                    "JOIN Itens_Pedido ip ON p.numero_pedido = ip.numero_pedido " +
                    "WHERE p.tipo_pedido = ? AND ip.setor_id = ?";
        ResultSet resultado = ConexaoBD.executarQuery(sql, tipoPedido.toString(), idSetor);

        try {
            while (resultado.next()) {
                int numeroPedido = resultado.getInt("numero_pedido");
                String tipo = resultado.getString("tipo_pedido");
                LocalDate dataPedido = resultado.getDate("data_pedido").toLocalDate();

                // Criar o objeto Pedido
                Pedido pedido = new Pedido(numeroPedido, TipoPedido.valueOf(tipo), dataPedido);

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    // Listar pedidos por tipo e período
    public List<Pedido> listarPedidosPorTipoEPeriodo(TipoPedido tipoPedido, LocalDate dataInicio, LocalDate dataFim) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT numero_pedido, tipo_pedido, data_pedido " +
                    "FROM Pedidos " +
                    "WHERE tipo_pedido = ? AND data_pedido BETWEEN ? AND ?";
        ResultSet resultado = ConexaoBD.executarQuery(sql, tipoPedido.toString(), dataInicio, dataFim);

        try {
            while (resultado.next()) {
                int numeroPedido = resultado.getInt("numero_pedido");
                String tipo = resultado.getString("tipo_pedido");
                LocalDate dataPedido = resultado.getDate("data_pedido").toLocalDate();

                // Criar o objeto Pedido
                Pedido pedido = new Pedido(numeroPedido, TipoPedido.valueOf(tipo), dataPedido);

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    // Listar pedidos por setor e período
    public List<Pedido> listarPedidosPorSetorEPeriodo(int idSetor, LocalDate dataInicio, LocalDate dataFim) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT p.numero_pedido, p.tipo_pedido, p.data_pedido " +
                    "FROM Pedidos p " +
                    "JOIN Itens_Pedido ip ON p.numero_pedido = ip.numero_pedido " +
                    "WHERE ip.setor_id = ? AND p.data_pedido BETWEEN ? AND ?";
        ResultSet resultado = ConexaoBD.executarQuery(sql, idSetor, dataInicio, dataFim);

        try {
            while (resultado.next()) {
                int numeroPedido = resultado.getInt("numero_pedido");
                String tipo = resultado.getString("tipo_pedido");
                LocalDate dataPedido = resultado.getDate("data_pedido").toLocalDate();

                // Criar o objeto Pedido
                Pedido pedido = new Pedido(numeroPedido, TipoPedido.valueOf(tipo), dataPedido);

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os pedidos: " + e.getMessage());
        }

        return pedidos;
    }

}
