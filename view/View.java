package view;

import model.Noticia;
import java.util.ArrayList;

/**
 * Camada de Interface (View): Responsável exclusivamente pela interação com o usuário.
 * Esta classe centraliza a exibição de menus e a listagem de dados no console,
 * garantindo a separação entre a lógica de apresentação e a lógica de negócio. [cite: 66, 67]
 */
public class View {

    /**
     * Exibe as opções do menu principal do sistema no console.
     */
    public static void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1 - Adicionar manual");
        System.out.println("2 - Adicionar automatico");
        System.out.println("3 - Listar");
        System.out.println("4 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    /**
     * Percorre e exibe todas as notícias armazenadas no sistema.
     * Caso a lista esteja vazia, informa o usuário adequadamente.
     * * @param listaDeNoticias ArrayList contendo os objetos do tipo Noticia a serem listados.
     */
    public static void listarNoticiasCadastradas(ArrayList<Noticia> listaDeNoticias) {
        if (listaDeNoticias.isEmpty()) {
            System.out.println("Nenhuma notícia cadastrada.");
            return;
        }

        System.out.println("\n--- LISTA DE NOTÍCIAS ---");
        for (Noticia noticia : listaDeNoticias) {
            System.out.println("Texto: " + noticia.texto);
            System.out.println("Classificacao: " + noticia.classificacao);
            System.out.println("-------------------");
        }
    }
}