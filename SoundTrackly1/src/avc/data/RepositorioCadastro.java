package avc.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RepositorioCadastro {

    public static void salvarDados(String nome, String idade, String email, String senha) {

        try {
        	FileWriter fw = new FileWriter("SoundTrackly1\\src\\usuarios.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(nome + ";" + idade + ";" + email + ";" + senha);
            bw.newLine();

            
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}