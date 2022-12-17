package com.supermercado.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.supermercado.controllers.CarritoCtrl;
import com.supermercado.controllers.ProductoCtrl;
import com.supermercado.models.Producto;

public class Ventana extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private ProductoCtrl produtoCtrl;
	private CarritoCtrl carritoCtrl;

	public Ventana(List<Producto> productos) {
		this.produtoCtrl = new ProductoCtrl(productos);
		this.carritoCtrl = new CarritoCtrl();
		
		// Configuracion de Ventana
		setSize(500, 400); // Tamanio de ventana
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierra programa al cerrar Ventana
		setLocationRelativeTo(null); // Posicion al centro de pantalla
		getContentPane().setBackground(Color.gray); // Color de fondo
		setTitle("Cajero"); // Titulo de ventana
		
		// Carga los componentes a la Ventana
		iniciarComponentes();
	}
	
	private void iniciarComponentes() {
		JPanel panel = new JPanel();
		this.getContentPane().add(panel); // Se agrega el Panel al JFrame (Ventana)
		panel.setBackground(Color.lightGray); // Color de fondo
		
		// Etiqueta tipo texto (Codigo:)
		JLabel codigoLabel = new JLabel();
		panel.setLayout(null); // Desactivamos diseño por defecto
		codigoLabel.setText("Codigo:");
		// posicion P y tamanio T: (xP, yP, xT, yT)
		codigoLabel.setBounds(20, 20, 80, 30); // Posicion y tamanio
		codigoLabel.setFont(new Font("arial", 1, 18));
		codigoLabel.setHorizontalAlignment(SwingConstants.RIGHT); // Ubicamos texto a la derecha
		panel.add(codigoLabel);
		
		// Etiqueta tipo input (input para ingresar el codigo de producto)
		JTextField codigoInput = new JTextField();
		codigoInput.setBounds(100, 20, 80, 30);
		panel.add(codigoInput);
		
		// Botones Agregar y Eliminar
		JButton botonAgregar = new JButton();
		botonAgregar.setText("Agregar");
		botonAgregar.setBounds(190, 20, 80, 30);
		botonAgregar.setEnabled(true);
		botonAgregar.setFont(new Font("arial", 1, 10));
		panel.add(botonAgregar);
		JButton botonEliminar = new JButton();
		botonEliminar.setText("Eliminar");
		botonEliminar.setBounds(270, 20, 80, 30);
		botonEliminar.setEnabled(true);
		botonEliminar.setFont(new Font("arial", 1, 10));
		panel.add(botonEliminar);
		
		// Etiqueta tipo imagen
		ImageIcon logoImage = new ImageIcon("database/logo.jpg");
		JLabel logoLabel = new JLabel();
		logoLabel.setBounds(400, 10, 50, 50);
		logoLabel.setIcon(new ImageIcon(logoImage.getImage().getScaledInstance(logoLabel.getWidth(), logoLabel.getHeight(), Image.SCALE_SMOOTH)));
		panel.add(logoLabel);
		
		// Lista de Productos (Se cargan los productos seleccionados)
		JTextArea productosArea = new JTextArea("   CODIGO\t\tPRODUCTO\tPRECIO\n");
		productosArea.setBounds(20, 70, 440, 200);
		productosArea.setFont(new Font("arial", 1, 16));
		productosArea.setEditable(false);
		panel.add(productosArea);
		
		// Botones Confirmar y Cancelar
		JButton botonConfirmar = new JButton();
		botonConfirmar.setText("Confirmar");
		botonConfirmar.setBounds(20, 280, 120, 40);
		botonConfirmar.setEnabled(true);
		botonConfirmar.setFont(new Font("arial", 1, 16));
		panel.add(botonConfirmar);
		JButton botonCancelar = new JButton();
		botonCancelar.setText("Cancelar");
		botonCancelar.setBounds(140, 280, 120, 40);
		botonCancelar.setEnabled(true);
		botonCancelar.setFont(new Font("arial", 1, 16));
		panel.add(botonCancelar);
		
		// Etiqueta tipo texto
		JLabel totalLabel = new JLabel();
		totalLabel.setText("Total:");
		totalLabel.setBounds(300, 280, 50, 40);
		totalLabel.setFont(new Font("arial", 1, 18));
		totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(totalLabel);
		
		// Etiqueta tipo texto
		double monto = carritoCtrl.calcularTotal();
		JLabel montoLabel = new JLabel();
		montoLabel.setText("$"+(String.valueOf(monto)));
		montoLabel.setBounds(350, 280, 80, 40);
		montoLabel.setFont(new Font("arial", 1, 18));
		montoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(montoLabel);
		
		// Etiqueta tipo texto
		JLabel ayudaLabel = new JLabel();
		ayudaLabel.setText(formatearProductosAyuda(this.produtoCtrl.obtenerProductos()));
		ayudaLabel.setBounds(20, 320, 450, 50);
		ayudaLabel.setFont(new Font("arial", 2, 10));
		panel.add(ayudaLabel);
		
		// Eventos
		// Boton Agregar
		ActionListener accionAgregar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!codigoInput.getText().isEmpty()) {
					Producto p = produtoCtrl.obtenerProducto(codigoInput.getText().toUpperCase());
					if(p != null) {
						carritoCtrl.agregarProducto(p);
						productosArea.append(formatearProducto(p));
						codigoInput.setText("");
						montoLabel.setText("$"+(String.valueOf(carritoCtrl.calcularTotal())));
					}
				}
			}
		};
		botonAgregar.addActionListener(accionAgregar);
		
		// Boton Eliminar
		ActionListener accionEliminar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!codigoInput.getText().isEmpty()) {
					Producto p = produtoCtrl.obtenerProducto(codigoInput.getText());
					if(p != null) {
						carritoCtrl.eliminarProducto(p);
						productosArea.setText("   CODIGO\t\tPRODUCTO\tPRECIO\n");
						for(Producto pro : carritoCtrl.obtenerProductos()) {
							productosArea.append(formatearProducto(pro));
						}
						codigoInput.setText("");
						montoLabel.setText("$"+(String.valueOf(carritoCtrl.calcularTotal())));
					}
				}
			}
		};
		botonEliminar.addActionListener(accionEliminar);
		
		// Boton Confirmar
		ActionListener accionConfirmar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					generarPDF(carritoCtrl);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (DocumentException e1) {
					e1.printStackTrace();
				}
				carritoCtrl.vaciarCarrito();
				productosArea.setText("   CODIGO\t\tPRODUCTO\tPRECIO\n");
				montoLabel.setText("$"+(String.valueOf(carritoCtrl.calcularTotal())));
			}
		};
		botonConfirmar.addActionListener(accionConfirmar);
		
		// Boton Cancelar
		ActionListener accionCancelar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				carritoCtrl.vaciarCarrito();
				productosArea.setText("   CODIGO\t\tPRODUCTO\tPRECIO\n");
				montoLabel.setText("$"+(String.valueOf(carritoCtrl.calcularTotal())));
			}
		};
		botonCancelar.addActionListener(accionCancelar);
	}
	
	// Metodo para formatear el producto a mostrar en la Ventana (Lista de productos a comprar)
	public static String formatearProducto(Producto p) {
		return "   "+p.getCodigo().toUpperCase()+"\t\t"+p.getNombre().toUpperCase()+"\t$"+p.getPrecio()+"\n";
	}
	
	// Metodo para formatear el producto a mostrar en la Ventana (Ayuda
	public static String formatearProductosAyuda(List<Producto> listaProductos) {
		String ayuda = "";
		for(Producto p : listaProductos) {
			ayuda += "["+p.getCodigo().toUpperCase()+" - "+p.getNombre().toUpperCase()+"] ";
		}
		return ayuda;
	}
	
	// Metodo para generar el PDF (recibo)
	public static void generarPDF(CarritoCtrl carrito) throws FileNotFoundException, DocumentException {
		Document document = new Document();
        String dest = "reportes/recibo.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Phrase recibo = new Phrase(formatearPDF(carrito));
        document.add(recibo);
        document.close();
        System.out.println("PDF creado");
	}
	
	// Metodo para formatear el recibo de la compra para el PDF
	public static String formatearPDF(CarritoCtrl carrito) {
		String result = "---------- RECIBO ----------\n";
		for(Producto p : carrito.obtenerProductos()) {
			result += p.getCodigo().toUpperCase()+" .................... "
					+p.getNombre().toUpperCase()+" .................... $"
					+p.getPrecio()+"\n";
		}
		return result;
	}
}





