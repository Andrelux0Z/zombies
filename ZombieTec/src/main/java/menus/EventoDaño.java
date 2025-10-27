package menus;

// Clase para representar un evento de daño
public class EventoDaño {
    private final int atacanteId;
    private final String atacanteTipo;
    private final int objetivoId;
    private final String objetivoTipo;
    private final int daño;
    private final long tiempo;

    public EventoDaño(int atacanteId, String atacanteTipo, int objetivoId, String objetivoTipo, int daño, long tiempo) {
        this.atacanteId = atacanteId;
        this.atacanteTipo = atacanteTipo;
        this.objetivoId = objetivoId;
        this.objetivoTipo = objetivoTipo;
        this.daño = daño;
        this.tiempo = tiempo;
    }

    public String toString() {
        return String.format("[%d ms] %s #%d hizo %d daño a %s #%d",
                tiempo, atacanteTipo, atacanteId, daño, objetivoTipo, objetivoId);
    }

    // Getters
    public int getAtacanteId() {
        return atacanteId;
    }

    public String getAtacanteTipo() {
        return atacanteTipo;
    }

    public int getObjetivoId() {
        return objetivoId;
    }

    public String getObjetivoTipo() {
        return objetivoTipo;
    }

    public int getDaño() {
        return daño;
    }
}