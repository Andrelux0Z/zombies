package menus;

import Tablero.Tablero;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;
import utils.Sprite;
import utils.Proyectil;
import Zombies.Zombies;
import Defensas.Defensa;

interface SeleccionListener {
    void onEntidadSeleccionada(Otros.Elemento entidad);
}

/*
 * Componente de vista para dibujar el tablero basado en el modelo Tablero.
 * Renderiza una cuadrícula de celdas blancas con bordes grises.
 */
public class BoardView extends JPanel {
    Tablero tablero;
    private int tamCelda;
    // Proyectiles en vuelo
    private final List<Proyectil> proyectiles = new ArrayList<>();
    private GameController gameController;
    private Otros.Elemento entidadSeleccionada;
    private SeleccionListener seleccionListener;

    public BoardView(Tablero tablero, int tamCelda) {
        this.tablero = tablero;
        this.tamCelda = tamCelda;
        updatePreferredSize();
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarEntidad(e.getX(), e.getY());
            }
        });
    }

    public void setModel(Tablero nuevo) {
        this.tablero = nuevo;
        this.proyectiles.clear();
        updatePreferredSize();
        sincronizarSpritesModelo();
        repaint();
    }

    public int getCellSize() {
        return tamCelda;
    }

    public void setGameController(GameController gc) {
        this.gameController = gc;
    }

    public void setSeleccionListener(SeleccionListener listener) {
        this.seleccionListener = listener;
    }

    public Otros.Elemento getEntidadSeleccionada() {
        return entidadSeleccionada;
    }

    private void seleccionarEntidad(int x, int y) {
        // Recorrer todas las celdas para encontrar entidades
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Object contenido = tablero.getCasilla(i, j).getContenido();
                if (contenido instanceof Otros.Elemento elem) {
                    Sprite sprite = elem.getSprite();
                    if (sprite != null) {
                        int spriteX = sprite.getX();
                        int spriteY = sprite.getY();
                        int spriteAncho = sprite.getAncho();
                        int spriteAlto = sprite.getAlto();
                        if (x >= spriteX && x <= spriteX + spriteAncho &&
                                y >= spriteY && y <= spriteY + spriteAlto) {
                            entidadSeleccionada = elem;
                            if (seleccionListener != null) {
                                seleccionListener.onEntidadSeleccionada(elem);
                            }
                            return;
                        }
                    }
                }
            }
        }
        // No se encontró entidad
        entidadSeleccionada = null;
        if (seleccionListener != null) {
            seleccionListener.onEntidadSeleccionada(null);
        }
    }

    // Agrega un proyectil a la escena
    public void agregarProyectil(Proyectil p) {
        if (p != null) {
            this.proyectiles.add(p);
        }
    }

    // Actualiza los proyectiles (movimiento y colisiones básicas)
    public void actualizarProyectilesYColisiones() {
        if (proyectiles.isEmpty())
            return;
        // Actualizar movimiento y limpieza por fuera de pantalla
        Iterator<Proyectil> it = proyectiles.iterator();
        while (it.hasNext()) {
            Proyectil p = it.next();
            p.actualizar();
            p.invalidarSiSaleDePantalla(getWidth(), getHeight());
            if (!p.isActivo()) {
                it.remove();
                continue;
            }
            // Revisar colisión con zombies si existen en el modelo y tienen sprite
            boolean impacto = false;
            for (int i = 0; i < tablero.getFilas() && !impacto; i++) {
                for (int j = 0; j < tablero.getColumnas() && !impacto; j++) {
                    Object contenido = tablero.getCasilla(i, j).getContenido();
                    if (contenido instanceof Zombies z) {
                        if (p.colisionaCon(z)) {
                            int daño = p.aplicarImpacto(z);
                            impacto = true;
                            if (gameController != null && daño > 0 && p.getAtacante() != null) {
                                gameController.registrarDaño(p.getAtacante(), z, daño, System.currentTimeMillis());
                            }
                            if (z.getVida() <= 0) {
                                tablero.getCasilla(i, j).limpiar();
                            }
                        }
                    }
                }
            }
            if (!p.isActivo()) {
                it.remove();
            }
        }
    }

    // Ajusta posición y tamaño de sprites de elementos colocados en el modelo
    // (zombies y defensas)
    private void sincronizarSpritesModelo() {
        if (tablero == null)
            return;
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Object contenido = tablero.getCasilla(i, j).getContenido();
                int x = j * tamCelda;
                int y = i * tamCelda;
                if (contenido instanceof Zombies z && z.getSprite() != null) {
                    Sprite s = z.getSprite();
                    s.setPosicion(x, y);
                    s.setTamano(tamCelda, tamCelda);
                } else if (contenido instanceof Defensas.Defensa d && d.getSprite() != null) {
                    Sprite s = d.getSprite();
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
        sincronizarSpritesModelo();
        revalidate();
        repaint();
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
        Graphics2D g2d = (Graphics2D) g;

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

                // Dibujar la defensa si existe en esta celda (basado en el modelo)
                Object contenido = tablero.getCasilla(i, j).getContenido();
                if (contenido instanceof Defensas.Defensa d && d.getSprite() != null) {
                    d.getSprite().dibujar(g2d);
                }

            }
        }

        // Dibujar todos los zombies por encima de las celdas
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Object contenido = tablero.getCasilla(i, j).getContenido();
                if (contenido instanceof Zombies z && z.getSprite() != null) {
                    z.getSprite().dibujar(g2d);
                }
            }
        }

        // Dibujar proyectiles por encima
        if (!proyectiles.isEmpty()) {
            for (Proyectil p : proyectiles) {
                p.dibujar(g2d);
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
