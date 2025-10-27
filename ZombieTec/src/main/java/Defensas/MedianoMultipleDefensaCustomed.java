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
public class MedianoMultipleDefensaCustomed extends MedianoMultiple implements Serializable {
    private String nombre;
    
    public MedianoMultipleDefensaCustomed(String nombre,int vida, int damage, int atackSpeed, int coste, int nivelAparicion, int rango, int cantObjetivos) {
        super(vida, damage, atackSpeed, coste, nivelAparicion, rango, cantObjetivos);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
