package br.com.local.listagemapi;

public class Hero {
    private String heroi;
    private String nome;
    private int rating;
    private String editora;

    public Hero() {
    }

    public Hero(String heroi, String nome, int rating, String editora) {
        this.heroi = heroi;
        this.nome = nome;
        this.rating = rating;
        this.editora = editora;
    }

    public String getHeroi() {
        return heroi;
    }

    public void setHeroi(String heroi) {
        this.heroi = heroi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
}
