public class Main {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Menu menu = new Menu();

        while(true) {
            menu.exibirMenuPrincipal();
            int opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Escolha uma opção: ", "[0-2]", "Opção inválida"));

            if (opcao == 0) {
                System.out.println("Encerrando sistema...");
                break;
            }

           switch (opcao) {
               case 1:
                     if(biblioteca.getQuantUsuarios() > 0){
                        System.out.println("Nenhum usuário cadastrado.");
                        break;
                     }
                    realizarLogin(biblioteca, menu);

                   break;
               default:
                   throw new AssertionError();
           }
        }

    }

    private static void realizarLogin(Biblioteca biblioteca, Menu menu) {
        String email = Entrada.solicitarEmail();
        String senha = Entrada.solicitarSenha();

        Usuario usuario = biblioteca.loginUsuario(email, senha);

        if (usuario != null) {
            System.out.printf("Bem-vindo, %s!\n", usuario.getNome());
            if (usuario instanceof Aluno || usuario instanceof Professor) {
                menu.exibirMenuUsuario(usuario);
            } else if (usuario instanceof Bibliotecario) {
                menu.exibirMenuBibliotecario(usuario);
            }
        } else {
            System.out.println("E-mail ou senha incorretos.");
        }
    }



}
