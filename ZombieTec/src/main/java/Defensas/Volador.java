package Defensas;

import Zombies.Zombies;

// Defensa que vuela (no puede ser atacada por zombies terrestres)

public class Volador extends Defensa {

    public Volador(int vida, int damage, int atackSpeed, int coste, int nivelAparicion,int velocidadMov) {
        super(vida, damage, atackSpeed, coste, nivelAparicion, 7); // Rango 7
    }
    
    public void moverse(Zombies objetivo){
        
    }
}
