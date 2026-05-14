package avc.data;

import java.io.*;
import java.util.ArrayList;
import avc.model.Musica;

public class RepositorioMusica {
    private static final String FILE_PATH = "src\\musicas.txt";

    public static void salvarTodas(ArrayList<Musica> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Musica m : lista) {
                bw.write(m.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Musica> carregarMusicas() {
        ArrayList<Musica> lista = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 5) {
                    lista.add(new Musica(dados[0], dados[1], dados[2], dados[3], Integer.parseInt(dados[4])));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}