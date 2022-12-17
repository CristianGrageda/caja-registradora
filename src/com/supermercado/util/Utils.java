package com.supermercado.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.supermercado.models.Producto;

/**
 * Esta Clase permite generar el archivo base (como una base de datos), en la cual se contiene todos los productos
 * que se pueden agregar al carrito.
 * 
 * @author cristian grageda
 *
 */
public class Utils {
	public static void main(String[] args) {
		
		System.out.println("Iniciando creacion de archivos...");
		System.out.println("Creacion de archivos inciado");
		System.out.println("Creando Lista de Productos...");
		List<Producto> productos = new ArrayList<Producto>();
		productos.add(new Producto("A01", "Fideos", 14.9));
		productos.add(new Producto("A02", "Arroz", 8.5));
		productos.add(new Producto("A03", "Leche", 15.0));
		productos.add(new Producto("A04", "Gaseosa", 7.5));
		System.out.println("Lista de Productos creada con exito");
		
		try {
			System.out.println("Creando archivos..");
			BufferedWriter bw = new BufferedWriter(new FileWriter("database/productos.txt"));
			for(Producto p : productos) {
				bw.write(formatProducto(p));
				bw.newLine();
			}
			System.out.println("Archivos creados con exito");
			bw.close();
		} catch (IOException e) {
			System.err.println("Hubo un error en la crecion de archivos");
			e.printStackTrace();
		}
		
		System.out.println("Cerrando creacion de archivos...");
		System.out.println("Creacion de archivos cerrado con exito");
	}
	
	public static String formatProducto(Producto producto) {
		return producto.getCodigo()+";"+producto.getNombre()+";"+producto.getPrecio();
	}
}
