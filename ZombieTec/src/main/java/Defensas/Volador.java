package Defensas;

// Defensa que vuela (no puede ser atacada por zombies terrestres)
public class Volador extends Defensa {

    public Volador(int vida, int damage, int atackSpeed, int coste, int nivelAparicion) {
        super(vida, damage, atackSpeed, coste, nivelAparicion, 7); // Rango 7
    }
}
