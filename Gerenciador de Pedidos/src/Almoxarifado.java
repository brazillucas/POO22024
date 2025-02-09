public class Almoxarifado extends Item {
    // Não possui atributos adicionais específicos

    // Construtor
    public Almoxarifado(int codigo, String nome) {
        super(codigo, nome, TipoItem.ALMOXARIFADO);
    }

    // Implementação do método abstrato
    @Override
    public void exibirDetalhes() {
        System.out.println("Almoxarifado - Código: " + getCodigo() + ", Nome: " + getNome());
    }
}