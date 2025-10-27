package menus;

// Estructura para devolver datos del objetivo (posición y referencia al zombie).
public class ObjetivoZombie {
    public final Zombies.Zombies zombie;
    public final double x, y; // coordenadas del centro del objetivo
    public final int fila, col; // por si hace falta saber su celda

    public ObjetivoZombie(Zombies.Zombies zombie, double x, double y, int fila, int col) {
        this.zombie = zombie;
        this.x = x;
        this.y = y;
        this.fila = fila;
        this.col = col;
    }
}