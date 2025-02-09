import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        // Criação de um objeto do tipo Funcionario
        Funcionario funcionario = new Funcionario(123, "João", 1, 1, LocalDate.of(2021, 1, 1), "Loja 1", "M", new ArrayList<>(Arrays.asList(1, 2, 3)));

        // Criação de um objeto do tipo Pedido
        Pedido pedido = new Pedido(1, TipoPedido.UNIFORME, new ArrayList<>(Arrays.asList(1, 2, 3)), 1, -1, 1);

        // Adiciona o pedido à lista de pedidos do funcionário
        funcionario.getPedidos().add(pedido.getNumeroPedido());

        // Criação de um objeto do tipo Almoxarifado
        Almoxarifado almoxarifado = new Almoxarifado("001", "Luva de segurança", TipoItem.ALMOXARIFADO);

        // Exibe os detalhes do almoxarifado
        almoxarifado.exibirDetalhes();
        

        System.out.println("Detalhes do Pedido:");
        System.out.println("Número do Pedido: " + pedido.getNumeroPedido());
        System.out.println("Tipo de Pedido: " + pedido.getTipoPedido());
        System.out.println("Itens: " + pedido.getItens());
        System.out.println("Quantidade: " + pedido.getQuantidade());
        System.out.println("Setor Destino: " + pedido.getSetorDestino());
    }
}
