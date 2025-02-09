public class Almoxarifado extends Item {
    // Não possui atributos adicionais específicos

    // Construtor
    public Almoxarifado(String codigo, String descricao, TipoItem tipo) {
        super(codigo, descricao, tipo);
    }

    // Implementação do método abstrato
    @Override
    public void exibirDetalhes() {
        System.out.println("Almoxarifado - Código: " + codigo + ", Descrição: " + descricao);
    }
}