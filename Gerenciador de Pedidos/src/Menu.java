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
 * exibirMenuAdministrador(Administrador administrador): Exibe o menu de administrador com as opções específicas para o administrador logado.
 * exibirMenuSetor(Setor setor): Exibe o menu de setor com as opções específicas para o setor selecionado.
 * exibirMenuFuncao(FuncaoFuncionario funcao): Exibe o menu de função com as opções específicas para a função selecionada.
 * exibirMenuPedido(Pedido pedido): Exibe o menu de pedido com as opções específicas para o pedido selecionado.
 * exibirMenuItem(Item item): Exibe o menu de item com as opções específicas para o item selecionado.
 */
public class Menu {

    public void exibirMenuIncial() {
        System.out.println("Menu Principal");
        System.out.println("1. Acessar sistema");
        System.out.println("2. Sair");
    }

    public void exibirMenuPrincipal() {
        System.out.println("Menu Principal");
        System.out.println("1. Menu Item");
        System.out.println("2. Menu Setor");
        System.out.println("3. Menu Funcionário");
        System.out.println("4. Realizar Pedido");
        System.out.println("5. Consultar Pedidos");
        System.out.println("6. Alterar Senha");
        System.out.println("7. Sair");
    }

    public void exibirMenuSetor() {
        System.out.println("Menu Setor");
        System.out.println("1. Cadastrar Funcionário");
        System.out.println("2. Consultar Pedidos");
        System.out.println("3. Sair");
    }

    public void exibirMenuFuncao() {
        System.out.println("Menu Função");
        System.out.println("1. Cadastrar Funcionário");
        System.out.println("2. Consultar Pedidos");
        System.out.println("3. Sair");
    }

    public void exibirMenuPedido() {
        System.out.println("Menu Pedido");
        System.out.println("1. Adicionar Item");
        System.out.println("2. Remover Item");
        System.out.println("3. Finalizar Pedido");
        System.out.println("4. Sair");
    }

    public void exibirMenuItem() {
        System.out.println("Menu Item");
        System.out.println("1. Alterar Quantidade");
        System.out.println("2. Sair");
    }

    public void exibirMenuConfiguracoes() {
        System.out.println("Menu Configurações");
        System.out.println("1. Alterar Senha");
        System.out.println("2. Sair");
    }

    public void exibirMenuSair() {
        System.out.println("Deseja realmente sair?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
    }

    public void exibirMenuFuncionario(Funcionario funcionario) {
        System.out.println("Menu Funcionário - " + funcionario.getNome());
        System.out.println("1. Realizar Pedido");
        System.out.println("2. Listar Itens do Pedido");
        System.out.println("3. Atualizar Dados");
        System.out.println("4. Sair");
    }

    public void exibirMenuAdministrador(Administrador administrador) {
        System.out.println("Menu Administrador - " + administrador.getNome());
        System.out.println("1. Cadastrar Item");
        System.out.println("2. Cadastrar Setor");
        System.out.println("3. Cadastrar Funcionário");
        System.out.println("4. Realizar Pedido");
        System.out.println("5. Consultar Pedidos");
        System.out.println("6. Alterar Senha");
        System.out.println("7. Sair");
    }

    public void exibirMenuSetor(Setor setor) {
        System.out.println("Menu Setor - " + setor.getNome());
        System.out.println("1. Cadastrar Funcionário");
        System.out.println("2. Consultar Pedidos");
        System.out.println("3. Sair");
    }

    public void exibirMenuFuncao(Funcoes funcao) {
        // System.out.println("Menu Função - " + funcao.getNome());
        System.out.println("1. Cadastrar Funcionário");
        System.out.println("2. Consultar Pedidos");
        System.out.println("3. Sair");
    }

    public void exibirMenuPedido(Pedido pedido) {
        // System.out.println("Menu Pedido - " + pedido.getId());
        System.out.println("1. Adicionar Item");
        System.out.println("2. Remover Item");
        System.out.println("3. Finalizar Pedido");
        System.out.println("4. Sair");
    }

    public void exibirMenuItem(Item item) {
        System.out.println("Menu Item - " + item.getNome());
        System.out.println("1. Alterar Quantidade");
        System.out.println("2. Sair");
    }
}
