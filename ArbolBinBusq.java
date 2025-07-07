/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectohashtable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elva
 */
public class ArbolBinBusq {
    Nodo raiz;
    
    public ArbolBinBusq() {
        this.raiz = null;
    }

    public ArbolBinBusq(Nodo raiz) {
        this.raiz = raiz;
    }
    
    public boolean IsEmpty(){
        return this.raiz == null;
    }

  
public void insertar(Nodo nuevoNodo){
    if (this.raiz == null){
        this.raiz = nuevoNodo;
        return;
    }
    insertarRecursivo(this.raiz = nuevoNodo);
}


private void insertarRecursivo(Nodo actual, Nodo nuevoNodo) {
    // Si la frecuencia del nuevo nodo es menor que la del nodo actual
    if (nuevoNodo.getFrecuencia() < actual.getFrecuencia()) {
        if (actual.hIzq == null) { // Si el hijo izquierdo es nulo, insertamos aquí
            actual.hIzq = nuevoNodo;
        } else { // Si no es nulo, seguimos buscando en el subárbol izquierdo
            insertarRecursivo(actual.hIzq, nuevoNodo);
        }
    } else if (nuevoNodo.getFrecuencia() > actual.getFrecuencia()) { // Si la frecuencia del nuevo nodo es mayor
        if (actual.hDer == null) { // Si el hijo derecho es nulo, insertamos aquí
            actual.hDer = nuevoNodo;
        } else { // Si no es nulo, seguimos buscando en el subárbol derecho
            insertarRecursivo(actual.hDer, nuevoNodo);
        }
    } else { // Si las frecuencias son iguales, puedes decidir cómo manejarlo.
             // Para simplificar, podríamos considerar insertar a la derecha o izquierda
             // o, mejor, usar el patrón como segundo criterio de ordenación para desempate.
             // Aquí, por ejemplo, si son iguales, lo insertamos basado en el patrón lexicográficamente.
        int comparacionPatron = nuevoNodo.getPatron().compareTo(actual.getPatron());
        if (comparacionPatron < 0) { // Si el nuevo patrón es "menor" al actual lexicográficamente
            if (actual.hIzq == null) {
                actual.hIzq = nuevoNodo;
            } else {
                insertarRecursivo(actual.hIzq, nuevoNodo);
            }
        } else if (comparacionPatron > 0) { // Si el nuevo patrón es "mayor" al actual lexicográficamente
            if (actual.hDer == null) {
                actual.hDer = nuevoNodo;
            } else {
                insertarRecursivo(actual.hDer, nuevoNodo);
            }
        } else {

        }
    }
}


/**
 * Busca un nodo por su patrón de ADN.
 * Si el árbol está ordenado solo por frecuencia, esta búsqueda no será eficiente ($O(N)$ peor caso).
 * @param patron El patrón de ADN a buscar.
 * @return El Nodo que contiene el patrón, o null si no se encuentra.
 */

public Nodo buscar(String patron) {
    return buscarRecursivo(this.raiz, patron);
}

private Nodo buscarRecursivo(Nodo actual, String patron) {
    if (actual == null) {
        return null; // Patrón no encontrado
    }
    // Si el patrón es el mismo que el actual
    if (patron.equals(actual.getPatron())) {
        return actual;
    }

    Nodo encontradoIzq = buscarRecursivo(actual.hIzq, patron);
    if (encontradoIzq != null) {
        return encontradoIzq;
    }
    return buscarRecursivo(actual.hDer, patron);
}

/**
 * Encuentra el nodo con la frecuencia más baja en el árbol.
 * @return El nodo con la frecuencia mínima, o null si el árbol está vacío.
 */

public Nodo min() {
    if (this.IsEmpty()) { // Si el árbol está vacío
        return null;
    }
    Nodo aux = this.raiz;
    while (aux.hIzq != null) {
        aux = aux.hIzq;
    }
    return aux;
}

/**
 * Encuentra el nodo con la frecuencia más alta en el árbol.
 * @return El nodo con la frecuencia máxima, o null si el árbol está vacío.
 */

public Nodo max() {
    if (this.IsEmpty()) { // Si el árbol está vacío
        return null;
    }
    Nodo aux = this.raiz;
    while (aux.hDer != null) {
        aux = aux.hDer;
    }
    return aux;
}

/**
 * Realiza un recorrido In-Order del árbol y devuelve una lista de la información de los patrones,
 * ordenada por su frecuencia (si el árbol se construyó así).
 * @return Una lista de cadenas con la información de los patrones.
 */

public List<String> getPatronesOrdenadosPorFrecuencia() {
    List<String> resultados = new ArrayList<>();
    inOrderRecursivo(this.raiz, resultados);
    return resultados;
}

// Método auxiliar recursivo para el recorrido In-Order
private void inOrderRecursivo(Nodo aux, List<String> resultados) {
    if (aux != null) {
        inOrderRecursivo(aux.hIzq, resultados);
        resultados.add(aux.mostrar());
        inOrderRecursivo(aux.hDer, resultados);
    }
}

public void imprimirInOrder() {
    if (this.IsEmpty()) {
        System.out.println("El árbol está vacío.");
        return;
    }
    imprimirInOrderRecursivo(this.raiz);
}

private void imprimirInOrderRecursivo(Nodo aux) {
    if (aux != null) {
        imprimirInOrderRecursivo(aux.hIzq);
        System.out.println(aux.mostrar()); // O usar tu método de GUI para mostrar
        imprimirInOrderRecursivo(aux.hDer);
    }
}

    String InOrder(Nodo raiz) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

// Método para obtener una representación en cadena del árbol
public String toString() {
    StringBuilder sb = new StringBuilder();
    toStringRecursivo(this.raiz, sb, "", true);
    return sb.toString();
}

private void toStringRecursivo(Nodo nodo, StringBuilder sb, String prefijo, boolean esUltimo) {
    if (nodo != null) {
        sb.append(prefijo);
        sb.append(esUltimo ? "└── " : "├── ");
        sb.append(nodo.mostrar()).append("\n");
        prefijo += esUltimo ? "    " : "│   ";
        toStringRecursivo(nodo.hIzq, sb, prefijo, false);
        toStringRecursivo(nodo.hDer, sb, prefijo, true);
    }
}

// Método para obtener una lista de todos los patrones en el árbol
public List<String> getPatrones() {
    List<String> patrones = new ArrayList<>();
    getPatronesRecursivo(this.raiz, patrones);
    return patrones;
}

