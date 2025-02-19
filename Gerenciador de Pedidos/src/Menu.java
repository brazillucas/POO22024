/*
 * Classe responsável por exibir os menus do sistema.
 * Esta classe irá conter métodos para mostrar diferentes opções de menu disponíveis.
 * 
 * Métodos:
 * exibirMenuPrincipal(): Exibe o menu principal do sistema.
 * exibirMenuFuncionario(): Exibe o menu de funcionário.
 * exibirMenuAdministrador(): Exibe o menu de administrador.
 * exibirMenuSetor(): Exibe o menu de setor.
 * exibirMenuFuncao(): Exibe o menu de função.
 * exibirMenuPedido(): Exibe o menu de pedido.
 * exibirMenuItem(): Exibe o menu de item.
 * exibirMenuRelatorio(): Exibe o menu de relatório.
 * exibirMenuConfiguracoes(): Exibe o menu de configurações.
 * exibirMenuSair(): Exibe o menu de saída.
 * 
 * exibirMenuFuncionario(Funcionario funcionario): Exibe o menu de funcionário com as opções específicas para o funcionário logado.
 * exibirMenuSetor(Setor setor): Exibe o menu de setor com as opções específicas para o setor selecionado.
 * exibirMenuFuncao(FuncaoFuncionario funcao): Exibe o menu de função com as opções específicas para a função selecionada.
 * exibirMenuPedido(Pedido pedido): Exibe o menu de pedido com as opções específicas para o pedido selecionado.
 * exibirMenuItem(Item item): Exibe o menu de item com as opções específicas para o item selecionado.
 */
public class Menu {

    public void exibirMenuIncial() {
        System.out.println("Menu Inicial");
        System.out.println("1. Acessar sistema");
        System.out.println("2. Sair");
    }

    public void exibirMenuPrincipal() {
        System.out.println("Menu Principal");
        System.out.println("1. Menu Item");
        System.out.println("2. Menu Setor");
        System.out.println("3. Menu Funcionário");
        System.out.println("4. Menu Pedidos");
        System.out.println("5. Alterar Senha");
        System.out.println("6. Logout");
    }

    public void exibirMenuSetor() {
        System.out.println("Menu Setor");
        System.out.println("1. Cadastrar Novo Setor");
        System.out.println("2. Listar Setores");
        System.out.println("3. Voltar");
    }

    public void exibirMenuFuncao() {
        System.out.println("Menu Função");
        System.out.println("1. Cadastrar Funcionário");
        System.out.println("2. Consultar Pedidos");
        System.out.println("3. Voltar");
    }

    public void exibirMenuPedido() {
        System.out.println("Menu Pedido");
        System.out.println("1. Realizar Pedido");
        System.out.println("2. Listar Pedidos");
        System.out.println("3. Atualizar Pedido");
        System.out.println("4. Voltar");
    }

    public void exibirRealizarPedido() {
        System.out.println("Realizar Pedido");
        System.out.println("1. Pedido de Almoxarifado");
        System.out.println("2. Pedido de EPI");
        System.out.println("3. Pedido de Uniforme");
        System.out.println("4. Voltar");
    }

    public void exibirMenuItem() {
        System.out.println("Menu Item");
        System.out.println("1. Consultar Itens");
        System.out.println("2. Importar itens");
        System.out.println("3. Voltar");
    }

    public void exibirConsultaItens() {
        System.out.println("Consulta de Itens");
        System.out.println("1. Consultar todos os itens");
        System.out.println("2. Consultar itens por setor");
        System.out.println("3. Consultar itens por id");
        System.out.println("4. Voltar");
    }

    public void exibirConsultaPedidos() {
        System.out.println("Consulta de Pedidos");
        System.out.println("1. Consultar todos os pedidos");
        System.out.println("2. Consultar pedidos por ID");
        System.out.println("3. Consultar pedidos por período");
        System.out.println("4. Consultar pedidos por setor");
        System.out.println("5. Consultar pedidos por funcionário");
        System.out.println("6. Voltar");
    }

    public void exibirMenuConfiguracoes() {
        System.out.println("Menu Configurações");
        System.out.println("1. Alterar Senha");
        System.out.println("2. Voltar");
    }

    public void exibirMenuSair() {
        System.out.println("Deseja realmente sair?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
    }

    public void exibirMenuFuncionario() {
        System.out.println("Menu Funcionário");
        System.out.println("1. Cadastrar Funcionário");
        System.out.println("2. Listar Pedido de Funcionário");
        System.out.println("3. Atualizar Dados");
        System.out.println("4. Voltar");
    }

    public void exibirMenuFuncionario(Funcionario funcionario) {
        System.out.println("Menu Funcionário - " + funcionario.getNome());
        System.out.println("1. Atualizar nome");
        System.out.println("2. Atualizar tamanho uniforme");
        System.out.println("3. Atualizar setor");
        System.out.println("4. Atualizar função");
        System.out.println("5. Excluir funcionário");
        System.out.println("6. Voltar");
    }

    public void exibirMenuPedido(Pedido pedido) {
        System.out.println("Menu Pedido - " + pedido.getNumeroPedido() + " - " + pedido.getTipoPedido());
        System.out.println("1. Adicionar Item");
        System.out.println("2. Remover Item");
        System.out.println("3. Alterar Quantidade");
        System.out.println("4. Voltar");
    }
}
