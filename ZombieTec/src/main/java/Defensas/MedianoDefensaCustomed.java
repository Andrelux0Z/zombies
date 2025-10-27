/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Defensas;

/**
 *
 * @author sando
 */
public class MedianoDefensaCustomed extends Mediano {
    private String nombre;

    public MedianoDefensaCustomed(String nombre,int vida, int damage, int atackSpeed, int coste, int nivelAparicion,int rango) {
        super(vida, damage, atackSpeed, coste, nivelAparicion,rango);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    
}
