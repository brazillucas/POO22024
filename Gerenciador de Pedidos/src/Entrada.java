import java.io.Console;
import java.util.Scanner;

public class Entrada {

    @SuppressWarnings("ConvertToTryWithResources")
    public static String solicitarEntradaValida(String mensagem, String regex, String mensagemErro) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            if (entrada.matches(regex)) {
                return entrada;
            } else {
                System.out.printf("%s! Tente novamente.\n", mensagemErro);
            }
        }
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public static String solicitarSenha() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (true) {
            Console console = System.console();
            String senha;
            if (console != null) {
                char[] senhaArray = console.readPassword("Senha (4-8 números): ");
                senha = new String(senhaArray);
            } else {
                System.out.print("Senha: ");
                senha = scanner.nextLine();
            }
            if (senha.matches("^\\d{4,8}$")) {
                return senha;
            } else {
                System.out.println("Senha inválida! Tente novamente.");
            }
        }
    }

    public static void limparTela() {
        System.out.println("\033\143");
    }

    public static void aguardarEnter() {
        System.out.print("Pressione ENTER para continuar...");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void finalizarFuncao() {
        aguardarEnter();
        limparTela();
    }
}
