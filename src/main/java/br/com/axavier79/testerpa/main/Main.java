package br.com.axavier79.testerpa.main;

import br.com.axavier79.testerpa.beans.DadosAmazon;
import br.com.axavier79.testerpa.fluxos.FluxoAmazon;
import br.com.axavier79.testerpa.fluxos.FluxoExcel;
import java.util.List;

/**
 *
 * @author Anderson Xavier
 * 
 * Classe principal do Projeto
 */
public class Main {
    private static List<DadosAmazon> dadosAmazon;
    
    /**
     * Método principal do projeto responsável por iniciar os fluxos de execução.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        FluxoAmazon fluxoAmazon = new FluxoAmazon();
        dadosAmazon = fluxoAmazon.iniciarFluxoAmazon();
        
        FluxoExcel fluxoExcel = new FluxoExcel();
        fluxoExcel.iniciarFluxoExcel(dadosAmazon);
    }
    
}
