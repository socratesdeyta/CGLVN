package Modelo;

import Vista.frmRemitente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultasRemitente extends Conexion {

    public Vector<ModeloTipoRemitente> mostrarTipoRemitente() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloTipoRemitente> datos = new Vector<>();
        ModeloTipoRemitente dat = null;
        String sql = "SELECT id_tiporemitente, nombre_tiporemitente FROM t_tiporemitente";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new ModeloTipoRemitente();
            dat.setId(0);
            dat.setNombre("Selecciona");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloTipoRemitente();
                dat.setId(rs.getInt("id_tiporemitente"));
                dat.setNombre(rs.getString("nombre_tiporemitente"));
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
    


    //SE PUEDE OCUPAR UN SOLO MODELO PARA TODAS LAS CONSULTAS????
    //Se puede cambiar los vectores por ArrayList??????????
    public Vector<ModeloSubtipoRemitente> mostrarSubtipoRemitente(int idTipoRemitente) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloSubtipoRemitente> datos = new Vector<>();
        ModeloSubtipoRemitente dat = null;
        String sql = "SELECT id_subtiporemitente, nombre_subtiporemitente FROM t_subtiporemitente WHERE id_tiporemitente =" + idTipoRemitente;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new ModeloSubtipoRemitente();
            dat.setId(0);
            dat.setNombre("Selecciona");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloSubtipoRemitente();
                dat.setId(rs.getInt("id_subtiporemitente"));
                dat.setNombre(rs.getString("nombre_subtiporemitente"));
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

    public boolean Seleccionar(ModeloRemitente mod, frmRemitente frm) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        try {
            int filaSeleccionada = frm.jtRemitente.getSelectedRow();
            String clave = frm.jtRemitente.getValueAt(filaSeleccionada, 0).toString();

            ps = con.prepareStatement("SELECT numero_remitente, siglas_remitente, nombre_remitente, id_tiporemitente, id_subtiporemitente "
                    + "FROM t_remitente WHERE CONCAT ('( ', siglas_remitente, ' ) - ', nombre_remitente) =?");

            ps.setString(1, clave);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            while (rs.next()) {
                //fun.setId(Integer.parseInt(rs.getString("id_funcionario")));
                mod.setNumero_remitente(rs.getString("numero_remitente"));
                mod.setSiglas_remitente(rs.getString("siglas_remitente"));
                mod.setNombre_remitente(rs.getString("nombre_remitente"));
                mod.setId_tiporemitente(Integer.parseInt(rs.getString("id_tiporemitente")));
                mod.setId_subtiporemitente(Integer.parseInt(rs.getString("id_subtiporemitente")));
            }

            System.out.println("Numero Remitente-> " + mod.getNumero_remitente());
            System.out.println("Siglas> " + mod.getSiglas_remitente());
            System.out.println("Nombre--> " + mod.getNombre_remitente());
            System.out.println("Tipo--> " + mod.getId_tiporemitente());
            System.out.println("Subtipo--> " + mod.getId_subtiporemitente());

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

    public boolean registrar(ModeloRemitente mod) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String ultimo = UltimoValorAgregado();
        System.out.println("ultimo " + ultimo);
        //int sub = subserieAsignada;

        String sql = "INSERT INTO t_remitente (numero_remitente, nombre_remitente, siglas_remitente, id_tiporemitente, id_subtiporemitente, id_funcionario) VALUES"
                + "(?,?,?,?,?,?) ";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ultimo);
            ps.setString(2, mod.getNombre_remitente());
            ps.setString(3, mod.getSiglas_remitente());
            ps.setInt(4, mod.getId_tiporemitente());
            
            int subtipo = mod.getId_subtiporemitente();
            int total = subtipo + 7;
            System.out.println("Total "+ total);
            
            int tipo = mod.getId_tiporemitente();
            if (tipo == 2) {
                ps.setInt(5, total);
            } else {
                ps.setInt(5, mod.getId_subtiporemitente());
            }
            //ps.setString(6, mod.getFuncionario());
            ps.setInt(6, mod.getFuncionario());
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

    public int existeSiglas(String SiglasRemitente) {
        System.out.println("El valor que se pasa si llego bien? " + SiglasRemitente);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT siglas_remitente FROM t_remitente WHERE siglas_remitente = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, SiglasRemitente);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Hay registros");
                return 1;
            }
            System.out.println("No hay");
            return 0;
        } catch (SQLException e) {
            System.err.println(e);
            return 0;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public int existeNombre(String NombreRemitente) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT nombre_remitente FROM t_remitente WHERE nombre_remitente = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, NombreRemitente);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Hay registros");
                return 1;
            }
            System.out.println("No hay");
            return 0;
        } catch (SQLException e) {
            System.err.println(e);
            return 0;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public String UltimoValorAgregado() {
        Connection con = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int contador;
        String ultimoValorAgregado = "";
        String sql = "SELECT last_insert_id(numero_remitente)+1 as Ultimo FROM t_remitente order by numero_remitente desc limit 1";
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                contador = (Integer.parseInt(rs.getString("Ultimo")));
                if (contador < 10) {
                    ultimoValorAgregado = "00" + contador;
                } else if (contador < 100) {
                    ultimoValorAgregado = "0" + contador;
                } else if (contador < 1000) {
                    ultimoValorAgregado = "" + contador;
                }
            }
            System.out.println("Modelo.Consultasremitente.UltimoValorAgregado()" + ultimoValorAgregado);
            return ultimoValorAgregado;
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

    public boolean modificar(ModeloRemitente mod) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE t_remitente SET numero_remitente=?, nombre_remitente=?, siglas_remitente=?, "
                + "id_tiporemitente=?, id_subtiporemitente=? WHERE numero_remitente=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, mod.getNumero_remitente());
            ps.setString(2, mod.getNombre_remitente());
            ps.setString(3, mod.getSiglas_remitente());
            ps.setInt(4, mod.getId_tiporemitente());
            
            int subtipo = mod.getId_subtiporemitente();
            int total = subtipo + 7;
            System.out.println("Total "+ total);
            int tipo = mod.getId_tiporemitente();
            if (tipo == 2) {
                ps.setInt(5, total);
            } else {
                ps.setInt(5, mod.getId_subtiporemitente());
            }
            ps.setString(6, mod.getNumero_remitente());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            System.err.println("Error el modificar");
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean eliminar(ModeloRemitente mod, frmRemitente frm) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        // int Fila = frm.jtFuncionarios.getSelectedRow();
        String sql = "DELETE FROM t_remitente WHERE numero_remitente=?";
        int result = JOptionPane.showConfirmDialog(null, "Eliminar registro?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.NO_OPTION) {
            //i System.out.println("No chosen");
        }
        if (result == JOptionPane.YES_OPTION) {
            try {
                ps = con.prepareStatement(sql);
                ps.setString(1, mod.getNumero_remitente());
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
                }//catch
            }//finally
        } //fin if
        return false;
    } // fin de metodo eliminar

    public boolean buscarCaracter(ModeloRemitente mod, frmRemitente frm) {

        DefaultTableModel modeloTabla = new DefaultTableModel();
        frm.jtRemitente.setModel(modeloTabla);

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        //String clave = frm.txtSiglas.getText();
        String clave = frm.txtBuscar.getText();
        String where = "";
        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta
            where = "WHERE CONCAT (siglas_remitente, nombre_remitente) LIKE '%" + clave + "%'";
        }

        String sql = "SELECT CONCAT ('( ', siglas_remitente, ' ) - ', nombre_remitente) AS Remitente, "
                + "nombre_tiporemitente, nombre_subtiporemitente FROM t_remitente "
                + "INNER JOIN t_tiporemitente ON t_remitente.id_tiporemitente = t_tiporemitente.id_tiporemitente "
                + "INNER JOIN t_subtiporemitente ON t_remitente.id_subtiporemitente = t_subtiporemitente.id_subtiporemitente "
                + where
                + "order by numero_remitente ASC";
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            //modeloTabla.addColumn("Numero");
            modeloTabla.addColumn("Remitente");
            modeloTabla.addColumn("Tipo remitente");
            modeloTabla.addColumn("Subtipo remitente");
            //int[] anchos = {15, 300, 50, 100};
            int[] anchos = {300, 50, 100};
            for (int i = 0; i < frm.jtRemitente.getColumnCount(); i++) {
                frm.jtRemitente.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Object[] filas = new Object[rsMd.getColumnCount()];
                for (int i = 0; i < rsMd.getColumnCount(); i++) {
                    filas[i] = rs.getObject(i + 1);
                    // System.out.println("Filas (" + filas[i] + ")");
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

    //Carga la tabla pensar si mejor llamarla "cargar tabla"
    public boolean buscar(ModeloRemitente mod, frmRemitente frm) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        frm.jtRemitente.setModel(modeloTabla);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        //String clave = frm.txtSiglas.getText();
        String clave = frm.txtBuscar.getText();
        String where = "";
        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta
            where = "WHERE CONCAT (siglas_remitente, nombre_remitente) LIKE '%" + clave + "%'";
        }

        String sql = "SELECT CONCAT ('( ', siglas_remitente, ' ) - ', nombre_remitente) AS Remitente, "
                + "nombre_tiporemitente, nombre_subtiporemitente FROM t_remitente "
                + "INNER JOIN t_tiporemitente ON t_remitente.id_tiporemitente = t_tiporemitente.id_tiporemitente "
                + "INNER JOIN t_subtiporemitente ON t_remitente.id_subtiporemitente = t_subtiporemitente.id_subtiporemitente "
                + where
                + "order by numero_remitente ASC";
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            //modeloTabla.addColumn("Numero");
            modeloTabla.addColumn("Remitente");
            modeloTabla.addColumn("Tipo remitente");
            modeloTabla.addColumn("Subtipo remitente");
            //int[] anchos = {15, 300, 50, 100};
            int[] anchos = {300, 50, 100};
            for (int i = 0; i < frm.jtRemitente.getColumnCount(); i++) {
                frm.jtRemitente.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Object[] filas = new Object[rsMd.getColumnCount()];
                for (int i = 0; i < rsMd.getColumnCount(); i++) {
                    filas[i] = rs.getObject(i + 1);
                    // System.out.println("Filas (" + filas[i] + ")");
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
    
            public Vector<ModeloSubtipoRemitente> mostrarSubtipoRemitente() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloSubtipoRemitente> datos = new Vector<>();
        ModeloSubtipoRemitente dat = null;
//        String sql = "SELECT id_subtiporemitente, nombre_subtiporemitente FROM t_subtiporemitente";
        String sql = "SELECT id_subtiporemitente, nombre_subtiporemitente FROM t_subtiporemitente ORDER BY id_subtiporemitente";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new ModeloSubtipoRemitente();
            dat.setId(0);
            dat.setNombre("Selecciona subtipo remitente");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloSubtipoRemitente();
                dat.setId(rs.getInt("id_subtiporemitente"));
                dat.setNombre(rs.getString("nombre_subtiporemitente"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        } //
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        //
        return datos;
    }


    public Vector<ModeloTipoRemitente> mostrartipoRemitente() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloTipoRemitente> datos = new Vector<ModeloTipoRemitente>();
        ModeloTipoRemitente dat = null;
        String sql = "SELECT id_tiporemitente, nombre_tiporemitente FROM t_tiporemitente";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new ModeloTipoRemitente();
            dat.setId(0);
            dat.setNombre("Selecciona tipo remitente");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloTipoRemitente();
                dat.setId(rs.getInt("id_tiporemitente"));
                dat.setNombre(rs.getString("nombre_tiporemitente"));
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

    public Vector<ModeloTipoRemitente> mostrarRemitente() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloTipoRemitente> datos = new Vector<ModeloTipoRemitente>();
        ModeloTipoRemitente dat = null;
        String sql = "SELECT numero_remitente, nombre_remitente FROM t_remitente order by numero_remitente asc";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new ModeloTipoRemitente();
            dat.setId(0);
            dat.setNombre("Selecciona remitente");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloTipoRemitente();
                dat.setId(rs.getInt("numero_remitente"));
                dat.setNombre(rs.getString("nombre_remitente"));
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

//------------------------------------------------------------------------------------------
    //--- esta parte se ocupara para el formuario de minuta
    public ArrayList<ModeloRemitenteArray> mostrarArregloRemitente() {
        System.out.println("Entro a mostrar arreglo remitente");
        ArrayList<ModeloRemitenteArray> datos = new ArrayList<>();
        ModeloRemitenteArray dat = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT numero_remitente, CONCAT ('( ', siglas_remitente, ' ) - ', nombre_remitente) AS Remitente FROM t_remitente order by numero_remitente asc";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                dat = new ModeloRemitenteArray();
                dat.setNumero_remitente(rs.getString("numero_remitente"));
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

    public Vector<ModeloSubtipoRemitente> filtrarSubtipoRemitente() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloSubtipoRemitente> datos = new Vector<ModeloSubtipoRemitente>();
        ModeloSubtipoRemitente dat = null;
        String sql = "SELECT id_subtiporemitente, nombre_subtiporemitente FROM t_subtiporemitente";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new ModeloSubtipoRemitente();
            dat.setId(0);
            dat.setNombre("Todos");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloSubtipoRemitente();
                dat.setId(rs.getInt("id_subtiporemitente"));
                dat.setNombre(rs.getString("nombre_subtiporemitente"));
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


}
