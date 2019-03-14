package Modelo;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Area extends Conexion {
    private int id;
    private String area;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
 public String toString() {
        return this.area;
    }

    public Vector<Area> mostrarArea() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        Vector<Area> datos = new Vector<Area>();
        Area dat = null;

        try {
            String sql = "SELECT id_area, nombre_area FROM t_area";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new Area();
            dat.setId(0);
            dat.setArea("Selecciona área:");
            datos.add(dat);

            while (rs.next()) {
                dat = new Area();
                dat.setId(rs.getInt("id_area"));
                dat.setArea(rs.getString("nombre_area"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }
}
