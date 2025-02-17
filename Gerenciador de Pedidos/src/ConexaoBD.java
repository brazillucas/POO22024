/*
 * Essa classe será responsável por:
 * 
 * Estabelecer a conexão com o banco de dados.
 * Executar consultas SQL (INSERT, SELECT, UPDATE, DELETE).
 * Fechar a conexão após as operações.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:sqlite:src\\Data\\GerenciadorPedidos.db"; // Caminho para o arquivo SQLite

    // Método para obter uma conexão com o banco de dados
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Método para fechar a conexão
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    // Método para executar consultas que não retornam resultados (INSERT, UPDATE, DELETE)
    public static void executarUpdate(String sql, Object... parametros) {
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = conectar();
            stmt = conexao.prepareStatement(sql);

            // Substituir os parâmetros na consulta
            for (int i = 0; i < parametros.length; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta: " + e.getMessage());
        } finally {
            fecharConexao(conexao);
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar o statement: " + e.getMessage());
                }
            }
        }
    }

    // Método para executar consultas que retornam resultados (SELECT)
    public static ResultSet executarQuery(String sql, Object... parametros) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet resultado = null;

        try {
            conexao = conectar();
            stmt = conexao.prepareStatement(sql);

            // Substituir os parâmetros na consulta
            for (int i = 0; i < parametros.length; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }

            resultado = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
            fecharConexao(conexao);
        }

        return resultado; // O ResultSet deve ser fechado pelo chamador
    }
}