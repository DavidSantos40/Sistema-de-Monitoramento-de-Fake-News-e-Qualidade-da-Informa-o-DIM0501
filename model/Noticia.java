package model;

/**
 * Entidade que representa uma Notícia dentro do sistema de monitoramento. [cite: 24, 25]
 * Armazena o conteúdo textual da notícia e sua respectiva classificação de qualidade. [cite: 26, 27]
 * * <p>Esta classe é o modelo de dados fundamental (POJO) utilizado para 
 * transferência de informações entre as camadas de serviço e interface.</p> [cite: 66, 67]
 */
public class Noticia {

    /**
     * Conteúdo integral do texto da notícia.
     */
    public String texto;

    /**
     * Rótulo de qualidade atribuído à notícia (Ex: Confiável, Duvidosa ou Falsa). [cite: 27]
     */
    public String classificacao;

    /**
     * Construtor para inicialização completa de uma notícia. 
     * * @param texto O corpo do texto da notícia.
     * @param classificacao A categoria de veracidade definida pelo sistema ou usuário. [cite: 27]
     */
    public Noticia(String texto, String classificacao) {
        this.texto = texto;
        this.classificacao = classificacao;
    }
}