/**
 * Início do Jogo:
 * O sistema deve solicitar o número de jogadores (entre 2 e 6).
 * O sistema deve solicitar o nome de cada jogador.
 * Todos os jogadores devem iniciar com um saldo de R$1.500.
 * 
 * Rodadas do Jogo:
 * Os jogadores devem lançar dois dados, e a soma determinará a quantidade de casas a serem avançadas.
 * A ordem dos turnos deve seguir a ordem de cadastro dos jogadores.
 * O sistema deve permitir que mais de um jogador ocupe a mesma posição no tabuleiro.
 * 
 * Ações nas Posições:
 * Compra de Propriedade/Companhia: O jogador deve poder comprar uma propriedade/companhia se ela estiver disponível.
 * Pagamento de Aluguel: O jogador deve pagar aluguel se parar em uma propriedade/companhia de outro jogador.
 * Sorte/Revés: O jogador deve ganhar ou perder dinheiro com base em um evento aleatório.
 * Imposto de Renda: O jogador deve pagar uma penalidade de R$200.
 * Melhorias em Propriedades: O jogador deve poder construir pousadas ou hotéis em suas propriedades.
 * Lucros ou Dividendos: O jogador recebe uma bonificação de R$200.
 * Volta Completa: O jogador deve receber R$200 ao completar uma volta no tabuleiro.
 * Prisão: O jogador deve ser movido para a prisão e ficar uma rodada sem jogar.
 * 
 * Fim do Jogo:
 * O jogo deve terminar quando restar apenas um jogador com saldo positivo.
 * Jogadores que ficarem sem saldo devem ser eliminados do jogo, e suas propriedades e companhias voltam a ficar disponíveis.
 * 
 * Interface:
 * O jogo deve ser implementado para interação via terminal.
 * O sistema deve registrar e exibir o estado atual de cada jogador, incluindo saldo, propriedades adquiridas e posição no tabuleiro.
 * O sistema deve exibir as ações realizadas por cada jogador, como movimentação, compra de propriedades/companhias, pagamento de aluguel, etc.
 */

import java.util.ArrayList;
import java.util.List;

public class Jogo {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        List<Jogador> jogadores = new ArrayList<>();
        int numJogadores = 0;

        // Solicitar o número de jogadores
        System.out.println("Bem-vindo ao Banco Imobiliário!");
        System.out.println("Digite o número de jogadores (2 a 6): ");
        numJogadores = Integer.parseInt(System.console().readLine());

        // Solicitar o nome de cada jogador
        for (int i = 0; i < numJogadores; i++) {
            System.out.println("Digite o nome do jogador " + (i + 1) + ": ");
            String nome = System.console().readLine();
            jogadores.add(new Jogador(nome));
        }

        iniciarRodadasDoJogo(tabuleiro, jogadores, numJogadores);

    }

    public static void iniciarRodadasDoJogo(Tabuleiro tabuleiro, List<Jogador> jogadores, int numJogadores) {
        // Validações para iniciar o jogo
        if (numJogadores < 2 || numJogadores > 6) {
            System.out.println("Número de jogadores inválido!");
            return;
        }

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
        Dado dado = new Dado();

        while (jogoAtivo) {
            Jogador jogador = jogadores.get(jogadorAtual);
            System.out.println("Vez do jogador: " + jogador.getNome());
            System.out.println("Saldo atual: " + jogador.getSaldo());

            // Lançar os dados
            int dado1 = dado.rolar();
            int dado2 = dado.rolar();
            int somaDados = dado1 + dado2;

            System.out.println("Dado 1: " + dado1);
            System.out.println("Dado 2: " + dado2);
            System.out.println("Total de casas a avançar: " + somaDados);

            // Mover o jogador
            jogador.mover(somaDados);

            // Executar a ação correspondente à posição
            tabuleiro.executarAcao(jogador, jogador.getPosicao());

            // Verificar se o jogador faliu
            if (jogador.falido()) {
                System.out.println("Jogador " + jogador.getNome() + " faliu!");
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
                System.out.println("Parabéns!");
                System.out.println("O jogador finalou com o seguinte estado:");
                System.out.println(jogadores.get(0).getEstado());
            }

            // Próximo jogador
            jogadorAtual = (jogadorAtual + 1) % numJogadores;
        }
    }
}
