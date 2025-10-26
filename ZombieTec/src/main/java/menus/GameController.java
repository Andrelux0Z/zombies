package menus;

import javax.swing.Timer;

import Defensas.Defensa;
import Zombies.Zombies;
import Zombies.ZombieBasico;
import utils.Proyectil;
import utils.Sprite;
import menus.ZombieSpawner;

// Controlador del juego.
// Hace que las torretas disparen al zombie más cercano.
// Mueve los proyectiles y detecta colisiones.
// Repinta el tablero.
public class GameController {

    // Constantes para que los "números mágicos" sean claros
    private static final int TICK_MS = 50; // Tiempo entre actualizaciones
    private static final double VEL_PROYECTIL = 6.0; // Velocidad de los proyectiles (px por tick)

    private final BoardView boardView;
    private Timer timer; // Llama periódicamente a "tickDeJuego()"
    private final ZombieSpawner zombieSpawner;

    public GameController(BoardView boardView) {
        this.boardView = boardView;
        this.zombieSpawner = new ZombieSpawner(boardView);
    }

    // Inicia el bucle de juego si no está ya corriendo.
    public void start() {
        if (timer != null && timer.isRunning())
            return;
        timer = new Timer(TICK_MS, e -> tickDeJuego());
        timer.start();
    }

    // Detiene el bucle de juego si está corriendo.
    public void stop() {
        if (timer != null)
            timer.stop();
    }

    // Un "tick" del juego: acciones que se repiten muchas veces por segundo.
    private void tickDeJuego() {
        if (boardView == null)
            return;
        // Intentar spawnear zombies periódicamente
        zombieSpawner.intentarSpawn();
        moverZombiesHaciaDefensaMasCercana();
        zombiesAtacanDefensas();
        dispararDefensas();
        boardView.actualizarProyectilesYColisiones();
        boardView.repaint();
    }

