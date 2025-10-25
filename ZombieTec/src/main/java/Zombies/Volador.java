/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Zombies;

/**
 *
 * @author sando
 */
public class Volador extends Contacto {

    public Volador(int vida, int damage, int atackSpeed, int coste, int nivelAparicion,int velocidadMov) {
        super(vida, damage, atackSpeed, coste, nivelAparicion,velocidadMov);
        this.setIsVolador(true); //No estoy seguro de si implementarlo asi, otra posibilidad es por el identificador
    }
    
    public void volar(){ //Metodo para que no sea detectado por otras tropas no aereas????
        
    }
}

