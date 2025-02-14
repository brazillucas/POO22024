import java.io.Console;
import java.util.Scanner;

public class Entrada {

    public static String solicitarEntradaValida(String mensagem, String regex, String mensagemErro) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            if (entrada.matches(regex)) {
                return entrada;
            } else {
                System.out.printf("%s! Tente novamente.\n", mensagemErro);
            }
        }
    }

    public static String solicitarSenha() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            Console console = System.console();
            String senha;
            if (console != null) {
                char[] senhaArray = console.readPassword("Senha: (Modelo: 4-8 caracteres, 1 letra maiúscula, 1 letra minúscula, 1 número e 1 caractere especial): ");
                senha = new String(senhaArray);
            } else {
                System.out.print("Senha: ");
                senha = scanner.nextLine();
            }
            if (senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{4,8}$")) {
                return senha;
            } else {
                System.out.println("Senha inválida! Tente novamente.");
            }
        }
    }

    public static String solicitarEmail() {
        return solicitarEntradaValida("Digite o email do usuário (Modelo: usuario@provedor.com): ", "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", "Email Inválido");
    }

    public static void limparTela() {
        System.out.println("\033\143");
    }

    public static void aguardarEnter() {
        solicitarEntradaValida("Pressione Enter para continuar...","^$", "Você não pressionou Enter.");
    }

    public static void finalizarFuncao() {
        aguardarEnter();
        limparTela();
    }
}
