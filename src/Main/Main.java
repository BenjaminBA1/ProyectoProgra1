/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;
// Importa la ventana principal de inicio de sesión
import InterfazGUI.LoginPrincipal;

/**
 * Clase principal del proyecto.
 * Es el punto de entrada de la aplicación.
 * Lanza la ventana de login utilizando un hilo seguro para Swing (EDT).
 * 
 * @author benal
 */
public class Main {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        
        // Ejecuta la ventana de login en el hilo de eventos de Swing (EDT)
        // Esto es buena práctica para asegurar estabilidad gráfica en Java
        java.awt.EventQueue.invokeLater(() -> {
            new LoginPrincipal().setVisible(true); // Muestra la interfaz de login
        });
    }
    
}
