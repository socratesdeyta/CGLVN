package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultasLogin extends Conexion {

    public boolean entrar(ModeloLogin modConsultasLogin) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

//        String sql = "SELECT clave, CONCAT(nombre, ' ', apellidop, ' ', apellidom) As Nombre, "
//                + "usuario_f, password_f, nombre_privilegio, t_funcionarios.id_privilegio FROM control_gestion.t_funcionarios "
//                + "INNER JOIN t_privilegio ON t_funcionarios.id_privilegio=t_privilegio.id_privilegio "
//                + "WHERE usuario_f = ?";
        String sql = "SELECT id_funcionarios, clave, CONCAT(nombre, ' ', apellidop, ' ', apellidom) As Nombre, "
                + "usuario_f, password_f, nombre_privilegio, t_funcionarios.id_privilegio FROM control_gestion.t_funcionarios "
                + "INNER JOIN t_privilegio ON t_funcionarios.id_privilegio=t_privilegio.id_privilegio "
                + "WHERE usuario_f = ?";

        System.out.println(sql);

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, modConsultasLogin.getUsuario());
            System.out.println("Dato enviado " + modConsultasLogin.getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {
//                if (modConsultasLogin.getPassword().equals(rs.getString(4))) {
//                    modConsultasLogin.setClave(rs.getString(1));
//                    modConsultasLogin.setNombreCompleto(rs.getString(2));
//                    modConsultasLogin.setUsuario(rs.getString(3));
//                    modConsultasLogin.setNombrePermiso(rs.getString(5));
//                    modConsultasLogin.setPermiso(rs.getInt(6));
               if (modConsultasLogin.getPassword().equals(rs.getString(5))) {
                    modConsultasLogin.setId(rs.getInt(1));
                    modConsultasLogin.setClave(rs.getString(2));
                    modConsultasLogin.setNombreCompleto(rs.getString(3));
                    modConsultasLogin.setUsuario(rs.getString(4));
                    modConsultasLogin.setNombrePermiso(rs.getString(6));
                    modConsultasLogin.setPermiso(rs.getInt(7));

                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasLogin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}//Cierra la clase
