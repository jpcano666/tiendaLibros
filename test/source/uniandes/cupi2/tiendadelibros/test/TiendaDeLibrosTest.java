/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_tiendaDeLibros
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.tiendadelibros.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import uniandes.cupi2.tiendadelibros.mundo.Libro;
import uniandes.cupi2.tiendadelibros.mundo.TiendaDeLibros;

/**
 * Esta es la clase usada para verificar que los métodos de la clase TiendaDeLibros estén correctamente implementados
 */
public class TiendaDeLibrosTest
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Es la clase donde se harán las pruebas
	 */
	private TiendaDeLibros tiendaDeLibros;

	/**
	 * Fecha predeterminada para la prueba
	 */
	private Date fecha;

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Construye una nueva tienda de libros vacía
	 */
	@Before
	public void setupEscenario1( )
	{
		tiendaDeLibros = new TiendaDeLibros( );
		fecha = new Date( );
	}

	/**
	 * Construye una nueva tienda con libros
	 */
	private void setupEscenario2( )
	{
		tiendaDeLibros = new TiendaDeLibros( );
		fecha = new Date( );
		tiendaDeLibros.registrarLibro( "Las mil y una noches", "AGHF324", 50000, 34000, TiendaDeLibros.CATEGORIAS[0], "" );
		tiendaDeLibros.registrarLibro( "Pulgarcito", "HAG--F456", 50000, 25000, TiendaDeLibros.CATEGORIAS[0], "" );
		tiendaDeLibros.registrarLibro( "Cuentos de los hermanos Grimm", "HAGF45", 50000, 25000, TiendaDeLibros.CATEGORIAS[0], "" );
		tiendaDeLibros.registrarLibro( "Ricitos de oro y los tres osos", "HGF456", 50000, 25000, TiendaDeLibros.CATEGORIAS[0], "");
		tiendaDeLibros.registrarLibro( "Cenicienta", "HAGF46", 500000, 250000, TiendaDeLibros.CATEGORIAS[0], "" );
	}

	/**
	 * <b>Prueba 1:</b> Verifica el método darCatalogo.<br>
	 * <b>Métodos a probar:</b><br>
	 * darCatalogo<br>
	 * <b>Casos de prueba:</b><br>
	 * 1. No hay libros al crear la tienda. <br>
	 * 2. Hay 5 libros después de ser registrados.
	 */
	@Test
	public void testDarCatalogo( )
	{
		assertTrue( "No debería haber libros", tiendaDeLibros.darCatalogo( ).size( ) == 0 );
		setupEscenario2( );
		assertTrue( "Debería haber 5 libros", tiendaDeLibros.darCatalogo( ).size( ) == 5 );
	}

	@Test
	public void testDarCategoriaCatalogo()
	{
		setupEscenario2();
		assertNotNull("Debería haber una categoría", tiendaDeLibros.darCatalogo().get(3).darCategoria());
	}

	/**
	 * <b>Prueba 2:</b> Verifica el método darPrimerLibro.<br>
	 * <b>Métodos a probar:</b><br>
	 * darPrimerLibro<br>
	 * <b>Casos de prueba:</b><br>
	 * 1. No hay primer libro al crear la tienda. <br>
	 * 2. El primer libro es correcto después de múltiples registros.
	 */
	@Test
	public void testDarPrimerLibro( )
	{
		assertTrue( "No debería haber un primer libro", tiendaDeLibros.darPrimerLibro( ) == null );
		setupEscenario2( );
		assertTrue( "El primer libro no es correcto", tiendaDeLibros.darPrimerLibro( ).darTitulo( ).equals(  "Cenicienta" ) );
	}

	/**
	 * <b>Prueba 3:</b> Verifica el método buscarPorTitulo.<br>
	 * <b>Métodos a probar:</b><br>
	 * buscarPorTitulo<br>
	 * <b>Casos de prueba:</b><br>
	 * 1. Encuentra un libro por su título. 
	 */
	@Test
	public void testBuscarPorTitulo( )
	{
		setupEscenario1( );
		tiendaDeLibros.registrarLibro( "Libro 1", "ISBN1", 50000, 50000, "Cuento Infantil", "" );
		Libro temp = new Libro( "Libro 1", "ISBN1", 50000, 50000, "Cuento Infantil", "" );
		assertEquals( "Falló la búsqueda por título", temp.darIsbn( ), tiendaDeLibros.buscarLibroPorTitulo( "Libro 1" ).darIsbn( ) );
	}

	/**
	 * <b>Prueba 4:</b> Verifica el método buscarPorISBN.<br>
	 * <b>Métodos a probar:</b><br>
	 * buscarPorISBN<br>
	 * <b>Casos de prueba:</b><br>
	 * 1. Encuentra un libro por su ISBN. 
	 */
	@Test
	public void testBuscarPorISBN( )
	{
		setupEscenario1( );
		tiendaDeLibros.registrarLibro( "Libro 1", "ISBN1", 50000, 50000, "Cuento Infantil", "" );
		Libro temp = new Libro( "Libro 1", "ISBN1", 50000, 50000, "Cuento Infantil", "" );
		assertEquals( "Falló la búsqueda por ISBN", temp.darIsbn( ), tiendaDeLibros.buscarLibroPorISBN( "ISBN1" ).darIsbn( ) );
	}

	/**
	 * <b>Prueba 5:</b> Verifica el método registrarLibro.<br>
	 * <b>Métodos a probar:</b><br>
	 * registrarLibro<br>
	 * <b>Casos de prueba:</b><br>
	 * 1. Registra un libro. <br>
	 * 2. No registra un libro con ISBN repetido.
	 */
	@Test
	public void testRegistrarLibro( )
	{
		try
		{
			tiendaDeLibros.registrarLibro( "Libro 1", "ISBN1", 50000, 50000, "Cuento Infantil", "" );
			assertTrue( "El tamaño del catálogo es incorrecto, debería registrar", 1 == tiendaDeLibros.darCatalogo( ).size( ) );
			tiendaDeLibros.registrarLibro( "Libro 1", "ISBN1", 50000, 50000, "Cuento Infantil", "" );
			assertTrue( "El tamaño del catálogo es incorrecto, no debería registrar", 1 == tiendaDeLibros.darCatalogo( ).size( ) );
		}
		catch(Exception e)
		{
			fail("No debería generar excepción");
		}
	}

	/**
	 * <b>Prueba 6:</b> Verifica el método registrarLibro, teniendo en cuenta los distintos casos de registro.<br>
	 * <b>Métodos a probar:</b><br>
	 * registrarLibro<br>
	 * <b>Casos de prueba:</b><br>
	 * 1. Registra un libro al inicio de la lista. <br>
	 * 2. Registra un libro en la mitad de la lista. <br>
	 * 3. Registra un libro al final de la lista.
	 */
	@Test
	public void testRegistrarLibro2( )
	{
		setupEscenario2( );

		try
		{
			// Caso de prueba 1
			tiendaDeLibros.registrarLibro( "Al Inicio", "ISBN12", 50000, 50000, "Cuento Infantil", "" );
			Libro actual = tiendaDeLibros.darPrimerLibro( );

			while( actual != null && actual.darSiguiente( ) != null )
			{
				if( actual.darSiguiente( ) != null && actual.compararPorTitulo( actual.darSiguiente( ) ) == 1 )
				{
					fail( "Los libros no están ordenados por título" );
				}
				actual = actual.darSiguiente( );
			}

			// Caso de prueba 2
			tiendaDeLibros.registrarLibro( "Mitad", "ISBN13", 50000, 50000, "Cuento Infantil", "" );

			actual = tiendaDeLibros.darPrimerLibro( );

			while( actual != null && actual.darSiguiente( ) != null )
			{
				if( actual.darSiguiente( ) != null && actual.compararPorTitulo( actual.darSiguiente( ) ) == 1 )
				{
					fail( "Los libros no están ordenados por título." );
				}
				actual = actual.darSiguiente( );
			}

			// Caso de prueba 3
			tiendaDeLibros.registrarLibro( "ZZZ Al final", "ISBN14", 50000, 50000, "Cuento Infantil", "" );

			actual = tiendaDeLibros.darPrimerLibro( );

			while( actual != null && actual.darSiguiente( ) != null )
			{
				if( actual.darSiguiente( ) != null && actual.compararPorTitulo( actual.darSiguiente( ) ) == 1 )
				{
					fail( "Los libros no están ordenados por título." );
				}
				actual = actual.darSiguiente( );
			}
		}
		catch( Exception e )
		{
			// No pasa por aquí
		}

	}

	/**
	 * <b>Prueba 7:</b> Verifica el método eliminarLibro.<br>
	 * <b>eliminarLibro a probar:</b><br>
	 * eliminarLibro<br>
	 * <b>Casos de prueba:</b><br>
	 * 1. Se elimina el primer libro de la lista. <br>
	 * 2. Se elimina el libro de la mitad en la lista. <br>
	 * 3. Se elimina el libro al final en la lista.
	 */
	@Test
	public void testEliminarLibro( )
	{
		setupEscenario2( );

		// Caso de prueba 1
		try
		{
			tiendaDeLibros.eliminarLibro( "HAGF46" );
			assertNull( "El libro no debería existir en la tienda.", tiendaDeLibros.buscarLibroPorISBN("HAGF46"));
			assertEquals( "Eliminó más de un libro.", tiendaDeLibros.darCatalogo().size(), 4 );
		}
		catch( Exception e )
		{
			fail( "No debería generar excepción" );
			e.printStackTrace();
		}
		// Caso de prueba 2
		try
		{
			tiendaDeLibros.registrarLibro( "Cenicienta", "HAGF46", 50000, 25000, "Cuento Infantil", "" );
			tiendaDeLibros.eliminarLibro( "AGHF324" );
			assertNull( "El libro no debería existir en la tienda.", tiendaDeLibros.buscarLibroPorISBN("AGHF324") );
			assertEquals( "Eliminó más de un libro.", tiendaDeLibros.darCatalogo().size(), 4 );
		}
		catch( Exception e )
		{
			fail( "No debería generar excepción" );
			e.printStackTrace();
		}
		// Caso de prueba 3
		try
		{
			tiendaDeLibros.registrarLibro( "Las mil y una noches", "AGHF324", 50000, 34000, "Cuento Infantil", "" );
			tiendaDeLibros.eliminarLibro( "HGF456" );

			assertNull( "El libro no debería existir en la tienda.", tiendaDeLibros.buscarLibroPorTitulo( "Ricitos de oro y los tres osos" ) );
			assertEquals( "Eliminó más de un libro.", tiendaDeLibros.darCatalogo().size(), 4 );
		}
		catch( Exception e )
		{
			fail( "No debería generar excepción." );
		}
	}

	/**
	 * <b>Prueba 8:</b> Verifica el método abastecer.<br>
	 * <b>Métodos a probar:</b><br>
	 * abastecer<br>
	 * <b>Casos de prueba:</b><br>
	 * 1. Las unidades son correctas al abastecer.
	 */
	@Test
	public void testAbastecer( )
	{
		setupEscenario2( );
		tiendaDeLibros.abastecer( "AGHF324", 10, fecha);
		assertTrue( "No se abasteció correctamente", 10 == tiendaDeLibros.buscarLibroPorISBN( "AGHF324" ).darCantidadActual( ) );
	}

	/**
	 * <b>Prueba 9:</b> Verifica el método vender.<br>
	 * <b>Métodos a probar:</b><br>
	 * vender<br>
	 * <b>Casos de prueba:</b><br>
	 * 1. Las unidades son correctas al vender.
	 */
	@Test
	public void testVender( )
	{
		setupEscenario2( );
		tiendaDeLibros.abastecer( "AGHF324", 20, fecha);
		tiendaDeLibros.vender( "AGHF324", 10, fecha);
		assertTrue( "No se vendió correctamente", 10 == tiendaDeLibros.buscarLibroPorISBN( "AGHF324" ).darCantidadActual( ) );
		boolean venta = tiendaDeLibros.vender( "AGHF324", 31, fecha);
		assertFalse( "No debería realizarse la venta", venta );
	}

	/**
	 * <b>Prueba 10:</b> Verifica el método darLibroMasCostoso.<br>
	 * <b>Métodos a probar:</b><br>
	 * darLibroMasCostoso<br>
	 * <b>Casos de prueba:</b><br>
	 * 1. El libro encontrado es el más costoso.
	 */
	@Test
	public void testDarLibroMasCostoso( )
	{
		setupEscenario2( );
		Libro temp = new Libro( "Cenicienta", "HAGF46", 500000, 250000, "Cuento Infantil", "" );
		assertEquals( "No retorna el libro correcto", temp.darIsbn( ), tiendaDeLibros.darLibroMasCostoso( ).darIsbn( ) );
	}

	/**
	 * <b>Prueba 11:</b> Verifica el método darLibroMenosCostoso.<br>
	 * <b>Métodos a probar:</b><br>
	 * darLibroMenosCostoso<br>
	 * <b>Casos de prueba:</b><br>
	 * 1. El libro encontrado es el menos costoso.
	 */
	@Test
	public void testDarLibroMenosCostoso( )
	{
		setupEscenario2( );
		Libro temp = new Libro( "Pulgarcito", "HAGF4567", 5000, 5000, "Cuento Infantil", "" );
		tiendaDeLibros.registrarLibro( "Pulgarcito", "HAGF4567", 5000, 5000, "Cuento Infantil", "" );
		assertEquals( "No retorna el libro correcto", temp.darIsbn( ), tiendaDeLibros.darLibroMasEconomico( ).darIsbn( ) );
	}

	/**
	 * <b>Prueba 12:</b> Verifica el método darLibroMasVendido.<br>
	 * <b>Métodos a probar:</b><br>
	 * darLibroMasVendido<br>
	 * <b>Casos de prueba:</b><br>
	 * 1. El libro encontrado es el más vendido.
	 */
	@Test
	public void testDarLibroMasVendido( )
	{
		setupEscenario2( );
		tiendaDeLibros.abastecer( "AGHF324", 10, fecha);
		tiendaDeLibros.abastecer( "HAGF456", 5, fecha);
		tiendaDeLibros.vender( "AGHF324", 8, fecha);
		tiendaDeLibros.vender( "HAGF456", 6, fecha);
		assertEquals( "No retorna el libro correcto", "AGHF324", tiendaDeLibros.darLibroMasVendido( ).darIsbn( ) );
	}

	/**
	 * Prueba 13: Verifica el método darCantidadTransaccionesAbastecimiento. <br>
	 * <b>Métodos a probar:</b> <br>
	 * darCantidadTransaccionesAbastecimiento<br>
	 * <b> Casos de prueba: </b><br>
	 * 1. La cantidad de transacciones de abastecimiento es correcta al abastecer.<br>
	 * 1. La cantidad de transacciones de abastecimiento no cambia al vender.<br>
	 */
	@Test
	public void testDarCantidadTransaccionesAbastecimiento( )
	{
		setupEscenario1( );
		tiendaDeLibros.registrarLibro( "LibroPrueba", "1SBN", 45000, 50000, "Cuento Infantil", "" );
		tiendaDeLibros.abastecer( "1SBN", 10, fecha);
		tiendaDeLibros.abastecer( "1SBN", 10, fecha);
		assertTrue( "La cantidad es incorrecta", 2 == tiendaDeLibros.darCantidadTransaccionesAbastecimiento( "1SBN" ) );
		tiendaDeLibros.vender( "1SBN", 10, fecha);
		tiendaDeLibros.vender( "1SBN", 10, fecha);
		assertTrue( "La cantidad es incorrecta, no debió aumentar", 2 == tiendaDeLibros.darCantidadTransaccionesAbastecimiento( "1SBN" ) );

	}

}