    // Mueve cada zombie hacia la defensa más cercana (si existe)
    private void moverZombiesHaciaDefensaMasCercana() {
        var modelo = boardView.tablero;
        int filas = modelo.getFilas();
        int cols = modelo.getColumnas();

        // Recolectar centros de todas las defensas (que tengan sprite)
        java.util.ArrayList<double[]> objetivos = new java.util.ArrayList<>();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                Object c = modelo.getCasilla(i, j).getContenido();
                if (c instanceof Defensa d) {
                    Sprite st = d.getSprite();
                    if (st == null)
                        continue;
                    double tx = st.getX() + st.getAncho() / 2.0;
                    double ty = st.getY() + st.getAlto() / 2.0;
                    objetivos.add(new double[] { tx, ty });
                }
            }
        }
        if (objetivos.isEmpty())
            return;

        // Para cada zombie, elegir el objetivo más cercano y moverse hacia él
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                Object c = modelo.getCasilla(i, j).getContenido();
                if (c instanceof Zombies z) {
                    Sprite sz = z.getSprite();
                    if (sz == null)
                        continue;
                    double zx = sz.getX() + sz.getAncho() / 2.0;
                    double zy = sz.getY() + sz.getAlto() / 2.0;
                    double mejorDist2 = Double.POSITIVE_INFINITY;
                    double[] mejor = null;
                    for (double[] obj : objetivos) {
                        double dx = obj[0] - zx;
                        double dy = obj[1] - zy;
                        double d2 = dx * dx + dy * dy;
                        if (d2 < mejorDist2) {
                            mejorDist2 = d2;
                            mejor = obj;
                        }
                    }
                    if (mejor != null) {
                        z.moverseHacia(mejor[0], mejor[1]);
                    }
                }
            }
        }
    }

    // Hace que los zombies ataquen a las defensas cercanas
    private void zombiesAtacanDefensas() {
        var modelo = boardView.tablero;
        int filas = modelo.getFilas();
        int cols = modelo.getColumnas();
        long ahora = System.currentTimeMillis();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                Object contenido = modelo.getCasilla(i, j).getContenido();
                if (contenido instanceof Zombies z) {
                    // Buscar la defensa más cercana a este zombie
                    ObjetivoDefensa objetivo = encontrarDefensaMasCercana(i, j);
                    if (objetivo != null) {
                        z.atacarDefensaCercana(objetivo.defensa, ahora, modelo, objetivo.fila, objetivo.col);
                    }
                }
            }
        }

        // Limpiar defensas muertas (por si acaso quedan algunas)
        limpiarDefensasMuertas();
    }

    // Recorre el tablero y pide a cada defensa que dispare si puede.
    private void dispararDefensas() {
        var modelo = boardView.tablero;
        int filas = modelo.getFilas();
        int cols = modelo.getColumnas();

        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < cols; col++) {
                Object contenido = modelo.getCasilla(fila, col).getContenido();
                if (contenido instanceof Defensa defensa) {
                    dispararConDefensaSiPuede(defensa, fila, col);
                }
            }
        }
    }

    // Dispara un proyectil desde la defensa hacia el zombie más cercano
    private void dispararConDefensaSiPuede(Defensa defensa, int fila, int col) {
        long ahora = System.currentTimeMillis();
        // Buscar objetivo
        ObjetivoZombie objetivo = encontrarZombieMasCercano(fila, col);
        if (objetivo == null)
            return; // No hay a quién disparar
        int cs = boardView.getCellSize();

        Proyectil p = defensa.disparar(objetivo.x, objetivo.y, fila, col, cs, VEL_PROYECTIL, ahora);
        if (p != null) {
            boardView.agregarProyectil(p);
        }
    }

    // Busca el zombie más cercano a la celda.
    private ObjetivoZombie encontrarZombieMasCercano(int filaT, int colT) {
        var modelo = boardView.tablero;
        int filas = modelo.getFilas();
        int cols = modelo.getColumnas();

        // Punto de referencia: centro de la torreta en (filaT, colT)
        Sprite spriteTorreta = null;
        Object contenidoTorreta = modelo.getCasilla(filaT, colT).getContenido();
        if (contenidoTorreta instanceof Defensa d && d.getSprite() != null) {
            spriteTorreta = d.getSprite();
        }
        double refX;
        double refY;
        if (spriteTorreta != null) {
            refX = spriteTorreta.getX() + spriteTorreta.getAncho() / 2.0;
            refY = spriteTorreta.getY() + spriteTorreta.getAlto() / 2.0;
        } else {
            int cs = boardView.getCellSize();
            refX = colT * cs + cs / 2.0;
            refY = filaT * cs + cs / 2.0;
        }

        ObjetivoZombie mejor = null;
        double mejorDist2 = 99999;

        // Recorremos todas las celdas buscando zombies y nos quedamos con el más
        // cercano
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < cols; col++) {
                Object contenido = modelo.getCasilla(fila, col).getContenido();
                if (contenido instanceof Zombies z) {
                    // Centro del zombie a partir de su Sprite
                    Sprite sz = z.getSprite();
                    double zx = sz.getX() + sz.getAncho() / 2.0;
                    double zy = sz.getY() + sz.getAlto() / 2.0;

                    double dx = zx - refX;
                    double dy = zy - refY;
                    double d2 = dx * dx + dy * dy; // distancia al cuadrado
                    if (d2 < mejorDist2) {
                        mejorDist2 = d2;
                        mejor = new ObjetivoZombie(z, zx, zy, fila, col);
                    }
                }
            }
        }
        return mejor;
    }

    // Estructura para devolver datos del objetivo (posición y referencia al
    // zombie).
    private static class ObjetivoZombie {
        final Zombies zombie;
        final double x, y; // coordenadas del centro del objetivo
        final int fila, col; // por si hace falta saber su celda

        ObjetivoZombie(Zombies zombie, double x, double y, int fila, int col) {
            this.zombie = zombie;
            this.x = x;
            this.y = y;
            this.fila = fila;
            this.col = col;
        }
    }

    // Estructura para devolver datos de la defensa más cercana

    // Tengo que revisar esto

    private static class ObjetivoDefensa {
        final Defensas.Defensa defensa;
        final int fila, col;

        ObjetivoDefensa(Defensas.Defensa defensa, int fila, int col) {
            this.defensa = defensa;
            this.fila = fila;
            this.col = col;
        }
    }

    // Busca la defensa más cercana a la celda (para ataques de zombies)
    private ObjetivoDefensa encontrarDefensaMasCercana(int filaZ, int colZ) {
        var modelo = boardView.tablero;
        int filas = modelo.getFilas();
        int cols = modelo.getColumnas();

        // Punto de referencia: centro del zombie en (filaZ, colZ)
        Sprite spriteZombie = null;
        Object contenidoZombie = modelo.getCasilla(filaZ, colZ).getContenido();
        if (contenidoZombie instanceof Zombies z && z.getSprite() != null) {
            spriteZombie = z.getSprite();
        }
        double refX;
        double refY;
        if (spriteZombie != null) {
            refX = spriteZombie.getX() + spriteZombie.getAncho() / 2.0;
            refY = spriteZombie.getY() + spriteZombie.getAlto() / 2.0;
        } else {
            int cs = boardView.getCellSize();
            refX = colZ * cs + cs / 2.0;
            refY = filaZ * cs + cs / 2.0;
        }

        ObjetivoDefensa mejor = null;
        double mejorDist2 = Double.POSITIVE_INFINITY; // distancia al cuadrado

        // Recorremos todas las celdas buscando defensas
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < cols; col++) {
                Object contenido = modelo.getCasilla(fila, col).getContenido();
                if (contenido instanceof Defensas.Defensa d) {
                    // Centro de la defensa
                    Sprite sd = d.getSprite();
                    double dx = sd.getX() + sd.getAncho() / 2.0;
                    double dy = sd.getY() + sd.getAlto() / 2.0;

                    double distX = dx - refX;
                    double distY = dy - refY;
                    double d2 = distX * distX + distY * distY; // distancia al cuadrado
                    if (d2 < mejorDist2) {
                        mejorDist2 = d2;
                        mejor = new ObjetivoDefensa(d, fila, col);
                    }
                }
            }
        }
        return mejor;
    }

    // Elimina las defensas que han sido destruidas (vida <= 0)
    private void limpiarDefensasMuertas() {
        var modelo = boardView.tablero;
        int filas = modelo.getFilas();
        int cols = modelo.getColumnas();

        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < cols; col++) {
                Object contenido = modelo.getCasilla(fila, col).getContenido();
                if (contenido instanceof Defensas.Defensa d) {
                    if (d.getVida() <= 0) {
                        modelo.getCasilla(fila, col).setContenido(null);
                    }
                }
            }
        }
    }
}
