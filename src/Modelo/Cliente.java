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
    /**
     * metodo que es privado que define la prioridad del cliente
     * @return letra de la categoría de la (A a la G)
     */
    
    private char asignarCategoria() {
        // si tiene discapacidad Y bebe, sera de prioridad maxima
        if (discapacidad && bebe) {
            return 'A';
        }

        if (edad >= 65) {
            return 'A'; //  es adulto mayor
        } else if (bebe && genero == 'F') {
            return 'B'; // es una mujer con un bebe
        } else if (discapacidad) {
            return 'C'; // persona con alguna discapacidad
        } else if (tramite == 'D') {
            return 'D'; // realizara multiples tramites
        } else if (tramite == 'E') {
            return 'E'; // solo ira a plataforma
        } else {
            return (genero == 'F') ? 'F' : 'G'; // para otros casos
        }
    }
    
     /**
     * metodo publico para poder recalcular la categoria y el codigo
     * si los datos cambian se usara al editar cliente
     */
    public void recalcularCategoria() {
        this.categoria = asignarCategoria();
        //int secuencia = Banco.getInstancia().getSiguienteSecuencia(categoria);
        //this.codigo = categoria + String.valueOf(secuencia);
    }
    // ==================== GETTERS ==================== METODOS DE ACCESO

    public String getNombre() {
        return nombre;
    }

    public char getGenero() {
        return genero;
    }

    public boolean tieneDiscapacidad() {
        return discapacidad;
    }

    public boolean tieneBebe() {
        return bebe;
    }

    public char getTramite() {
        return tramite;
    }

    public int getTolerancia() {
        return tolerancia;
    }

    public int getEdad() {
        return edad;
    }

    public boolean isOtroCaso() {
        return otroCaso;
    }

    public long getTiempoIngreso() {
        return tiempoIngreso;
    }

    public char getCategoria() {
        return categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    // ==================== SETTERS ==================== METODOS QUE MODIFICAN

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public void setDiscapacidad(boolean discapacidad) {
        this.discapacidad = discapacidad;
    }

    public void setBebe(boolean bebe) {
        this.bebe = bebe;
    }

    public void setTramite(char tramite) {
        this.tramite = tramite;
    }

    public void setTolerancia(int tolerancia) {
        this.tolerancia = tolerancia;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setOtroCaso(boolean otroCaso) {
        this.otroCaso = otroCaso;
    }

    // ==================== OTROS ====================

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}
