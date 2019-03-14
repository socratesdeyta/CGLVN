package Controlador;

import Modelo.ConsultasDestino;
import Modelo.ModeloDestino;
import Modelo.ModeloTipoDestino;
import Vista.frmDestino;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class CtrlDestino implements ActionListener, MouseListener, KeyListener, FocusListener {

    private final ModeloDestino mod;
    private final ConsultasDestino modC;
    private final frmDestino frm;
    //String ClaveFuncionario;
    int idFuncionario;
    
    //Color colorFondo = Color.decode("#691A31");
    //Color colorLetra = Color.decode("#D4C19C");
    //Color colorBlanco = Color.decode("#FFFFFF");

   // public CtrlDestino(ModeloDestino modDes, ConsultasDestino modConDes, frmDestino frmDes, String ClaveFuncionario) {
     public CtrlDestino(ModeloDestino modDes, ConsultasDestino modConDes, frmDestino frmDes, int idFuncionario) {
        this.mod = modDes;
        this.modC = modConDes;
        this.frm = frmDes;
        //this.ClaveFuncionario = ClaveFuncionario;
        this.idFuncionario = idFuncionario;
        //this.modLogin = modLogin;

        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        //this.frm.btnBuscar.addActionListener(this);
        this.frm.btnNuevo.addActionListener(this);
        this.frm.btnCancelar.addActionListener(this);
        this.frm.jtDestino.addMouseListener(this);
        this.frm.btnCancelarMod.addActionListener(this);
        this.frm.btnGuardarMod.addActionListener(this);
        this.frm.txtBuscar.addKeyListener(this);  //Para buscar caracter por caracter y modificar la tabla de acuerdo al resultado
        //this.frm.txtSiglas.addKeyListener(this);
        //Focus para que cuando pierda el foco el dato sea MAYUS
        this.frm.txtSiglas.addFocusListener(this);
        //sólo se permite pulsar una fila a la vez.
        this.frm.jtDestino.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void CrearAddActionListenerparaTipoDestino() {
        if (frm.cbxTipoDestino.isEnabled()) {
            this.frm.cbxTipoDestino.addActionListener(this);
        }
    }

    public void iniciar() {
        frm.setTitle("Destinatario");
        frm.setLocationRelativeTo(null);
        //frm.getContentPane().setBackground(colorFondo);
//        frm.lblBuscar.setForeground(colorBlanco);
//        frm.lblNombre.setForeground(colorBlanco);
//        frm.lblSiglas.setForeground(colorBlanco);
//        frm.lblTipoDestino.setForeground(colorBlanco);
//        frm.lblSubtipoDestino.setForeground(colorBlanco);
        
        //frm.txtBuscar.setVisible(false);
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == frm.txtSiglas) {
            String mayus = frm.txtSiglas.getText();
            frm.txtSiglas.setText(mayus.toUpperCase());
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == frm.jtDestino) {
            //frm.cbxSubtipoDestino.setEnabled(false);

            //           deshabilitar();
            //si es2 menos 7
            if (modC.Seleccionar(mod, frm)) {

                frm.txtNumero.setText(String.valueOf(mod.getNumero_destino()));
                frm.txtSiglas.setText(String.valueOf(mod.getSiglas_destino()));
                frm.txtNombre_destino.setText(String.valueOf(mod.getNombre_destino()));
                frm.cbxTipoDestino.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getId_tipodestino())));
                System.out.println("Aqui es donde marca el error " + mod.getId_subtipodestino());

//                if (frm.cbxTipoDestino.getSelectedIndex() == 2) {
//                    System.out.println("Se selecciono Otro " + mod.getId_subtipodestino());
//                    frm.cbxSubtipoDestino.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getId_subtipodestino() - 7)));
//                    System.out.println("Tipo " + frm.cbxTipoDestino.getSelectedItem()
//                            + " Subtipo " + frm.cbxSubtipoDestino.getSelectedItem());
//                    System.out.println("Valor asignado a subtipoDestino " + frm.cbxSubtipoDestino.getSelectedItem());
//                } else {
                //System.out.println("Se selecciono SRE " + mod.getId_subtipodestino());
                //frm.cbxSubtipoDestino.removeAllItems();
                //---
                ConsultasDestino ConsultaDestino = new ConsultasDestino();
                DefaultComboBoxModel modelSubtipo = new DefaultComboBoxModel(ConsultaDestino.mostrarSubtipoDestino());
                frm.cbxSubtipoDestino.setModel(modelSubtipo);
                frm.cbxSubtipoDestino.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getId_subtipodestino())));

                System.out.println("( " + mod.getId_tipodestino() + " )" + " Tipo " + frm.cbxTipoDestino.getSelectedItem()
                        + " --- ( " + mod.getId_subtipodestino() + " )" + " Subtipo " + frm.cbxSubtipoDestino.getSelectedItem());

                //}
                frm.btnModificar.setVisible(true);
                frm.btnEliminar.setVisible(true);
                frm.btnLimpiar.setVisible(false);
                frm.cbxSubtipoDestino.setEnabled(false);
                //frm.cbxArea.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getArea())));
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
        //Y SI SOLO SE PARA EL NUMERO DE ITEM DEL COMBOBOX Y ASI YA NO SE LLAMA A LA
        //FUNCION MODELOTIPODESTINO
        if (e.getSource() == frm.cbxTipoDestino) {
            ModeloTipoDestino TipoDest = (ModeloTipoDestino) frm.cbxTipoDestino.getSelectedItem();
            ConsultasDestino ConsultaDestino = new ConsultasDestino();
            //ConsultaMinuta.mostrarSeccion(mod, frm); 

            //Esta de mas
            //ConsultaDestino.mostrarTipoDestino();
            frm.cbxSubtipoDestino.setEnabled(true);
            DefaultComboBoxModel modelSubtipo = new DefaultComboBoxModel(ConsultaDestino.mostrarSubtipoDestino(TipoDest.getId()));
            frm.cbxSubtipoDestino.setModel(modelSubtipo);
            frm.cbxSubtipoDestino.setEnabled(true);

            // EL MODELO QUE MUESTRA EL CBXSUBTIPO SIGUE CON POCOS ITEMS PORQUE SELECCIONA POR SRE(7) U OTRO (3)
            //POR ESO CUANDO SE QUIERE INSERTAR O MOSTRAR EL NUMERO DE ITEM, MARCA EL ERROR'
            //LA SOLUCION PUEDE SER QUE AL DESPUES DE GUARDAR EL CBXSUBTIPODEST TOME OTRO MODELO DONDE SE MUESTR TODOS LOS CAMPOS PARA SUBTIPO
            //VER SI EL MODELO DONDE SE ENCUENTRAN TODOS LOS DATOS (ID, SIGLAS, NOMBRE, TIPO,SUBTIPO) NOS PUEDE SERVIR PARA ESTO
        } // Cierre frmMinSal.cbxSeccion

