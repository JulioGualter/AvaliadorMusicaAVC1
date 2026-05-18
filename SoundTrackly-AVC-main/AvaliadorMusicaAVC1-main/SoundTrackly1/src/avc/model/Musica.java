package avc.model;

public class Musica extends Midia {
    private String artista;

    public Musica(String nome, String artista, String genero, String comentario, int estrelas) {
        super(nome, genero, comentario, estrelas); 
        this.artista = artista;
    }

    public String getArtista() { return artista; }
    
    public String toFileFormat() {
        return nome + ";" + artista + ";" + genero + ";" + comentario + ";" + estrelas;
    }

    @Override
    public String toString() {
        return "🎵 " + nome + " - " + artista + " [" + estrelas + "/5]";
    }
}