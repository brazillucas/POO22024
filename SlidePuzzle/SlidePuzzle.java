/**
 * Classe SlidePuzzle
 * Executa um jogo de quebra-cabecas com um tabuleiro 3x3.
 * 
 * @author Lucas e Maria Luisa
 *
 */

import java.util.*;

public class SlidePuzzle {

		/**
		 * Declara uma variavel de scanner global
		 */
		static Scanner scan = new Scanner(System.in);

		/**
		 * Imprime uma mensagem de bem vindo
		 */
		public static void bemVindo() {
			System.out.println("Bem vinde ao nosso jogo!");

		}

		/**
		 * Imprime uma mensagem de despedida e finaliza a execucao do programa
		 */
		public static void encerraJogo() {
			System.out.println("Obrigado por utilizar nosso programa!");
			System.out.println("Programa encerrando...");
			System.exit(0);
		}

		/**
		 * Imprime uma linha formada pelo caractere "="
		 * @param quantidade Quantas vezes deve ser impresso o caractere da linha
		 */
		public static void imprimeLinha(int quantidade) {
			for (int contador = 0; contador <= quantidade; contador++) {
				System.out.print("=");
			}
			System.out.println("");
		}

		/**
		 * Cria uma matriz 3x3 para ser o tabuleiro do jogo
		 * @return tabuleiro O tabuleiro que sera usado no jogo
		 */
		public static int[][] criaTabuleiro() {
			int[][] tabuleiro = new int[3][3];
			int numero = 1;

			for (int linha = 0; linha < 3; linha++) {
				for (int coluna = 0; coluna < 3; coluna++) {
					if (numero < 9) {
						tabuleiro[linha][coluna] = numero;
						numero++;
					}
				}
			}
			return tabuleiro;
		}

		/**
		 * Imprime o tabuleiro usando alguns caracteres
		 * especiais para dar a impressao de um quadrado.
		 * 
		 * @param tabuleiro A matriz 3x3 que contem as pecas do jogo
		 * 					para a impressao.
		 * 					Cada posicao da matriz eh um numero inteiro.
		 * 					O valor 0 representa uma celula vazia.
		 */
		public static void imprimeTabuleiro(int[][] tabuleiro) {
			//Intera linha por linha para imprimir o conteudo
			for (int linha = 0; linha < 3; linha++) {
				//Imprime a linha de cima do quadrado
				System.out.println(" +---+---+---+");
				//Intera por coluna para imprimir o conteudo da celula
				for (int coluna = 0; coluna < 3; coluna++) {
					/**
					 * verifica se a posicao contem o espaco vazio
					 * Se tiver imprime o espaco vazio e a barra vertical
					*/
					if (tabuleiro[linha][coluna] == 0) {
						System.out.print(" |  ");
					} else {
						/**
						 * Se contem outro numero na celula, imprimi uma barra vertical
						 * E depois imprime a celular
						 */
						System.out.print(" | "+tabuleiro[linha][coluna]);
					}
				}
				//Fecha a lateral direita do quadrado com uma barra vertical
				System.out.println(" |");
			}
			//Fecha o quadrado com a linha inferior
			System.out.println(" +---+---+---+");
		}