//        if (e.getSource() == frm.cbxTipoDestino) {
//            ModeloTipoDestino TipoDest = (ModeloTipoDestino) frm.cbxTipoDestino.getSelectedItem();
//            ConsultasDestino ConsultaDestino = new ConsultasDestino();
//            //ConsultaMinuta.mostrarSeccion(mod, frm); 
//            ConsultaDestino.mostrarTipoDestino();
//            DefaultComboBoxModel modelSubtipo = new DefaultComboBoxModel(ConsultaDestino.mostrarSubtipoDestino(TipoDest.getId()));
//            frm.cbxSubtipoDestino.setModel(modelSubtipo);
//        } // Cierre frmMinSal.cbxSeccion
        if (e.getSource() == frm.btnNuevo) {
            limpiar();
            habilitar();
            ocultarBotones();
            frm.txtNombre_destino.requestFocus();
            CrearAddActionListenerparaTipoDestino();
        }

        if (e.getSource() == frm.btnCancelar) {
            JOptionPane.showMessageDialog(null, "Registro Cancelado");
            inicioBotones();
            limpiar();
            deshabilitar();
        }

        if (e.getSource() == frm.btnModificar) {
            habilitar();
            ocultarBotonesMod();
            CrearAddActionListenerparaTipoDestino();

            //ModeloTipoDestino TipoDest = (ModeloTipoDestino) frm.cbxTipoDestino.getSelectedItem();
            System.out.println("Que regresa frm.cbxTipoDestino.getSelectedItem  ---> " + frm.cbxTipoDestino.getSelectedItem());
            System.out.println("Que regresa frm.cbxTipoDestino.getSelectedIndex  ---> " + frm.cbxTipoDestino.getSelectedIndex());
           
            ConsultasDestino ConsultaDestino = new ConsultasDestino();
            //ConsultaMinuta.mostrarSeccion(mod, frm); 
            //ConsultaDestino.mostrarTipoDestino();

            int idSubtipo = mod.getId_subtipodestino();
            int idTipo = mod.getId_tipodestino();
            int numeroItems = frm.cbxSubtipoDestino.countComponents();
            
            System.out.println("Forma 1 - Numero de  items en combobox  ---> " + numeroItems);
            System.out.println("Forma 2 - Numero de  items en combobox  ---> " + frm.cbxSubtipoDestino.getItemCount());

            DefaultComboBoxModel modelSubtipo = new DefaultComboBoxModel(ConsultaDestino.mostrarSubtipoDestino(frm.cbxTipoDestino.getSelectedIndex()));
            frm.cbxSubtipoDestino.setEnabled(true);
            frm.cbxSubtipoDestino.setModel(modelSubtipo);
            
            //No quiero dejar el 7777777777777777777777777777777
            if (idTipo == 2) {
                frm.cbxSubtipoDestino.setSelectedIndex(Integer.parseInt(String.valueOf(idSubtipo - 7)));

            } else {

                frm.cbxSubtipoDestino.setSelectedIndex(Integer.parseInt(String.valueOf(idSubtipo)));
            }

            //--------------------------------
            //Si pudiera cargar el combo solo con los items que le corresponden se podria habilitar la edicion de subtipoDestino
            //Checar si en el boton de modificar se puede cargar el metodo para que solo carge con base en la opcion de tipoDestino
            //el metodo ya esta echo, solo faltaria llamarlo con el parametro () y supongo que cambiar el metodo de solo los items llamadps
            //        frm.cbxSubtipoDestino.setEnabled(true);
        }

        if (e.getSource() == frm.btnGuardarMod) {
            System.out.println("Se presiono el boton Guardar Modificacion");
            mod.setNumero_destino(frm.txtNumero.getText());
            mod.setNombre_destino(frm.txtNombre_destino.getText());
            mod.setSiglas_destino(frm.txtSiglas.getText());
            mod.setId_tipodestino(frm.cbxTipoDestino.getSelectedIndex());
            mod.setId_subtipodestino(frm.cbxSubtipoDestino.getSelectedIndex());

            if (mod.getNombre_destino().equals("") || mod.getSiglas_destino().equals("")) {
                JOptionPane.showMessageDialog(null, "Hay campos vacios, debe llenar todos los campos");
            } else {
                if (mod.getId_tipodestino() == 0) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un Tipo de destino");
                } else {
                    if (mod.getId_subtipodestino() != 0) {

                        if (modC.modificar(mod)) {
                            JOptionPane.showMessageDialog(null, "Registro modificado");
                            inicioBotones();
                            deshabilitar();
                            limpiar();
                            frm.txtBuscar.setText("");
                            modC.buscar(mod, frm);
                            //modConDes.buscar(modDes, frmDes);
                            //PORQUE AQUI NO SE REGRSA AL MODELO SIN PARAMETROS
                            

                        } else {
                            JOptionPane.showMessageDialog(null, "Error al modificar");
                            limpiar();
                        }
                    }else{
                         JOptionPane.showMessageDialog(null, "Debe seleccionar un Subtipo de destino");

                    }
                }
            }
        }

        if (e.getSource() == frm.btnCancelarMod) {
            JOptionPane.showMessageDialog(null, "Modificacion Cancelada");
            inicioBotones();
            limpiar();
            deshabilitar();
        }

        if (e.getSource() == frm.btnEliminar) {
            if (modC.eliminar(mod, frm)) {
                limpiar();
                inicioBotones();
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                modC.buscar(mod, frm);
            } else {
                JOptionPane.showMessageDialog(null, "Eliminar... Cancelado");
                limpiar();
            }

        }

