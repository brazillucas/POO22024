package BancoIF;
/**
 * Classe SorteReves (Herda de Posicao):
 * Atributos:
 * valorMinimoGanho: int (valor mínimo que o jogador pode ganhar)
 * valorMaximoGanho: int (valor máximo que o jogador pode ganhar)
 * valorMinimoPerda: int (valor mínimo que o jogador pode perder)
 * valorMaximoPerda: int (valor máximo que o jogador pode perder)
 * Métodos:
 * sortearGanho(): double (sorteia um valor que o jogador vai ganhar ou perder)
 * sortearPerda(): double (sorteia um valor que o jogador vai perder)
 * sortearSorteReves(): String (sorteia uma carta de Sorte/Reves)
 * getValorMinimoGanho(): int (retorna o valor mínimo que o jogador pode ganhar)
 * getValorMaximoGanho(): int (retorna o valor máximo que o jogador pode ganhar)
 * getValorMinimoPerda(): int (retorna o valor mínimo que o jogador pode perder)
 * getValorMaximoPerda(): int (retorna o valor máximo que o jogador pode perder)
 */

public class SorteReves {

    // Atributos
    private int valorMinimoGanho;
    private int valorMaximoGanho;
    private int valorMinimoPerda;
    private int valorMaximoPerda;

    // Construtor

    public SorteReves() {
        this.valorMinimoGanho = 1;
        this.valorMaximoGanho = 150;
        this.valorMinimoPerda = 1;
        this.valorMaximoPerda = 80;
    }

    // Métodos
    private double sortearGanho() {
        int valor = (int) (Math.random() * (valorMaximoGanho - valorMinimoGanho + 1) + valorMinimoGanho);
        return valor;
    }

    private double sortearPerda() {
        int valor = (int) (Math.random() * (valorMaximoPerda - valorMinimoPerda + 1) + valorMinimoPerda);
        return valor * -1;
    }

    public double sortearSorteReves() {
        double sorteio = Math.random();
        if (sorteio < 0.5) {
            return this.sortearGanho();
        } else {
            return this.sortearPerda();
        }
    }
}
