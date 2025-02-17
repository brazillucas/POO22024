import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        
        SistemaDeLogin sistemaLogin = new SistemaDeLogin();

        Menu menu = new Menu();
        menu.exibirMenuPrincipal();

        String opc = Entrada.solicitarEntradaValida("Digite a opção desejada: ", "^[0-1]$", "Opção inválida. Digite apenas números.");

        while (!opc.equals("2")) {
            switch (opc) {
                case "1":
                    menu.exibirMenuAdministrador();
                    break;
                case "2":
                    System.out.println("Saindo...");
                    break;
            }
            opc = Entrada.solicitarEntradaValida("Digite a opção desejada: ", "^[0-1]$", "Opção inválida. Digite apenas números.");
        }

        // Fechar o sistema
        System.out.println("Saindo do sistema...");
        System.exit(0);


        
        // Adiciona o administrador ao sistema de login
        // sistemaLogin.adicionarAdministrador(administrador);

        // Autentica o usuário
        // administrador = sistemaLogin.autenticar(usuario, senha);
        
        // Cria um gerenciador de pedidos
        // GerenciadorPedidos gerenciador = new GerenciadorPedidos(administrador);
        
        // Cria um setor
        // Setor setor = new Setor(1, "Setor 1");
    }

    public static void iniciarSistema() {
    }
}
