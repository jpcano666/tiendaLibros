/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_tiendaDeLibros
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.tiendadelibros.mundo;

import java.util.*;

/**
 * Clase que representa el libro.
 */
public class Libro
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * El c�digo ISBN del libro.
	 */
	private String isbn;

	/**
	 * El t�tulo del libro.
	 */
	private String titulo;

	/**
	 * El precio de venta del libro.
	 */
	private double precioVenta;

	/**
	 * El precio de compra del libro.
	 */
	private double precioCompra;

	/**
	 * Cantidad actual de ejemplares del libro.
	 */
	private int cantidadActual;

	/**
	 * Ruta de la imagen del libro.
	 */
	private String rutaImagen;

	/**
	 * Referencia al libro anterior.
	 */
	private Libro anterior;

	/**
	 * Referencia al libro siguiente.
	 */
	private Libro siguiente;

	/**
	 * Representa la categor�a del libro
	 */
	private String categoria;

	/**
	 * Representa la primera transacci�n.
	 */
	private Transaccion primeraTransaccion;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Crea un libro con su t�tulo, isbn, autor y precioVenta. <br>
	 * <b>post: </b>El t�tulo, el isbn, el precio de venta, el precio de compra, la categor�a y la ruta de la imagen se inicializaron con los valores dados. <br>
	 * La cantidad actual se inicializ� en cero y la primera transacci�n se inicializ� en null. El libro anterior y siguiente se inicializaron en null.
	 * @param pTitulo T�tulo del libro. pTitulo != null && pTitulo != "".
	 * @param pIsbn C�digo ISBN del libro. pIsbn != null && pIsbn != "".
	 * @param pPrecioVenta precioVenta del libro. pPrecioVenta > 0.
	 * @param pPrecioCompra precioVenta del libro. pPrecioCompra > 0.
	 * @param pCategoria Categor�a a la que pertenece el libro. pCategoria != null && pCategoria != "". pCategoria pertenece a CATEGORIAS.
	 * @param pRutaImagen Ruta de la imagen del libro. pRutaImagen != null && pRutaImagen != "".
	 */
	public Libro( String pTitulo, String pIsbn, double pPrecioVenta, double pPrecioCompra, String pCategoria, String pRutaImagen )
	{
		titulo = pTitulo;
		isbn = pIsbn;
		precioVenta = pPrecioVenta;
		precioCompra = pPrecioCompra;
		categoria = pCategoria;
		rutaImagen = pRutaImagen;
		cantidadActual = 0;
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Retorna el c�digo IBSN del libro.
	 * @return El c�digo ISBN del libro.
	 */
	public String darIsbn( )
	{
		return isbn;
	}

	/**
	 * Retorna la categor�a del libro.
	 * @return La categor�a del libro.
	 */
	public String darCategoria( )
	{
		return categoria;
	}

	/**
	 * Retorna el anterior libro de la lista.
	 * @return Anterior libro.
	 */
	public Libro darAnterior()
	{
		return anterior;
	}

	/**
	 * Retorna el siguiente libro de la lista.
	 * @return Siguiente libro.
	 */
	public Libro darSiguiente()
	{
		return siguiente;
	}

	/**
	 * Retorna la primera transacci�n del libro.
	 * @return primera transacci�n del libro.
	 */
	public Transaccion darPrimeraTransaccion()
	{
		return primeraTransaccion;
	}

	/**
	 * Cambia el anterior libro de la lista.
	 * @param pLibro Libro a asignar como anterior.
	 */
	public void cambiarAnterior(Libro pLibro)
	{
		anterior = pLibro;
	}
	
	public void desconectarSiguiente()
	{
		siguiente = siguiente.darSiguiente();
	}

	/**
	 * Cambia el siguiente libro de la lista.
	 * @param pLibro Libro a asignar como siguiente.
	 */
	public void cambiarSiguiente(Libro pLibro)
	{
		siguiente = pLibro;
	}

	/**
	 * Agrega una transacci�n al final de la lista de transacciones.
	 * @param pTransaccion transaccion a agregar.
	 */
	public void agregarTransaccion( Transaccion pTransaccion )
	{
		Transaccion nueva = primeraTransaccion;
		
		while(nueva.darSiguiente() != null)
		{
			nueva = nueva.darSiguiente();
		}		
		nueva.cambiarSiguiente(pTransaccion);
	}

	/**
	 * Compara dos libros por su t�tulo. Si los t�tulos son iguales los compara por isbn.
	 * @param pLibro Libro con el cual se desea comparar. pLibro != null.
	 * @return 0 si los dos libros son iguales, 1 si el libro actual es mayor, -1 si es menor.
	 */
	public int compararPorTitulo( Libro pLibro )
	{
		int comparacion = titulo.compareToIgnoreCase( pLibro.darTitulo( ) );
		if( comparacion == 0 )
		{
			comparacion = 0;
		}
		else if( comparacion > 0 )
		{
			comparacion = 1;
		}
		else
		{
			comparacion = -1;
		}
		return comparacion;
	}

	/**
	 * Retorna el t�tulo del libro.
	 * @return El t�tulo del libro.
	 */
	public String darTitulo( )
	{
		return titulo;
	}

	/**
	 * Retorna el precio de venta del libro.
	 * @return El precio de venta del libro.
	 */
	public double darPrecioVenta( )
	{
		return precioVenta;
	}

	/**
	 * Retorna el precio de compra del libro.
	 * @return El precio de compra del libro.
	 */
	public double darPrecioCompra( )
	{
		return precioCompra;
	}

	/**
	 * Retorna la cantidad actual de ejemplares del libro.
	 * @return La cantidad actual de ejemplares del libro.
	 */
	public int darCantidadActual( )
	{
		return cantidadActual;
	}

	/**
	 * Retorna la ruta de la imagen del libro.
	 * @return La ruta de la imagen del libro.
	 */
	public String darRutaImagen( )
	{
		return rutaImagen;
	}

	/**
	 * Vende la cantidad de ejemplares que entra por par�metro.
	 * @param pCantidad La cantidad de ejemplares que se van a vender.
	 * @param pFecha La fecha en la que se realiz� la transacci�n. pFecha != null.
	 * @return Retorna true en caso de que se pueda vender la cantidad que entra por par�metro. Retorna false en caso de que la cantidad sea mayor a la actual.
	 */
	public boolean vender( int pCantidad, Date pFecha )
	{
		boolean vendido = false;
		// Verifica que la cantidad que entra por par�metro sea menor o igual a la cantidad igual
		if( pCantidad <= cantidadActual )
		{
			// Disminuye la cantidad actual de ejemplares
			cantidadActual -= pCantidad;
			// Crea una nueva transacci�n con el tipo venta y la fecha actual
			Transaccion nueva = new Transaccion( Transaccion.Tipo.VENTA, pCantidad, pFecha );
			// Agrega la nueva transacci�n a la colecci�n de transacciones del libro
			this.agregarTransaccion(nueva);
			
			vendido = true;
		}
		return vendido;
	}

	/**
	 * Abastece la cantidad de ejemplares que entra por par�metro.
	 * @param pCantidad La cantidad de ejemplares que se van a agregar a la cantidad actual.
	 * @param pFecha La fecha en la que se realiz� la transacci�n. pFecha != null.
	 */
	public void abastecer( int pCantidad, Date pFecha )
	{
		// Aumenta la cantidad actual de ejemplares
		cantidadActual += pCantidad;
		// Crea una nueva transacci�n con el tipo abastecimiento y la fecha actual		
		if(primeraTransaccion == null)
		{
			primeraTransaccion = new Transaccion( Transaccion.Tipo.ABASTECIMIENTO, pCantidad, pFecha );
		}
		else
		{
			Transaccion actual = primeraTransaccion;
			Transaccion nueva = new Transaccion( Transaccion.Tipo.ABASTECIMIENTO, pCantidad, pFecha );
			while(actual.darSiguiente() != null)
			{
				actual = actual.darSiguiente();
			}
			actual.cambiarSiguiente(nueva);
		}
		// Agrega la nueva transacci�n a la colecci�n de transacciones del libro

	}

	/**
	 * Retorna la colecci�n de transacciones.
	 * @return La colecci�n de transacciones.
	 */
	public ArrayList<Transaccion> darTransacciones( )
	{
		ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
		Transaccion nueva = primeraTransaccion;
		while(nueva != null)
		{
			transacciones.add(nueva);
			nueva = nueva.darSiguiente();
		}
		return transacciones;
	}

	/**
	 * Retorna la representaci�n en cadena de caracteres un objeto Libro.
	 * @return La representaci�n en cadena de caracteres del objeto Libro.
	 */
	public String toString( )
	{
		String representacion = titulo + " (" + isbn + ") - " + categoria;
		return representacion;
	}

}
