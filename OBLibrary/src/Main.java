public class Main {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Menu menu = new Menu();

        while(true) {
            menu.exibirMenuPrincipal();
            int opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Escolha uma opção: ", "[0-2]", "Opção inválida"));

            if (opcao == 0) {
                System.out.println("Salvando relatórios...");
                biblioteca.salvarDados();
                System.out.println("Encerrando sistema...");
                break;
            }

           if (opcao == 1 && biblioteca.getQuantUsuarios() == 0) {
                System.out.println("Nenhum usuário cadastrado.");
                continue;
            }

            if (opcao == 1) {
                realizarLogin(biblioteca, menu);
            }
        }

    }

    private static void realizarLogin(Biblioteca biblioteca, Menu menu) {
        String email = Entrada.solicitarEmail();
        String senha = Entrada.solicitarSenha();

        Usuario usuario = biblioteca.loginUsuario(email, senha);
        int opcao;

        while (biblioteca.getUsuarioLogado() != null) {
            if (usuario instanceof Aluno || usuario instanceof Professor) {
                menu.exibirMenuUsuario(usuario);
                opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Escolha uma opção: ", "^[0-4]$", "Opção inválida"));
                
                if (opcao == 0) {
                    biblioteca.logoutUsuario();
                } else if (opcao == 1) {
                    ObraLiteraria obra = biblioteca.selecionarObra();
                    if (obra != null) {
                        biblioteca.getUsuarioLogado().realizarEmprestimo(obra);
                    } else {
                        System.out.println("Obra não encontrada.");
                        System.out.println("Retornando ao menu...");
                    }
                } else if (opcao == 2) {
                    biblioteca.getUsuarioLogado().exibirObrasEmprestadas();
                    int idObra = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID da obra que deseja devolver: ", "^[0-9]+$", "ID inválido"));

                    ObraLiteraria obra = biblioteca.buscarObra(idObra);
                    if (obra != null) {
                        usuario.realizarDevolucao(obra);
                    } else {
                        System.out.println("Obra não encontrada.");
                        System.out.println("Retornando ao menu...");
                    }
                } else if (opcao == 3) {
                    biblioteca.getUsuarioLogado().exibirObrasEmprestadas();
                }

            } else if (usuario instanceof Bibliotecario) {
                menu.exibirMenuBibliotecario(usuario);

                opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Escolha uma opção: ", "^[0-3]$", "Opção inválida"));

                if (opcao == 0) {
                    biblioteca.logoutUsuario();
                } else if (opcao == 1) {
                    ((Bibliotecario) usuario).cadastrarUsuario(biblioteca);
                } else if (opcao == 2) {
                    biblioteca.getUsuarioLogado().exibirObrasEmprestadas();
                    int idObra = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID da obra que deseja registrar a devolução: ", "^[0-9]+$", "ID inválido"));

                    ObraLiteraria obra = biblioteca.buscarObra(idObra);
                    if (obra != null) {
                        ((Bibliotecario) usuario).registrarDevolucao(biblioteca.getUsuarioLogado(), obra);

                    } else {
                        System.out.println("Obra não encontrada.");
                        System.out.println("Retornando ao menu...");
                    }
                } else if (opcao == 3) {
                    System.out.println("Gerando relatórios...");
                    ((Bibliotecario) usuario).gerarRelatorios(biblioteca);
                }
            }
        }

        System.out.println("E-mail ou senha incorretos.");
    }



}
