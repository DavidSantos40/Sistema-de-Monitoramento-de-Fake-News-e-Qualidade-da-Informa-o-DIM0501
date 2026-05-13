package model;

public enum Categoria {

    CONFIAVEL("Confiavel"),
    DUVIDOSA("Duvidosa"),
    FALSA("Falsa");

    public final String NomeCategoria;

    Categoria(String nomeCategoria) {
        this.NomeCategoria = nomeCategoria;
    }
}