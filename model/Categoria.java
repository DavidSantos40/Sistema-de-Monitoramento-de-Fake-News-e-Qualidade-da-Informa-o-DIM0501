package model;

/**
 * Enumeração que define as categorias permitidas para classificação de notícias.
 * * <p>A utilização de um Enum em vez de Strings soltas (Magic Strings) evita erros 
 * de digitação, garante a integridade dos dados e centraliza as regras de 
 * classificação em um único local do sistema.</p>
 */
public enum Categoria {

    /**
     * Representa uma notícia que atendeu a todos os critérios de veracidade.
     */
    CONFIAVEL("Confiavel"),

    /**
     * Representa uma notícia com procedência incerta ou que falhou em algum critério leve.
     */
    DUVIDOSA("Duvidosa"),

    /**
     * Representa uma notícia identificada como desinformação por critérios heurísticos.
     */
    FALSA("Falsa");

    /**
     * Armazena o nome amigável da categoria para exibição na interface.
     */
    public final String NomeCategoria;

    /**
     * Construtor do Enum que associa um valor textual a cada constante.
     * @param nomeCategoria Texto legível da categoria.
     */
    Categoria(String nomeCategoria) {
        this.NomeCategoria = nomeCategoria;
    }
}