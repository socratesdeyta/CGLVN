package Modelo;

import Vista.frmCorrespondenciaEntrada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ConsultasCorrespondencia extends Conexion {
    
        public boolean buscarCaracter(ModeloCorrespondencia mod, frmCorrespondenciaEntrada frm) {
                // Color a las filas de la tabla (gris y blanco
//        FormatoTabla formatoTabla = new FormatoTabla();
//        jtActMinOfi.setDefaultRenderer(Object.class, formatoTabla);
        
        DefaultTableModel modeloTabla = new DefaultTableModel();
        frm.jtCorrespondencia.setModel(modeloTabla);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String clave = frm.txtBuscar.getText();
         String where = "";
        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta 
            // buscarLVN = "AND numero LIKE 'LVN-"+clave+"%'";
            where = "WHERE asunto LIKE '%" + clave + "%'";
        }
        String sql = "SELECT numero, siglas_tipoenvio, asunto, CONCAT ('( ', siglas_remitente, ' ) - ', nombre_remitente) AS remitente, "
                + " referencia, fecha_entrega FROM t_correspondencia "
                + " INNER JOIN t_tipoenvio ON t_correspondencia.documento = t_tipoenvio.id_tipoenvio "
                + " INNER JOIN t_remitente ON t_correspondencia.remitente = t_remitente.id_remitente "
                + where
                + " order by numero DESC";

  try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            //modeloTabla.addColumn("Numero");
            modeloTabla.addColumn("No.");
            modeloTabla.addColumn("Tipo");
            modeloTabla.addColumn("Asunto");
            modeloTabla.addColumn("Remitente");
            modeloTabla.addColumn("Referencia");
            //modeloTabla.addColumn("Fecha envio");
            modeloTabla.addColumn("Fecha entrega");

            //int[] anchos = {40, 32, 350, 150, 92, 92};
            //int[] anchos = {72, 72, 78, 78, 120, 645, 80};
            int[] anchos = {50, 40, 700, 320, 120, 120};
            for (int i = 0; i < frm.jtCorrespondencia.getColumnCount(); i++) {
                frm.jtCorrespondencia.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Object[] filas = new Object[rsMd.getColumnCount()];
                for (int i = 0; i < rsMd.getColumnCount(); i++) {
                    filas[i] = rs.getObject(i + 1);
                    //   System.out.println("Filas (" + filas[i] + ")");
                }
                   modeloTabla.addRow(filas);
            }
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

    public boolean Seleccionar(ModeloCorrespondencia mod, frmCorrespondenciaEntrada frm) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        try {
            int filaSeleccionada = frm.jtCorrespondencia.getSelectedRow();
            String clave = frm.jtCorrespondencia.getValueAt(filaSeleccionada, 0).toString();
//            ps = con.prepareStatement("SELECT numero, fecha_registro, seccion, serie, subserie, "
//                    + "destinatario, funcionario, t_tipoenvio.id_tipodocumento, envio, referencia, asunto, fecha_envio, responsable FROM t_documentosenviados "
//                    + "LEFT JOIN t_tipoenvio ON t_tipoenvio.id_tipoenvio = t_documentosenviados.envio "
//                    + "WHERE numero=?");
            ps = con.prepareStatement("SELECT numero, documento, remitente, CONCAT ('( ', siglas_remitente, ' ) - ', nombre_remitente) AS nombre_remitente, "
                    + " referencia, infoadicional, fecha_envio, fecha_entrega, asunto, responsable, asignado_a FROM t_correspondencia "
                    + " INNER JOIN t_tipoenvio ON t_correspondencia.documento = t_tipoenvio.id_tipoenvio "
                    + " INNER JOIN t_remitente ON t_correspondencia.remitente = t_remitente.id_remitente "
                    + "WHERE numero=?");
            ps.setString(1, clave);
            rs = ps.executeQuery();
            while (rs.next()) {
                //mod.setNumero(rs.getString("numero"));
                mod.setTipo_correspondencia(Integer.parseInt(rs.getString("documento")));
                int tipoEnvio = rs.getInt("documento");
                System.out.println("Valija y Correo -> " + tipoEnvio);
                switch (tipoEnvio) {
                    case 2: //
                        mod.setTipo_correspondencia(Integer.parseInt("1"));
                        break;
                    case 5:
                        mod.setTipo_correspondencia(Integer.parseInt("2"));
                        break;
                    default:
                        System.out.print("The entered value is unrecognized!");
                        break;
                }
                mod.setNombre_remitente(rs.getString("nombre_remitente"));
                mod.setReferencia(rs.getString("referencia"));
                mod.setInformacion(rs.getString("infoadicional"));
                mod.setFecha_envio(rs.getDate("fecha_envio"));
                mod.setFecha_entrega(rs.getDate("fecha_entrega"));
                mod.setAsunto(rs.getString("asunto"));
                ModeloSEM_Admin ModelSEMAdm = (ModeloSEM_Admin) frm.cbxAsignadoA.getSelectedItem();
                ModelSEMAdm.setId(rs.getInt("asignado_a"));
            }
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

    public int compararRemitente(String cadena) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String where = null;
        if (!"".equals(cadena)) { //Si no es null asigna el numero de campo a la consulta
            where = "WHERE CONCAT ('( ', siglas_remitente, ' ) - ', nombre_remitente) LIKE '" + cadena + "'";
            String sql = "SELECT id_remitente FROM t_remitente "
                    + where
                    + "order by numero_remitente ASC";
            try {
                ps = con.prepareStatement(sql);
                System.out.println(sql);
                rs = ps.executeQuery();
                System.out.println("---------Se ejecuta la consulta");

                while (rs.next()) {
                    System.out.println("Si existe un dato en la tabla------------------------------------");
                    int id_remitente = rs.getInt("id_remitente");
                    return id_remitente;
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

//------------------------------------------------------------------------------------------
////--- esta parte se ocupara para el formuario de minuta
//    public ArrayList<ModeloDestinoArray> mostrarArregloDestino() {
//        System.out.println("Entro a mostrar arreglo destino");
//        ArrayList<ModeloDestinoArray> datos = new ArrayList<>();
//        ModeloDestinoArray dat = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Connection con = getConexion();
//        //String sql = "SELECT numero_destino, nombre_destino FROM t_destino order by numero_destino asc";
//        String sql = "SELECT numero_destino, CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario FROM t_destino order by numero_destino asc";
//        try {
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                dat = new ModeloDestinoArray();
//                dat.setNumero_destino(rs.getString("numero_destino"));
////                dat.setNombre(rs.getString("seccion"));
////                dat.setNombre_destino(rs.getString("nombre_destino"));
//                dat.setNombre_destino(rs.getString("Destinatario"));
//                datos.add(dat);
//            }
//            rs.close();
//        } catch (SQLException ex) {
//            System.err.println(ex.toString());
//        } finally {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                System.err.println(e);
//            }
//        }
//        return datos;
//    }
    //--- esta parte se ocupara para el formuario de minuta
    public ArrayList<ModeloRemitenteArray> mostrarArregloRemitente() {
        System.out.println("Entro a mostrar arreglo remitente");
        ArrayList<ModeloRemitenteArray> datos = new ArrayList<>();
        ModeloRemitenteArray dat = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        //String sql = "SELECT numero_destino, nombre_destino FROM t_destino order by numero_destino asc";
        String sql = "SELECT numero_remitente, CONCAT ('( ', siglas_remitente, ' ) - ', nombre_remitente) AS Remitente FROM t_remitente order by numero_remitente asc";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                dat = new ModeloRemitenteArray();
                dat.setNumero_remitente(rs.getString("numero_remitente"));
//                dat.setNombre(rs.getString("seccion"));
//                dat.setNombre_destino(rs.getString("nombre_destino"));
                dat.setNombre_remitente(rs.getString("Remitente"));
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
    public Vector<ModeloSEM_Admin> mostrarAsignado_a() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloSEM_Admin> datos = new Vector<ModeloSEM_Admin>();
        ModeloSEM_Admin dat = null;
        String sql = "SELECT id_funcionarios, CONCAT(nombre, ' ', apellidop, ' ', apellidom) As concatenar FROM t_funcionarios WHERE id_cargo < 7 or id_area = 5";
        //String sql = "SELECT * FROM control_gestion.t_funcionarios WHERE id_cargo < 7 or id_area = 5";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new ModeloSEM_Admin();
            dat.setId(0);
            dat.setNombre("Selecciona");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloSEM_Admin();
                dat.setId(rs.getInt("id_funcionarios"));
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

    //-------
    public Vector<ModeloTipoCorres> mostrarTipoCorres() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloTipoCorres> datos = new Vector<ModeloTipoCorres>();
        ModeloTipoCorres dat = null;
        String sql = "SELECT id_tipoenvio, nombre_tipoenvio FROM t_tipoenvio WHERE id_tipoenvio = 2 or id_tipoenvio = 5";
        //String sql = "SELECT * FROM control_gestion.t_funcionarios WHERE id_cargo < 7 or id_area = 5";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new ModeloTipoCorres();
            dat.setId(0);
            dat.setNombre("Selecciona");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloTipoCorres();
                dat.setId(rs.getInt("id_tipoenvio"));
//                dat.setNombre(rs.getString("seccion"));
                dat.setNombre(rs.getString("nombre_tipoenvio"));
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

    public boolean registrar(ModeloCorrespondencia modCorres) {

        PreparedStatement ps = null;
        Connection con = getConexion();

        String ultimo = UltimoValor();
        System.out.println("Ultimo valor a agregar -->" + ultimo);

        System.out.println("TipoDocumento --> " + modCorres.getTipo_correspondencia());

        String sql = "INSERT INTO t_correspondencia (numero, documento, remitente, referencia, "
                + "infoadicional, fecha_envio, fecha_entrega, asunto, responsable, asignado_a) VALUES "
                + "(?,?,?,?,?,?,?,?,?,?)";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ultimo);
            ps.setInt(2, modCorres.getTipo_correspondencia());
            ps.setInt(3, modCorres.getRemitente());
            ps.setString(4, modCorres.getReferencia());
            ps.setString(5, modCorres.getInformacion());
            ps.setDate(6, modCorres.getFecha_envio());
            ps.setDate(7, modCorres.getFecha_entrega());
            ps.setString(8, modCorres.getAsunto());
            ps.setInt(9, modCorres.getResponsable());
            ps.setInt(10, modCorres.getAsignado_a());
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

        String sql = "SELECT (COUNT(*)+1) as Ultimo FROM t_correspondencia";
        //String sql = "SELECT (COUNT(*)) as Ultimo FROM t_documentosenviados";
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                contador = (Integer.parseInt(rs.getString("Ultimo")));
                if (contador < 10) {
                    ultimoValor = "000" + contador;
                } else if (contador < 100) {
                    ultimoValor = "00" + contador;
                } else if (contador < 1000) {
                    ultimoValor = "0" + contador;
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

}//Cierra la clase
