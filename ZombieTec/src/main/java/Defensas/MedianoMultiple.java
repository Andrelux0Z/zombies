package Defensas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sando
 */
public class MedianoMultiple extends Mediano {
    int cantObjetivos;
    
    public MedianoMultiple(int vida, int damage, int atackSpeed, int coste, int nivelAparicion,int cantObjetivos) {
        super(vida, damage, atackSpeed, coste, nivelAparicion);
        this.cantObjetivos = cantObjetivos;
    }

}
