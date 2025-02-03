/**
 * 1. Classe ObraLiteraria
    Responsabilidade: Representa uma obra literária disponível na biblioteca.

    Atributos:
    id (int): Identificador único da obra.
    titulo (String): Título da obra.
    autor (String): Autor da obra.
    quantidadeDisponivel (int): Quantidade de exemplares disponíveis para empréstimo.
    quantidadeTotal (int): Quantidade total de exemplares da obra.

    Métodos:
    consultarInformacoes(): Retorna informações detalhadas sobre a obra.
    atualizarQuantidade(int quantidade): Atualiza a quantidade disponível após empréstimos ou devoluções.
    */

public class ObraLiteraria {

    private int id;
    private String titulo;
    private String autor;
    private int quantidadeDisponivel;
    private int quantidadeTotal;

    public ObraLiteraria(int id, String titulo, String autor, int quantidadeTotal) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = quantidadeTotal;
    }

    public ObraLiteraria(int id, String titulo, int quantidadeTotal) {
        this.id = id;
        this.titulo = titulo;
        this.autor = "Autor Desconhecido";
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = quantidadeTotal;
    }

    public String consultarInformacoes() {
        return "ID: " + id + "\nTítulo: " + titulo + "\nAutor: " + autor + "\nQuantidade disponível: " + quantidadeDisponivel + "\nQuantidade total: " + quantidadeTotal;
    }

    public void atualizarQuantidade(int quantidade) {
        quantidadeDisponivel += quantidade;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

}
