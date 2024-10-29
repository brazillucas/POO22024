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
		 * Declara uma variavel de scanner global para ser usada
		 * em todas as funcoes do programa
		 */
		static Scanner scan = new Scanner(System.in);

		/**
		 * Imprime uma mensagem de bem vindo(a) para o usuario
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
		 * Imprime uma linha formada pelo caractere "=" para separar
		 * as mensagens exibidas ao usuario
		 * @param quantidade Quantas vezes deve ser impresso o caractere
		 * 					 para formar a linha
		 */
		public static void imprimeLinha(int quantidade) {
			for (int contador = 0; contador <= quantidade; contador++) {
				System.out.print("=");
			}
			System.out.println("");
		}

		/**
		 * Cria uma tabuleiro 3x3  inicializado com os
		 * números de 1 a 8, deixando a ultima posicao vazia.
		 * Os números são preenchidos sequencialmente,
		 * simulando um estado inicial ordenado.
		 * 
		 * @return tabuleiro Uma array bidirecional representando
		 * 			tabuleiro o jogo.
		 */
		public static int[][] criarTabuleiro() {
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
		 * Imprime o tabuleiro do jogo no console, utilizando caracteres especiais
		 * para formar um quadrado visualmente agradavel.
		 * 
		 * @param tabuleiro A matriz 3x3 que contem as pecas do jogo
		 * 					para a impressao.
		 */
		public static void imprimirTabuleiro(int[][] tabuleiro) {
			//Intera linha por linha para imprimir o conteudo
			for (int linha = 0; linha < 3; linha++) {
				//Imprime a linha de cima do quadrado
				System.out.println(" +---+---+---+");
				//Intera por coluna para imprimir o conteudo da celula
				for (int coluna = 0; coluna < 3; coluna++) {
					/**
					 * verifica se a posicao contem o espaco vazio.
					 * Se tiver imprime o espaco vazio e a barra vertical.
					*/
					if (tabuleiro[linha][coluna] == 0) {
						System.out.print(" |  ");
					} else {
						/**
						 * Se contem um numero na celula, imprime uma barra vertical
						 * E depois imprime o valor.
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

		/**
		 * Troca duas pecas do tabuleiro de posicao.
		 * Se "trocarZeros" for "true", seleciona aleatoriamente duas posições adjacentes
		 * ao espaço vazio para trocar. Caso contrário, troca a peça na posicao
 		 * especificada por "linha" e "coluna" com o espaço vazio.
		 * 
		 * @param tabuleiro A matriz 3x3 onde ira ocorrer a troca.
		 * @param trocarZeros Indica se a troca e aleatória (true) ou
		 * 					  definida pelo usuário (false).
		 * @param linha A linha do valor que o usuario deseja trocar
		 * 				de lugar (relevante apenas se trocarZeros for false).
		 * @param coluna A coluna do valor que o usuario deseja trocar
		 * 				de lugar (relevante apenas se trocarZeros for false).
		 * 
		 * @return O tabuleiro, agora com as duas pecas trocadas.
		 */
		public static int[][] trocaPecas(int[][] tabuleiro, int linha, int coluna) {
			//Acha a linha e a coluna da celula vazia
			int linha1 = 0;
			int coluna1 = 0;

			//Procura a posicao da celula vazia
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (tabuleiro[i][j] == 0) {
						linha1 = i;
						coluna1 = j;
						break;
					}
				}
			}

			System.out.println("Peca que mudou: " + tabuleiro [linha][coluna]);

			//Recebe temporariamente o numero que sera trocado
			int temporario = tabuleiro[linha][coluna];

			//faz a troca dos dois numeros
			tabuleiro[linha][coluna] = tabuleiro[linha1][coluna1];
			tabuleiro[linha1][coluna1] = temporario;
			
			//Retorna o tabuleiro com 2 pecas com as posicoes trocas
			return tabuleiro;
		}

		/**
		 * Embaralha o tabuleiro criado no inicio de cada partida. Com o
		 * auxilio da funcao selecionarDificuldade() define quantas trocas
		 * serao feitas no tabuleiro. Com a dificuldade selecionada, gera 2
		 * numeros para escolher uma celula que sera trocada com a celula
		 * vazia. Manda essa celula para o metodo validarJogada() e se for
		 * entra no loop para chamar a funcao trocaPecas() para efetuar
		 * as trocas quantas vezes forem necessárias.
		 * Enquanto o tabuleiro finalizar o loop for ordenado, embaralha
		 * novamente.
		 * 
		 * @param tabuleiro Uma matriz 3x3 que contem os numeros que devem ser 
		 * 					trocados na execucao da funcao.
		 * @return O tabuleiro embaralhado conforme a dificuldade escolhida.
		 */
		public static int[][] embaralharTabuleiro(int[][] tabuleiro) {
			/*Chama a funcao selecionarDificulade() para definir quantas
			 * trocas devem ser realizadas no tabuleiro
			 */
			int dificuldade = selecionarDificuldade();

			//Gera um construtor de numero aleatorio
			Random aleatorio = new Random();
			//Gera 2 numeros aleatorios que serao usados para
			//Selecionar a celula a ser trocada

			int linhaMudanca = -1;
			int colunaMudanca = -1;
			boolean podeMudar = false;

			// Embaralha o tabuleiro até que ele não retorne ordenado
			do {
				// Gera dois numeros aleatorios para selecionar a celula a ser trocada
				// e valida se a jogada pode ser feita. Se nao puder, gera novos numeros.
				while(podeMudar == false) {
					linhaMudanca = aleatorio.nextInt(3);
					colunaMudanca = aleatorio.nextInt(3);
					// Confirma e o movimento pode ser feito
					podeMudar = validarJogada(linhaMudanca, colunaMudanca, tabuleiro);
				}

				//Chama a funcao trocaPecas() para efetivar o
				//embalhamento do tabuleiro
				for (int vezes = 0; vezes < dificuldade;vezes++) {
					tabuleiro = trocaPecas(tabuleiro, linhaMudanca, colunaMudanca);
				}
			//Verifica se o tabuleiro continua ordenado, se estiver, embaralha novamente
			} while (conferirTabuleiro(tabuleiro) == true);

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
				case 0 -> /**
					 * Caso o usuario queira encerrar o programa, chama
					 * a funcao encerraJogo() que ira finalizar o jogo
					 */
					encerraJogo();
				case 1 -> /**
					 * Caso o usuario queira jogar uma partida, chama
					 * a funcao comecarJogo() que ira executar a logica
					 * do jogo
					 */
					comecarJogo();
				case 2 -> /**
					 * Caso o usuario queira ler as instrucoes, chama
					 * a funcao mostrarInstrucoes() que ira listar as
					 * instrucoes necessarias para jogar
					 */
					mostrarInstrucoes();
				default -> {
							/**
							 * Em caso de nao usar uma das opcoes validas, a
							 * tela e limpa e um aviso aparece para o usuario
							 * logo em seguida eh chamado o menu() novamente,
							 * assim o jogador pode continuar tentando
							 * acertar ou sair do jogo.
							 */
							limparConsole();
							System.out.println("Opcao nao encontrada!");
							System.out.println("Reinsira uma opcao valida.");
							menu();
                        }
			}
		}

		/**
		 * Le a entrada de uma variavel inteira que o usuario
		 * escolheu.
		 * 
		 * @return Um valor inteiro representando a escolha do usuario.
		 */
		public static int lerOpcao() {
			int opc = -1;
			boolean entradaValida = false;
			/**
			 * Le a opcao selecionada ate obter uma
			 * entrada no formato correto
			 */
			while (!entradaValida) {
				try {
					opc = scan.nextInt();
					limparBuffer();
					entradaValida = true;
				} catch (Exception e) {
					System.out.print("Entrada invalida!! Digite um numero inteiro: ");
					limparBuffer();
				}
			}

			return opc; 
		}

		/**
		 * Exibe as regras do jogo, aguarda a confirmação do usuário,
		 * limpa a tela e retorna ao menu.
		 */
		public static void mostrarInstrucoes () {

			limparConsole();
			
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

			limparBuffer();

			limparConsole();
			menu();
		}

		/**
		 * Exibe um menu com as dificuldades disponiveis.
		 * Le a escolha do usuario e baseado nessa escolha
		 * gera um retorno especifico.
		 * Se a escolha nao for valida, o programa continua
		 * aguardando em loop uma resposta correta.
		 * 
		 * @return 20, 40 ou 80, que sera usado para definir quantas trocas
		 * 			de posicoes das pecas serao efetuadas.
		 */
		public static int selecionarDificuldade() {

			limparConsole();
			imprimeLinha(10);
			System.out.println("Niveis de dificuldade:");
			System.out.println("1 - Facil");
			System.out.println("2 - Medio");
			System.out.println("3 - Dificil");
			imprimeLinha(10);
			System.out.println("Selecione a dificuldade do jogo.");

			while (true) {
				System.out.print("Opcao Selecionada: ");
				int dificuldade = lerOpcao();
		
				return switch (dificuldade) {
					case 1 -> 20;
					case 2 -> 40;
					case 3 -> 80;
					default -> {
						System.out.println("Opcao invalida!");
						yield -1; // Usamos yield para retornar um valor e continuar o loop
					}
				};
			}

		}

		/**
		 * Move a peca no tabuleiro (uma matriz 3x3) de lugar
		 * desde que o seu valor seja valido (entre 1 e 8) e esteja
		 * ao lado da peca vazia.
		 * 
		 * @param tabuleiro A matriz 3x3 que contem os valores a
		 * 					serem trocados.
		 * @param jogada O valor da peca que o usuario deseja mover
		 * 				 para o espaco vazio.
		 * @return Verdadeiro ou falso, a depender se a peca foi movida ou nao.
		 */
		public static boolean moverPeca(int[][] tabuleiro, int jogada) {

			/**
			 * Verifica se o valor a ser trocado nao eh maior
			 * que o valor maximo presente no tabuleiro (8) ou
			 * menor que o valor minimo (0)
			 */
			if (jogada < 1 || jogada > 8 && jogada != 9) {
				limparConsole();
				System.out.println("Peca incorreta, tente novamente!");
				imprimirTabuleiro(tabuleiro);
				return false;
			} else if (jogada == 9) {
				return false;
			}

			/**
			 * Rotula o loop que roda o tabuleiro como "tab" para que
			 * Caso a troca de pecas seja valida e seja efetivada, o
			 * loop seja interrompido
			 */
			for (int linha = 0; linha < 3; linha++) {
				for (int coluna = 0; coluna < 3; coluna++) {
					
					if (tabuleiro[linha][coluna] == jogada) {
						/**
						 * Valida se o movimento pode ser executado com a peca
						 * na posicao atual, considerando as regras estabelecidas
						 * em validaJogada()
						 */
						if (validarJogada(linha, coluna, tabuleiro) == true) {
							limparConsole();
							tabuleiro = trocaPecas(tabuleiro, linha, coluna);
							imprimirTabuleiro(tabuleiro);
							return true;
						}
						/**
						 * Caso a jogada nao seja valida, limpa a tela
						 * E informa o usuario que nao pode mudar aquela peca
						 */
						limparConsole();
						System.out.println("Movimento invalido, tente novamente!");
						imprimirTabuleiro(tabuleiro);
						return false;
					}
					
				}
			}
			/**
			 * Depois de cada execucao, imprime o tabuleiro para o
			 * jogador saber qual o estado atual do jogo
			 */
			return false;
		}

		/**
		 * Verifica se a posicao da jogada e valida, ou seja,
		 * se a peca vazia esta ao lado da peca que o jogador
		 * deseja mover.
		 * Considera tambem as posicoes de borda do tabuleiro.
		 * 
		 * @param linha A linha da posicao do valor a ser mudado
		 * @param coluna A coluna da posicao do valor a ser mudado
		 * @param tabuleiro A matriz com os valores onde a funcao ira
		 * 					procurar a celula vazia para fazer as comparacoes
		 * @return Falso ou verdadeiro, para saber se a mudanca e valida ou nao
		 */
		public static boolean validarJogada(int linha, int coluna, int[][]tabuleiro) {

			// Pega o tamanho da matriz
			int maxLinha = tabuleiro.length;
			int maxColuna = tabuleiro[0].length;

			// Verifica se a posicao de cima e valida e esta vazia
			if (linha > 0 && tabuleiro[linha-1][coluna] == 0) {
				return true;
			}

			// Verifica se a posicao de baixo e valida e esta vazia
			if (linha < maxLinha - 1 && tabuleiro[linha+1][coluna] == 0) {
				return true;
			}

			// Verifica se a posicao a esquerda e valida e esta vazia
			if (coluna > 0 && tabuleiro[linha][coluna -1] == 0) {
				return true;
			}

			// Verifica se a posicao a direira e valida e esta vazia
			if (coluna < maxColuna - 1 && tabuleiro[linha][coluna + 1] == 0) {
				return true;
			}

			//Se nenhuma das condicoes acima for verdadeira, retorna falso
			return false;

		}
		

		/**
		 * Gera um vetor ordenado para conferir se o tabuleiro esta na ordem
		 * correta (1 a 8) e depois compara com o tabuleiro para verificar
		 * se tambem esta ordenado.
		 * 
		 * @param tabuleiro A matrix 3x3 usada como tabuleiro de jogo e que aqui
		 * 					sera verificada para saber se o jogador ja conseguiu.
		 *
		 * @return Um valor booleano para indicar se o tabuleiro o tabuleiro
		 * 			ja esta ordenado.
		 */
		public static boolean conferirTabuleiro(int[][] tabuleiro) {
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
		 * Limpa o buffer do scan para que não existam problemas
		 * na leitura de dados do usuário por conta de entradas
		 * anteriores.
		 */
		public static void limparBuffer() {
			// Limpa o buffer do Scanner antes de ler a entrada do usuário
			scan.nextLine();
		}

		/**
		 * Finaliza partida atual, imprime o tabuleiro e
		 * uma mensagem de parabens para o jogador.
		 * Depois chama o menu para o jogador escolher se
		 * quer continuar jogando ou sair.
		 * 
		 * @param tabuleiro Uma matriz 3x3 que a essa altura deve
		 * 					estar ordenada.
		 */
		public static void encerrarPartida(int[][] tabuleiro) {
			
			limparConsole();
			
			imprimeLinha(25);
			
			System.out.println("PARABENS!! VOCE COMPLETOU O JOGO");
			
			imprimirTabuleiro(tabuleiro);
			
			imprimeLinha(25);

			System.out.print("Pressione ENTER para continuar...");
    		
			limparBuffer();
			
			limparConsole();
			
			menu();
		}

		/**
		 * Cria um tabuleiro, embaralha ele e comeca o jogo.
		 * A cada jogada, verifica se o tabuleiro esta ordenado
		 * e se estiver, chama a funcao encerraPartida().
		 * A cada 20 jogadas, pergunta se o jogador quer continuar
		 * jogando ou se deseja comecar um novo jogo.
		 * 
		 */
		public static void comecarJogo() {
			int[][] tabuleiro = criarTabuleiro();
			tabuleiro = embaralharTabuleiro(tabuleiro);
			
			limparConsole();
			imprimirTabuleiro(tabuleiro);
			
			System.out.print("Selecione a peca que quer mover (Caso queira sair, digite 9): ");
			int jogada = lerOpcao();
			int jogadas = 0;
			

			/** intera enquanto o jogador informar uma pessa pra mexer, quando informar
			 *  o codigo de sair, o loop Finaliza
			 */
			while(true) {
				jogada = fecharPartida(jogada);
				boolean mudouPecas = moverPeca(tabuleiro, jogada);
				
				/**
				 * A cada tentativa de mudanca de peca o programa verifica se ja
				 * esta com a matriz ordenada, se estiver, chama o metodo 
				 * encerraPartida().
				 */
				if(conferirTabuleiro(tabuleiro)) {
					encerrarPartida(tabuleiro);
					break;

				} else {
					if (mudouPecas == true) {
						jogadas++;
					}
					
					System.out.println("Jogadas: " + jogadas);
					
					/** A cada 20 jogadas, o programa pergunta se o jogador quer
					 * parar ali e comecar um novo jogo
					 */
					if (jogadas > 19 && (jogadas % 20) == 0) {
						
						limparConsole();
						imprimirTabuleiro(tabuleiro);
						System.out.println("Jogadas: " + jogadas);
						boolean continuar = continuarJogo();
						
						if (continuar == true) {
							limparConsole();
							imprimirTabuleiro(tabuleiro);
							jogada++;
							System.out.println("Jogadas: " + jogadas);
							
							
						} else {
							break;
						}
					}

					System.out.print("Selecione a peca que quer mover (Caso queira sair, digite 9): ");
					jogada = lerOpcao();

					jogada = fecharPartida(jogada);
				}
			}
			limparConsole();
			menu();

		}

		/**
		 * Recebe uma jogada e verifica se ela e igual a 9 (comando de saida).
		 * Se for, pergunta se o jogador deseja sair do jogo
		 * ou continuar jogando.
		 * @param jogada O ultimo numero escolhido pelo jogador.
		 * @return O numero inteiro escolhido pelo jogador antes da chamada
		 * 			do metodo.
		 */
		private static int fecharPartida(int jogada) {
			if (jogada == 9) {
				System.out.print("Tem certeza que deseja sair? (S - Sim ou Qualquer Tecla - Nao) ");
				String escolha = scan.nextLine().toUpperCase();

				if (escolha.equals("S")) {
					limparConsole();
					menu();
				}

			}
			
			return jogada;
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
			
			while (true) {
				System.out.print("Deseja continuar o jogo ou comecar um novo? (C - Continuar ou N - Novo Jogo): ");

				String escolha = scan.nextLine().toUpperCase();

				switch (escolha) {
					case "C" -> {
								return true;
					}
					case "N" -> {
								return false;
					}
					default -> System.out.println("Opcao invalida! Pressione ENTER...");
				}
			}

		}

		/**
		 * Imprime dois caracteres que limpam a tela do console.
		 * Usado para manter um padrao limpo nas telas do jogo.
		 */
		public static void limparConsole() {
			System.out.print("\033\143");

		}
		
		/**
		 * Comeca o programa, criando um tabuleiro de exemplo, imprimindo
		 * ele e chamando o menu() para o usuario
		* @param args este parâmetro serve para caso o programa precise
					  receber algum valor como argumento.
		 */
		public static void main (String[] args) {
		    
		    int[][] tabuleiro = criarTabuleiro();
		    
		    System.out.println("Referencia");
		    imprimirTabuleiro(tabuleiro);
		    
			bemVindo();
			menu();
			
		}

}
