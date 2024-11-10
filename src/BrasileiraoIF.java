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
            limpaBuffer();

            switch (opc) {
                case 1:
                    //cadastrarPartida(equipes);
                    break;
                case 2:
                    mostrarLider(equipes);
                    break;
                case 3:
                    listarG4(equipes);
                    break;
                case 4:
                    listarZ4(equipes);
                    break;
                case 5:
                    cadastrarEquipe(equipes);
                    break;
                case 6:
                    System.out.println("Obrigado por utilizar este gerenciador!");
                    System.out.println("Programa encerrando...");
                    System.exit(0);
            }
        }
    }

    public static void mostrarLider(Equipe[] equipes) {
        int limiteEquipes = acharUltimaEquipe(equipes);
        int lider = 0;

        for(int i = 1; i <= limiteEquipes; i++) {
            if (equipes[i-1].getPontos() > equipes[i].getPontos()) {
                lider = i;
            } else if (equipes[i-1].getPontos() == equipes[i].getPontos() && equipes[i-1].getSaldoGols > equipes[i].getSaldoGols()) {
                lider = i;
            } else if (equipes[i-1].getPontos() == equipes[i].getPontos() && equipes[i-1].getSaldoGols == equipes[i].getSaldoGols() && equipes[i-1].getCartVermelho() > equipes[i].getCartVermelho()) {
                lider = i;
            } else if (equipes[i-1].getPontos() == equipes[i].getPontos() && equipes[i-1].getSaldoGols == equipes[i].getSaldoGols() && equipes[i-1].getCartVermelho() == equipes[i].getCartVermelho() && equipes[i-1].getCartAmarelo() > equipes[1].getCartAmarelo()) {
                lider = i;
            }
        }

        System.out.println("Equipe Lider do Campeonato:");
        listarEquipe(equipes, lider);
        encerrarFuncao();
    }

    public static void listarG4(Equipe[] equipes) {
        int limiteEquipes = acharUltimaEquipe(equipes);

        if (limiteEquipes < 0) {
            System.out.println("Quantidade de equipes insuficiente!");
            System.out.println("Cadastre novas equipes e tente novamente!");
        } else {
            for(int i = 0; i < 4; i++) {
                listarEquipe(equipes, i);
            }
        }
        encerrarFuncao();
    }

    public static void listarZ4(Equipe[] equipes) {
        int limiteEquipes = acharUltimaEquipe(equipes);

        System.out.println(limiteEquipes);

        if (limiteEquipes > 3) {
            for (int i = limiteEquipes - 3; i <= limiteEquipes; i++) {
                listarEquipe(equipes, i);
            }
        } else if (limiteEquipes < 0) {
            System.out.println("Quantidade de equipes insuficiente!");
            System.out.println("Cadastre novas equipes e tente novamente!");
        } else {
            for(int i = 0; i < limiteEquipes + 1; i++) {
                listarEquipe(equipes, i);
            }
        }
        encerrarFuncao();
    }

    public static void cadastrarEquipe(Equipe[] equipes) {
        int limiteEquipes = acharUltimaEquipe(equipes);
        String nome;

        System.out.println("Ultima posicao ocupada: " + limiteEquipes);
        System.out.println(getPartidas());

        if(limiteEquipes < 19) {
            if (getPartidas() == 0) {
                System.out.print("Digite o nome da nova equipe: ");
                nome = scan.nextLine();
                
                equipes[limiteEquipes + 1] = new Equipe(nome);
                System.out.println("Nova equipe cadastrada:");
                listarEquipe(equipes, limiteEquipes+1);
            } else {
                System.out.println("Campeonato já iniciado!");
                System.out.println("Não é possível adicionar uma nova equipe!");
                System.out.println("Pressione qualquer tecla para voltar ao menu...");
            }
        }
        encerrarFuncao();
    }

    public static void listarEquipe(Equipe[] equipes, int posicao) {
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.print("Nome: ");
        System.out.println(equipes[posicao].getNome());
        System.out.print("Saldo de gols: ");
        System.out.println(equipes[posicao].getSaldoGols());
        System.out.print("Quantidade de Cartões Vermelhos: ");
        System.out.println(equipes[posicao].getCartVermelho());
        System.out.print("Quantidade de Cartões Amarelos: ");
        System.out.println(equipes[posicao].getCartAmarelo());
        System.out.println("+++++++++++++++++++++++++++++++++++++\n");
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

    public static void limpaBuffer() {
        scan.nextLine();
    }

    public static void encerrarFuncao() {
        System.out.print("Pressione ENTER para voltar para o menu...");
        limpaBuffer();
        limpaConsole();
    }
}