//        if (e.getSource() == frm.btnBuscar) {
//            if (modC.buscar(mod, frm)) {
//                limpiar();
//                inicioBotones();
//            } else {
//                JOptionPane.showMessageDialog(null, "Error al Buscar");
//                limpiar();
//            }
//        }
        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
        }

        if (e.getSource() == frm.btnGuardar) {
            System.out.println("Clave de funcionario antes de ser insertada " + idFuncionario);

            mod.setNumero_destino(frm.txtNumero.getText());
            mod.setNombre_destino(frm.txtNombre_destino.getText());
            mod.setSiglas_destino(frm.txtSiglas.getText());
            mod.setId_tipodestino(frm.cbxTipoDestino.getSelectedIndex());
            //int contadorTipo =frm.cbxTipoDestino.getItemCount();
            //  System.out.println("Contador tipo "+contadorTipo);
            //int contadorSubTipo =frm.cbxSubtipoDestino.getItemCount();
            //System.out.println("Contador Subtipo "+contadorSubTipo);
            //Aqui esta el error, guarda el numero de index del combobox y no el id del tipo de envio
            //no funciono ESTO NO ENSE;ARA A NO JUGAR CON LO MODELOS
            //mod.setId_subtipodestino(frm.cbxSubtipoDestino.getSelectedIndex()+contadorTipo);

            //AQUI EL MODELO CON SRE(7) O OTROS (3) ITEMS ASIGNA EL DATO AL MODELO. (QUE NO ES EL MISMO QUE MODSUBTIPODESTINO)
            //PERO CBXTIPODESTINO SIGUE CON EL HACE LOS FILTROS, SE DEBE ASIGNAR EL OTRO, EL QUE TIENE LOS 10 ITEMS
            mod.setId_subtipodestino(frm.cbxSubtipoDestino.getSelectedIndex());

            //mod.setFuncionario(ClaveFuncionario);
            mod.setFuncionario(idFuncionario);
            // mod.setFuncionario(String.valueOf(modConMin.mostrarNombreFuncionario(modMin.getFuncionario())));
            //frmMin.lblFuncionario.setText(String.valueOf(modConMin.mostrarNombreFuncionario(modMin.getFuncionario())));

            System.out.println("Numero ->" + mod.getNumero_destino());
            System.out.println("Nombre ->" + mod.getNombre_destino());
            System.out.println("Siglas ->" + mod.getSiglas_destino());
            System.out.println("TipoDestino ->" + mod.getId_tipodestino());
            System.out.println("SubTipo Destino ->" + mod.getId_subtipodestino());
            System.out.println("De donde chingaos saca el numero de funcionario ->" + mod.getFuncionario());

            if (mod.getNombre_destino().equals("") || mod.getSiglas_destino().equals("")) {
                JOptionPane.showMessageDialog(null, "Hay campos vacios, debe llenar todos los campos");
            } else {
                if (modC.existeSiglas(mod.getSiglas_destino()) != 0) {
                    JOptionPane.showMessageDialog(null, "Existe un registro con esas siglas");
                } else {
                    if (modC.existeNombre(mod.getNombre_destino()) != 0) {
                        JOptionPane.showMessageDialog(null, "Existe un registro con ese nombre");
                    } else {
                        if (mod.getId_tipodestino() == 0) {
                            JOptionPane.showMessageDialog(null, "Debe seleccionar un Tipo de destino");
                        } else {
                            if (mod.getId_subtipodestino() != 0) {
                                if (modC.registrar(mod)) {
                                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                                    inicioBotones();
                                    deshabilitar();
                                    limpiar();
                                    frm.txtBuscar.setText("");
                                    modC.buscar(mod, frm);
                                    //SE CAMBIA EL MODELO PARA QUE MUESTRE TODOS LOS ITEMS

                                    // EL METODO SIN PARAMETROS YA EXISTIA
                                    ConsultasDestino ConsultaDestino = new ConsultasDestino();
                                    DefaultComboBoxModel modelSubtipo = new DefaultComboBoxModel(ConsultaDestino.mostrarSubtipoDestino());
                                    frm.cbxSubtipoDestino.setModel(modelSubtipo);
                                    //frm.cbxSubtipoDestino.setModel(ModeloDestino);

                                    frm.cbxSubtipoDestino.setEnabled(false);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                                    limpiar();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Debe seleccionar un Subtipo de destino");
                            }

                        } //else valida tipo destino
                    } //else valida campos Nombre
                } //else valida siglas
            } //if Valida campos vacios-
        }//if boton guardar -
    } // Cierre actionPerformed
    // }

    @Override
    public void keyPressed(KeyEvent e
    ) {

    }

    @Override
    public void keyReleased(KeyEvent e
    ) {
//Modificara la tabla de acuerdo al caracter que se muestre en txtbuscar
        if (e.getSource() == frm.txtBuscar) {
            if (modC.buscarCaracter(mod, frm)) {
                limpiar();
                inicioBotones();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Buscar");
                limpiar();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e
    ) {
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
        frm.txtBuscar.setVisible(true);
        frm.lblBuscar.setVisible(true);
        frm.cbxSubtipoDestino.setEnabled(false);

    }

    public void ocultarBotones() {
        frm.btnGuardar.setVisible(true);
        frm.btnCancelar.setVisible(true);
        frm.btnNuevo.setVisible(false);
        frm.btnModificar.setVisible(false);
        frm.btnEliminar.setVisible(false);
        frm.btnLimpiar.setVisible(true);
        frm.txtBuscar.setVisible(false);
        frm.lblBuscar.setVisible(false);
        //  frm.btnBuscar.setVisible(false);
    }

    public void ocultarBotonesMod() {
        frm.btnGuardarMod.setVisible(true);
        frm.btnCancelarMod.setVisible(true);
        frm.btnNuevo.setVisible(false);
        frm.btnModificar.setVisible(false);
        frm.btnEliminar.setVisible(false);
        frm.btnLimpiar.setVisible(false);
        // frm.btnBuscar.setVisible(false);
    }

    public void mostrarBotonesMod() {
        frm.btnGuardarMod.setVisible(false);
        frm.btnCancelarMod.setVisible(false);
        frm.btnNuevo.setVisible(true);
        frm.btnModificar.setVisible(true);
        frm.btnEliminar.setVisible(true);
        frm.btnLimpiar.setVisible(true);
        //frm.btnBuscar.setVisible(true);
    }

    public void mostrarBotones() {
        frm.btnGuardar.setVisible(false);
        frm.btnCancelar.setVisible(false);
        frm.btnNuevo.setVisible(true);
        frm.btnModificar.setVisible(true);
        frm.btnEliminar.setVisible(true);
        frm.btnLimpiar.setVisible(true);
        //frm.btnBuscar.setVisible(true);

    }

    public void limpiar() {
        frm.txtNumero.setText(null);
        frm.txtNombre_destino.setText(null);
        frm.txtSiglas.setText(null);
        frm.cbxTipoDestino.setSelectedIndex(0);
        frm.cbxSubtipoDestino.setSelectedIndex(0);
        //frm.txtBuscar.setText("");
    }

    public void habilitar() {
        frm.txtNumero.setEnabled(false);
        frm.txtNombre_destino.setEditable(true);
        frm.txtSiglas.setEditable(true);
        frm.cbxTipoDestino.setEnabled(true);
        //Validar si se puede modificar el subtipo con solo los datos filatrados
        //frm.cbxSubtipoDestino.setEnabled(false);
        frm.jtDestino.setVisible(false);
        frm.txtBuscar.setVisible(false);
        frm.lblBuscar.setVisible(false);
    }

    public void deshabilitar() {
        frm.txtNumero.setEnabled(true);
        frm.txtNombre_destino.setEditable(false);
        frm.txtSiglas.setEditable(false);
        frm.cbxTipoDestino.setEnabled(false);
        frm.cbxSubtipoDestino.setEnabled(false);
        frm.jtDestino.setVisible(true);
        frm.txtBuscar.setVisible(true);
        frm.lblBuscar.setVisible(true);
    }
}
