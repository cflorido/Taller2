package Modelo;

import java.util.ArrayList;

public class Bebida implements Producto{
	
	private String nombre;
	private int precioBase;
	private int calorias;

	public Bebida(String nombre, int precio, int calorias)
	{
		this.nombre = nombre;
		this.precioBase = precio;
		this.calorias = calorias;
	}


	public String getNombre()
	{
		return this.nombre;
	}
	
	public int getPrecio()
	{
		return precioBase;
	}
	
	public String generarTextoFactura()
	{
		String factura = "Nombre: " + getNombre() + "|   Precio: $" + getPrecio()  +"|  Calorias: " +getCalorias()+"\n";
		return factura;
	}


	public int getCalorias() {
		return calorias;
	}



}
