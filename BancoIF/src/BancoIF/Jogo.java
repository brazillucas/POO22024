package BancoIF;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe principal do jogo Banco Imobiliário.
 * 
 * <p>
 * Esta classe inicializa o tabuleiro, coleta os dados dos jogadores e gerencia
 * o fluxo principal do jogo, incluindo a execução de rodadas e verificação das condições de vitória.
 * </p>
 */
public class Jogo {

    /**
     * Construtor da classe Jogo.
     * 
     * <p>
     * Inicializa uma nova instância da classe Jogo.
     * </p>
     */
    public Jogo() {
        // Construtor vazio
    }

    /**
     * Método principal que inicia o jogo.
     * 
     * <p>
     * Este método inicia o jogo, exibindo um menu para o jogador escolher entre iniciar o jogo ou sair.
     * </p>
     * 
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        // Solicitar o número de jogadores
        limparTela();
        System.out.println("Bem-vindo ao Banco Imobiliário!");
        imprimirLinha();
        // Imprime um menu com as opções do jogo
        System.out.println("1 - Iniciar jogo");
        System.out.println("2 - Sair");
        String opcao = solicitarEntradaValida("Escolha uma opção: ", "^[1-2]$", "Opção inválida");

        // Se o jogador escolher sair, encerra o jogo
        if (opcao.equals("2")) {
            System.out.println("Até mais!");
            return;
        }

        // Iniciar o jogo
        iniciarRodadasDoJogo();

    }

    /**
     * Inicia as rodadas do jogo.
     * 
     * <p>
     * Este método coleta as informações dos jogadores, instancia um tabuleiro e
     * inicia o loop principal das rodadas do jogo.
     * </p>
     * 
     */
    public static void iniciarRodadasDoJogo() {
        // Inicia variáveis do jogo
        Tabuleiro tabuleiro = new Tabuleiro();
        List<Jogador> jogadores = new ArrayList<>();
        int numJogadores;

        // Solicitar o número de jogadores
        numJogadores = Integer.parseInt(solicitarEntradaValida("Digite o número de jogadores (2 a 6): ", "^[2-6]$", "Número de jogadores inválido"));
        System.out.printf("Número de jogadores: %d\n", numJogadores);
        aguardarEnter();
        
        // Solicitar o nome de cada jogador
        for (int i = 0; i < numJogadores; i++) {
            String nome = solicitarEntradaValida("Digite o nome do jogador " + (i + 1) + " (somente letras): ", "^[a-zA-Z]+$", "Nome inválido");
            jogadores.add(new Jogador(nome));
        }

        imprimirLinha();
        // Exibir jogadores cadastrados
        System.out.println("Jogadores cadastrados:");
        imprimirLinha();
        for (Jogador jogador : jogadores) {
            System.out.printf("%s\n\n",jogador.getEstado());
        }
        imprimirLinha();
        aguardarEnter();
        limparTela();

        // Validações para iniciar o jogo
        if (jogadores.size() < numJogadores) {
            System.out.println("Erro ao iniciar o jogo!");
            return;
        }

        // Variáveis de controle de rodadas do jogo
        int jogadorAtual = 0;
        boolean jogoAtivo = true;
        int rodadas = 0;
        String saida = "S";

        // Iniciar as rodadas do jogo
        do {
            // Incrementa o número de rodadas
            rodadas++;
            System.out.println("Rodada: " + rodadas);
            imprimirLinha();

            // Exibir informações do jogador
            Jogador jogador = jogadores.get(jogadorAtual);
            exibirJogadorInfo(jogador);
            
            // Lançar os dados
            int somaDados = lancarDados();
            imprimirLinha();

            // Mover o jogador
            jogador.mover(somaDados);

            // Executar a ação correspondente à posição
            tabuleiro.executarAcao(jogador, jogador.getPosicao(), somaDados);
            imprimirLinha();
            System.out.println(jogador.getEstado());
            imprimirLinha();
            aguardarEnter();

            /**
             * Se o número de jogadas for múltiplo de 60, pergunta o jogador se deseja continuar
             * ou se deseja encerrar o jogo
             * */ 
            if(rodadas % 60 == 0) {
                System.out.println("Vocês jogaram 60 rodadas.");
                saida = solicitarEntradaValida("Deseja continuar jogando? (S/N): ", "^[SsNn]$", "Opção inválida");
            }

            visualizarTabuleiro(tabuleiro, jogadores);
            limparTela();

            // Verificar se o jogador faliu
            if (jogador.getFalido()) {
                exibirFalencia(tabuleiro, jogadores, jogador);
            }

            // Verificar se o jogo acabou
            int jogadoresAtivos = 0;
            for (Jogador jogadorAtivo : jogadores) {
                if (!jogadorAtivo.getFalido()) {
                    jogadoresAtivos++;
                }
            }

            //Recebe o valor condição de parada do loop
            jogoAtivo = finalizaJogo(jogadores, jogoAtivo, jogadoresAtivos);

            // Próximo jogador
            jogadorAtual = (jogadorAtual + 1) % jogadores.size();


        } while (jogoAtivo && saida.equalsIgnoreCase("S"));
    }

