
public class App {
    public static void main(String[] args) {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        Menu menu = new Menu();
        
        String opc;
        menu.exibirMenuIncial();
        opc = Entrada.solicitarEntradaValida("Digite a opção desejada: ", "^[1-2]$", "Opção inválida. Digite apenas números");
        
        while (!opc.equals("2")) {
            switch (opc) {
                case "1":
                    // Acessar o sistema
                    Entrada.limparTela();
                    gerenciador.iniciarSistema();
                    break;
            }
            opc = Entrada.solicitarEntradaValida("Digite a opção desejada: ", "^[1-2]$", "Opção inválida. Digite apenas números");
        }
        
        // Fechar o sistema
        System.out.println("Saindo...");
        Entrada.aguardarEnter();
    }
}
