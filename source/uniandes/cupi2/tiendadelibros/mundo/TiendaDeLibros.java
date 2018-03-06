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
 * Clase que representa la tienda de libros.
 */
public class TiendaDeLibros
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Atributo que representa el primer libro de la tienda
	 */
	private Libro primerLibro;

	/**
	 * 
	 */
	public static final String[] CATEGORIAS = {"Cuento Infantil", "Romance", "Novela Hist�rica", "Terror", "Biograf�a", "Ciencia Ficci�n"};

	/**
	 * Cantidad actual en la caja de la tienda de libros.
	 */
	private double caja;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Crea una nueva tienda de libros. <br>
	 * <b>post:</b> El primer libro del cat�logo fue inicializado en null. <br>
	 * La caja fue inicializada en 1000000.
	 */
	public TiendaDeLibros( )
	{
		caja = 1000000;
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Retorna el cat�logo de libros.
	 * @return El cat�logo de libros.
	 */
	public ArrayList<Libro> darCatalogo( )
	{
		ArrayList<Libro> catalogo = new ArrayList<Libro>();

		Libro actual = primerLibro;
		while(actual != null)
		{
			catalogo.add(actual);
			actual = actual.darSiguiente();
		}
		return catalogo;
	}

	/**
	 * Retorna el primer libro de la lista.
	 * @return El primer libro de la lista.
	 */
	public Libro darPrimerLibro( )
	{
		return primerLibro;
	}

	/**
	 * Retorna el valor actual en la caja.
	 * @return El valor actual en caja.
	 */
	public double darCaja( )
	{
		return caja;
	}

	/**
	 * Modifica el valor actual de la caja.
	 * @param pCaja El nuevo valor de la caja.
	 */
	public void cambiarCaja( double pCaja )
	{
		caja = pCaja;
	}

	/**
	 * Busca un libro por el t�tulo dado por par�metro.
	 * @param pTitulo El titulo del libro que se quiere buscar. pTitulo != null && pTitulo != "".
	 * @return Si existe un libro con ese t�tulo, lo retorna. En caso contrario, retorna null.
	 */
	public Libro buscarLibroPorTitulo( String pTitulo )
	{
		Libro buscado = null;
		Libro actual = primerLibro;

		while(actual != null)
		{
			if(actual.darTitulo().equals(pTitulo))
			{
				buscado = actual;
			}
			actual = actual.darSiguiente();
		}
		return buscado;
	}

	/**
	 * Busca un libro por el c�digo ISBN dado por par�metro.
	 * @param pIsbn El c�digo ISBN del libro que se quiere buscar. pIsbn != null && pIsbn != "".
	 * @return Si existe un libro con ese ISBN, lo retorna. En caso contrario, retorna null.
	 */
	public Libro buscarLibroPorISBN( String pIsbn )
	{
		Libro buscado = null;
		Libro actual = primerLibro;

		while(actual != null)
		{
			if(actual.darIsbn().equals(pIsbn))
			{
				buscado = actual;
			}
			actual = actual.darSiguiente();
		}

		return buscado;
	}

	/**
	 * Registra un libro en la tienda de libros. <br>
	 * <b>post: </b> El libro fue creado y agregado al cat�logo alfab�ticamente.
	 * @param pTitulo El t�tulo del libro que se quiere agregar. pTitulo != null && pTitulo != "".
	 * @param pIsbn El c�digo ISBN del libro que se quiere agregar. pIsbn != null && pIsbn != "".
	 * @param pPrecioVenta El precio de venta del libro que se quiere agregar. pPrecioVenta > 0.
	 * @param pPrecioCompra El precio de compra del libro que se quiere agregar. pPrecioCompra > 0.
	 * @param pCategoria Categor�a a la que pertenece el libro. pCategoria != null && pCategoria != "". pCategoria pertenece a CATEGORIAS.
	 * @param pRutaImagen La ruta de la imagen del libro. pRutaImagen != null && pRutaImagen != "".
	 * @return El nuevo libro registrado en caso de que si se haya podido realizar la operaci�n, null en caso de que el libro ya exista.
	 */
	public Libro registrarLibro( String pTitulo, String pIsbn, double pPrecioVenta, double pPrecioCompra, String pCategoria, String pRutaImagen )
	{
		// Comprueba si el libro con ese ISBN no ha sido creado
		Libro buscado = buscarLibroPorISBN( pIsbn );
		Libro nuevo = null;
		if( buscado == null )
		{
			if(primerLibro == null)
			{
				primerLibro = new Libro(pTitulo, pIsbn, pPrecioVenta, pPrecioCompra, pCategoria, pRutaImagen);
				nuevo = primerLibro;
			}
			else
			{
				nuevo = new Libro(pTitulo, pIsbn, pPrecioVenta, pPrecioCompra, pCategoria, pRutaImagen);
				if(nuevo.compararPorTitulo(primerLibro) < 0)
				{
					nuevo.cambiarSiguiente(primerLibro);
					primerLibro.cambiarAnterior(nuevo);
					primerLibro = nuevo;
				}
				else
				{
					Libro actual = primerLibro;
					Libro ant = null;
					while(actual != null && nuevo.compararPorTitulo(actual) > 0)
					{
						ant = actual;
						actual = actual.darSiguiente();						
					}
					ant.cambiarSiguiente(nuevo);
					nuevo.cambiarAnterior(ant);
					nuevo.cambiarSiguiente(actual);
					if(actual != null)
					{
						actual.cambiarAnterior(nuevo);
					}
				}				
			}				
		}
		return nuevo;
	}

	/**
	 * Elimina un libro con el ISBN dado por par�metro. Si la cantidad actual de ejemplares es mayor a cero, no se eliminar� el libro. <br>
	 * <b>post: </b> El libro fue eliminado del cat�logo.
	 * @param pIsbn El ISBN del libro que se quiere eliminar. pIsbn != null && pIsbn != "".
	 * @return Retorna true si se pudo eliminar, false si el libro no existe o si la cantidad actual de ejemplares es mayor a cero.
	 */
	public boolean eliminarLibro( String pIsbn )
	{
		boolean eliminado = false;

		if(pIsbn.equals(primerLibro.darIsbn()))
		{
			primerLibro = primerLibro.darSiguiente();
			if(primerLibro != null)
			{
				primerLibro.cambiarAnterior(null);
			}
			eliminado = true;
		}	
		else
		{
			Libro nuevo = this.buscarLibroPorISBN(pIsbn);
			if(nuevo != null)
			{
				Libro anterior = nuevo.darAnterior();

				anterior.desconectarSiguiente();
				if(nuevo.darSiguiente() != null)
				{
					Libro siguiente = nuevo.darSiguiente();
					siguiente.cambiarAnterior(anterior);
				}
				eliminado = true;
			}
		}
		return eliminado;
	}

	/**
	 * Abastece un libro con la cantidad de ejemplares dada por par�metro. <br>
	 * <b>post: </b> Se abasteci� el libro con el ISBN dado y se disminuy� la cantidad en caja con el precio final del abastecimiento.
	 * @param pIsbn El C�digo ISBN del libro que se quiere abastecer. pIsbn!= null && pISBN != "".
	 * @param pCantidad La cantidad de ejemplares que se van a abastecer. pCantidad >= 0.
	 * @param pFecha La fecha en la que se realiz� la transacci�n. pFecha != "" && pFecha != null.
	 * @return Retorna true si se pudo abastecer el libro, false en caso contrario.
	 */
	public boolean abastecer( String pIsbn, int pCantidad, Date pFecha )
	{
		Libro buscado = buscarLibroPorISBN( pIsbn );
		boolean seAbastecio = false;
		if( buscado != null && caja >= pCantidad * buscado.darPrecioCompra( ) )
		{
			buscado.abastecer( pCantidad, pFecha );
			// Disminuye la caja con el valor total de los ejemplares abastecidos
			caja -= pCantidad * buscado.darPrecioCompra( );
			seAbastecio = true;
		}
		return seAbastecio;
	}

	/**
	 * Vende la cantidad de ejemplares del libro con el ISBN dado por par�metro. <br>
	 * <b>post: </b> Se vendi� el libro con el ISBN dado y se aument� la cantidad en caja con el precio final de la venta.
	 * @param pIsbn El C�digo ISBN del libro que se quiere vender. pIsbn != null && pIsbn != "".
	 * @param pCantidad La cantidad de ejemplares que se van a vender.
	 * @param pFecha La fecha en la que se realiz� la transacci�n. pFecha != "" && pFecha != null.
	 * @return Retorna true en caso de que se pueda vender la cantidad de ejemplares dada por par�metro. False en caso contrario.
	 */
	public boolean vender( String pIsbn, int pCantidad, Date pFecha )
	{
		boolean vendido = false;
		Libro buscado = buscarLibroPorISBN( pIsbn );
		if( buscado != null )
		{
			vendido = buscado.vender( pCantidad, pFecha );
			// Aumenta la caja con el valor total de los ejemplares vendidos
			caja += pCantidad * buscado.darPrecioVenta( );
		}
		return vendido;
	}

	/**
	 * Busca el libro m�s costoso, es decir el libro con el mayor precio de venta en el cat�logo.
	 * @return El libro m�s costoso. En caso de que el catalogo est� vac�o, retorna null
	 */
	public Libro darLibroMasCostoso( )
	{
		// Guarda el libro m�s costoso y su precio
		Libro actual = primerLibro;
		Libro masCostoso = primerLibro;
		while(actual != null)
		{
			if(actual.darPrecioVenta() > masCostoso.darPrecioVenta())
			{
				masCostoso = actual;
			}
			actual = actual.darAnterior();
		}
		return masCostoso;
	}

	/**
	 * Busca el libro m�s econ�mico en el cat�logo de libros. El libros m�s econ�mico es el libro con el menor precio de venta.
	 * @return El libro menos costoso. En caso de que el cat�logo est� vac�o, retorna null.
	 */
	public Libro darLibroMasEconomico( )
	{
		Libro menosCostoso = primerLibro;
		Libro actual = primerLibro;

		while(actual != null)
		{
			if(actual.darPrecioVenta() < menosCostoso.darPrecioVenta())
			{
				menosCostoso = actual;
			}
			actual = actual.darSiguiente();
		}
		return menosCostoso;
	}

	/**
	 * Busca el libro m�s vendido, es decir el libro con m�s transacciones de tipo VENTA.
	 * @return El libro m�s vendido. En caso de que el cat�logo est� vac�o, retorna null.
	 */
	public Libro darLibroMasVendido( )
	{
		Libro actual = primerLibro;
		Libro elMas = null;

		int masVentas = 0;

		while(actual != null)
		{
			int ventas = 0;
			for(Transaccion a : actual.darTransacciones())
			{
				if(a.darTipo().equals(Transaccion.Tipo.VENTA))
				{
					ventas += a.darCantidad();
				}
			}
			if(ventas > masVentas)
			{
				masVentas = ventas;
				elMas = actual;
			}
			actual = actual.darSiguiente();
		}
		return elMas;
	}



	/**
	 * Calcula la cantidad de transacciones de abastecimiento del libro con el ISBN dado por par�metro.
	 * @param pIsbn El c�digo ISBN del libro que se quiere buscar. pIsbn != null && pIsbn != "".
	 * @return La cantidad de transacciones de abastecimiento. En caso de que no encuentre el libro o no tenga transacciones, retorna cero.
	 */
	public int darCantidadTransaccionesAbastecimiento( String pIsbn )
	{
		// Busca el libro con el ISBN dado por par�metro
		Libro buscado = buscarLibroPorISBN( pIsbn );
		int cantidadTransacciones = 0;
		// Verifica que si exista el libro
		if( buscado != null )
		{
			// Guarda las transacciones del libro buscado
			ArrayList<Transaccion> transacciones = buscado.darTransacciones( );
			for( int i = 0; i < transacciones.size( ); i++ )
			{
				Transaccion actual = transacciones.get( i );
				// Verifica y cuenta las transacciones de tipo ABASTECIMIENTO
				if( actual.darTipo( ).equals( Transaccion.Tipo.ABASTECIMIENTO ) )
				{
					cantidadTransacciones++;
				}
			}
		}
		return cantidadTransacciones;
	}

	// -----------------------------------------------------------------
	// Puntos de Extensi�n
	// -----------------------------------------------------------------

	/**
	 * M�todo para la extensi�n 1.
	 * @return Respuesta 1.
	 */
	public String metodo1( )
	{
		return "Respuesta 1";
	}

	/**
	 * M�todo para la extensi�n 2.
	 * @return Respuesta 2.
	 */
	public String metodo2( )
	{
		return "Respuesta 2";
	}

}
