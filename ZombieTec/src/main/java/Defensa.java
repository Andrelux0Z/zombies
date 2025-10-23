/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sando
 */
public asbtract class Defensa {
    private int vida;
    private Imagen apariencia;
    private int daño;
    private int atackSpeed;
    private int coste;
    private int nivelAparicion;
    private int rango;
    private Historial reporte;
    private int identificador;
    private int ubicacion;
    
    
    public void atacar(Zombie objetivo){
        objetivo.recibirDaño();
        reporte.setAtaqueDado(atackSpeed);
    
    }
    
    
    public void recibirDaño(int daño){
    
    
    }
    
    
    public void subirNivel(){}
    
    public void actualizarReporte(){}

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDaño() {
        return daño;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }

    public Imagen getApariencia() {
        return apariencia;
    }

    public void setApariencia(Imagen apariencia) {
        this.apariencia = apariencia;
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

    public void setCoste(int coste) {
        this.coste = coste;
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

    public void setRango(int rango) {
        this.rango = rango;
    }

    public Historial getReporte() {
        return reporte;
    }

    public void setReporte(Historial reporte) {
        this.reporte = reporte;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
    
    
}
