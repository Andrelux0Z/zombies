package menus;

// Estructura para devolver datos de la defensa más cercana
public class ObjetivoDefensa {
    public final Defensas.Defensa defensa;
    public final int fila, col;

    public ObjetivoDefensa(Defensas.Defensa defensa, int fila, int col) {
        this.defensa = defensa;
        this.fila = fila;
        this.col = col;
    }
}