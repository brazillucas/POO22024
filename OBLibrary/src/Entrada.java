import java.io.Console;
import java.util.Scanner;

public class Entrada {

    public static String solicitarEntradaValida(String mensagem, String regex, String mensagemErro) {
        try (Scanner scanner = new Scanner(System.in)) {
            while(true) {
                System.out.print(mensagem);
                String entrada = scanner.nextLine();
                if (entrada.matches(regex)) {
                    return entrada;
                } else {
                    System.out.printf("%s! Tente novamente.\n", mensagemErro);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao solicitar entrada.");
            return null;
        }
    }

    public static String solicitarSenha() {
        while(true) {
            Console console = System.console();
            if (console == null) {
                System.out.println("Console indisponível");
                System.exit(1);
            }
            char[] senhaArray = console.readPassword("Senha: ");
            String senha = new String(senhaArray);
            if (senha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{4,8}$")) {
                return senha;
            } else {
                System.out.println("Senha inválida! Tente novamente.");
            }
        }
    }

    public static String solicitarEmail() {
        return solicitarEntradaValida("Digite o email do usuário: ", "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", "Email Inválido");
    }
}
