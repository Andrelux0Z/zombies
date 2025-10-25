package Defensas;

import Otros.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sando
 */
public abstract class Defensa extends Elemento {
    
    public Defensa(int vida, int coste, int nivelAparicion) {
        super(vida, coste, nivelAparicion);
    }

    public Defensa(int vida, int damage, int atackSpeed, int coste, int nivelAparicion, int rango) {
        super(vida, damage, atackSpeed, coste, nivelAparicion, rango);
    }


    
}
