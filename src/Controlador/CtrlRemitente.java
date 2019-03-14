package Controlador;

import static Controlador.CtrlHome.frmCorrEnt;
import Modelo.ConsultasCorrespondencia;
import Modelo.ConsultasDestino;
import Modelo.ConsultasRemitente;

import Modelo.ModeloRemitente;
import Modelo.ModeloRemitenteArray;
import Modelo.ModeloTipoRemitente;

import Vista.frmRemitente;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class CtrlRemitente implements ActionListener, MouseListener, KeyListener, FocusListener {

    private final ModeloRemitente mod;
    private final ConsultasRemitente modC;
    private final frmRemitente frm;
    //String ClaveFuncionario;
    int idFuncionario;

    //Color colorFondo = Color.decode("#691A31");
    //Color colorLetra = Color.decode("#D4C19C");
    //Color colorBlanco = Color.decode("#FFFFFF");
    // public CtrlDestino(ModeloDestino modDes, ConsultasDestino modConDes, frmDestino frmDes, String ClaveFuncionario) {
    public CtrlRemitente(ModeloRemitente modRemi, ConsultasRemitente modConRemi, frmRemitente frmRemi, int idFuncionario) {
        this.mod = modRemi;
        this.modC = modConRemi;
        this.frm = frmRemi;
        //this.ClaveFuncionario = ClaveFuncionario;
        this.idFuncionario = idFuncionario;
        //this.modLogin = modLogin;

        //this.frm.
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        //this.frm.btnBuscar.addActionListener(this);
        this.frm.btnNuevo.addActionListener(this);
        this.frm.btnCancelar.addActionListener(this);
        this.frm.jtRemitente.addMouseListener(this);
        this.frm.btnCancelarMod.addActionListener(this);
        this.frm.btnGuardarMod.addActionListener(this);
        this.frm.txtBuscar.addKeyListener(this);  //Para buscar caracter por caracter y modificar la tabla de acuerdo al resultado
        //this.frm.txtSiglas.addKeyListener(this);
        //Focus para que cuando pierda el foco el dato sea MAYUS
        this.frm.txtSiglas.addFocusListener(this);
        //sólo se permite pulsar una fila a la vez.
        this.frm.jtRemitente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void CrearAddActionListenerparaTipoRemitente() {
        if (frm.cbxTipoRemitente.isEnabled()) {
            this.frm.cbxTipoRemitente.addActionListener(this);
        }
    }

    public void iniciar() {
        frm.setTitle("Remitente");
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
        if (e.getSource() == frm.jtRemitente) {
            //frm.cbxSubtipoDestino.setEnabled(false);

            //           deshabilitar();
            //si es 2 menos 7
            if (modC.Seleccionar(mod, frm)) {

                frm.txtNumero.setText(String.valueOf(mod.getNumero_remitente()));
                frm.txtNombre_remitente.setText(String.valueOf(mod.getNombre_remitente()));
                frm.txtSiglas.setText(String.valueOf(mod.getSiglas_remitente()));
                frm.cbxTipoRemitente.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getId_tiporemitente())));
                System.out.println("Aqui es donde marca el error " + mod.getId_subtiporemitente());

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
                ConsultasRemitente ConRemi = new ConsultasRemitente();
                DefaultComboBoxModel modelSubtipo = new DefaultComboBoxModel(ConRemi.mostrarSubtipoRemitente());
                frm.cbxSubtipoRemitente.setModel(modelSubtipo);
                frm.cbxSubtipoRemitente.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getId_subtiporemitente())));

                System.out.println("( " + mod.getId_tiporemitente() + " )" + " Tipo " + frm.cbxTipoRemitente.getSelectedItem()
                        + "/n --- ( " + mod.getId_subtiporemitente() + " )" + " Subtipo " + frm.cbxSubtipoRemitente.getSelectedItem());

                //}
                frm.btnModificar.setVisible(true);
                frm.btnEliminar.setVisible(true);
                frm.btnLimpiar.setVisible(false);
                frm.cbxSubtipoRemitente.setEnabled(false);
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
        if (e.getSource() == frm.cbxTipoRemitente) {
            ModeloTipoRemitente TipoDest = (ModeloTipoRemitente) frm.cbxTipoRemitente.getSelectedItem();
            ConsultasRemitente ConRemi = new ConsultasRemitente();
            //ConsultaMinuta.mostrarSeccion(mod, frm); 

            //Esta de mas
            //ConsultaDestino.mostrarTipoDestino();
            frm.cbxSubtipoRemitente.setEnabled(true);
            DefaultComboBoxModel modelSubtipo = new DefaultComboBoxModel(ConRemi.mostrarSubtipoRemitente(TipoDest.getId()));
            frm.cbxSubtipoRemitente.setModel(modelSubtipo);
            frm.cbxSubtipoRemitente.setEnabled(true);

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
            frm.txtNombre_remitente.requestFocus();
            CrearAddActionListenerparaTipoRemitente();
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
            CrearAddActionListenerparaTipoRemitente();

            System.out.println("Que regresa frm.cbxTipoRemitente.getSelectedItem  ---> " + frm.cbxTipoRemitente.getSelectedItem());
            System.out.println("Que regresa frm.cbxTipoRemitente.getSelectedIndex  ---> " + frm.cbxTipoRemitente.getSelectedIndex());

            ConsultasRemitente ConRemi = new ConsultasRemitente();

            int idSubtipo = mod.getId_subtiporemitente();
            int idTipo = mod.getId_tiporemitente();
            int numeroItems = frm.cbxSubtipoRemitente.countComponents();

            System.out.println("Forma 1 - Numero de  items en combobox  ---> " + numeroItems);
            System.out.println("Forma 2 - Numero de  items en combobox  ---> " + frm.cbxSubtipoRemitente.getItemCount());

            DefaultComboBoxModel modelSubtipo = new DefaultComboBoxModel(ConRemi.mostrarSubtipoRemitente(frm.cbxTipoRemitente.getSelectedIndex()));
            frm.cbxSubtipoRemitente.setEnabled(true);
            frm.cbxSubtipoRemitente.setModel(modelSubtipo);

            //No quiero dejar el 7777777777777777777777777777777
            if (idTipo == 2) {
                frm.cbxSubtipoRemitente.setSelectedIndex(Integer.parseInt(String.valueOf(idSubtipo - 7)));

            } else {

                frm.cbxSubtipoRemitente.setSelectedIndex(Integer.parseInt(String.valueOf(idSubtipo)));
            }

            //--------------------------------
            //Si pudiera cargar el combo solo con los items que le corresponden se podria habilitar la edicion de subtipoDestino
            //Checar si en el boton de modificar se puede cargar el metodo para que solo carge con base en la opcion de tipoDestino
            //el metodo ya esta echo, solo faltaria llamarlo con el parametro () y supongo que cambiar el metodo de solo los items llamadps
            //        frm.cbxSubtipoDestino.setEnabled(true);
        }

        if (e.getSource() == frm.btnGuardarMod) {
            System.out.println("Se presiono el boton Guardar Modificacion");
            mod.setNumero_remitente(frm.txtNumero.getText());
            mod.setNombre_remitente(frm.txtNombre_remitente.getText());
            mod.setSiglas_remitente(frm.txtSiglas.getText());
            mod.setId_tiporemitente(frm.cbxTipoRemitente.getSelectedIndex());
            mod.setId_subtiporemitente(frm.cbxSubtipoRemitente.getSelectedIndex());

            if (mod.getNombre_remitente().equals("") || mod.getSiglas_remitente().equals("")) {
                JOptionPane.showMessageDialog(null, "Hay campos vacios, debe llenar todos los campos");
            } else {
                if (mod.getId_tiporemitente() == 0) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un Tipo de destino");
                } else {
                    if (mod.getId_subtiporemitente() != 0) {

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
                    } else {
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

            mod.setNumero_remitente(frm.txtNumero.getText());
            mod.setNombre_remitente(frm.txtNombre_remitente.getText());
            mod.setSiglas_remitente(frm.txtSiglas.getText());
            mod.setId_tiporemitente(frm.cbxTipoRemitente.getSelectedIndex());
            //int contadorTipo =frm.cbxTipoDestino.getItemCount();
            //  System.out.println("Contador tipo "+contadorTipo);
            //int contadorSubTipo =frm.cbxSubtipoDestino.getItemCount();
            //System.out.println("Contador Subtipo "+contadorSubTipo);
            //Aqui esta el error, guarda el numero de index del combobox y no el id del tipo de envio
            //no funciono ESTO NO ENSE;ARA A NO JUGAR CON LO MODELOS
            //mod.setId_subtipodestino(frm.cbxSubtipoDestino.getSelectedIndex()+contadorTipo);

            //AQUI EL MODELO CON SRE(7) O OTROS (3) ITEMS ASIGNA EL DATO AL MODELO. (QUE NO ES EL MISMO QUE MODSUBTIPODESTINO)
            //PERO CBXTIPODESTINO SIGUE CON EL HACE LOS FILTROS, SE DEBE ASIGNAR EL OTRO, EL QUE TIENE LOS 10 ITEMS
            mod.setId_subtiporemitente(frm.cbxSubtipoRemitente.getSelectedIndex());

            //mod.setFuncionario(ClaveFuncionario);
            mod.setFuncionario(idFuncionario);
            // mod.setFuncionario(String.valueOf(modConMin.mostrarNombreFuncionario(modMin.getFuncionario())));
            //frmMin.lblFuncionario.setText(String.valueOf(modConMin.mostrarNombreFuncionario(modMin.getFuncionario())));

            System.out.println("Numero ->" + mod.getNumero_remitente());
            System.out.println("Nombre ->" + mod.getNombre_remitente());
            System.out.println("Siglas ->" + mod.getSiglas_remitente());
            System.out.println("TipoRemitente ->" + mod.getId_tiporemitente());
            System.out.println("SubTipo Destino ->" + mod.getId_subtiporemitente());
            System.out.println("De donde chingaos saca el numero de funcionario ->" + mod.getFuncionario());

            if (mod.getNombre_remitente().equals("") || mod.getSiglas_remitente().equals("")) {
                JOptionPane.showMessageDialog(null, "Hay campos vacios, debe llenar todos los campos");
            } else {
                if (modC.existeSiglas(mod.getSiglas_remitente()) != 0) {
                    JOptionPane.showMessageDialog(null, "Existe un registro con esas siglas");
                } else {
                    if (modC.existeNombre(mod.getNombre_remitente()) != 0) {
                        JOptionPane.showMessageDialog(null, "Existe un registro con ese nombre");
                    } else {
                        if (mod.getId_tiporemitente() == 0) {
                            JOptionPane.showMessageDialog(null, "Debe seleccionar un Tipo de destino");
                        } else {
                            if (mod.getId_subtiporemitente() != 0) {
                                if (modC.registrar(mod)) {
                                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                                    inicioBotones();
                                    deshabilitar();
                                    
                                    //asignar el valor al formulario Correpondencia
                                    String remitente = "( " + frm.txtSiglas.getText() + " ) - " + frm.txtNombre_remitente.getText()  ;
                                    frmCorrEnt.txtRemitente.setText(remitente);
                                    
                                    //'( ', siglas_remitente, ' ) - ', nombre_remitente
                                    
                                    limpiar();
                                    frm.txtBuscar.setText("");
                                    modC.buscar(mod, frm);

                                    if (CtrlHome.frmCorrEnt != null) {
                                        frm.dispose();
                                        CtrlCorrespondenciaEntrada.frmRemi = null;
                                        
                                        ConsultasCorrespondencia conCorres = new ConsultasCorrespondencia();
                                        
                                        ArrayList<ModeloRemitenteArray> listaRemitente = conCorres.mostrarArregloRemitente();
                                        TextAutoCompleter textautocompleter = new TextAutoCompleter(frmCorrEnt.txtRemitente);
                                        for (int i = 0; i < listaRemitente.size(); i++) {
                                            textautocompleter.addItem(listaRemitente.get(i));
                                            textautocompleter.setMode(0);
                                            System.out.println("Prueba - >" + listaRemitente.get(0));
                                        }
                                    }

                                    //SE CAMBIA EL MODELO PARA QUE MUESTRE TODOS LOS ITEMS
                                    // EL METODO SIN PARAMETROS YA EXISTIA
                                    ConsultasRemitente ConRemi = new ConsultasRemitente();
                                    DefaultComboBoxModel modelSubtipo = new DefaultComboBoxModel(ConRemi.mostrarSubtipoRemitente());
                                    frm.cbxSubtipoRemitente.setModel(modelSubtipo);
                                    //frm.cbxSubtipoDestino.setModel(ModeloDestino);

                                    frm.cbxSubtipoRemitente.setEnabled(false);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                                    limpiar();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Debe seleccionar un Subtipo de remitente");
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
        frm.cbxSubtipoRemitente.setEnabled(false);

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
        frm.txtNombre_remitente.setText(null);
        frm.txtSiglas.setText(null);
        frm.cbxTipoRemitente.setSelectedIndex(0);
        frm.cbxSubtipoRemitente.setSelectedIndex(0);
        //frm.txtBuscar.setText("");
    }

    public void habilitar() {
        frm.txtNumero.setEnabled(false);
        frm.txtNombre_remitente.setEditable(true);
        frm.txtSiglas.setEditable(true);
        frm.cbxTipoRemitente.setEnabled(true);
        //Validar si se puede modificar el subtipo con solo los datos filatrados
        //frm.cbxSubtipoDestino.setEnabled(false);
        frm.jtRemitente.setVisible(false);
        frm.txtBuscar.setVisible(false);
        frm.lblBuscar.setVisible(false);
    }

    public void deshabilitar() {
        frm.txtNumero.setEnabled(true);
        frm.txtNombre_remitente.setEditable(false);
        frm.txtSiglas.setEditable(false);
        frm.cbxTipoRemitente.setEnabled(false);
        frm.cbxSubtipoRemitente.setEnabled(false);
        frm.jtRemitente.setVisible(true);
        frm.txtBuscar.setVisible(true);
        frm.lblBuscar.setVisible(true);
    }
}
