package Zombies;

import Otros.*;
import Tablero.Tablero;
import utils.Sprite;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sando
 */
public abstract class Zombies extends Elemento {
    // Velocidad de movimiento del zombie en píxeles por tick (un tick ≈ 50 ms)
    private int velocidadMov;
    private long ultimoAtaqueMs = 0L;

    public Zombies(int vida, int coste, int nivelAparicion, int velocidadMov) {
        super(vida, coste, nivelAparicion);
        this.velocidadMov = velocidadMov;
    }

    public Zombies(int vida, int damage, int atackSpeed, int coste, int nivelAparicion, int velocidadMov, int rango) {
        super(vida, damage, atackSpeed, coste, nivelAparicion, rango);
        this.velocidadMov = velocidadMov;
    }

    // Mueve al zombie hacia un punto objetivo (objetivoX, objetivoY)
    public void moverseHacia(double objetivoX, double objetivoY) {
        Sprite sprite = getSprite();

        // Centro actual del zombie
        double centroX = sprite.getX() + sprite.getAncho() / 2.0;
        double centroY = sprite.getY() + sprite.getAlto() / 2.0;

        // Vector desde la posición actual hacia el objetivo
        double magnitudX = objetivoX - centroX;
        double magnitudY = objetivoY - centroY;
        double distancia = Math.hypot(magnitudX, magnitudY);
        if (distancia == 0) {
            return;
        }

        // DISTANCIA MÍNIMA: no nos movemos si estamos muy cerca (para no cubrir la
        // defensa)
        double distanciaMinima = sprite.getAncho() * 0.8; // 80% del ancho del sprite
        if (distancia <= distanciaMinima) {
            return; // Ya estamos lo suficientemente cerca, no nos movemos más
        }

        // Velocidad en píxeles por tick
        double velocidadPorTick = getVelocidadMov();
        if (velocidadPorTick <= 0) {
            return;
        }

        // Dirección normalizada y desplazamiento de este tick
        double velX = (magnitudX / distancia) * velocidadPorTick;
        double velY = (magnitudY / distancia) * velocidadPorTick;

        // Nueva posición de la esquina superior izquierda del sprite
        double nuevaX;
        double nuevaY;
        if (distancia <= velocidadPorTick) {
            // Si estamos muy cerca, colocamos el centro exactamente en el objetivo
            nuevaX = objetivoX - sprite.getAncho() / 2.0;
            nuevaY = objetivoY - sprite.getAlto() / 2.0;
        } else {
            nuevaX = sprite.getX() + velX;
            nuevaY = sprite.getY() + velY;
        }

        // Aplicar la nueva posición
        sprite.setPosicion((int) Math.round(nuevaX), (int) Math.round(nuevaY));
    }

    // Atajo para moverse hacia el centro del sprite de un elemento objetivo
    public void moverseHacia(Elemento objetivo) {
        if (objetivo == null) {
            return;
        }
        Sprite spriteObjetivo = objetivo.getSprite();
        if (spriteObjetivo == null) {
            return;
        }
        double objetivoCentroX = spriteObjetivo.getX() + spriteObjetivo.getAncho() / 2.0;
        double objetivoCentroY = spriteObjetivo.getY() + spriteObjetivo.getAlto() / 2.0;
        moverseHacia(objetivoCentroX, objetivoCentroY);
    }

    public int getVelocidadMov() {
        return velocidadMov;
    }

    public void setVelocidadMov(int velocidadMov) {
        this.velocidadMov = velocidadMov;
    }

    // Devuelve true si ya pasó el tiempo de recarga según atackSpeed
    public boolean puedeAtacar(long ahoraMs) {
        int ataquesPorSegundo = getAtackSpeed();
        int cooldownMs = 1000 / Math.max(1, ataquesPorSegundo);
        long transcurrido = ahoraMs - ultimoAtaqueMs;
        return transcurrido >= cooldownMs;
    }

    // Registra el instante del último ataque para calcular el cooldown
    public void registrarAtaque(long ahoraMs) {
        this.ultimoAtaqueMs = ahoraMs;
    }

    // Ataca a una defensa cercana si puede (cooldown y distancia)
    public void atacarDefensaCercana(Defensas.Defensa defensa, long ahoraMs, Tablero tablero, int filaDefensa,
            int colDefensa) {
        if (defensa == null || !puedeAtacar(ahoraMs)) {
            return;
        }

        // Verificar distancia
        Sprite spriteZombie = getSprite();
        Sprite spriteDefensa = defensa.getSprite();
        if (spriteZombie == null || spriteDefensa == null) {
            return;
        }

        double centroZX = spriteZombie.getX() + spriteZombie.getAncho() / 2.0;
        double centroZY = spriteZombie.getY() + spriteZombie.getAlto() / 2.0;
        double centroDX = spriteDefensa.getX() + spriteDefensa.getAncho() / 2.0;
        double centroDY = spriteDefensa.getY() + spriteDefensa.getAlto() / 2.0;

        double distancia = Math.hypot(centroDX - centroZX, centroDY - centroZY);
        double distanciaMaximaAtaque = spriteZombie.getAncho() * 1.2; // 120% del ancho del zombie

        if (distancia <= distanciaMaximaAtaque) {
            // Atacar: reducir vida de la defensa
            int dano = getDamage();
            defensa.setVida(defensa.getVida() - dano);

            // Registrar ataque
            registrarAtaque(ahoraMs);

            // Si la defensa muere, limpiar su celda
            if (defensa.getVida() <= 0) {
                tablero.getCasilla(filaDefensa, colDefensa).setContenido(null);
            }
        }
    }

    public String getRutaImagen() {
        String nombre = this.getClass().getSimpleName().toLowerCase();
        return "src/main/java/Resourses/zombies/" + nombre + ".png";
    }
}
