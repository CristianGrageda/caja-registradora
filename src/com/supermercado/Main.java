package com.supermercado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.supermercado.gui.Ventana;
import com.supermercado.models.Producto;

/*
 * Proyecto Caja registradora
 * Este proyecto es una simulacion de una caja registradora, se pueden cargar/eliminar Productos mediante su Codigo
 * y se calcular el monto total. Al confirmar la compra, se genera un pdf con el resumen de la compra.
 * 
 * El objetivo fue practicar con las interfaces en Java (Swing) y el manejo de archivos
 * 
 * Main: Clase Principal, se encarga de ejecutar el programa
 * Producto: Clase Modelo para instanciar cada producto
 * ProductoCtrl: Clase con Logica para operar con los productos segun lo solicitado
 * Carrito: Clase Modelo para almacenar cada Producto seleccionado
 * CarritoCtrl: Clase con logica para operar con los productos almacenador en el Carrito
 * 
 * @author cristian grageda
 * 
 */

public class Main {
	public static void main(String[] args){
		System.out.println("Iniciando aplicacion.");
		System.out.println("Aplicacion iniciada...");
		try {
			BufferedReader br = new BufferedReader(new FileReader("database/productos.txt"));
			String linea = "";
			String[] parts;
			List<Producto> productos = new ArrayList<Producto>();
			while((linea = br.readLine()) != null ) {
				parts = linea.split(";");
				productos.add(new Producto(parts[0], parts[1], Double.parseDouble(parts[2])));
			}
			br.close();
			System.out.println("Abriendo Ventana...");
			Ventana v = new Ventana(productos);
			v.setVisible(true);
			System.out.println("Ventana abierta con exito");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
