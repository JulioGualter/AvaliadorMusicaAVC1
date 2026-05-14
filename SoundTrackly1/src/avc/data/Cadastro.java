package avc.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Cadastro {

    public static void salvarDados(String nome, String idade, String email, String senha) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("dados.txt", true));
            
            writer.write(nome + " | " + idade + " | " + email + " | " + senha);
            writer.newLine();

            writer.close();

            System.out.println("Dados de Usuario Criado!");

        } catch (IOException e) {
        	
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }
}