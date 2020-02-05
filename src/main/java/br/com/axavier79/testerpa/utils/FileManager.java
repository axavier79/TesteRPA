package br.com.axavier79.testerpa.utils;

import java.io.File;

/**
 * Classe responsável por tratar arquivos.
 * 
 * @author Anderson Xavier
 */
public class FileManager {

    /**
     * Método responsável por retornar um array com o conteúdo de uma pasta 
     *
     * @param caminhoPasta Caminho da pasta
     *
     * @return Retorna um array de File com a lista do conteúdo da pasta
     */
    public static File[] listarConteudoPasta(String caminhoPasta) {

        File dir = new File(caminhoPasta);

        return dir.listFiles();

    }

    /**
     * Método responsável por excluir arquivos
     *
     * @param file - Recebe um File a ser excluído
     *
     * @return Retorna true se o arquivo foi excluído com sucesso
     */
    public static boolean excluirArquivo(File file) {
        try {
            file.delete();
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
