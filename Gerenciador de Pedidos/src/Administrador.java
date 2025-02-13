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

    // Construtores
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
        BancoDeDados.cadastrarItem(item);
    }

    public void cadastrarSetor(Setor setor) {
        BancoDeDados.cadastrarSetor(setor);
    }
    
    public void cadastrarFuncionario(Funcionario funcionario) {
        BancoDeDados.cadastrarFuncionario(funcionario);
    }

    public void salvarPedido(Pedido pedido) {
        BancoDeDados.salvarPedido(pedido);
    }

    public void consultarPedidos(Filtro<?> filtro) {
        /*
         * O método consulta:
         * - Pedidos por setor 1
         * - Pedidos por período 1
         * - Pedidos por funcionário 1
         * - Pedidos por número do pedido
         * - Todos os itens
         */
        if (filtro.getTipoFiltro().equals("Setor") && filtro.getValorFiltro() instanceof Setor setor) {
            BancoDeDados.listarPedidosPorSetor(setor.getId());
        } else if (filtro.getTipoFiltro().equals("Período")) {
            BancoDeDados.listarPedidosPorPeriodo(filtro.getDataInicial(), filtro.getDataFinal());
            
        } else if (filtro.getTipoFiltro().equals("Funcionário") && filtro.getValorFiltro() instanceof Integer matricula) {
            BancoDeDados.listarPedidosPorFuncionario(matricula);
        } else if (filtro.getTipoFiltro().equals("Número do Pedido") && filtro.getValorFiltro() instanceof Integer numeroPedido) {
            BancoDeDados.listarPedidosPorNumero(numeroPedido);
        } else {
            BancoDeDados.listarPedidos();
        }
    }

    public void alterarSenha(String novaSenha) {
        this.senha = novaSenha;
        this.alterouSenha = true;
    }

}
