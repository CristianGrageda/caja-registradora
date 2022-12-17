package com.supermercado.controllers;

import java.util.List;

import com.supermercado.models.Producto;

public class ProductoCtrl {
	
	private List<Producto> productos;

	public ProductoCtrl(List<Producto> productos) {
		this.productos = productos;
	}
	
	public Producto obtenerProducto(String codigo) {
		Producto producto = null;
		for(Producto p : this.productos) {
			if(codigo.equals(p.getCodigo())) {
				producto = p;
			}
		}
		return producto;
	}

	public List<Producto> obtenerProductos() {
		return this.productos;
	}

}
