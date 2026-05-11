import java.util.ArrayList;
import java.util.Scanner;

class Noticia {

    String texto;
    String classificacao;

    public  Noticia (String texto, String classificacao){
        
        this.texto = texto;
        this.classificacao = classificacao;
        
    }

    
}


class view{

    public static void exibirMenu(){

        System.out.println("1 - adicionar manual");
            System.out.println("2 - adicionar automatico");
            System.out.println("3 - listar");
            System.out.println("4 - sair");

    }


    public static void listarNoticiasCadastradas(ArrayList<Noticia> listaDeNoticias) {



        if (listaDeNoticias.isEmpty()) {
            System.out.println("Nenhuma notícia cadastrada.");
            return;
        }

        for (Noticia noticia : listaDeNoticias) {
            System.out.println("Texto: " + noticia.texto);
            System.out.println("Classificacao: " + noticia.classificacao);
            System.out.println("-------------------");
        }
        
    }

}

public class SistemaFakeNews {

    static ArrayList<Noticia> noticiasCadastradas = new ArrayList<>();

    public static void adicionarNoticiasAutomatico(String textoNoticia, String categoria1) {

        if (!validarNoticiciaSePossuiTexto(textoNoticia)) {
            return;
        }
            String categoriaFinal = atribuirCategoria(categoria1);
            Noticia novaNoticia = new Noticia(textoNoticia, categoriaFinal);

            adicionarNoticia(novaNoticia);


    }

    public static String atribuirCategoria(String categoria){

        if(categoria == null || categoria.isEmpty()){

            return "Informação duvidosa!";
            
        }

        return categoria;

    }

    private static void adicionarNoticia(Noticia noticia){

        noticiasCadastradas.add(noticia);

    }

  

    //------------------------------------------------------------------------------------------


    public static String analisarCategoria(String texto) {
        int score = 0;

        if (!texto.contains("FONTE")) {
            score = score + 1;
        }
        if (texto.contains("!!!")) {
            score = score + 1;
        }
        if (texto.contains("URGENTE")) {
            score = score + 1;
        }
        if (texto.length() < 10) {
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

    private static boolean validarNoticiciaSePossuiTexto(String texto){
        if (texto.trim().isEmpty()) {

            System.out.println("Erro: O conteúdo da notícia não pode estar vazio.");
            return false;
        
        }
        return true;
    }



    //Função separar noticias manuais e automaticas e se possivel criar metodos que se utilize para as duas funções, para evitar repetição de código
    public static void adicionarNoticiaManual(Scanner sc) {
        System.out.print("Digite o texto: ");
        String textoNoticiaManual = sc.nextLine();

        if (!validarNoticiciaSePossuiTexto(textoNoticiaManual)) {
            return;
        }
        
        System.out.print("Digite classificacao: ");
        String categoriaNoticiaManual = sc.nextLine();

        if (categoriaNoticiaManual.equals("")) {
            String categoriaVazia = null;
            adicionarNoticia(new Noticia(textoNoticiaManual, categoriaVazia));
        } else {
            adicionarNoticiasAutomatico(textoNoticiaManual, categoriaNoticiaManual);
        }
    }

    public static void adicionarNoticiaAutomatico(Scanner sc) {
        System.out.print("Digite o texto: ");
        String textoDaNoticia = sc.nextLine();

        String categoriaNoticiaAutomatico = analisarCategoria(textoDaNoticia);
        adicionarNoticiasAutomatico(textoDaNoticia, categoriaNoticiaAutomatico);
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
                view.listarNoticiasCadastradas(noticiasCadastradas);
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