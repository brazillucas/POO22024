import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        // Suponha que temos os seguintes IDs de itens no banco de dados
    int camisaPoloId = 101; // ID de uma camisa polo
    int luvasProtecaoId = 201; // ID de luvas de proteção

    // Criar setor e funcionário
    Setor setorProducao = new Setor(1,"Produção");
    Funcionario funcionarioJoao = new Funcionario(1, "João Silva", 1, FuncaoFuncionario.ACOUGUEIRO, LocalDate.of(2020, 1, 1), 114, "M");

    // Criar um pedido
    Pedido pedido = new Pedido(1, TipoPedido.UNIFORME, LocalDate.now());

    // Adicionar itens ao pedido usando seus IDs
    pedido.adicionarItem(camisaPoloId, 5, -1, funcionarioJoao.getMatricula()); // 5 camisas polo para João
    pedido.adicionarItem(luvasProtecaoId, 10, setorProducao.getId(), -1); // 10 luvas de proteção para o setor Produção



        // // Criação de um objeto do tipo Funcionario
        // Funcionario funcionario = new Funcionario(123, "João", 1, 1, LocalDate.of(2021, 1, 1), "Loja 1", "M", new ArrayList<>(Arrays.asList(1, 2, 3)));

        // // Criação de um objeto do tipo Pedido
        // Pedido pedidoFuncionario = new Pedido(1, TipoPedido.UNIFORME, LocalDate.of(2021, 1, 1));

        // // Adiciona o pedido à lista de pedidos do funcionário
        // funcionario.getPedidos().add(pedidoFuncionario.getNumeroPedido());

        // // Criação de um objeto do tipo Almoxarifado
        // Almoxarifado almoxarifado = new Almoxarifado("001", "Luva de segurança", TipoItem.ALMOXARIFADO);

        // // Exibe os detalhes do almoxarifado
        // almoxarifado.exibirDetalhes();
        

        // System.out.println("Detalhes do Pedido:");
        // System.out.println("Número do Pedido: " + pedido.getNumeroPedido());
        // System.out.println("Tipo de Pedido: " + pedido.getTipoPedido());
        // System.out.println("Itens: " + pedido.getItens());
        // System.out.println("Quantidade: " + pedido.getItens().getQuantidade());
        // System.out.println("Setor Destino: " + pedido.getSetorDestino());
    }
}
