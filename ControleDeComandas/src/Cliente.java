public class Cliente {
    private String nome;
    @SuppressWarnings("unused")
    private String telefone;
    @SuppressWarnings("unused")
    private String endereco;

    public Cliente(String nome, String telefone, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return this.nome;
    }
}
