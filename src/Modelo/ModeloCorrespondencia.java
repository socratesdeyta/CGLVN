package Modelo;

import java.sql.Date;

public class ModeloCorrespondencia {

    private int id_correspodencia;
    private char numero;
    private int tipo_correspondencia;
    private int remitente;
    private String nombre_remitente;
    private String referencia;
    private String informacion;
    private Date fecha_envio;
    private Date fecha_entrega;
    private String asunto;
    private int responsable;
    private int asignado_a;
    private String otros;

    public String getNombre_remitente() {
        return nombre_remitente;
    }

    public void setNombre_remitente(String nombre_remitente) {
        this.nombre_remitente = nombre_remitente;
    }
    
    public int getId_correspodencia() {
        return id_correspodencia;
    }

    public void setId_correspodencia(int id_correspodencia) {
        this.id_correspodencia = id_correspodencia;
    }

    public char getNumero() {
        return numero;
    }

    public void setNumero(char numero) {
        this.numero = numero;
    }

    public int getTipo_correspondencia() {
        return tipo_correspondencia;
    }

    public void setTipo_correspondencia(int tipo_correspondencia) {
        this.tipo_correspondencia = tipo_correspondencia;
    }

    public int getRemitente() {
        return remitente;
    }

    public void setRemitente(int remitente) {
        this.remitente = remitente;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public Date getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(Date fecha_envio) {
        this.fecha_envio = fecha_envio;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public int getResponsable() {
        return responsable;
    }

    public void setResponsable(int responsable) {
        this.responsable = responsable;
    }

    public int getAsignado_a() {
        return asignado_a;
    }

    public void setAsignado_a(int asignado_a) {
        this.asignado_a = asignado_a;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }
    
    

}
