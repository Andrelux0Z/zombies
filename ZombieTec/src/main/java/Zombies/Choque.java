/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Zombies;

import Otros.Elemento;

/**
 *
 * @author sando
 */
public class Choque extends Contacto {

    public Choque(int vida, int damage, int atackSpeed, int coste, int nivelAparicion, int velocidadMov) {
        super(vida, damage, atackSpeed, coste, nivelAparicion, velocidadMov);
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
