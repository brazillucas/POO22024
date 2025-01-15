package BancoIF;

/**
 * Representa uma propriedade no tabuleiro do jogo Banco Imobiliário.
 * <p>
 * Esta classe herda de {@link Posicao} e adiciona funcionalidades específicas
 * para propriedades, como valores de compra, aluguel, e gerenciamento de
 * melhorias (pousadas e hotéis). Além disso, define um proprietário para a propriedade.
 * </p>
 */

public class Propriedade extends Posicao {
    /**
     * O valor de compra da propriedade.
     */
    private double valorCompra;
    /**
     * O valor base do aluguel.
     */
    private double aluguel;
    /**
     * O valor do aluguel se a propriedade tiver uma pousada.
     */
    private double aluguelPousada;
    /**
     * O valor do aluguel se a propriedade tiver um hotel.
     */
    private double aluguelHotel;
    /**
     * O jogador proprietário da propriedade.
     */
    private Jogador proprietario;
    /**
     * O nível de melhoria da propriedade (0 = sem melhorias, 1 = pousada, 2 = hotel).
     */
    private int nivelMelhoria;

    /**
     * Construtor da classe Propriedade.
     * <p>
     * Inicializa uma propriedade com os valores fornecidos e define o tipo como "Propriedade".
     * O proprietário é inicializado como <code>null</code> e o nível de melhoria como 0.
     * </p>
     *
     * @param nome          O nome da propriedade.
     * @param preco         O valor de compra da propriedade.
     * @param aluguel       O valor base do aluguel.
     * @param aluguelPousada O valor do aluguel com pousada.
     * @param aluguelHotel  O valor do aluguel com hotel.
     */
    public Propriedade(String nome, double preco, double aluguel, double aluguelPousada, double aluguelHotel) {
        super(nome, "Propriedade");
        this.valorCompra = preco;
        this.aluguel = aluguel;
        this.aluguelPousada = aluguelPousada;
        this.aluguelHotel = aluguelHotel;
        this.proprietario = null;
        this.nivelMelhoria = 0;
    }

    /**
     * Retorna o valor de compra da propriedade.
     * 
     * @return O valor de compra da propriedade.
     */
    public double getPreco() {
        return this.valorCompra;
    }

    /**
     * Define o valor de compra da propriedade.
     * 
     * @param preco O valor de compra da propriedade.
     */
    public void setPreco(int preco) {
        this.valorCompra = preco;
    }

    /**
     * Retorna o valor base do aluguel da propriedade.
     * 
     * @return O valor base do aluguel da propriedade.
     */
    public double getAluguel() {
        return this.aluguel;
    }

    /**
     * Define o valor base do aluguel da propriedade.
     * 
     * @param aluguel O valor base do aluguel da propriedade.
     */
    public void setAluguel(double aluguel) {
        this.aluguel = aluguel;
    }

    /**
     * Retorna o valor do aluguel se a propriedade possuir uma pousada.
     * 
     * @return O valor do aluguel com pousada.
     */
    public double getAluguelPousada() {
        return this.aluguelPousada;
    }

    /**
     * Define o valor do aluguel se a propriedade possuir uma pousada.
     * 
     * @param aluguelPousada O valor do aluguel com pousada.
     */
    public void setAluguelPousada(double aluguelPousada) {
        this.aluguelPousada = aluguelPousada;
    }

    /**
     * Retorna o valor do aluguel se a propriedade possuir um hotel
     * 
     * @return O valor do aluguel com hotel.
     */
    public double getAluguelHotel() {
        return this.aluguelHotel;
    }

    /**
     * Define o valor do aluguel se a propriedade possuir um hotel.
     * 
     * @param aluguelHotel O valor do aluguel com hotel.
     */
    public void setAluguelHotel(double aluguelHotel) {
        this.aluguelHotel = aluguelHotel;
    }

    /**
     * Retorna o jogador proprietário da propriedade.
     * 
     * @return O jogador proprietário da propriedade.
     */
    public Jogador getProprietario() {
        return this.proprietario;
    }

    /**
     * Define o jogador proprietário da propriedade.
     * 
     * @param proprietario O jogador proprietário da propriedade.
     */
    public void setProprietario(Jogador proprietario) {
        this.proprietario = proprietario;
    }

