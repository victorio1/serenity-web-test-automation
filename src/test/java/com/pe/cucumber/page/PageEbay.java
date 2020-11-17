package com.pe.cucumber.page;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.junit.Assert;
import org.mockito.internal.matchers.Contains;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pe.cucumber.utils.WaitUtil;

import io.appium.java_client.functions.ExpectedCondition;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.ClickOnBy;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.webdriver.WebDriverFacade;

public class PageEbay extends PageObject{
	
	// Constructor
	
//	WebDriver driver;
	private static final Properties properties= new Properties();{
		
		InputStream resource= Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("configuracion.properties");
		try {
			properties.load(resource);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// Locators
	
	@FindBy(id="gh-ac")
	private WebElement boxBuscar;
	
	@FindBy(id="gh-btn")
	private WebElementFacade btnBuscar;
	
	@FindBy(id="x-refine__group_1__0")
	private WebElement listSize;
	
	
	@FindBy(id="x-refine__group_1__1")
	private WebElement listMarca;
	
	@FindBy(xpath = "/html/body/div[4]/div[5]/ul/li[1]/ul/li[2]/ul")
	private WebElement listTallaMarca;
	
	@FindBy(xpath = "//*[@id=\"s0-13-11-0-1-2-6-0-2[1]-20[1]-4\"]/button/span")
	private WebElement btnVerTodo;
	
	@FindBy(xpath = "//div[contains(@class,\"x-overlay-sub-panel__aspect-options-list\")]")
	private WebElement panelMarca;
	
	@FindBy(xpath = "//input[contains(@class,\"x-searchable-list__textbox__aspect-Brand\")]")
	private WebElement buscarMarca;

	@FindBy(xpath = "//button[contains(@aria-label,'Aplicar')]")
	private WebElement btnAplicar;
	
	@FindBy(xpath = "//div[contains(@class,'x-overlay-sub-panel__col')]")
	private WebElement options;
	
	@FindBy(xpath = "/html/body/div[4]/div[6]/div[1]/div/div[1]/div[3]/div[1]/div/span/button")
	private WebElement btnSelectOrden;
	
	@FindBy(xpath = "/html/body/div[4]/div[6]/div[1]/div/div[1]/div[3]/div[1]/div")
	private WebElement groupOptions;
	
	@FindBy(id = "srp-river-results")
	private WebElement ListaProductosFiltrados;
	
	public void getUrl() {
		getAllWebDriver().get(properties.getProperty("ebay.url"));
		getAllWebDriver().manage().window().maximize();
	}
	
	public void buscarProducto(String producto) {
		WaitUtil.with(getAllWebDriver(), boxBuscar);
//		boxBuscar.sendKeys(producto);
//		btnBuscar.click();
		enter(producto).into(boxBuscar);;
		clickOn(btnBuscar);
	}
	
	public void checkTalla(String talla) {
		WaitUtil.with(getAllWebDriver(), listTallaMarca);
		//click a la opcion talla a escoger
		WebElement li= listTallaMarca.findElement(By.tagName("li"));
		List<WebElement> input=li.findElements(By.tagName("input"));
		System.out.println(input.size() + "son la cantidad de input");
		for(WebElement elemento : input) {
			System.out.println(elemento.getAttribute("aria-label")+ "---------");
			if(elemento.getAttribute("aria-label").equals(talla)) {
				System.out.print(elemento.getAttribute("aria-label") + "es la talla");
				elemento.click();
				break;
			}
		}
	}
	
	public void checkMarca(String marca) {
		WaitUtil.with(getAllWebDriver(), listTallaMarca);
		WebElement li= listTallaMarca.findElement(By.tagName("li"));
		List<WebElement> button=li.findElements(By.tagName("button"));
		for(WebElement elemento : button) {
			if(elemento.getAttribute("aria-label").contains("Marca")) {
				elemento.click();
				break;
			}
		}
		WaitUtil.with(getAllWebDriver(), panelMarca);
		enter(marca).into(buscarMarca);
		waitFor(2000).millisecond();
		buscarMarca.sendKeys(Keys.TAB);
		
		List<WebElement> span = options.findElements(By.tagName("span"));
		for(int i = 0; i < span.size(); i++) {
			if (span.get(i).getText().contains(marca)) {
				span.get(i).click();
				break;
			}
		}
		
		waitFor(3000).millisecond();
		btnAplicar.click();
		waitFor(8000).millisecond();
//		$(buscarMarca).waitUntilVisible();

		
	}
	
	public void ordenAscendente(String orden) {
		WaitUtil.with(getAllWebDriver(), btnSelectOrden);
		btnSelectOrden.click();
		WaitUtil.with(getAllWebDriver(), groupOptions);
		List<WebElement> span = groupOptions.findElements(By.tagName("span"));
		System.out.println(span.size()+" span existen");
		for (WebElement elementoOrden: span) {
			System.out.println(elementoOrden.getText()+"---------");
			if(elementoOrden.getText().equals(orden)) {
				elementoOrden.click();
				break;
				
			}
		}
	}
	
	public Vector<String> listarProductoNombre(){
		Vector<String> vector = new Vector<String>();
		WebElement ul = ListaProductosFiltrados.findElement(By.xpath("ul"));
		List<WebElement> listaNombre=ul.findElements(By.xpath("li/div/div[2]/a/h3"));
		System.out.println("------LISTA ASCENDENTE DE NOMBRES DE LOS 5 PRIMEROS PRODUCTOS");
		for (int i=0; i < listaNombre.size(); i++) {
			vector.add(listaNombre.get(0).getText()+"\n");
			vector.add(listaNombre.get(1).getText()+"\n");
			vector.add(listaNombre.get(2).getText()+"\n");
			vector.add(listaNombre.get(3).getText()+"\n");
			vector.add(listaNombre.get(4).getText()+"\n");
			vector.add(listaNombre.get(5).getText()+"\n");
			Collections.sort(vector);
			System.out.println(vector);
			break;
		}
		return vector;
	}
	
	
	public List<Double> listarProductoPrecio(){
		List<Double> options = new ArrayList<Double>();
		WebElement ul = ListaProductosFiltrados.findElement(By.xpath("ul"));
		List<WebElement> lista=ul.findElements(By.xpath("li/div/div[2]/div[3]/div[1]/span"));
		System.out.println("------LISTA DESCENDETE DE PRECIOS DE LOS 5 PRIMEROS PRODUCTOS");
		for(int i=0; i< lista.size();i++) {
			options.add(Double.parseDouble(((lista.get(0).getText().replaceAll("S/.", "")))));
			options.add(Double.parseDouble(((lista.get(1).getText().replaceAll("S/.", "")))));
			options.add(Double.parseDouble(((lista.get(2).getText().replaceAll("S/.", "")))));
			options.add(Double.parseDouble(((lista.get(3).getText().replaceAll("S/.", "")))));
			options.add(Double.parseDouble(((lista.get(4).getText().replaceAll("S/.", "")))));
			Collections.sort(options, Collections.reverseOrder());
			System.out.println(options);
			break;
		}
		return options;
	}
	
	public void validarMarca(String marcaLista) {
		WebElement ul = ListaProductosFiltrados.findElement(By.xpath("ul"));
		List<WebElement> listaNombres = ul.findElements(By.xpath("li/div/div[2]/a/h3"));
		System.out.println(listaNombres.size() + "datos existen");
		for(int i=0; i< listaNombres.size(); i++) {
			System.out.println(listaNombres.get(i).getText()+">>>>>>>>");
			Assert.assertTrue(listaNombres.get(i).getText().contains(marcaLista));
		}
		
	}
	
	
//	public void checkMarca(String marca) {
//		WebElement ul= listSize.findElement(By.tagName("ul"));
//		List<WebElement> li=ul.findElements(By.xpath("li/div/a/div/div/span"));
//		for(WebElement elemento : li) {
//			if(elemento.getText().equals(marca)) {
//				System.out.print(elemento.getText() + "es la marca");
//				elemento.click();
//				break;
//			}
//		}
//	}
	
	private WebDriver getAllWebDriver() {
		WebDriverFacade facade=(WebDriverFacade) getDriver();
		return facade.getProxiedDriver();
		
	}
		
}
