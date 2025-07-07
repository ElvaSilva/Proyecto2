/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import static InterfazProye2.InterfazProye2.arbol;
import static InterfazProye2.InterfazProye2.tablaHash;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


// Importaciones para los componentes Swing (deben ser especificas, no Object)
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import proyectohashtable.ArbolBinBusq;
import proyectohashtable.HashTable;
import proyectohashtable.Nodo;

/**
 *
 * @author elohym
 */

public class InterfazProye2 extends javax.swing.JFrame {
    public static ArbolBinBusq arbol = new ArbolBinBusq();
    public static HashTable tablaHash = new HashTable();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(InterfazProye2.class.getName());
    private static final long serialVersionUID = 1L;

    // (Eliminadas las declaraciones duplicadas de los componentes Swing)

    // Debe ser un campo de la clase para no recrearlo cada vez.
    private Map<String, String> codonesAminoacidos;

    public InterfazProye2() {
        // La visibilidad se establece en el método main después de que la ventana está completamente construida.
        // this.setVisible(true);

        initComponents(); // Método auto-generado por NetBeans para construir la GUI.
        
        // Inicializar el mapa de codones/aminoácidos una vez en el constructor.
        inicializarCodonesAminoacidos();
        
        setLocationRelativeTo(null);
    }


    /**
     * Inicializa el mapa de traducción de codones de ARN a aminoácidos.
     * Este mapa debería basarse en la tabla de codones de tu proyecto.
     */
    private void inicializarCodonesAminoacidos() {
        codonesAminoacidos = new HashMap<>();

        codonesAminoacidos.put("UUU", "Fenilalanina");
        codonesAminoacidos.put("UUC", "Fenilalanina");
        codonesAminoacidos.put("UUA", "Leucina");
        codonesAminoacidos.put("UUG", "Leucina");
        codonesAminoacidos.put("CUU", "Leucina");
        codonesAminoacidos.put("CUC", "Leucina");
        codonesAminoacidos.put("CUA", "Leucina");
        codonesAminoacidos.put("CUG", "Leucina");
        codonesAminoacidos.put("UCU", "Serina");
        codonesAminoacidos.put("UCC", "Serina");
        codonesAminoacidos.put("UCA", "Serina");
        codonesAminoacidos.put("UCG", "Serina");
        codonesAminoacidos.put("UAU", "Tirosina");
        codonesAminoacidos.put("UAC", "Tirosina");
        codonesAminoacidos.put("UGU", "Cistenia");
        codonesAminoacidos.put("UGC", "Cistenia");
        codonesAminoacidos.put("UGG", "Triptofano");
        codonesAminoacidos.put("CCU", "Prolina");
        codonesAminoacidos.put("CCC", "Prolina");
        codonesAminoacidos.put("CCA", "Prolina");
        codonesAminoacidos.put("CCG", "Prolina");
        codonesAminoacidos.put("CAU", "Histidina");
        codonesAminoacidos.put("CAA", "Glutamina");
        codonesAminoacidos.put("CAG", "Glutamina");
        codonesAminoacidos.put("CGU", "Arginina");
        codonesAminoacidos.put("CGC", "Arginina");
        codonesAminoacidos.put("CGA", "Arginina");
        codonesAminoacidos.put("CGG", "Arginina");
        codonesAminoacidos.put("AUU", "Isoleucina");
        codonesAminoacidos.put("AUC", "Isoleucina");
        codonesAminoacidos.put("AUA", "Isoleucina");
        codonesAminoacidos.put("ACU", "Treonina");
        codonesAminoacidos.put("ACC", "Treonina");
        codonesAminoacidos.put("ACA", "Treonina");
        codonesAminoacidos.put("ACG", "Treonina");
        codonesAminoacidos.put("AAU", "Asparagina");
        codonesAminoacidos.put("AAC", "Asparagina");
        codonesAminoacidos.put("AAA", "Lisina");
        codonesAminoacidos.put("AAG", "Lisina");
        codonesAminoacidos.put("AGU", "Serina");
        codonesAminoacidos.put("AGC", "Serina");
        codonesAminoacidos.put("AGA", "Arginina");
        codonesAminoacidos.put("AGG", "Arginina");
        codonesAminoacidos.put("GUU", "Valina");
        codonesAminoacidos.put("GUC", "Valina");
        codonesAminoacidos.put("GUA", "Valina");
        codonesAminoacidos.put("GUG", "Valina");
        codonesAminoacidos.put("GCU", "Alanina");
        codonesAminoacidos.put("GCC", "Alanina");
        codonesAminoacidos.put("GCA", "Alanina");
        codonesAminoacidos.put("GCG", "Alanina");
        codonesAminoacidos.put("GAU", "Acido Aspartico");
        codonesAminoacidos.put("GAC", "Acido Aspartico");
        codonesAminoacidos.put("GAA", "Acido Glutamico");
        codonesAminoacidos.put("GAG", "Acido Glutamico");
        codonesAminoacidos.put("GGU", "Glicina");
        codonesAminoacidos.put("GGC", "Glicina");
        codonesAminoacidos.put("GGA", "Glicina");
        codonesAminoacidos.put("GGG", "Glicina");
    
        codonesAminoacidos.put("AUG", "Metionina (INICIO)"); // Codón de inicio
        // Codones de parada (STOP)
        codonesAminoacidos.put("UAA", "STOP");
        codonesAminoacidos.put("UAG", "STOP");
        codonesAminoacidos.put("UGA", "STOP");
        // ***************************************************************************************************
    }
        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        // </editor-fold>
        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        subirArch = new javax.swing.JButton();
        patronesGuardados = new javax.swing.JButton();
        buscarPatron = new javax.swing.JButton();
        buscarFreq = new javax.swing.JButton();
        aminoacidos = new javax.swing.JButton();
        colisiones = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        exit = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 400, 200));

        subirArch.setText("Subir archivo");
        subirArch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subirArchActionPerformed(evt);
            }
        });
        getContentPane().add(subirArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 130, -1));

        patronesGuardados.setText("Ver patrones");
        patronesGuardados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patronesGuardadosActionPerformed(evt);
            }
        });
        getContentPane().add(patronesGuardados, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 120, -1));

        buscarPatron.setText("Buscar patrón");
        buscarPatron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarPatronActionPerformed(evt);
            }
        });
        getContentPane().add(buscarPatron, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 130, -1));

        buscarFreq.setText("Buscar patrón");
        buscarFreq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarFreqActionPerformed(evt);
            }
        });
        getContentPane().add(buscarFreq, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 130, -1));

        aminoacidos.setText("Aminoácidos");
        aminoacidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aminoacidosActionPerformed(evt);
            }
        });
        getContentPane().add(aminoacidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 120, -1));

        colisiones.setText("Colisiones");
        getContentPane().add(colisiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 120, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mas frecuente", "Menos frecuente" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 130, -1));

        exit.setText("X");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 30, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subirArchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subirArchActionPerformed
   JFileChooser fileChooser = new JFileChooser();
        // Filtra para que solo se puedan seleccionar archivos .txt
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Texto (*.txt)", "txt")); 
        int resultado = fileChooser.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            try {
                String secuenciaADN = leerContenidoArchivo(archivoSeleccionado);
                procesarSecuenciaADN(secuenciaADN);
                JOptionPane.showMessageDialog(this, "Archivo cargado y procesado exitosamente.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                logger.log(java.util.logging.Level.SEVERE, "Error al leer archivo", ex);
            } catch (HeadlessException ex) { // Captura cualquier otra excepción durante el procesamiento
                JOptionPane.showMessageDialog(this, "Error al procesar la secuencia: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                logger.log(java.util.logging.Level.SEVERE, "Error al procesar secuencia", ex);
            }
        }
    }                                         

/**
     * Lee todo el contenido de un archivo a una cadena de texto.
     * @param archivo El objeto File del archivo a leer.
     * @return El contenido del archivo como String.
     * @throws IOException Si ocurre un error de lectura.
     */
    private String leerContenidoArchivo(File archivo) throws IOException {
        // Usa Files.readString para leer todo el contenido del archivo de una vez (Java 11+)
        return Files.readString(archivo.toPath());
    }

    /**
     * Procesa la secuencia de ADN: extrae tripletes, los inserta en la tabla hash,
     * y luego usa los datos de la tabla hash para construir el árbol binario de búsqueda.
     * @param secuenciaADN La cadena de ADN a procesar.
     */
    private void procesarSecuenciaADN(String secuenciaADN) {
        // Reiniciar las estructuras de datos para un nuevo archivo
        tablaHash = new HashTable();
        arbol = new ArbolBinBusq(); // Asegúrate de que el constructor de ArbolBinBusq reinicie la raíz.

        secuenciaADN = secuenciaADN.toUpperCase().replace(" ", "").replace("\n", "").replace("\r", ""); // Limpiar espacios y saltos de línea

        if (secuenciaADN.length() < 3) {
            JOptionPane.showMessageDialog(this, "La secuencia de ADN es demasiado corta para extraer tripletes.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Extraer tripletes e insertarlos en la tabla hash
        for (int i = 0; i <= secuenciaADN.length() - 3; i += 3) {
            String triplete = secuenciaADN.substring(i, i + 3);
            // Validar que el triplete solo contenga caracteres de ADN válidos
            if (triplete.matches("[ATCG]+")) { // Asegura que solo sean A, T, C, G
                tablaHash.insertar(triplete, i); // Inserta el triplete y su ubicación
            } else {
                // Opcional: registrar o notificar sobre tripletes inválidos
                logger.log(Level.WARNING, "Triplete inv\u00e1lido encontrado y omitido: ''{0}'' en posici\u00f3n {1}", new Object[]{triplete, i});
            }
        }
        
        // ************* Llenar el BST con los Nodos de la HashTable *************
        List<Nodo> nodosParaBST = tablaHash.obtenerTodosLosNodos();
        if (nodosParaBST.isEmpty()) {
             JOptionPane.showMessageDialog(this, "No se extrajeron patrones válidos de la secuencia.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            for (Nodo nodo : nodosParaBST) {
                arbol.insertar(nodo); // Insertar cada nodo de la tabla hash en el BST
            }
        }
    }

    //GEN-LAST:event_subirArchActionPerformed

    private void patronesGuardadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patronesGuardadosActionPerformed
       if (arbol.getRaiz() == null) { // Asumiendo que ArbolBinBusq tiene un getRaiz() o un isEmpty()
            jTextArea2.setText("No hay patrones cargados en el árbol. Cargue un archivo primero.");
            return;
        }

        jTextArea2.setText(arbol.InOrder(arbol.getRaiz())); // Asumo que arbol.raiz ahora se obtiene con getRaiz()
        }                                                 
    //GEN-LAST:event_patronesGuardadosActionPerformed

    private void buscarPatronActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarPatronActionPerformed

        String patronABuscar = textCampoPatron.getText().trim().toUpperCase(); 

        if (patronABuscar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un patrón para buscar.");
            return;
        }
        if (patronABuscar.length() != 3) {
            JOptionPane.showMessageDialog(this, "Los patrones deben ser de 3 caracteres (tripletes).");
            return;
        }

        // Validar que el input solo contenga A, T, C, G antes de buscar
        if (!patronABuscar.matches("[ATCG]+")) {
            JOptionPane.showMessageDialog(this, "El patrón debe contener solo las letras A, T, C, G.", "Error de Patrón", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Nodo resultado = tablaHash.buscar(patronABuscar);
        if (resultado != null) {
            jTextArea2.setText(resultado.mostrar()); // Usar jTextArea2 aquí
        } else {
            jTextArea2.setText("El patrón '" + patronABuscar + "' no se encontró en la secuencia."); // Usar jTextArea2 aquí
        }
        }                                            
    //GEN-LAST:event_buscarPatronActionPerformed

    private void aminoacidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aminoacidosActionPerformed
        if (tablaHash.getTamano() == 0) {
            JOptionPane.showMessageDialog(this, "No hay datos para generar el reporte de aminoácidos. Cargue un archivo primero.");
            return;
        }

        // Asegurarse de que 'codonesAminoacidos' esté inicializado en el constructor.

        Map<String, List<String>> aminoacidosYPatrones = new HashMap<>(); // Aminoácido -> Lista de patrones de ADN
        Map<String, Integer> aminoacidosFrecuencia = new HashMap<>(); // Aminoácido -> Frecuencia total

            for (Nodo nodoPatron : tablaHash.obtenerTodosLosNodos()) {
            String patronADN = nodoPatron.getPatron();
            String patronARN = convertirADNtoARN(patronADN);
            // Si el codón no existe en el mapa (ej. codón de parada, o inválido que se coló)
            String aminoacido = codonesAminoacidos.getOrDefault(patronARN, "--- STOP/Inválido ---"); // Manejar codones STOP y no válidos

            // Agrupar y sumar frecuencias
            aminoacidosYPatrones.computeIfAbsent(aminoacido, k -> new ArrayList<>()).add(patronADN);
            aminoacidosFrecuencia.put(aminoacido, aminoacidosFrecuencia.getOrDefault(aminoacido, 0) + nodoPatron.getFrecuencia());
        }

        // Formatear y mostrar los resultados
        StringBuilder sb = new StringBuilder();
        sb.append("--- Listado por Aminoácido ---\n\n");

        // Ordenar los aminoácidos por nombre para una salida consistente
        aminoacidosYPatrones.keySet().stream().sorted().forEach(aminoacido -> {
            sb.append("Aminoácido: ").append(aminoacido)
              .append(" (Frecuencia Total: ").append(aminoacidosFrecuencia.get(aminoacido)).append(")\n");
            sb.append("  Patrones de ADN que lo generan: ");
            aminoacidosYPatrones.get(aminoacido).forEach(patron -> sb.append(patron).append(" "));
            sb.append("\n\n");
        });

        jTextArea2.setText(sb.toString()); // Usar jTextArea2 aquí
    }                                           

    /**
     * Convierte una secuencia de ADN a ARN. (T -> U)
     * @param adn La secuencia de ADN.
     * @return La secuencia de ARN resultante.
     */
    private String convertirADNtoARN(String adn) {
        return adn.replace('T', 'U');
    }

    //GEN-LAST:event_aminoacidosActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
    this.dispose();
}//GEN-LAST:event_exitActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // Este método se dispara cuando cambia la selección del JComboBox2.
        // Si el botón 'buscarFreq' ya maneja la lógica, este podría estar vacío
        // o podrías llamar a 'buscarFreqActionPerformed' directamente desde aquí.
        // Por ahora, se deja como está, ya que 'buscarFreqActionPerformed'
        // parece estar diseñado para usarse con este JComboBox.
    }                                          

    //GEN-LAST:event_jComboBox2ActionPerformed

    private void buscarFreqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarFreqActionPerformed

        if (arbol.getRaiz() == null) {
            jTextArea2.setText("No hay patrones cargados en el árbol para buscar frecuencia. Cargue un archivo primero.");
            return;
        }

        // Obtener el elemento seleccionado del JComboBox2
        String seleccion = (String) jComboBox2.getSelectedItem(); // Es seguro castear a String
        
        Nodo resultado = null;
        if ("Mas frecuente".equals(seleccion)) {
            resultado = arbol.max(); // Llama al método max() de tu árbol
        } else if ("Menos frecuente".equals(seleccion)) {
            resultado = arbol.min(); // Llama al método min() de tu árbol
        }

        if (resultado != null) {
            jTextArea2.setText(resultado.mostrar()); // Muestra la información del nodo
        } else {
            jTextArea2.setText("No se pudo encontrar el patrón solicitado (quizás el árbol está vacío o la selección es inválida).");
        }
    }   
    //GEN-LAST:event_buscarFreqActionPerformed

    private void colisionesActionPerformed(java.awt.event.ActionEvent evt) {
        if (tablaHash.getTamano() == 0) {
            JOptionPane.showMessageDialog(this, "No hay datos para generar el reporte de colisiones. Cargue un archivo primero.");
            return;
        }
        String reporte = tablaHash.getReporteColisiones();
        jTextArea2.setText(reporte); // Muestra el reporte en el JTextArea
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);

        }
        //</editor-fold>


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aminoacidos;
    private javax.swing.JButton buscarFreq;
    private javax.swing.JButton buscarPatron;
    private javax.swing.JButton colisiones;
    private javax.swing.JButton exit;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JButton patronesGuardados;
        private javax.swing.JButton subirArch;
        // End of variables declaration//GEN-END:variables
    
    }



