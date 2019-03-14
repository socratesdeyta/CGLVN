package Modelo;

import Formato.FormatoTabla;
import Formato.FormatoTablaBitacora;
import Vista.frmActualizarMinutaOficios;
import Vista.frmDestino;
import Vista.frmVerMinuta;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultasVerMinuta extends Conexion {

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
            dat.setNombre(" ");
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

    public Vector<ModeloSeccion> mostrarSeccion() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloSeccion> datos = new Vector<>();
        ModeloSeccion dat = null;
        String sql = "SELECT id_seccion, codigo_seccion, seccion, CONCAT(codigo_seccion, ' ', seccion) As concatenar FROM t_seccion";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new ModeloSeccion();
            dat.setId(0);
            dat.setNombre(" ");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloSeccion();
                dat.setId(rs.getInt("id_seccion"));
                dat.setNombre(rs.getString("concatenar"));
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

    public Vector<Serie> mostrarSerie() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<Serie> datos = new Vector<Serie>();
        Serie dat = null;
        //String sql = "SELECT id_serie, codigo_serie, serie, CONCAT (codigo_serie, ' ', serie) AS concatenar FROM t_serie";
        String sql = "SELECT id_serie, CONCAT (codigo_serie, ' ', serie) AS concatenar FROM t_serie";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new Serie();
            dat.setId(0);
            dat.setNombre(" ");
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
            dat.setNombre(" ");
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
    
        public Vector<TipoDocumento> mostrarTipoDocumento() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<TipoDocumento> datos = new Vector<TipoDocumento>();
        TipoDocumento dat = null;
        String sql = "SELECT id_tipodocumento, nombre_tipodocumento FROM control_gestion.t_tipodocumento";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new TipoDocumento();
            dat.setId(0);
            dat.setNombre(" ");
            datos.add(dat);
            while (rs.next()) {
                dat = new TipoDocumento();
                dat.setId(rs.getInt("id_tipodocumento"));
                // dat.setNombre(rs.getString("serie"));
                dat.setNombre(rs.getString("nombre_tipodocumento"));
                datos.add(dat);
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return datos;
    }

    public Vector<TipoEnvio> mostrarTipoEnvio() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<TipoEnvio> datos = new Vector<TipoEnvio>();
        TipoEnvio dat = null;
        String sql = "SELECT id_tipoenvio, nombre_tipoenvio FROM control_gestion.t_tipoenvio";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            dat = new TipoEnvio();
            dat.setId(0);
            dat.setNombre(" ");
            datos.add(dat);
            while (rs.next()) {
                dat = new TipoEnvio();
                dat.setId(rs.getInt("id_tipoenvio"));
                // dat.setNombre(rs.getString("serie"));
                dat.setNombre(rs.getString("nombre_tipoenvio"));
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
    
        public String mostrarNombreFuncionarioInt(int clave_funcionario) {
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
    

//        public Vector<ModeloTipoDestino> mostrarTipoDestino() {
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Connection con = getConexion();
//        Vector<ModeloTipoDestino> datos = new Vector<>();
//        ModeloTipoDestino dat = null;
//        String sql = "SELECT id_tipodestino, nombre_tipodestino FROM t_tipodestino";
//        try {
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            dat = new ModeloTipoDestino();
//            dat.setId(0);
//            dat.setNombre("Selecciona");
//            datos.add(dat);
//            while (rs.next()) {
//                dat = new ModeloTipoDestino();
//                dat.setId(rs.getInt("id_tipodestino"));
//                dat.setNombre(rs.getString("nombre_tipodestino"));
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
            dat.setNombre(" ");
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

    public boolean Seleccionar(ModeloVerMinuta mod, frmVerMinuta frm) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        try {
            int filaSeleccionada = frm.jtVerMin.getSelectedRow();
            String clave = frm.jtVerMin.getValueAt(filaSeleccionada, 0).toString();
            ps = con.prepareStatement("SELECT numero, fecha_registro, seccion, serie, subserie, "
                    + "destinatario, funcionario, t_tipoenvio.id_tipodocumento, envio, referencia, asunto, fecha_envio, responsable FROM t_documentosenviados "
                    + "LEFT JOIN t_tipoenvio ON t_tipoenvio.id_tipoenvio = t_documentosenviados.envio "
                    + "WHERE numero=?");
            ps.setString(1, clave);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            while (rs.next()) {
                mod.setNumero(rs.getString("numero"));
                mod.setFecha_registro(rs.getDate("fecha_registro"));
                mod.setSeccion(Integer.parseInt(rs.getString("seccion")));
                mod.setSerie(Integer.parseInt(rs.getString("serie")));
                String subserie = rs.getString("subserie");
                if (subserie == null) {
                    mod.setSubserie(Integer.parseInt("0"));
                } else {
                    mod.setSubserie(Integer.parseInt(rs.getString("subserie")));
                    System.out.println("Modelo.ConsultasActualizarMinutaOficios.Seleccionar()---->" + mod.getSubserie());
                }
                mod.setDestinatario(Integer.parseInt(rs.getString("destinatario")));
                mod.setFuncionario(rs.getString("funcionario"));
                mod.setEnvio(Integer.parseInt(rs.getString("envio")));
                mod.setDocumento(Integer.parseInt(rs.getString("t_tipoenvio.id_tipodocumento")));
                mod.setReferencia(rs.getString("referencia"));
                mod.setAsunto(rs.getString("asunto"));
                mod.setFecha_envio(rs.getDate("fecha_envio"));
                mod.setResponsable(rs.getInt("responsable"));

                System.out.println("Modelo.Numero " + mod.getNumero());
                System.out.println("Modelo.Seccion " + mod.getSeccion());
                System.out.println("Modelo.Serie " + mod.getSerie());
                System.out.println("Modelo.Subserie " + mod.getSubserie());
                System.out.println("Modelo.destinatario " + mod.getDestinatario());
                System.out.println("Modelo.funcionario " + mod.getFuncionario());
                System.out.println("Modelo.Documento " + mod.getDocumento());
                System.out.println("Modelo.envio " + mod.getEnvio());
                System.out.println("Modelo.referencia " + mod.getReferencia());
                System.out.println("Modelo.asunto " + mod.getAsunto());
                System.out.println("Modelo.responsable " + mod.getResponsable());
                System.out.println("Modelo.fechaenvio " + mod.getFecha_envio());
                
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
    
    public boolean RegistrarFechaEnvio(ModeloActualizarMinutaOficios mod, Date fechaEnvio, int idFuncionario) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE t_documentosenviados SET fecha_envio=?, responsable=? "
                + "WHERE numero=?";
//        String sql = "UPDATE t_destino SET numero_destino=?, nombre_destino=?, siglas_destino=?, "
//                + "id_tipodestino=?, id_subtipodestino=? WHERE numero_destino=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1, fechaEnvio);
            // mod.setFecha_registro(rs.getDate("fecha_registro"));
             //modMinActNT.setFecha_registro(rs.getDate("fecha_registro"));
            ps.setInt(2, idFuncionario);
            ps.setString(3, mod.getNumero());
            
                        System.err.println("Fecha Envio "+fechaEnvio);
                        System.err.println("Funcionario "+mod.getFuncionario());
                        System.err.println("Numero LVN "+mod.getNumero());
            
            
            //ps.setInt(4, mod.getId_tipodestino());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            System.err.println("Error el Actualizar Minuta Oficios");
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

//        public boolean registrarFechaEnvio(ModeloActualizarMinutaOficios mod) {
//        PreparedStatement ps = null;
//        Connection con = getConexion();
//        String ultimo = UltimoValorAgregado();
//        System.out.println("ultimo " + ultimo);
//        //int sub = subserieAsignada;
//        //String sql = "INSERT INTO t_destino (numero_destino, nombre_destino, siglas_destino, id_tipodestino, id_subtipodestino, numero_funcionario) VALUES"
////        String sql = "INSERT INTO t_destino (numero_destino, nombre_destino, siglas_destino, id_tipodestino, id_subtipodestino, id_funcionario) VALUES"
////                + "(?,?,?,?,?,?) ";
//        String sql = "INSERT INTO t_destino (numero_destino, nombre_destino, siglas_destino, id_tipodestino, id_subtipodestino, id_funcionario) VALUES"
//                + "(?,?,?,?,?,?) ";
//        System.out.println(sql);
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setString(1, ultimo);
//            ps.setString(2, mod.getNombre_destino());
//            ps.setString(3, mod.getSiglas_destino());
//            ps.setInt(4, mod.getId_tipodestino());
//
//            int subtipo = mod.getId_subtipodestino();
//            int total = subtipo + 7;
//            System.out.println("Total " + total);
//
//            int tipo = mod.getId_tipodestino();
//            if (tipo == 2) {
//                ps.setInt(5, total);
//            } else {
//                ps.setInt(5, mod.getId_subtipodestino());
//            }
//            //ps.setString(6, mod.getFuncionario());
//            ps.setInt(6, mod.getFuncionario());
//            ps.execute();
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
//    public boolean registrar(ModeloActualizarMinutaOficios mod) {
//        PreparedStatement ps = null;
//        Connection con = getConexion();
//        String ultimo = UltimoValorAgregado();
//        System.out.println("ultimo " + ultimo);
//        //int sub = subserieAsignada;
//        //String sql = "INSERT INTO t_destino (numero_destino, nombre_destino, siglas_destino, id_tipodestino, id_subtipodestino, numero_funcionario) VALUES"
//        String sql = "INSERT INTO t_destino (numero_destino, nombre_destino, siglas_destino, id_tipodestino, id_subtipodestino, id_funcionario) VALUES"
//                + "(?,?,?,?,?,?) ";
//        System.out.println(sql);
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setString(1, ultimo);
//            ps.setString(2, mod.getNombre_destino());
//            ps.setString(3, mod.getSiglas_destino());
//            ps.setInt(4, mod.getId_tipodestino());
//
//            int subtipo = mod.getId_subtipodestino();
//            int total = subtipo + 7;
//            System.out.println("Total " + total);
//
//            int tipo = mod.getId_tipodestino();
//            if (tipo == 2) {
//                ps.setInt(5, total);
//            } else {
//                ps.setInt(5, mod.getId_subtipodestino());
//            }
//            //ps.setString(6, mod.getFuncionario());
//            ps.setInt(6, mod.getFuncionario());
//            ps.execute();
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

//    public boolean modificar(ModeloActualizarMinutaOficios mod) {
//        PreparedStatement ps = null;
//        Connection con = getConexion();
//        String sql = "UPDATE t_destino SET numero_destino=?, nombre_destino=?, siglas_destino=?, "
//                + "id_tipodestino=?, id_subtipodestino=? WHERE numero_destino=?";
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setString(1, mod.getNumero_destino());
//            ps.setString(2, mod.getNombre_destino());
//            ps.setString(3, mod.getSiglas_destino());
//            ps.setInt(4, mod.getId_tipodestino());
//
//            int subtipo = mod.getId_subtipodestino();
//            int total = subtipo + 7;
//            System.out.println("Total " + total);
//            int tipo = mod.getId_tipodestino();
//            if (tipo == 2) {
//                ps.setInt(5, total);
//            } else {
//                ps.setInt(5, mod.getId_subtipodestino());
//            }
//            ps.setString(6, mod.getNumero_destino());
//            ps.execute();
//            return true;
//        } catch (SQLException e) {
//            System.err.println(e);
//            System.err.println("Error el modificar");
//            return false;
//        } finally {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                System.err.println(e);
//            }
//        }
//    }
//    public boolean eliminar(ModeloActualizarMinutaOficios mod, frmActualizarMinutaOficios frm) {
//        PreparedStatement ps = null;
//        Connection con = getConexion();
//        // int Fila = frm.jtFuncionarios.getSelectedRow();
//        String sql = "DELETE FROM t_destino WHERE numero_destino=?";
//        int result = JOptionPane.showConfirmDialog(null, "Eliminar registro?", "", JOptionPane.YES_NO_OPTION);
//        if (result == JOptionPane.NO_OPTION) {
//            //i System.out.println("No chosen");
//        }
//        if (result == JOptionPane.YES_OPTION) {
//            try {
//                ps = con.prepareStatement(sql);
//                ps.setString(1, mod.getNumero_destino());
//                ps.execute();
//                return true;
//            } catch (SQLException e) {
//                System.err.println(e);
//                return false;
//            } finally {
//                try {
//                    con.close();
//                } catch (SQLException e) {
//                    System.err.println(e);
//                }//catch
//            }//finally
//        } //fin if
//        return false;
//    } // fin de metodo eliminar
    //Funcion que modifica la tabla en relacion a lo que se teclea en buscar
    public boolean buscarCaracter(ModeloVerMinuta mod, frmVerMinuta frm) {

                // Color a las filas de la tabla (gris y blanco
//        FormatoTabla formatoTabla = new FormatoTabla();
//        jtActMinOfi.setDefaultRenderer(Object.class, formatoTabla);

        FormatoTablaBitacora formatoTablaBitacora = new FormatoTablaBitacora();
        frm.jtVerMin.setDefaultRenderer(Object.class, formatoTablaBitacora);

        
        DefaultTableModel modeloTabla = new DefaultTableModel();
        frm.jtVerMin.setModel(modeloTabla);


        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        //String clave = frm.txtBuscar.getText();
        String clave = frm.txtBuscarAsunto.getText();
        //String buscarLVN = "";
        String buscar = "";
        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta 
            // buscarLVN = "AND numero LIKE 'LVN-"+clave+"%'";
            //buscarLVN = "WHERE numero LIKE 'LVN-" + clave + "%'";
            buscar = "WHERE asunto LIKE '%" + clave + "%'";
        }
        String sql = "SELECT numero, fecha_registro, IFNULL(t_subserie.codigo_subserie, t_serie.codigo_serie) As Clasificacion, "
                + "nombre_tipodocumento, nombre_tipoenvio, asunto, fecha_envio "
                //+ "asunto "
                + "FROM control_gestion.t_documentosenviados "
                //+ "LEFT JOIN t_seccion ON t_documentosenviados.seccion=t_seccion.id_seccion "
                + "LEFT JOIN t_serie ON t_documentosenviados.serie=t_serie.id_serie "
                + "LEFT JOIN t_subserie ON t_documentosenviados.subserie=t_subserie.id_subserie "
                + "LEFT JOIN t_destino ON t_documentosenviados.destinatario=t_destino.id_destino "
                + "LEFT JOIN t_funcionarios ON t_documentosenviados.funcionario=t_funcionarios.clave "
                + "LEFT JOIN t_tipoenvio ON t_tipoenvio.id_tipoenvio = t_documentosenviados.envio "
                + "left JOIN t_tipodocumento ON t_tipodocumento.id_tipodocumento = t_tipoenvio.id_tipodocumento "
                // + "WHERE envio > 1 AND fecha_envio IS NULL "
                //+ buscarLVN
                + buscar
                + "order by numero desc;";

        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            modeloTabla.addColumn("Numero");
            modeloTabla.addColumn("Fecha");
            modeloTabla.addColumn("Clasificacion");
            modeloTabla.addColumn("Documento");
            modeloTabla.addColumn("Envio");
            modeloTabla.addColumn("Asunto");

            modeloTabla.addColumn("Fecha Envio");

            int[] anchos = {72, 72, 78, 78, 120, 645, 80};
            
            for (int i = 0; i < frm.jtVerMin.getColumnCount(); i++) {
                frm.jtVerMin.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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

//        try {
//            ps = con.prepareStatement(sql);
//            System.out.println(sql);
//            rs = ps.executeQuery();
//            System.out.println("---------Se ejecuta la consulta");
//            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
//            //modeloTabla.addColumn("Numero");
//            modeloTabla.addColumn("Destinatario");
//            modeloTabla.addColumn("Tipo destino");
//            modeloTabla.addColumn("Subtipo destino");
//            //int[] anchos = {15, 300, 50, 100};
//            int[] anchos = {300, 50, 100};
//            for (int i = 0; i < frm.jtDestino.getColumnCount(); i++) {
//                frm.jtDestino.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
//            }
//            while (rs.next()) {
//                Object[] filas = new Object[rsMd.getColumnCount()];
//                for (int i = 0; i < rsMd.getColumnCount(); i++) {
//                    filas[i] = rs.getObject(i + 1);
//                    // System.out.println("Filas (" + filas[i] + ")");
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
    //Carga la tabla pensar si mejor llamarla "cargar tabla"
//    public boolean buscar(ModeloActualizarMinutaOficios mod, frmActualizarMinutaOficios frm) {
//        DefaultTableModel modeloTabla = new DefaultTableModel();
//        frm.jtActMinOfi.setModel(modeloTabla);
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Connection con = getConexion();
//        //String clave = frm.txtSiglas.getText();
//        String clave = frm.txtBuscar.getText();
//        String where = "";
//        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta
//            //where = "WHERE nombre_destino = '" + clave + "'";
////            where = "WHERE Destinatario LIKE '%" + clave + "%'";
//            //where = "WHERE nombre_destino LIKE '%" + clave + "%'";
//            where = "WHERE CONCAT (siglas_destino, nombre_destino) LIKE '%" + clave + "%'";
//        }
//        //String sql = "SELECT numero_destino, CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario, "
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
//            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
//            //modeloTabla.addColumn("Numero");
//            modeloTabla.addColumn("Destinatario");
//            modeloTabla.addColumn("Tipo destino");
//            modeloTabla.addColumn("Subtipo destino");
//            //int[] anchos = {15, 300, 50, 100};
//            int[] anchos = {300, 50, 100};
//            for (int i = 0; i < frm.jtActMinOfi.getColumnCount(); i++) {
//                frm.jtActMinOfi.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
//            }
//            while (rs.next()) {
//                Object[] filas = new Object[rsMd.getColumnCount()];
//                for (int i = 0; i < rsMd.getColumnCount(); i++) {
//                    filas[i] = rs.getObject(i + 1);
//                    // System.out.println("Filas (" + filas[i] + ")");
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

    public boolean actualizarTabla(ModeloActualizarMinutaOficios mod, frmActualizarMinutaOficios frm) {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        frm.jtActMinOfi.setModel(modeloTabla);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        //String clave = frm.txtSiglas.getText();
        String clave = frm.txtBuscar.getText();
        String where = "";
//        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta
//            //where = "WHERE nombre_destino = '" + clave + "'";
////            where = "WHERE Destinatario LIKE '%" + clave + "%'";
//            //where = "WHERE nombre_destino LIKE '%" + clave + "%'";
//            where = "WHERE CONCAT (siglas_destino, nombre_destino) LIKE '%" + clave + "%'";
//        }
        //String sql = "SELECT numero_destino, CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario, "
//        String sql = "SELECT CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario, "
//                + "nombre_tipodestino, nombre_subtipodestino FROM t_destino "
//                + "INNER JOIN t_tipodestino ON t_destino.id_tipodestino = t_tipodestino.id_tipodestino "
//                + "INNER JOIN t_subtipodestino ON t_destino.id_subtipodestino = t_subtipodestino.id_subtipodestino "
//                + where
//                + "order by numero_destino ASC";


//        String sql = "SELECT numero, fecha_registro, "
//                + "t_destino.nombre_destino, "
//                + "t_tipoenvio.nombre_tipoenvio "
//                //+ "asunto "
//                + "FROM control_gestion.t_documentosenviados "
//                + "LEFT JOIN t_seccion ON t_documentosenviados.seccion=t_seccion.id_seccion "
//                + "LEFT JOIN t_serie ON t_documentosenviados.serie=t_serie.id_serie "
//                + "LEFT JOIN t_subserie ON t_documentosenviados.subserie=t_subserie.id_subserie "
//                + "LEFT JOIN t_destino ON t_documentosenviados.destinatario=t_destino.id_destino "
//                + "LEFT JOIN t_funcionarios ON t_documentosenviados.funcionario=t_funcionarios.clave "
//                + "LEFT JOIN t_tipoenvio ON t_documentosenviados.envio=t_tipoenvio.id_tipoenvio "
//                + "WHERE envio > 1 AND fecha_envio IS NULL "
//                //+ buscarLVN
//                + "order by id_documentosenviados asc;";


        String sql = "SELECT numero, fecha_registro, IFNULL(t_subserie.codigo_subserie, t_serie.codigo_serie) As Clasificacion, "
                + "t_tipoenvio.nombre_tipoenvio, asunto "
                //+ "asunto "
                + "FROM control_gestion.t_documentosenviados "
                //+ "LEFT JOIN t_seccion ON t_documentosenviados.seccion=t_seccion.id_seccion "
                + "LEFT JOIN t_serie ON t_documentosenviados.serie=t_serie.id_serie "
                + "LEFT JOIN t_subserie ON t_documentosenviados.subserie=t_subserie.id_subserie "
                + "LEFT JOIN t_destino ON t_documentosenviados.destinatario=t_destino.id_destino "
                //+ "LEFT JOIN t_funcionarios ON t_documentosenviados.funcionario=t_funcionarios.clave "
                + "LEFT JOIN t_tipoenvio ON t_documentosenviados.envio=t_tipoenvio.id_tipoenvio "
                + "WHERE envio > 1 AND fecha_envio IS NULL "
                //+ buscarLVN
                + "order by numero asc;";

        
        
        
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            modeloTabla.addColumn("Numero");
            modeloTabla.addColumn("Fecha");
            modeloTabla.addColumn("Clasificacion");
            modeloTabla.addColumn("Tipo de envio");
            modeloTabla.addColumn("Asunto");
            //int[] anchos = {15, 300, 50, 100};
            int[] anchos = {72, 72, 76, 122, 600, 72};
            for (int i = 0; i < frm.jtActMinOfi.getColumnCount(); i++) {
                frm.jtActMinOfi.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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
    
    
//     public boolean actualizarTabla(ModeloActualizarMinutaOficios mod, frmActualizarMinutaOficios frm) {
//        DefaultTableModel modeloTabla = new DefaultTableModel();
//        frm.jtActMinOfi.setModel(modeloTabla);
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Connection con = getConexion();
//        //String clave = frm.txtSiglas.getText();
//        String clave = frm.txtBuscar.getText();
//        String where = "";
//        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta
//            //where = "WHERE nombre_destino = '" + clave + "'";
////            where = "WHERE Destinatario LIKE '%" + clave + "%'";
//            //where = "WHERE nombre_destino LIKE '%" + clave + "%'";
//            where = "WHERE CONCAT (siglas_destino, nombre_destino) LIKE '%" + clave + "%'";
//        }
//        //String sql = "SELECT numero_destino, CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario, "
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
//            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
//            //modeloTabla.addColumn("Numero");
//            modeloTabla.addColumn("Destinatario");
//            modeloTabla.addColumn("Tipo destino");
//            modeloTabla.addColumn("Subtipo destino");
//            //int[] anchos = {15, 300, 50, 100};
//            int[] anchos = {300, 50, 100};
//            for (int i = 0; i < frm.jtActMinOfi.getColumnCount(); i++) {
//                frm.jtActMinOfi.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
//            }
//            while (rs.next()) {
//                Object[] filas = new Object[rsMd.getColumnCount()];
//                for (int i = 0; i < rsMd.getColumnCount(); i++) {
//                    filas[i] = rs.getObject(i + 1);
//                    // System.out.println("Filas (" + filas[i] + ")");
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
    
    
    
    
    
    public Vector<ModeloSubtipoDestino> mostrarSubtipoDestino() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloSubtipoDestino> datos = new Vector<>();
        ModeloSubtipoDestino dat = null;
        String sql = "SELECT id_subtipodestino, nombre_subtipodestino FROM t_subtipodestino";
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
//---------------------------------------

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

    public Vector<ModeloDestino> mostrarDestino() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        Vector<ModeloDestino> datos = new Vector<>();
        ModeloDestino dat = null;
        String sql = "SELECT id_destino, nombre_destino, siglas_destino, CONCAT(siglas_destino, ' - ', nombre_destino) As concatenar FROM t_destino";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new ModeloDestino();
            dat.setId_destino(0);
            dat.setNombre_destino("Selecciona destino");
            datos.add(dat);
            while (rs.next()) {
                dat = new ModeloDestino();
                dat.setId_destino(rs.getInt("numero_destino"));
                dat.setNombre_destino(rs.getString("nombre_destino"));
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
    public ArrayList<ModActOfiArray> mostrarArregloDestino() {
        System.out.println("Entro a mostrar arreglo destino");
        ArrayList<ModActOfiArray> datos = new ArrayList<>();
        ModActOfiArray dat = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        //String sql = "SELECT numero_destino, nombre_destino FROM t_destino order by numero_destino asc";
        String sql = "SELECT numero_destino, CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario FROM t_destino order by numero_destino asc";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                dat = new ModActOfiArray();
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
