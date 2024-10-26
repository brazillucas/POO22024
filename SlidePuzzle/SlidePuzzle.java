import java.util.*;

public class SlidePuzzle {

		/**
		 * Imprime uma mensagem de bem vindo
		 */
		public static void bemVindo() {
			System.out.println("Bem vinde ao nosso jogo!");

		}

		/**
		 * Finaliza a execucao do programa
		 * @return 0, retorna vazio para finalizar o programa
		 */
		public static void encerraJogo() {
			System.out.println("Obrigado por utilizar nosso programa!");
			System.out.println("Programa encerrando...");
			return;
		}

		/**
		 * Imprime uma linha formada pelo caractere "="
		 * @param quantidade
		 * @return 
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
		 * @return 
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

		//Chama o menu para selecionar o que fazer no jogo
		public static void menu() {
			
			/* Imprime um pequeno menu para apresentar ao usuario
			 * as opcoes do sistema.
			 */
		    imprimeLinha(25);
		    System.out.println("Escolha das opcoes abaixo: ");
		    System.out.println("0 - Sair");
		    System.out.println("1 - Comecar um novo jogo");
		    System.out.println("2 - Instrucoes");
		    imprimeLinha(25);
			
			/* Cria uma variavel que sera usada no Switch abaixo
			 * Essa variavel e iniciada recebendo o retorno da
			 * funcao lerOpcao(); que retorna um numero
			 */
		    int opc = lerOpcao();

