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
                        int funcao, LocalDate dataAdmissao,
                        int lojaTrabalho, String tamanhoUniforme, List<Integer> pedidos, String senha, boolean alterouSenha) {
        super(matricula, nome, setor, funcao, dataAdmissao, lojaTrabalho, tamanhoUniforme, pedidos, true);
        this.senha = senha;
        this.alterouSenha = alterouSenha;
    }

    public Administrador(int matricula, String nome, int setor,
                        int funcao, LocalDate dataAdmissao,
                        int lojaTrabalho, String tamanhoUniforme,
                        List<Integer> pedidos) {
        super(matricula, nome, setor, funcao, dataAdmissao, lojaTrabalho, tamanhoUniforme, pedidos, true);
        this.setSenha(String.valueOf(matricula).substring(4));
        this.alterouSenha = false;
    }

    public Administrador(int matricula, String nome, int setor,
                        int funcao, LocalDate dataAdmissao,
                        int lojaTrabalho, String tamanhoUniforme) {
        super(matricula, nome, setor, funcao, dataAdmissao, lojaTrabalho, tamanhoUniforme);
        this.setSenha(String.valueOf(matricula).substring(4));
        this.alterouSenha = false;
    }

    // Getters e Setters
    public String getSenha() {
        return senha;
    }

    public boolean isAlterouSenha() {
        return alterouSenha;
    }

    public final void setSenha(String senha) {
        Criptografia criptografia = new Criptografia(senha, Criptografia.SHA256);
        this.senha = criptografia.criptografar();
        this.alterouSenha = true;
    }

    // Métodos
    public void cadastrarSetor(Setor setor) {
        BancoDeDados banco = new BancoDeDados();
        banco.cadastrarSetor(setor);
    }
    
    public void cadastrarFuncionario(Funcionario funcionario) {
        BancoDeDados bancoDeDados = new BancoDeDados();
        bancoDeDados.cadastrarFuncionario(funcionario);
    }

    public void salvarPedido(Pedido pedido, BancoDeDados bancoDeDados) {
        bancoDeDados.salvarPedido(pedido);
    }

    public void realizarPedido(Pedido pedido, BancoDeDados bancoDeDados) {
        bancoDeDados.salvarPedido(pedido);
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
        BancoDeDados bancoDados = new BancoDeDados();
        if (filtro.getTipoFiltro().equals("Setor") && filtro.getValorFiltro() instanceof Setor setor) {
            bancoDados.listarPedidosPorSetor(setor.getId());
        } else if (filtro.getTipoFiltro().equals("Período")) {
            bancoDados.listarPedidosPorPeriodo(filtro.getDataInicial(), filtro.getDataFinal());
            
        } else if (filtro.getTipoFiltro().equals("Funcionário") && filtro.getValorFiltro() instanceof Integer matricula) {
            bancoDados.listarPedidosPorFuncionario(matricula);
        } else if (filtro.getTipoFiltro().equals("Número do Pedido") && filtro.getValorFiltro() instanceof Integer numeroPedido) {
            bancoDados.listarPedidosPorNumero(numeroPedido);
        } else {
            bancoDados.listarPedidos();
        }
    }

    public void alterarSenha(String novaSenha) {
        this.senha = novaSenha;
        this.alterouSenha = true;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                ", Matrícula=" + getMatricula() +
                ", Nome='" + getNome() + '\'' +
                ", Setor=" + getSetor() +
                ", Função=" + getFuncao() +
                ", Admissao=" + getDataAdmissao() +
                ", Loja=" + getLojaTrabalho() +
                ", pedidos=" + getPedidos() +
                '}';
    }

}
