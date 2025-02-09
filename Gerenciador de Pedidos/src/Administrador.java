/*
 * Administrador
 * Herança: A classe Administrador herda de Funcionario, pois o administrador também é um funcionário.
 * Atributos adicionais:
 * senha (inicialmente definida como os 4 últimos dígitos da matrícula)
 * alterouSenha (booleano para verificar se a senha foi alterada)
 * 
 * Métodos:
 * cadastrarItem(Item item)
 * Permite o cadastro de novos itens no sistema.
 * cadastrarSetor(Setor setor)
 * Permite o cadastro de novos setores no sistema.
 * cadastrarFuncionario(Funcionario funcionario)
 * Permite o cadastro de novos funcionários no sistema.
 * realizarPedido(Pedido pedido)
 * Permite a criação e registro de novos pedidos no sistema.
 * consultarPedidos(Filtro filtro)
 * Permite consultar pedidos com base em filtros específicos (setor, período, funcionário ou número do pedido).
 * alterarSenha(String novaSenha)
 * Permite ao administrador alterar sua senha padrão.
 */

import java.time.LocalDate;
import java.util.List;

public class Administrador extends Funcionario {

    private String senha;
    private boolean alterouSenha;

    public Administrador(int matricula, String nome, int setor,
                        FuncaoFuncionario funcao, LocalDate dataAdmissao,
                        int lojaTrabalho, String tamanhoUniforme, List<Integer> pedidos, String senha, boolean alterouSenha) {
        super(matricula, nome, setor, funcao, dataAdmissao, lojaTrabalho, tamanhoUniforme, pedidos);
        this.senha = senha;
        this.alterouSenha = alterouSenha;
    }

    public Administrador(int matricula, String nome, int setor,
                        FuncaoFuncionario funcao, LocalDate dataAdmissao,
                        int lojaTrabalho, String tamanhoUniforme,
                        List<Integer> pedidos) {
        super(matricula, nome, setor, funcao, dataAdmissao, lojaTrabalho, tamanhoUniforme, pedidos);
        this.senha = String.valueOf(matricula).substring(4);
        this.alterouSenha = false;
    }

    // Getters e Setters
    public String getSenha() {
        return senha;
    }

    public boolean isAlterouSenha() {
        return alterouSenha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
        this.alterouSenha = true;
    }

    // Métodos
    public void cadastrarItem(Item item) {
        // Implementação do método
        BancoDeDados.cadastrarItem(item);
    }

    public void cadastrarSetor(Setor setor) {
        // Implementação do método
        
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        // Implementação do método
    }

    public void realizarPedido(Pedido pedido) {
        // Implementação do método
    }

    public void consultarPedidos(Filtro filtro) {
        // Implementação do método
    }

    public void alterarSenha(String novaSenha) {
        // Implementação do método
    }

}
