package com.supermercado.controllers;

import java.util.List;

import com.supermercado.models.Carrito;
import com.supermercado.models.Producto;

public class CarritoCtrl {
	private Carrito carrito;
	
	public CarritoCtrl() {
		this.carrito = new Carrito();
	}
	
	public void agregarProducto(Producto p) {
		this.carrito.getProductos().add(p);
	}
	
	public void eliminarProducto(Producto p) {
		this.carrito.getProductos().remove(p);
	}
	
	public List<Producto> obtenerProductos(){
		return this.carrito.getProductos();
	}
	
	public double calcularTotal() {
		double total = 0;
		for(Producto p : this.carrito.getProductos()) {
			total += p.getPrecio();
		}
		return total;
	}
	
	public void vaciarCarrito() {
		this.carrito.clearProductos();
	}
}
