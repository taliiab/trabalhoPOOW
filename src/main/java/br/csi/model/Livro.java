package br.csi.model;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private int ano;

    public Livro() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getEditora() { return editora; }
    public void setEditora(String editora) { this.editora = editora; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

}
