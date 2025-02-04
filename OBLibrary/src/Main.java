public class Main {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.carregarDados();

        Menu menu = new Menu();
        Entrada.limparTela();
        
        // Loop principal do sistema
        while (true) {
            menu.exibirMenuPrincipal();
            int opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Escolha uma opção: ", "[0-4]", "Opção inválida"));

            switch (opcao) {
                case 0:
                    biblioteca.fechamentoSistema();
                    System.out.println("Encerrando sistema...");
                    return;
                case 1:
                    if (biblioteca.getQuantUsuarios() == 0) {
                        System.out.println("Nenhum usuário cadastrado.");
                        Entrada.finalizarFuncao();
                    } else {
                        biblioteca.realizarLogin(menu);
                        Entrada.finalizarFuncao();
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
                    Entrada.aguardarEnter();
                    Entrada.limparTela();
                    break;
            }
        }

    }

}
