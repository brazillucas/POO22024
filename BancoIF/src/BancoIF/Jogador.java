package BancoIF;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um jogador no jogo.
 * 
 * <p>
 * Esta classe contém informações sobre o jogador.
 * <ul>
 * <li>Nome</li>
 * <li>Saldo</li>
 * <li>Posição no tabuleiro</li>
 * <li>Propriedades</li>
 * <li>Companhias</li>
 * <li>Estado de falência</li>
 * </ul>
 * </p>
 *
*/

public class Jogador {
    // Atributos
    /**
     * O nome do jogador.
     */
    private String nome;
    /**
     * O saldo do jogador.
     */
    private double saldo;
    /**
     * A posição do jogador no tabuleiro.
     */
    private int posicao;
    /**
     * A lista de propriedades que o jogador possui.
     */
    private List<Propriedade> propriedades;
    /**
     * A lista de companhias que o jogador possui.
     */
    private List<Companhia> companhias;
    /**
     * Indica se o jogador está falido.
     */
    private boolean falido;

    // Construtor
    /**
     * Construtor da classe Jogador.
     * <p>
     * Cria um novo jogador com um nome e saldo inicial de R$ 1500.00.
     * </p>
     * 
     * @param nome O nome do jogador.
     */
    public Jogador(String nome) {
        this.nome = nome;
        this.saldo = 1500;
        this.posicao = 0;
        this.propriedades = new ArrayList<>();
        this.companhias = new ArrayList<>();
        this.falido = false;
    }

    // Metodos
    /**
     * Retorna o nome do jogador.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna o saldo do jogador.
     */
    public double getSaldo() {
        return this.saldo;
    }

    /**
     * Configura uma nova posição no tabuleiro para o jogador.
     */
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    /**
     * Retorna a posição atual do jogador no tabuleiro.
     * @return A posição do jogador.
     */
    public int getPosicao() {
        return this.posicao;
    }

    /**
     * Retorna o estado de falência do jogador.
     * 
     * @return true se o jogador está falido, false caso contrário.
     */
    public boolean getFalido() {
        return this.falido;
    }

    /**
     * Configura o jogador como falido.
     */    
    public void setFalencia() {
        this.falido = true;
    }

    /**
     * Move o jogador para uma nova posição no tabuleiro.
     * 
     * <p>
     * Quando o jogador completa 40 casas, a contagem começa novamente a partir de 0.
     * Caso o jogador complete uma volta no tabuleiro, ele recebe R$ 200.00.
     * </p>
     * 
     * @param casas O número de casas para mover.
     */
    public void mover(int casas) {
        this.posicao += casas;
        if (this.posicao >= 40) {
            this.posicao -= 40;
            this.saldo += 200;
            System.out.println("Jogador " + this.nome + " ganhou R$ 200.00 por completar uma volta no tabuleiro.");
            Jogo.imprimirLinha();
        }
    }

    /**
     * Permite que o jogador compre uma propriedade.
     * 
     * <p>
     * O jogador paga o valor da propriedade e a adiciona à sua lista de propriedades.
     * </p>
     * 
     * @param propriedade A propriedade a ser comprada.
     */
    public void comprarPropriedade(Propriedade propriedade) {
            this.saldo -= propriedade.getPreco();
            this.propriedades.add(propriedade);
            propriedade.setProprietario(this);
    }

    /**
     * Permite que o jogador compre uma companhia.
     * 
     * <p>
     * O jogador paga o valor da companhia e a adiciona à sua lista de companhias.
     * </p>
     * @param companhia A companhia a ser comprada.
     */
    public void comprarCompanhia(Companhia companhia) {
            this.pagar(companhia.getValorCompra());
            this.companhias.add(companhia);
            companhia.setProprietario(this);
    }

    /**
     * Subtrai um valor do saldo do jogador.
     * 
     * <p>
     * Essa função é utilizada para subtrair valores do saldo do jogador, como aluguéis e taxas.
     * </p>
     * @param valor O valor a ser subtraído.
     */
    public void pagar(double valor) {
        this.saldo -= valor;
    }

    /**
     * Adiciona um valor ao saldo do jogador.
     * 
     * <p>
     * Essa função é utilizada para o jogador receber valores durante o jogo, como aluguéis e lucros.
     * </p>
     * @param valor O valor a ser adicionado.
     */
    public void receber(double valor) {
        this.saldo += valor;
    }

    /**
     * Constrói uma pousada em uma propriedade.
     * 
     * <p>
     * O jogador paga o valor da construção e a propriedade que será melhorada.
     * <ul>
     * <li><i>Esta função só pode ser chamada se o jogador possuir saldo suficiente.</i></li>
     * </ul>
     * 
     * </p>
     * @param propriedade A propriedade a ser melhorada.
     */
    public void construirPousada(Propriedade propriedade) {
        this.pagar(propriedade.getAluguel());
        propriedade.setNivelMelhoria(1);
    }

    /**
     * Constrói um hotel em uma propriedade.
     * 
     * <p>
     * O jogador paga o valor da construção e a propriedade que será melhorada.
     * <ul>
     * <li><i>Esta função só pode ser chamada após a construção de uma pousada.</i></li>
     * <li><i>Esta função só pode ser chamada se o jogador possuir saldo suficiente.</i></li>
     * </ul>
     * </p>
     * 
     * @param propriedade A propriedade a ser melhorada.
     */
    public void construirHotel(Propriedade propriedade) {
        this.pagar(propriedade.getAluguelPousada());
        propriedade.setNivelMelhoria(2);
    }

    /**
     * Retorna o estado atual do jogador.
     * 
     * <p>
     * Retorna uma string com o nome do jogador, seu saldo, sua posição no tabuleiro.
     * </p>
     * 
     * @return O nome, o saldo e a posição do jogador.
     */
    public String getEstado() {
        return String.format("Jogador: %s\nSaldo: R$ %.2f\nPosição: %02d", this.nome, this.saldo, this.posicao);
    }

    /**
     * Retorna os dados completos do jogador.
     *
     * <p>
     * Retorna uma string com o nome do jogador, seu saldo, sua posição no tabuleiro,
     * e a lista de propriedades e companhias que possui.
     * </p>
     * 
     * @return O nome, o saldo, a posiçao no tabuleiro e a lista de propriedades e companhias do jogador.
     */
    @Override
    public String toString() {
        return String.format("Jogador: %s\nSaldo: R$ %.2f\nPosição: %02d\nPropriedades: %s\n\nCompanhias: %s", 
                     this.nome, this.saldo, this.posicao, this.propriedades, this.companhias);
    }
}