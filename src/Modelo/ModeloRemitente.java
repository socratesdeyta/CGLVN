package Modelo;

public class ModeloRemitente {

    private int id_remitente;
    private String numero_remitente;
    private String nombre_remitente;
    private String siglas_remitente;
    private int id_tiporemitente;
    private int id_subtiporemitente;
    //private String funcionario;
    private int funcionario;

    public int getId_remitente() {
        return id_remitente;
    }

    public void setId_remitente(int id_remitente) {
        this.id_remitente = id_remitente;
    }

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

    public String getSiglas_remitente() {
        return siglas_remitente;
    }

    public void setSiglas_remitente(String siglas_remitente) {
        this.siglas_remitente = siglas_remitente;
    }

    public int getId_tiporemitente() {
        return id_tiporemitente;
    }

    public void setId_tiporemitente(int id_tiporemitente) {
        this.id_tiporemitente = id_tiporemitente;
    }

    public int getId_subtiporemitente() {
        return id_subtiporemitente;
    }

    public void setId_subtiporemitente(int id_subtiporemitente) {
        this.id_subtiporemitente = id_subtiporemitente;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    
}
