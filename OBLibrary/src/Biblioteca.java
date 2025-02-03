
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 7. Classe Biblioteca Responsabilidade: Gerencia todas as operações do sistema
 * e coordena as interações entre as entidades.
 *
 *  *Atributos: usuarios (List<Usuario>): Lista de todos os usuários
 * cadastrados. obras (List<ObraLiteraria>): Lista de todas as obras
 * disponíveis. emprestimos (List<Emprestimo>): Lista de todos os empréstimos
 * realizados. Métodos: carregarDados(): Carrega os dados do sistema (usuários,
 * obras e empréstimos) a partir de arquivos .txt. salvarDados(): Salva os dados
 * do sistema (usuários, obras e empréstimos) em arquivos .txt.
 * loginUsuario(String email, String senha): Verifica as credenciais e retorna o
 * usuário logado, se válido. logoutUsuario(): Encerra a sessão do usuário
 * atual. cadastrarUsuario(Usuario usuario): Cadastra um novo usuário no sistema
 * (operação exclusiva do bibliotecário). gerarRelatorioObrasEmprestadas(): Gera
 * um relatório das obras atualmente emprestadas.
 * gerarRelatorioUsuariosComAtraso(): Gera um relatório dos usuários com
 * empréstimos em atraso.
 */
public class Biblioteca {

