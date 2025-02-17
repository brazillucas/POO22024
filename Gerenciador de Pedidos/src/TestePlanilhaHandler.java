import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestePlanilhaHandler {

    // Método para exportar planilha de uniformes
    public static void exportarUniformes(List<ItemPedido> itens, String caminhoArquivo, int numeroLoja) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Uniformes");

            // Estilo para células mescladas
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);

            // Primeira linha: "NOME DA EMPRESA"
            Row row0 = sheet.createRow(0);
            Cell cell0 = row0.createCell(0);
            cell0.setCellValue("NOME DA EMPRESA");
            cell0.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10)); // Mesclar A1:K1

            // A2:B3: "NUMERO DA LOJA: <<nº da loja do administrador>>"
            Row row1 = sheet.createRow(1);
            Cell cell1 = row1.createCell(0);
            cell1.setCellValue("NUMERO DA LOJA: " + numeroLoja);
            cell1.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 1)); // Mesclar A2:B3

            // C2:G2: "PEDIDO DE UNIFORMES PARA:"
            Row row2 = sheet.getRow(1); // Reutilizar a linha 2
            if (row2 == null) row2 = sheet.createRow(1);
            Cell cell2 = row2.createCell(2);
            cell2.setCellValue("PEDIDO DE UNIFORMES PARA:");
            cell2.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 6)); // Mesclar C2:G2

            // C3:G3: "[ ] Novas admissões [ ] Reposições/Trocas"
            Row row3 = sheet.getRow(2); // Reutilizar a linha 3
            if (row3 == null) row3 = sheet.createRow(2);
            Cell cell3 = row3.createCell(2);
            cell3.setCellValue("[ ] Novas admissões [ ] Reposições/Trocas");
            cell3.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 6)); // Mesclar C3:G3

            // H2:H3: "DATA:"
            Row row4 = sheet.getRow(1); // Reutilizar a linha 2
            if (row4 == null) row4 = sheet.createRow(1);
            Cell cell4 = row4.createCell(7);
            cell4.setCellValue("DATA:");
            cell4.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 7, 7)); // Mesclar H2:H3

            // I2:K3: Data atual
            Row row5 = sheet.getRow(1); // Reutilizar a linha 2
            if (row5 == null) row5 = sheet.createRow(1);
            Cell cell5 = row5.createCell(8);
            cell5.setCellValue(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            cell5.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 8, 10)); // Mesclar I2:K3

            // A4:K4: Declaração de recebimento
            Row row6 = sheet.createRow(3);
            Cell cell6 = row6.createCell(0);
            cell6.setCellValue("DECLARAÇÃO DE RECEBIMENTO DO UNIFORME");
            cell6.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 10)); // Mesclar A4:K4

            // Cabeçalho da tabela (A7:K7)
            Row headerRow = sheet.createRow(6);
            String[] headers = {"Nome", "Matrícula", "Função", "Tamanho", "Data Admissão", "Motivo Troca", "Uniforme", "Moletom", "Assinatura"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Preencher os dados dos itens
            int rowNum = 7;
            for (ItemPedido item : itens) {
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(0).setCellValue(item.getFuncionarioDestino().getNome());
                dataRow.createCell(1).setCellValue(item.getFuncionarioDestino().getMatricula());
                dataRow.createCell(2).setCellValue(item.getFuncionarioDestino().getFuncao());
                dataRow.createCell(3).setCellValue(item.getFuncionarioDestino().getTamanhoUniforme());
                dataRow.createCell(4).setCellValue(item.getFuncionarioDestino().getDataAdmissao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                dataRow.createCell(5).setCellValue("Reposição"); // Exemplo de motivo
                dataRow.createCell(6).setCellValue("X"); // Uniforme marcado
                dataRow.createCell(7).setCellValue(""); // Moletom não marcado
                dataRow.createCell(8).setCellValue(""); // Assinatura
            }

            // Salvar o arquivo
            try (FileOutputStream fileOut = new FileOutputStream(caminhoArquivo)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            System.err.println("Erro ao exportar planilha de uniformes: " + e.getMessage());
        }
    }

    // Método para exportar planilha de EPI
    public static void exportarEPI(List<ItemPedido> itens, String caminhoArquivo, int numeroLoja) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("EPI");

            // Estilo para células mescladas
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);

            // A1:J2: Título
            Row row0 = sheet.createRow(0);
            Cell cell0 = row0.createCell(0);
            cell0.setCellValue("PEDIDO / RECIDO DE ENTREGA DE EQUIPAMENTO DE PROTEÇÃO INDIVIDUAL - EPI");
            cell0.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 9)); // Mesclar A1:J2

            // A3: Loja
            Row row1 = sheet.createRow(2);
            Cell cell1 = row1.createCell(0);
            cell1.setCellValue("Loja:");
            cell1.setCellStyle(headerStyle);

            // B3: Número da loja
            Cell cell2 = row1.createCell(1);
            cell2.setCellValue(numeroLoja);
            cell2.setCellStyle(headerStyle);

            // E3: Data atual
            Cell cell3 = row1.createCell(4);
            cell3.setCellValue(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            cell3.setCellStyle(headerStyle);

            // A4:J6: Declaração
            Row row2 = sheet.createRow(3);
            Cell cell4 = row2.createCell(0);
            cell4.setCellValue("Recebi de <>, gratuitamente, os Equipamentos de Proteção Individual - EPIs, abaixo relacionados, bem como as orientações de uso e conservação. Me comprometo a usá-los unicamente para os fins que se destinam e solicitar a troca quando desgastados. Fico ciente da obrigatoriedade do seu uso bem como da devolução deles no término do contrato de trabalho ou indenização no caso de dano ou extravio.");
            cell4.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(3, 5, 0, 9)); // Mesclar A4:J6

            // Cabeçalho da tabela (A7:J7)
            Row headerRow = sheet.createRow(6);
            String[] headers = {"Matrícula", "Nome", "Função", "EPI (Tipo)", "Tamanho", "C.A. do EPI", "Data Recebimento", "Assinatura"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Preencher os dados dos itens
            int rowNum = 7;
            for (ItemPedido item : itens) {
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(0).setCellValue(item.getFuncionarioDestino().getMatricula());
                dataRow.createCell(1).setCellValue(item.getFuncionarioDestino().getNome());
                dataRow.createCell(2).setCellValue(item.getFuncionarioDestino().getFuncao());
                dataRow.createCell(3).setCellValue(item.getItemDescricao()); // Descrição do item
                dataRow.createCell(4).setCellValue(item.getTamanho());
                dataRow.createCell(5).setCellValue(item.getCa()); // Certificado de Aprovação
                dataRow.createCell(6).setCellValue(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                dataRow.createCell(7).setCellValue(""); // Assinatura
            }

            // Salvar o arquivo
            try (FileOutputStream fileOut = new FileOutputStream(caminhoArquivo)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            System.err.println("Erro ao exportar planilha de EPI: " + e.getMessage());
        }
    }
}
