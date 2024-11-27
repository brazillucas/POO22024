import java.util.*;

public class BrasileiraoIF {

    static Scanner scan = new Scanner(System.in);
    static int partidas;

    public static void main(String[] args) {
        Equipe[] equipes = new Equipe[20];
        menu(equipes);
    }

    public static void menu(Equipe[] equipes) {
        int opc;
        limpaConsole();

        while(true) {
            System.out.println("Menu");
            System.out.println("1 - Cadastrar Resultado de Partida;");
            System.out.println("2 - Visualizar Lider do Campeonato;");
            System.out.println("3 - Listar G4;");
            System.out.println("4 - Listar Z4;");
            System.out.println("5 - Cadastrar Nova Equipe;");
            System.out.println("6 - Sair");

            System.out.print("Escolha uma opcao: ");
            opc = lerOpcao();

            switch (opc) {
                case 1:
                    cadastrarPartida(equipes);
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
                default:
                    System.out.println("Opção inválida!");
                    encerrarFuncao();
            }
        }
    }

    public static void cadastrarPartida(Equipe[] equipes) {
        int limiteEquipes = acharUltimaEquipe(equipes);
        int golsPro1, golsPro2, cartVermelho1, cartVermelho2, cartAmarelo1, cartAmarelo2;
        String equipe1, equipe2;
        int equipe1Posicao, equipe2Posicao;
        equipe1Posicao = equipe2Posicao = -1;
        boolean equipe1Encontrada = false, equipe2Encontrada = false;

        if (limiteEquipes < 1) {
            erroQuantidadeEquipes();
            encerrarFuncao();
            return;
        } else {
            limpaConsole();
            System.out.println("Equipes Cadastradas:");
            imprimirCabecalho();
            for (int i = 0; i <= limiteEquipes; i++) {
                listarEquipe(equipes, i);
            }
        }

        System.out.print("Digite o nome da equipe mandante: ");
        equipe1 = scan.nextLine();
        System.out.print("Digite o nome da equipe visitante: ");
        equipe2 = scan.nextLine();

        for (int i = 0; i <= limiteEquipes; i++) {
            if (equipes[i].getNome().equals(equipe1)) {
                equipe1Encontrada = true;
                equipe1Posicao = i;
            } else if (equipes[i].getNome().equals(equipe2)) {
                equipe2Encontrada = true;
                equipe2Posicao = i;
            }
        }

        if(equipe1Encontrada == false) {
            System.out.println("Equipe mandante não encontrada!");
            encerrarFuncao();
            return;
        } else if (equipe2Encontrada == false) {
            System.out.println("Equipe visitante não encontrada!");
            encerrarFuncao();
            return;
        }

        //Lê dados da partida
        System.out.print("Digite a quantidade de gols da equipe mandante: ");
        golsPro1 = lerOpcao();
        System.out.print("Digite a quantidade de gols da equipe visitante: ");
        golsPro2 = lerOpcao();
        
        System.out.print("Digite a quantidade de cartões vermelhos da equipe mandante: ");
        cartVermelho1 = lerOpcao();
        System.out.print("Digite a quantidade de cartões vermelhos da equipe visitante: ");
        cartVermelho2 = lerOpcao();

        System.out.print("Digite a quantidade de cartões amarelos da equipe mandante: ");
        cartAmarelo1 = lerOpcao();
        System.out.print("Digite a quantidade de cartões amarelos da equipe visitante: ");
        cartAmarelo2 = lerOpcao();

        //adiciona pontos para as duas equipes
        if (golsPro1 > golsPro2) {
            equipes[equipe1Posicao].setPontos(3);
        } else if (golsPro1 < golsPro2) {
            equipes[equipe2Posicao].setPontos(3);
        } else {
            equipes[equipe1Posicao].setPontos(1);
            equipes[equipe2Posicao].setPontos(1);
        }

        //adiciona valores da equipe mandante
        equipes[equipe1Posicao].setGolsPro(golsPro1);
        equipes[equipe1Posicao].setSaldoGols(golsPro1 - golsPro2);
        equipes[equipe1Posicao].setCartVermelho(cartVermelho1);
        equipes[equipe1Posicao].setCartAmarelo(cartAmarelo1);
        //adiciona valores da equipe visitante
        equipes[equipe2Posicao].setGolsPro(golsPro2);
        equipes[equipe2Posicao].setSaldoGols(golsPro2 - golsPro1);
        equipes[equipe2Posicao].setCartVermelho(cartVermelho2);
        equipes[equipe2Posicao].setCartAmarelo(cartAmarelo2);

        System.out.println("Partida cadastrada com sucesso!");
        partidas ++;
        System.out.println("Partidas cadastradas: " + partidas);
        ordenarEquipes(equipes);
        encerrarFuncao();
    }

