package com.pe.cucumber.definition;

import com.pe.cucumber.page.PageEbay;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class StepsDefinitionEbay {
	
	@Steps
	PageEbay page;
	
	@Given("^buscamos el producto (.*) por talla (.*) y marca (.*)$")
	public void buscamos_el_producto_por_talla_y_marca(String producto, String talla, String marca){
		page.getUrl();
		page.buscarProducto(producto);
		page.checkTalla(talla);
		page.checkMarca(marca);
	}
	
	@And("^seleccionamos el orden (.*) para listar el producto$")
	public void seleccionamos_el_orden_para_listar_el_producto(String orden){
		page.ordenAscendente(orden);
	}
	
	@When("^ordenamos los productos por nombre ascendente$")
	public void ordenamos_los_productos_por_nombre_ascendente(){
		page.listarProductoNombre();
	}
	
	@And("^ordenamos los precios en modo descendente$")
	public void ordenamos_los_precios_en_modo_descendente(){
		page.listarProductoPrecio();
	}
	

	@Then("^validamos que los productos listados muestren la marca (.*)$")
	public void validamos_que_los_productos_listados_muestren_la_marca(String marcaLista){
		page.validarMarca(marcaLista);
	}
	
}