    /**
     * Define o nível de melhoria da propriedade.
     * 
     * @param nivel O novo nível de melhoria da propriedade.
     */
    public void setNivelMelhoria(int nivel) {
        this.nivelMelhoria = nivel;
    }

    /**
     * Retorna o nível de melhoria da propriedade.
     * 
     * @return O nível de melhoria da propriedade atual.
     */
    public int getNivelMelhoria() {
        return this.nivelMelhoria;
    }

    /**
     * Calcula qual o valor de aluguel a ser pago na propriedade.
     * 
     * <p>
     * O método verifica o nível de melhoria da propriedade e retorna o valor
     * correspondente de aluguel.
     * </p>
     * 
     * @return O valor do aluguel a ser pago na propriedade.
     */
    public double calcularAluguel() {
        switch (this.nivelMelhoria) {
            case 1:
                return this.aluguelPousada;
            case 2:
                return this.aluguelHotel;
            default:
                return this.aluguel;
        }
    }

    /**
     * Pegunta o jogador se deseja construir melhorias na propriedade.
     * 
     * <p>
     * Se o jogador deseja construir melhorias, ele pode escolher entre construir
     * uma pousada ou um hotel.
     * </p>
     * <p>
     * O método verifica se o jogador tem saldo suficiente
     * para construir a melhoria e, em caso positivo, realiza a construção.
     * </p>
     * @param jogador O jogador proprietário da propriedade.
     */
    private void realizarMelhorias(Jogador jogador) {
        // Informa ao jogador que caiu em uma propriedade que ele é o dono.
        System.out.println("Jogador " + jogador.getNome() + " caiu na sua própria propriedade: " + this.getNome() + ".");

        if (this.nivelMelhoria == 2) 
        {
            System.out.println("A propriedade já possui um hotel");
            System.out.println("Não é possível construir mais melhorias nesta propriedade");
        } else
        {
            
            do {
                // Utiliza operador ternário para exibir o nível de melhoria da propriedade
                // Se o nível de melhoria for 0, exibe "Básico", senão exibe "Pousada"
                String nivelMelhoriaStr = (this.nivelMelhoria == 0) ? "Básico" : "Pousada";
                
                // Informa ao jogador o nível de melhoria atual da propriedade
                System.out.println("O nível de melhoria desta propriedade é: " + nivelMelhoriaStr);
                Jogo.imprimirLinha();
                // Imprime a lista de melhorias disponíveis
                System.out.println("Deseja construir melhorias na propriedade?");
                System.out.println("1 - Construir pousada (R$" + this.aluguelPousada + ")");
                System.out.println("2 - Construir hotel (R$" + this.aluguelHotel + ")");
                System.out.println("0 - Não construir");

                // Verifica se o jogador deseja construir melhorias na propriedade
                // Converte a entrada e executa a ação correspondente
                int opcao = Integer.parseInt(Jogo.solicitarEntradaValida("Escolha uma opção: ", "^[0-2]$", "Opção inválida"));
                Jogo.imprimirLinha();

                if (opcao == 0) {
                    System.out.println("Nenhuma melhoria foi construída");
                    Jogo.aguardarEnter();
                    return;
                } else if (this.nivelMelhoria == 0 && opcao == 2) {
                    System.out.println("Ainda não é possível construir um hotel nesta propriedade");
                    System.out.println("Construa uma pousada primeiro");
                    Jogo.imprimirLinha();
                    System.out.println("O menu será exibido novamente. Escolha uma opção válida.");
                    Jogo.aguardarEnter();
                    Jogo.limparTela();
                } else if (opcao == 1) {
                    if (jogador.getSaldo() < this.aluguelPousada) {
                        System.out.println("Saldo insuficiente para construir uma pousada");
                        Jogo.aguardarEnter();
                        return;
                    } else if (this.nivelMelhoria == opcao) {
                        System.out.println("A propriedade já possui uma pousada");
                        System.out.println("Construa um hotel para melhorar a propriedade");
                        Jogo.imprimirLinha();
                        System.out.println("O menu será exibido novamente. Escolha uma opção válida.");
                        Jogo.aguardarEnter();
                        Jogo.limparTela();
                        continue;
                    }
                    jogador.construirPousada(this);
                    System.out.println("Pousada construída com sucesso!");
                    return;
                } else {
                    if (jogador.getSaldo() < this.aluguelHotel) {
                        System.out.println("Saldo insuficiente para construir um hotel");
                        Jogo.aguardarEnter();
                        return;
                    }
                    jogador.construirHotel(this);
                    System.out.println("Hotel construído com sucesso!");
                    return;
                }
            } while(true);
        }
    }

