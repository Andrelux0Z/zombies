package Defensas;

import Zombies.Zombies;
import Zombies.Historial;
import utils.Sprite;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sando
 */
public abstract class Defensa {
    private int vida;
    private int damage;
    private int atackSpeed;

    private int nivelAparicion;
    private int rango;
    private String identificador;
    private int coste;

    private int ubicacion;
    private Historial reporte;
    protected Sprite sprite;

    public Defensa(int vida, int coste, int nivelAparicion) {
        this.vida = vida;
        this.coste = coste;
        this.nivelAparicion = nivelAparicion;
    }

    public Defensa(int vida, int damage, int atackSpeed, int coste, int nivelAparicion, int rango) {
        this.vida = vida;
        this.damage = damage;
        this.atackSpeed = atackSpeed;
        this.coste = coste;
        this.nivelAparicion = nivelAparicion;
        this.rango = rango;
    }

    public void atacar(Zombies objetivo) {

        if (damage > objetivo.getVida())
            reporte.setAtaqueDado(vida);
        else
            reporte.setAtaqueDado(damage);

        objetivo.recibirDaño(damage);
    }

    public void recibirDaño(int damage, Zombies atacante) {

        if (damage > vida) {
            this.reporte.setAtaqueRecibido(this.vida);
            this.reporte.setVidaFinal(0);
            this.vida = 0;
            // actualizarReporte(Zombies atacante,vida)
        } else {
            this.reporte.setAtaqueRecibido(damage);
            this.reporte.setVidaFinal(this.vida);
            this.vida -= damage;
            // actualizarReporte(Zombies atacante,damage);
        }
    }

    public void generarID(String iniciales) {
        // TODO generar numeros
    }

    public void subirNivel() {
    }

    public void actualizarReporte(Zombies atacante, int damage) {
        // TODO no entiendo como putas almacenar al atacante y luego como imprimirlo

    }

    // GETTER Y SETTER:

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDaño() {
        return damage;
    }

    public void setDaño(int damage) {
        this.damage = damage;
    }

    public int getAtackSpeed() {
        return atackSpeed;
    }

    public void setAtackSpeed(int atackSpeed) {
        this.atackSpeed = atackSpeed;
    }

    public int getCoste() {
        return coste;
    }

    public int getNivelAparicion() {
        return nivelAparicion;
    }

    public int getRango() {
        return rango;
    }

    public Historial getReporte() {
        return reporte;
    }

    public void setReporte(Historial reporte) {
        this.reporte = reporte;
    }

    public String getIdentificador() {
        return identificador;
    }

    public int getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

}
