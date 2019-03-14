package Controlador;

import Modelo.ConsultasFuncionarios;
import Modelo.Funcionario;
import Vista.frmFuncionario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
//import javax.swing.table.DefaultTableModel;

public class CtrlFuncionario implements ActionListener, MouseListener {

    private final Funcionario mod;
    private final ConsultasFuncionarios modC;
    private final frmFuncionario frm;
    //Color colorFondo = Color.decode("#691A31");
    Color colorLetra = Color.decode("#DEC9A3");
    Color colorBlanco = Color.decode("#FFFFFF");
    //private DefaultTableModel modeloTabla;

    public CtrlFuncionario(Funcionario mod, ConsultasFuncionarios modC, frmFuncionario frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnNuevo.addActionListener(this);
        this.frm.btnCancelar.addActionListener(this);
        this.frm.jtFuncionarios.addMouseListener(this);
        this.frm.btnCancelarMod.addActionListener(this);
        this.frm.btnGuardarMod.addActionListener(this);

        //sólo se permite pulsar una fila a la vez.
        this.frm.jtFuncionarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void iniciar() {
        frm.setTitle("Funcionario");
        frm.setLocationRelativeTo(null);
        frm.txtId.setVisible(false);
//        frm.getContentPane().setBackground(colorFondo);

//        frm.lblClave.setForeground(colorBlanco);
//        frm.lblNombre.setForeground(colorBlanco);
//        frm.lblPaterno.setForeground(colorBlanco);
//        frm.lblMaterno.setForeground(colorBlanco);
//        frm.lblCorreo.setForeground(colorBlanco);
//        frm.lblCargo.setForeground(colorBlanco);
//        frm.lblArea.setForeground(colorBlanco);
//        frm.lblUsuario.setForeground(colorBlanco);
//        frm.lblPassword.setForeground(colorBlanco);
//        frm.lblPermiso.setForeground(colorBlanco);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == frm.jtFuncionarios) {

            deshabilitar();

            if (modC.Seleccionar(mod, frm)) {

                frm.txtClave.setText(String.valueOf(mod.getClave()));
                frm.txtNombre.setText(String.valueOf(mod.getNombre()));
                frm.txtPaterno.setText(String.valueOf(mod.getPaterno()));
                frm.txtMaterno.setText(String.valueOf(mod.getMaterno()));
                frm.txtCorreo.setText(String.valueOf(mod.getCorreo()));
                frm.cbxCargo.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getCargo())));
                frm.cbxArea.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getArea())));
                frm.txtUsuario.setText(String.valueOf(mod.getUsuario()));
                frm.txtPassword.setText(String.valueOf(mod.getPassword()));
                frm.cbxPermiso.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getPermiso())));
                frm.btnModificar.setVisible(true);
                frm.btnEliminar.setVisible(true);
                frm.btnLimpiar.setVisible(false);

                //mostrarBotones();
                //JOptionPane.showMessageDialog(null, "Registro Seleccionado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al Seleccionar");
                limpiar();
            }
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frm.btnNuevo) {
            limpiar();
            habilitar();
            ocultarBotones();
            frm.txtNombre.requestFocus();
        }

        if (e.getSource() == frm.btnGuardar) {

            //nombre, apellidop, apellidom, mail_f, id_cargo, id_area, usuario_f, password_f, id_privilegio
            mod.setClave(frm.txtClave.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setPaterno(frm.txtPaterno.getText());
            mod.setMaterno(frm.txtMaterno.getText());
            mod.setCorreo(frm.txtCorreo.getText());
            mod.setCargo(frm.cbxCargo.getSelectedIndex());
            mod.setArea(frm.cbxArea.getSelectedIndex());
            mod.setUsuario(frm.txtUsuario.getText());
            mod.setPassword(frm.txtPassword.getText());
            mod.setPermiso(frm.cbxPermiso.getSelectedIndex());

            if (mod.getNombre().equals("") || mod.getPaterno().equals("") || mod.getCorreo().equals("")
                    || mod.getUsuario().equals("") || mod.getPassword().equals("")) {
                JOptionPane.showMessageDialog(null, "Hay campos vacios, debe llenar todos los campos");
            } else {
                if (mod.getCargo() == 0) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un Cargo");
                } else {
                    if (mod.getArea() == 0) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar un Area");
                    } else {
                        if (mod.getPermiso() == 0) {
                            JOptionPane.showMessageDialog(null, "Debe seleccionar un Permiso");
                        } else {
                            // if (modC.existeCargo(mod.getCargo()) == 0) {
                            if (modC.esMail(mod.getCorreo())) {

                                if (modC.registrar(mod)) {
                                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                                    inicioBotones();
                                    deshabilitar();
                                    limpiar();
                                    modC.buscar(mod, frm);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                                    limpiar();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Correo no valido");
                            }
                            // } else {
                            //    JOptionPane.showMessageDialog(null, "El usuario ya existe");
                            //ExisteCargo
                            //  } //else valida Cargo ya registrado
                        } //else valida campos Permiso
                    } //else valida campos Area
                } //else valida campos Cargo
            } //if Valida campos vacios    2
        }//if boton guardar    1

        if (e.getSource() == frm.btnCancelar) {
            JOptionPane.showMessageDialog(null, "Registro Cancelado");
            inicioBotones();
            limpiar();
            deshabilitar();
            //modC.buscar(mod, frm);

            //Se ocupa para no guarde la clave al darle cancelar, ya que si cancela y despues le da buscar sin datos muestra el ultimo valor que se trabajo
            // mod.setClave(frm.txtClave.getText());
        }

        if (e.getSource() == frm.btnModificar) {
            habilitar();
            System.out.println("se llamo a habilitar");
            ocultarBotonesMod();
            System.out.println("se ocultaron los botones");
        }

        if (e.getSource() == frm.btnGuardarMod) {
            System.out.println("Se presiono el boton Guardar Modificacion");
            mod.setClave(frm.txtClave.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setPaterno(frm.txtPaterno.getText());
            mod.setMaterno(frm.txtMaterno.getText());
            mod.setCorreo(frm.txtCorreo.getText());
            mod.setCargo(frm.cbxCargo.getSelectedIndex());
            mod.setArea(frm.cbxArea.getSelectedIndex());
            mod.setUsuario(frm.txtUsuario.getText());
            mod.setPassword(frm.txtPassword.getText());
            mod.setPermiso(frm.cbxPermiso.getSelectedIndex());

            if (modC.modificar(mod)) {
                //mostrarBotonesMod();
                deshabilitar();
                limpiar();
                inicioBotones();
                modC.buscar(mod, frm);

                JOptionPane.showMessageDialog(null, "Registro modificado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar");
                limpiar();
            }

        }

        if (e.getSource() == frm.btnCancelarMod) {
            JOptionPane.showMessageDialog(null, "Modificacion Cancelada");
            inicioBotones();
            limpiar();
            deshabilitar();

        }

        if (e.getSource() == frm.btnEliminar) {
            //mod.setId(Integer.parseInt(frm.txtId.getText()));

            if (modC.eliminar(mod, frm)) {
                limpiar();
                inicioBotones();
                modC.buscar(mod, frm);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Eliminar... Cancelado");
                limpiar();
            }

        }

        if (e.getSource() == frm.btnBuscar) {
            if (modC.buscar(mod, frm)) {

                // JOptionPane.showMessageDialog(null, "Busca realizada");
                limpiar();
                inicioBotones();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Buscar");
                limpiar();
            }
        }

        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
        }
    }

    public void inicioBotones() {
        frm.btnGuardar.setVisible(false);
        frm.btnCancelar.setVisible(false);
        frm.btnGuardarMod.setVisible(false);
        frm.btnCancelarMod.setVisible(false);
        frm.btnNuevo.setVisible(true);
        frm.btnModificar.setVisible(false);
        frm.btnEliminar.setVisible(false);
        frm.btnLimpiar.setVisible(false);
        frm.btnBuscar.setVisible(true);
    }

    public void ocultarBotones() {
        frm.btnGuardar.setVisible(true);
        frm.btnCancelar.setVisible(true);
        frm.btnNuevo.setVisible(false);
        frm.btnModificar.setVisible(false);
        frm.btnEliminar.setVisible(false);
        frm.btnLimpiar.setVisible(true);
        frm.btnBuscar.setVisible(false);
    }

    public void ocultarBotonesMod() {
        frm.btnGuardarMod.setVisible(true);
        frm.btnCancelarMod.setVisible(true);
        frm.btnNuevo.setVisible(false);
        frm.btnModificar.setVisible(false);
        frm.btnEliminar.setVisible(false);
        frm.btnLimpiar.setVisible(false);
        frm.btnBuscar.setVisible(false);
    }

    public void mostrarBotonesMod() {
        frm.btnGuardarMod.setVisible(false);
        frm.btnCancelarMod.setVisible(false);
        frm.btnNuevo.setVisible(true);
        frm.btnModificar.setVisible(true);
        frm.btnEliminar.setVisible(true);
        frm.btnLimpiar.setVisible(true);
        frm.btnBuscar.setVisible(true);
    }

    public void mostrarBotones() {
        frm.btnGuardar.setVisible(false);
        frm.btnCancelar.setVisible(false);
        frm.btnNuevo.setVisible(true);
        frm.btnModificar.setVisible(true);
        frm.btnEliminar.setVisible(true);
        frm.btnLimpiar.setVisible(true);
        frm.btnBuscar.setVisible(true);

    }

    public void limpiar() {
        frm.txtId.setText(null);
        frm.txtClave.setText(null);
        frm.txtNombre.setText(null);
        frm.txtPaterno.setText(null);
        frm.txtMaterno.setText(null);
        frm.txtCorreo.setText(null);
        frm.cbxCargo.setSelectedIndex(0);
        frm.cbxArea.setSelectedIndex(0);
        frm.txtUsuario.setText(null);
        frm.txtPassword.setText(null);
        frm.cbxPermiso.setSelectedIndex(0);
    }

    public void habilitar() {
        frm.txtClave.setEnabled(false);
        frm.txtId.setEditable(true);
        frm.txtClave.setEditable(true);
        frm.txtNombre.setEditable(true);
        frm.txtPaterno.setEditable(true);
        frm.txtMaterno.setEditable(true);
        frm.txtCorreo.setEditable(true);
        frm.cbxCargo.setEnabled(true);
        frm.cbxArea.setEnabled(true);
        frm.txtUsuario.setEditable(true);
        frm.txtPassword.setEditable(true);
        frm.cbxPermiso.setEnabled(true);
        frm.jtFuncionarios.setVisible(false);
    }

    public void deshabilitar() {
        frm.txtClave.setEnabled(true);
        frm.txtId.setEditable(false);
        //frm.txtClave.setEditable(false);
        frm.txtNombre.setEditable(false);
        frm.txtPaterno.setEditable(false);
        frm.txtMaterno.setEditable(false);
        frm.txtCorreo.setEditable(false);
        frm.cbxCargo.setEnabled(false);
        frm.cbxArea.setEnabled(false);
        frm.txtUsuario.setEditable(false);
        frm.txtPassword.setEditable(false);
        frm.cbxPermiso.setEnabled(false);
        frm.jtFuncionarios.setVisible(true);
    }

}
