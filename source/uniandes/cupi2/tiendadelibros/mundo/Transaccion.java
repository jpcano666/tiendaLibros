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
 * Clase que representa una transacci�n de un libro.
 */
public class Transaccion
{
    // -----------------------------------------------------------------
    // Enumeraciones
    // -----------------------------------------------------------------

    /**
     * Enumeradores para los tipos de transacciones posibles.
     */
    public enum Tipo {
        /**
         * Representa el tipo venta
         */
        VENTA,
        /**
         * Representa el tipo abastecimiento
         */
        ABASTECIMIENTO
    }

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Tipo de la transacci�n.
     */
    private Tipo tipo;

    /**
     * Cantidad de ejemplares utilizados en la transacci�n.
     */
    private int cantidad;

    /**
     * Fecha en que se realiz� la transacci�n.
     */
    private Date fecha;
    
    private Transaccion siguiente;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Crea una transacci�n con el tipo, cantidad de ejemplares y la fecha en que se realiz�.
     * @param pTipo Tipo de la transacci�n. pTipo != null.
     * @param pCantidad Cantidad de ejemplares utilizados en la transacci�n. pCantidad > 0.
     * @param pFecha Fecha en que se realiz� la transacci�n. pFecha != null.
     */
    public Transaccion( Tipo pTipo, int pCantidad, Date pFecha )
    {
        tipo = pTipo;
        cantidad = pCantidad;
        fecha = pFecha;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el tipo de la transacci�n.
     * @return El tipo de la transacci�n.
     */
    public Tipo darTipo( )
    {
        return tipo;
    }

    /**
     * Retorna la cantidad de ejemplares utilizados en la transacci�n.
     * @return La cantidad de ejemplares utilizados en la transacci�n.
     */
    public int darCantidad( )
    {
        return cantidad;
    }

    /**
     * Retorna la fecha de la transacci�n.
     * @return La fecha de la transacci�n.
     */
    public Date darFecha( )
    {
        return fecha;
    }
    
    /**
     * Retorna la siguiente transacci�n de la lista.
     * @return Siguiente transacci�n.
     */
    public Transaccion darSiguiente()
    {
    	return siguiente;
    }
    
    /**
     * Cambia la siguiente transacci�n de la lista.
     * @param pTransaccion Transacci�n a asignar como siguiente.
     */
    public void cambiarSiguiente(Transaccion pTransaccion)
    {
    	siguiente = pTransaccion;
    }
    
    public int compararFechas(Date pFecha)
    {
    	String nueva = pFecha.toString();
    	String otra = fecha.toString();
    	int a = nueva.compareToIgnoreCase(otra);
    	
    	if(a == 0)
    	{
    		a = 0;
    	}
    	else if(a < 0)
    	{
    		a = -1;
    	}
    	else
    	{
    		a = 1;
    	}
    	return a;
    }

    /**
     * Retorna la representaci�n en cadena de caracteres un objeto Transaccion.
     * @return La representaci�n en cadena de caracteres del objeto Transaccion.
     */
    public String toString( )
    {
        String representacion = fecha + " - " + tipo + ": " + cantidad;
        return representacion;
    }
}
