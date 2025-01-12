

public class ControleComandas {

    public static void main(String[] args) {

        Restaurante restAvenida = new Restaurante("Restaturante Pimenta Mineira");

        while(true) {
            limparTela();
            System.out.printf("Bem-vindo ao %s!\n", restAvenida.getNome());
            imprimirSeparador();
            System.out.println("MENU\n"
                            + "1 - Cadastrar comanda\n"
                            + "2 - Realizar pedido\n"
                            + "3 - fechar comanda\n"
                            + "4 - encerrar dia");

            int opcao = Integer.parseInt(solicitarEntradaValida("Informe a opção desejada: ", "^[1-4]", "Opção incorreta!"));

            imprimirSeparador();
            
            switch(opcao) {
                case 1: restAvenida.cadastraComanda(); break;
                case 2: restAvenida.realizarPedido(); break;
                case 3: restAvenida.fecharComanda(); break;
                case 4: System.out.println("Encerrando o programa..."); return;
            }
        }
    }

    public static String solicitarEntradaValida(String mensagem, String regex, String mensagemErro) {
        System.out.println("Digite 'sair' para encerrar o programa.");
        while(true) {
            System.out.print(mensagem);
            String entrada = System.console().readLine();
            if (entrada.matches(regex)) {
                return entrada;
            } else if(entrada.equalsIgnoreCase("sair")) {
                System.out.println("Encerrando o programa...");
                System.exit(0);
            } else {
                System.out.printf("%s! Tente novamente.\n", mensagemErro);
            }
        }
    }

    public static void imprimirSeparador() {
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void limparTela() {
        System.out.println("\033\143");
    }

    public static void pausar() {
        System.out.print("Pressione ENTER para continuar...");
        System.console().readLine();
    }
}