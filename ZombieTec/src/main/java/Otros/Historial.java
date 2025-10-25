package Otros;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sando
 */
public class Historial {
    private final int vidaInicial;
    private int vidaFinal;
    private ArrayList<ReportesEspecificos> arrReportesEspecificos;

    public Historial(int vidaInicial) {
        this.vidaInicial = vidaInicial;
        this.vidaFinal = vidaInicial;
        arrReportesEspecificos = new ArrayList<>();
        
    }

    public void mostrarReporteEspecifico(Elemento oposicion){
    //Si se presiona una Defensa o zombie (btn) mostrar el reporte especifico para 
    //ese par de de Elementos especifico
}
    
    public void mostrarHistorialCompleto(){
        //Al finalizar la batalla se mostrara el historial completo de cada Elemento
    }

    
    
    
    
    //////////Gets y set
     
    public int getVidaFinal() {
        return vidaFinal;
    }

    public void setVidaFinal(int vidaFinal) {
        this.vidaFinal = vidaFinal;
    }

    public ArrayList<ReportesEspecificos> getArrReportesEspecificos() {
        return arrReportesEspecificos;
    }

    public void setArrReportesEspecificos(ArrayList<ReportesEspecificos> arrReportesEspecificos) {
        this.arrReportesEspecificos = arrReportesEspecificos;
    }
    
    
    
}
