package utils;

import java.awt.Color;
import java.awt.Graphics2D;

import Zombies.Zombies;

// Proyectil básico del juego.
public class Proyectil {

    private int damage;
    // Velocidades en pixeles por actualización
    private double vx;
    private double vy;
    private int ancho;
    private int alto;

    private double x;
    private double y;

    private boolean activo = true;

    // Representación visual opcional
    private Sprite sprite;

    // Referencia al atacante
    private Otros.Elemento atacante;

    // constructor básico
    public Proyectil(int damage, double vx, double vy, int ancho, int alto, double x, double y) {
        this.damage = damage;
        this.vx = vx;
        this.vy = vy;
        this.ancho = ancho;
        this.alto = alto;
        this.x = x;
        this.y = y;
    }

    // constructor con sprite
    public Proyectil(int damage, double vx, double vy, int ancho, int alto, double x, double y, String rutaSprite) {
        this(damage, vx, vy, ancho, alto, x, y);
        this.sprite = new Sprite(rutaSprite, (int) Math.round(x), (int) Math.round(y), ancho, alto);
    }

    // constructor con atacante
    public Proyectil(int damage, double vx, double vy, int ancho, int alto, double x, double y,
            Otros.Elemento atacante) {
        this(damage, vx, vy, ancho, alto, x, y);
        this.atacante = atacante;
    }

    // constructor con atacante y sprite
    public Proyectil(int damage, double vx, double vy, int ancho, int alto, double x, double y, String rutaSprite,
            Otros.Elemento atacante) {
        this(damage, vx, vy, ancho, alto, x, y, rutaSprite);
        this.atacante = atacante;
    }

    public void actualizar() {
        if (!activo)
            return;
        // Movimiento
        x += vx;
        y += vy;
        // Sincronizar sprite si existe
        if (sprite != null) {
            sprite.setPosicion((int) Math.round(x), (int) Math.round(y));
        }
    }

    // Dibuja el proyectil. Si tiene sprite, utiliza su render;
    // en caso contrario, dibuja un óvalo sólido como marcador visual.
    public void dibujar(Graphics2D g2d) {
        if (!activo)
            return;
        if (sprite != null) {
            sprite.dibujar(g2d);
        } else {
            g2d.setColor(Color.ORANGE);
            g2d.fillOval((int) Math.round(x), (int) Math.round(y), ancho, alto);
        }
    }

    // Devuelve true si el proyectil colisiona con el sprite del zombie.
    // Si el zombie no tiene sprite, devuelve false.
    public boolean colisionaCon(Zombies zombie) {
        if (zombie == null || zombie.getSprite() == null || !activo)
            return false;
        Sprite spriteZombie = zombie.getSprite();
        int proyectilX = (int) Math.round(x);
        int proyectilY = (int) Math.round(y);
        int zombieX = spriteZombie.getX();
        int zombieY = spriteZombie.getY();
        return intersecta(proyectilX, proyectilY, ancho, alto, zombieX, zombieY, spriteZombie.getAncho(),
                spriteZombie.getAlto());
    }

    // Aplica el daño al zombie y desactiva el proyectil.
    public int aplicarImpacto(Zombies zombie) {
        if (!activo || zombie == null)
            return 0;
        // Zombies hereda de Elemento, así que gestionamos la vida directamente.
        int vidaAntes = zombie.getVida();
        int vidaRestante = vidaAntes - damage;
        zombie.setVida(Math.max(0, vidaRestante));
        activo = false;
        return Math.min(damage, vidaAntes); // Daño real aplicado
    }

    // Marca el proyectil como inactivo si sale de los límites indicados.
    public void invalidarSiSaleDePantalla(int anchoPantalla, int altoPantalla) {
        if (!activo)
            return;
        int posicionX = (int) Math.round(x);
        int posicionY = (int) Math.round(y);
        if (posicionX + ancho < 0 || posicionX > anchoPantalla || posicionY + alto < 0 || posicionY > altoPantalla) {
            activo = false;
        }
    }

    // Intersección de rectángulos (comprobando solape en X e Y)
    private boolean intersecta(int proyectilX, int proyectilY, int proyectilAncho, int proyectilAlto,
            int zombieX, int zombieY, int zombieAncho, int zombieAlto) {
        // Bordes del rectángulo del proyectil
        int izquierdaProyectil = proyectilX;
        int superiorProyectil = proyectilY;
        int derechaProyectil = proyectilX + proyectilAncho;
        int inferiorProyectil = proyectilY + proyectilAlto;

        // Bordes del rectángulo del zombie
        int izquierdaZombie = zombieX;
        int superiorZombie = zombieY;
        int derechaZombie = zombieX + zombieAncho;
        int inferiorZombie = zombieY + zombieAlto;

        boolean haySolapeX = (derechaProyectil > izquierdaZombie) && (izquierdaProyectil < derechaZombie);
        boolean haySolapeY = (inferiorProyectil > superiorZombie) && (superiorProyectil < inferiorZombie);

        return haySolapeX && haySolapeY;
    }

    // Setters y getters

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getVelocidadX() {
        return vx;
    }

    public void setVelocidadX(double vx) {
        this.vx = vx;
    }

    public double getVelocidadY() {
        return vy;
    }

    public void setVelocidadY(double vy) {
        this.vy = vy;
    }

    public void setVelocidad(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
        if (sprite != null)
            sprite.setTamano(ancho, alto);
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
        if (sprite != null)
            sprite.setTamano(ancho, alto);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setPosicion(double x, double y) {
        this.x = x;
        this.y = y;
        if (sprite != null)
            sprite.setPosicion((int) Math.round(x), (int) Math.round(y));
    }

    public boolean isActivo() {
        return activo;
    }

    public void desactivar() {
        this.activo = false;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        if (sprite != null) {
            sprite.setPosicion((int) Math.round(x), (int) Math.round(y));
            sprite.setTamano(ancho, alto);
        }
    }

    public Otros.Elemento getAtacante() {
        return atacante;
    }
}
