/*
 * Funcionario
 * Atributos:
 * matricula
 * nome
 * setor (código do setor)
 * funcao (Codigo da função)
 * dataAdmissao
 * lojaTrabalho
 * tamanhoUniforme
 * pedidos (numero do pedido | lista de pedidos contendo itens para este funcionário)
 * Métodos:
 * atualizarDados()
 * listarItensPedidos()
 */

import java.time.LocalDate;
 import java.util.List;

public class Funcionario {
    private int matricula;
    private String nome;
    private int funcao;
    private LocalDate dataAdmissao;
    private int lojaTrabalho;
    private String tamanhoUniforme;
    private int setor;
    private boolean ativo;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Integer> pedidos;

    // Construtores
    public Funcionario(int matricula, String nome, int setor, int funcao, LocalDate dataAdmissao, int lojaTrabalho, String tamanhoUniforme) {
        this.matricula = matricula;
        this.nome = nome;
        this.setor = setor;
        this.funcao = funcao;
        this.dataAdmissao = dataAdmissao;
        this.lojaTrabalho = lojaTrabalho;
        this.tamanhoUniforme = tamanhoUniforme;
        this.ativo = true;
    }

    public Funcionario(int matricula, String nome, int setor, int funcao, LocalDate dataAdmissao, int lojaTrabalho, String tamanhoUniforme, List<Integer> pedidos, boolean ativo) {
        this.matricula = matricula;
        this.nome = nome;
        this.setor = setor;
        this.funcao = funcao;
        this.dataAdmissao = dataAdmissao;
        this.lojaTrabalho = lojaTrabalho;
        this.tamanhoUniforme = tamanhoUniforme;
        this.pedidos = pedidos;
        this.ativo = true;
    }

    // Getters e Setters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSetor() {
        return setor;
    }

    public void setSetor(int setor) {
        this.setor = setor;
    }

    public int getFuncao() {
        return funcao;
    }

    public void setFuncao(int funcao) {
        this.funcao = funcao;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public int getLojaTrabalho() {
        return lojaTrabalho;
    }

    public void setLojaTrabalho(int lojaTrabalho) {
        this.lojaTrabalho = lojaTrabalho;
    }

    public String getTamanhoUniforme() {
        return tamanhoUniforme;
    }

    public void setTamanhoUniforme(String tamanhoUniforme) {
        this.tamanhoUniforme = tamanhoUniforme;
    }

    public List<Integer> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Integer> pedidos) {
        this.pedidos = pedidos;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // Métodos
    public void atualizarDados() {
        // Atualiza os dados do funcionário
    }

    public void listarItensPedidos() {
        for (int pedido : pedidos) {
            System.out.println("Pedido: " + pedido);
        }
    }
}
