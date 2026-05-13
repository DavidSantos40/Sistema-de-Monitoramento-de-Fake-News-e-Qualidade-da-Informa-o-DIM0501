import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Classificacao {
    CONFIAVEL,
    DUVIDOSA,
    FALSA
}

class Noticia {

    private String texto;
    private Classificacao classificacao;

    public Noticia(String texto, Classificacao classificacao) {
        this.texto = texto;
        this.classificacao = classificacao;
    }

    public String getTexto() {
        return texto;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }
}

class View {

    public static void exibirMenu() {

        System.out.println("\n===== SISTEMA FAKE NEWS =====");
        System.out.println("1 - Adicionar notícia manualmente");
        System.out.println("2 - Adicionar notícia automaticamente");
        System.out.println("3 - Listar notícias");
        System.out.println("4 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void listarNoticias(List<Noticia> noticias) {

        if (noticias.isEmpty()) {
            System.out.println("\nNenhuma notícia cadastrada.");
            return;
        }

        System.out.println("\n===== NOTÍCIAS CADASTRADAS =====");

        for (Noticia noticia : noticias) {

            System.out.println("Texto: " + noticia.getTexto());
            System.out.println("Classificação: " + noticia.getClassificacao());
            System.out.println("--------------------------------");
        }
    }

    public static void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}

class NoticiaService {

    private List<Noticia> noticias = new ArrayList<>();

    public void adicionarNoticiaManual(String texto, String classificacaoTexto) {

        if (!validarTexto(texto)) {
            return;
        }

        Classificacao classificacao;

        if (classificacaoTexto == null || classificacaoTexto.trim().isEmpty()) {

            classificacao = Classificacao.DUVIDOSA;

        } else {

            classificacao = converterClassificacao(classificacaoTexto);

            if (classificacao == null) {

                View.exibirMensagem("Classificação inválida.");
                return;
            }
        }

        Noticia noticia = new Noticia(texto, classificacao);
        noticias.add(noticia);

        View.exibirMensagem("Notícia adicionada com sucesso.");
    }

    public void adicionarNoticiaAutomatica(String texto) {

        if (!validarTexto(texto)) {
            return;
        }

        Classificacao classificacao = analisarCategoria(texto);

        Noticia noticia = new Noticia(texto, classificacao);

        noticias.add(noticia);

        View.exibirMensagem("Notícia analisada e adicionada com sucesso.");
    }

    private boolean validarTexto(String texto) {

        if (texto == null || texto.trim().isEmpty()) {

            View.exibirMensagem("Erro: o texto não pode estar vazio.");
            return false;
        }

        return true;
    }

    private Classificacao converterClassificacao(String texto) {

        texto = texto.trim().toUpperCase();

        try {

            return Classificacao.valueOf(texto);

        } catch (IllegalArgumentException e) {

            return null;
        }
    }

    private Classificacao analisarCategoria(String texto) {

        int score = 0;

        String textoMaiusculo = texto.toUpperCase();

        if (!textoMaiusculo.contains("FONTE")) {
            score++;
        }

        if (textoMaiusculo.contains("!!!")) {
            score++;
        }

        if (textoMaiusculo.contains("URGENTE")) {
            score++;
        }

        if (texto.length() < 10) {
            score++;
        }

        if (score == 0) {

            return Classificacao.CONFIAVEL;

        } else if (score == 1) {

            return Classificacao.DUVIDOSA;

        } else {

            return Classificacao.FALSA;
        }
    }

    public List<Noticia> listarNoticias() {
        return noticias;
    }
}

public class SistemaFakeNews {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        NoticiaService noticiaService = new NoticiaService();

        boolean executando = true;

        while (executando) {

            View.exibirMenu();

            String opcao = scanner.nextLine();

            switch (opcao) {

                case "1":

                    System.out.print("Digite o texto da notícia: ");
                    String textoManual = scanner.nextLine();

                    System.out.print("Digite a classificação ");
                    System.out.println("(CONFIAVEL, DUVIDOSA, FALSA)");
                    System.out.print("Ou pressione ENTER para padrão: ");

                    String classificacaoManual = scanner.nextLine();

                    noticiaService.adicionarNoticiaManual(
                            textoManual,
                            classificacaoManual
                    );

                    break;

                case "2":

                    System.out.print("Digite o texto da notícia: ");
                    String textoAutomatico = scanner.nextLine();

                    noticiaService.adicionarNoticiaAutomatica(
                            textoAutomatico
                    );

                    break;

                case "3":

                    View.listarNoticias(
                            noticiaService.listarNoticias()
                    );

                    break;

                case "4":

                    executando = false;
                    View.exibirMensagem("Sistema encerrado.");

                    break;

                default:

                    View.exibirMensagem("Opção inválida.");
            }
        }

        scanner.close();
    }
}