    /**
     * Pergunta ao jogador se deseja visualizar o tabuleiro.
     * 
     * <p>
     * Exibe uma mensagem perguntando se o jogador deseja visualizar o tabuleiro.
     * Caso a resposta seja sim, limpa a tela e imprime o tabuleiro.
     * </p>
     * 
     * @param tabuleiro O tabuleiro do jogo.
     */
    private static void visualizarTabuleiro(Tabuleiro tabuleiro, List<Jogador> jogadores) {
        imprimirLinha();
        System.out.println("Deseja visualizar o tabuleiro? (S/N)");
        String visualizarTabuleiro = solicitarEntradaValida("Digite a opção: ", "^[SsNn]$", "Opção inválida");
        if (visualizarTabuleiro.equalsIgnoreCase("S")) {
            limparTela();
            tabuleiro.exibirTabuleiro(jogadores);
            imprimirLinha();
            aguardarEnter();
        }
    }

    /**
     * Lança dois dados e retorna a soma dos valores.
     * 
     * <p>
     * Este método gera dois números aleatórios entre 1 e 6, simulando o lançamento de dois dados.
     * Em seguida, exibe os valores dos dados e a soma deles (que será a quantidade de casas que o jogador deve avançar).
     * </p>
     * 
     */
    private static int lancarDados() {
        Random aleatorio = new Random();
        int dado1 = aleatorio.nextInt(6) + 1;
        int dado2 = aleatorio.nextInt(6) + 1;
        int somaDados = dado1 + dado2;
        
        System.out.println("Dado 1: " + dado1);
        System.out.println("Dado 2: " + dado2);
        System.out.printf("Total de casas a avançar: %02d\n", somaDados);
        return somaDados;
    }

    /**
     * Exibe as informações do jogador atual.
     * 
     * <p>
     * Exibe o nome, saldo e posição atual do jogador.
     * </p>
     * 
     * @param jogador O jogador a ser exibido.
     */
    private static void exibirJogadorInfo(Jogador jogador) {
        System.out.println("Vez do jogador: " + jogador.getNome());
        System.out.printf("Saldo atual: R$ %.2f\n", jogador.getSaldo());
        System.out.printf("Posição atual: %02d\n", jogador.getPosicao());
    }

    /**
     * Exibe as informações do jogador falido.
     * 
     * <p>
     * Exibe uma mensagem informando que o jogador faliu e exibe o estado final do jogador.
     * </p>
     * 
     * @param tabuleiro O tabuleiro do jogo.
     * @param jogadores A lista de jogadores ativos.
     * @param jogador O jogador que faliu.
     */
    private static void exibirFalencia(Tabuleiro tabuleiro, List<Jogador> jogadores, Jogador jogador) {
        imprimirLinha();
        System.out.println("Jogador " + jogador.getNome() + " faliu!");
        System.out.println("O jogador "+ jogador.getNome() + " finalou com o seguinte estado:");
        System.out.println(jogador);
        imprimirLinha();
        aguardarEnter();
        // Remover jogador do jogo
        jogadores.remove(jogador);
        // Remove propriedade e companhias do jogador
        tabuleiro.removerPropriedadesCompanhias(jogador);
    }

    /**
     * Finaliza o jogo
     * 
     * <p>
     * Verifica se o jogo acabou, ou seja, se restou apenas um jogador ativo.
     * </p>
     * 
     * <p>
     * Se o jogo acabou, exibe uma mensagem de vitória e o estado final do jogador vencedor.
     * </p>
     * 
     * @param jogadores Lista de jogadores ativos.
     * @param jogoAtivo Indica se o jogo está ativo.
     * @param jogadoresAtivos Número de jogadores ativos.
     * @return Indica se o jogo está ativo.
     */
    private static boolean finalizaJogo(List<Jogador> jogadores, boolean jogoAtivo, int jogadoresAtivos) {
        if (jogadoresAtivos == 1) {
            jogoAtivo = false;
            System.out.println("Fim do jogo!");
            System.out.println("Jogador " + jogadores.get(0).getNome() + " venceu!");
            imprimirLinha();
            System.out.printf("%s finalou com o seguinte estado:\n", jogadores.get(0).getNome());
            imprimirLinha();
            System.out.println(jogadores.get(0));
            System.out.println("Parabéns!");
        }
        return jogoAtivo;
    }

    /**
     * Solicita uma entrada válida do usuário.
     * 
     * <p>
     * Exibe uma mensagem e verifica se a entrada satisfaz o padrão da
     * expressão regular. Caso a entrada seja inválida, exibe uma mensagem de erro
     * e solicita novamente.
     * </p>
     * 
     * @param mensagem A mensagem a ser exibida ao usuário.
     * @param regex A expressão regular para validar a entrada.
     * @param mensagemErro A mensagem de erro a ser exibida em caso de entrada inválida.
     * @return A entrada válida do usuário.
     */
    public static String solicitarEntradaValida(String mensagem, String regex, String mensagemErro) {
        while (true) {
            System.out.print(mensagem);
            String entrada = System.console().readLine();
            if (entrada.matches(regex)) {
                return entrada;
            } else {
                System.out.println(mensagemErro + "! Tente novamente.");
            }
        }
    }

    /**
     * Imprime uma linha de separação no terminal.
     * 
     * <p>
     * Imprime uma linha de 20 caracteres '-' no terminal.
     * </p>
     * 
     */
    public static void imprimirLinha() {
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Limpa a tela do terminal.
     */
    public static void limparTela() {
        System.out.print("\033\143");
        System.out.flush();
    }

    /**
     * Aguarda o jogador pressionar Enter para continuar.
     * <p>
     * Exibe uma mensagem no console e pausa a execução até que o usuário pressione Enter.
     * </p>
     */
    public static void aguardarEnter() {
        System.out.print("Pressione Enter para continuar...");
        System.console().readLine();
    }
}
