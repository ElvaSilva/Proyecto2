/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectohashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author elohym
 */

public class HashTable {
    
    private List<Nodo> [] tabla;
    
    private int capacidad;
    private int tamano;
    
    private static final double FACTOR_CARGA_MAXIMO = 0.75;
    private static final int CAPACIDAD_INICIAL = 101;
    
        // Mapa para llevar un registro de las colisiones para el reporte.
    // La clave es el índice del cubo, el valor es una lista de patrones que colisionaron en ese cubo.
    private Map<Integer, List<String>> colisionesDetectadas;

    /**
     * Constructor de la Tabla Hash. Inicializa el array de la tabla y otras variables.
     */
    @SuppressWarnings("unchecked")
    public HashTable() {
        this.capacidad = CAPACIDAD_INICIAL;
        this.tabla = new ArrayList[capacidad]; // Crear un array de ArrayLists
        // Inicializar cada "cubo" del array con una nueva ArrayList vacía.
        for (int i = 0; i < capacidad; i++) {
            this.tabla[i] = new ArrayList<>();
        }
        this.tamano = 0;
        this.colisionesDetectadas = new HashMap<>();
    }

    // --- Funciones Hash ---

    /**
     * Calcula el valor hash para una cadena de texto (patrón de ADN).
     * @param key El patrón de ADN (String) cuya clave hash se va a calcular.
     * @return El índice del cubo en la tabla hash.
     */
    
    private int calcularHash(String key) {
        int hash = 0;
        // Una función hash común para strings: suma de valores ASCII * potencia de un primo
        // Esto ayuda a distribuir mejor las claves.
        int prime = 31; // Un número primo pequeño
        for (int i = 0; i < key.length(); i++) {
            hash = (prime * hash + key.charAt(i)) % capacidad;
            // Asegurarse de que el hash sea no negativo (en Java, % puede devolver negativo)
            if (hash < 0) {
                hash += capacidad;
            }
        }
        return hash;
    }

    // --- Operaciones Principales de la Tabla Hash ---

    /**
     * Inserta un patrón de ADN en la tabla hash o actualiza sus ocurrencias
     * si el patrón ya existe.
     * @param patron El patrón de ADN a insertar o actualizar.
     * @param ubicacion La ubicación (índice) donde se encontró el patrón.
     */
    public void insertar(String patron, int ubicacion) {
        // Redimensionar si el factor de carga es demasiado alto (opcional, pero buena práctica)
        if ((double) tamano / capacidad > FACTOR_CARGA_MAXIMO) {
            redimensionar();
        }

        int indice = calcularHash(patron);
        List<Nodo> cubo = tabla[indice]; // Obtener la lista (cubo) en el índice calculado

        // Buscar si el patrón ya existe en este cubo (manejo de colisiones)
        for (Nodo nodoExistente : cubo) {
            if (nodoExistente.getPatron().equals(patron)) {
                // Si el patrón ya existe, simplemente actualizamos sus ocurrencias
                nodoExistente.addOcurrencia(ubicacion);
                return; // Salir de la función, el patrón ya fue manejado
            }
        }

        // Si el patrón no existe en el cubo, creamos un nuevo Nodo y lo añadimos.
        Nodo nuevoNodo = new Nodo(patron, ubicacion);
        
        // --- Registro de Colisiones ---
        // Si el cubo ya tiene elementos antes de añadir el nuevo, significa que hay una colisión.
        if (!cubo.isEmpty()) {
            // Añadir el patrón actual al registro de colisiones para este índice.
            // Esto es si múltiples patrones distintos llegan al mismo cubo.
            colisionesDetectadas.computeIfAbsent(indice, k -> new ArrayList<>()).add(patron);
            // También añadir los patrones que ya estaban en el cubo si es la primera vez que se detecta la colisión
            if (colisionesDetectadas.get(indice).size() == 1) { // Si el actual fue el primero en registrar la colisión
                for (Nodo n : cubo) {
                    colisionesDetectadas.get(indice).add(n.getPatron());
                }
            }
        }
        cubo.add(nuevoNodo); // Añadir el nuevo nodo al final de la lista del cubo
        tamano++; // Incrementamos el número de elementos únicos en la tabla
    }

    /**
     * Busca un patrón específico en la tabla hash.
     * @param patron El patrón de ADN a buscar.
     * @return El objeto Nodo correspondiente al patrón si se encuentra, o null si no.
     */
    public Nodo buscar(String patron) {
        int indice = calcularHash(patron);
        List<Nodo> cubo = tabla[indice];

        // Iterar sobre la lista del cubo para encontrar el patrón
        for (Nodo nodo : cubo) {
            if (nodo.getPatron().equals(patron)) {
                return nodo; // Patrón encontrado
            }
        }
        return null; // Patrón no encontrado en la tabla
    }

    /**
     * Obtiene una lista de todos los Nodos almacenados en la tabla hash.
     * Esto es útil para alimentar el Árbol Binario de Búsqueda.
     * @return Una lista de todos los objetos Nodo en la tabla.
     */
    public List<Nodo> obtenerTodosLosNodos() {
        List<Nodo> todosLosNodos = new ArrayList<>();
        for (List<Nodo> cubo : tabla) {
            todosLosNodos.addAll(cubo); // Agrega todos los nodos de cada cubo a la lista
        }
        return todosLosNodos;
    }

