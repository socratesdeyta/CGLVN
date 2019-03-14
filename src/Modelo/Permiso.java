package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Permiso extends Conexion {
    
    private int id;
    private String permiso;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }
    
    @Override
     public String toString() {
        return this.permiso;
    }

    public Vector<Permiso> mostrarPermiso() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        Vector<Permiso> datos = new Vector<Permiso>();
        Permiso dat = null;

        try {
            String sql = "SELECT id_privilegio, nombre_privilegio FROM t_privilegio";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new Permiso();
            dat.setId(0);
            dat.setPermiso("Selecciona permiso:");
            datos.add(dat);

            while (rs.next()) {
                dat = new Permiso();
                dat.setId(rs.getInt("id_privilegio"));
                dat.setPermiso(rs.getString("nombre_privilegio"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }
}
