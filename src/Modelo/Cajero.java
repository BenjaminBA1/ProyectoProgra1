/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//igual, ubicacion de la clase 
package Modelo;

import java.util.ArrayList;

/*
 *Clase que representa un cajero del banco.
 * Cada cajero tiene un identificador, lleva el control de los clientes atendidos
 * y calcula el tiempo total de atención acumulado.
 * Se asume que el cajero atiende de uno en uno cuando se le asigna un cliente.
 * @author pame
 */
public class Cajero {
   private int id; // Identificador único del cajero
   private int cantidadAtendidos; // Total de clientes que ha atendido
   private long tiempoTotalAtencion; // Suma de los tiempos de espera de todos los clientes atendidos
   private ArrayList<Cliente> clientesAtendidos; // Lista de clientes que ha atendido
    
   /**
     * Constructor del cajero
     * @param id número que identifica al cajero (1 a 5 típicamente)
     */
    public Cajero(int id) {
        this.id = id;
        this.cantidadAtendidos = 0;
        this.tiempoTotalAtencion = 0;
        this.clientesAtendidos = new ArrayList<>();
    }
    
    // getter del identificador del cajero
    public int getId() {
        return id;
    }

    // getter de la cantidad de clientes que ha atendido
    public int getCantidadAtendidos() {
        return cantidadAtendidos;
    }

    // getter del tiempo total de atención acumulado
    public long getTiempoTotalAtencion() {
        return tiempoTotalAtencion;
    }

    // devuelve la lista de clientes atendidos por este cajero
    public ArrayList<Cliente> getClientesAtendidos() {
        return clientesAtendidos;
    }
     
        /**
     * Método para que el cajero atienda a un cliente.
     * Calcula el tiempo que esperó el cliente desde su ingreso al banco
     * y lo suma al total. Luego lo guarda en la lista de atendidos.
     * 
     * @param cliente el cliente que va a ser atendido
     */
    public void atenderCliente(Cliente cliente) {
        long ahora = System.currentTimeMillis();
        long espera = (ahora - cliente.getTiempoIngreso()) / 60000; // convertir de milisegundos a minutos

        tiempoTotalAtencion += espera;
        cantidadAtendidos++;
        clientesAtendidos.add(cliente);
    }
}
