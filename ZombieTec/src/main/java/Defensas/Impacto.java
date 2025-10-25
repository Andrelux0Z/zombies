package Defensas;

import Otros.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sando
 */
public class Impacto extends Contacto {

    public Impacto(int vida, int damage, int atackSpeed, int coste, int nivelAparicion) {
        super(vida, damage, atackSpeed, coste, nivelAparicion);
    }

    @Override
    public void atacar(Elemento objetivo) {
        super.atacar(objetivo);
        autodestruccion();
    }



    public void autodestruccion() {
        this.getReporte().setVidaFinal(0); //Reporte
        this.setVida(0);                   //Atributo
    }
}