    private List<Usuario> usuarios;
    private Usuario usuarioLogado;
    private List<ObraLiteraria> obras;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.usuarios = new ArrayList<>();
        this.obras = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        Usuario usuario = new Bibliotecario("João", "joao@google.com", "1aA.", "12345678");
        this.usuarios.add(usuario);
        carregarDados();
    }

    public void carregarDados() {
        String linha;

        // Código para carregar os dados do sistema a partir de arquivos .txt
        File arquivoUsuarios = new File("OBLibrary\\src\\dados\\usuarios.txt");
        // Leitura dos arquivos e carregamento dos dados
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoUsuarios))) {
            while ((linha = leitor.readLine()) != null) {
                String[] dadosUsuario = linha.split(";");
                if(dadosUsuario[3] == "1") {
                        String nome = dadosUsuario[0];
                        String email = dadosUsuario[1];
                        String senha = dadosUsuario[2];
                        String matricula = dadosUsuario[3];
                        String curso = dadosUsuario[4];
                        Usuario usuario = new Aluno(nome, email, senha, matricula, curso);
                        this.usuarios.add(usuario);
                } else if (dadosUsuario[3] == "2") {
                        String nome = dadosUsuario[0];
                        String email = dadosUsuario[1];
                        String senha = dadosUsuario[2];
                        String departamento = dadosUsuario[3];
                        Usuario usuario = new Professor(nome, email, senha, departamento);
                        this.usuarios.add(usuario);
                } else if (dadosUsuario[3] == "3") {
                        String nome = dadosUsuario[0];
                        String email = dadosUsuario[1];
                        String senha = dadosUsuario[2];
                        String telefone = dadosUsuario[3];
                        int devolucoesRealizadas = Integer.parseInt(dadosUsuario[4]);
                        Usuario usuario = new Bibliotecario(nome, email, senha, telefone, devolucoesRealizadas);
                        this.usuarios.add(usuario);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro de leitura.");
        }

        // Carregamento de obras
        File arquivoObras = new File("OBLibrary\\src\\dados\\acervo.csv");

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoObras))) {
            linha = leitor.readLine();
            while ((linha = leitor.readLine()) != null) {
                String[] dadosObra = linha.split(",");
                int id = Integer.parseInt(dadosObra[0]);
                String titulo = dadosObra[1];
                int quantidade = Integer.parseInt(dadosObra[2]);
                ObraLiteraria obra = new ObraLiteraria(id, titulo, quantidade);
                this.obras.add(obra);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro de leitura.");
        }

        // Carregamento de empréstimos
        File arquivoEmprestimos = new File("OBLibrary\\src\\dados\\emprestimos.txt");

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoEmprestimos));) {
            while ((linha = leitor.readLine()) != null) {
                String[] dadosEmprestimo = linha.split(";");
                if (dadosEmprestimo.length == 2) {
                    String emailUsuario = dadosEmprestimo[0];
                    String tituloObra = dadosEmprestimo[1];
                    Usuario usuarioTemp = null;
                    ObraLiteraria obraTemp = null;
                    for (Usuario usuarioAtual : usuarios) {
                        if (usuarioAtual.getEmail().equals(emailUsuario)) {
                            usuarioTemp = usuarioAtual;
                            break;
                        }
                    }
                    for (ObraLiteraria obraAtual : obras) {
                        if (obraAtual.getTitulo().equals(tituloObra)) {
                            obraTemp = obraAtual;
                            break;
                        }
                    }
                    Emprestimo emprestimo = new Emprestimo(usuarioTemp, obraTemp);
                    this.emprestimos.add(emprestimo);
                } else if (dadosEmprestimo.length == 3) {
                    String emailUsuario = dadosEmprestimo[0];
                    String tituloObra = dadosEmprestimo[1];
                    LocalDate dataEmprestimo = LocalDate.parse(dadosEmprestimo[2]);
                    Usuario usuarioTemp = null;
                    ObraLiteraria obraTemp = null;
                    for (Usuario usuarioAtual : usuarios) {
                        if (usuarioAtual.getEmail().equals(emailUsuario)) {
                            usuarioTemp = usuarioAtual;
                            break;
                        }
                    }
                    for (ObraLiteraria obraAtual : obras) {
                        if (obraAtual.getTitulo().equals(tituloObra)) {
                            obraTemp = obraAtual;
                            break;
                        }
                    }
                    Emprestimo emprestimo = new Emprestimo(usuarioTemp, obraTemp, dataEmprestimo);
                    this.emprestimos.add(emprestimo);
                } else if (dadosEmprestimo.length == 4) {
                    String emailUsuario = dadosEmprestimo[0];
                    String tituloObra = dadosEmprestimo[1];
                    LocalDate dataEmprestimo = LocalDate.parse(dadosEmprestimo[2]);
                    LocalDate dataDevolucaoPrevista = LocalDate.parse(dadosEmprestimo[3]);
                    Usuario usuarioTemp = null;
                    ObraLiteraria obraTemp = null;
                    for (Usuario usuarioAtual : usuarios) {
                        if (usuarioAtual.getEmail().equals(emailUsuario)) {
                            usuarioTemp = usuarioAtual;
                            break;
                        }
                    }
                    for (ObraLiteraria obraAtual : obras) {
                        if (obraAtual.getTitulo().equals(tituloObra)) {
                            obraTemp = obraAtual;
                            break;
                        }
                    }
                    Emprestimo emprestimo = new Emprestimo(usuarioTemp, obraTemp, dataEmprestimo, dataDevolucaoPrevista);
                    this.emprestimos.add(emprestimo);
                } else if (dadosEmprestimo.length == 5) {
                    String emailUsuario = dadosEmprestimo[0];
                    String tituloObra = dadosEmprestimo[1];
                    LocalDate dataEmprestimo = LocalDate.parse(dadosEmprestimo[2]);
                    LocalDate dataDevolucaoPrevista = LocalDate.parse(dadosEmprestimo[3]);
                    LocalDate dataDevolucaoRealizada = LocalDate.parse(dadosEmprestimo[4]);
                    Usuario usuarioTemp = null;
                    ObraLiteraria obraTemp = null;
                    for (Usuario usuarioAtual : usuarios) {
                        if (usuarioAtual.getEmail().equals(emailUsuario)) {
                            usuarioTemp = usuarioAtual;
                            break;
                        }
                    }
                    for (ObraLiteraria obraAtual : obras) {
                        if (obraAtual.getTitulo().equals(tituloObra)) {
                            obraTemp = obraAtual;
                            break;
                        }
                    }
                    Emprestimo emprestimo = new Emprestimo(usuarioTemp, obraTemp, dataEmprestimo, dataDevolucaoPrevista, dataDevolucaoRealizada);
                    this.emprestimos.add(emprestimo);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void exibirObrasDisponiveis() {
        for (ObraLiteraria obra : obras) {
            if (obra.getQuantidadeDisponivel() > 0) {
                System.out.printf("Id: %d | Título: %s | Autor: %s | Quantidade disponível: %d\n", obra.getId(), obra.getTitulo(), obra.getAutor(), obra.getQuantidadeDisponivel());
            }
        }
        System.out.println();
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

    public void cadastrarUsuario(Usuario usuario) {
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

    public void gerarRelatorioObrasEmprestadas() {
        // Código para gerar relatório de obras emprestadas
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucaoPrevista().isBefore(LocalDate.now())) {
                System.out.println("Usuário " + emprestimo.getUsuario().getNome() + " está com a obra " + emprestimo.getObra().getTitulo() + " em atraso.");
            }
        }
    }

    public void gerarRelatorioUsuariosComAtraso() {
        // Código para gerar relatório de usuários com atraso
        File arquivoRelatorio = new File("OBLibrary\\src\\dados\\relatorio_usuarios_atraso.txt");
        try {
            arquivoRelatorio.createNewFile();
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo.");
        }

    }

    public void salvarDados() {
        // Código para salvar os dados do sistema em arquivos .txt

        // Salvamento de obras
        File arquivoObras = new File("OBLibrary\\src\\dados\\obras.txt");

        try {
            arquivoObras.createNewFile();
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo.");
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoObras))) {
            for (ObraLiteraria obra : obras) {
                String linha = obra.getId() + ";" + obra.getTitulo() + ";" + obra.getAutor() + ";"
                        + obra.getQuantidadeDisponivel() + "\n";
                escritor.write(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo.");
        }

        // Salvamento de usuários
        File arquivoUsuarios = new File("OBLibrary\\src\\dados\\usuarios.txt");

        try {
            arquivoUsuarios.createNewFile();
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo.");
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoUsuarios))) {
            for (Usuario usuarioAtual : usuarios) {
                String linha = "";
                if (usuarioAtual.getTipoUsuario() == 1) {
                    linha = usuarioAtual.getNome() + ";" + usuarioAtual.getEmail() + ";" + usuarioAtual.getSenha() + ";" + ((Aluno) usuarioAtual).getMatricula() + ";" + ((Aluno) usuarioAtual).getCurso();usuarioAtual.getTipoUsuario();
                } else if (usuarioAtual.getTipoUsuario() == 2) {
                    linha = usuarioAtual.getNome() + ";" + usuarioAtual.getEmail() + ";" + usuarioAtual.getSenha() + ";" + ((Professor) usuarioAtual).getDepartamento() + usuarioAtual.getTipoUsuario();
                } else if (usuarioAtual.getTipoUsuario() == 3) {
                    linha = usuarioAtual.getNome() + ";" + usuarioAtual.getEmail() + ";" + usuarioAtual.getSenha() + ";" + ((Bibliotecario) usuarioAtual).getTelefone() + ((Bibliotecario) usuarioAtual).getDevolucoesRealizadas() + usuarioAtual.getTipoUsuario();
                }
                linha += "\n";

                escritor.write(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo.");
        }


        // Salvamento de empréstimos
        File arquivoEmprestimos = new File("OBLibrary\\src\\dados\\emprestimos.txt");
        
        try {
            arquivoEmprestimos.createNewFile();
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo.");
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoEmprestimos))) {
            for (Emprestimo emprestimo : emprestimos) {
                String linha = emprestimo.getUsuario().getEmail() + ";" + emprestimo.getObra().getTitulo() + ";"
                        + emprestimo.getDataEmprestimo();
                if (emprestimo.getDataDevolucaoRealizada() != null) {
                    linha += ";" + emprestimo.getDataDevolucaoRealizada();
                } else {
                    linha += ";" + emprestimo.getDataDevolucaoPrevista();
                }
                linha += "\n";

                escritor.write(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro de escrita.");
        }

    }
}
