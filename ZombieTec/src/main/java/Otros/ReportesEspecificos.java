/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Otros;

/**
 *
 * @author sando
 */
public class ReportesEspecificos {
    private Elemento oposicion; // Enemigo dependiendo de si es usado con zombie o defensa
    private int ataqueRecibido;
    private int ataqueDado;

    public ReportesEspecificos(Elemento oposicion) {
        this.oposicion = oposicion;
        this.ataqueDado = 0;
        this.ataqueRecibido = 0;
    }

    //////// Gets y Sets

    public Elemento getOposicion() {
        return oposicion;
    }

    public int getAtaqueRecibido() {
        return ataqueRecibido;
    }

    public void setAtaqueRecibido(int ataqueRecibido) {
        this.ataqueRecibido = ataqueRecibido;
    }

    public int getAtaqueDado() {
        return ataqueDado;
    }

    public void addAtaqueDado(int daño) {
        this.ataqueDado += daño;
    }

    public void addAtaqueRecibido(int daño) {
        this.ataqueRecibido += daño;
    }

}
