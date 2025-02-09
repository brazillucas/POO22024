/*
 * PlanilhaHandler
 * Métodos:
 * exportarUniformes(List<Item> itens, String caminhoArquivo)
 * exportarEPI(List<Item> itens, String caminhoArquivo)
 * exportarAlmoxarifado(List<Item> itens, String caminhoArquivo)
 */

import java.util.ArrayList;
import java.util.List;

public class PlanilhaHandler {
    
    public static void exportarUniformes(List<ItemPedido> itens, String caminhoArquivo) {
        // Exporta os itens de uniformes para uma planilha
        List<String> dados = new ArrayList<>();
        for (ItemPedido itemPedido : itens) {
            dados.add(itemPedido.toString());
        }
        CSVHandler csvHandler = new CSVHandler();
        csvHandler.escreverCSV(caminhoArquivo, dados);
    }

    public static void exportarEPI(List<ItemPedido> itens, String caminhoArquivo) {
        // Exporta os itens de EPI para uma planilha
        List<String> dados = new ArrayList<>();
        for (ItemPedido itemPedido : itens) {
            dados.add(itemPedido.toString());
        }
        CSVHandler csvHandler = new CSVHandler();
        csvHandler.escreverCSV(caminhoArquivo, dados);
    }

    public static void exportarAlmoxarifado(List<ItemPedido> itens, String caminhoArquivo) {
        // Exporta os itens do almoxarifado para uma planilha
        List<String> dados = new ArrayList<>();
        for (ItemPedido itemPedido : itens) {
            dados.add(itemPedido.toString());
        }
        CSVHandler csvHandler = new CSVHandler();
        csvHandler.escreverCSV(caminhoArquivo, dados);
    }

}
