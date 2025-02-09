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
    
    public void exportarUniformes(List<Item> itens, String caminhoArquivo) {
        // Exporta os itens de uniformes para uma planilha
        List<String> dados = new ArrayList<>();
        for (Item item : itens) {
            dados.add(item.toString());
        }
        CSVHandler csvHandler = new CSVHandler();
        csvHandler.escreverCSV(caminhoArquivo, dados);
    }

    public void exportarEPI(List<Item> itens, String caminhoArquivo) {
        // Exporta os itens de EPI para uma planilha
        List<String> dados = new ArrayList<>();
        for (Item item : itens) {
            dados.add(item.toString());
        }
        CSVHandler csvHandler = new CSVHandler();
        csvHandler.escreverCSV(caminhoArquivo, dados);
    }

    public void exportarAlmoxarifado(List<Item> itens, String caminhoArquivo) {
        // Exporta os itens do almoxarifado para uma planilha
        List<String> dados = new ArrayList<>();
        for (Item item : itens) {
            dados.add(item.toString());
        }
        CSVHandler csvHandler = new CSVHandler();
        csvHandler.escreverCSV(caminhoArquivo, dados);
    }

}
