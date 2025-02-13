/*
 * CSVHandler
 * Métodos:
 * lerCSV(String caminhoArquivo)
 * escreverCSV(String caminhoArquivo, List<?> dados)
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {

    public List<String> lerCSV(String caminhoArquivo) {
        // Lê os dados do arquivo CSV
        List<String> dados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Processa a linha
                dados.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
        return dados;
    }

    public void escreverCSV(String caminhoArquivo, List<?> dados) {
        // Escreve os dados no arquivo CSV
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(caminhoArquivo)))) {
            for (Object dado : dados) {
                writer.println(dado.toString());
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo CSV: " + e.getMessage());
        }
    }
}
