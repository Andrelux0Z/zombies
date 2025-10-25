package Zombies;

import Otros.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sando
 */
public abstract class Zombies extends Elemento{
    
    public Zombies(int vida, int coste, int nivelAparicion) {
        super(vida, coste, nivelAparicion);
    }

    public Zombies(int vida, int damage, int atackSpeed, int coste, int nivelAparicion, int rango) {
        super(vida, damage, atackSpeed, coste, nivelAparicion, rango);
    }

    
    public void moverseHaciaArbol() {
        
        
        
    }


}
