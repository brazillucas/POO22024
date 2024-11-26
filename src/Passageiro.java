import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Passageiro {
    private String nome;
    private String sobrenome;
    private String nomeCompleto;
    private LocalDate dataNascimento;
    private String CPF;
    private int idade;
    private String email;
    private boolean comorbidades;

    // Construtor
    public Passageiro(String nome, String sobrenome, String dataNascimento, String CPF, String email, boolean comorbidades) {
        this.nome = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
        this.sobrenome = sobrenome.substring(0, 1).toUpperCase() + sobrenome.substring(1).toLowerCase();

        // Preenche data de nascimento
        DateTimeFormatter padraoISO = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.dataNascimento = LocalDate.parse(dataNascimento, padraoISO); // Converte e armazena no padrão ISO
        
        this.CPF = CPF;
        this.email = email;
        this.comorbidades = comorbidades;
        this.nomeCompleto = this.nome + " " + this.sobrenome;

        // Calcular idade
        this.idade = LocalDate.now().getYear() - this.dataNascimento.getYear();
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getDataNascimento() {
        DateTimeFormatter padraoBrasil = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.dataNascimento.format(padraoBrasil); // Formata para exibição
    }

    public String getCPF() {
        return this.CPF;
    }

    public int getIdade() {
        return idade;
    }

    public String getEmail() {
        return email;
    }

    public boolean getComorbidades() {
        return comorbidades;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    // Recebe a data em formato DD-MM-YYYY
    public void setDataNascimento(String dataNascimento) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.dataNascimento = LocalDate.parse(dataNascimento, inputFormatter);
    }

    public void setCPF(String novoCPF) {
        this.CPF = novoCPF;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setComorbidades(boolean comorbidades) {
        this.comorbidades = comorbidades;
    }

    // toString
    @Override
    public String toString() {
        return "Passageiro [comorbidades=" + comorbidades +
        ", dataNascimento=" + dataNascimento +
        ", email=" + email +
        ", nome=" + nome +
        ", sobrenome=" + sobrenome +
        ", nomeCompleto=" + nomeCompleto + "]";
    }
}
