import java.util.List;

public class Funcoes {
    private int codigo;
    private String nome;

    // Construtor
    public Funcoes(int codigo, String descricao) {
        this.codigo = codigo;
        this.nome = descricao;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String descricao) {
        this.nome = descricao;
    }

    // Método para buscar uma função pelo código
    public static Funcoes getByCodigo(int codigo, List<Funcoes> funcoes) {
        for (Funcoes funcao : funcoes) {
            if (funcao.getCodigo() == codigo) {
                return funcao;
            }
        }
        throw new IllegalArgumentException("Código de função inválido: " + codigo);
    }
}