    // --- Redimensionamiento (Opcional, pero recomendado para eficiencia) ---

    /**
     * Redimensiona la tabla hash a una nueva capacidad (generalmente el doble).
     * Rehace el hash de todos los elementos en la nueva tabla.
     * Se llama cuando el factor de carga excede un umbral.
     */
    @SuppressWarnings("unchecked")
    private void redimensionar() {
        int nuevaCapacidad = obtenerSiguientePrimo(capacidad * 2); // Buscar el siguiente primo después del doble de la capacidad actual
        
        List<Nodo>[] tablaAntigua = tabla; // Guardar referencia a la tabla actual
        this.capacidad = nuevaCapacidad;
        this.tabla = new ArrayList[nuevaCapacidad]; // Crear una nueva tabla con la nueva capacidad
        this.tamano = 0; // Resetear el tamaño, ya que rehashearemos todo
        this.colisionesDetectadas = new HashMap<>(); // Resetear el registro de colisiones para el nuevo hash

        // Inicializar los nuevos cubos
        for (int i = 0; i < nuevaCapacidad; i++) {
            this.tabla[i] = new ArrayList<>();
        }

        // Reinsertar todos los elementos de la tabla antigua en la nueva tabla
        for (List<Nodo> cubo : tablaAntigua) {
            for (Nodo nodo : cubo) {
                // Para redimensionar, no queremos addOcurrencia, sino re-insertar el nodo completo
                // Se debe tener cuidado aquí: si el Nodo es mutable, su frecuencia y ubicaciones
                // ya están actualizadas. Aquí estamos insertando el NODO como un nuevo elemento
                // en la NUEVA tabla hash.
                
                // Simplemente re-insertamos el nodo sin cambiar su contenido.
                // Hay que usar una lógica de inserción que no incremente la frecuencia,
                // ya que los Nodos ya tienen su frecuencia final.
                int nuevoIndice = calcularHash(nodo.getPatron());
                // Registro de colisiones para la nueva tabla hash durante el redimensionamiento
                if (!this.tabla[nuevoIndice].isEmpty()) {
                    this.colisionesDetectadas.computeIfAbsent(nuevoIndice, k -> new ArrayList<>()).add(nodo.getPatron());
                    if (this.colisionesDetectadas.get(nuevoIndice).size() == 1) { // Añadir los que ya estaban
                         for (Nodo n : this.tabla[nuevoIndice]) {
                            this.colisionesDetectadas.get(nuevoIndice).add(n.getPatron());
                        }
                    }
                }
                this.tabla[nuevoIndice].add(nodo);
                this.tamano++; // Contar el tamaño en la nueva tabla
            }
        }
    }

    /**
     * Helper para encontrar el siguiente número primo después de un número dado.
     * @param num El número a partir del cual buscar el siguiente primo.
     * @return El siguiente número primo.
     */
    private int obtenerSiguientePrimo(int num) {
        while (true) {
            if (esPrimo(num)) {
                return num;
            }
            num++;
        }
    }

    /**
     * Helper para verificar si un número es primo.
     * @param num El número a verificar.
     * @return true si es primo, false en caso contrario.
     */
    private boolean esPrimo(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    // --- Reporte de Colisiones ---

    /**
     * Genera un reporte detallado de las colisiones ocurridas en la tabla hash.
     * @return Una cadena de texto con el reporte de colisiones.
     */
    public String getReporteColisiones() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("--- Reporte de Colisiones de la Tabla Hash ---\n");
        reporte.append("Capacidad actual de la tabla: ").append(capacidad).append("\n");
        reporte.append("Número total de patrones únicos: ").append(tamano).append("\n");
        reporte.append("Número de cubos (buckets) con colisiones: ").append(colisionesDetectadas.size()).append("\n\n");

        if (colisionesDetectadas.isEmpty()) {
            reporte.append("No se registraron colisiones significativas (todos los patrones se mapearon a cubos vacíos o ya contenían el mismo patrón).\n");
            reporte.append("Sin embargo, es posible que patrones distintos hayan llegado al mismo cubo pero no se registraron como 'colisión de inserción' si el cubo ya estaba ocupado desde el inicio o durante redimensionamiento.\n");
            reporte.append("Para un reporte más exhaustivo, se listarán los cubos con más de un patrón distinto.\n\n");
        }
        
        // Un reporte más preciso es iterar sobre la tabla y ver cuántos cubos tienen más de un elemento
        int colisionesReales = 0;
        for (int i = 0; i < capacidad; i++) {
            List<Nodo> cubo = tabla[i];
            if (cubo.size() > 1) { // Si hay más de un elemento en el cubo, es una colisión
                colisionesReales++;
                reporte.append("Cubo [").append(i).append("]: Colisión con ").append(cubo.size()).append(" patrones:\n");
                for (Nodo nodo : cubo) {
                    reporte.append("  - Patrón: '").append(nodo.getPatron())
                           .append("', Frecuencia: ").append(nodo.getFrecuencia()).append("\n");
                }
                reporte.append("\n");
            }
        }
        reporte.append("Total de cubos con múltiples patrones (colisiones reales): ").append(colisionesReales).append("\n");

        return reporte.toString();
    }
    
    /**
     * Retorna el número actual de patrones únicos almacenados en la tabla.
     * @return El tamaño de la tabla hash.
     */
    public int getTamano() {
        return tamano;
    }

    void SubirArch() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
