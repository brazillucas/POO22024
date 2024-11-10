import java.util.*;

public class BrasileiraoIF {

    static Scanner scan = new Scanner(System.in);
    private static int partidas;
    
    public static int getPartidas() {
        return partidas;
    }

    public static void setPartidas(int partidas) {
        partidas += partidas;
    }

    public static void main(String[] args) {
        Equipe[] equipes = new Equipe[20];
        menu(equipes);
    }

    public static void menu(Equipe[] equipes) {
        int opc;

        while(true) {
            System.out.println("Menu");
            System.out.println("1 - Cadastrar Resultado de Partida;");
            System.out.println("2 - Visualizar Lider do Campeonato;");
            System.out.println("3 - Listar G4;");
            System.out.println("4 - Listar Z4;");
            System.out.println("5 - Cadastrar Nova Equipe;");
            System.out.println("6 - Sair");

            System.out.print("Escolha uma opcao: ");
            opc = scan.nextInt();

            switch (opc) {
                case 1:
                    cadastrarPartida(equipes);
                    break;
                case 2:
                    mostrarLider();
                    break;
                case 3:
                    listarG4();
                    break;
                case 4:
                    listarZ4();
                    break;
                case 5:
                    cadastrarEquipe();
                    break;
                case 6:
                    System.out.println("Obrigado por utilizar este gerenciador!");
                    System.out.println("Programa encerrando...");
                    System.exit(0);
            }
        }
    }

    public static void cadastrarEquipe(Equipe[] equipes) {
        int limiteEquipes = acharUltimaEquipe(equipes);

        if(limiteEquipes < 19) {
            if (getPartidas() == 0) {
                System.out.print("Digite o nome da nova equipe: ");
                String nome = scan.nextLine();
                equipes[limiteEquipes + 1] = new Equipe(nome);
            } else {
                System.out.println("Campeonato já iniciado!");
                System.out.println("Não é possível adicionar uma nova equipe!");
                System.out.println("Voltando ao menu...");
                limpaConsole();
            }
        }

    }

    public static int acharUltimaEquipe(Equipe[] equipes) {
        for(int i = 0; i < equipes.length; i++) {
            if(equipes[i] == null) {
                return i-1;
            }
        }
        return equipes.length-1;
    }

    public static void limpaConsole() {
        System.out.println("\033\143");
    }
}