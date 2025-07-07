/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectohashtable;

import javax.swing.SwingUtilities; // Importar para SwingUtilities.invokeLater

/**
 * Clase principal que inicia la aplicación de bioinformática.
 * Esta clase es responsable de crear y hacer visible la interfaz gráfica.
 *
 * @author elva
 */
public class ProyectoHashTable {

    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     * @param args Argumentos de la línea de comandos (no utilizados en esta aplicación).
     */
    
    public static void main(String[] args) {
        // Asegurarse de que la creación y visualización de la GUI se realice en el Event Dispatch Thread (EDT).
        // Esto es crucial para la seguridad de hilos en Swing y evita bloqueos o comportamientos inesperados.
        SwingUtilities.invokeLater(() -> {
            InterfazProye2 ventana = new InterfazProye2(); // Crea una instancia de tu ventana
            ventana.setVisible(true); // Hace visible la ventana
            ventana.setLocationRelativeTo(null); // Opcional: Centra la ventana en la pantalla
        });
    }
}