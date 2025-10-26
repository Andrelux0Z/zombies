package menus;

import Tablero.Tablero;
import Zombies.ZombieBasico;
import Zombies.Zombies;
import utils.Sprite;
import java.util.Random;

/**
 * Clase responsable de spawnear zombies en los bordes del tablero de forma
 * periódica.
 */
public class ZombieSpawner {

    private final BoardView boardView;
    private final Random random = new Random();
    private int spawnCounter = 0;
    private static final int SPAWN_INTERVAL = 50; // Spawnear cada 50 ticks

    public ZombieSpawner(BoardView boardView) {
        this.boardView = boardView;
    }

    /**
     * Intenta spawnear un zombie si es el momento adecuado.
     * Se llama en cada tick del juego.
     */
    public void intentarSpawn() {
        spawnCounter++;
        if (spawnCounter >= SPAWN_INTERVAL) {
            spawnCounter = 0;
            spawnearZombie();
        }
    }

    /**
     * Crea y coloca un zombie en una posición aleatoria en los bordes del tablero.
     */
    private void spawnearZombie() {
        if (boardView == null)
            return;

        Tablero modelo = boardView.tablero;
        int filas = modelo.getFilas();
        int cols = modelo.getColumnas();
        int cs = boardView.getCellSize();

        // Elegir borde aleatorio: 0=arriba, 1=derecha, 2=abajo, 3=izquierda
        int borde = random.nextInt(4);
        int fila, col;

        switch (borde) {
            case 0: // Arriba
                fila = 0;
                col = random.nextInt(cols);
                break;
            case 1: // Derecha
                fila = random.nextInt(filas);
                col = cols - 1;
                break;
            case 2: // Abajo
                fila = filas - 1;
                col = random.nextInt(cols);
                break;
            case 3: // Izquierda
            default:
                fila = random.nextInt(filas);
                col = 0;
                break;
        }

        // Verificar que la celda esté vacía
        if (modelo.getCasilla(fila, col).getContenido() != null) {
            return; // Celda ocupada, no spawnear
        }

        // Crear zombie básico
        Zombies zombie = new ZombieBasico();

        // Crear sprite con tamaño de celda
        double x = col * cs;
        double y = fila * cs;
        Sprite spriteZombie = new Sprite(zombie.getRutaImagen(), (int) x, (int) y, cs, cs);
        zombie.setSprite(spriteZombie);

        // Colocar zombie en el modelo
        modelo.getCasilla(fila, col).setContenido(zombie);
    }
}