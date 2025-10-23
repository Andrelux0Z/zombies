/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sando
 */
public class Historial {
    private int ataqueRecibido;
    private int ataqueDado;
    private int vidaInicial;
    private int VidaFinal;

    public Historial(int ataqueRecibido, int ataqueDado, int vidaInicial, int VidaFinal) {
        this.ataqueRecibido = ataqueRecibido;
        this.ataqueDado = ataqueDado;
        this.vidaInicial = vidaInicial;
        this.VidaFinal = VidaFinal;
    }
    
            
    public int getAtaqueRecibido() {
        return ataqueRecibido;
    }

    public void setAtaqueRecibido(int ataqueRecibido) {
        this.ataqueRecibido += ataqueRecibido;
    }

    public int getAtaqueDado() {
        return ataqueDado;
    }

    public void setAtaqueDado(int ataqueDado) {
        this.ataqueDado += ataqueDado;
    }

    public int getVidaInicial() {
        return vidaInicial;
    }

    public int getVidaFinal() {
        return VidaFinal;
    }

    public void setVidaFinal(int VidaFinal) {
        this.VidaFinal = VidaFinal;
    }
    
    
    
}
