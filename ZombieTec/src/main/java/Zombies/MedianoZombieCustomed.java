/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Zombies;

/**
 *
 * @author sando
 */
public class MedianoZombieCustomed extends Mediano{
    private String nombre;
    
    public MedianoZombieCustomed(String nombre, int vida, int damage, int atackSpeed, int coste, int nivelAparicion, int velocidadMov, int rango) {
        super(vida, damage, atackSpeed, coste, nivelAparicion, velocidadMov, rango);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
}
