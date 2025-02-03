/**
 * 8. Classe Menu
Responsabilidade: Gerencia a interface de interação com o usuário.

Métodos:
exibirMenuPrincipal(): Exibe o menu principal do sistema.
exibirMenuUsuarioLogado(Usuario usuario): Exibe o menu específico para o tipo de usuário logado.
processarOpcao(int opcao): Processa a opção selecionada pelo usuário.
 */
public class Menu {
    public void exibirMenuPrincipal() {
        System.out.println("Bem-vindo à OBLibrary!");
        System.out.println("1 - Login");
        System.out.println("0 - Sair");
    }

    public void exibirMenuUsuario(Usuario usuario) {
        if(usuario.getTipoUsuario() == 1){
            System.out.println("=== Menu Aluno ===");
        } else if(usuario.getTipoUsuario() == 2){
            System.out.println("=== Menu Professor ===");
        }
        System.out.println("Bem-vindo, " + usuario.getNome() + "!");
        System.out.println("1 - Realizar Empréstimo");
        System.out.println("2 - Realizar Devolução");
        System.out.println("3 - Verificar Empréstimos Ativos");
        System.out.println("0 - Logout");
    }

    public void exibirMenuBibliotecario(Usuario usuario) {
        System.out.println("=== Menu Bibliotecário ===");
        System.out.println("Bem-vindo, " + usuario.getNome() + "!");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Registrar Devolução");
        System.out.println("3 - Gerar Relatórios");
        System.out.println("0 - Logout");
    }

}
