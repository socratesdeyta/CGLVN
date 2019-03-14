package Modelo;

import java.sql.Date;

public class ModeloFavorito {

    private int id_documentosenviados;
    private char numero;
    private Date fecha_registro;
    private int seccion;
    private int serie;
    private int subserie;
    private int destinatario;
    //private String funcionario;
    private int funcionario;
    //private int documento;
    private int envio;
   // private String referencia;
    private String asunto;
    private Date fecha_envio;
    private int responsable;
    private String otro_destinatario;
    private String otras_referencias;

    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }
    
    

    public int getId_documentosenviados() {
        return id_documentosenviados;
    }

    public void setId_documentosenviados(int id_documentosenviados) {
        this.id_documentosenviados = id_documentosenviados;
    }

    public char getNumero() {
        return numero;
    }

    public void setNumero(char numero) {
        this.numero = numero;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public int getSeccion() {
        return seccion;
    }

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getSubserie() {
        return subserie;
    }

    public void setSubserie(int subserie) {
        this.subserie = subserie;
    }

    public int getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(int destinatario) {
        this.destinatario = destinatario;
    }

//    public String getFuncionario() {
//        return funcionario;
//    }
//
//    public void setFuncionario(String funcionario) {
//        this.funcionario = funcionario;
//    }

//    public int getDocumento() {
//        return documento;
//    }
//
//    public void setDocumento(int documento) {
//        this.documento = documento;
//    }

    public int getEnvio() {
        return envio;
    }

    public void setEnvio(int envio) {
        this.envio = envio;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Date getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(Date fecha_envio) {
        this.fecha_envio = fecha_envio;
    }

    public int getResponsable() {
        return responsable;
    }

    public void setResponsable(int responsable) {
        this.responsable = responsable;
    }

    public String getOtro_destinatario() {
        return otro_destinatario;
    }

    public void setOtro_destinatario(String otro_destinatario) {
        this.otro_destinatario = otro_destinatario;
    }

    public String getOtras_referencias() {
        return otras_referencias;
    }

    public void setOtras_referencias(String otras_referencias) {
        this.otras_referencias = otras_referencias;
    }
    
    

}
