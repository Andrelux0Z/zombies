package utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

// Clase para mostrar imágenes de zombies y defensas en el juego
public class Sprite {

    private BufferedImage imagen;
    private int x;
    private int y;
    private int ancho;
    private int alto;

    // Constructor
    public Sprite(String rutaImagen, int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        cargarImagen(rutaImagen);
    }

    // Carga la imagen desde un archivo
    private void cargarImagen(String rutaImagen) {
        try {
            BufferedImage imagenOriginal = ImageIO.read(new File(rutaImagen));
            this.imagen = redimensionarImagen(imagenOriginal, ancho, alto);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar la imagen: " + rutaImagen, e);
        }
    }

    // Ajusta el tamaño de la imagen
    private BufferedImage redimensionarImagen(BufferedImage imagenOriginal, int ancho, int alto) {
        Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        BufferedImage imagenRedimensionada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = imagenRedimensionada.createGraphics();
        g2d.drawImage(imagenEscalada, 0, 0, null);
        g2d.dispose();

        return imagenRedimensionada;
    }

    // Dibuja el sprite en la pantalla
    public void dibujar(Graphics2D g2d) {
        if (imagen != null) {
            g2d.drawImage(imagen, x, y, ancho, alto, null);
        }
    }

    // Cambia la posición del sprite
    public void setPosicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Cambia el tamaño del sprite
    public void setTamano(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        if (imagen != null) {
            BufferedImage imagenOriginal = this.imagen;
            this.imagen = redimensionarImagen(imagenOriginal, ancho, alto);
        }
    }

    // Cambia la imagen del sprite
    public void cambiarImagen(String rutaImagen) {
        cargarImagen(rutaImagen);
    }

    // Obtener posición X
    public int getX() {
        return x;
    }

    // Obtener posición Y
    public int getY() {
        return y;
    }

    // Obtener ancho
    public int getAncho() {
        return ancho;
    }

    // Obtener alto
    public int getAlto() {
        return alto;
    }

    // Verifica si se hizo clic sobre el sprite
    public boolean contienePunto(int puntoX, int puntoY) {
        int bordeDerecho = x + ancho;
        int bordeInferior = y + alto;

        boolean dentroEnX = (puntoX >= x) && (puntoX <= bordeDerecho);
        boolean dentroEnY = (puntoY >= y) && (puntoY <= bordeInferior);

        return dentroEnX && dentroEnY;
    }
}
