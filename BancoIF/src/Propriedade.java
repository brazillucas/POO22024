/**
 * Classe Propriedade (Herda de Posicao):
 * Atributos:
 * valorCompra: double (valor de compra da propriedade)
 * aluguel: double (valor base do aluguel)
 * aluguelPousada: double (valor do aluguel com pousada)
 * aluguelHotel: double (valor do aluguel com hotel)
 * proprietario: Jogador (jogador proprietário da propriedade)
 * nivelMelhoria: int (nível de melhoria: 0 = sem melhoria, 1 = pousada, 2 = hotel)
 * Métodos:
 * calcularAluguel(): double (calcula o valor do aluguel com base nas melhorias)
 * getProprietario(): Jogador (retorna o proprietário da propriedade)
 * setProprietario(Jogador jogador): void (define o proprietário da propriedade)
 * getValorCompra(): double (retorna o valor de compra da propriedade)
 * getNivelMelhoria(): int (retorna o nível de melhoria da propriedade)
 * setNivelMelhoria(int nivel): void (define o nível de melhoria da propriedade)
*/

public class Propriedade extends Posicao {
    private double valorCompra;
    private double aluguel;
    private double aluguelPousada;
    private double aluguelHotel;
    private Jogador proprietario;
    private int nivelMelhoria;


    public Propriedade(String nome, double preco, double aluguel, double aluguelPousada, double aluguelHotel) {
        super(nome, "Propriedade");
        this.valorCompra = preco;
        this.aluguel = aluguel;
        this.aluguelPousada = aluguelPousada;
        this.aluguelHotel = aluguelHotel;
        this.proprietario = null;
        this.nivelMelhoria = 0;
    }

    public double getPreco() {
        return valorCompra;
    }

    public void setPreco(int preco) {
        this.valorCompra = preco;
    }

    public double getAluguel() {
        return this.aluguel;
    }

    public void setAluguel(double aluguel) {
        this.aluguel = aluguel;
    }

    public double getAluguelPousada() {
        return this.aluguelPousada;
    }

    public void setAluguelPousada(double aluguelPousada) {
        this.aluguelPousada = aluguelPousada;
    }

    public double getAluguelHotel() {
        return this.aluguelHotel;
    }

    public void setAluguelHotel(double aluguelHotel) {
        this.aluguelHotel = aluguelHotel;
    }

    public Jogador getProprietario() {
        return proprietario;
    }

    public void setProprietario(Jogador proprietario) {
        this.proprietario = proprietario;
    }

    public void setNivelMelhoria(int nivel) {
        this.nivelMelhoria = nivel;
    }

    public int getNivelMelhoria() {
        return this.nivelMelhoria;
    }

    //@Override
    public void acao(Jogador jogador) {
        // Implementar a ação que ocorre quando um jogador cai nesta propriedade
    }
}