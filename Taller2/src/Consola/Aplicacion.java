package Consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;
import javax.swing.JOptionPane;

import java.io.InputStreamReader;

import java.util.Scanner;

import Modelo.Bebida;
import Modelo.Combo;
import Modelo.Ingrediente;
import Modelo.Pedido;
import Modelo.Producto;
import Modelo.ProductoAjustado;
import Modelo.Restaurante;





public class Aplicacion
{

	private Restaurante restaurante;

	
	public void ejecutarAplicacion()  throws FileNotFoundException, IOException
	{
		this.restaurante = new Restaurante();
		System.out.println("Cargando restaurante...");
		this.restaurante.cargarInformacionRestaurante("./data/ingredientes.txt","./data/menu.txt", "./data/combos.txt", "./data/Bebidas.txt");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				
				Scanner sc = new Scanner(System.in);
				System.out.print("\nPor favor seleccione una opción: ");
				int opcion_seleccionada = sc.nextInt();
				

				if (opcion_seleccionada == 1)
					ejecutarMenu();				
				else if (opcion_seleccionada == 2)
					ejecutarIniciarPedid();

					if (restaurante.getPedidoEnCurso()!=null){
				
						if (opcion_seleccionada == 3)
							ejecutarAgregarElemento();
						else if (opcion_seleccionada == 4)
							ejecutarAgregarBebida();
						else if (opcion_seleccionada == 5)
							ejecutarCerrarPedido();
						else if (opcion_seleccionada == 6)
							ejecutarConsultar();

					}

				else if (opcion_seleccionada == 7)
				
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}

				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}







	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar el menu");
		System.out.println("2. Iniciar un nuevo pedido");
		if (restaurante.getPedidoEnCurso()!=null){
		System.out.println("3. Agregar un elemento a un pedido");
		System.out.println("4. Agregar una bebida");
		System.out.println("5. Cerrar un pedido y guardar su factura");
		System.out.println("6. Consultar la información de un pedido dado su id");
		System.out.println("7. Salir de la aplicación");
	}
		else{
			System.out.println("3. Salir de la aplicación");
		}

	}

	private void ejecutarMenu()
	{
		System.out.println("-----------------Menú-----------------");
		String columna1 = "\nNombre";
		String columna2 = "$ Precio\n";
		System.out.println(String.format("%1$-25s %2$10s", columna1, columna2));
		System.out.println("--------------------------------------");
		for (Modelo.ProductoMenu cada_producto:this.restaurante.getMenuBase()) {
			System.out.println(String.format("%1$-25s %2$10d", cada_producto.getNombre(), cada_producto.getPrecio()));

		}
		
	}
	
	private void ejecutarIniciarPedid()
	{

		String cliente = input("Por favor ingrese su nombre: ");
		String direccion = input("Por favor ingrese su direccion: ");
  
		this.restaurante.iniciarPedido(cliente, direccion);
		System.out.println("¡El pedido se ha creado correctamente!");
	}




	/**
	 * 
	 */
	private void ejecutarAgregarElemento()
	{
		Modelo.Pedido pedidoEnCurso = this.restaurante.getPedidoEnCurso();
		System.out.println("Bienvenido, ¿Que tipo de elemento desea agregar?\n");	
		System.out.println("1. Combos de hamburguesa");	
		System.out.println("2. Productos individuales");
		System.out.println("3. Producto ajustado\n");

		int opcionSeleccionada = Integer.parseInt(input("\nPor favor, ingrese la opción que es de su interés: "));
		
		if (opcionSeleccionada == 1){
			System.out.println("\nUsted ha seleccionado agregar un combo");				
			System.out.println("\n¿Que ingrediente combo agregar?\n");					
			int numero4 = 1;
			
			for (Modelo.Combo productos: restaurante.getcombos()) {
				System.out.println(numero4+". "+productos.getNombre());
				numero4 += 1;
			}

			int opcionSeleccionada2 = Integer.parseInt(input("\nPor favor, ingrese la opción que es de su interés: "));			
			Combo comboo = restaurante.getcombos().get(opcionSeleccionada2-1);
			
			pedidoEnCurso.agregarProducto(comboo);
			System.out.println("!Se agrego el combo exitosamente¡");
		}

		if (opcionSeleccionada == 2) {
			System.out.println("\nUsted ha seleccionado agregar un ingrediente individual");				
			System.out.println("\n¿Que ingrediente desea agregar?\n");		
			int numero = 1;
			
			for (Modelo.ProductoMenu productos: restaurante.getMenuBase()) {
				System.out.println(numero+". "+productos.getNombre());
				numero += 1;
			}
			
			
			String opcionSeleccionada2 = input("\nPor favor, ingrese la opción que es de su interés: ");
			int numeroo = Integer.parseInt(opcionSeleccionada2);
			System.out.println("Agregando producto...");
				
			pedidoEnCurso.agregarProducto(this.restaurante.getMenuBase().get(numeroo-1));
			System.out.println("¡El ingrediente ha sido exitosamente agregado!");				
		
		}
		if (opcionSeleccionada == 3) {
			
			if (pedidoEnCurso.itemsPedido.size() ==0){
				System.out.println("No hay combos agregados");
			}
			else {
			
			pedidoEnCurso.consultarelementospedido(pedidoEnCurso);
			int seleccionado = Integer.parseInt(input("¿A que producto desea adicionarle ingrediente?: "));
			
			ArrayList<Producto> listaproductos = pedidoEnCurso.getproductospedido(pedidoEnCurso);
			Producto producto= listaproductos.get(seleccionado-1);
			
			

			ProductoAjustado ajustado = new ProductoAjustado(producto);
			pedidoEnCurso.agregarProducto(ajustado);

			int x = 0;				
			while (x == 0){
				int opcion = Integer.parseInt(input("¿Quiere agregar o eliminar (1 para agregar/0 para eliminar): "));
				int numero = 1;
				
				if (opcion == 1){

					for (Ingrediente ingredientes: restaurante.getIngrediente()) {
						System.out.println(numero+". "+ingredientes.getNombre());
						numero += 1;
					}

				int opcion2 = Integer.parseInt(input("¿Que ingrediente desea seleccionar?: "));			
				ArrayList<Ingrediente> ingredienteselecionardo = this.restaurante.getIngrediente();
				Ingrediente ingredienteselec = ingredienteselecionardo.get(opcion2-1);
				
				ajustado.agregarIngrediente(ingredienteselec);
				
				int y = Integer.parseInt(input("\n¡Quiere seguir modificando este producto? (1 si/0 no)\n"));
				if (y==0){
					x+=1;
				}

				}
				
				else if (opcion == 0){

					int numero3 = 1;

					System.out.println("Que ingrediente desea eliminar");

					for (Ingrediente ingredientes: ajustado.getingredientes(ajustado)) {
						System.out.println(numero3+". "+ingredientes.getNombre());
						numero3 += 1;
					}
					if (numero3 > 1){
				int opcion3 = Integer.parseInt(input("que ingrediente quiere seleccionar para eliminar: "));	
				ArrayList<Ingrediente> ingredienteselecionardo = this.restaurante.getIngrediente();
				Ingrediente ingredienteselec = ingredienteselecionardo.get(opcion3-1);
				ajustado.eliminarIngrediente(opcion3-1, ingredienteselec);
					}
					else if (numero3 == 1){

						System.out.println("No tiene ingredientes agregados para eliminar");
					}
					String y = input("\n¡Quiere seguir modificando este producto? (1 si/0 no)\n");
					if (y== "0"){
						x+=1;
					}
				}
			}
		}
		}
	}


	
	private void ejecutarAgregarBebida() {

		int num = 0;
		for (Bebida bebidas:restaurante.getbebidas()){
			
			System.out.println(num + ". "+ bebidas.getNombre());
			num +=1;
		}

		int x = Integer.parseInt(input("\nPor favor, ingrese la opción que es de su interés: "));
		Bebida seleccionada = restaurante.getbebidas().get(x);
		restaurante.getPedidoEnCurso().agregarProducto(seleccionada);
		System.out.println("!Se agregado la bebida correctamente¡");


	}

	/**
	 * @throws IOException
	 */
	private void ejecutarCerrarPedido() throws IOException
	{
		System.out.println("\nEl ID de su pedido es: "+ restaurante.getPedidoEnCurso().getIdPedido());

		System.out.println("\nSu pedido ha sido guardado");

		int centinela = 0;
		for(Pedido facturas:restaurante.getpedidos()){
			if (facturas.itemsPedido.equals(restaurante.getPedidoEnCurso().itemsPedido)){
				centinela +=1;
				if (centinela >=2){
					System.out.println("\nYa hay un pedido identico");	
				}			
			
			}

		}	
		this.restaurante.cerraryGuardarPedido();

	}
	private void ejecutarConsultar()
	{
		String nombreid = input("Ingrese el ID de la factura: ");
        File file = new File("data/facturas/" + nombreid + ".txt"); // Reemplaza "file.txt" con el nombre de tu archivo
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se ha encontrado.");
        }
	}


	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
	}

public static String input(String mensaje) {
        try {
            System.out.print(mensaje);
            BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
            String input= reader.readLine();
            return input;

        }
        catch (IOException e) {
            System.out.println("Error leyendo la entrada estandar");
            e.printStackTrace();
        }
        return null;
    }

}