/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Zombies;

/**
 *
 * @author sando
 */
public class VoladorZombieCustomed extends Volador {
    private String nombre;
    
    public VoladorZombieCustomed(String nombre, int vida, int damage, int atackSpeed, int coste, int nivelAparicion, int velocidadMov) {
        super(vida, damage, atackSpeed, coste, nivelAparicion, velocidadMov);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
}
