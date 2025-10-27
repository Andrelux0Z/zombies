/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Defensas;

import java.io.Serializable;

/**
 *
 * @author sando
 */
public class MuroDefensaCustomed extends Muro implements Serializable {
    private String nombre;
    
    public MuroDefensaCustomed(String nombre, int vida, int coste, int nivelAparicion) {
        super(vida, coste, nivelAparicion);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
}
