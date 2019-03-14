package Modelo;

import Vista.frmFuncionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultasFuncionarios extends Conexion {

    public boolean esMail(String correo) {
        //patron para validar el correo
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\"
                + ".[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(correo);
        return mather.find();
    }

    public int existeCargo(int cargo) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT count (id) FROM t_funcionarios WHERE id_cargo = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cargo);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    public boolean Seleccionar(Funcionario fun, frmFuncionario frm) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        try {
            int filaSeleccionada = frm.jtFuncionarios.getSelectedRow();
            String clave = frm.jtFuncionarios.getValueAt(filaSeleccionada, 0).toString();
            ps = con.prepareStatement("SELECT clave, nombre, apellidop, apellidom, mail_f, id_cargo, id_area, usuario_f, password_f, id_privilegio FROM t_funcionarios WHERE clave=?");
            ps.setString(1, clave);

            rs = ps.executeQuery();

            System.out.println("---------Se ejecuta la consulta");

            while (rs.next()) {
                //fun.setId(Integer.parseInt(rs.getString("id_funcionario")));
                fun.setClave(rs.getString("clave"));
                fun.setNombre(rs.getString("nombre"));
                fun.setPaterno(rs.getString("apellidop"));
                fun.setMaterno(rs.getString("apellidom"));
                fun.setCorreo(rs.getString("mail_f"));
                fun.setCargo(Integer.parseInt(rs.getString("id_cargo")));
                fun.setArea(Integer.parseInt(rs.getString("id_area")));
                fun.setUsuario(rs.getString("usuario_f"));
                fun.setPassword(rs.getString("password_f"));
                fun.setPermiso(Integer.parseInt(rs.getString("id_privilegio")));
            }

            System.out.println("Nombre-->" + fun.getClave());

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

    public boolean registrar(Funcionario fun) {

        PreparedStatement ps = null;
        Connection con = getConexion();

        String ultimo = UltimoValorAgregado();

        String sql = "INSERT INTO t_funcionarios (clave, nombre, apellidop, apellidom, "
                + "mail_f, id_cargo, id_area, usuario_f, password_f, id_privilegio)VALUES "
                + "(?,?,?,?,?,?,?,?,?,?)";

        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ultimo);
            ps.setString(2, fun.getNombre());
            ps.setString(3, fun.getPaterno());
            ps.setString(4, fun.getMaterno());
            ps.setString(5, fun.getCorreo());
            ps.setInt(6, fun.getCargo());
            ps.setInt(7, fun.getArea());
            ps.setString(8, fun.getUsuario());
            ps.setString(9, fun.getPassword());
            ps.setInt(10, fun.getPermiso());
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

    public boolean modificar(Funcionario fun) {

        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE t_funcionarios SET clave=?, nombre=?, apellidop=?, apellidom=?, "
                + "mail_f=?, id_cargo=?, id_area=?, usuario_f=?, password_f=?, id_privilegio=? WHERE "
                + "clave=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, fun.getClave());
            ps.setString(2, fun.getNombre());
            ps.setString(3, fun.getPaterno());
            ps.setString(4, fun.getMaterno());
            ps.setString(5, fun.getCorreo());
            ps.setInt(6, fun.getCargo());
            ps.setInt(7, fun.getArea());
            ps.setString(8, fun.getUsuario());
            ps.setString(9, fun.getPassword());
            ps.setInt(10, fun.getPermiso());
            ps.setString(11, fun.getClave());
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

    public boolean eliminar(Funcionario fun, frmFuncionario frm) {

        PreparedStatement ps = null;
        Connection con = getConexion();

        // int Fila = frm.jtFuncionarios.getSelectedRow();
        String sql = "DELETE FROM t_funcionarios WHERE clave=?";

        int result = JOptionPane.showConfirmDialog(null, "Eliminar registro?", "", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.NO_OPTION) {
            //i System.out.println("No chosen");
        }
        if (result == JOptionPane.YES_OPTION) {
            System.out.println("Picked YES");
            try {
                ps = con.prepareStatement(sql);
                ps.setString(1, fun.getClave());
                ps.execute();
                // modeloTabla.removeRow(Fila);
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

    public String UltimoValorAgregado() {
        Connection con = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int contador;
        String ultimoValorAgregado = "";

        String sql = "SELECT last_insert_id(clave)+1 as Ultimo FROM t_funcionarios order by clave desc limit 1";
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                contador = (Integer.parseInt(rs.getString("Ultimo")));
                if (contador < 10) {
                    ultimoValorAgregado = "0" + contador;
                } else if (contador < 100) {
                    ultimoValorAgregado = "" + contador;
                }
            }
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

    public String UltimoValor() {
        Connection con = getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int contador;
        String ultimoValor = "";

        String sql = "SELECT (COUNT(*)+1) as Ultimo FROM t_funcionarios";
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                contador = (Integer.parseInt(rs.getString("Ultimo")));
                if (contador < 10) {
                    ultimoValor = "LVN-00" + contador;
                } else if (contador < 100) {
                    ultimoValor = "LVN-0" + contador;
                } else if (contador < 1000) {
                    ultimoValor = "LVN-" + contador;
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

    public boolean buscar(Funcionario fun, frmFuncionario frm) {

        DefaultTableModel modeloTabla = new DefaultTableModel();
        frm.jtFuncionarios.setModel(modeloTabla);

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String clave = frm.txtClave.getText();
        String where = "";
        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta
            where = "WHERE clave = '" + clave + "'";
        }

        String sql = "SELECT clave, CONCAT(nombre, ' ', apellidop, ' ', apellidom) As Nombre, "
                + "mail_f, nombre_cargo, nombre_area, usuario_f, password_f, nombre_privilegio FROM control_gestion.t_funcionarios "
                + "INNER JOIN t_cargo ON t_funcionarios.id_cargo=t_cargo.id_cargo "
                + "INNER JOIN t_area ON t_funcionarios.id_area=t_area.id_area "
                + "INNER JOIN t_privilegio ON t_funcionarios.id_privilegio=t_privilegio.id_privilegio "
                + where
                + "order by clave ASC";
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();

            System.out.println("---------Se ejecuta la consulta");

            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();

            // modeloTabla.addColumn("id_funcionario");
            modeloTabla.addColumn("clave");
            modeloTabla.addColumn("Nombre");
            //modeloTabla.addColumn("apellidop");
            //modeloTabla.addColumn("apellidom");
            modeloTabla.addColumn("mail_f");
            modeloTabla.addColumn("nombre_cargo");
            //modeloTabla.addColumn("id_cargo");
            modeloTabla.addColumn("nombre_area");
            //modeloTabla.addColumn("id_area");
            modeloTabla.addColumn("usuario_f");
            modeloTabla.addColumn("password_f");
            modeloTabla.addColumn("nombre_privilegio");
            //modeloTabla.addColumn("id_privilegio");

            int[] anchos = {70, 300, 250, 350, 350, 200, 150, 150,};

            for (int i = 0; i < frm.jtFuncionarios.getColumnCount(); i++) {
                frm.jtFuncionarios.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }

            while (rs.next()) {

                Object[] filas = new Object[rsMd.getColumnCount()];
                for (int i = 0; i < rsMd.getColumnCount(); i++) {
                    filas[i] = rs.getObject(i + 1);
                    System.out.println("Filas (" + filas[i] + ")");
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
}//Cierra la clase
