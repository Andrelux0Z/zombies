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
public class ContactoDefensaCustomed extends Contacto implements Serializable {
    private String nombre;
    
    public ContactoDefensaCustomed(String nombre,int vida, int damage, int atackSpeed, int coste, int nivelAparicion) {
        super(vida, damage, atackSpeed, coste, nivelAparicion);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }


    
}
