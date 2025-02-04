+-----------------------------+
|         <<abstract>>        |
|          Usuario            |
+-----------------------------+
| - nome: String              |
| - email: String             |
| - senha: String             |
| - TipoUsuario: int          |
| - emprestimosRealizados: List<Emprestimo> |
| - bloqueado: boolean        |
+-----------------------------+
| + login(String, String): boolean |
| + logout(): void            |
| + verificarBloqueio(): boolean |
| + validacaoEmprestimo(ObraLiteraria): boolean |
| + verificarLimiteEmprestimos(): boolean |
| + getNome(): String         |
| + getEmail(): String        |
| + getSenha(): String        |
| + getTipoUsuario(): int     |
| + getEmprestimosRealizados(): List<Emprestimo> |
| + exibirObrasEmprestadas(): void |
| + estaBloqueado(): boolean  |
| + setBloqueado(boolean): void |
| + setNome(String): void     |
| + setEmail(String): void    |
| + setSenha(String): void    |
+-----------------------------+
          ▲
          |
+-----------------------------+
|          Aluno              |
+-----------------------------+
| - matricula: String         |
| - curso: String             |
+-----------------------------+
| + getMatricula(): String    |
| + getCurso(): String        |
+-----------------------------+

          ▲
          |
+-----------------------------+
|         Professor           |
+-----------------------------+
| - departamento: String      |
+-----------------------------+
| + getDepartamento(): String |
+-----------------------------+

          ▲
          |
+-----------------------------+
|      Bibliotecario          |
+-----------------------------+
| - telefone: String          |
| - devolucoesRealizadas: int |
+-----------------------------+
| + getTelefone(): String     |
| + setTelefone(String): void |
| + getDevolucoesRealizadas(): int |
| + incrementaDevolucoesRealizadas(): void |
| + cadastrarUsuario(Biblioteca): void |
| + gerarRelatorios(Biblioteca): void |
+-----------------------------+

+-----------------------------+
|       ObraLiteraria         |
+-----------------------------+
| - id: int                   |
| - titulo: String            |
| - autor: String             |
| - quantidadeDisponivel: int |
| - quantidadeTotal: int      |
+-----------------------------+
| + consultarInformacoes(): String |
| + getQuantidadeDisponivel(): int |
| + atualizarQuantidade(int): void |
| + getId(): int              |
| + getTitulo(): String       |
| + getAutor(): String        |
| + setId(int): void          |
| + setTitulo(String): void   |
| + setAutor(String): void    |
| + setQuantidadeTotal(int): void |
| + getQuantidadeTotal(): int |
+-----------------------------+

+-----------------------------+
|         Emprestimo          |
+-----------------------------+
| - usuario: Usuario          |
| - obra: ObraLiteraria       |
| - dataEmprestimo: LocalDate |
| - dataDevolucaoPrevista: LocalDate |
| - dataDevolucaoRealizada: LocalDate |
| - emprestimoAtivo: boolean  |
| - atrasado: boolean         |
+-----------------------------+
| + verificarAtraso(): boolean|
| + getObra(): ObraLiteraria  |
| + getUsuario(): Usuario     |
| + getDataEmprestimo(): LocalDate |
| + getAtrasado(): boolean    |
| + setAtrasado(boolean): void|
| + getDataDevolucaoPrevista(): LocalDate |
| + setDevolucaoRealizada(): void |
| + getDataDevolucaoRealizada(): LocalDate |
| + setUsuario(Usuario): void |
| + setObra(ObraLiteraria): void |
| + setDataEmprestimo(LocalDate): void |
| + setDataDevolucaoPrevista(LocalDate): void |
| + getEmprestimoAtivo(): boolean |
| + setEmprestimoAtivo(boolean): void |
| + getDiasAtraso(): int      |
| + toString(): String        |
+-----------------------------+

+-----------------------------+
|         Biblioteca          |
+-----------------------------+
| - usuarios: List<Usuario>   |
| - usuarioLogado: Usuario    |
| - obras: List<ObraLiteraria>|
| - emprestimos: List<Emprestimo> |
+-----------------------------+
| + carregarDados(): void     |
| + salvarDados(): void       |
| + loginUsuario(String, String): Usuario |
| + logoutUsuario(): void     |
| + cadastrarUsuario(Usuario): void |
| + realizarEmprestimo(ObraLiteraria, Usuario): void |
| + realizarDevolucao(ObraLiteraria, Usuario): void |
| + exibirObrasDisponiveis(): void |
| + consultarObra(String): void |
| + consultarObra(int): void  |
| + selecionarObra(): ObraLiteraria |
| + buscarObra(int): ObraLiteraria |
| + getEmprestimosAtivos(): boolean |
| + getUsuariosComAtraso(): boolean |
| + gerarRelatorioObrasEmprestadas(): void |
| + gerarRelatorioUsuariosComAtraso(): void |
| + fechamentoSistema(): void |
| + salvarEmprestimosArquivo(): void |
| + salvarUsuariosArquivo(): void |
+-----------------------------+

+-----------------------------+
|           Menu              |
+-----------------------------+
+-----------------------------+
| + exibirMenuPrincipal(): void |
| + exibirMenuUsuario(Usuario): void |
| + exibirMenuBibliotecario(Usuario): void |
+-----------------------------+

+-----------------------------+
|          Entrada            |
+-----------------------------+
+-----------------------------+
| + solicitarEntradaValida(String, String, String): String |
| + solicitarSenha(): String  |
| + solicitarEmail(): String  |
| + limparTela(): void        |
| + aguardarEnter(): void     |
| + finalizarFuncao(): void   |
+-----------------------------+