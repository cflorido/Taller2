package Modelo;

import java.util.ArrayList;

import Modelo.Producto;
import Modelo.ProductoMenu;


public class Combo implements Producto{
	
	private String nombreCombo;
	private double descuento;
	private ArrayList<ProductoMenu> itemsCombo;



	public Combo(String nombre, double descuento)
	{
		this.nombreCombo = nombre;
		this.descuento = descuento;
		this.itemsCombo = new ArrayList<>();
	
	}	
	
	public String generarTextoFactura(){
		String factura = "Nombre: " + getNombre() + "|   Precio: $" + getPrecio()  +"|  Calorias: " +getCalorias()+"\n";
		return factura;
	}	
	
	public void agregarItemCombo(ProductoMenu itemCombo)
	{

		this.itemsCombo.add(itemCombo);
	}
	


	public String getNombre()
	{
		return this.nombreCombo;
	}
	
	public int getPrecio()
	{
		int contador = 0;
		for (ProductoMenu elementos:this.itemsCombo){

			contador +=elementos.getPrecio();

		}
		double preciofinal = contador*(1-this.descuento); 

		return (int)preciofinal;

	}
	
	public ArrayList<ProductoMenu> getItems()
	{
		return this.itemsCombo;
	}

	@Override
	public int getCalorias()
	{
		int contador = 0;
		for (ProductoMenu elementos:this.itemsCombo){

			contador +=elementos.getCalorias();

		}

		return (int)contador;

	}



}