		//Recebe o tabuleiro e troca pecas aleatorias de lugar
		/**
		 * Troca duas pecas do tabuleiro conforme indicado pelo usuario
		 * Se for uma troca ao criar o tabuleiro, gera duas coordenadas
		 * aleatorias que estejam do lado da celula vazia para trocar
		 * 
		 * @param tabuleiro A matriz 3x3 onde ira ocorrer a troca
		 * @param trocarZeros Um booleano que indica se eh uma troca de
		 * 					  inicio de partida ou uma troca no meio da
		 * 					  partida.
		 * @param linha A linha onde esta o valor que o usuario deseja
		 * 				trocar de lugar.
		 * @param coluna A coluna onde esta o valor que o usuario deseja
		 * 				trocar de lugar.
		 * 
		 * @return O tabuleiro, agora com a duas pecas trocadas.
		 */
		public static int[][] trocaPecas(int[][] tabuleiro, boolean trocarZeros, int linha, int coluna) {
			//Acha a linha e a coluna da celula vazia
			int linha1 = 0;
			int coluna1 = 0;
			int linha2 = 0;
			int coluna2 = 0;
			boolean podeMudar = false;

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (tabuleiro[i][j] == 0) {
						linha1 = i;
						coluna1 = j;
						break;
					}
				}
			}

			if (trocarZeros) {
				//Gera um construtor de numero aleatorio
				Random aleatorio = new Random();
				//Gera 2 numeros aleatorios que serao usados para
				//Selecionar a celula a ser trocada

				while(podeMudar == false) {
					linha2 = aleatorio.nextInt(3);
					coluna2 = aleatorio.nextInt(3);
					podeMudar = validaJogada(linha2, coluna2, tabuleiro);
				}
			} else {
				linha2 = linha;
				coluna2 = coluna;
				System.out.println("Peca que vai mudar: " + tabuleiro [linha2][coluna2]);
			}

			//Recebe temporariamente o numero que sera trocado
			int temporario = tabuleiro[linha2][coluna2];

			//faz a troca dos dois numeros
			tabuleiro[linha2][coluna2] = tabuleiro[linha1][coluna1];
			tabuleiro[linha1][coluna1] = temporario;
			
			//Retorna o tabuleiro com 2 pecas com as posicoes trocas
			return tabuleiro;
		}

		//Recebe o tabuleiro e retorna ele embaralhado segundo a dificuldade
		/**
		 * Embaralha o tabuleiro criado no inicio de cada partida com o
		 * Auxilio da funcao selecionarDificuldade()
		 * @param tabuleiro A matriz 3x3 que contem os numeros que devem ser 
		 * 					trocados na execucao da funcao.
		 * @return O tabuleiro embaralhado conforme a dificuldade escolhida.
		 */
		public static int[][] embaralhaTabuleiro(int[][] tabuleiro) {
			/*Chama a funcao selecionarDificulade() para definir quantas
			 * trocas devem ser realizadas no tabuleiro
			 */
			int dificuldade = selecionarDificuldade();

			/*Inteira pela quantidade de vezes definidas pela
			 * dificuldade selecionada
			 */
			for (int vezes = 0; vezes < dificuldade;vezes++) {
				tabuleiro = trocaPecas(tabuleiro, true, 0, 0);
			}

			//Retorna o tabuleiro embaralhado
			return tabuleiro;
		}

		/**
		 * Imprime um pequeno menu para apresentar ao usuario
		 * as opcoes do sistema.
		 * Conforme a escolha, uma funcao especifica eh chamada.
		 * Caso nenhuma opcao valida seja escolhida, uma mensagem
		 * de erro eh exibida e a funcao menu() eh chamada
		 * novamente. Evitando a necessidade de um loop
		 * na chamada da funcao, ao usar recursividade.
		 */
		public static void menu() {
			
			imprimeLinha(25);
		    System.out.println("Escolha das opcoes abaixo: ");
		    System.out.println("0 - Sair");
		    System.out.println("1 - Comecar um novo jogo");
		    System.out.println("2 - Instrucoes");
		    imprimeLinha(25);
			
			System.out.print("Opcao Selecionada: ");
			/* Cria uma variavel que sera usada no Switch abaixo
			 * Essa variavel e iniciada recebendo o retorno da
			 * funcao lerOpcao(); que retorna um numero
			 */
		    int opcao = lerOpcao();

			switch(opcao) {
				case 0:
					/**
					 * Caso o usuario queira encerrar o programa, chama
					 * a funcao encerraJogo() que ira finalizar o jogo
					 */
					encerraJogo();
					break;
				case 1:
					/**
					 * Caso o usuario queira jogar uma partida, chama
					 * a funcao comecarJogo() que ira executar a logica
					 * do jogo
					 */
					comecarJogo();
					break;
				case 2:
					/**
					 * Caso o usuario queira ler as instrucoes, chama
					 * a funcao mostrarInstrucoes() que ira listar as
					 * instrucoes necessarias para jogar
					 */
					mostrarInstrucoes();
					break;
				default:
					/**
					 * Em caso de nao usar uma das opcoes validas, a
					 * tela e limpa e um aviso aparece para o usuario
					 * logo em seguida eh chamado o menu() novamente,
					 * assim o jogador pode continuar tentando acertar
					 * ou sair do app
					 */
					limpaConsole();
					System.out.println("Opcao invalida!");
					System.out.println("Reinsira um numero valido.");
					menu();
					break;
			}
		}

		/**
		 * Le a entrada de uma variavel inteira que o usuario
		 * Escolheu.
		 * 
		 * @return A entrada que o usuario digirar depois da mensagem exibida
		 */
		public static int lerOpcao() {
			int opc;
			/**
			 * Le a opcao selecionada e caso nao esteja no formado correto
			 * Chama novamente a funcao recursivamente ate opter uma
			 * Entrada no formato correto
			 */
			try {
				opc = scan.nextInt();
			} catch (Exception e) {
				System.out.println("Entrada invalida!! Digite um numero inteiro:");
				limpaBuffer();
				opc = lerOpcao();
			}
			return opc; 
		}

		/**
		 * Exibe as regras do jogo para o usuario
		 * Depois de exibir as regras, aguarda a confirmacao
		 * do usuario, a partir dessa confirmacao, limpa a tela
		 * E chama novamente o menu()
		 */
		public static void mostrarInstrucoes () {

			limpaConsole();
			
			System.out.println("1 - No inicio de cada partida, voce precisa\nescolher um nivel de dificuldade.");
			System.out.println("As dificuldades se dividem nos seguintes niveis:");
			System.out.println("	1 - Facil");
			System.out.println("	2 - Medio");
			System.out.println("	3 - Dificil\n");
			
			System.out.println("2 - Em cada rodada, digite o numero da peca que deseja mover");
			System.out.println("Lembre-se: so e possivel movimentar as pecas \nque estao ao lado do espaco em braco\n");

			System.out.println("3 - O objetivo do jogo eh:");
			System.out.println("- movimentar as pecas ate que o tabuleiro esteja ordenado.");
			System.out.println("A maneira correta eh: 1 a 8, com espaco vazio no final\n");

			System.out.println("4 -Para ganhar o jogo, eh preciso que os numeros sejam ordenados:");
			System.out.println("	Da primeira casa superior esquerda para a direita");
			System.out.println("	De cima para baixo, e que a ultima casa esteja em branco.\n");

			System.out.println("5 - A cada 20 jogadas o jogo te perguntara se deseja \ncontinuar jogando.");
			System.out.println("Caso queira, voce pode selecionar para comecar um novo jogo \nou trocar o nivel de dificuldade.");

			imprimeLinha(25);
			System.out.print("Pressione ENTER para voltar ao menu...");

			limpaBuffer();
			limpaBuffer();

			limpaConsole();
			menu();
		}

		/**
		 * Exibe um menu com as dificuldades disponiveis.
		 * Le a escolha do usuario e baseado nessa escolha
		 * eh gerado um retorno especifico.
		 * Se a escolha nao for valida, o programa continua
		 * aguardando em loop infinito uma resposta correta.
		 * 
		 * @return 20, 40 ou 80, que sera usado para definir quantas trocas
		 * 			de posicoes das pecas serao efetuadas.
		 */
		public static int selecionarDificuldade() {

			limpaConsole();
			imprimeLinha(10);
			System.out.println("Niveis de dificuldade:");
			System.out.println("1 - Facil");
			System.out.println("2 - Medio");
			System.out.println("3 - Dificil");
			imprimeLinha(10);
			System.out.println("Selecione a dificuldade do jogo.");

			while(true) {

				System.out.print("Opcao Selecionada: ");
				int dificuldade = lerOpcao();
				
				switch(dificuldade) {
					case 1:
						return 20;
					case 2:
						return 40;
					case 3:
						return 80;
					default:
						System.out.println("Opcao invalida!");

				}
			}

		}

		/**
		 * Move a peca no tabuleiro (uma matriz 3x3) de lugar
		 * Desde que o valor da jogada (peca a ser troca) esteja ao
		 * Lado de um espaco vazio e dentro dos valores validos
		 * 
		 * @param tabuleiro A matriz 3x3 que contem os valores a serem trocados
		 * @param jogada O valor que deve estar do lado de um espaco vazio
		 * 				para ser trocado
		 */
		public static void moverPeca(int[][] tabuleiro, int jogada) {

			/**
			 * Verifica se o valor a ser trocado nao eh maior
			 * que o valor maximo presente no tabuleiro (8) ou
			 * menor que o valor minimo (0)
			 */
			if (jogada < 1 || jogada > 8) {
				limpaConsole();
				System.out.println("Peca incorreta, tente novamente!");
				imprimeTabuleiro(tabuleiro);
				return;
			}

			/**
			 * Rotula o loop que roda o tabuleiro como "tab" para que
			 * Caso a troca de pecas seja valida e seja efetivada, o
			 * loop seja interrompido
			 */
			tab:
			for (int linha = 0; linha < 3; linha++) {
				for (int coluna = 0; coluna < 3; coluna++) {
					
					if (tabuleiro[linha][coluna] == jogada) {
						/**
						 * Valida se o movimento pode ser executado com a peca
						 * na posicao atual, considerando as regras estabelecidas
						 * em validaJogada()
						 */
						if (validaJogada(linha, coluna, tabuleiro) == true) {
							limpaConsole();
							tabuleiro = trocaPecas(tabuleiro, false, linha, coluna);
							break tab;
						}
						/**
						 * Caso a jogada nao seja valida, limpa a tela
						 * E informa o usuario que nao pode mudar aquela peca
						 */
						limpaConsole();
						System.out.println("Movimento invalido, tente novamente!");
						
					}
					
				}
			}
			/**
			 * Depois de cada execucao, imprime o tabuleiro para o
			 * jogador saber qual o estado atual do jogo
			 */
			imprimeTabuleiro(tabuleiro);
		}

		/**
		 * Recebe a linha, a jogada e o tabuleiro para comparar
		 * se ao redor do valor da jogada realmente esta vazio
		 * @param linha A linha da posicao do valor a ser mudado
		 * @param coluna A coluna da posicao do valor a ser mudado
		 * @param tabuleiro A matriz com os valores onde a funcao ira
		 * 					procurar a celula vazia para fazer as comparacoes
		 * @return Falso ou verdadeiro, para saber se a mudanca e valida ou nao
		 */
		public static boolean validaJogada(int linha, int coluna, int[][]tabuleiro) {
			if (linha == 0 && coluna == 0) {
				if(tabuleiro[linha+1][coluna] == 0 || tabuleiro[linha][coluna+1] == 0) {
					return true;
				}
			}

			if (linha == 0 && coluna == 1) {
				if(tabuleiro[linha][coluna-1] == 0 || tabuleiro[linha+1][coluna] == 0 || tabuleiro[linha][coluna+1] == 0) {
					return true;
				}
			}

			if(linha == 0 && coluna == 2) {
				if(tabuleiro[linha][coluna-1] == 0 || tabuleiro[linha+1][coluna] == 0) {
					return true;
				}
			}

			if(linha == 1 && coluna == 0) {
				if (tabuleiro[linha-1][coluna] == 0 || tabuleiro[linha][coluna+1] == 0 || tabuleiro[linha+1][coluna] == 0) {
					return true;
				}
			}

			if(linha == 1 && coluna == 1) {
				if(tabuleiro[linha-1][coluna] == 0 || tabuleiro[linha][coluna-1] == 0 || tabuleiro[linha+1][coluna] == 0 || tabuleiro[linha][coluna+1] == 0) {
					return true;
				}
			}

			if (linha == 1 && coluna == 2) {
				if(tabuleiro[linha-1][coluna] == 0 || tabuleiro[linha][coluna-1] == 0 || tabuleiro[linha+1][coluna] == 0) {
					return true;
				}
			}

			if(linha == 2 && coluna == 0) {
				if(tabuleiro[linha-1][coluna] == 0 || tabuleiro[linha][coluna+1] == 0) {
					return true;
				}
			}

			if(linha == 2 && coluna == 1) {
				if(tabuleiro[linha][coluna-1] == 0 || tabuleiro[linha-1][coluna] == 0 || tabuleiro[linha][coluna+1] == 0) {
					return true;
				}
			}

			if(linha == 2 && coluna == 2) {
				if(tabuleiro[linha][coluna-1] == 0 || tabuleiro[linha-1][coluna] == 0) {
					return true;
				}
			}

			return false;

		}
		

		/**
		 * Gera um vetor ordenado para conferir se o tabuleiro esta na ordem
		 * correta (1 a 8) e depois compara com o tabuleiro para verificar
		 * se esta ordenado tambem
		 * 
		 * @param tabuleiro A matrix 3x3 usada como tabuleiro de jogo e que aqui
		 * 					sera verificada para saber se o jogador ja conseguiu
		 *
		 *  @return Um valor booleano para indicar se o tabuleiro o tabuleiro
		 * 			ja esta ordenado.
		 */
		public static boolean confereTabuleiro(int[][] tabuleiro) {
			int tabuleiroCorrigido[] = new int[9];
			
			for (int i = 0; i < 8; i++) {
				tabuleiroCorrigido[i] = i+1;
			}

			tabuleiroCorrigido[8] = 0;

			int contador = 0;
			
			for (int linha = 0; linha < 3; linha ++) {
				for (int coluna = 0; coluna < 3; coluna ++) {
					if(tabuleiro[linha][coluna] != tabuleiroCorrigido[contador]) {
						return false;
					}
					contador++;
				}
			}
			return true;
		}
		
		/**
		 * Limpa o buffer do scan, garantindo que nao vai ser feita uma
		 * Leitura falsa (de lixo) para dentro da variavel
		 */
		public static void limpaBuffer() {
			// Limpa o buffer do Scanner antes de ler a entrada do usuário
			scan.nextLine();
		}

		/**
		 * Finaliza partida atual
		 * Imprime o tabuleiro e uma mensagem de parabens para
		 * O jogador, por ter completado o tabuleiro.
		 * Quando o usuario decide continuar, chama o menu() novamente.
		 * @param tabuleiro Uma matriz 3x3 que a essa altura deve
		 * 					estar ordenada.
		 */
		public static void encerraPartida(int[][] tabuleiro) {
			
			limpaConsole();
			
			imprimeLinha(25);
			
			System.out.println("PARABENS!! VOCE COMPLETOU O JOGO");
			
			imprimeTabuleiro(tabuleiro);
			
			imprimeLinha(25);
			
			limpaBuffer();

			System.out.print("Pressione ENTER para continuar...");
    		
			limpaBuffer();
			
			limpaConsole();
			
			menu();
		}

		/**
		 * Inicia o jogo de fato. criando um tabuleiro, embaralhando ele
		 * Imprime o tabuleiro para o jogador e depois entra num loop
		 * Lendo as jogadas que serao feitas pelo programa
		 */
		public static void comecarJogo() {
			int[][] tabuleiro = criaTabuleiro();
			tabuleiro = embaralhaTabuleiro(tabuleiro);
			
			limpaConsole();
			imprimeTabuleiro(tabuleiro);
			
			System.out.print("Selecione a peca que quer mover (Caso queira sair, digite 9): ");
			int jogada = lerOpcao();
			int jogadas = 0;

			/** intera enquanto o jogador informar uma pessa pra mexer, quando informar
			 *  o codigo de sair, o loop Finaliza
			 */
			while(jogada != 9) {
			moverPeca(tabuleiro, jogada);
			
			/**
			 * A cada tentativa de mudanca de peca o programa verifica se ja
			 * esta com a matriz ordenada, se estiver, chama o metodo 
			 * encerraPartida().
			 */
			if(confereTabuleiro(tabuleiro)) {
				encerraPartida(tabuleiro);
				break;

			} else {
				
				jogadas++;
				System.out.println("Jogadas: " + jogadas);
				
				/** A cada 20 jogadas, o programa pergunta se o jogador quer
				 * parar ali e comecar um novo jogo
				 */				
				if ((jogadas % 20) == 0) {
					
					limpaConsole();
					imprimeTabuleiro(tabuleiro);
					System.out.println("Jogadas: " + jogadas);
					boolean continuar = continuarJogo();
					
					if (continuar) {
						break;
					}
				}
				limpaBuffer();
				System.out.print("Selecione a peca que quer mover (Caso queira sair, digite 9): ");
				jogada = lerOpcao();
			}
			}
			limpaConsole();
			menu();

		}

		/**
		 * Le um numero int usado para definir se o jogador
		 * quer continuar jogando ou se deseja sair para
		 * o menu
		 * 
		 * @return falso ou verdadeiro, a depender do
		 * 			valor escolhido pelo usuario
		 */
		public static boolean continuarJogo() {
			String escolha;

			while (true) {
				limpaBuffer();

				System.out.print("Deseja continuar o jogo ou comecar um novo? (C - Continuar ou N - Novo Jogo): ");
				
				escolha = scan.nextLine();

				switch (escolha) {
					case "C" -> {
                                            return false;
                                }
					case "c" -> {
                                            return false;
                                }
					case "N" -> {
                                            return true;
                                }
					case "n" -> {
                                            return true;
                                }
					default -> System.out.println("Opcao invalida! Pressione ENTER...");
				}
			}

		}

		/**
		 * Imprime dois caracteres que limpam a tela do console.
		 * Usado para manter um padrao limpo nas telas do jogo.
		 */
		public static void limpaConsole() {
			System.out.print("\033\143");

		}
		
		/**
		 * Comeca o programa, criando um tabuleiro de exemplo, imprimindo
		 * ele e chamando o menu() para o usuario
		* @param args este parâmetro serve para caso o programa precise
					  receber algum valor como argumento.
		 */
		public static void main (String[] args) {
		    
		    int[][] tabuleiro = criaTabuleiro();
		    
		    System.out.println("Referencia");
		    imprimeTabuleiro(tabuleiro);
		    
			bemVindo();
			menu();
			
		}

}
