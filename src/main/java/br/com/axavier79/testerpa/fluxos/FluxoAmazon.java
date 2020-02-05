package br.com.axavier79.testerpa.fluxos;

import br.com.axavier79.testerpa.beans.DadosAmazon;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author Anderson Xavier
 * 
 * Classe responsável por capturar os dados no site www.amazon.com
 */
public class FluxoAmazon {

    private WebDriver driver = null;
    private ChromeOptions options = null;

    /**
     * Método construtor
     */
    public FluxoAmazon() {
        options = new ChromeOptions();
//        options.addArguments("headless");
//        //The window-size is important for responsive sites
//        options.addArguments("windows-size=1200x600");
        driver = new ChromeDriver(options);
    }

    /**
     * Método responsável por iniciar o fluxo de execução de captura
     * dos dados no site.
     * 
     * @return Lista de objetos DadosAmazon com as informações capturadas.
     */
    public List<DadosAmazon> iniciarFluxoAmazon() {
        List<WebElement> listaDivs = new ArrayList<>();
        List<WebElement> listaSpans = new ArrayList<>();
        List<DadosAmazon> listaDados = new ArrayList<>();

        driver.get("http://www.amazon.com");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }

        driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input")).click();

        int x = 1;
        listaDivs = driver.findElements(By.tagName("div"));
        for (WebElement listaDiv : listaDivs) {
            DadosAmazon dadosAmazon = new DadosAmazon();
            boolean controle = false;
            if (listaDiv
                    .getAttribute("class")
                    .equals("a-section a-spacing-medium")) {

                listaSpans = listaDiv.findElements(By.tagName("span"));
                for (WebElement listaSpan : listaSpans) {
                    if (listaSpan
                            .getAttribute("class")
                            .equals("a-size-medium a-color-base a-text-normal")) {
                        try {
                            dadosAmazon.setDescricao(listaSpan.getText());
                        } catch (Exception e) {
                        }
                        controle = true;
                    }
                    if (listaSpan.getText().contains("$") && controle) {
                        try {
                            dadosAmazon.setValor(listaSpan.getText().replace("\n", "."));
                        } catch (Exception e) {
                        }
                        listaDados.add(dadosAmazon);
                        break;
                    }
                }
            }
        }
        driver.close();
        return listaDados;
    }
}
