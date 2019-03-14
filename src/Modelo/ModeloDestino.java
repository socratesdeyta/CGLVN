package Modelo;

public class ModeloDestino {

    private int id_destino;
    private String numero_destino;
    private String nombre_destino;
    private String siglas_destino;
    private int id_tipodestino;
    private int id_subtipodestino;
    //private String funcionario;
    private int funcionario;

    public String getNumero_destino() {
        return numero_destino;
    }

    public int getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(int funcionario) {
        this.funcionario = funcionario;
    }

    public void setNumero_destino(String numero_destino) {
        this.numero_destino = numero_destino;
    }

    public int getId_destino() {
        return id_destino;
    }

    public void setId_destino(int id_destino) {
        this.id_destino = id_destino;
    }

    public String getNombre_destino() {
        return nombre_destino;
    }

    public void setNombre_destino(String nombre_destino) {
        this.nombre_destino = nombre_destino;
    }

    public String getSiglas_destino() {
        return siglas_destino;
    }

    public void setSiglas_destino(String siglas_destino) {
        this.siglas_destino = siglas_destino;
    }

    public int getId_tipodestino() {
        return id_tipodestino;
    }

    public void setId_tipodestino(int id_tipodestino) {
        this.id_tipodestino = id_tipodestino;
    }

    public int getId_subtipodestino() {
        return id_subtipodestino;
    }

    public void setId_subtipodestino(int id_subtipodestino) {
        this.id_subtipodestino = id_subtipodestino;
    }

//    public String getFuncionario() {
//        return funcionario;
//    }
//
//    public void setFuncionario(String funcionario) {
//        this.funcionario = funcionario;
//    }
}
