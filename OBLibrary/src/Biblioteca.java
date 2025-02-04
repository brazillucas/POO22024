
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Usuario> usuarios;
    private Usuario usuarioLogado;
    private List<ObraLiteraria> obras;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.usuarios = new ArrayList<>();
        this.obras = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void carregarDados() {
        // Código para carregar os dados do sistema a partir de arquivos .txt
        lerArquivoUsuarios();

        // Carregamento de obras
        lerAcervoObras();

        // Carregamento de empréstimos
        getArquivoEmprestimos();
    }

    private void getArquivoEmprestimos() {
        String linha;
        File arquivoEmprestimos = new File("OBLibrary\\src\\dados\\emprestimos.txt");

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoEmprestimos))) {
            // Formato de data
            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Pular a primeira linha
            leitor.readLine();
            // Leitura das linhas do arquivo
            while ((linha = leitor.readLine()) != null) {
                String[] dadosEmprestimo = linha.split(";");
                
                // Verificar se a linha contém o número esperado de campos
                if (dadosEmprestimo.length < 4) {
                    System.err.println("Linha inválida: " + linha);
                    continue;
                }

                String emailUsuario = dadosEmprestimo[0];
                int idObra = Integer.parseInt(dadosEmprestimo[1]);
                LocalDate dataEmprestimo = LocalDate.parse(dadosEmprestimo[2], formatoData);
                LocalDate dataDevolucaoRealizada = null;
                if (!dadosEmprestimo[3].equals("null")) {
                    dataDevolucaoRealizada = LocalDate.parse(dadosEmprestimo[3], formatoData);
                }

                Usuario usuarioTemp = null;
                ObraLiteraria obraTemp = null;
                for (Usuario usuarioAtual : usuarios) {
                    if (usuarioAtual.getEmail().equals(emailUsuario)) {
                        usuarioTemp = usuarioAtual;
                        break;
                    }
                }
                for (ObraLiteraria obraAtual : obras) {
                    if (obraAtual.getId() == idObra) {
                        obraAtual.atualizarQuantidade(-1);
                        obraTemp = obraAtual;
                        break;
                    }
                }
                Emprestimo novoEmprestimo = new Emprestimo(usuarioTemp, obraTemp, dataEmprestimo, dataDevolucaoRealizada);
                this.emprestimos.add(novoEmprestimo);

                for(Usuario usuarioAtual : usuarios) {
                    if(usuarioAtual.getEmail().equals(emailUsuario)) {
                        usuarioAtual.getEmprestimosRealizados().add(novoEmprestimo);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de empréstimos não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro de leitura.");
        }
    }

    private void lerAcervoObras() {
        String linha;
        File arquivoObras = new File("OBLibrary\\src\\dados\\acervo.csv");

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoObras))) {
            leitor.readLine();
            while ((linha = leitor.readLine()) != null) {
                String[] dadosObra = linha.split(",");
                int id = Integer.parseInt(dadosObra[0]);
                String titulo = dadosObra[1];
                int quantidade = Integer.parseInt(dadosObra[2]);
                ObraLiteraria obra = new ObraLiteraria(id, titulo, "Autor Desconhecido", quantidade);
                this.obras.add(obra);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo do acervo de livros não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro de leitura.");
        }
    }

    private void lerArquivoUsuarios() {
        String linha;
        File arquivoUsuarios = new File("OBLibrary\\src\\dados\\usuarios.txt");
        // Leitura dos arquivos e carregamento dos dados
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoUsuarios))) {
            leitor.readLine();
            while ((linha = leitor.readLine()) != null) {
                String[] dadosUsuario = linha.split(";");
                switch (dadosUsuario[0]) {
                    case "1":
                        String nomeAluno = dadosUsuario[1];
                        String emailAluno = dadosUsuario[2];
                        String senhaAluno = dadosUsuario[3];
                        String matricula = dadosUsuario[4];
                        String curso = dadosUsuario[5];
                        Usuario aluno = new Aluno(nomeAluno, emailAluno, senhaAluno, matricula, curso);
                        this.usuarios.add(aluno);
                        break;
                    case "2":
                        String nomeProfessor = dadosUsuario[1];
                        String emailProfessor = dadosUsuario[2];
                        String senhaProfessor = dadosUsuario[3];
                        String departamento = dadosUsuario[4];
                        Usuario professor = new Professor(nomeProfessor, emailProfessor, senhaProfessor, departamento);
                        this.usuarios.add(professor);
                        break;
                    case "3":
                        String nomeBibliotecario = dadosUsuario[1];
                        String emailBibliotecario = dadosUsuario[2];
                        String senhaBibliotecario = dadosUsuario[3];
                        String telefone = dadosUsuario[4];
                        int devolucoesRealizadas = Integer.parseInt(dadosUsuario[5]);
                        Usuario bibliotecario = new Bibliotecario(nomeBibliotecario, emailBibliotecario, senhaBibliotecario, telefone, devolucoesRealizadas);
                        this.usuarios.add(bibliotecario);
                        break;
                    default:
                        System.out.println("Tipo de usuário desconhecido: " + dadosUsuario[0]);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de usuários não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro de leitura.");
        }
    }

    public Usuario loginUsuario(String email, String senha) {
        for (Usuario usuarioTemp : usuarios) {
            if (usuarioTemp.login(email, senha)) {
                this.usuarioLogado = usuarioTemp;
                return usuarioTemp;
            }
        }
        return null;
    }

    public void realizarLogin(Menu menu) {
        String email = Entrada.solicitarEmail();
        String senha = Entrada.solicitarSenha();

        Usuario usuario = this.loginUsuario(email, senha);
        int opcao;

        if (usuario == null) {
            System.out.println("E-mail ou senha incorretos.");
            return;
        } else {
            System.out.println("Login efetuado com sucesso.");
        }

        while (this.getUsuarioLogado() != null) {
            Entrada.finalizarFuncao();
            if (usuario instanceof Aluno || usuario instanceof Professor) {

                menu.exibirMenuUsuario(usuario);
                opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Escolha uma opção: ", "^[0-5]$", "Opção inválida"));

                switch (opcao) {
                    case 0:
                        this.logoutUsuario();
                        break;
                    case 1: {
                        if (!usuario.verificarLimiteEmprestimos()) {
                            break;
                        }
                        ObraLiteraria obra = this.selecionarObra();
                        if (obra != null) {
                            this.realizarEmprestimo(obra, this.getUsuarioLogado());
                        } else {
                            System.out.println("Obra não encontrada.");
                            System.out.println("Retornando ao menu...");
                        }
                        break;
                    }
                    case 2:
                        this.getUsuarioLogado().exibirObrasEmprestadas();
                        break;
                    case 3:
                        if (usuario.verificarBloqueio()) {
                            System.out.println("Usuário bloqueado por atraso.");
                        } else {
                            System.out.println("Usuário não está bloqueado.");
                        }
                        break;
                    case 4:
                        this.executarConsulta();
                        break;
                    default:
                        break;
                }

            } else if (usuario instanceof Bibliotecario bibliotecario) {

                menu.exibirMenuBibliotecario(usuario);

                opcao = Integer.parseInt(Entrada.solicitarEntradaValida("Escolha uma opção: ", "^[0-4]$", "Opção inválida"));

                switch (opcao) {
                    case 0:
                        this.logoutUsuario();
                        break;
                    case 1:
                        bibliotecario.cadastrarUsuario(this);
                        break;
                    case 2:
                        this.getUsuarios().stream()
                            .filter(u -> u.getTipoUsuario() != 3)
                            .forEach(Usuario::exibirObrasEmprestadas);

                        int idObra = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID da obra que deseja registrar a devolução: ", "^[0-9]+$", "ID inválido"));
                        ObraLiteraria obra = this.buscarObra(idObra);

                        this.getUsuarios().stream()
                            .filter(u -> u.getTipoUsuario() != 3)
                            .forEach(u -> System.out.println("Nome: " + u.getNome() + " | E-mail: " + u.getEmail()));

                        String emailUsuario = Entrada.solicitarEmail();
                        Usuario usuarioDevolucao = null;
                        for (Usuario usuarioAtual : usuarios) {
                            if (usuarioAtual.getEmail().equals(emailUsuario)) {
                                usuarioDevolucao = usuarioAtual;
                                break;
                            }
                        }

                        if(usuarioDevolucao == null) {
                            System.out.println("Usuário não encontrado.");
                            break;
                        }

                        if (obra != null) {
                            this.realizarDevolucao(obra, usuarioDevolucao);

                        } else {
                            System.out.println("Obra não encontrada.");
                            System.out.println("Retornando ao menu...");
                        }
                        break;
                    case 3:
                        System.out.println("Gerando relatórios...");
                        bibliotecario.gerarRelatorios(this);
                        break;
                    case 4:
                        this.executarConsulta();
                    default:
                        break;
                }
            }
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void realizarDevolucao(ObraLiteraria obra, Usuario usuario) {
        livros:
        for (Emprestimo emprestimoDevolucao : usuario.getEmprestimosRealizados()) {
            if (emprestimoDevolucao.getObra().equals(obra)) {
                emprestimoDevolucao.setDevolucaoRealizada();
                // Tira o bloqueio do usuário
                usuario.setBloqueado(false);
                System.out.printf("A obra %s não está mais emprestada para %s.\n", emprestimoDevolucao.getUsuario().getNome(), obra.getTitulo(), usuario.getNome());
                break livros;
            }
        }

        // Verifica se o usuário possui mais alguma obra bloqueado por atraso
        for (Emprestimo emprestimoAtraso : usuario.getEmprestimosRealizados()) {
            if (emprestimoAtraso.getEmprestimoAtivo() && emprestimoAtraso.getAtrasado()) {
                usuario.setBloqueado(true);
                break;
            }
        }
        
        if(this.usuarioLogado instanceof Bibliotecario bibliotecario) {
            bibliotecario.incrementaDevolucoesRealizadas();
        }

        // System.out.printf("%s não possui a obra %s emprestada.\n", usuario.getNome(), obra.getTitulo());
    }

    public void realizarEmprestimo(ObraLiteraria obra, Usuario usuario) {
        if (!usuario.validacaoEmprestimo(obra)) {
            return;
        }
        Emprestimo novoEmprestimo = new Emprestimo(usuario, obra);
        usuario.getEmprestimosRealizados().add(novoEmprestimo);
        this.emprestimos.add(novoEmprestimo);
        System.out.println("Empréstimo realizado com sucesso.");
        System.out.printf("Novo livro emprestado: %s\n", obra.getTitulo());
        obra.atualizarQuantidade(-1);
        System.out.println("Quantidade disponível atualizada.");
        System.out.println(obra.consultarInformacoes());
    }

    public void exibirObrasDisponiveis() {
        for (ObraLiteraria obra : obras) {
            if (obra.getQuantidadeDisponivel() > 0) {
                System.out.printf("Id: %d | Título: %s | Autor: %s | Quantidade disponível: %d\n", obra.getId(), obra.getTitulo(), obra.getAutor(), obra.getQuantidadeDisponivel());
            }
        }
        System.out.println();
    }

    private void executarConsulta() {
        int opcConsulta = Integer.parseInt(Entrada.solicitarEntradaValida("1 - Consultar por ID\n2 - Consultar por Título\nEscolha uma opção: ", "^[1-2]$", "Opção inválida"));

        if (opcConsulta == 1) {
            int consulta = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o ID ou título da obra: ", "^[0-9]+$", "ID inválido"));
            this.consultarObra(consulta);
        } else {
            String consulta = Entrada.solicitarEntradaValida("Digite o ID ou título da obra: ", "^[a-zA-Z ]+$", "Título inválido");
            this.consultarObra(consulta);
        }
    }

    public void consultarObra(String titulo) {
        for (ObraLiteraria obra : obras) {
            if (obra.getTitulo().equals(titulo)) {
                System.out.println(obra.consultarInformacoes());
                return;
            }
        }
        System.out.println("Obra não encontrada.");
    }

    public void consultarObra(int id) {
        for (ObraLiteraria obra : obras) {
            if (obra.getId() == id) {
                System.out.println(obra.consultarInformacoes());
                return;
            }
        }
    }

    public int getQuantUsuarios() {
        return usuarios.size();
    }

    public void logoutUsuario() {
        // Código para encerrar a sessão do usuário atual
        System.out.printf("Usuário %s desconectado.\n", this.usuarioLogado.getNome());
        this.usuarioLogado = null;
    }

    public Usuario getUsuarioLogado() {
        return this.usuarioLogado;
    }

    public void adicionarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public ObraLiteraria selecionarObra() {
        // Código para selecionar uma obra disponível
        exibirObrasDisponiveis();
        int idObra = Integer.parseInt(Entrada.solicitarEntradaValida("Digite o código da obra desejada: ", "^[0-9]+$", "Código Inválido"));

        for (ObraLiteraria obra : obras) {
            if (obra.getId() == idObra && obra.getQuantidadeDisponivel() > 0) {
                return obra;
            }
        }

        System.out.println("Obra não encontrada ou não disponível.");
        return null;
    }

    public ObraLiteraria buscarObra(int id) {
        // Código para buscar uma obra pelo título
        for (ObraLiteraria obra : obras) {
            if (obra.getId() == id) {
                return obra;
            }
        }
        return null;
    }

    public boolean  getEmprestimosAtivos() {
        
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getEmprestimoAtivo()) {
                return true;
            }
        }
        return false;
    }

    public boolean  getUsuariosComAtraso() {

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getAtrasado() && emprestimo.getEmprestimoAtivo()) {
                return true;
            }
        }
        return false;
    }

    public void gerarRelatorioObrasEmprestadas() {
        // Código para gerar relatório de obras emprestadas

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getEmprestimoAtivo()) {
                System.out.println("Usuário: " + emprestimo.getUsuario().getNome() + " | Obra: " + emprestimo.getObra().getId() + " | " + emprestimo.getObra().getTitulo() + " | Data de empréstimo: " + emprestimo.getDataEmprestimo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | Previsão de Entrega: " + emprestimo.getDataDevolucaoPrevista().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
            }
        }
    }

    public void gerarRelatorioUsuariosComAtraso() {
        // Código para gerar relatório de usuários com atraso
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getAtrasado() && emprestimo.getEmprestimoAtivo()) {
                System.out.println("Usuário: " + emprestimo.getUsuario().getNome() + " | Obra: " + emprestimo.getObra().getId() + " | " + emprestimo.getObra().getTitulo() + " | Previsao de Entrada: " + emprestimo.getDataDevolucaoPrevista().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | Dias de Atraso: " + emprestimo.getDiasAtraso() + "\n");
                emprestimo.getUsuario().setBloqueado(true);
            }
        }
    }

    public void fechamentoSistema() {
        // Código para salvar os dados do sistema em arquivos
        System.out.println("Salvando relatórios...");

        // Salvamento de usuários
        salvarUsuariosArquivo();

        // Salvamento de empréstimos
        salvarEmprestimosArquivo();

    }

    public void salvarEmprestimosArquivo() {
        File arquivoEmprestimos = new File("OBLibrary\\src\\dados\\emprestimos.txt");

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoEmprestimos))) {
            escritor.write("email;tituloObra;dataEmprestimo;dataDevolucaoRealizada\n");

            System.out.println("Total de empréstimos para salvar: " + emprestimos.size());

            for (Emprestimo emprestimo : emprestimos) {
                String linha = emprestimo.getUsuario().getEmail() + ";"
                        + emprestimo.getObra().getId() + ";"
                        + emprestimo.getDataEmprestimo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                if (emprestimo.getDataDevolucaoRealizada() != null) {
                    linha += ";" + emprestimo.getDataDevolucaoRealizada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                } else {
                    linha += ";" + "null";
                }
                linha += "\n";
                escritor.write(linha);
            }

            escritor.flush(); // Força a escrita no arquivo
            System.out.println("Empréstimos salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao criar ou escrever no arquivo.");
        }
    }

    public void salvarUsuariosArquivo() { 
        File arquivoUsuarios = new File("OBLibrary\\src\\dados\\usuarios.txt");

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoUsuarios))) {
            arquivoUsuarios.createNewFile();

            escritor.write("tipo;nome;email;senha;atributo1;atributo2\n");

            for (Usuario usuarioAtual : usuarios) {
                String linha = "";
                switch (usuarioAtual.getTipoUsuario()) {
                    case 1:
                        linha = usuarioAtual.getTipoUsuario() + ";"
                                + usuarioAtual.getNome() + ";"
                                + usuarioAtual.getEmail() + ";"
                                + usuarioAtual.getSenha() + ";"
                                + ((Aluno) usuarioAtual).getMatricula() + ";"
                                + ((Aluno) usuarioAtual).getCurso();
                        break;
                    case 2:
                        linha = usuarioAtual.getTipoUsuario() + ";"
                                + usuarioAtual.getNome() + ";"
                                + usuarioAtual.getEmail() + ";"
                                + usuarioAtual.getSenha() + ";"
                                + ((Professor) usuarioAtual).getDepartamento() + ";"
                                + "null";
                        break;
                    case 3:
                        linha = usuarioAtual.getTipoUsuario() + ";"
                                + usuarioAtual.getNome() + ";"
                                + usuarioAtual.getEmail() + ";"
                                + usuarioAtual.getSenha() + ";"
                                + ((Bibliotecario) usuarioAtual).getTelefone() + ";"
                                + ((Bibliotecario) usuarioAtual).getDevolucoesRealizadas();
                        break;
                }
                linha += "\n";

                escritor.write(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar ou escrever no arquivo.");
        }
    }
}
