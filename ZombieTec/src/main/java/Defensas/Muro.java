package Defensas;

import Zombies.Zombies;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 * @author sando
 */

public class Muro extends Defensa {

    public Muro(int vida, int coste, int nivelAparicion) {
        super(vida, 0, 0, coste, nivelAparicion, 0);
    }

    @Override
    public void atacar(Zombies objetivo) {
    }

}
