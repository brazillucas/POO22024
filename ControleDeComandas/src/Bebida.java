public class Bebida extends Produto {
    @SuppressWarnings("unused")
    private final double volume;
    @SuppressWarnings("unused")
    private final boolean alcolica;

    public Bebida(double volume, boolean alcolica, double valorUnit, String nome) {
        super(valorUnit, nome);
        this.volume = volume;
        this.alcolica = alcolica;
    }

}
