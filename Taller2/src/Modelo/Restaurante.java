package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Modelo.Pedido;
import Modelo.ProductoMenu;
import Modelo.Combo;
import Modelo.Ingrediente;
import Modelo.Bebida;

public class Restaurante {

	private ArrayList<Ingrediente> ingredientes;
	private ArrayList<ProductoMenu> menuBase;
	private ArrayList<Combo> combos;
	private ArrayList<Pedido> pedidos;
	private Pedido pedidoEnCurso;
	private HashMap<String, ProductoMenu> hola;
	private ArrayList<Bebida> bebidas;	
	
	public Restaurante(){
		
		this.ingredientes = new ArrayList<>();
		this.menuBase = new ArrayList<>();
		this.combos = new ArrayList<>();
		this.pedidos = new ArrayList<>();
		this.pedidoEnCurso = null;
		this.hola = new HashMap<>();
		this.bebidas = new ArrayList<>();
	}
	
	public void iniciarPedido(String nombreCliente, String direccionCliente)
	{
		this.pedidoEnCurso = new Pedido(nombreCliente, direccionCliente);
		this.pedidos.add(pedidoEnCurso);

	}
	
	public void cerraryGuardarPedido() throws IOException
	{
		File factura = new File("data/facturas/" + this.pedidoEnCurso.getIdPedido() + ".txt");
		this.pedidoEnCurso.guardarFactura(factura);
		this.pedidoEnCurso = null;
	}
	/**
	 * @return
	 */
	public ArrayList<Pedido> getpedidos(){
		return pedidos;
	}
	public ArrayList<Bebida> getbebidas(){
		return bebidas;
	}
	public Pedido getPedidoEnCurso() {
		return pedidoEnCurso;
	}

	public void setPedidoEnCurso(Pedido pedidoEnCurso) {
		this.pedidoEnCurso = pedidoEnCurso;
	}

	public ArrayList<ProductoMenu> getMenuBase() {
		return menuBase;
	}
	public ArrayList<Ingrediente> getIngrediente() {
		return ingredientes;
	}
	public ArrayList<Combo> getcombos() {
		return combos;
	}	
	public void cargarInformacionRestaurante(String archivoIngredientes, String archivoMenu, String archivoCombos, String archivobebidas)throws FileNotFoundException, IOException
	{

		cargarIngrediente(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
		cargarbebidas(archivobebidas);
	}

	public void cargarIngrediente(String archivoIngrediente) {

		
        try (BufferedReader br = new BufferedReader(new FileReader(archivoIngrediente))) {
        	String line;
            while ((line = br.readLine()) != null) {
            	
                String[] parts = line.split(";");
 
                int integerNumber = Integer.parseInt(parts[1]);
    			Ingrediente itemingrediente = new Ingrediente(parts[0],integerNumber, Integer.parseInt(parts[2]));
    			ingredientes.add(itemingrediente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}





	public void cargarbebidas(String archivo) {

		
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        	String line;
            while ((line = br.readLine()) != null) {
            	
                String[] parts = line.split(";");
 
                int integerNumberr = Integer.parseInt(parts[1]);
    			Bebida itemingredientee = new Bebida(parts[0],integerNumberr, Integer.parseInt(parts[2]));
    			bebidas.add(itemingredientee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}







	public void cargarMenu(String archivoMenu) {
		

        try (BufferedReader br = new BufferedReader(new FileReader(archivoMenu))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                int integerNumber = Integer.parseInt(parts[1]);
    			ProductoMenu menu = new ProductoMenu(parts[0], integerNumber, Integer.parseInt(parts[2]));
    			menuBase.add(menu);
				hola.put(parts[0], menu);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
		

	public void cargarCombos(String archivoCombos) {
		

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCombos))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
    			String nombreCombo = parts[0];
    			double descuento = Double.parseDouble(parts[1].replace("%", ""))/100;
    			Combo combo = new Combo(nombreCombo, descuento);
				
				for (int i=2;i<parts.length;i++){
					ProductoMenu item = hola.get(parts[i]);	
					combo.agregarItemCombo(item);
				}
				combos.add(combo);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	


}

	