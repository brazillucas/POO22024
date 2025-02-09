public enum FuncaoFuncionario {
    ACOUGUEIRO(1, "Açougueiro(a)"),
    BALCONISTA_DE_ACOUGUE(2, "Balconista de Açougue"),
    AUXILIAR_DE_PADARIA(3, "Auxiliar de Padaria"),
    ATENDENTE_DE_FRIOS(4, "Atendente de Frios"),
    OPERADOR_DE_CAMARA(5, "Operador de Câmara"),
    ENCARREGADO_DE_FRIOS(6, "Encarregado(a) de Frios"),
    REPOSITOR(7, "Repositor(a)"),
    AUXILIAR_DE_DEPOSITO(8, "Auxiliar de Depósito"),
    FISCAL_DE_LOJA(9, "Fiscal de Loja"),
    ENCARREGADO_DE_DEPOSITO(10, "Encarregado de Depósito"),
    CONFERENTE(11, "Conferente"),
    ENCARREGADO_DE_HORTIFRUTI(12, "Encarregado(a) de Hortifruti"),
    ENCARREGADO_DE_LOJA(13, "Encarregado(a) de Loja"),
    EMBALADOR(14, "Embalador(a)"),
    AUX_REPOS_MERCADORIAS(15, "Auxiliar de Reposição de Mercadorias"),
    APRENDIZ_VENDEDOR(16, "Aprendiz de Vendedor de Comércio Varejista"),
    AUXILIAR_DE_LOJA(17, "Auxiliar de Loja"),
    AGENTE_PREVENCAO_PERDAS(18, "Agente de Prevenção de Perdas"),
    ORIENTADOR_TRAFEGO_ESTACIONAMENTO(19, "Orientador de Tráfego para Estacionamento");

    private final int codigo;
    private final String descricao;

    // Construtor do Enum
    FuncaoFuncionario(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    // Método para obter o código da função
    public int getCodigo() {
        return codigo;
    }

    // Método para obter a descrição da função
    public String getDescricao() {
        return descricao;
    }

    // Método estático para buscar uma função pelo código
    public static FuncaoFuncionario getByCodigo(int codigo) {
        for (FuncaoFuncionario funcao : values()) {
            if (funcao.getCodigo() == codigo) {
                return funcao;
            }
        }
        throw new IllegalArgumentException("Código de função inválido: " + codigo);
    }
}