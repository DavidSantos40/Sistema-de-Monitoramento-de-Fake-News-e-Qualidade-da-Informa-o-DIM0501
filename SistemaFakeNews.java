import java.util.ArrayList;
import java.util.Scanner;

class Noticia {
    String texto;
    String classificacao;
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

    public static void func2() {
        // lista tudo
        for (int i = 0; i < listarNoticiasCadastradas.size(); i++) {
            System.out.println("Texto: " + listarNoticiasCadastradas.get(i).texto);
            System.out.println("Classificacao: " + listarNoticiasCadastradas.get(i).classificacao);
            System.out.println("-------------------");
        }
    }

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
            System.out.println("1 - adicionar manual");
            System.out.println("2 - adicionar automatico");
            System.out.println("3 - listar");
            System.out.println("4 - sair");

            String opcoes = sc.nextLine();

            if (opcoes.equals("1")) {
                adicionarNoticiaManual(sc);
            } else if (opcoes.equals("2")) {
                adicionarNoticiaAutomatico(sc);
            } else if (opcoes.equals("3")) {
                func2();
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