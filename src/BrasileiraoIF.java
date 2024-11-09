import java.util.*;

public class BrasileiraoIF {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int opc;

        while(true) {
            System.out.println("Menu");
            System.out.println("1 - Cadastrar Resultado de Partida;");
            System.out.println("2 - Visualizar Lider do Campeonato;");
            System.out.println("3 - Listar G4;");
            System.out.println("4 - Listar Z4;");
            System.out.println("5 - Cadastrar Nova Equipe;");
            System.out.println("6 - Sair");

            switch (opc) {
                case 1:
                    cadastrarPartida();
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
                    System.out(0);
            }
        }
    }

}