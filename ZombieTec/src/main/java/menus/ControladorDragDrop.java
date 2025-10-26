package menus;

import Tablero.Casilla;
import java.awt.Image;
import java.awt.Point;
import java.awt.dnd.*;
import javax.swing.JFrame;
import utils.Sprite;
import Defensas.*; // Para instanciar las defensas reales
import javax.swing.ImageIcon;

/*
 * Controlador que maneja la lógica de arrastrar y soltar defensas en el tablero.
 */
public class ControladorDragDrop {
    private BoardView boardView;
    private int filasTablero;
    private int columnasTablero;
    private JFrame ventanaPadre;
    private String defensaSeleccionada;
    private Image imagenDefensaSeleccionada;

    public ControladorDragDrop(BoardView boardView, int filasTablero, int columnasTablero, JFrame ventanaPadre) {
        this.boardView = boardView;
        this.filasTablero = filasTablero;
        this.columnasTablero = columnasTablero;
        this.ventanaPadre = ventanaPadre;
    }

    public void setDefensaSeleccionada(String nombre, Image imagen) {
        this.defensaSeleccionada = nombre;
        this.imagenDefensaSeleccionada = imagen;
    }

    public void setDimensiones(int filas, int columnas) {
        this.filasTablero = filas;
        this.columnasTablero = columnas;
    }

    /* Configura el tablero para que pueda recibir defensas */
    public void configurarDropEnTablero() {
        new DropTarget(boardView, new DropTargetListener() {

            public void dragEnter(DropTargetDragEvent dtde) {
            }

            public void dragOver(DropTargetDragEvent dtde) {
            }

            public void dropActionChanged(DropTargetDragEvent dtde) {
            }

            public void dragExit(DropTargetEvent dte) {
            }

            public void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);

                    Point puntoSoltado = dtde.getLocation();
                    int tamCelda = boardView.getTamCelda();
                    int columna = puntoSoltado.x / tamCelda;
                    int fila = puntoSoltado.y / tamCelda;

                    if (fila >= 0 && fila < filasTablero && columna >= 0 && columna < columnasTablero) {
                        colocarDefensaEnTablero(fila, columna, defensaSeleccionada);
                        System.out.println("Defensa '" + defensaSeleccionada + "' colocada en fila " + fila
                                + ", columna " + columna);
                    }

                    dtde.dropComplete(true);

                } catch (Exception e) {
                    System.err.println("Error al soltar defensa: " + e.getMessage());
                    e.printStackTrace();
                    dtde.dropComplete(false);
                }
            }
        });
    }

    /* Coloca una defensa en el tablero en la fila y columna indicadas */
    private void colocarDefensaEnTablero(int fila, int columna, String nombreDefensa) {
        Casilla casilla = boardView.tablero.getCasilla(fila, columna);
        boolean ocupada = (casilla != null) && (casilla.getContenido() != null);

        if (ocupada) {
            javax.swing.JOptionPane.showMessageDialog(ventanaPadre,
                    "Ya hay una defensa en esa casilla.",
                    "Casilla ocupada",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 1) Crear la instancia real de la defensa según el nombre arrastrado
        Defensa instancia = crearDefensa(nombreDefensa);
        if (instancia == null) {
            javax.swing.JOptionPane.showMessageDialog(ventanaPadre,
                    "Defensa desconocida: " + nombreDefensa,
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2) Colocar la defensa en el modelo (casilla del tablero)
        casilla.setContenido(instancia);

        // 3) Crear y asociar el sprite visual a la defensa y a la vista
        String rutaImagen = instancia.getRutaImagen();
        int tamCelda = boardView.getCellSize();
        int x = columna * tamCelda;
        int y = fila * tamCelda;
        Sprite sprite = new Sprite(rutaImagen, x, y, tamCelda, tamCelda);
        instancia.setSprite(sprite); // Sincronizar también con el modelo
        boardView.repaint();
    }

    // Crea la defensa adecuada según el nombre del ícono arrastrado.
    private Defensa crearDefensa(String nombre) {
        if (nombre == null)
            return null;
        String key = nombre.trim().toLowerCase();
        switch (key) {
            case "bomba":
                return new Bomba();
            case "gatoglobo":
                return new GatoGlobo();
            case "lanzallamas":
                return new Lanzallamas();
            case "metralleta":
                return new Metralleta();
            case "torreta": // manejar también el caso con mayúscula inicial desde el panel
                return new Torreta();
            default:
                return null;
        }
    }
}
