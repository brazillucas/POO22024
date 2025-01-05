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
     * Método principal que inicia o jogo.
     * 
     * <p>
     * Este método configura o jogo coletando informações dos jogadores e inicia
     * o loop principal das rodadas.
     * </p>
     * 
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        List<Jogador> jogadores = new ArrayList<>();
        int numJogadores = 0;

        // Solicitar o número de jogadores
        limparTela();
        System.out.println("Bem-vindo ao Banco Imobiliário!");
        numJogadores = Integer.parseInt(solicitarEntradaValida("Digite o número de jogadores (2 a 6): ", "^[2-6]$", "Número de jogadores inválido"));
        aguardarEnter();
        
        // Solicitar o nome de cada jogador
        for (int i = 0; i < numJogadores; i++) {
            String nome = solicitarEntradaValida("Digite o nome do jogador " + (i + 1) + " (somente letras): ", "^[a-zA-Z]+$", "Nome inválido");
            jogadores.add(new Jogador(nome));
        }

        imprimirLinha();
        iniciarRodadasDoJogo(tabuleiro, jogadores, numJogadores);

    }

    /**
     * Inicia as rodadas do jogo.
     * 
     * @param tabuleiro O tabuleiro do jogo.
     * @param jogadores A lista de jogadores.
     * @param numJogadores O número de jogadores.
     */
    public static void iniciarRodadasDoJogo(Tabuleiro tabuleiro, List<Jogador> jogadores, int numJogadores) {
        // Validações para iniciar o jogo
        if (jogadores == null || jogadores.size() < numJogadores) {
            System.out.println("Erro ao iniciar o jogo!");
            return;
        }

        if (tabuleiro == null) {
            System.out.println("Erro ao iniciar o jogo!");
            System.out.println("Tabuleiro não foi inicializado!");
            return;
        }
        
        // Iniciar as rodadas do jogo
        int jogadorAtual = 0;
        boolean jogoAtivo = true;
        Random aleatorio = new Random();

        // Variável de controle de rodadas do jogo
        int rodadas = 0;
        String saida = "S";

        do {
            Jogador jogador = jogadores.get(jogadorAtual);
            System.out.println("Vez do jogador: " + jogador.getNome());
            System.out.printf("Saldo atual: R$ %.2f\n", jogador.getSaldo());
            System.out.println("Posição atual: " + jogador.getPosicao());
            
            // Lançar os dados
            int dado1 = aleatorio.nextInt(6) + 1;
            int dado2 = aleatorio.nextInt(6) + 1;
            int somaDados = dado1 + dado2;
            
            System.out.println("Dado 1: " + dado1);
            System.out.println("Dado 2: " + dado2);
            System.out.println("Total de casas a avançar: " + somaDados);
            imprimirLinha();

            // Mover o jogador
            jogador.mover(somaDados);

            // Executar a ação correspondente à posição
            tabuleiro.executarAcao(jogador, jogador.getPosicao(), somaDados);
            imprimirLinha();
            System.out.println(jogador.getEstado());
            imprimirLinha();
            aguardarEnter();
            limparTela();

            // Verificar se o jogador faliu
            if (jogador.falido()) {
                System.out.println("Jogador " + jogador.getNome() + " faliu!");
                System.out.println("O jogador "+ jogador.getNome() + " finalou com o seguinte estado:");
                System.out.println(jogador);
                // Remover jogador do jogo
                jogadores.remove(jogador);
            }

            // Verificar se o jogo acabou
            int jogadoresAtivos = 0;
            for (Jogador jogadorAtivo : jogadores) {
                if (!jogadorAtivo.falido()) {
                    jogadoresAtivos++;
                }
            }

            if (jogadoresAtivos == 1) {
                jogoAtivo = false;
                System.out.println("Fim do jogo!");
                System.out.println("Jogador " + jogadores.get(0).getNome() + " venceu!");
                System.out.println("O jogador finalou com o seguinte estado:");
                System.out.println(jogadores.get(0));
                System.out.println("Parabéns!");
            }

            // Próximo jogador
            jogadorAtual = (jogadorAtual + 1) % numJogadores;
            
            // Incrementa o número de rodadas
            rodadas++;
            // Se o número de jogadas for múltiplo de 60, pergunta o jogador se deseja continuar
            // ou se deseja encerrar o jogo
            if(rodadas % 60 == 0) {
                saida = solicitarEntradaValida("Deseja continuar jogando? (S/N): ", "^[SsNn]$", "Opção inválida");
            }
        } while (jogoAtivo && saida.equalsIgnoreCase("S"));
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
        System.out.print("\033[H\033[2J");
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
