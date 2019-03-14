package Modelo;

import Vista.frmDestino;
import Vista.frmMinutarioSalida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

public class ConsultasMinutarioSalida extends Conexion {

    //Prueba para combo autocompletado
    //public DefaultComboBoxModel getLista(ModeloMinutarioSalida mod, frmMinutarioSalida frm, String cadenaEscrita) throws SQLException {
//    public DefaultComboBoxModel getLista(String cadenaEscrita) throws SQLException {  
//    DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
//        //frm.cbxDestinatario.setModel(modeloCombo);
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Connection con = getConexion();
//
//        String where = "";
//        if (!"".equals(cadenaEscrita)) { //Si no es null asigna el numero de campo a la consulta
//
//            where = "WHERE CONCAT (siglas_destino, nombre_destino) LIKE '%" + cadenaEscrita + "%'";
//        }
//        String sql = "SELECT CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario, "
//                + "nombre_tipodestino, nombre_subtipodestino FROM t_destino "
//                + "INNER JOIN t_tipodestino ON t_destino.id_tipodestino = t_tipodestino.id_tipodestino "
//                + "INNER JOIN t_subtipodestino ON t_destino.id_subtipodestino = t_subtipodestino.id_subtipodestino "
//                + where
//                + "order by numero_destino ASC";
//        try {
//            ps = con.prepareStatement(sql);
//            System.out.println(sql);
//            rs = ps.executeQuery();
//            System.out.println("---------Se ejecuta la consulta");
//            while (rs.next()) {
//                modeloCombo.addElement(rs.getString("Destinatario"));
//            }
//            return modeloCombo;
//        } catch (SQLException e) {
//            System.err.println(e);
//        } finally {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                System.err.println(e);
//            }
//        }
//        return null;
//    }
    //Carga la tabla pensar si mejor llamarla "cargar tabla"
    //Se llama a este metodo cuando se pone el cursor en el cuadro de texto destinatario
    public int compararDestinatario(String cadena) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String where = null;
        if (!"".equals(cadena)) { //Si no es null asigna el numero de campo a la consulta
            where = "WHERE CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) LIKE '" + cadena + "'";
            String sql = "SELECT id_destino FROM t_destino "
                    + where
                    + "order by numero_destino ASC";
            try {
                ps = con.prepareStatement(sql);
                System.out.println(sql);
                rs = ps.executeQuery();
                System.out.println("---------Se ejecuta la consulta");

                while (rs.next()) {

                    System.out.println("Si existe un dato en la tabla------------------------------------");
                    int id_destino = rs.getInt("id_destino");
                    return id_destino;
                }
            } catch (SQLException e) {
                System.err.println(e);

            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        }
        return 0;
    }

//    //Se llama a este metodo cuando se pone el cursor en el cuadro de texto destinatario
//    public int compararDestinatario(String cadena) {
//        //DefaultTableModel modeloTabla = new DefaultTableModel();
//        //frm.jtDestino.setModel(modeloTabla);
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Connection con = getConexion();
//        //String clave = frm.txtSiglas.getText();
//        //String clave = frm.txtBuscar.getText();
//        //String where = "0";  // Se cambio de "" a "0"
//        String where = "WHERE CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) LIKE '0'";
//        if (!"".equals(cadena)) { //Si no es null asigna el numero de campo a la consulta
//            //where = "WHERE nombre_destino = '" + clave + "'";
////            where = "WHERE Destinatario LIKE '%" + clave + "%'";
//            //where = "WHERE nombre_destino LIKE '%" + clave + "%'";
//            //where = "WHERE CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) LIKE '%" + cadena + "%'";
//            where = "WHERE CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) LIKE '" + cadena + "'";
//        }
//        //String sql = "SELECT numero_destino, CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario, "
//        String sql = "SELECT id_destino FROM t_destino "
//                + where
//                + "order by numero_destino ASC";
//        try {
//            ps = con.prepareStatement(sql);
//            System.out.println(sql);
//            rs = ps.executeQuery();
//            System.out.println("---------Se ejecuta la consulta");
////            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
//            //modeloTabla.addColumn("Numero");
////            modeloTabla.addColumn("Destinatario");
////            modeloTabla.addColumn("Tipo destino");
////            modeloTabla.addColumn("Subtipo destino");
//            //int[] anchos = {15, 300, 50, 100};
////            int[] anchos = {300, 50, 100};
////            for (int i = 0; i < frm.jtDestino.getColumnCount(); i++) {
////                frm.jtDestino.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
////            }
//            while (rs.next()) {
////                Object[] filas = new Object[rsMd.getColumnCount()];
////                for (int i = 0; i < rsMd.getColumnCount(); i++) {
////                    filas[i] = rs.getObject(i + 1);
//                System.out.println("Si existe un dato en la tabla------------------------------------");
//                int id_destino = rs.getInt("id_destino");
//                return id_destino;
//            }
//
//            //Aqui debe hacer algo
//            //modeloTabla.addRow(filas);
////            }
//        } catch (SQLException e) {
//            System.err.println(e);
//
//        } finally {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                System.err.println(e);
//            }
//        }
//        return 0;
//    }
//------------------------------------------------------------------------------------------
//--- esta parte se ocupara para el formuario de minuta
    public ArrayList<ModeloDestinoArray> mostrarArregloDestino() {
        System.out.println("Entro a mostrar arreglo destino");
        ArrayList<ModeloDestinoArray> datos = new ArrayList<>();
        ModeloDestinoArray dat = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        //String sql = "SELECT numero_destino, nombre_destino FROM t_destino order by numero_destino asc";
        String sql = "SELECT numero_destino, CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario FROM t_destino order by numero_destino asc";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                dat = new ModeloDestinoArray();
                dat.setNumero_destino(rs.getString("numero_destino"));
//                dat.setNombre(rs.getString("seccion"));
//                dat.setNombre_destino(rs.getString("nombre_destino"));
                dat.setNombre_destino(rs.getString("Destinatario"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return datos;
    }

    //--- Muestra el LVN-Asignado despues de la insersipn
    //public ArrayList<ModeloLVN> mostrarLVN(int funcionario) {
    public String mostrarLVN(int funcionario) {
        System.out.println("Entro a mostrar mostrarLVN");
        String lvn = null;
        //ArrayList<ModeloDestinoArray> datos = new ArrayList<>();
        //ModeloDestinoArray dat = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        //String sql = "SELECT numero_destino, nombre_destino FROM t_destino order by numero_destino asc";
        //String sql = "SELECT id_documentos, CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario FROM t_destino order by numero_destino asc";
        String sql = "SELECT numero FROM control_gestion.t_documentosenviados WHERE funcionario = " + funcionario + " ORDER BY id_documentosenviados DESC LIMIT 1";
        //String sql = "SELECT id_subserie, codigo_subserie, subserie, CONCAT (codigo_subserie, ' ', subserie) AS concatenar FROM t_subserie WHERE id_serie=" + idSerie;

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                //dat = new ModeloDestinoArray();

                //dat.setNumero_destino(rs.getString("numero_destino"));
                lvn = rs.getString("numero");
                System.out.println("mostrarLVN()-->" + lvn);
//                dat.setNombre(rs.getString("seccion"));
//                dat.setNombre_destino(rs.getString("nombre_destino"));
                //dat.setNombre_destino(rs.getString("Destinatario"));
                //datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return lvn;
    }

    //-------
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
    
//        public Vector<ModeloSeccion> mostrarSeccion(int id_seccion) {
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Connection con = getConexion();
//        Vector<ModeloSeccion> datos = new Vector<ModeloSeccion>();
//        ModeloSeccion dat = null;
//        String sql = "SELECT id_seccion, codigo_seccion, seccion, CONCAT(codigo_seccion, ' ', seccion) As concatenar FROM t_seccion WHERE id_seccion=" + id_seccion;
//        try {
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            dat = new ModeloSeccion();
//            //dat.setId(0);
//            //dat.setNombre("Selecciona seccion");
//            datos.add(dat);
//            while (rs.next()) {
//                dat = new ModeloSeccion();
//                dat.setId(rs.getInt("id_seccion"));
////                dat.setNombre(rs.getString("seccion"));
//                dat.setNombre(rs.getString("concatenar"));
//                datos.add(dat);
//            }
//            rs.close();
//        } catch (SQLException ex) {
//            System.err.println(ex.toString());
//        }
//        return datos;
//    }
    
    
//        public Vector<Serie> mostrarSerie() {
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Connection con = getConexion();
//        Vector<Serie> datos = new Vector<Serie>();
//        Serie dat = null;
//        String sql = "SELECT id_serie, codigo_serie, serie, CONCAT (codigo_serie, ' ', serie) AS concatenar FROM t_serie WHERE id_seccion=" + idSeccion;
//        try {
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//
//            dat = new Serie();
//            dat.setId(0);
//            dat.setNombre("Selecciona serie");
//            datos.add(dat);
//            while (rs.next()) {
//                dat = new Serie();
//                dat.setId(rs.getInt("id_serie"));
//                // dat.setNombre(rs.getString("serie"));
//                dat.setNombre(rs.getString("concatenar"));
//                datos.add(dat);
//            }
//            rs.close();
//
//        } catch (SQLException ex) {
//            System.err.println(ex.toString());
//        }
//        return datos;
//    }

    public Vector<Serie> mostrarSerie(Integer idSeccion) {
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

    // Modulo que regresa un String cuando solo se quiere conocer un dato.
    public String mostrarTipoDocumento(int idtipodocumento) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String tipoDoc = "";
        TipoDocumento dat = null;
        //String sql = "SELECT id_tipodocumento, nombre_tipodocumento, siglas_tipodocumento FROM t_tipodocumento WHERE id_tipodocumento=" + idtipodocumento;
        String sql = "SELECT nombre_tipodocumento FROM t_tipodocumento WHERE id_tipodocumento=" + idtipodocumento;
        System.out.println("SQL" + sql);
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new TipoDocumento();
            while (rs.next()) {
                dat = new TipoDocumento();
                dat.setNombre(rs.getString("nombre_tipodocumento"));
                System.out.println("Si lo asigno" + dat.getNombre());
                tipoDoc = dat.getNombre();
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return tipoDoc;
    }

    public boolean registrar(ModeloMinutarioSalida modMinSal, int subserieAsignada) {

        PreparedStatement ps = null;
        Connection con = getConexion();

        String ultimo = UltimoValor();
        System.out.println("Ultimo valor a agregar -->" + ultimo);
        int sub = subserieAsignada;

        String sql = "INSERT INTO t_documentosenviados (numero, fecha_registro, seccion, serie, "
                + "subserie, destinatario, funcionario, envio, asunto)VALUES "
                + "(?,?,?,?,?,?,?,?,?)";

        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ultimo);
            ps.setDate(2, modMinSal.getFecha_registro());
            ps.setInt(3, modMinSal.getSeccion());
            ps.setInt(4, modMinSal.getSerie());
            System.out.println("EN INSERTAR " + subserieAsignada);
            if (sub != 0) {
                ps.setInt(5, modMinSal.getSubserie());
                System.out.println("EN INSERTAR es diferente de 0 " + modMinSal.getSubserie());

            } else {
                ps.setNull(5, modMinSal.getSubserie());
                System.out.println("No existe Subserie " + modMinSal.getSubserie());
            }

            //ps.setNull(5, modMinSal.getSubserie());
            ps.setInt(6, modMinSal.getDestinatario());
            //ps.setString(7, modMinSal.getFuncionario());
            ps.setInt(7, modMinSal.getFuncionario());
            ps.setInt(8, modMinSal.getEnvio());
            ps.setString(9, modMinSal.getAsunto());
            ps.execute();
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

    public String UltimoValor() {
        Connection con = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int contador;
        String ultimoValor = "";

        String sql = "SELECT (COUNT(*)+1) as Ultimo FROM t_documentosenviados";
        //String sql = "SELECT (COUNT(*)) as Ultimo FROM t_documentosenviados";
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                contador = (Integer.parseInt(rs.getString("Ultimo")));
                if (contador < 10) {
                    ultimoValor = "LVN-000" + contador;
                } else if (contador < 100) {
                    ultimoValor = "LVN-00" + contador;
                } else if (contador < 1000) {
                    ultimoValor = "LVN-0" + contador;
                }
            }
            return ultimoValor;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public Object[] mostrarSerie(String numeroSeleccionadoenComboBox) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}//Cierra la clase
