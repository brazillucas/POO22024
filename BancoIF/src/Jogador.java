/**
 * 
 * Classe Jogador:
 * Atributos:
 * nome: String (nome do jogador)
 * saldo: double (saldo do jogador)
 * posicao: int (posição atual no tabuleiro)
 * propriedades: List<Propriedade> (lista de propriedades que o jogador possui)
 * companhias: List<Companhia> (lista de companhias que o jogador possui)
 * Métodos:
 * mover(int casas): void (move o jogador um número de casas no tabuleiro)
 * comprarCompanhia(Companhia companhia): void (compra uma companhia)
 * comprarPropriedade(Propriedade propriedade): void (compra uma propriedade)
 * pagarAluguel(double valor): void (paga um aluguel para outro jogador)
 * receber(double valor): void (recebe um valor)
 * construirPousada(Propriedade propriedade): void (constrói uma pousada em uma propriedade)
 * construirHotel(Propriedade propriedade): void (constrói um hotel em uma propriedade)
 * falido(): boolean (verifica se o jogador está falido)
 * getPosicao(): int (retorna a posição atual do jogador)
 * getEstado(): String (retorna o estado atual do jogador: saldo, propriedades e posição)
 * toString(): String (retorna uma representação do jogador e suas propriedas/companhias)
 *
*/
import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private double saldo;
    private int posicao;
    private List<Propriedade> propriedades;
    private List<Companhia> companhias;

    // Construtor
    public Jogador(String nome) {
        this.nome = nome;
        this.saldo = 1500;
        this.posicao = 0;
         this.propriedades = new ArrayList<>();
        this.companhias = new ArrayList<>();
    }

    // Metodos
    public String getNome() {
        return this.nome;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getPosicao() {
        return this.posicao;
    }

    public void mover(int casas) {
        this.posicao += casas;
        if (this.posicao >= 40) {
            this.posicao -= 40;
            this.saldo += 200;
        }
    }

    public void comprarPropriedade(Propriedade propriedade) {
        if (this.saldo >= propriedade.getPreco()) {
            this.saldo -= propriedade.getPreco();
            this.propriedades.add(propriedade);
            propriedade.setProprietario(this);
        } else {
            System.out.println("Saldo insuficiente para comprar a propriedade");
        }
    }

    public void comprarCompanhia(Companhia Companhia) {
        this.saldo -= Companhia.getValorCompra();
        this.companhias.add(Companhia);
        Companhia.setProprietario(this);
    }

    public void pagarAluguel(double valor) {
        this.saldo -= valor;
    }

    public void receber(double valor) {
        this.saldo += valor;
    }

    public void construirPousada(Propriedade propriedade) {
        if (propriedade.getProprietario() == this && propriedade.getNivelMelhoria() == 0 && this.saldo >= propriedade.getAluguelPousada()) {
            this.saldo -= propriedade.getAluguelPousada();
            propriedade.setNivelMelhoria(1);
        } else {
            System.out.println("Não é possível construir uma pousada nesta propriedade");
        }
    }

    public void construirHotel(Propriedade propriedade) {
        if (propriedade.getProprietario() == this && propriedade.getNivelMelhoria() == 1 && this.saldo >= propriedade.getAluguelHotel()) {
            this.saldo -= propriedade.getAluguelHotel();
            propriedade.setNivelMelhoria(2);
        } else {
            System.out.println("Não é possível construir um hotel nesta propriedade");
        }
    }

    public boolean falido() {
        if (this.saldo < 0) {
            return true;
        }
        return false;
    }

    public int rolarDados() {
        Dado dado = new Dado();
        return dado.rolar() + dado.rolar();
    }

    public String getEstado() {
        return "Jogador: " + this.nome + "\nSaldo: " + this.saldo + "\nPosição: " + this.posicao;
    }

    @Override
    public String toString() {
        return "Jogador: " + this.nome + "\nSaldo: " + this.saldo + "\nPosição: " + this.posicao + "\nPropriedades: " + this.propriedades + "\nCompanhias: " + this.companhias;
    }
}