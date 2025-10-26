package Tablero;

/**
 * Representa una casilla individual dentro del tablero de juego.
 * Cada casilla tiene coordenadas (x, y) y puede contener un objeto.
 * 
 * @author andre
 */
public class Casilla {

    private final int x;
    private final int y;
    private Object contenido;

    /**
     * Constructor de la casilla
     * 
     * @param x Coordenada X de la casilla
     * @param y Coordenada Y de la casilla
     */
    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        this.contenido = null;
    }

    /**
     * Obtiene la coordenada X de la casilla
     * 
     * @return Coordenada X
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la coordenada Y de la casilla
     * 
     * @return Coordenada Y
     */
    public int getY() {
        return y;
    }

    /**
     * Obtiene el contenido actual de la casilla
     * 
     * @return El objeto contenido en la casilla, o null si está vacía
     */
    public Object getContenido() {
        return contenido;
    }

    /**
     * Coloca un objeto en la casilla
     * 
     * @param contenido El objeto a colocar en la casilla
     */
    public void setContenido(Object contenido) {
        this.contenido = contenido;
    }

    /**
     * Verifica si la casilla está vacía
     * 
     * @return true si la casilla no tiene contenido, false en caso contrario
     */
    public boolean estaVacia() {
        return contenido == null;
    }

    /**
     * Limpia el contenido de la casilla
     */
    public void limpiar() {
        this.contenido = null;
    }

    @Override
    public String toString() {
        String estado;
        if (estaVacia()) {
            estado = "Vacía";
        } else {
            estado = "Ocupada: " + contenido.getClass().getSimpleName();
        }
        return String.format("Casilla[%d,%d] - %s", x, y, estado);
    }
}
