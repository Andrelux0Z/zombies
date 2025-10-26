package Zombies;

/**
 * Zombie básico de prueba. Ataca por contacto y se mueve lentamente.
 */
public class ZombieBasico extends Contacto {

    // Constructor por defecto con valores sencillos
    public ZombieBasico() {
        // vida, damage, atackSpeed, coste, nivelAparicion, velocidadMov
        super(60, 30, 1, 0, 0, 2);
    }

    // Constructor parametrizado para pruebas
    public ZombieBasico(int vida, int damage, int atackSpeed, int coste, int nivelAparicion, int velocidadMov) {
        super(vida, damage, atackSpeed, coste, nivelAparicion, velocidadMov);
    }
}
