package view;
import model.Noticia;
import java.util.ArrayList;


public class View {


    Noticia noticia;

    public static void exibirMenu() {

        System.out.println("\n--- MENU ---");
        System.out.println("1 - Adicionar manual");
        System.out.println("2 - Adicionar automatico");
        System.out.println("3 - Listar");
        System.out.println("4 - Sair");
        System.out.print("Escolha uma opcao: ");

    }

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