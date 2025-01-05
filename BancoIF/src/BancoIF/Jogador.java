package BancoIF;
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
    private boolean falido;

    // Construtor
    public Jogador(String nome) {
        this.nome = nome;
        this.saldo = 1500;
        this.posicao = 0;
         this.propriedades = new ArrayList<>();
        this.companhias = new ArrayList<>();
        this.falido = false;
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
    
    public boolean falido() {
        return this.falido;
    }

    public void setFalencia() {
        this.falido = true;
    }

    public void mover(int casas) {
        this.posicao += casas;
        if (this.posicao >= 40) {
            this.posicao -= 40;
            this.saldo += 200;
            System.out.println("Jogador " + this.nome + " ganhou R$200 por completar uma volta no tabuleiro.");

        }
    }

    public void comprarPropriedade(Propriedade propriedade) {
            this.saldo -= propriedade.getPreco();
            this.propriedades.add(propriedade);
            propriedade.setProprietario(this);
    }

    public void comprarCompanhia(Companhia companhia) {
            this.saldo -= companhia.getValorCompra();
            this.companhias.add(companhia);
            companhia.setProprietario(this);
    }

    public void pagar(double valor) {
        this.saldo -= valor;
    }

    public void receber(double valor) {
        this.saldo += valor;
    }

    public void construirPousada(Propriedade propriedade) {
        if (this.saldo >= propriedade.getAluguelPousada()){
            if (propriedade.getNivelMelhoria() == 0) {
                this.saldo -= propriedade.getAluguel();
                propriedade.setNivelMelhoria(1);
            } else if (propriedade.getNivelMelhoria() == 1) {
                System.out.println("Já existe uma pousada nesta propriedade");
                System.out.println("Construa um hotel");
            }
        } else {
            System.out.println("Saldo insuficiente para construir uma pousada");
        }
    }

    public void construirHotel(Propriedade propriedade) {
        if (this.saldo >= propriedade.getAluguelHotel()) {
            if (propriedade.getNivelMelhoria() == 1) {
                this.saldo -= propriedade.getAluguelPousada();
                propriedade.setNivelMelhoria(2);
            } else if (propriedade.getNivelMelhoria() == 2) {
                System.out.println("A propriedade já possui um hotel");
            } else {
                System.out.println("Não é possível construir um hotel nesta propriedade");
                System.out.println("Construa uma pousada primeiro");
            }
        } else {
            System.out.println("Saldo insuficiente para construir um hotel");
        }
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