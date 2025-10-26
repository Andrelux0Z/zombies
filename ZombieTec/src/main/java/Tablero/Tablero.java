package Tablero;

/**
 * Representa un tablero de juego compuesto por casillas cuadradas.
 * El tablero tiene un tamaño variable definido en su construcción.
 * 
 * @author andre
 */
public class Tablero {

    private final int filas;
    private final int columnas;
    private final Casilla[][] casillas;

    /**
     * Constructor que crea un tablero cuadrado
     * 
     * @param tamano El número de filas y columnas del tablero
     */
    public Tablero(int tamano) {
        this(tamano, tamano);
    }

    /**
     * Constructor que crea un tablero rectangular
     * 
     * @param filas    Número de filas del tablero
     * @param columnas Número de columnas del tablero
     */
    public Tablero(int filas, int columnas) {
        if (filas <= 0 || columnas <= 0) {
            throw new IllegalArgumentException("Las dimensiones del tablero deben ser positivas");
        }

        this.filas = filas;
        this.columnas = columnas;
        this.casillas = new Casilla[filas][columnas];

        // Inicializar todas las casillas
        inicializarCasillas();
    }

    /**
     * Inicializa todas las casillas del tablero con sus coordenadas
     */
    private void inicializarCasillas() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casillas[i][j] = new Casilla(i, j);
            }
        }
    }

    /**
     * Obtiene el número de filas del tablero
     * 
     * @return Número de filas
     */
    public int getFilas() {
        return filas;
    }

    /**
     * Obtiene el número de columnas del tablero
     * 
     * @return Número de columnas
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * Obtiene una casilla específica del tablero
     * 
     * @param fila    Índice de la fila (0 a filas-1)
     * @param columna Índice de la columna (0 a columnas-1)
     * @return La casilla en la posición especificada
     * @throws IndexOutOfBoundsException si las coordenadas están fuera de rango
     */
    public Casilla getCasilla(int fila, int columna) {
        validarCoordenadas(fila, columna);
        return casillas[fila][columna];
    }

    /**
     * Coloca un objeto en una casilla específica
     * 
     * @param fila    Índice de la fila
     * @param columna Índice de la columna
     * @param objeto  El objeto a colocar
     * @throws IndexOutOfBoundsException si las coordenadas están fuera de rango
     */
    public void colocarObjeto(int fila, int columna, Object objeto) {
        validarCoordenadas(fila, columna);
        casillas[fila][columna].setContenido(objeto);
    }

    /**
     * Obtiene el objeto contenido en una casilla específica
     * 
     * @param fila    Índice de la fila
     * @param columna Índice de la columna
     * @return El objeto en la casilla, o null si está vacía
     * @throws IndexOutOfBoundsException si las coordenadas están fuera de rango
     */
    public Object obtenerObjeto(int fila, int columna) {
        validarCoordenadas(fila, columna);
        return casillas[fila][columna].getContenido();
    }

    /**
     * Limpia el contenido de una casilla específica
     * 
     * @param fila    Índice de la fila
     * @param columna Índice de la columna
     * @throws IndexOutOfBoundsException si las coordenadas están fuera de rango
     */
    public void limpiarCasilla(int fila, int columna) {
        validarCoordenadas(fila, columna);
        casillas[fila][columna].limpiar();
    }

    /**
     * Verifica si una casilla está vacía
     * 
     * @param fila    Índice de la fila
     * @param columna Índice de la columna
     * @return true si la casilla está vacía, false en caso contrario
     * @throws IndexOutOfBoundsException si las coordenadas están fuera de rango
     */
    public boolean casillaVacia(int fila, int columna) {
        validarCoordenadas(fila, columna);
        return casillas[fila][columna].estaVacia();
    }

    /**
     * Limpia todo el tablero, removiendo el contenido de todas las casillas
     */
    public void limpiarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casillas[i][j].limpiar();
            }
        }
    }

    /**
     * Valida que las coordenadas estén dentro de los límites del tablero
     * 
     * @param fila    Índice de la fila
     * @param columna Índice de la columna
     * @throws IndexOutOfBoundsException si las coordenadas están fuera de rango
     */
    private void validarCoordenadas(int fila, int columna) {
        if (fila < 0 || fila >= filas) {
            throw new IndexOutOfBoundsException(
                    String.format("Fila %d fuera de rango [0, %d)", fila, filas));
        }
        if (columna < 0 || columna >= columnas) {
            throw new IndexOutOfBoundsException(
                    String.format("Columna %d fuera de rango [0, %d)", columna, columnas));
        }
    }

    /**
     * Verifica si unas coordenadas son válidas
     * 
     * @param fila    Índice de la fila
     * @param columna Índice de la columna
     * @return true si las coordenadas son válidas, false en caso contrario
     */
    public boolean coordenadasValidas(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Tablero [%dx%d]\n", filas, columnas));
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (casillas[i][j].estaVacia()) {
                    sb.append("[ ]");
                } else {
                    sb.append("[X]");
                }
                if (j < columnas - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
