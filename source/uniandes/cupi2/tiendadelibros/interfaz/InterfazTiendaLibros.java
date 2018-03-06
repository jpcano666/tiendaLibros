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
package uniandes.cupi2.tiendadelibros.interfaz;

import java.awt.BorderLayout;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import uniandes.cupi2.tiendadelibros.mundo.Libro;
import uniandes.cupi2.tiendadelibros.mundo.TiendaDeLibros;

/**
 * Clase que representa la ventana principal de la tienda de libros.
 */
@SuppressWarnings("serial")
public class InterfazTiendaLibros extends JFrame
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * La tienda de libros que se est� administrando.
	 */
	private TiendaDeLibros tiendaDeLibros;

	// -----------------------------------------------------------------
	// Componentes de la interfaz
	// -----------------------------------------------------------------

	/**
	 * Panel que contiene la imagen.
	 */
	private PanelImagen panelImagen;

	/**
	 * Panel que muestra la caja de la tienda.
	 */
	private PanelCaja panelCaja;

	/**
	 * Panel que presenta los libros actuales en la tienda y permite hacer modificaciones en el inventario.
	 */
	private PanelBodega panelBodega;

	/**
	 * Panel para hacer consultas sobre los libros en la tienda.
	 */
	private PanelConsultas panelConsultas;

	/**
	 * Panel extensi�n.
	 */
	private PanelExtension panelExtension;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea la ventana principal de la aplicaci�n. <br>
	 * <b>post: </b> Se cre� la ventana con sus paneles, y la ventana est� conectada al mundo.
	 */
	public InterfazTiendaLibros( )
	{
		setTitle( "Tienda de Libros" );
		setSize( 750, 700 );
		setDefaultCloseOperation( EXIT_ON_CLOSE );

		tiendaDeLibros = new TiendaDeLibros( );

		setLayout( new BorderLayout( ) );

		JPanel centro = new JPanel( );
		centro.setLayout( new BorderLayout( ) );

		panelImagen = new PanelImagen( );
		add( panelImagen, BorderLayout.NORTH );

		panelCaja = new PanelCaja( );
		centro.add( panelCaja, BorderLayout.NORTH );
		panelBodega = new PanelBodega( this );
		centro.add( panelBodega, BorderLayout.CENTER );
		panelConsultas = new PanelConsultas( this );
		centro.add( panelConsultas, BorderLayout.SOUTH );
		add( centro, BorderLayout.CENTER );

		panelExtension = new PanelExtension( this );
		add( panelExtension, BorderLayout.SOUTH );

		setLocationRelativeTo( null );
		setResizable( false );

		panelCaja.actualizarCaja( tiendaDeLibros.darCaja( ) );
	}

	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Crea la ventana de di�logo para registrar un nuevo libro en la tienda. <br>
	 * <b>post: </b> Se crea la ventana de di�logo.
	 */
	public void dialogoRegistrarLibro( )
	{
		DialogoRegistrar dialogo = new DialogoRegistrar( this );
		dialogo.setVisible( true );
	}

	/**
	 * Registra el nuevo libro en la tienda de libros. <br>
	 * <b>post: </b> Si los datos ingresados por el usuario son v�lidos queda registrado el libro, de lo contrario se le informa al usuario.
	 * @param pIsbn El ISBN del nuevo libro.
	 * @param pTitulo El t�tulo del nuevo libro.
	 * @param pPrecioCompra El precio de compra del nuevo libro.
	 * @param pPrecioVenta El precio de venta del nuevo libro.
	 * @param pRutaImagen La ruta de la imagen del libro.
	 * @param pCategoria Categor�a a la que pertenece el libro.
	 * @param pDialogo Ventana de di�logo de la cual se obtuvo la informaci�n.
	 */
	public void registrarLibro( String pTitulo, String pIsbn, String pPrecioCompra, String pPrecioVenta, String pCategoria, String pRutaImagen, DialogoRegistrar pDialogo )
	{
		double precioCompra = 0;
		double precioVenta = 0;
		boolean condicionesValidas = true;

		if( pIsbn.equals( "" ) || pTitulo.equals( "" ) || pPrecioCompra.equals( "" ) || pPrecioVenta.equals( "" ) )
		{
			condicionesValidas = false;
			JOptionPane.showMessageDialog( this, "Debe llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE );
		}

		if( condicionesValidas )
		{
			try
			{
				precioCompra = Double.parseDouble( pPrecioCompra );
			}
			catch( Exception e )
			{
				condicionesValidas = false;
				JOptionPane.showMessageDialog( this, "El precio de compra debe ser num�rico", "Error", JOptionPane.ERROR_MESSAGE );
			}
		}

		if( condicionesValidas )
		{
			try
			{
				precioVenta = Double.parseDouble( pPrecioVenta );
			}
			catch( Exception e )
			{
				condicionesValidas = false;
				JOptionPane.showMessageDialog( this, "El precio de venta debe ser num�rico", "Error", JOptionPane.ERROR_MESSAGE );
			}
		}

		if( condicionesValidas )
		{
			if( precioCompra <= 0 )
			{
				JOptionPane.showMessageDialog( this, "Ingrese un precio de compra v�lido", "Error", JOptionPane.ERROR_MESSAGE );
			}
			else if( precioVenta <= 0 )
			{
				JOptionPane.showMessageDialog( this, "Ingrese un precio de venta v�lido", "Error", JOptionPane.ERROR_MESSAGE );
			}
			else if( pRutaImagen.equals( "" ) )
			{
				JOptionPane.showMessageDialog( this, "Elija una imagen", "Error", JOptionPane.ERROR_MESSAGE );
			}
			else
			{
				Libro registrado = tiendaDeLibros.registrarLibro( pTitulo, pIsbn, precioVenta, precioCompra, pCategoria, pRutaImagen );
				if( registrado == null )
				{
					JOptionPane.showMessageDialog( this, "Ya existe un libro con ese ISBN", "Error", JOptionPane.ERROR_MESSAGE );
				}
				else
				{
					pDialogo.dispose( );
					panelBodega.refrescar( tiendaDeLibros.darCatalogo( ) );
					panelBodega.cambiarSeleccionado( registrado );
					panelConsultas.limpiar( );
					JOptionPane.showMessageDialog( this, "Se agreg� el libro exitosamente", "�xito", JOptionPane.INFORMATION_MESSAGE );
				}

			}
		}
	}

	/**
	 * M�todo para aumentar las unidades disponibles de un libro existente. <br>
	 * <b>post: </b> La cantidad actual del libro se aumenta por el n�mero de unidades dispuesto por el usuario.
	 * @param pLibro Libro del cual se desea adquirir m�s unidades.
	 */
	public void abastecerLibro( Libro pLibro )
	{
		if( pLibro != null )
		{
			String respuesta = JOptionPane.showInputDialog( this, "Introduzca la cantidad de unidades", "Abastecer libro", JOptionPane.QUESTION_MESSAGE );
			if( respuesta != null )
			{
				try
				{
					int unidades = Integer.parseInt( respuesta );
					if( unidades <= 0 )
					{
						JOptionPane.showMessageDialog( this, "La cantidad debe ser mayor a cero. ", "Abastecer libro", JOptionPane.ERROR_MESSAGE );
					}
					else
					{
						Date fechaActual = new Date( );
						boolean exito = tiendaDeLibros.abastecer( pLibro.darIsbn( ), unidades, fechaActual );
						if( exito )
						{
							panelBodega.refrescar( tiendaDeLibros.darCatalogo( ) );
							panelBodega.cambiarSeleccionado( pLibro );
							panelCaja.actualizarCaja( tiendaDeLibros.darCaja( ) );
							panelConsultas.limpiar( );
						}
						else
						{
							double valor = pLibro.darPrecioCompra( ) * unidades;
							NumberFormat formatter = NumberFormat.getInstance( );
							String moneyString = formatter.format( valor );

							JOptionPane.showMessageDialog( this, "No hay dinero suficiente en caja para realizar esta compra. Valor: $" + moneyString, "Abastecer libro", JOptionPane.ERROR_MESSAGE );
						}
					}
				}
				catch( NumberFormatException e )
				{
					JOptionPane.showMessageDialog( this, "Introduzca una cantidad v�lida", "Abastecer libro", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog( this, "Seleccione un libro", "Abastecer libro", JOptionPane.ERROR_MESSAGE );
		}
	}

	/**
	 * M�todo para disminuir las unidades disponibles de un libro existente. <br>
	 * <b>post: </b> La cantidad actual del libro se disminuye por el n�mero de unidades que el usuario desea vender, si no hay unidades suficientes se informa al usuario.
	 * @param pLibro Libro del cual se desea vender unidades.
	 */
	public void venderLibro( Libro pLibro )
	{
		if( pLibro != null )
		{
			String respuesta = JOptionPane.showInputDialog( this, "Introduzca la cantidad de unidades", "Vender libro", JOptionPane.QUESTION_MESSAGE );
			if( respuesta != null )
			{
				try
				{
					int unidades = Integer.parseInt( respuesta );
					if( unidades <= 0 )
					{
						JOptionPane.showMessageDialog( this, "La cantidad debe ser mayor a cero. ", "Vender libro", JOptionPane.ERROR_MESSAGE );
					}
					else
					{
						Date fechaActual = new Date( );
						boolean vender = tiendaDeLibros.vender( pLibro.darIsbn( ), unidades, fechaActual );
						if( !vender )
						{
							JOptionPane.showMessageDialog( this, "No hay suficientes libros para vender", "Vender libro", JOptionPane.ERROR_MESSAGE );
						}
						else
						{
							panelBodega.refrescar( tiendaDeLibros.darCatalogo( ) );
							panelCaja.actualizarCaja( tiendaDeLibros.darCaja( ) );
							panelBodega.cambiarSeleccionado( pLibro );
							panelConsultas.limpiar( );
						}
					}
				}
				catch( NumberFormatException e )
				{
					JOptionPane.showMessageDialog( this, "Introduzca una cantidad v�lida", "Vender libro", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog( this, "Seleccione un libro", "Vender libro", JOptionPane.ERROR_MESSAGE );
		}
	}

	/**
	 * M�todo para eliminar el libro seleccionado por el usuario. <br>
	 * <b>post: </b> Se elimina el libro de la tienda si es posible. En caso contrario, se informa al usuario.
	 * @param pLibro Libro a eliminar.
	 */
	public void eliminarLibro( Libro pLibro )
	{
		if( pLibro != null )
		{
			boolean exito = tiendaDeLibros.eliminarLibro( pLibro.darIsbn( ) );
			if( !exito )
			{
				JOptionPane.showMessageDialog( this, "No fue posible eliminar el libro, a�n posee unidades", "Eliminar libro", JOptionPane.ERROR_MESSAGE );
			}
			else
			{
				actualizar( );
				JOptionPane.showMessageDialog( this, "Se ha eliminado el libro", "Eliminar libro", JOptionPane.INFORMATION_MESSAGE );
			}
		}
		else
		{
			JOptionPane.showMessageDialog( this, "Seleccione un libro", "Eliminar libro", JOptionPane.ERROR_MESSAGE );
		}

	}

	/**
	 * Busca el libro que su ISBN coincide con el ingresado por el usuario. <br>
	 * <b>post: </b> Se refresca el panel con el libro encontrado. Si no lo encuentra notifica al usuario.
	 */
	public void buscarPorISBN( )
	{
		String respuesta = JOptionPane.showInputDialog( this, "Introduzca el ISBN", "Buscar libro", JOptionPane.QUESTION_MESSAGE );
		if( respuesta != null )
		{
			if( !respuesta.equals( "" ) )
			{
				Libro encontrado = tiendaDeLibros.buscarLibroPorISBN( respuesta );
				if( encontrado == null )
				{
					JOptionPane.showMessageDialog( this, "No se encontr� ning�n libro con ese ISBN", "Buscar libro", JOptionPane.ERROR_MESSAGE );
				}
				else
				{
					panelConsultas.actualizar( encontrado );
				}
			}
			else
			{
				JOptionPane.showMessageDialog( this, "Introduzca el ISBN", "Buscar libro", JOptionPane.ERROR_MESSAGE );
			}
		}

	}

	/**
	 * Busca el libro que su t�tulo coincide con el ingresado por el usuario. <br>
	 * <b>post: </b> Se refresca el panel con el libro encontrado. Si no lo encuentra notifica al usuario.
	 */
	public void buscarPorTitulo( )
	{
		String respuesta = JOptionPane.showInputDialog( this, "Introduzca el t�tulo", "Buscar por t�tulo", JOptionPane.QUESTION_MESSAGE );
		if( respuesta != null )
		{
			if( !respuesta.equals( "" ) )
			{
				Libro encontrado = tiendaDeLibros.buscarLibroPorTitulo( respuesta );
				if( encontrado == null )
				{
					JOptionPane.showMessageDialog( this, "No se encontr� ning�n libro con ese t�tulo", "Buscar por t�tulo", JOptionPane.ERROR_MESSAGE );
				}
				else
				{
					panelConsultas.actualizar( encontrado );
				}
			}
			else
			{
				JOptionPane.showMessageDialog( this, "Introduzca el t�tulo", "Buscar por t�tulo", JOptionPane.ERROR_MESSAGE );
			}
		}

	}

	/**
	 * Busca el libro con el mayor precio en la tienda. <br>
	 * <b>post: </b> Se refresca el panel con el libro encontrado.
	 */
	public void buscarMasCostoso( )
	{
		Libro encontrado = tiendaDeLibros.darLibroMasCostoso( );
		panelConsultas.actualizar( encontrado );
	}

	/**
	 * Busca el libro con el menor precio en la tienda. <br>
	 * <b>post: </b> Se refresca el panel con el libro encontrado.
	 */
	public void buscarMasEconomico( )
	{
		Libro encontrado = tiendaDeLibros.darLibroMasEconomico( );
		panelConsultas.actualizar( encontrado );
	}

	/**
	 * Busca el libro con el mayor n�mero de unidades vendidas en la tienda. <br>
	 * <b>post: </b> Se refresca el panel con el libro encontrado.
	 */
	public void buscarMasVendido( )
	{
		Libro encontrado = tiendaDeLibros.darLibroMasVendido( );
		panelConsultas.actualizar( encontrado );
	}

	/**
	 * Actualiza la informaci�n.
	 */
	public void actualizar( )
	{
		ArrayList<Libro> catalogo = tiendaDeLibros.darCatalogo( );
		panelBodega.refrescar( catalogo );
		if( !catalogo.isEmpty( ) )
		{
			panelBodega.cambiarSeleccionado( catalogo.get( 0 ) );
		}
		panelConsultas.limpiar( );
		panelCaja.actualizarCaja( tiendaDeLibros.darCaja( ) );
	}

	// -----------------------------------------------------------------
	// Puntos de Extensi�n
	// -----------------------------------------------------------------

	/**
	 * Este m�todo ejecuta la opci�n 1.
	 */
	public void reqFuncOpcion1( )
	{
		String respuesta = tiendaDeLibros.metodo1( );
		actualizar( );
		JOptionPane.showMessageDialog( this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}

	/**
	 * Este m�todo ejecuta la opci�n 2.
	 */
	public void reqFuncOpcion2( )
	{
		String respuesta = tiendaDeLibros.metodo2( );
		actualizar( );
		JOptionPane.showMessageDialog( this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}

	// -----------------------------------------------------------------
	// Main
	// -----------------------------------------------------------------

	/**
	 * Ejecuta la aplicaci�n.
	 * @param pArgs Par�metros de la ejecuci�n. No son necesarios.
	 */
	public static void main( String[] pArgs )
	{
		try
		{
			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );

			InterfazTiendaLibros ventana = new InterfazTiendaLibros( );
			ventana.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}