    public static void ordenarEquipes(Equipe[] equipes) {
        int limiteEquipes = acharUltimaEquipe(equipes);
        Equipe temporaria;

        for (int i = 0; i <= limiteEquipes; i++) {
            for (int j = 0; j <= limiteEquipes; j++)
            {
                if(equipes[i].getPontos() > equipes[j].getPontos())
                {
                    temporaria = equipes[i];
                    equipes[i] = equipes[j];
                    equipes[j] = temporaria;
                }
                else if(equipes[i].getPontos() == equipes[j].getPontos() &&
                        equipes[i].getSaldoGols() > equipes[j].getSaldoGols())
                {
                    temporaria = equipes[i];
                    equipes[i] = equipes[j];
                    equipes[j] = temporaria;
                }
                else if(equipes[i].getPontos() == equipes[j].getPontos() &&
                        equipes[i].getSaldoGols() == equipes[j].getSaldoGols() &&
                        equipes[i].getGolsPro() > equipes[j].getGolsPro())
                {
                    temporaria = equipes[i];
                    equipes[i] = equipes[j];
                    equipes[j] = temporaria;
                }
                else if(equipes[i].getPontos() == equipes[j].getPontos() &&
                        equipes[i].getSaldoGols() == equipes[j].getSaldoGols() &&
                        equipes[i].getGolsPro() == equipes[j].getGolsPro() &&
                        equipes[i].getCartVermelho() < equipes[j].getCartVermelho())
                {
                    temporaria = equipes[i];
                    equipes[i] = equipes[j];
                    equipes[j] = temporaria;
                }
                else if(equipes[i].getPontos() == equipes[j].getPontos() &&
                        equipes[i].getSaldoGols() == equipes[j].getSaldoGols() &&
                        equipes[i].getGolsPro() == equipes[j].getGolsPro() &&
                        equipes[i].getCartVermelho() == equipes[j].getCartVermelho() &&
                        equipes[i].getCartAmarelo() < equipes[j].getCartAmarelo())
                {
                    temporaria = equipes[i];
                    equipes[i] = equipes[j];
                    equipes[j] = temporaria;
                }
            }
        }
    }

    public static void mostrarLider(Equipe[] equipes) {
        int limiteEquipes = acharUltimaEquipe(equipes);

        if (limiteEquipes < 0) {
            erroQuantidadeEquipes();
            encerrarFuncao();
            return;
        }

        ordenarEquipes(equipes);
        limpaConsole();

        System.out.println("Equipe Lider do Campeonato:");
        imprimirCabecalho();
        listarEquipe(equipes, 0);
        encerrarFuncao();
    }

    public static void listarG4(Equipe[] equipes) {
        int limiteEquipes = acharUltimaEquipe(equipes);
        limpaConsole();

        System.out.println("Equipes no G4:");
        if (limiteEquipes < 0) {
            erroQuantidadeEquipes();
        } else if (limiteEquipes < 3) {
            imprimirCabecalho();
            for(int i = 0; i < limiteEquipes + 1; i++) {
                listarEquipe(equipes, i);
            }
        } else {
            imprimirCabecalho();
            for(int i = 0; i < 4; i++) {
                listarEquipe(equipes, i);
            }
        }
        encerrarFuncao();
    }

    public static void listarZ4(Equipe[] equipes) {
        int limiteEquipes = acharUltimaEquipe(equipes);
        limpaConsole();

        System.out.println("Equipes no Z4:");
        if (limiteEquipes > 3) {
            imprimirCabecalho();
            for (int i = limiteEquipes - 3; i <= limiteEquipes; i++) {
                listarEquipe(equipes, i);
            }
        } else if (limiteEquipes < 0) {
            erroQuantidadeEquipes();
        } else {
            imprimirCabecalho();
            for(int i = 0; i < limiteEquipes + 1; i++) {
                listarEquipe(equipes, i);
            }
        }
        encerrarFuncao();
    }

    public static void cadastrarEquipe(Equipe[] equipes) {
        int limiteEquipes = acharUltimaEquipe(equipes);
        String nome;

        
        if (partidas > 0) {
            System.out.println("Campeonato já iniciado!");
            System.out.println("Não é possível adicionar uma nova equipe!");
        } else if(limiteEquipes < 19) {
            limpaConsole();
            System.out.print("Digite o nome da nova equipe: ");
            nome = scan.nextLine();
            if (verificarEquipe(equipes, nome)){
                return;
            }
            equipes[limiteEquipes + 1] = new Equipe(nome);
            System.out.println("Nova equipe cadastrada:");
            imprimirCabecalho();
            listarEquipe(equipes, limiteEquipes+1);
        } else {
            System.out.println("Limite de equipes atingido!");
            System.out.println("Não é possível adicionar uma nova equipe!");
        }
        encerrarFuncao();
    }

    public static boolean verificarEquipe(Equipe[] equipes, String nome) {
        for (int i = 0; i < acharUltimaEquipe(equipes) + 1; i++) {
            if (equipes[i].getNome().equals(nome)) {
                System.out.println("Equipe já cadastrada!");
                encerrarFuncao();
                return true;
            }
        }
        return false;
    }

    public static void listarEquipe(Equipe[] equipes, int posicao) {
    System.out.println("----------------------------------------------------------------");
    Equipe equipe = equipes[posicao];
    System.out.printf("| %-20s | %-5d | %-5d | %-5d | %-5d | %-5d |\n",
            equipe.getNome(),
            equipe.getPontos(),
            equipe.getSaldoGols(),
            equipe.getGolsPro(),
            equipe.getCartVermelho(),
            equipe.getCartAmarelo());
}

    private static void imprimirCabecalho() {
        System.out.printf("| %-20s | %-5s | %-5s | %-5s | %-5s | %-5s |\n",
                "Nome", "Pts", "SG", "GP", "CV", "CA");
    }

    public static int acharUltimaEquipe(Equipe[] equipes) {
        for(int i = 0; i < equipes.length; i++) {
            if(equipes[i] == null) {
                return i-1;
            }
        }
        return equipes.length-1;
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

    public static void erroQuantidadeEquipes() {
        System.out.println("Quantidade de equipes insuficiente!");
        System.out.println("Cadastre novas equipes e tente novamente!");
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