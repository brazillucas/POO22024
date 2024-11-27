import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Passageiro passageiros[] = new Passageiro[30];
        int ultimoPassageiro = 0;

        menu();
        int opc = lerOpcao();

        while (opc != 4) {
            switch (opc) {
                case 1:
                    if (verificarLimitePassageiros(ultimoPassageiro) ) {
                        Passageiro passageiroTemp = criarPassageiro();

                        if (validarDuploPassageiro(passageiros, passageiroTemp, ultimoPassageiro)) {
                            System.out.println("Passageiro já registrado!");
                            System.out.println("Tente novamente com novos dados.");
                            encerrarFluxo();
                        } else {
                            passageiros[ultimoPassageiro] = passageiroTemp;
                            ultimoPassageiro++;

                            // Exibir o passageiro registrado
                            System.out.println("Passageiro cadastrado com sucesso:");
                            System.out.println(passageiros[ultimoPassageiro - 1]);
                            
                            encerrarFluxo();
                        }
                        break;
                    }

                    break;
                case 2:
                    if (ultimoPassageiro == 0) {
                        System.out.println("Nenhum passageiro registrado!");
                        System.out.println("Registre passageiros antes de imprimi-los.");
                    } else {
                        listarPassageiros(passageiros, ultimoPassageiro);
                    }
                    encerrarFluxo();
                    break;
                case 3:
                    System.out.println("Cadastro de voos");
                    encerrarFluxo();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    encerrarFluxo();
                    break;
            }
            menu();
            opc = lerOpcao();
        }

        encerrarPrograma();

    }

    // Exibe o menu principal
    public static void menu() {
        System.out.println("Bem-vindo ao SISVEPA!");
        System.out.println("1 - Cadastro de passageiros;");
        System.out.println("2 - Listar Passageiros;");
        System.out.println("3 - Cadastro de voos;");
        System.out.println("4 - Sair;");
        System.out.print("Selecione uma opção: ");
    }

    // Cria um objeto Passageiro com base nas entradas do usuário
    public static Passageiro criarPassageiro() {
        String nome = solicitarEntradaValida("Digite o nome (somente letras): ", "^[a-zA-Z]+$");
        String sobrenome = solicitarEntradaValida("Digite o sobrenome (somente letras): ", "^[a-zA-Z]+$");
        String dataNascimento = solicitarEntradaValida("Digite a data de nascimento (formato DD-MM-AAAA): ", "^\\d{2}-\\d{2}-\\d{4}$");
        String CPF = solicitarEntradaValida("Digite o CPF (formato 999.999.999-99)", "^\\d{3}.\\d{3}.\\d{3}-\\d{2}");
        String email = solicitarEntradaValida("Digite o email: ", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

        // Lógica para verificar se o passageiro possui comorbidades
        boolean comorbidade = false;
        String comorbidadeTemp = "";
        while (true) {
            System.out.print("Possui comorbidades? (Sim/Nao): ");
            comorbidadeTemp = scan.nextLine();
            if (comorbidadeTemp.equalsIgnoreCase("Sim")) {
                comorbidade = true;
                break;
            } else if (comorbidadeTemp.equalsIgnoreCase("Nao")) {
                break;
            } else {
                System.out.println("Entrada inválida. Digite 'Sim' ou 'Nao'.");
            }
        }

        // Retorna o objeto Passageiro após validação
        return new Passageiro(nome, sobrenome, dataNascimento, CPF, email, comorbidade);

    }

    // Verifica se o passageiro não está duplicado
    public static boolean validarDuploPassageiro(Passageiro[] passageiros, Passageiro passageiro, int limite) {
        for (int i = 0; i <= limite; i++) {
            if (passageiros[i] != null) {
                if (passageiros[i].getNome().equals(passageiro.getNome()) &&
                    passageiros[i].getSobrenome().equals(passageiro.getSobrenome()) &&
                    passageiros[i].getDataNascimento().equals(passageiro.getDataNascimento()) &&
                    passageiros[i].getEmail().equals(passageiro.getEmail()) &&
                    passageiros[i].getComorbidades() == passageiro.getComorbidades()) {

                    return true; // Duplicidade encontrada
                }
            }
        }
        return false;
    }
    
    // Verifica se o limite de passageiros foi atingido
    public static boolean verificarLimitePassageiros(int ultimoPassageiro) {
        if (ultimoPassageiro > 30) {
            System.out.println("Limite de passageiros atingido. Não é possível cadastrar mais passageiros.");
            return false;
        }
        return true;
    }

    // Método para solicitar e validar entrada
    private static String solicitarEntradaValida(String mensagem, String regex) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scan.nextLine();
            if (entrada.matches(regex)) {
                return entrada;
            } else {
                System.out.println("Entrada inválida. Tente novamente.");
            }
        }
    }

    public static void listarPassageiros(Passageiro[] passageiros, int limite) {
        // Imprime usando foreach
        for (int i = 0; i < limite; i++) {
            System.out.println(passageiros[i]);
        }
    }

    public static int lerOpcao() {
        int opc = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                opc = scan.nextInt();
                limpaBuffer();
                if(opc < 0) {
                    System.out.print("Entrada inválida!! Digite um número inteiro positivo: ");
                    entradaValida = false;
                } else {
                    entradaValida = true;
                }
            } catch (Exception e) {
                System.out.print("Entrada inválida!! Digite um número inteiro positivo: ");
                limpaBuffer();
            }
        }
        return opc;
    }

    public static void limpaConsole() {
        System.out.println("\033\143");
    }

    public static void limpaBuffer() {
        scan.nextLine();
    }

    static void mensagemEspera() {
        System.out.print("Pressione Enter para continuar...");
    }

    /**
     * Imprime uma mensagem de despedida e finaliza a execucao do programa
     */
    public static void encerrarPrograma() {
        System.out.println("Obrigado por utilizar nosso programa!");
        System.out.println("Programa encerrando...");
        System.exit(0);
    }

    public static void encerrarFluxo() {
        mensagemEspera();
        limpaBuffer();
        limpaConsole();
    }
}
