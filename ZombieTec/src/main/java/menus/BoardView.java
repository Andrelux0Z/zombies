package menus;

import Tablero.Tablero;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;
import utils.Sprite;

/*
 * Componente de vista para dibujar el tablero basado en el modelo Tablero.
 * Renderiza una cuadrícula de celdas blancas con bordes grises.
 */
public class BoardView extends JPanel {
    Tablero tablero;
    private int tamCelda;
    // Imágenes de defensas por celda
    private Image[][] defensasVisuales;
    // Sprites de defensas por celda
    private Sprite[][] spritesDefensas;

    public BoardView(Tablero tablero, int tamCelda) {
        this.tablero = tablero;
        this.tamCelda = tamCelda;
        // Inicializar arreglos de defensas
        this.defensasVisuales = new Image[tablero.getFilas()][tablero.getColumnas()];
        this.spritesDefensas = new Sprite[tablero.getFilas()][tablero.getColumnas()];
        updatePreferredSize();
        setBackground(Color.WHITE);
    }

    public void setModel(Tablero nuevo) {
        this.tablero = nuevo;
        // Reinicializar los arreglos de defensas con el nuevo tamaño
        this.defensasVisuales = new Image[nuevo.getFilas()][nuevo.getColumnas()];
        this.spritesDefensas = new Sprite[nuevo.getFilas()][nuevo.getColumnas()];
        updatePreferredSize();
        repaint();
    }

    /* Agrega una imagen de defensa a una celda específica. */
    public void agregarDefensaVisual(int fila, int columna, Image imagen) {
        if (fila >= 0 && fila < tablero.getFilas() && columna >= 0 && columna < tablero.getColumnas()) {
            defensasVisuales[fila][columna] = imagen;
        }
    }

    public int getCellSize() {
        return tamCelda;
    }

    public int getTamCelda() {
        return tamCelda;
    }

    /* Indica si ya existe un sprite en la celda dada. */
    public boolean haySpriteEn(int fila, int columna) {
        return spritesDefensas != null
                && fila >= 0 && fila < tablero.getFilas()
                && columna >= 0 && columna < tablero.getColumnas()
                && spritesDefensas[fila][columna] != null;
    }

    /* Agrega un sprite de defensa en una celda específica. */
    public void agregarSpriteDefensa(int fila, int columna, Sprite sprite) {
        if (fila >= 0 && fila < tablero.getFilas() && columna >= 0 && columna < tablero.getColumnas()) {
            spritesDefensas[fila][columna] = sprite;
        }
    }

    /* Ajusta posición y tamaño de todos los sprites a la cuadrícula actual. */
    public void sincronizarSpritesConCeldas() {
        if (spritesDefensas == null)
            return;
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Sprite s = spritesDefensas[i][j];
                if (s != null) {
                    int x = j * tamCelda;
                    int y = i * tamCelda;
                    s.setPosicion(x, y);
                    s.setTamano(tamCelda, tamCelda);
                }
            }
        }
    }

    public void setCellSize(int newSize) {
        if (newSize <= 0)
            newSize = 1;
        this.tamCelda = newSize;
        updatePreferredSize();
        // Al cambiar el tamaño de celda, sincronizar sprites
        sincronizarSpritesConCeldas();
        revalidate();
        repaint();
    }

    public void setTamCelda(int nuevoTam) {
        setCellSize(nuevoTam);
    }

    private void updatePreferredSize() {
        int w = tablero.getColumnas() * tamCelda;
        int h = tablero.getFilas() * tamCelda;
        setPreferredSize(new Dimension(w, h));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int filas = tablero.getFilas();
        int columnas = tablero.getColumnas();

        // Limpiar fondo a blanco
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dibujar celdas blancas con bordes grises
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int x = j * tamCelda;
                int y = i * tamCelda;

                // Relleno blanco
                g.setColor(Color.WHITE);
                g.fillRect(x, y, tamCelda, tamCelda);

                // Borde gris claro
                g.setColor(new Color(200, 200, 200));
                g.drawRect(x, y, tamCelda, tamCelda);

                // Dibujar la defensa si existe en esta celda (vía Image)
                if (defensasVisuales[i][j] != null) {
                    // Dibujar la imagen de la defensa escalada al tamaño de la celda
                    g.drawImage(defensasVisuales[i][j], x, y, tamCelda, tamCelda, this);
                }

                // Dibujar la defensa si existe como Sprite
                if (spritesDefensas != null && spritesDefensas[i][j] != null) {
                    spritesDefensas[i][j].dibujar((Graphics2D) g);
                }
            }
        }

        // Dibujar la rejilla por encima para que las líneas no queden tapadas por las
        // imágenes
        g.setColor(new Color(200, 200, 200));
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int x = j * tamCelda;
                int y = i * tamCelda;
                g.drawRect(x, y, tamCelda, tamCelda);
            }
        }
    }
}
