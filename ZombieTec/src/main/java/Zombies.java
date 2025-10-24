
import java.util.HashSet;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sando
 */
public abstract class Zombies {
    private int vida;
    private final int damage;
    private final int atackSpeed;
    private final int coste;
    private int nivelAparicion;
    private final int rango;
    private Historial reporte;
    private final String identificador;
    private int ubicacion;

    public Zombies(int vida, int damage, int atackSpeed, int coste, int nivelAparicion, int rango) {
        this.vida = vida;
        this.damage = damage;
        this.atackSpeed = atackSpeed;
        this.coste = coste;
        this.nivelAparicion = nivelAparicion;
        this.rango = rango;
    }
    
    
    
    
    public void atacar(Defensa objetivo){
        
        if(damage > objetivo.getVida())
            reporte.setAtaqueDado(vida);
        else
            reporte.setAtaqueDado(damage);
        
        objetivo.recibirDaño(damage);     
    }
    
    
    public void recibirDaño(int damage){
        
        if(damage > vida){
            this.reporte.setAtaqueRecibido(this.vida);
            this.reporte.setVidaFinal(0);
            this.vida = 0;
        }
        else{
            this.reporte.setAtaqueRecibido(damage);
            this.reporte.setVidaFinal(this.vida);
            this.vida -= damage;
        }
    }
    
    public void actualizarReporte(){}
    
    public void aumentarCapacidadCoste() {}
    
    public void moverseHaciaArbol(){}
    

    
    public int getVida() {
        return vida;
    }

    public int getAtackSpeed() {
        return atackSpeed;
    }

    public int getCoste() {
        return coste;
    }

    public int getNivelAparicion() {
        return nivelAparicion;
    }

    public void setNivelAparicion(int nivelAparicion) {
        this.nivelAparicion = nivelAparicion;
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

    public int getDaño() {
        return damage;
    }

    
    
    
    
    
    
}
