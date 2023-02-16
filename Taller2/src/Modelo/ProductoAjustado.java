package Modelo;

import java.util.ArrayList;



public class ProductoAjustado implements Producto
{
	private Producto base;
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	private int calorias;

	public ProductoAjustado(Producto producto)
	{
		this.base =  producto;
		this.agregados = new ArrayList<>();
		this.eliminados = new ArrayList<>();

	}

	private static void add(Producto producto) {
	}

	public ArrayList<Ingrediente> getingredientes(ProductoAjustado producto){

		return producto.agregados;
	}


	public String getNombre()
	{
		return base.getNombre();
	}
	
	public int getPrecio()
	{
		int precioproducto = base.getPrecio();
		int conta = 0;
		for (Ingrediente agregadoss : agregados){

			conta += agregadoss.getCostoAdicional();
		}
		
		return precioproducto+conta;
	}
	
	public void agregarIngrediente(Ingrediente agregado)
	{
		agregados.add(agregado);
		

		
	}
	
	public void eliminarIngrediente(int eliminado, Ingrediente ingreeli)
	{
		agregados.remove(eliminado);
		eliminados.add(ingreeli);
	}
	
	public String generarTextoFactura()
	{
		String factura = "Nombre: " + getNombre() + "|   Precio: $" + getPrecio()  +"|  Calorias: " +getCalorias()+"\n";
		return factura;	
	}

	@Override
	public int getCalorias() {
		int  calorias = base.getCalorias();
		int contaa = 0;
		for (Ingrediente agregadoss : agregados){

			contaa += agregadoss.getCalorias();
		}
		
		return calorias+contaa;
	
	}	


}