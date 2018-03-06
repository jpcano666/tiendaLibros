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
package uniandes.cupi2.tiendadelibros.mundo;

import java.util.*;

/**
 * Clase que representa una transacción de un libro.
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
     * Tipo de la transacción.
     */
    private Tipo tipo;

    /**
     * Cantidad de ejemplares utilizados en la transacción.
     */
    private int cantidad;

    /**
     * Fecha en que se realizó la transacción.
     */
    private Date fecha;
    
    private Transaccion siguiente;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Crea una transacción con el tipo, cantidad de ejemplares y la fecha en que se realizó.
     * @param pTipo Tipo de la transacción. pTipo != null.
     * @param pCantidad Cantidad de ejemplares utilizados en la transacción. pCantidad > 0.
     * @param pFecha Fecha en que se realizó la transacción. pFecha != null.
     */
    public Transaccion( Tipo pTipo, int pCantidad, Date pFecha )
    {
        tipo = pTipo;
        cantidad = pCantidad;
        fecha = pFecha;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el tipo de la transacción.
     * @return El tipo de la transacción.
     */
    public Tipo darTipo( )
    {
        return tipo;
    }

    /**
     * Retorna la cantidad de ejemplares utilizados en la transacción.
     * @return La cantidad de ejemplares utilizados en la transacción.
     */
    public int darCantidad( )
    {
        return cantidad;
    }

    /**
     * Retorna la fecha de la transacción.
     * @return La fecha de la transacción.
     */
    public Date darFecha( )
    {
        return fecha;
    }
    
    /**
     * Retorna la siguiente transacción de la lista.
     * @return Siguiente transacción.
     */
    public Transaccion darSiguiente()
    {
    	return siguiente;
    }
    
    /**
     * Cambia la siguiente transacción de la lista.
     * @param pTransaccion Transacción a asignar como siguiente.
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
     * Retorna la representación en cadena de caracteres un objeto Transaccion.
     * @return La representación en cadena de caracteres del objeto Transaccion.
     */
    public String toString( )
    {
        String representacion = fecha + " - " + tipo + ": " + cantidad;
        return representacion;
    }
}