    /**
     * Solicita o pagamento de aluguel ao jogador.
     * 
     * <p>
     * O método calcula o valor do aluguel e solicita o pagamento ao jogador.
     * Caso o jogador não tenha saldo suficiente para pagar o aluguel, ele é declarado
     * falido.
     * </p>
     * @param jogador O jogador que caiu na propriedade.
     */
    private void solicitarAluguel(Jogador jogador) {
        // Informar que caiu em uma propriedade com dono e que deve pagar aluguel
        System.out.println("Jogador " + jogador.getNome() + " caiu na propriedade " + getNome() + " de " + proprietario.getNome());
        // Jogador deve pagar aluguel
        double valorAluguel = calcularAluguel();
        // Verificar se o jogador tem saldo suficiente para pagar o aluguel
        if (jogador.getSaldo() < valorAluguel) {
            System.out.printf("Jogador %s não tem saldo suficiente para pagar o aluguel de R$ %.2f\n", jogador.getNome(), valorAluguel);
            jogador.setFalencia();
            return;
        }
        // Pagar aluguel ao proprietário
        jogador.pagar(valorAluguel);
        this.proprietario.receber(valorAluguel);
        System.out.printf("Jogador %s pagou R$ %.2f de aluguel para %s.\n", jogador.getNome(), valorAluguel, this.proprietario.getNome());

        Jogo.imprimirLinha();
        // Mostra o saldo do proprietário
        System.out.printf("Novo saldo do proprietário %s: R$ %.2f.\n", this.proprietario.getNome(), this.proprietario.getSaldo());
    }

    //@Override
    /**
     * Executa as ações que ocorrem quando um jogador cai em uma propriedade.
     * <ul>
     * <li>Se a propriedade não tem proprietário, o jogador pode comprá-la.</li>
     * <li>Se a propriedade tem o jogador como proprietário, ele pode construir melhorias.</li>
     * <li>Se a propriedade tem outro jogador como proprietário, o jogador deve pagar aluguel.</li>
     * </ul>
     * 
     * @param jogador   O jogador que caiu na propriedade.
     */
    @Override
    public void acao(Jogador jogador) {
        // Verifica se a propriedade está disponível para compra
        if (this.proprietario == null) {
            // Jogador pode comprar a propriedade
            System.out.println("Propriedade disponível para compra: " + this.getNome());
            System.out.printf("Valor da propriedade: R$ %.2f.\n", this.valorCompra);
            
            // Verifica se o jogador quer comprar a propriedade
            String escolha = Jogo.solicitarEntradaValida("Deseja comprar a propriedade? (S/N): ", "[sS|nN]", "Opção inválida");
            Jogo.imprimirLinha();

            if (escolha.equalsIgnoreCase("S")) {
                // Verifica se o jogador tem saldo suficiente para comprar a propriedade
                if (jogador.getSaldo() >= this.valorCompra) {
                    jogador.comprarPropriedade(this);
                    System.out.println("Jogador " + jogador.getNome() + " comprou a propriedade: " + this.getNome());
                } else {
                    System.out.println("Saldo insuficiente para comprar a propriedade");
                }
            } else {
                System.out.println("Jogador " + jogador.getNome() + " não comprou a propriedade: " + this.getNome());
            }
        } else if(this.proprietario == jogador) {
            // Oferece ao jogador a possibilidade de construir melhorias na propriedade
            realizarMelhorias(jogador);
        } else if (this.proprietario != jogador) {
            // Solicita o pagamento de aluguel ao jogador
            solicitarAluguel(jogador);
        }
    }
}