package Modelo;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Cargo extends Conexion {

    private int id;
    private String cargo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return this.cargo;
    }

    public Vector<Cargo> mostrarCargo() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        Vector<Cargo> datos = new Vector<Cargo>();
        Cargo dat = null;

        try {
            String sql = "SELECT id_cargo, nombre_cargo FROM t_cargo";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new Cargo();
            dat.setId(0);
            dat.setCargo("Selecciona cargo:");
            datos.add(dat);

            while (rs.next()) {
                dat = new Cargo();
                dat.setId(rs.getInt("id_cargo"));
                dat.setCargo(rs.getString("nombre_cargo"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }
}
