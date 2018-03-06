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
package uniandes.cupi2.tiendadelibros.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import uniandes.cupi2.tiendadelibros.mundo.Libro;
import uniandes.cupi2.tiendadelibros.mundo.TiendaDeLibros;
import uniandes.cupi2.tiendadelibros.mundo.Transaccion;
import uniandes.cupi2.tiendadelibros.mundo.Transaccion.Tipo;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Libro est�n correctamente implementados
 */
public class LibroTest
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Libro libro;

    /**
     * Fecha predeterminada para transacciones
     */
    private Date fecha;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo Libro sin unidades
     */
    @Before
    public void setupEscenario1( )
    {
        libro = new Libro( "Las mil y una noches", "HAGFSD123", 34000, 50000, "Cuento Infantil","./data/imagenes/lasmilyunanoches.jpg" );
        fecha = new Date( );
    }

    /**
     * Construye un nuevo Libro con unidades
     */
    private void setupEscenario2( )
    {
        libro = new Libro( "Las mil y una noches", "HAGFSD123", 34000, 50000, TiendaDeLibros.CATEGORIAS[0], "./data/imagenes/lasmilyunanoches.jpg" );
        fecha = new Date();
        libro.abastecer( 50, fecha );
        libro.abastecer( 100, fecha );
        libro.abastecer( 8, fecha );
    }

    /**
     * <b>Prueba 1:</b> Verifica el m�todo darISBN.<br>
     * <b>M�todos a probar:</b><br>
     * darISBN<br>
     * <b>Casos de prueba:</b><br>
     * 1. Dar el ISBN de un libro.
     */
    @Test
    public void testDarISBN( )
    {
        assertEquals( "El ISBN es incorrecto", "HAGFSD123", libro.darIsbn( ) );
    }
    
    /**
     * <b>Prueba 2:</b> Verifica el m�todo darTitulo.<br>
     * <b>M�todos a probar:</b><br>
     * darTitulo<br>
     * <b>Casos de prueba:</b><br>
     * 1. Dar el t�tulo de un libro.
     */
    @Test
    public void testDarTitulo( )
    {
        
        assertEquals( "El t�tulo es incorrecto", "Las mil y una noches", libro.darTitulo( ) );
    }
    
    /**
     * <b>Prueba 3:</b> Verifica el m�todo darPrecioVenta.<br>
     * <b>M�todos a probar:</b><br>
     * darPrecioVenta<br>
     * <b>Casos de prueba:</b><br>
     * 1. Dar el precio de venta de un libro.
     */
    @Test
    public void testTDarPrecioVenta( )
    {
        
        assertTrue( "El precio es incorrecto", 34000 == libro.darPrecioVenta( ) );
    }

    /**
     * <b>Prueba 4:</b> Verifica el m�todo darPrecioCompra.<br>
     * <b>M�todos a probar:</b><br>
     * darPrecioCompra<br>
     * <b>Casos de prueba:</b><br>
     * 1. Dar el precio de compra de un libro.
     */
    @Test
    public void testTDarPrecioCompra( )
    {
        
        assertTrue( "El precio es incorrecto", 50000 == libro.darPrecioCompra( ) );
    }

    /**
     * <b>Prueba 5:</b> Verifica el m�todo darCantidadActual.<br>
     * <b>M�todos a probar:</b><br>
     * darCantidadActual<br>
     * <b>Casos de prueba:</b><br>
     * 1. La cantidad de unidades cuando el libro es nuevo. <br>
     * 2. La cantidad de unidades despu�s de abstecer.
     */
    @Test
    public void testDarCantidadActual( )
    {
        
        assertTrue( "La cantidad actual es incorrecta", 0 == libro.darCantidadActual( ) );
        libro.abastecer( 50, fecha );
        assertTrue( "La cantidad actual es incorrecta", 50 == libro.darCantidadActual( ) );
    }
    
    /**
     * <b>Prueba 6:</b> Verifica el m�todo cambiarAnterior.<br>
     * <b>M�todos a probar:</b><br>
     * darAnterior<br>
     * cambiarAnterior<br>
     * <b>Casos de prueba:</b><br>
     * 1. No hay un anterior al crear el libro. <br>
     * 2. El anterior es correcto despu�s de haber sido asignado.
     */
    @Test
    public void testCambiarAnterior( )
    {
        assertTrue( "No deber�a haber un anterior", libro.darAnterior( ) == null );
        libro.cambiarAnterior( new Libro("", "ANTERIOR00", 56000, 50000, "Cuento Infantil", "") );
        assertTrue( "No se est� cambiando el anterior libro.", libro.darAnterior( ) != null );
        assertTrue( "El libro anterior no es correcto.", libro.darAnterior( ).darIsbn( ).equals( "ANTERIOR00" ) );
    }
    
    /**
     * <b>Prueba 7:</b> Verifica el m�todo cambiarSiguiente.<br>
     * <b>M�todos a probar:</b><br>
     * darSiguiente<br>
     * cambiarSiguiente<br>
     * <b>Casos de prueba:</b><br>
     * 1. No hay un siguiente al crear el libro. <br>
     * 2. El siguiente es correcto despu�s de haber sido asignado.
     */
    @Test
    public void testCambiarSiguiente( )
    {
        assertTrue( "No deber�a haber un siguiente", libro.darSiguiente( ) == null );
        libro.cambiarSiguiente( new Libro("", "SIGUIENTE00", 56000, 50000, "Cuento Infantil", "") );
        assertTrue( "No se est� cambiando el siguiente libro.", libro.darSiguiente( ) != null );
        assertTrue( "El libro siguiente no es correcto.", libro.darSiguiente( ).darIsbn( ).equals( "SIGUIENTE00" ) );
    }
    
    /**
     * <b>Prueba 8:</b> Verifica el m�todo darPrimeraTransaccion.<br>
     * <b>M�todos a probar:</b><br>
     * darPrimeraTransaccion<br>
     * <b>Casos de prueba:</b><br>
     * 1. No hay primera transacci�n al crear el libro. <br>
     * 2. La primera transacci�n es correcta despu�s de m�ltiples transacciones.
     */
    @Test
    public void testDarPrimeraTransaccion( )
    {
        assertTrue( "No deber�a haber una primera transacci�n", libro.darPrimeraTransaccion( ) == null );
        setupEscenario2( );
        assertTrue( "La primera transacci�n no es correcta", libro.darPrimeraTransaccion( ).darCantidad( ) == 50 );
    }

    /**
     * <b>Prueba 9:</b> Verifica el m�todo vender.<br>
     * <b>M�todos a probar:</b><br>
     * vender<br>
     * <b>Casos de prueba:</b><br>
     * 1. Las unidades son correctas al vender.
     */
    @Test
    public void testVender( )
    {
        setupEscenario2( );
        libro.vender( 100, fecha );
        libro.vender( 58, fecha );
        assertTrue( "No se vendi� correctamente", 0 == libro.darCantidadActual( ) );
    }

    /**
     * <b>Prueba 10:</b> Verifica el m�todo abastecer.<br>
     * <b>M�todos a probar:</b><br>
     * abastecer<br>
     * <b>Casos de prueba:</b><br>
     * 1. Las unidades son correctas al abastecer.
     */
    @Test
    public void testAbastecer( )
    {
        
        libro.abastecer( 50, fecha );
        libro.abastecer( 100, fecha );
        libro.abastecer( 8, fecha );
        assertTrue( "No se abasteci� correctamente", 158 == libro.darCantidadActual( ) );
    }

    /**
     * <b>Prueba 11:</b> Verifica el m�todo agregarTransaccion.<br>
     * <b>M�todos a probar:</b><br>
     * agregarTransaccion<br>
     * <b>Casos de prueba:</b><br>
     * 1. Agrega m�ltiples transacciones por orden de llegada. <br>
     */
    @Test
    public void testAgregarTransaccion( )
    {
        assertTrue( "No deber�a haber una primera transacci�n", libro.darPrimeraTransaccion( ) == null );
        libro.abastecer( 87, new Date() );
        libro.abastecer( 87, new Date() );
        libro.abastecer( 87, new Date() );
        libro.abastecer( 87, new Date() );
        libro.abastecer( 87, new Date() );
        libro.vender( 56, new Date() );
        Transaccion actual = libro.darPrimeraTransaccion( ).darSiguiente( );
        while(actual.darSiguiente( ) != null)
        {
            if(actual.darTipo( ) == Tipo.VENTA)
            {
                fail("La transacci�n de venta no se agreg� al final de la transacci�n.");
            }
            actual = actual.darSiguiente( );
        }
        assertTrue( "La �ltima transacci�n no es correcta.", actual.darTipo( ) == Tipo.VENTA );
    }
    
    /**
     * <b>Prueba 12:</b> Verifica el m�todo darTransacciones.<br>
     * <b>M�todos a probar:</b><br>
     * darTransacciones<br>
     * <b>Casos de prueba:</b><br>
     * 1. Las transacciones son correctas al abastecer. <br>
     * 2. Las transacciones son correctas al vender.
     */
    @Test
    public void testDarTransacciones( )
    {
        setupEscenario2( );
        assertTrue( "N�mero de transacciones incorrecto", 3 == libro.darTransacciones( ).size( ) );
        libro.vender( 100, fecha );
        libro.vender( 58, fecha );
        assertTrue( "N�mero de transacciones incorrecto", 5 == libro.darTransacciones( ).size( ) );
    }
    
    /**
     * Prueba 13: Verifica el m�todo compararPorTitulo. <br>
     * <b>M�todos a probar:</b> <br>
     * compararPorTitulo<br>
     * <b> Casos de prueba: </b><br>
     * 1. Los dos libros tienen el mismo t�tulo y el mismo ISBN.<br>
     * 2. El libro contra el cual se compara va antes alfab�ticamente. <br>
     * 3. El libro contra el cual se compara va despu�s alfab�ticamente. <br>
     */
    @Test
    public void testCompararPorTitulo( )
    {
        //Caso de prueba 1
        Libro libro1 = new Libro("Las mil y una noches", "HAGFSD123", 56000, 50000, TiendaDeLibros.CATEGORIAS[0], "");
        assertEquals("El resultado de la comparaci�n no es correcto", libro.compararPorTitulo( libro1 ), 0);
        
        //Caso de prueba 2
        Libro libro2 = new Libro("Antes que nada", "HAGFSD123", 56000, 50000, TiendaDeLibros.CATEGORIAS[0], "");
        assertEquals("El resultado de la comparaci�n no es correcto", libro.compararPorTitulo( libro2 ), 1);
        
        //Caso de prueba 3
        Libro libro3 = new Libro("Siempre al final", "HAGFSD123", 56000, 50000, TiendaDeLibros.CATEGORIAS[0], "");
        assertEquals("El resultado de la comparaci�n no es correcto", libro.compararPorTitulo( libro3 ), -1);
    }

}
