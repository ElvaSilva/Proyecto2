/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectohashtable;

import java.util.ArrayList;
import java.util.List; // Importamos List para el tipo de ubicaciones

/**
 *
 * @author elva
 */

public class Nodo {
    private String patron;
    private int frecuencia;
    private List<Interger> ubicaciones;
    
    // Punteros para la estrutura del Arbol Binario de Busqueda
    Nodo hIzq; // Hijo izquierdo
    Nodo hDer; // Hijo derecho
    
    /**
     * 
     * @author elohym
     * Constructor por defecto. Inicializa los atributos a valores predeterminados.
     * Aunque no es el constructor principal para añadir datos, puede ser útil.
     */
    
    public Nodo() {
        this.patron = null;
        this.frecuencia = 0;
        this.ubicaciones = new ArrayList<>(); // Inicializamos la lista vacía
        this.hIzq = null;
        this.hDer = null;
    }

    /**
     * Constructor principal para crear un nuevo nodo cuando se encuentra
     * un patrón por primera vez.
     *
     * @param patron El patrón de ADN (triplete) que se almacena en este nodo.
     * @param primeraUbicacion La primera posición (índice) donde se encontró este patrón.
     */
    
    public Nodo(String patron, int primeraUbicacion) {
        this.patron = patron;
        this.frecuencia = 1; // La primera vez que se encuentra, la frecuencia es 1
        this.ubicaciones = new ArrayList<>(); // Creamos la lista de ubicaciones
        this.ubicaciones.add(primeraUbicacion); // Añadimos la primera ubicación
        this.hIzq = null;
        this.hDer = null;
    }
    
    /**
     * Incrementa la frecuencia de este patrón y añade una nueva ubicación
     * a la lista de ocurrencias.Este método se llama cuando un patrón
 ya existente es encontrado de nuevo en la secuencia de ADN. * @param nuevaUbicacion La nueva posición (índice) donde se encontró el patrón.
     * @param nuevaUbicacion
     */
    
    public void addOcurrencia(int nuevaUbicacion) {
        this.frecuencia++; // Incrementa la frecuencia
        this.ubicaciones.add(nuevaUbicacion); // Añade la nueva ubicación a la lista
    }

    /**
     * Retorna una cadena de texto formateada con la información del patrón,
     * sus ubicaciones y su frecuencia.* @return String que representa el contenido del nodo.
     * @return
     */
    
    public String mostrar() {
        // Formateamos la lista de ubicaciones para una mejor visualización.
        // Por ejemplo, "[0, 9, 18]"
        String ubicacionesStr = ubicaciones.toString(); 
        
        return "Patrón: " + this.patron + 
               ". Ubicaciones en secuencia principal: " + ubicacionesStr + 
               ". Frecuencia: " + this.frecuencia;
    }

    // --- Métodos Getters ---
    /**
     * Obtiene el patrón de ADN almacenado en este nodo.
     * @return El patrón de ADN (String).
     */
    public String getPatron() {
        return patron;
    }

    /**
     * Obtiene la frecuencia de aparición de este patrón.
     * @return La frecuencia (int).
     */
    public int getFrecuencia() {
        return frecuencia;
    }
    
    /**
     * Obtiene la lista de ubicaciones (índices) donde se encontró el patrón.
     * @return Una lista de enteros (List<Integer>) con las ubicaciones.
     */
    public List<Interger> getUbicaciones() {
        return ubicaciones;
    }

    private static class Interger {

        public Interger() {
        }
    }
    
}
// Fin de la clase Nodo