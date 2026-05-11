import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Noticia {

    String texto;
    String classificacao;


    
}


class view{

    public static void exibirMenu(){

        System.out.println("1 - adicionar manual");
            System.out.println("2 - adicionar automatico");
            System.out.println("3 - listar");
            System.out.println("4 - sair");

    }


    public static void ListarNoticiasCadastradas(ArrayList<Noticia> ListaDeNoticas) {



        if (ListaDeNoticas.isEmpty()) {
            System.out.println("Nenhuma notícia cadastrada.");
            return;
        }
        for (int i = 0; i < ListaDeNoticas.size(); i++) {

            System.out.println("Texto: " + ListaDeNoticas.get(i).texto);
            System.out.println("Classificacao: " + ListaDeNoticas.get(i).classificacao);
            System.out.println("-------------------");

        }
    }

}

public class SistemaFakeNews {

    static ArrayList<Noticia> listarNoticiasCadastradas = new ArrayList<>();

    // função que faz tudo
    public static void adicionarNoticias(String textoNoticia, String categoria1) {
        if (!ehTextoValido(textoNoticia)) {
            System.out.println("Erro: O conteúdo da notícia não pode estar vazio.");
            return;
        }
            String categoriaFinal = atribuirCategoria(categoria1);
            Noticia novaNoticia = criarNoticia(textoNoticia, categoriaFinal);

            adicionarNoticia(novaNoticia);
    }

    public static String atribuirCategoria(String categoria){

        if(categoria == null || categoria.isEmpty()){

            return "Informação duvidosa!";
            
        }

        return categoria;

    }

    private static boolean ehTextoValido(String texto){

        return texto != null && !texto.isEmpty();

    }

    private static Noticia criarNoticia(String texto, String classificacao){
        Noticia noticia = new Noticia();
        noticia.texto = texto;
        noticia.classificacao = classificacao;
        return noticia;
    }

    private static void adicionarNoticia(Noticia noticia){

        listarNoticiasCadastradas.add(noticia);

    }

    //------------------------------------------------------------------------------------------


    public static String analisarCategoria(String txt) {
        int score = 0;

        if (!txt.contains("FONTE")) {
            score = score + 1;
        }
        if (txt.contains("!!!")) {
            score = score + 1;
        }
        if (txt.contains("URGENTE")) {
            score = score + 1;
        }
        if (txt.length() < 10) {
            score = score + 1;
        }

        if (score == 0) {
            return "confiavel";
        } else if (score == 1) {
            return "duvidosa";
        } else {
            return "falsa";
        }
    }

    public static void adicionarNoticiaManual(Scanner sc) {
        System.out.print("Digite o texto: ");
        String textoNoticiaManual = sc.nextLine();

        System.out.print("Digite classificacao: ");
        String categoriaNoticiaManual = sc.nextLine();

        if (categoriaNoticiaManual.equals("")) {
            String categoriaVazia = null;
            adicionarNoticias(textoNoticiaManual, categoriaVazia);
        } else {
            adicionarNoticias(textoNoticiaManual, categoriaNoticiaManual);
        }
    }

    public static void adicionarNoticiaAutomatico(Scanner sc) {
        System.out.print("Digite o texto: ");
        String textoDaNoticia = sc.nextLine();

        String categoriaNoticiaAutomatico = analisarCategoria(textoDaNoticia);
        adicionarNoticias(textoDaNoticia, categoriaNoticiaAutomatico);
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            

            view.exibirMenu();

            String opcoes = sc.nextLine();

            if (opcoes.equals("1")) {
                adicionarNoticiaManual(sc);
            } else if (opcoes.equals("2")) {
                adicionarNoticiaAutomatico(sc);
            } else if (opcoes.equals("3")) {
                view.ListarNoticiasCadastradas(listarNoticiasCadastradas);
            } else if (opcoes.equals("4")) {
                break;
            } else {
                System.out.println("errado");
            }
        }

        sc.close();
    }

    // inicia programa
    public static void main(String[] args) {
        menu();
    }
}