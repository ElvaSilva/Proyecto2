/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectohashtable;

/**
 *
 * @author elva
 */
public class Nodo {
    String patron;
    int valor;
    String ocurrencias;
    Nodo hIzq;
    Nodo hDer;

    public Nodo() {
        this.patron = null;
        this.valor = 0;
        this.ocurrencias = null;
        this.hIzq = null;
        this.hDer = null;
    }

    public Nodo(String ocurrencias) {
        this.patron = null;
        this.valor = 0; 
        this.ocurrencias = ocurrencias;
        this.hIzq = null;
        this.hDer = null;
    }
    
    public String mostrar(){
        return this.patron + ". Ubicaciones en secuencia principal: " + 
                this.ocurrencias + ". Frecuencia: " + this.valor;
    }
    
}