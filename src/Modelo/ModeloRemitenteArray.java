package Modelo;

public class ModeloRemitenteArray {

    private String numero_remitente;
    private String nombre_remitente;

    public String getNumero_remitente() {
        return numero_remitente;
    }

    public void setNumero_remitente(String numero_remitente) {
        this.numero_remitente = numero_remitente;
    }

    public String getNombre_remitente() {
        return nombre_remitente;
    }

    public void setNombre_remitente(String nombre_remitente) {
        this.nombre_remitente = nombre_remitente;
    }

            public String toString() {
        return this.nombre_remitente;
    }
    
}
