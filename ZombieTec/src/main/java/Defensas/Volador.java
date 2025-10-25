package Defensas;

import Zombies.Zombies;

// Defensa que vuela (no puede ser atacada por zombies terrestres)

public class Volador extends Defensa {

    public Volador(int vida, int damage, int atackSpeed, int coste, int nivelAparicion,int velocidadMov) {
        super(vida, damage, atackSpeed, coste, nivelAparicion, 1); // Se coloca sobre una tropa, por lo que rango debe ser 1
        this.setIsVolador(true); //No estoy seguro de si implementarlo asi, otra posibilidad es por el identificador
    }
    
    public void moverse(Zombies objetivo){ 
        
    }
    
    public void volar(){ //Metodo para que no sea targeteado por no voladores???
        
    }
    
}
