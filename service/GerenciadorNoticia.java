package service;

import model.Categoria;
import model.Noticia;
import view.View;
import java.util.ArrayList;
import java.util.Scanner;

 


public class GerenciadorNoticia {

    View intView;

    // Lista de notícias cadastras (mantida estática para este escopo, 
    // mas o ideal em POO é que seja um atributo de instância 'private')
    static ArrayList<Noticia> noticiasCadastradas = new ArrayList<>();

    /**
     * Processa e registra uma nova notícia no sistema.
     * Valida o conteúdo antes de salvar.
     *
     * @param texto O conteúdo da notícia.
     * @param categoria A categoria atribuída (Confiável, Duvidosa, Falsa).
     */
    public static void processarERegistrarNoticia(String texto, String categoria) {
        if (!validarNoticiciaSePossuiTexto(texto)) {
            return; // Interrompe se o texto for inválido
        }

        String categoriaFinal = atribuirCategoriaManual(categoria);
        Noticia novaNoticia = new Noticia(texto, categoriaFinal);
        
        noticiasCadastradas.add(novaNoticia);
        System.out.println("Notícia registrada com sucesso!");
    }

    public static void adicionarNoticiaManual(Scanner sc) {
        System.out.print("Digite o texto: ");
        String texto = sc.nextLine();

        if (!validarNoticiciaSePossuiTexto(texto)) {
            return;
        }

        System.out.print("Digite classificacao: ");
        String categoria = sc.nextLine();

        processarERegistrarNoticia(texto, categoria);
    }

    public static void adicionarNoticiaAutomatico(Scanner sc) {
        System.out.print("Digite o texto: ");
        String texto = sc.nextLine();

        if (!validarNoticiciaSePossuiTexto(texto)) {
            return;
        }

        String categoriaCalculada = analisarCategoria(texto);
        processarERegistrarNoticia(texto, categoriaCalculada);
    }


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
            return Categoria.CONFIAVEL.NomeCategoria;
        } else if (score == 1) {
            return Categoria.DUVIDOSA.NomeCategoria;
        } else {
            return Categoria.FALSA.NomeCategoria;
        }
    }

    public static String atribuirCategoriaManual(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            return "Informação duvidosa!";
        }
        return categoria;
    }

    private static boolean validarNoticiciaSePossuiTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            System.out.println("Erro: O conteúdo da notícia não pode estar vazio.");
            return false;
        }
        return true;
    }

    /**
     * Exibe o menu principal e gerencia a interação do usuário.
     */
    public static void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // Correção do bug: Chamando a classe View corretamente
            View.exibirMenu();
            String opcoes = sc.nextLine();

            if (opcoes.equals("1")) {
                adicionarNoticiaManual(sc);
            } else if (opcoes.equals("2")) {
                adicionarNoticiaAutomatico(sc);
            } else if (opcoes.equals("3")) {
                // Correção do bug: Chamando a classe View corretamente
                View.listarNoticiasCadastradas(noticiasCadastradas);
            } else if (opcoes.equals("4")) {
                System.out.println("Encerrando sistema...");
                break;
            } else {
                System.out.println("Opção inválida, tente novamente.");
            }
        }

        sc.close();
    }
}