package BancoIF;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Representa o tabuleiro do jogo.
 * <ul>
 * <li>O tabuleiro é composto por várias posições, como:
 * <ul>
 * <li><b>"Propriedade"</b>;</li>
 * <li><b>"Companhia"</b>;</li>
 * <li><b>"Sorte/Reves"</b>;</li>
 * <li>entre outros.</li>
 * </ul>
 * <li>Cada posição possui um nome e um tipo.</li>
 * <li>O tabuleiro é responsável por gerenciar as ações de cada posição.</li>
 * <li>O tabuleiro é inicializado a partir de um arquivo CSV.</li>
 * </ul>
 */
public class Tabuleiro {
    // Atributos
    /**
     * As posições do tabuleiro.
     */
    private Posicao[] posicoes;

    // Construtor
    /**
     * Construtor da classe Tabuleiro.
     * <p>
     * Inicializa o tabuleiro a partir de um arquivo CSV.
     * </p>
     */
    public Tabuleiro() {
        posicoes = new Posicao[40]; // Número total de posições no tabuleiro.
        inicializarTabuleiro();
    }

    // Métodos
    /**
     * Inicializa o tabuleiro a partir de um arquivo CSV.
     * <p>
     * O arquivo CSV contém as informações de cada posição do tabuleiro.
     * </p>
     * <p>
     * Cada linha do arquivo contém as informações de uma posição, como:
     * <ul>
     * <li>Nome da posição;</li>
     * <li>Tipo da posição;</li>
     * <li>Outros atributos específicos de cada tipo de posição.</li>
     * </ul>
     * </p>
     * <p>
     * A depender do tipo da posição, um objeto específico é criado e armazenado no
     * tabuleiro.
     * </p>
     */
    private void inicializarTabuleiro() {
        // Leitura do arquivo CSV
        try (BufferedReader br = new BufferedReader(new FileReader("BancoIF/src/BancoIF/tabuleiro.csv"))) {
            String linha;
            int indice = 0;
            // Leitura de cada linha do arquivo
            while ((linha = br.readLine()) != null) {
                // Usa a vírgula como separador do conteúdo da linha
                String[] dados = linha.split(",");
                // Lê o tipo da posição que está na primeira coluna
                String tipo = dados[0];
                // Cria um objeto da posição a depender do tipo
                switch (tipo) {
                    case "Propriedade":
                        // Cria um objeto Propriedade com os atributos da linha
                        // Converte cada atributo para o tipo correto
                        this.posicoes[indice] = new Propriedade(dados[1], Integer.parseInt(dados[2]), Integer.parseInt(dados[3]), Integer.parseInt(dados[4]), Integer.parseInt(dados[5]));
                        break;
                    case "CartaDeSorte":
                        this.posicoes[indice] = new CartaDeSorte(dados[1]);
                        break;
                    case "ImpostoDeRenda":
                        this.posicoes[indice] = new ImpostoDeRenda(dados[1]);
                        break;
                    case "Companhia":
                        this.posicoes[indice] = new Companhia(dados[1], Integer.parseInt(dados[2]), Integer.parseInt(dados[3]));
                        break;
                    case "Cadeia":
                        this.posicoes[indice] = new Cadeia(dados[1]);
                        break;
                    case "LucrosOuDividendos":
                        this.posicoes[indice] = new LucrosOuDividendos(dados[1]);
                        break;
                    default:
                        // Em caso de não reconhecimento do tipo, cria uma posição genérica
                        this.posicoes[indice] = new Posicao(dados[1], dados[2]);
                        break;
                }
                // Incrementa para a próxima posição
                indice++;
            }
        } catch (IOException e) {
            // Em caso de erro na leitura do arquivo, imprime a exceção
            e.printStackTrace();
        }
    }

    /**
     * Verifica se a posição do jogador está contida nas posições do tabuleiro e retorna a posição do tabuleiro correspondente ao índice.
     * 
     * <p>
     * O índice é a posição que o jogador está no tabuleiro, que varia de 0 a 39.
     * </p>
     * 
     * <p>
     * Se o índice for inválido, uma exceção é lançada.
     * </p>
     * 
     * @param indice A posição do jogador no tabuleiro.
     * @return O objeto que está naquela posição do tabuleiro.
     */
    private Posicao getPosicao(int indice) {
        if (indice >= 0 && indice < posicoes.length) {
            return this.posicoes[indice];
        } else {
            throw new IndexOutOfBoundsException("Posição inválida! A posição não está contida no tabuleiro.");
        }
    }

    /**
     * Remove as propriedade e companhias de um jogador falido
     * 
     * @param jogador O jogador que está falido.
     */
    public void removerPropriedadesCompanhias(Jogador jogador) {
        for (int i = 0; i < posicoes.length; i++) {
            if (posicoes[i].getTipo().equals("Propriedade")) {
                Propriedade propriedade = (Propriedade) posicoes[i];
                if (propriedade.getProprietario() == jogador) {
                    propriedade.setProprietario(null);
                }
            } else if (posicoes[i].getTipo().equals("Companhia")) {
                Companhia companhia = (Companhia) posicoes[i];
                if (companhia.getProprietario() == jogador) {
                    companhia.setProprietario(null);
                }
            }
        }
    }

    /**
     * Executa a ação da posição do tabuleiro.
     * 
     * <p>
     * Este método executa a ação da posição do tabuleiro, que pode variar de acordo
     * com o tipo da posição.
     * </p>
     * 
     * @param jogador O jogador que está na posição e executando a ação.
     * @param indice A posição do jogador no tabuleiro.
     * @param somaDados O resultado da soma dos dados para ser usado em Compahias.
     */
    public void executarAcao(Jogador jogador, int indice, int somaDados) {
        Posicao posicao = getPosicao(indice);
        // Se o jogador cair na prisão, ele é movido para a posição da cadeia.
        if (posicao.getNome().equals("Vá para a Prisão") || posicao.getNome().equals("Cadeia")) {
            for (int i = 0; i < posicoes.length; i++) {
                if (posicoes[i].getNome().equals("Cadeia")) {
                    jogador.setPosicao(i);
                    break;
                }
            }
        }
        // Se o jogador cair em uma companhia, envia a soma dos dados jogados para calcular o aluguel.
        if(posicao.getTipo() == "Companhia") {
            posicao.acao(jogador, somaDados);
        } else {
            posicao.acao(jogador);
        }
    }

}