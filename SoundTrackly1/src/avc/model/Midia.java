package avc.model;

public abstract class Midia {
    protected String nome;
    protected String genero;
    protected String comentario;
    protected int estrelas;

    public Midia(String nome, String genero, String comentario, int estrelas) {
        this.nome = nome;
        this.genero = genero;
        this.comentario = comentario;
        this.estrelas = estrelas;
    }

    //Getters
    public String getNome() { return nome; }
    public String getGenero() { return genero; }
    public String getComentario() { return comentario; }
    public int getEstrelas() { return estrelas; }
}