			switch(opc) {
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
					return;
				case 2:
					/**
					 * Caso o usuario queira ler as instrucoes, chama
					 * a funcao mostrarInstrucoes() que ira listar as
					 * instrucoes necessarias para jogar
					 */
					mostrarInstrucoes();
					return;
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
					return;
			}
		}

		public static int lerOpcao() {

		    Scanner scan = new Scanner(System.in);

			System.out.print("Opcao Selecionada: ");
			return scan.nextInt();
		}

		public static void mostrarInstrucoes () {
			Scanner scan = new Scanner(System.in);

			limpaConsole();
			
			System.out.println("1 - Para comecar, voce precisa escolher um nivel de dificuldade.");
			System.out.println("	As dificuldades se dividem nos seguintes niveis:");
			System.out.println("		1.1 - Facil");
			System.out.println("		1.2 - Medio");
			System.out.println("		1.3 - Dificil");
			
			System.out.println("2 - Em cada rodada, digite o numero da peca que deseja mover\n");
			System.out.println("	Lembre-se: so e possivel movimentar as pecas que estao ao lado do espaco em braco\n");
			System.out.println("3 - O objetivo do jogo e movimentar as pecas ate que o tabuleiro esteja ordenado da maneira correta (1 a 8 com espaco vazio no final\n");
			System.out.println("4 -Para ganhar o jogo, e preciso que os numeros sejam ordenados da primeira casa superior esquerda para a direita, de cima para baixo, e que a ultima casa esteja em branco.\n");
			System.out.println("5 - Caso queira, voce pode selecionar para comecar um novo jogo ou trocar o nivel de dificuldade.");

			imprimeLinha(25);
			System.out.print("Pressione ENTER para voltar ao menu...");

			String descarte = scan.nextLine();

			limpaConsole();
			menu();

		}

		/**
		 * Le um numero que sera retornado e usado para definir o nivel de
		 * dificuldade do jogo.
		 * 
		 * @return 20, 40 ou 80, que sera usado para definir quantas trocas
		 * 			de posicoes das pecas serao efetuadas.
		 */
		public static int selecionarDificuldade() {
			Scanner scan = new Scanner(System.in);

			limpaConsole();
			imprimeLinha(10);
			System.out.println("Niveis de dificuldade:");
			System.out.println("1 - Facil");
			System.out.println("2 - Medio");
			System.out.println("3 - Dificil");
			imprimeLinha(10);
			System.out.println("Selecione a dificuldade do jogo.");

			while(true) {

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
		 * Le o numero da tecla que o programa deve tentar mover de lugar
		 * 
		 * @return A entrada que o usuario colocar depois da impressao.
		 */
		public static int lerJogada() {

			Scanner scan = new Scanner(System.in);

			System.out.print("Selecione a peca que quer mover (Caso queira sair, digite 9): ");
			return scan.nextInt();
		}


		public static void moverPeca(int[][] tabuleiro, int jogada) {

			if (jogada < 1 || jogada > 8) {
				limpaConsole();
				System.out.println("Peca incorreta, tente novamente!");
				imprimeTabuleiro(tabuleiro);
				return;
			}

			tab:
			for (int linha = 0; linha < 3; linha++) {
				for (int coluna = 0; coluna < 3; coluna++) {
					
					if (tabuleiro[linha][coluna] == jogada) {
						
						if (validaJogada(linha, coluna, tabuleiro) == true) {
							limpaConsole();
							tabuleiro = trocaPecas(tabuleiro, false, linha, coluna);
							break tab;
						}
						
						limpaConsole();
						System.out.println("Movimento invalido, tente novamente!");
						
					}
					
				}
			}
			
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
		
		public static boolean confereTabuleiro(int[][] tabuleiro) {
			int tabuleiroCorrigido[] = new int[9];
			
			for (int i = 0; i < 8; i++) {
				tabuleiroCorrigido[i] = i+1;
			}

			tabuleiroCorrigido[8] = 0;

			int contador = 0;
			boolean conferencia = false;
			
			conferindo:
			for (int linha = 0; linha < 3; linha ++) {
				for (int coluna = 0; coluna < 3; coluna ++) {
					if(tabuleiro[linha][coluna] == tabuleiroCorrigido[contador]) {
						conferencia = true;
					} else {
						conferencia = false;
						break conferindo;
					}
					contador++;
				}
			}
			
			return conferencia;			
		}
		
		public static void encerraPartida(int[][] tabuleiro) {
			limpaConsole();
			
			imprimeLinha(25);
			
			System.out.println("PARABENS!! VOCE COMPLETOU O JOGO");
			
			imprimeTabuleiro(tabuleiro);
			
			imprimeLinha(25);
			
			System.out.print("Pressione ENTER para continuar...");
			
			Scanner scan = new Scanner(System.in);
			
			String espera = scan.nextLine();
			
			limpaConsole();
			
			menu();
		}

		public static void comecarJogo() {
			int[][] tabuleiro = criaTabuleiro();
			tabuleiro = embaralhaTabuleiro(tabuleiro);
			limpaConsole();
			imprimeTabuleiro(tabuleiro);
			
			 int jogada = lerJogada();
			 int jogadas = 0;
			 boolean finalizar = false;
			 
			 while(jogada != 9) {
				moverPeca(tabuleiro, jogada);
				
				finalizar = confereTabuleiro(tabuleiro);
				
				if(finalizar) {
					encerraPartida(tabuleiro);
					return;
					
				} else {
					
					
						jogadas++;
					System.out.println("Jogadas: " + jogadas);
					
					if ((jogadas % 20) == 0) {
						
						limpaConsole();
						
						imprimeTabuleiro(tabuleiro);
											
						System.out.println("Jogadas: " + jogadas);
						
						boolean continuar = continuarJogo();
						
						if (continuar) {
							break;
						}
					}
					jogada = lerJogada();
				}
			 }
			 limpaConsole();
			 menu();

		}

		public static boolean continuarJogo() {
			Scanner scan = new Scanner(System.in);

			int escolha = 0;

			while (true) {
				System.out.print("Deseja continuar o jogo ou comecar um novo? (1 - Continuar ou 2 - Novo Jogo): ");
				escolha = scan.nextInt();
				if (escolha == 1) {
					return false;
				} else if (escolha == 2) {
					return true;
				} else {
					System.out.println("Opcao invalida!");
				}
			}

		}

		
		public static void limpaConsole() {
			System.out.print("\033\143");
		}
		
		public static void main (String[] args) {
		    
		    int[][] tabuleiro = criaTabuleiro();
		    
		    System.out.println("Referencia");
		    imprimeTabuleiro(tabuleiro);
		    
			bemVindo();
			menu();
			
			
		}

}
