package Modelo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Modelo.Pedido;
import Modelo.Producto;

public class Pedido{
	
		private static int numeroPedidos = 0;
		private int idPedido;
		private String nombreCliente;
		private String direccionCliente;	
		public ArrayList<Producto> itemsPedido;
	

	public Pedido(String nombreCliente, String direccionCliente)
		{
			this.idPedido = numeroPedidos+100;
			this.nombreCliente = nombreCliente;
			this.direccionCliente = direccionCliente;
			this.itemsPedido = new ArrayList<>();
		}
	
	public void consultarelementospedido(Pedido pedidoencurso){

		ArrayList<Producto> lista= pedidoencurso.itemsPedido;
		int contador = 1;
		for (Producto elementos : lista){
			System.out.println(contador + ". "+ elementos.getNombre());
			contador +=1 ;

		}


	}

	public ArrayList<Producto> getproductospedido(Pedido pedidoencurso){

		return pedidoencurso.itemsPedido;

	}

	public static int getNumeroPedidos()
		{
			return numeroPedidos;
		}
		
	public int getIdPedido()
		{
			return idPedido;
		}
	
	public void agregarProducto(Producto nuevoItem)
		{
			this.itemsPedido.add(nuevoItem);
		}
	
	private int getPrecioNetoPedido()
	{
		int precio = 0;
		for (Producto elementos: itemsPedido)
		{
			precio += elementos.getPrecio();
		}
		return precio;
	}
	private int getCalorias()
	{
		int cal = 0;
		for (Producto elementos: itemsPedido)
		{
			cal += elementos.getCalorias();
		}
		return cal;
	}	
	private int getPrecioTotalPedido()
	{
		return getPrecioNetoPedido() + getPrecioIVAPedido();
	}
	
	private int getPrecioIVAPedido()
	{
		return (int)(getPrecioNetoPedido()*0.19);
	}
	
	private String generarTextoFactura() {
		String x = "Factura\n";
		x += nombreCliente + "\n";
		x += direccionCliente + "\n";
		x += "Precio neto: " + getPrecioNetoPedido() + "\n";
		x += "Precio IVA: " + getPrecioIVAPedido() + "\n";
		x += "Precio total: " + getPrecioTotalPedido() + "\n";
		x += "Calorias totales: " + getCalorias() + "\n";
		
		for (Producto productos:itemsPedido){
			x += productos.generarTextoFactura();
		}
		return x;
	
	}
	
	public void guardarFactura(File archivo) throws IOException
	{
	    try {
	        FileWriter writer = new FileWriter(archivo);
	        writer.write(this.generarTextoFactura());
	        writer.close();
	 
	      } catch (IOException e) {
	        System.out.println("Ha ocurrido un error");
	        e.printStackTrace();
	      }
		Pedido.numeroPedidos += 1;
	}
}
