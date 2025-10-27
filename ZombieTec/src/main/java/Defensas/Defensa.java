package Defensas;

import Otros.*;
import utils.Proyectil;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sando
 */
public abstract class Defensa extends Elemento {

    private long ultimoDisparoMs = 0L;

    public Defensa(int vida, int coste, int nivelAparicion) {
        super(vida, coste, nivelAparicion);
    }

    public Defensa(int vida, int damage, int atackSpeed, int coste, int nivelAparicion, int rango) {
        super(vida, damage, atackSpeed, coste, nivelAparicion, rango);
    }

    public String getRutaImagen() {
        String nombre = this.getClass().getSimpleName().toLowerCase();
        return "src/main/java/Resourses/defensas/" + nombre + ".png";
    }

    // Devuelve true si ya pasó el tiempo de recarga según atackSpeed
    // (ataques/segundo)
    public boolean puedeDisparar(long ahoraMs) {
        int ataquesPorSegundo = getAtackSpeed();
        int cooldownMs = 1000 / Math.max(1, ataquesPorSegundo);
        long transcurrido = ahoraMs - ultimoDisparoMs;
        return transcurrido >= cooldownMs;
    }

    // Registra el instante del último disparo para calcular el cooldown
    public void registrarDisparo(long ahoraMs) {
        this.ultimoDisparoMs = ahoraMs;
    }

    // Crea y devuelve un proyectil dirigido al objetivo si el cooldown lo permite.
    // Si no puede disparar o la dirección no es válida, devuelve null.
    public Proyectil disparar(double objetivoX, double objetivoY, int fila, int col, int tamCelda,
            double velocidadProyectil, long ahoraMs) {
        // Verificar cooldown
        if (!puedeDisparar(ahoraMs)) {
            return null;
        }

        // Origen del disparo
        double origenX = col * tamCelda + tamCelda / 2.0;
        double origenY = fila * tamCelda + tamCelda / 2.0;

        // Dirección hacia el objetivo
        double dx = objetivoX - origenX;
        double dy = objetivoY - origenY;
        double distancia = Math.hypot(dx, dy);
        if (distancia == 0) {
            return null; // evitar división por cero
        }

        // Verificar que el objetivo esté dentro del rango
        if (distancia > getRango() * tamCelda) {
            return null; // objetivo fuera de rango
        }

        double velX = (dx / distancia) * velocidadProyectil;
        double velY = (dy / distancia) * velocidadProyectil;

        // Crear proyectil con el daño de la defensa
        Proyectil proyectil = new Proyectil(getDamage(), velX, velY, 6, 6, origenX, origenY, this);

        // Registrar disparo
        registrarDisparo(ahoraMs);

        return proyectil;
    }

}
