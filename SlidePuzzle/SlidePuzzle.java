import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class SlidePuzzle {

		public static void bemVindo() {
			System.out.println("Bem vinde ao nosso jogo!");

		}

		public static void encerraJogo() {
			System.out.println("Obrigado por utilizar nosso programa!");
			System.out.println("Programa encerrando...");
			return;
		}

		public static void imprimeLinha(int quantidade) {
			for (int contador = 0; contador <= quantidade; contador++) {
				System.out.print("=");
			}
			System.out.println("");
		}

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

		//imprime o tabuleiro em forma de quadrado
		public static void imprimeTabuleiro(int[][] tabuleiro) {
			//Intera linha por linha para imprimir o conteudo
			for (int linha = 0; linha < 3; linha++) {
				//Imprime a linha de cima do quadrado
				System.out.println(" +---+---+---+");
				//Intera por coluna para imprimir o conteudo da celula
				for (int coluna = 0; coluna < 3; coluna++) {
					//verifica se a posicao contem o espaco vazio
					if (tabuleiro[linha][coluna] == 0) {
						//Se tiver imprime o espaco vazio e a barra vertical
						System.out.print(" |  ");
					} else {
						//Se contem outro numero na celular, imprimi uma barra vertical
						//E depois imprime a celular
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
		public static int[][] trocaPecas(int[][] tabuleiro, boolean trocarZeros, int linha, int coluna, int jogada) {
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
					podeMudar = validaJogada(linha2, coluna2, tabuleiro[linha2][coluna2], tabuleiro);
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
			
			System.out.println("Mudou");
			
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
				tabuleiro = trocaPecas(tabuleiro, true, 0, 0, 0);
			}

			//Retorna o tabuleiro embaralhado
			return tabuleiro;
		}

		//Chama o menu para selecionar o que fazer no jogo
		public static void menu() {
		    imprimeLinha(25);
		    System.out.println("Escolha das opcoes abaixo: ");
		    System.out.println("0 - Sair");
		    System.out.println("1 - Comecar um novo jogo");
		    System.out.println("2 - Instrucoes");
		    imprimeLinha(25);

		    int opc = lerOpcao();

			switch(opc) {
				case 0:
					encerraJogo();
					break;
				case 1:
					comecarJogo();
					return;
				case 2:
					mostrarInstrucoes();
					return;
				default:
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

			System.out.println("1 -");
			System.out.println("2 -");
			System.out.println("3 -");
			System.out.println("4 -");
			System.out.println("5 -");

			imprimeLinha(25);
			System.out.print("Pressione ENTER para voltar ao menu...");

			String descarte = scan.nextLine();

			limpaConsole();
			menu();

		}

		public static int selecionarDificuldade() {
		    Scanner scan = new Scanner(System.in);

			limpaConsole();
			imprimeLinha(10);
			System.out.println("Niveis de dificuldade:");
			System.out.println("1 - Facil");
			System.out.println("2 - Medio");
			System.out.println("3 - Dificuldade");
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

		public static int lerJogada() {

			Scanner scan = new Scanner(System.in);

			System.out.print("Selecione a peca que quer mover (Caso queira sair, digite 9): ");
			return scan.nextInt();
		}

		public static void moverPeca(int[][] tabuleiro, int jogada) {


			if (jogada < 1 || jogada > 8) {
				System.out.println("Peca incorreta, tente novamente!");
				return;
			}

			tab:
			for (int linha = 0; linha < 3; linha++) {
				for (int coluna = 0; coluna < 3; coluna++) {
					if (tabuleiro[linha][coluna] == jogada) {
						if (validaJogada(linha, coluna, jogada, tabuleiro) == true) {
							System.out.println("validada");
							tabuleiro = trocaPecas(tabuleiro, false, linha, coluna, jogada);
							break tab;
						}
						System.out.println("Movimento invalido, tente novamente!");
					}
				}
			}

			imprimeTabuleiro(tabuleiro);
		}

		//Recebe a linha, a jogada e o tabuleiro para comparar\
		//se ao redor do valor da jogada realmente esta vazio
		public static boolean validaJogada(int linha, int coluna, int jogada, int[][]tabuleiro) {
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

		public static void comecarJogo() {
			int[][] tabuleiro = criaTabuleiro();
			tabuleiro = embaralhaTabuleiro(tabuleiro);
			imprimeTabuleiro(tabuleiro);
			/*
			 * int jogada = lerJogada();
			 * System.out.println(jogada);
			 */

			 int jogada = lerJogada();
			 int mudancas = 0;
			 while(jogada != 9) {
				moverPeca(tabuleiro, jogada);
				mudancas++;

				if (mudancas == 20) {
					limpaConsole();
					imprimeTabuleiro(tabuleiro);
					boolean continuar = continuarJogo();
					if (continuar) {
						break;
					}
					mudancas = 0;
				}
				jogada = lerJogada();
			 }
			 limpaConsole();
			 menu();

		}

		public static boolean continuarJogo() {
			Scanner scan = new Scanner(System.in);

			int escolha = 0;

			while (true) {
				System.out.print("Deseja continuar o jogo ou comecar um novo? (1 - Sim ou 2 - Nao): ");
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
		    Scanner scan = new Scanner(System.in);
		    
		    int[][] tabuleiro = criaTabuleiro();
		    
		    System.out.println("Referencia");
		    imprimeTabuleiro(tabuleiro);
		    
			bemVindo();
			menu();
			
			
		}

}
