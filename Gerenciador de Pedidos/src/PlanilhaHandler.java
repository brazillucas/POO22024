/*
 * PlanilhaHandler
 * Métodos:
 * exportarUniformes(List<Item> itens, String caminhoArquivo)
 * exportarEPI(List<Item> itens, String caminhoArquivo)
 * exportarAlmoxarifado(List<Item> itens, String caminhoArquivo)
 */

/*
 * Planilha de Uniformes
 * A planilha de saída para uniformes deve conter a seguinte configuração:
 * 
 * A primeira linha tem as colunas de A a K mescladas com o texto "NOME DA EMPRESA" como conteúdo
 * A2:B3 mesclados com o conteúdo "NUMERO DA LOJA: <<nº da loja do administrador>>"
 * C2:G2 mesclados com o conteúdo "PEDIDO DE UNIFORMES PARA:"
 * C3:G3 mesclados com o conteúdo "[ ] Novas admissões [ ] Reposições/Trocas" (que será marcado com "x" dependendo do tipo de pedido)
 * H2:H3 mesclados com o conteúdo "DATA:"
 * I2:K3 mesclados preenchido com <> no formato dd/mm/aaaa
 * A4:K4 mesclados com conteúdo uma declaração de recebimento do uniforme
 * Nome do funcionário
 * Matrícula do funcionário
 * Funcao
 * Tamanho
 * Data de Admissão
 * Motivo de Troca
 * G5:H5 mesclados com conteúdo "Tipo de uniforme" (a ser preenchido apenas com "X")
 * Uniforme (coluna G)
 * Moletom (coluna H)
 * Campo de Assinatura
 * As próximas linhas terão apenas bordas finas configuradas e o conteúdo resultando da query realizada
 */

/*
 * Planilha de EPI
 * A planilha de saída para EPI deve conter a seguinte configuração:
 * 
 * A1:J2 mesclados com conteúdo "PEDIDO / RECIDO DE ENTREGA DE EQUIPAMENTO DE PROTEÇÃO INDIVIDUAL - EPI"
 * A linha três tem plano de fundo "ccc" até a coluna J
 * A3 com conteúdo "Loja:"
 * B3 com conteúdo <<número da loja do administrador>>
 * E3 com conteúdo <> no formato dd/mm/aaaa
 * A4:J6 mescladas com conteúdo "Recebi de <>, gratuitamente, os Equipamentos de Proteção Individual - EPIs, abaixo relacionados, bem como as orientações de uso e conservação. Me comprometo a usá-los unicamente para os fins que se destinam e solicitar a troca quando desgastados. Fico ciente da obrigatoriedade do seu uso bem como da devolução deles no término do contrato de trabalho ou indenização no caso de dano ou extravio."
 * A7 a J7:
 * Matricula
 * Nome
 * Função
 * EPI (Tipo)
 * Tamanho
 * C.A. do EPI
 * Data de Recebimento
 * Assinatura
 */

 import java.io.FileWriter;
 import java.io.IOException;
 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
 import java.util.List;
 
 public class PlanilhaHandler {
 
     // Método para exportar planilha de uniformes em CSV
     public static void exportarUniformes(List<ItemPedido> itens, String caminhoArquivo, int numeroLoja, LocalDate dataAtual) {
        BancoDeDados bancoD = new BancoDeDados();
         try (FileWriter writer = new FileWriter(caminhoArquivo)) {
             // Cabeçalho personalizado
             writer.write("NOME DA EMPRESA\n");
             writer.write("NUMERO DA LOJA: " + numeroLoja + ",,PEDIDO DE UNIFORMES PARA:\n");
             writer.write(",,[ ] Novas admissões [ ] Reposições/Trocas\n");
             writer.write("DATA:," + dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
             writer.write("DECLARAÇÃO DE RECEBIMENTO DO UNIFORME\n");
 
             // Cabeçalho da tabela
             writer.write("Nome,Matrícula,Função,Tamanho,Data Admissão,Motivo Troca,Uniforme,Moletom,Assinatura\n");
 
             // Preencher os dados dos itens
             for (ItemPedido item : itens) {
                
                Funcionario funcionarioAtual = bancoD.buscarFuncionarioPorMatricula(item.getFuncionarioDestino());

                if (funcionarioAtual == null) {
                    System.err.println("Funcionário não encontrado para matrícula: " + item.getFuncionarioDestino());
                    continue;
                }

                 writer.write(
                     funcionarioAtual.getNome() + "," +
                     funcionarioAtual.getMatricula() + "," +
                     funcionarioAtual.getFuncao() + "," +
                     funcionarioAtual.getTamanhoUniforme() + "," +
                     funcionarioAtual.getDataAdmissao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "," +
                     "Reposição" + "," + // Exemplo de motivo
                     "X" + "," + // Uniforme marcado
                     "" + "," + // Moletom não marcado
                     "" + "\n" // Assinatura
                 );
             }
         } catch (IOException e) {
             System.err.println("Erro ao exportar planilha de uniformes: " + e.getMessage());
         }
     }
 
     // Método para exportar planilha de EPI em CSV
     public static void exportarEPI(List<ItemPedido> itens, String caminhoArquivo, int numeroLoja) {

        BancoDeDados bancoD = new BancoDeDados();

        

        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
             // Cabeçalho personalizado
             writer.write("PEDIDO / RECIDO DE ENTREGA DE EQUIPAMENTO DE PROTEÇÃO INDIVIDUAL - EPI\n");
             writer.write("Loja: " + numeroLoja + ",,,," + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
             writer.write("Recebi de <>, gratuitamente, os Equipamentos de Proteção Individual - EPIs, abaixo relacionados, bem como as orientações de uso e conservação. Me comprometo a usá-los unicamente para os fins que se destinam e solicitar a troca quando desgastados. Fico ciente da obrigatoriedade do seu uso bem como da devolução deles no término do contrato de trabalho ou indenização no caso de dano ou extravio.\n");
 
             // Cabeçalho da tabela
             writer.write("Matrícula,Nome,Função,EPI (Tipo),Tamanho,C.A. do EPI,Data Recebimento,Assinatura\n");
 
             // Preencher os dados dos itens
             for (ItemPedido item : itens) {
                // Pegar dados do banco de dados
                Item itemData = BancoDeDados.buscarItemPorId(item.getItemId());
                
                Funcionario funcionarioAtual = bancoD.buscarFuncionarioPorMatricula(item.getFuncionarioDestino());

                if (funcionarioAtual == null) {
                    System.err.println("Funcionário não encontrado para matrícula: " + item.getFuncionarioDestino());
                    continue;
                }
                

                writer.write(
                     funcionarioAtual.getMatricula() + "," +
                     funcionarioAtual.getNome() + "," +
                     funcionarioAtual.getFuncao() + "," +
                     itemData.getNome() + "," + // Descrição do item
                     (itemData instanceof EPI epi ? epi.getTamanho() : "N/A") + "," +
                     (itemData instanceof EPI epi ? epi.getCa() : "N/A") + "," + // Certificado de Aprovação
                     LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "," +
                     "" + "\n" // Assinatura
                 );
             }
         } catch (IOException e) {
             System.err.println("Erro ao exportar planilha de EPI: " + e.getMessage());
         }
     }
 }