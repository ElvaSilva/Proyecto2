/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectohashtable;

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
    
    public void insertar(Nodo aux){
        if(this.IsEmpty() == true){
            this.raiz = aux;
        }else{
            Nodo aux2 = new Nodo();
            aux2 = this.raiz;
            while(aux2 != null){
                if(aux.valor < aux2.valor){
                    if(aux2.hIzq != null){
                        aux2 = aux2.hIzq;
                    }
                    aux2.hIzq = aux;
                }
                if(aux.valor > aux2.valor){
                    if(aux2.hDer != null){
                        aux2 = aux2.hDer;
                    }
                    aux2.hDer = aux;
                }
            }
        }
    }
    
    public Nodo min(){
        Nodo aux = new Nodo();
        aux = this.raiz;
        while(aux.hIzq != null){
            aux = aux.hIzq;
        }
        return aux;
    }
    
    public Nodo max(){
        Nodo aux = new Nodo();
        aux = this.raiz;
        while(aux.hDer != null){
            aux = aux.hDer;
        }
        return aux;
    }
    
    public String InOrder(Nodo aux){
        String texto = "";
        if(aux != null){
            this.InOrder(aux.hIzq);
            texto = aux.mostrar() + "/n";
            this.InOrder(aux.hDer);
        }
    }
}
