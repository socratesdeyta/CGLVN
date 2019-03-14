package Modelo;

import Vista.frmMinuta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ConsultasMinuta extends Conexion {

    public boolean Seleccionar(ModeloMinuta modMin, frmMinuta frmMin) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        try {
            int filaSeleccionada = frmMin.jtMinuta.getSelectedRow();
            String clave = frmMin.jtMinuta.getValueAt(filaSeleccionada, 0).toString();
//            ps = con.prepareStatement("SELECT numero, fecha_registro, seccion, serie, subserie, destinatario, funcionario, envio, asunto FROM t_documentosenviados WHERE numero=?");
            ps = con.prepareStatement("SELECT numero, fecha_registro, seccion, serie, subserie, destinatario, funcionario, envio, asunto FROM t_documentosenviados WHERE numero=?");

            ps.setString(1, clave);

            rs = ps.executeQuery();

            System.out.println("---------Se ejecuta la consulta");

            while (rs.next()) {
                //fun.setId(Integer.parseInt(rs.getString("id_funcionario")));
                modMin.setNumero(rs.getString("numero"));
                modMin.setFecha_registro(rs.getDate("fecha_registro"));
                modMin.setSeccion(Integer.parseInt(rs.getString("seccion")));
                modMin.setSerie(Integer.parseInt(rs.getString("serie")));

//                modMin.setSubserie(Integer.parseInt(rs.getString("subserie")));
                modMin.setDestinatario(Integer.parseInt(rs.getString("destinatario")));
                modMin.setFuncionario(rs.getString("funcionario"));
                modMin.setEnvio(Integer.parseInt(rs.getString("envio")));
                modMin.setAsunto(rs.getString("asunto"));

            }

            System.out.println("Numero LVN-> " + modMin.getNumero());
            System.out.println("Fecha de Registro> " + modMin.getFecha_registro());
            System.out.println("Seccion--> " + modMin.getSeccion());
            System.out.println("Serie--> " + modMin.getSerie());
            System.out.println("Subserie--> " + modMin.getSubserie());
            System.out.println("Destinatario--> " + modMin.getDestinatario());
            System.out.println("Funcionario--> " + modMin.getFuncionario());
            System.out.println("Envio--> " + modMin.getEnvio());
            System.out.println("Asunto--> " + modMin.getAsunto());

            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public Vector<ModeloSeccion> mostrarSeccion() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloSeccion> datos = new Vector<ModeloSeccion>();
        ModeloSeccion dat = null;
        String sql = "SELECT id_seccion, codigo_seccion, seccion, CONCAT(codigo_seccion, ' ', seccion) As concatenar FROM t_seccion";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new ModeloSeccion();
            dat.setId(0);
            dat.setNombre("Selecciona seccion");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloSeccion();
                dat.setId(rs.getInt("id_seccion"));
//                dat.setNombre(rs.getString("seccion"));
                dat.setNombre(rs.getString("concatenar"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }
    
    
        public String mostrarNombreFuncionario(String clave_funcionario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String tipoDoc = "";
        TipoDocumento dat = null;
        //String sql = "SELECT id_tipodocumento, nombre_tipodocumento, siglas_tipodocumento FROM t_tipodocumento WHERE id_tipodocumento=" + idtipodocumento;
        String sql = "SELECT CONCAT(nombre, ' ', apellidop, ' ', apellidom) As Nombre FROM t_funcionarios WHERE clave=" + clave_funcionario;
        System.out.println("SQL " + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new TipoDocumento();
            while (rs.next()) {
                dat = new TipoDocumento();
                dat.setNombre(rs.getString("nombre"));
                System.out.println("Si lo asigno" + dat.getNombre());
                tipoDoc = dat.getNombre();
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return tipoDoc;
    }
    
//Lo cambie de Integer a int
    public Vector<Serie> mostrarSerie(int idSeccion) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<Serie> datos = new Vector<Serie>();
        Serie dat = null;
        String sql = "SELECT id_serie, codigo_serie, serie, CONCAT (codigo_serie, ' ', serie) AS concatenar FROM t_serie WHERE id_seccion=" + idSeccion;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new Serie();
            dat.setId(0);
            dat.setNombre("Selecciona serie");
            datos.add(dat);
            while (rs.next()) {
                dat = new Serie();
                dat.setId(rs.getInt("id_serie"));
                // dat.setNombre(rs.getString("serie"));
                dat.setNombre(rs.getString("concatenar"));
                datos.add(dat);
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }

    public Vector<Serie> mostrarSerie() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<Serie> datos = new Vector<Serie>();
        Serie dat = null;
        String sql = "SELECT id_serie, codigo_serie, serie, CONCAT (codigo_serie, ' ', serie) AS concatenar FROM t_serie";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new Serie();
            dat.setId(0);
            dat.setNombre("Seleccionar serie");
            datos.add(dat);
            while (rs.next()) {
                dat = new Serie();
                dat.setId(rs.getInt("id_serie"));
                // dat.setNombre(rs.getString("serie"));
                dat.setNombre(rs.getString("concatenar"));
                datos.add(dat);
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }

    public Vector<Subserie> mostrarSubserie(int idSerie) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<Subserie> datos = new Vector<Subserie>();
        Subserie dat = null;
        String sql = "SELECT id_subserie, codigo_subserie, subserie, CONCAT (codigo_subserie, ' ', subserie) AS concatenar FROM t_subserie WHERE id_serie=" + idSerie;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new Subserie();
            dat.setId(0);
            dat.setNombre("Selecciona subserie");
            datos.add(dat);
            while (rs.next()) {
                dat = new Subserie();
                dat.setId(rs.getInt("id_subserie"));
                // dat.setNombre(rs.getString("subserie"));
                dat.setNombre(rs.getString("concatenar"));
                datos.add(dat);
            }
//                     System.out.println("modelo.subserie.mostrarSubserie() Despues del ciclo " + datos.size());
//            
//                     if (datos.isEmpty()){         
//                        System.out.println("Asignar null a subserie " + datos.size());
//                        datos = null;
//            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }

    public Vector<Subserie> mostrarSubserie() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<Subserie> datos = new Vector<Subserie>();
        Subserie dat = null;
        String sql = "SELECT id_subserie, codigo_subserie, subserie, CONCAT (codigo_subserie, ' ', subserie) AS concatenar FROM t_subserie";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new Subserie();
            dat.setId(0);
            dat.setNombre("Selecciona subserie");
            datos.add(dat);
            while (rs.next()) {
                dat = new Subserie();
                dat.setId(rs.getInt("id_subserie"));
                // dat.setNombre(rs.getString("subserie"));
                dat.setNombre(rs.getString("concatenar"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }
    
    
        public Vector<FuncionarioCbx> mostrarFuncionario() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<FuncionarioCbx> datos = new Vector<FuncionarioCbx>();
        FuncionarioCbx dat = null;
        String sql = "SELECT id_subserie, codigo_subserie, subserie, CONCAT (codigo_subserie, ' ', subserie) AS concatenar FROM t_subserie";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new FuncionarioCbx();
            dat.setId(0);
            dat.setNombre("Selecciona subserie");
            datos.add(dat);
            while (rs.next()) {
                dat = new FuncionarioCbx();
                dat.setId(rs.getInt("id_subserie"));
                // dat.setNombre(rs.getString("subserie"));
                dat.setNombre(rs.getString("concatenar"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }
    
    

    public Vector<Destinatario> mostrarDestinatario() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<Destinatario> datos = new Vector<Destinatario>();
        Destinatario dat = null;
        // String sql = "SELECT id_destinatario, tipo_destinatario, siglas_destinatario, nombre_destinatario, CONCAT(siglas_destinatario, ' - ', nombre_destinatario) As concatenar FROM t_destinatario";
        String sql = "SELECT id_destino, nombre_destino, siglas_destino, CONCAT(siglas_destino, ' - ', nombre_destino) As concatenar FROM t_destino";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new Destinatario();
            dat.setId(0);
            dat.setNombre("Selecciona Destinatario");
            datos.add(dat);
            while (rs.next()) {
                dat = new Destinatario();
                dat.setId(rs.getInt("id_destino"));
//                dat.setNombre(rs.getString("seccion"));
                dat.setNombre(rs.getString("concatenar"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }
    
        public Vector<Envio> mostrarEnvio(int idtipodocumento) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<Envio> datos = new Vector<Envio>();
        Envio dat = null;
        String sql = "SELECT id_tipoenvio, nombre_tipoenvio, siglas_tipoenvio, id_tipodocumento FROM t_tipoenvio WHERE id_tipodocumento=" + idtipodocumento;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new Envio();

            //Se comento para que no muestre el primer elemento
            //            dat.setId(0);
            //            dat.setNombre("Selecciona Tipo de Envio");
            //            datos.add(dat);
            while (rs.next()) {
                dat = new Envio();
                dat.setId(rs.getInt("id_tipoenvio"));
                dat.setNombre(rs.getString("nombre_tipoenvio"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }
        
         public Vector<Envio> mostrarEnvio() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<Envio> datos = new Vector<Envio>();
        Envio dat = null;
        String sql = "SELECT id_tipoenvio, nombre_tipoenvio, siglas_tipoenvio, id_tipodocumento FROM t_tipoenvio" ;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new Envio();

            while (rs.next()) {
                dat = new Envio();
                dat.setId(rs.getInt("id_tipoenvio"));
                dat.setNombre(rs.getString("nombre_tipoenvio"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }
    
        public Vector<TipoDocumento> mostrarDocumento(int idtipodocumento) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<TipoDocumento> datos = new Vector<TipoDocumento>();
        TipoDocumento dat = null;
        String sql = "SELECT id_tipodocumento, nombre_tipodocumento FROM t_tipodocumento WHERE id_tipodocumento=" + idtipodocumento;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new TipoDocumento();
            //Se comento para que no muestre el primer elemento
            //            dat.setId(0);
            //            dat.setNombre("Selecciona Tipo de Envio");
            //            datos.add(dat);
            while (rs.next()) {
                dat = new TipoDocumento();
                dat.setId(rs.getInt("id_tipodocumento"));
                dat.setNombre(rs.getString("nombre_tipodocumento"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }
        


}
