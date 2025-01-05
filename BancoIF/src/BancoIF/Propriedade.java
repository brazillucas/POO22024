package BancoIF;
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
    public void acao(Jogador jogador, int somaDados) {
        // Implementar a ação que ocorre quando um jogador cai nesta propriedade
        if (proprietario == null) {
            // Jogador pode comprar a propriedade
            System.out.println("Propriedade disponível para compra: " + this.getNome());
            System.out.println("Valor da propriedade: R$" + valorCompra);
            // Implementar lógica de compra
            System.out.print("Deseja comprar a propriedade? (s/n) ");
            String escolha = System.console().readLine();

            if (escolha.toLowerCase().equals("s")) {
                // Verificar se o jogador tem saldo suficiente para comprar a propriedade
                if (jogador.getSaldo() >= valorCompra) {
                    jogador.comprarPropriedade(this);
                    System.out.println("Jogador " + jogador.getNome() + " comprou a propriedade " + getNome());
                } else {
                    System.out.println("Saldo insuficiente para comprar a propriedade");
                }
            } else {
                System.out.println("Jogador " + jogador.getNome() + " não comprou a propriedade " + getNome());
            }
        } else if(this.proprietario == jogador) {
            if (this.nivelMelhoria == 2) {
                // Informar que caiu em uma propriedade própria com hotel
                System.out.println("Jogador " + jogador.getNome() + " caiu na sua própria propriedade com hotel: " + getNome() + ".");
                System.out.println("Não é possível construir mais melhorias nesta propriedade");
            } else {
                // Informar que caiu em uma propriedade própria
                System.out.println("Jogador " + jogador.getNome() + " caiu na sua própria propriedade: " + getNome() + ".");
                String entrada;
                do {
                    System.out.println("O nível de melhoria desta propriedade é: " + this.nivelMelhoria);
                    Jogo.imprimirLinha();
                    // Verificar se o jogador deseja construir melhorias na propriedade
                    System.out.println("Deseja construir melhorias na propriedade?");
                    System.out.println("1 - Construir pousada (R$" + aluguelPousada + ")");
                    System.out.println("2 - Construir hotel (R$" + aluguelHotel + ")");
                    System.out.println("0 - Não construir");
                    System.out.print("Escolha uma opção: ");
                    entrada = System.console().readLine();
                } while(!entrada.equals("0") && !entrada.equals("1") && !entrada.equals("2"));
                // Converte a entrada e executa a ação correspondente
                int opcao = Integer.parseInt(entrada);
                switch (opcao) {
                    case 1:
                        jogador.construirPousada(this);
                        break;
                    case 2:
                        jogador.construirHotel(this);
                        break;
                    default:
                        System.out.println("Nenhuma melhoria foi construída");
                        break;
                }
            }
        } else if (this.proprietario != jogador) {
            // Informar que caiu em uma propriedade com dono e que deve pagar aluguel
            System.out.println("Jogador " + jogador.getNome() + " caiu na propriedade " + getNome() + " de " + proprietario.getNome());
            // Jogador deve pagar aluguel
            double valorAluguel = calcularAluguel();
            // Verificar se o jogador tem saldo suficiente para pagar o aluguel
            if (jogador.getSaldo() < valorAluguel) {
                System.out.println("Jogador " + jogador.getNome() + " não tem saldo suficiente para pagar o aluguel de R$" + valorAluguel);
                jogador.setFalencia();
                return;
            }
            // Pagar aluguel ao proprietário
            jogador.pagar(valorAluguel);
            this.proprietario.receber(valorAluguel);
            System.out.println("Jogador " + jogador.getNome() + " pagou R$" + valorAluguel + " de aluguel para " + proprietario.getNome());

            Jogo.imprimirLinha();
            // Mostra o saldo do proprietário
            System.out.println("Novo saldo do proprietário " + proprietario.getNome() + ": R$" + proprietario.getSaldo());
        }
    }

    public double calcularAluguel() {
        switch (nivelMelhoria) {
            case 1:
                return aluguelPousada;
            case 2:
                return aluguelHotel;
            default:
                return aluguel;
        }
    }
}