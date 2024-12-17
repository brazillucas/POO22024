public class Bebida extends Produto {
    private double volume;
    private boolean alcolica;

    public Bebida(double volume, boolean alcolica, double valorUnit, String nome) {
        super(valorUnit, nome);
        this.volume = volume;
        this.alcolica = alcolica;
    }



}
