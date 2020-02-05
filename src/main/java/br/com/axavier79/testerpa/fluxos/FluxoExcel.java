package br.com.axavier79.testerpa.fluxos;

import br.com.axavier79.testerpa.beans.DadosAmazon;
import br.com.axavier79.testerpa.utils.FileManager;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Anderson Xavier
 * 
 * Classe responsável por salvar as informações em uma planilha Excel.
 */
public class FluxoExcel {

    /**
     * Método responsável por criar a planilha Excel e salvar as informações nela.
     * 
     * @param dadosAmazon Recebe uma lista de DadosAmazon para ser salva na planilha.
     */
    public static void iniciarFluxoExcel(List<DadosAmazon> dadosAmazon) {
        String caminho = "Arquivos/";
        String nome = "Iphone.xlsx";
        FileManager fm = new FileManager();
        File[] lista = fm.listarConteudoPasta(caminho);
        if (lista.length > 0) {
            for (File file : lista) {
                fm.excluirArquivo(file);
            }
        }

        File file = new File(caminho + nome);
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        sheet = xssfWorkbook.createSheet("iphone");

        for (int i = 0; i <= dadosAmazon.size(); i++) {
            int cellnum = 0;
            if (i == 0) {
                row = sheet.createRow(i);

                cell = row.createCell(cellnum++);
                cell.setCellStyle(criarEstilo(xssfWorkbook));
                cell.setCellValue("DESCRIÇÃO");

                cell = row.createCell(cellnum++);
                cell.setCellStyle(criarEstilo(xssfWorkbook));
                cell.setCellValue("VALOR");
            } else {
                row = sheet.createRow(i);

                cell = row.createCell(cellnum++);
                cell.setCellStyle(criarEstilo(xssfWorkbook));
                cell.setCellValue(dadosAmazon.get(i - 1).getDescricao());

                cell = row.createCell(cellnum++);
                cell.setCellStyle(criarEstilo(xssfWorkbook));
                cell.setCellValue(dadosAmazon.get(i - 1).getValor());
            }
        }

        try {
            //ESCREVENDO O ARQUIVO EM DISCO
            FileOutputStream out = new FileOutputStream(file);

            xssfWorkbook.write(out);
            out.close();
            xssfWorkbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por criar o estilo que será utilizado nas células.
     * 
     * @param xssfWorkbook Recebe um objeto XSSFWorkbook.
     * 
     * @return Um objeto CellStyle com o estilo criado.
     */
    public static CellStyle criarEstilo(XSSFWorkbook xssfWorkbook) {
        CellStyle style = xssfWorkbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
//        style.setWrapText(true); //Quebrar Texto

        //Borda de baixo
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        //Borda da esquerda
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        //Borda da direita    
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        //Borda do topo    
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());

        return style;
    }
}
