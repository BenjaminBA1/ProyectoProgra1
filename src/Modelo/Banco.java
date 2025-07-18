/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//ubicacion de la clase 
package Modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Clase principal que gestiona la simulación del banco.
 * Administra la fila de clientes, los cajeros, el control de prioridad,
 * atención, reportes y clientes no atendidos.
 * @author ben
 */
public class Banco {
        private static Banco instancia = new Banco(); // Singleton

    private PriorityQueue<Cliente> fila; // Cola con prioridad para ordenar clientes según categoría
    private ArrayList<Cajero> cajeros;   // Lista de cajeros disponibles (5 regulares)
    private ArrayList<Cliente> noAtendidos; // Lista de clientes que abandonaron por tolerancia o por fila llena
    private Map<Character, Integer> secuencias; // Lleva la secuencia de códigos por cada categoría A–G

    // Constructor privado para patrón Singleton
    private Banco() {
        this.fila = new PriorityQueue<>(Comparator.comparing(Cliente::getCategoria));
        this.cajeros = new ArrayList<>();
        this.noAtendidos = new ArrayList<>();
        this.secuencias = new HashMap<>();

        // Inicializar contadores de cada categoría
        for (char letra : new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'}) {
            secuencias.put(letra, 0);
        }

        //  Crear 5 cajeros regulares
        for (int i = 0; i < 5; i++) {
            //cajeros.add(new Cajero(i + 1));
        }
    }

    // Método estático para obtener la instancia única (Singleton)
    public static Banco getInstancia() {
        return instancia;
    }

    // Devuelve la siguiente secuencia para una categoría específica
    public int getSiguienteSecuencia(char categoria) {
        int actual = secuencias.getOrDefault(categoria, 0) + 1;
        secuencias.put(categoria, actual);
        return actual;
    }

    // Devuelve la lista de clientes actuales en la fila (como ArrayList)
    public List<Cliente> getClientes() {
        return new ArrayList<>(fila);
    }

    // Agrega un cliente a la fila si hay espacio; si no, lo marca como no atendido
    public void agregarCliente(Cliente cliente) {
        if (fila.size() < 25) {
            fila.add(cliente);
        } else {
            noAtendidos.add(cliente);
        }
    }

    // Elimina un cliente de la fila según el índice (para usar con tabla)
    public void eliminarCliente(int index) {
        ArrayList<Cliente> lista = new ArrayList<>(fila);
        if (index >= 0 && index < lista.size()) {
            Cliente c = lista.get(index);
            fila.remove(c); // Quitar de la PriorityQueue
        }
    }

    // Reemplaza un cliente en la fila (para edición)
    public void reemplazarCliente(int index, Cliente nuevo) {
        ArrayList<Cliente> lista = new ArrayList<>(fila);
        if (index >= 0 && index < lista.size()) {
            Cliente viejo = lista.get(index);
            fila.remove(viejo);
            fila.add(nuevo);
        }
    }

    // Atiende a los clientes disponibles por cada cajero
    public void atender() {
        long ahora = System.currentTimeMillis();
        Iterator<Cliente> it = fila.iterator();

        // Eliminar los que ya superaron su tolerancia
        while (it.hasNext()) {
            Cliente c = it.next();
            long espera = (ahora - c.getTiempoIngreso()) / 60000;
            if (espera >= c.getTolerancia()) {
                noAtendidos.add(c);
                it.remove();
            }
        }

        // Atender con cada cajero si hay clientes en espera
        for (Cajero cajero : cajeros) {
            if (!fila.isEmpty()) {
                Cliente cliente = fila.poll();
                //cajero.atenderCliente(cliente);
            }
        }
    }
    
    // codigo para el cliente pero no se usa directamente porque el codigo se asigna desde la clase cliente
    public String generarCodigoConPrioridad(Cliente cliente) {
        char categoria = cliente.getCategoria();
        int secuencia = secuencias.get(categoria) + 1;
        secuencias.put(categoria, secuencia);
        return String.format("%c%d", categoria, secuencia);
    }

    // aqui se hace un reporte completo del banco
    /*public String generarReporteTexto() {
        StringBuilder sb = new StringBuilder();
        int totalAtendidos = 0;
        int totalClientes = fila.size();
        Map<Character, Integer> porCategoria = new HashMap<>();

        sb.append("=== Reporte por Cajero ===\n");

        for (Cajero cajero : cajeros) {
            int atendidos = cajero.getCantidadAtendidos();
            long tiempo = cajero.getTiempoTotalAtencion();
            double promedio = atendidos > 0 ? (double) tiempo / atendidos : 0;

            totalAtendidos += atendidos;

            sb.append("Cajero ").append(cajero.getId())
              .append(" - Atendidos: ").append(atendidos)
              .append(", Promedio: ").append(String.format("%.2f", promedio))
              .append(" min\n");

            // cuenta por la categoria
            for (Cliente c : cajero.getClientesAtendidos()) {
                char letra = c.getCategoria();
                porCategoria.put(letra, porCategoria.getOrDefault(letra, 0) + 1);
            }
        }

        sb.append("\n=== Totales Generales ===\n");
        sb.append("Total ingresados al banco: ").append(totalClientes + totalAtendidos + noAtendidos.size()).append("\n");
        sb.append("Total atendidos: ").append(totalAtendidos).append("\n");
        sb.append("En espera: ").append(fila.size()).append("\n");
        sb.append("Se fueron sin ser atendidos: ").append(noAtendidos.size()).append("\n");

        sb.append("\n=== Atendidos por Categoría ===\n");
        for (Map.Entry<Character, Integer> e : porCategoria.entrySet()) {
            sb.append("Categoría ").append(e.getKey()).append(": ").append(e.getValue()).append("\n");
        }

        return sb.toString();
    }

    // aqui se hace un reporte especifico de los clientes que no fueron atendidos 
    public String generarReporteNoAtendidos() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Clientes que se fueron sin ser atendidos ===\n");

        if (noAtendidos.isEmpty()) {
            sb.append("No hay clientes que se hayan ido.\n");
        } else {
            for (Cliente c : noAtendidos) {
                sb.append("- ").append(c.getCodigo())
                  .append(", tolerancia: ").append(c.getTolerancia())
                  .append(" minutos\n");
            }
        }

        return sb.toString();
    }

    // restablece el sistema y vuelve a iniciar
    public void reset() {
        fila.clear();
        noAtendidos.clear();

        for (Cajero c : cajeros) {
            c.getClientesAtendidos().clear();
        }

        for (char letra : secuencias.keySet()) {
            secuencias.put(letra, 0);
        }
    }*/
}
