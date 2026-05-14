package service;

import model.Categoria;
import model.Noticia;
import view.View;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Camada de Serviço: Orquestra as regras de negócio do sistema de monitoramento.
 * Esta classe é responsável pelo processamento, análise heurística de fake news,
 * gerenciamento do fluxo do menu e garantia da integridade dos dados (validação).
 * * <p>Melhoria em relação ao original: Separação total da lógica de armazenamento 
 * e cálculo da interface com o usuário.</p>
 */
public class GerenciadorNoticia {
    
    /**
     * Lista estática para armazenamento em memória das notícias registradas.
     * Originalmente chamada de 'data', agora possui nome semântico.
     */
    static ArrayList<Noticia> noticiasCadastradas = new ArrayList<>();

    /**
     * Centraliza o registro de notícias no sistema. 
     * Aplica a lógica de "falha rápida" (fail-fast) ao validar o texto antes de prosseguir.
     * * @param texto O conteúdo da notícia a ser salvo.
     * @param categoria A classificação (manual ou calculada) da notícia.
     */
    public static void processarERegistrarNoticia(String texto, String categoria) {
        if (!validarNoticiciaSePossuiTexto(texto)) {
            return; 
        }

        String categoriaFinal = atribuirCategoriaManual(categoria);
        Noticia novaNoticia = new Noticia(texto, categoriaFinal);
        
        noticiasCadastradas.add(novaNoticia);
        System.out.println("Notícia registrada com sucesso!");
    }

    /**
     * Captura dados via console para cadastro com classificação manual.
     * @param sc Scanner instanciado no menu para leitura de dados.
     */
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

    /**
     * Captura dados via console e utiliza o motor de análise automática para classificar.
     * @param sc Scanner instanciado no menu para leitura de dados.
     */
    public static void adicionarNoticiaAutomatico(Scanner sc) {
        System.out.print("Digite o texto: ");
        String texto = sc.nextLine();

        if (!validarNoticiciaSePossuiTexto(texto)) {
            return;
        }

        String categoriaCalculada = analisarCategoria(texto);
        processarERegistrarNoticia(texto, categoriaCalculada);
    }

    /**
     * Motor de análise heurística. Atribui pontos (score) com base em padrões 
     * comuns de desinformação (falta de fontes, excesso de pontuação, alarmismo).
     * * @param texto O conteúdo a ser analisado.
     * @return String contendo o nome da Categoria definida no Enum.
     */
    public static String analisarCategoria(String texto) {
        int score = 0;

        if (!texto.contains("FONTE")) score++;
        if (texto.contains("!!!")) score++;
        if (texto.contains("URGENTE")) score++;
        if (texto.length() < 10) score++;

        if (score == 0) return Categoria.CONFIAVEL.NomeCategoria;
        else if (score == 1) return Categoria.DUVIDOSA.NomeCategoria;
        else return Categoria.FALSA.NomeCategoria;
    }

    /**
     * Trata casos de entrada manual vazia, garantindo que nenhuma notícia fique sem rótulo.
     * @param categoria Entrada bruta do usuário.
     * @return String tratada ou valor padrão "Duvidosa".
     */
    public static String atribuirCategoriaManual(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            return "Informação duvidosa!";
        }
        return categoria;
    }

    /**
     * Implementação de Programação Defensiva. 
     * Verifica nulidade e espaços vazios para evitar erros em tempo de execução.
     * * @param texto String a ser validada.
     * @return boolean True se o texto for válido.
     */
    private static boolean validarNoticiciaSePossuiTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            System.out.println("Erro: O conteúdo da notícia não pode estar vazio.");
            return false;
        }
        return true;
    }

    /**
     * Gerencia o loop principal da aplicação e delega as chamadas para a classe View.
     * Substituiu a estrutura de 'if-else' por 'switch-case' para melhor legibilidade.
     */
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        boolean ativo = true;
        while (ativo) {
            View.exibirMenu();
            String opcoes = sc.nextLine();

            switch (opcoes) {
                case "1":
                    adicionarNoticiaManual(sc);
                    break;
                case "2":
                    adicionarNoticiaAutomatico(sc);
                    break;
                case "3":
                    View.listarNoticiasCadastradas(noticiasCadastradas);
                    break;
                case "4":
                    System.out.println("Encerrando sistema...");
                    ativo = false;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }
        sc.close();
    }
}