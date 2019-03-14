package Modelo;

import Vista.frmDestino;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultasDestino extends Conexion {

    public Vector<ModeloTipoDestino> mostrarTipoDestino() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloTipoDestino> datos = new Vector<>();
        ModeloTipoDestino dat = null;
        String sql = "SELECT id_tipodestino, nombre_tipodestino FROM t_tipodestino";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new ModeloTipoDestino();
            dat.setId(0);
            dat.setNombre("Selecciona");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloTipoDestino();
                dat.setId(rs.getInt("id_tipodestino"));
                dat.setNombre(rs.getString("nombre_tipodestino"));
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
    public Vector<ModeloSubtipoDestino> mostrarSubtipoDestino(int idTipoDestino) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloSubtipoDestino> datos = new Vector<>();
        ModeloSubtipoDestino dat = null;
        String sql = "SELECT id_subtipodestino, nombre_subtipodestino FROM t_subtipodestino WHERE id_tipodestino =" + idTipoDestino;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new ModeloSubtipoDestino();
            dat.setId(0);
            dat.setNombre("Selecciona");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloSubtipoDestino();
                dat.setId(rs.getInt("id_subtipodestino"));
                dat.setNombre(rs.getString("nombre_subtipodestino"));
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

    public boolean Seleccionar(ModeloDestino mod, frmDestino frm) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        try {
            int filaSeleccionada = frm.jtDestino.getSelectedRow();
            String clave = frm.jtDestino.getValueAt(filaSeleccionada, 0).toString();
//            ps = con.prepareStatement("SELECT numero_destino, siglas_destino, nombre_destino, id_tipodestino, id_subtipodestino "
//                    + "FROM t_destino WHERE numero_destino=?");
            ps = con.prepareStatement("SELECT numero_destino, siglas_destino, nombre_destino, id_tipodestino, id_subtipodestino "
                    + "FROM t_destino WHERE CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) =?");

            ps.setString(1, clave);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            while (rs.next()) {
                //fun.setId(Integer.parseInt(rs.getString("id_funcionario")));
                mod.setNumero_destino(rs.getString("numero_destino"));
                mod.setSiglas_destino(rs.getString("siglas_destino"));
                mod.setNombre_destino(rs.getString("nombre_destino"));
                mod.setId_tipodestino(Integer.parseInt(rs.getString("id_tipodestino")));
                mod.setId_subtipodestino(Integer.parseInt(rs.getString("id_subtipodestino")));
            }

            System.out.println("Numero Destino-> " + mod.getNumero_destino());
            System.out.println("Siglas> " + mod.getSiglas_destino());
            System.out.println("Nombre--> " + mod.getNombre_destino());
            System.out.println("Tipo--> " + mod.getId_tipodestino());
            System.out.println("Subtipo--> " + mod.getId_subtipodestino());

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

    public boolean registrar(ModeloDestino mod) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String ultimo = UltimoValorAgregado();
        System.out.println("ultimo " + ultimo);
        //int sub = subserieAsignada;
        //String sql = "INSERT INTO t_destino (numero_destino, nombre_destino, siglas_destino, id_tipodestino, id_subtipodestino, numero_funcionario) VALUES"
        String sql = "INSERT INTO t_destino (numero_destino, nombre_destino, siglas_destino, id_tipodestino, id_subtipodestino, id_funcionario) VALUES"
                + "(?,?,?,?,?,?) ";
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ultimo);
            ps.setString(2, mod.getNombre_destino());
            ps.setString(3, mod.getSiglas_destino());
            ps.setInt(4, mod.getId_tipodestino());
            
            int subtipo = mod.getId_subtipodestino();
            int total = subtipo + 7;
            System.out.println("Total "+ total);
            
            int tipo = mod.getId_tipodestino();
            if (tipo == 2) {
                ps.setInt(5, total);
            } else {
                ps.setInt(5, mod.getId_subtipodestino());
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

    public int existeSiglas(String SiglasDestino) {
        System.out.println("El valor que se pasa si llego bien? " + SiglasDestino);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        //String sql = "SELECT siglas_destino FROM t_destino WHERE siglas_destino != 'OIC'";
        String sql = "SELECT siglas_destino FROM t_destino WHERE siglas_destino = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, SiglasDestino);
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

    public int existeNombre(String NombreDestino) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        String sql = "SELECT nombre_destino FROM t_destino WHERE nombre_destino = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, NombreDestino);
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
        String sql = "SELECT last_insert_id(numero_destino)+1 as Ultimo FROM t_destino order by numero_destino desc limit 1";
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
            System.out.println("Modelo.ConsultasDestino.UltimoValorAgregado()" + ultimoValorAgregado);
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

    public boolean modificar(ModeloDestino mod) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE t_destino SET numero_destino=?, nombre_destino=?, siglas_destino=?, "
                + "id_tipodestino=?, id_subtipodestino=? WHERE numero_destino=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, mod.getNumero_destino());
            ps.setString(2, mod.getNombre_destino());
            ps.setString(3, mod.getSiglas_destino());
            ps.setInt(4, mod.getId_tipodestino());
            
            int subtipo = mod.getId_subtipodestino();
            int total = subtipo + 7;
            System.out.println("Total "+ total);
            int tipo = mod.getId_tipodestino();
            if (tipo == 2) {
                ps.setInt(5, total);
            } else {
                ps.setInt(5, mod.getId_subtipodestino());
            }
            ps.setString(6, mod.getNumero_destino());
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

    public boolean eliminar(ModeloDestino mod, frmDestino frm) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        // int Fila = frm.jtFuncionarios.getSelectedRow();
        String sql = "DELETE FROM t_destino WHERE numero_destino=?";
        int result = JOptionPane.showConfirmDialog(null, "Eliminar registro?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.NO_OPTION) {
            //i System.out.println("No chosen");
        }
        if (result == JOptionPane.YES_OPTION) {
            try {
                ps = con.prepareStatement(sql);
                ps.setString(1, mod.getNumero_destino());
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

    public boolean buscarCaracter(ModeloDestino mod, frmDestino frm) {

        DefaultTableModel modeloTabla = new DefaultTableModel();
        frm.jtDestino.setModel(modeloTabla);

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        //String clave = frm.txtSiglas.getText();
        String clave = frm.txtBuscar.getText();
        String where = "";
        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta
            where = "WHERE CONCAT (siglas_destino, nombre_destino) LIKE '%" + clave + "%'";
        }
        //String sql = "SELECT numero_destino, CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario, "
        String sql = "SELECT CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario, "
                + "nombre_tipodestino, nombre_subtipodestino FROM t_destino "
                + "INNER JOIN t_tipodestino ON t_destino.id_tipodestino = t_tipodestino.id_tipodestino "
                + "INNER JOIN t_subtipodestino ON t_destino.id_subtipodestino = t_subtipodestino.id_subtipodestino "
                + where
                + "order by numero_destino ASC";
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            //modeloTabla.addColumn("Numero");
            modeloTabla.addColumn("Destinatario");
            modeloTabla.addColumn("Tipo destino");
            modeloTabla.addColumn("Subtipo destino");
            //int[] anchos = {15, 300, 50, 100};
            int[] anchos = {300, 50, 100};
            for (int i = 0; i < frm.jtDestino.getColumnCount(); i++) {
                frm.jtDestino.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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
    public boolean buscar(ModeloDestino mod, frmDestino frm) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        frm.jtDestino.setModel(modeloTabla);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        //String clave = frm.txtSiglas.getText();
        String clave = frm.txtBuscar.getText();
        String where = "";
        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta
            //where = "WHERE nombre_destino = '" + clave + "'";
//            where = "WHERE Destinatario LIKE '%" + clave + "%'";
            //where = "WHERE nombre_destino LIKE '%" + clave + "%'";
            where = "WHERE CONCAT (siglas_destino, nombre_destino) LIKE '%" + clave + "%'";
        }
        //String sql = "SELECT numero_destino, CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario, "
        String sql = "SELECT CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario, "
                + "nombre_tipodestino, nombre_subtipodestino FROM t_destino "
                + "INNER JOIN t_tipodestino ON t_destino.id_tipodestino = t_tipodestino.id_tipodestino "
                + "INNER JOIN t_subtipodestino ON t_destino.id_subtipodestino = t_subtipodestino.id_subtipodestino "
                + where
                + "order by numero_destino ASC";
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            //modeloTabla.addColumn("Numero");
            modeloTabla.addColumn("Destinatario");
            modeloTabla.addColumn("Tipo destino");
            modeloTabla.addColumn("Subtipo destino");
            //int[] anchos = {15, 300, 50, 100};
            int[] anchos = {300, 50, 100};
            for (int i = 0; i < frm.jtDestino.getColumnCount(); i++) {
                frm.jtDestino.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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

    public Vector<ModeloSubtipoDestino> mostrarSubtipoDestino() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloSubtipoDestino> datos = new Vector<>();
        ModeloSubtipoDestino dat = null;
        String sql = "SELECT id_subtipodestino, nombre_subtipodestino FROM t_subtipodestino ORDER BY id_subtipodestino";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new ModeloSubtipoDestino();
            dat.setId(0);
            dat.setNombre("Selecciona subtipo destino");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloSubtipoDestino();
                dat.setId(rs.getInt("id_subtipodestino"));
                dat.setNombre(rs.getString("nombre_subtipodestino"));
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

    public Vector<ModeloTipoDestino> mostrartipoDestino() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloTipoDestino> datos = new Vector<ModeloTipoDestino>();
        ModeloTipoDestino dat = null;
        String sql = "SELECT id_tipodestino, nombre_tipodestino FROM t_tipodestino";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new ModeloTipoDestino();
            dat.setId(0);
            dat.setNombre("Selecciona tipo destino");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloTipoDestino();
                dat.setId(rs.getInt("id_tipodestino"));
                dat.setNombre(rs.getString("nombre_tipodestino"));
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

    public Vector<ModeloTipoDestino> mostrarDestino() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloTipoDestino> datos = new Vector<ModeloTipoDestino>();
        ModeloTipoDestino dat = null;
        String sql = "SELECT numero_destino, nombre_destino FROM t_destino order by numero_destino asc";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new ModeloTipoDestino();
            dat.setId(0);
            dat.setNombre("Selecciona destino");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloTipoDestino();
                dat.setId(rs.getInt("numero_destino"));
                dat.setNombre(rs.getString("nombre_destino"));
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

    public Vector<ModeloSubtipoDestino> filtrarSubtipoDestino() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloSubtipoDestino> datos = new Vector<ModeloSubtipoDestino>();
        ModeloSubtipoDestino dat = null;
        String sql = "SELECT id_subtipodestino, nombre_subtipodestino FROM t_subtipodestino";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new ModeloSubtipoDestino();
            dat.setId(0);
            dat.setNombre("Todos");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloSubtipoDestino();
                dat.setId(rs.getInt("id_subtipodestino"));
                dat.setNombre(rs.getString("nombre_subtipodestino"));
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

//    public boolean filtrar(ModeloDestino modDes, frmDestino frmDes) {
//        DefaultTableModel modeloTabla = new DefaultTableModel();
//        frmDes.jtDestino.setModel(modeloTabla);
//
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Connection con = getConexion();
//        int clave = frmDes.cbxFiltrar.getSelectedIndex();
//        String where = "";
//        if (clave != 0) { //Si no es null asigna el numero de campo a la consulta
//            where = "WHERE id_subtipodestino = " + clave;
//        }
//        String sql = "SELECT numero_destino, siglas_destino, nombre_destino, id_tipodestino, id_subtipodestino "
//                + "FROM t_destino "
//                + where
//                + " order by nombre_destino ASC";
//        System.out.println(sql);
//        try {
//            ps = con.prepareStatement(sql);
//            System.out.println(sql);
//            rs = ps.executeQuery();
//            System.out.println("---------Se ejecuta la consulta");
//            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
//            // modeloTabla.addColumn("id_funcionario");
//            modeloTabla.addColumn("numero_destino");
//            modeloTabla.addColumn("siglas_destino");
//            modeloTabla.addColumn("nombre_destino");
//            int[] anchos = {10, 70, 300};
//            for (int i = 0; i < frmDes.jtDestino.getColumnCount(); i++) {
//                frmDes.jtDestino.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
//            }
//            while (rs.next()) {
//
//                Object[] filas = new Object[rsMd.getColumnCount()];
//                for (int i = 0; i < rsMd.getColumnCount(); i++) {
//                    filas[i] = rs.getObject(i + 1);
//                    System.out.println("Filas (" + filas[i] + ")");
//                }
//                modeloTabla.addRow(filas);
//            }
//            return true;
//        } catch (SQLException e) {
//            System.err.println(e);
//            return false;
//        } finally {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                System.err.println(e);
//            }
//        }
//    }
}
