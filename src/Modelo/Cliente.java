/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * lo que vamos a ver en esta clase es donde se maneja la logica del cliente 
 * Cada cliente tiene características personales y de atención
 * que determinan su prioridad en la fila (categoría de la "A"a la "G").
 * El código se forma con la letra de prioridad y un número secuencial.
 * Ejemplo: A1, C4, F4, etc.
 * La categoría se asigna automáticamente en el constructor.
 * @author pame
 */
public class Cliente {
    //acá se crean las variables osea las caracteristicas 
    private String nombre;       // Nombre del cliente
    private char genero;         // Masculino o Femenino
    private boolean discapacidad; // Si tiene discapacidad
    private boolean bebe;         // Si tiene bebé en brazos
    private char tramite;        // 'D' (múltiples tramites), 'E' (plataforma)
    private int tolerancia;      // Minutos que puede esperar antes de irse
    private int edad;            // Edad del cliente
    private boolean otroCaso;    // Si es un caso general (no tiene mucha importancia)
    private long tiempoIngreso;  // Tiempo en que ingresó al banco (en ms)
    private char categoria;      // Letra de prioridad de la "A" a la "G"
    private String codigo;       // Código final asignado (ejemplo: A3, B7, C4 y así.
    
     /**
     * Constructor principal del cliente.
     * Se asignan los valores como parametro y se calcula automáticamente
     * la categoría y el código.
     */
    
    public Cliente(String nombre, char genero, boolean discapacidad, boolean bebe, 
                   char tramite, int tolerancia, int edad, boolean otroCaso) {

        this.nombre = nombre;
        this.genero = genero;
        this.discapacidad = discapacidad;
        this.bebe = bebe;
        this.tramite = tramite;
        this.tolerancia = tolerancia;
        this.edad = edad;
        this.otroCaso = otroCaso;
        this.tiempoIngreso = System.currentTimeMillis();

        // Determinar la categoría según condiciones
        //this.categoria = asignarCategoria();

        // Generar el código con base en la categoría
        //int secuencia = Banco.getInstancia().getSiguienteSecuencia(categoria);
        //this.codigo = categoria + String.valueOf(secuencia);
    }
}
