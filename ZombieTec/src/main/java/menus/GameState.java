package menus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Tablero.Tablero;
import Defensas.Defensa;
import Zombies.Zombies;
import utils.Proyectil;

/**
 * Clase que representa el estado completo de una partida guardada.
 * Contiene toda la información necesaria para restaurar el juego.
 */
public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    // Información básica del tablero
    private int filasTablero;
    private int columnasTablero;

    // Estado de las defensas (posiciones y estado)
    private List<DefensaGuardada> defensas;

    // Estado de los zombies (posiciones y estado)
    private List<ZombieGuardado> zombies;

    // Proyectiles activos
    private List<ProyectilGuardado> proyectiles;

    // Estado del juego
    private long tiempoJuego;
    private boolean juegoActivo;

    // Información del spawner
    private int contadorSpawn;
    private static final int INTERVALO_SPAWN = 100;

    public GameState() {
        this.defensas = new ArrayList<>();
        this.zombies = new ArrayList<>();
        this.proyectiles = new ArrayList<>();
    }

    /**
     * Crea un GameState a partir del estado actual del juego
     */
    public static GameState crearDesdeJuego(Tablero tablero, List<Proyectil> proyectilesActivos, long tiempoJuego, boolean juegoActivo, int contadorSpawn) {
        GameState state = new GameState();

        state.filasTablero = tablero.getFilas();
        state.columnasTablero = tablero.getColumnas();
        state.tiempoJuego = tiempoJuego;
        state.juegoActivo = juegoActivo;
        state.contadorSpawn = contadorSpawn;

        // Guardar defensas
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Object contenido = tablero.getCasilla(i, j).getContenido();
                if (contenido instanceof Defensa defensa) {
                    state.defensas.add(new DefensaGuardada(defensa, i, j));
                }
            }
        }

        // Guardar zombies
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Object contenido = tablero.getCasilla(i, j).getContenido();
                if (contenido instanceof Zombies zombie) {
                    state.zombies.add(new ZombieGuardado(zombie, i, j));
                }
            }
        }

        // Guardar proyectiles
        for (Proyectil p : proyectilesActivos) {
            if (p.isActivo()) {
                state.proyectiles.add(new ProyectilGuardado(p));
            }
        }

        return state;
    }

    // Getters y setters
    public int getFilasTablero() { return filasTablero; }
    public int getColumnasTablero() { return columnasTablero; }
    public List<DefensaGuardada> getDefensas() { return defensas; }
    public List<ZombieGuardado> getZombies() { return zombies; }
    public List<ProyectilGuardado> getProyectiles() { return proyectiles; }
    public long getTiempoJuego() { return tiempoJuego; }
    public boolean isJuegoActivo() { return juegoActivo; }
    public int getContadorSpawn() { return contadorSpawn; }

    /**
     * Clase interna para guardar el estado de una defensa
     */
    public static class DefensaGuardada implements Serializable {
        private static final long serialVersionUID = 1L;

        private String tipoDefensa; // Nombre de la clase (Torreta, Bomba, etc.)
        private int vida;
        private int fila, columna;

        public DefensaGuardada(Defensa defensa, int fila, int columna) {
            this.tipoDefensa = defensa.getClass().getSimpleName();
            this.vida = defensa.getVida();
            this.fila = fila;
            this.columna = columna;
        }

        // Getters
        public String getTipoDefensa() { return tipoDefensa; }
        public int getVida() { return vida; }
        public int getFila() { return fila; }
        public int getColumna() { return columna; }
    }

    /**
     * Clase interna para guardar el estado de un zombie
     */
    public static class ZombieGuardado implements Serializable {
        private static final long serialVersionUID = 1L;

        private String tipoZombie; // Nombre de la clase (ZombieBasico, etc.)
        private int vida;
        private int velocidadMov;
        private long ultimoAtaqueMs;
        private int fila, columna;

        public ZombieGuardado(Zombies zombie, int fila, int columna) {
            this.tipoZombie = zombie.getClass().getSimpleName();
            this.vida = zombie.getVida();
            this.velocidadMov = zombie.getVelocidadMov();
            this.ultimoAtaqueMs = zombie.getUltimoAtaqueMs();
            this.fila = fila;
            this.columna = columna;
        }

        // Getters
        public String getTipoZombie() { return tipoZombie; }
        public int getVida() { return vida; }
        public int getVelocidadMov() { return velocidadMov; }
        public long getUltimoAtaqueMs() { return ultimoAtaqueMs; }
        public int getFila() { return fila; }
        public int getColumna() { return columna; }
    }

    /**
     * Clase interna para guardar el estado de un proyectil
     */
    public static class ProyectilGuardado implements Serializable {
        private static final long serialVersionUID = 1L;

        private int damage;
        private double vx, vy;
        private int ancho, alto;
        private double x, y;
        private boolean activo;

        public ProyectilGuardado(Proyectil proyectil) {
            this.damage = proyectil.getDamage();
            this.vx = proyectil.getVelocidadX();
            this.vy = proyectil.getVelocidadY();
            this.ancho = proyectil.getAncho();
            this.alto = proyectil.getAlto();
            this.x = proyectil.getX();
            this.y = proyectil.getY();
            this.activo = proyectil.isActivo();
        }

        // Getters
        public int getDamage() { return damage; }
        public double getVx() { return vx; }
        public double getVy() { return vy; }
        public int getAncho() { return ancho; }
        public int getAlto() { return alto; }
        public double getX() { return x; }
        public double getY() { return y; }
        public boolean isActivo() { return activo; }
    }
}