package Otros;

import utils.Sprite;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sando
 */
public abstract class Elemento {
    
    private int vida;
    private int damage;
    private int atackSpeed;
    
    private final int coste;
    private int nivelAparicion;
    private int rango;
    private String identificador;
    private boolean isVolador = false;
    
    private Historial reportes;
    private int ubicacion;
    protected Sprite sprite; // Sprite visual del zombie
    private Elemento tipo;
    
    public Elemento(int vida, int damage, int atackSpeed, int coste, int nivelAparicion, int rango) {
        this.vida = vida;
        this.damage = damage;
        this.atackSpeed = atackSpeed;
        this.coste = coste;
        this.nivelAparicion = nivelAparicion;
        this.rango = rango;
    }

        public Elemento(int vida, int coste, int nivelAparicion) {
        this.vida = vida;
        this.coste = coste;
        this.nivelAparicion = nivelAparicion;
    }
    
    public void atacar(Elemento objetivo) { //Note que modifica los valores del reporte y del objeto atacante y objetivo
        int indicePropio = buscarReporte(objetivo);
        int indiceOposicion = buscarReporte(this);
        
        
        if (damage > objetivo.getVida()){
            //Modificar valores del objeto
            objetivo.setVida(0);
            
            //Modificar reportes
            this.reportes.getArrReportesEspecificos().get(indicePropio).setAtaqueDado(vida);
            objetivo.reportes.getArrReportesEspecificos().get(indiceOposicion).setAtaqueRecibido(vida);
            objetivo.reportes.setVidaFinal(0);
            
        }else {
            //Modificar valores del objeto
            objetivo.setVida(getVida()-damage);
            
            //Modificar reportes
            this.reportes.getArrReportesEspecificos().get(indicePropio).setAtaqueDado(damage);
            objetivo.reportes.getArrReportesEspecificos().get(indiceOposicion).setAtaqueRecibido(damage);
            objetivo.reportes.setVidaFinal(0);
        }   
    }

    public void generarID(String iniciales) {//TODO generar numeros
        
    }    
    
    public int buscarReporte(Elemento objetivo) { //Verifica si ya hay un reporte en el arreglo de historiales
        ReportesEspecificos reporte;
        
        for(int i=0 ; i<this.reportes.getArrReportesEspecificos().size() ; i++){
            if(this.reportes.getArrReportesEspecificos().get(i).getOposicion() == this){ //Si lo encuentra, retornar indice
                return i;
            }
        }
        reporte = new ReportesEspecificos(objetivo.tipo); //Si no lo encuentra, crearlo
        this.reportes.getArrReportesEspecificos().add(reporte);
        return reportes.getArrReportesEspecificos().size()-1;
    }

    public void aumentarCapacidadCoste() { //TODO: Crear aumento de los niveles
        //Aumentar tambien la capacidad maxima de "elixir"
        
    }

    public void subirNivel(){
        int aumento = (int)(Math.random() * 20) + 5;
        double aumentoPorcentual = (1+(aumento/100));
        
        this.setDamage((int)(this.damage*aumentoPorcentual)); 
        this.setVida((int)(this.vida*aumentoPorcentual));
        
    }
    
    //Funcion que retorna un bool si el objetivo esta muerto o no, tambien modifica los valores del objeto
    public boolean isDead(){
        if(this.vida <= 0){
            this.setVida(0);
            return true;
        }
        return false;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Gets y Sets >>>>>>>>>>>>>>>>>>>>>>

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
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

    public void setNivelAparicion(int nivelAparicion) {
        this.nivelAparicion = nivelAparicion;
    }

    public int getRango() {
        return rango;
    }

    public Historial getReporte() {
        return this.reportes;
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

    public int getDamage() {
        return damage;
    }
    
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Elemento getTipo() {
        return tipo;
    }

    public void setTipo(Elemento tipo) {
        this.tipo = tipo;
    }

    public boolean isIsVolador() {
        return isVolador;
    }

    public void setIsVolador(boolean isVolador) {
        this.isVolador = isVolador;
    }
    
    
}
