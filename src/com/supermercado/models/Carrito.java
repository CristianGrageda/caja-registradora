package com.supermercado.models;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
	private List<Producto> productos;

	public Carrito() {
		this.productos = new ArrayList<Producto>();
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public void clearProductos() {
		this.productos.clear();
	}
}
