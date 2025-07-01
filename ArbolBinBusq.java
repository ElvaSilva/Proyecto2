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
        if(this.raiz == null){
            return true;
        }
        return false;
    }
    
    public void insertar(Nodo aux){
        if(this.IsEmpty() == true){
            this.raiz = aux;
        }else{
            Nodo aux2 = new Nodo();
            aux2 = this.raiz;
            while(aux2.hIzq != null){
                if(aux2.valor > aux.valor){
                }
            }
        }
    }
}
