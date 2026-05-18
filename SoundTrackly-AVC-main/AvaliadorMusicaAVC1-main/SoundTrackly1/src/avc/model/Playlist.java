package avc.model;

import java.util.ArrayList;

// Classe que representa uma Playlist criada pelo usuário
public class Playlist {

    // Nome da playlist
    private String nome;

    // Lista de músicas que fazem parte da playlist
    private ArrayList<Musica> musicas;

    // Comentário da avaliação da playlist
    private String comentario;

    // Quantidade de estrelas da avaliação (0 a 5)
    private int estrelas;

    // Indica se a playlist já foi avaliada pelo usuário
    private boolean avaliada;

    // Construtor: cria a playlist com nome e lista de músicas vazia
    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
        this.comentario = "";
        this.estrelas = 0;
        this.avaliada = false;
    }

    // Adiciona uma música à playlist
    public void adicionarMusica(Musica m) {
        musicas.add(m);
    }

    // Registra a avaliação da playlist (estrelas + comentário)
    public void avaliar(int estrelas, String comentario) {
        this.estrelas = estrelas;
        this.comentario = comentario;
        this.avaliada = true;
    }

    // Calcula a média de estrelas das músicas da playlist
    public double getMediaEstrelas() {
        if (musicas.isEmpty()) return 0;
        int total = 0;
        for (Musica m : musicas) {
            total += m.getEstrelas();
        }
        return (double) total / musicas.size();
    }

    // ---- Getters ----

    public String getNome() { return nome; }

    public ArrayList<Musica> getMusicas() { return musicas; }

    public String getComentario() { return comentario; }

    public int getEstrelas() { return estrelas; }

    public boolean isAvaliada() { return avaliada; }
}
