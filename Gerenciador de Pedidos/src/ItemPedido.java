/*
 * ItemPedido
 * Essa classe será responsável por armazenar a relação entre um item e sua quantidade em um pedido específico.
 * 
 * Atributos
 * itemId: O ID do item (chave primária no banco de dados).
 * quantidade: A quantidade desse item no pedido.
 * Métodos
 * getItemId(): Retorna o ID do item.
 * getQuantidade(): Retorna a quantidade.
 * setQuantidade(int novaQuantidade): Atualiza a quantidade.
 */

 public class ItemPedido {
    @SuppressWarnings("FieldMayBeFinal")
    private int itemId; // ID do item no banco de dados
    private int quantidade;
    private int numero_pedido;
    private int setorDestino; // Para almoxarifado
    private int funcionarioDestino; // Para uniformes e EPI

    // Construtor
    public ItemPedido(int itemId, int quantidade, int numero_pedido, int setorDestino, int funcionarioDestino) {
        this.itemId = itemId;
        this.quantidade = quantidade;
        this.numero_pedido = numero_pedido;
        this.setorDestino = setorDestino;
        this.funcionarioDestino = funcionarioDestino;
    }

    // Getters e Setters
    public int getItemId() {
        return this.itemId;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getNumeroPedido() {
        return this.numero_pedido;
    }

    public void setNumeroPedido(int numero_pedido) {
        this.numero_pedido = numero_pedido;
    }

    public int getSetorDestino() {
        return this.setorDestino;
    }

    public void setSetorDestino(int setorDestino) {
        this.setorDestino = setorDestino;
    }

    public int getFuncionarioDestino() {
        return this.funcionarioDestino;
    }

    public void setFuncionarioDestino(int funcionarioDestino) {
        this.funcionarioDestino = funcionarioDestino;
    }
}