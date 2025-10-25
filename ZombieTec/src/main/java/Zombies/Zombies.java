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
    private int velocidadMov;


    public Zombies(int vida, int coste, int nivelAparicion,int velocidadMov) {
        super(vida, coste, nivelAparicion);
        this.velocidadMov = velocidadMov;
    }

    public Zombies(int vida, int damage, int atackSpeed, int coste, int nivelAparicion,int velocidadMov, int rango) {
        super(vida, damage, atackSpeed, coste, nivelAparicion,rango);
        this.velocidadMov = velocidadMov;
    }

    
    public void moverseHaciaArbol() {
        
        
    }


    public int getVelocidadMov() {
        return velocidadMov;
    }

    public void setVelocidadMov(int velocidadMov) {
        this.velocidadMov = velocidadMov;
    }
    
    
    
}
