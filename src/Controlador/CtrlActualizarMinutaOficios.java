package Controlador;

import Modelo.ConsultasActualizarMinutaOficios;
import Modelo.ConsultasDestino;
import Modelo.ConsultasMinuta;
import Modelo.ModeloActualizar;
import Modelo.ModeloActualizarMinutaOficios;
import Modelo.ModeloDestino;
import Modelo.ModeloTipoDestino;
import Vista.frmActualizarMinutaOficios;
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
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
//import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

//public class CtrlActualizarMinutaOficios implements ActionListener, MouseListener {
public class CtrlActualizarMinutaOficios implements ActionListener, MouseListener, KeyListener {

    private final ModeloActualizarMinutaOficios mod;
    private final ConsultasActualizarMinutaOficios modC;
    private final frmActualizarMinutaOficios frm;
    //String ClaveFuncionario;
    int idFuncionario;
    Date fechaEnvio;

    //Date FechaAlDia;
    //Color colorFondo = Color.decode("#691A31");
    //Color colorLetra = Color.decode("#D4C19C");
    //Color colorBlanco = Color.decode("#FFFFFF");
    // public CtrlDestino(ModeloDestino modDes, ConsultasDestino modConDes, frmDestino frmDes, String ClaveFuncionario) {
    public CtrlActualizarMinutaOficios(ModeloActualizarMinutaOficios modActOfi, ConsultasActualizarMinutaOficios modConActOfi, frmActualizarMinutaOficios frmActOfi, int idFuncionario) {
        this.mod = modActOfi;
        this.modC = modConActOfi;
        this.frm = frmActOfi;
        //this.ClaveFuncionario = ClaveFuncionario;
        this.idFuncionario = idFuncionario;
        //this.fechaEnvio = frm.jdcFechaEnvio.getDate();
        //this.modLogin = modLogin;

        this.frm.btnAsignarFecha.addActionListener(this);

//        this.frm.btnGuardar.addActionListener(this);
//        this.frm.btnModificar.addActionListener(this);
//        this.frm.btnEliminar.addActionListener(this);
//        this.frm.btnLimpiar.addActionListener(this);
        //this.frm.btnBuscar.addActionListener(this);
//        this.frm.btnNuevo.addActionListener(this);
//        this.frm.btnCancelar.addActionListener(this);
        this.frm.jtActMinOfi.addMouseListener(this);
//        this.frm.btnCancelarMod.addActionListener(this);
//        this.frm.btnGuardarMod.addActionListener(this);
        this.frm.txtBuscar.addKeyListener(this);  //Para buscar caracter por caracter y modificar la tabla de acuerdo al resultado
        //this.frm.txtSiglas.addKeyListener(this);
        //Focus para que cuando pierda el foco el dato sea MAYUS
        //this.frm.txtAsunto.addFocusListener(this);
        //sólo se permite pulsar una fila a la vez.
        this.frm.jtActMinOfi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ConsultasActualizarMinutaOficios ConActOfi = new ConsultasActualizarMinutaOficios();
        //sConsultasActualizarMinutaOficio ConActOfi = new ConsultasActualizarMinutaOficio();

        DefaultComboBoxModel modelDestino = new DefaultComboBoxModel(ConActOfi.mostrarDestinatario());
        frm.cbxDestinatario.setModel(modelDestino);

        DefaultComboBoxModel modelSeccion = new DefaultComboBoxModel(ConActOfi.mostrarSeccion());
        frm.cbxSeccion.setModel(modelSeccion);

        DefaultComboBoxModel modelSerie = new DefaultComboBoxModel(ConActOfi.mostrarSerie());
        frm.cbxSerie.setModel(modelSerie);

        DefaultComboBoxModel modelSubserie = new DefaultComboBoxModel(ConActOfi.mostrarSubserie());
        frm.cbxSubserie.setModel(modelSubserie);

        DefaultComboBoxModel modelTipoEnvio = new DefaultComboBoxModel(ConActOfi.mostrarTipoEnvio());
        frm.cbxTipoEnvio.setModel(modelTipoEnvio);

        //frm.jdcFechaRegistro.setDate(modActOfi.getFecha_registro());
//        DefaultComboBoxModel modelFuncionario = new DefaultComboBoxModel(ConActOfi.mostrarNombreFuncionario(clave_funcionario));
//        frm.cbxSerie.setModel(modelSerie);
//        DefaultComboBoxModel modelEnvio = new DefaultComboBoxModel(ConActOfi.mostrarEnvio());
//        frm.cbxSerie.setModel(modelSerie);
    }

//    public void CrearAddActionListenerparaTipoDestino() {
//        if (frm.cbxSubtipoDestino.isEnabled()) {
//            this.frm.cbxSubtipoDestino.addActionListener(this);
//        }
//    }
    public void iniciar() {
        frm.setTitle("Actualizar Minuta Oficios");
        frm.setLocationRelativeTo(null);
//        frm.getContentPane().setBackground(colorFondo);
//        frm.lblBuscar.setForeground(colorBlanco);
//        frm.lblNombre.setForeground(colorBlanco);
//        frm.lblSiglas.setForeground(colorBlanco);
//        frm.lblTipoDestino.setForeground(colorBlanco);
//        frm.lblSubtipoDestino.setForeground(colorBlanco);

        //frm.txtBuscar.setVisible(false);
    }

//    @Override
//    public void focusLost(FocusEvent e) {
//        if (e.getSource() == frm.txtAsunto) {
//            String mayus = frm.txtAsunto.getText();
//            frm.txtAsunto.setText(mayus.toUpperCase());
//        }
//    }
//    @Override
//    public void focusGained(FocusEvent e) {
//    }
    // esta funcion agrega a los combobox los datos de la fila seleccionada
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == frm.jtActMinOfi) {

            //Limpia txtBuscar
            frm.txtBuscar.setText("");

            // data fecha envio, NO visible
            frm.jdcFechaEnvio.setVisible(true);
            // boton data fecha envio, NO visible
            frm.btnAsignarFecha.setVisible(true);

            //frm.cbxSubtipoDestino.setEnabled(false);
            //           deshabilitar();
            //si es2 menos 7
            if (modC.Seleccionar(mod, frm)) {

                frm.txtNumero.setText(String.valueOf(mod.getNumero()));
                frm.txtFecha.setText(String.valueOf(mod.getFecha_registro()));
                //frm.jdcFechaRegistro.setDate(mod.getFecha_registro());
                frm.cbxDestinatario.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getDestinatario())));
                frm.cbxSeccion.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getSeccion())));
                frm.cbxSerie.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getSerie())));
                frm.cbxSubserie.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getSubserie())));
                frm.txtFuncionario.setText(String.valueOf(modC.mostrarNombreFuncionario(mod.getFuncionario())));
                frm.cbxTipoEnvio.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getEnvio())));
                frm.txtAsunto2.setText(String.valueOf(mod.getAsunto()));

            } else {
                JOptionPane.showMessageDialog(null, "Error al Seleccionar");
                //     limpiar();
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

        if (e.getSource() == frm.btnAsignarFecha) {

            //        Fecha de registro
            java.util.Date date = frm.jdcFechaEnvio.getDate();
            long d = date.getTime();
            java.sql.Date fecha = new java.sql.Date(d);
            fechaEnvio = fecha;
            System.out.println("fecha de envio -->" + fechaEnvio);
            
            if (mod.getNumero().equals("")) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado algun LVN");
            } else if ((frm.jdcFechaEnvio.getDate() == null) || (fechaEnvio == null)) {
                JOptionPane.showMessageDialog(null, "El campo Fecha de envio se encuentra vacio");
            }
            if (modC.RegistrarFechaEnvio(mod, fechaEnvio, idFuncionario)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                frm.txtBuscar.setText("");
                modC.actualizarTabla(mod, frm);
                frm.jdcFechaEnvio.setVisible(false);
                frm.btnAsignarFecha.setVisible(false);

                limpiar();

            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                //limpiar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un Subtipo de destino");
        }

        if (e.getSource() == frm.txtBuscar) {
            if (modC.buscarCaracter(mod, frm)) {
                //  limpiar();
//                inicioBotones();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Buscar");
                //   limpiar();
            }
        }

        if (e.getSource() == frm.jdcFechaEnvio) {
            //        Fecha de registro
            java.util.Date date = frm.jdcFechaEnvio.getDate();
            long d = date.getTime();
            java.sql.Date fecha = new java.sql.Date(d);
            fechaEnvio = fecha;
            System.out.println("fecha de envio -->" + fechaEnvio);
        }

    }

    public void limpiar() {

        frm.txtFuncionario.setText(null);
        //frm.txtNombre_destino.setText(null);
        frm.txtNumero.setText(null);
        frm.txtFecha.setText(null);
        frm.cbxDestinatario.setSelectedIndex(0);
        frm.cbxSeccion.setSelectedIndex(0);
        frm.cbxSerie.setSelectedIndex(0);
        frm.cbxSubserie.setSelectedIndex(0);
        frm.txtFuncionario.setText(null);
        frm.cbxTipoEnvio.setSelectedIndex(0);
        //frm.txtAsunto.setText("");
        frm.txtAsunto2.setText(null);
        //frm.txtBuscar.setText("");
    }

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
                //inicioBotones();
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

} //else valida tipo destino
//} //else valida campos Nombre
//} //else valida siglas
//            } //if Valida campos vacios-
//        }//if boton guardar -

//Y SI SOLO SE PARA EL NUMERO DE ITEM DEL COMBOBOX Y ASI YA NO SE LLAMA A LA
//FUNCION MODELOTIPODESTINO
//        if (e.getSource() == frm.cbxSubtipoDestino) {
//            ModeloTipoDestino TipoDest = (ModeloTipoDestino) frm.cbxSubtipoDestino.getSelectedItem();
//            ConsultasDestino ConsultaDestino = new ConsultasDestino();
//            //ConsultaMinuta.mostrarSeccion(mod, frm); 
//
//            //Esta de mas
//            //ConsultaDestino.mostrarTipoDestino();
//            frm.cbxSubtipoDestino.setEnabled(true);
//            DefaultComboBoxModel modelSubtipo = new DefaultComboBoxModel(ConsultaDestino.mostrarSubtipoDestino(TipoDest.getId()));
//            frm.cbxSubtipoDestino.setModel(modelSubtipo);
//            frm.cbxSubtipoDestino.setEnabled(true);
// EL MODELO QUE MUESTRA EL CBXSUBTIPO SIGUE CON POCOS ITEMS PORQUE SELECCIONA POR SRE(7) U OTRO (3)
//POR ESO CUANDO SE QUIERE INSERTAR O MOSTRAR EL NUMERO DE ITEM, MARCA EL ERROR'
//LA SOLUCION PUEDE SER QUE AL DESPUES DE GUARDAR EL CBXSUBTIPODEST TOME OTRO MODELO DONDE SE MUESTR TODOS LOS CAMPOS PARA SUBTIPO
//VER SI EL MODELO DONDE SE ENCUENTRAN TODOS LOS DATOS (ID, SIGLAS, NOMBRE, TIPO,SUBTIPO) NOS PUEDE SERVIR PARA ESTO
//       } // Cierre frmMinSal.cbxSeccion
//        if (e.getSource() == frm.cbxTipoDestino) {
//            ModeloTipoDestino TipoDest = (ModeloTipoDestino) frm.cbxTipoDestino.getSelectedItem();
//            ConsultasDestino ConsultaDestino = new ConsultasDestino();
//            //ConsultaMinuta.mostrarSeccion(mod, frm); 
//            ConsultaDestino.mostrarTipoDestino();
//            DefaultComboBoxModel modelSubtipo = new DefaultComboBoxModel(ConsultaDestino.mostrarSubtipoDestino(TipoDest.getId()));
//            frm.cbxSubtipoDestino.setModel(modelSubtipo);
//        } // Cierre frmMinSal.cbxSeccion
//        if (e.getSource() == frm.btnNuevo) {
//          //  limpiar();
//            habilitar();
//            ocultarBotones();
////            frm.txtNombre_destino.requestFocus();
//            CrearAddActionListenerparaTipoDestino();
//        }
//        if (e.getSource() == frm.btnCancelar) {
//            JOptionPane.showMessageDialog(null, "Registro Cancelado");
//            inicioBotones();
//         //   limpiar();
//            deshabilitar();
//        }
//        if (e.getSource() == frm.btnModificar) {
//            habilitar();
//            ocultarBotonesMod();
//            CrearAddActionListenerparaTipoDestino();
//
//            //ModeloTipoDestino TipoDest = (ModeloTipoDestino) frm.cbxTipoDestino.getSelectedItem();
//            System.out.println("Que regresa frm.cbxTipoDestino.getSelectedItem  ---> " + frm.cbxSubtipoDestino.getSelectedItem());
//            System.out.println("Que regresa frm.cbxTipoDestino.getSelectedIndex  ---> " + frm.cbxSubtipoDestino.getSelectedIndex());
//
//            ConsultasDestino ConsultaDestino = new ConsultasDestino();
//            //ConsultaMinuta.mostrarSeccion(mod, frm); 
//            //ConsultaDestino.mostrarTipoDestino();
//
////            int idSubtipo = mod.getId_subtipodestino();
////            int idTipo = mod.getId_tipodestino();
//            int numeroItems = frm.cbxSubtipoDestino.countComponents();
//
//            System.out.println("Forma 1 - Numero de  items en combobox  ---> " + numeroItems);
//            System.out.println("Forma 2 - Numero de  items en combobox  ---> " + frm.cbxSubtipoDestino.getItemCount());
//
//            DefaultComboBoxModel modelSubtipo = new DefaultComboBoxModel(ConsultaDestino.mostrarSubtipoDestino(frm.cbxSubtipoDestino.getSelectedIndex()));
//            frm.cbxSubtipoDestino.setEnabled(true);
//            frm.cbxSubtipoDestino.setModel(modelSubtipo);
//
//            //No quiero dejar el 7777777777777777777777777777777
////            if (idTipo == 2) {
////                frm.cbxSubtipoDestino.setSelectedIndex(Integer.parseInt(String.valueOf(idSubtipo - 7)));
////
////            } else {
////
////                frm.cbxSubtipoDestino.setSelectedIndex(Integer.parseInt(String.valueOf(idSubtipo)));
////            }
//
//            //--------------------------------
//            //Si pudiera cargar el combo solo con los items que le corresponden se podria habilitar la edicion de subtipoDestino
//            //Checar si en el boton de modificar se puede cargar el metodo para que solo carge con base en la opcion de tipoDestino
//            //el metodo ya esta echo, solo faltaria llamarlo con el parametro () y supongo que cambiar el metodo de solo los items llamadps
//            //        frm.cbxSubtipoDestino.setEnabled(true);
//        }
//        if (e.getSource() == frm.btnGuardarMod) {
//            System.out.println("Se presiono el boton Guardar Modificacion");
//            mod.setNumero_destino(frm.txtFuncionario.getText());
//
//            //mod.setNombre_destino(frm.txtNombre_destino.getText());
//            mod.setSiglas_destino(frm.txtAsunto.getText());
//            mod.setId_tipodestino(frm.cbxSubtipoDestino.getSelectedIndex());
//            mod.setId_subtipodestino(frm.cbxSubtipoDestino.getSelectedIndex());
//
//            if (mod.getNombre_destino().equals("") || mod.getSiglas_destino().equals("")) {
//                JOptionPane.showMessageDialog(null, "Hay campos vacios, debe llenar todos los campos");
//            } else {
//                if (mod.getId_tipodestino() == 0) {
//                    JOptionPane.showMessageDialog(null, "Debe seleccionar un Tipo de destino");
//                } else {
//                    if (mod.getId_subtipodestino() != 0) {
//
//                        if (modC.modificar(mod)) {
//                            JOptionPane.showMessageDialog(null, "Registro modificado");
//                            inicioBotones();
//                            deshabilitar();
//                            limpiar();
//                            frm.txtBuscar.setText("");
//                            modC.buscar(mod, frm);
//                            //modConDes.buscar(modDes, frmDes);
//                            //PORQUE AQUI NO SE REGRSA AL MODELO SIN PARAMETROS
//
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Error al modificar");
//                            limpiar();
//                        }
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Debe seleccionar un Subtipo de destino");
//
//                    }
//                }
//            }
//        }
//        if (e.getSource() == frm.btnCancelarMod) {
//            JOptionPane.showMessageDialog(null, "Modificacion Cancelada");
//            inicioBotones();
// //           limpiar();
//            deshabilitar();
//        }
//        if (e.getSource() == frm.btnEliminar) {
//            if (modC.eliminar(mod, frm)) {
////                limpiar();
//                inicioBotones();
//                JOptionPane.showMessageDialog(null, "Registro eliminado");
//                modC.buscar(mod, frm);
//            } else {
//                JOptionPane.showMessageDialog(null, "Eliminar... Cancelado");
// //               limpiar();
//            }
//
//        }
//        if (e.getSource() == frm.btnBuscar) {
//            if (modC.buscar(mod, frm)) {
//                limpiar();
//                inicioBotones();
//            } else {
//                JOptionPane.showMessageDialog(null, "Error al Buscar");
//                limpiar();
//            }
//        }
//        if (e.getSource() == frm.btnLimpiar) {
// //           limpiar();
//        }
//        if (e.getSource() == frm.btnGuardar) {
//            System.out.println("Clave de funcionario antes de ser insertada " + idFuncionario);
//
////            mod.setNumero_destino(frm.txtFuncionario.getText());
////
////            //mod.setNombre_destino(frm.txtNombre_destino.getText());
////            mod.setSiglas_destino(frm.txtAsunto.getText());
////            mod.setId_tipodestino(frm.cbxSubtipoDestino.getSelectedIndex());
//            //int contadorTipo =frm.cbxTipoDestino.getItemCount();
//            //  System.out.println("Contador tipo "+contadorTipo);
//            //int contadorSubTipo =frm.cbxSubtipoDestino.getItemCount();
//            //System.out.println("Contador Subtipo "+contadorSubTipo);
//            //Aqui esta el error, guarda el numero de index del combobox y no el id del tipo de envio
//            //no funciono ESTO NO ENSE;ARA A NO JUGAR CON LO MODELOS
//            //mod.setId_subtipodestino(frm.cbxSubtipoDestino.getSelectedIndex()+contadorTipo);
//
//            //AQUI EL MODELO CON SRE(7) O OTROS (3) ITEMS ASIGNA EL DATO AL MODELO. (QUE NO ES EL MISMO QUE MODSUBTIPODESTINO)
//            //PERO CBXTIPODESTINO SIGUE CON EL HACE LOS FILTROS, SE DEBE ASIGNAR EL OTRO, EL QUE TIENE LOS 10 ITEMS
////            mod.setId_subtipodestino(frm.cbxSubtipoDestino.getSelectedIndex());
//
//            //mod.setFuncionario(ClaveFuncionario);
////            mod.setFuncionario(idFuncionario);
//            // mod.setFuncionario(String.valueOf(modConMin.mostrarNombreFuncionario(modMin.getFuncionario())));
//            //frmMin.lblFuncionario.setText(String.valueOf(modConMin.mostrarNombreFuncionario(modMin.getFuncionario())));
//
//            System.out.println("Numero ->" + mod.getNumero_destino());
//            System.out.println("Nombre ->" + mod.getNombre_destino());
//            System.out.println("Siglas ->" + mod.getSiglas_destino());
//            System.out.println("TipoDestino ->" + mod.getId_tipodestino());
//            System.out.println("SubTipo Destino ->" + mod.getId_subtipodestino());
//            System.out.println("De donde chingaos saca el numero de funcionario ->" + mod.getFuncionario());
//
//            if (mod.getNombre_destino().equals("") || mod.getSiglas_destino().equals("")) {
//                JOptionPane.showMessageDialog(null, "Hay campos vacios, debe llenar todos los campos");
//            } else {
//                if (modC.existeSiglas(mod.getSiglas_destino()) != 0) {
//                    JOptionPane.showMessageDialog(null, "Existe un registro con esas siglas");
//                } else {
//                    if (modC.existeNombre(mod.getNombre_destino()) != 0) {
//                        JOptionPane.showMessageDialog(null, "Existe un registro con ese nombre");
//                    } else {
//                        if (mod.getId_tipodestino() == 0) {
//                            JOptionPane.showMessageDialog(null, "Debe seleccionar un Tipo de destino");
//                        } else {
//                            if (mod.getId_subtipodestino() != 0) {
//                                if (modC.registrar(mod)) {
//                                    JOptionPane.showMessageDialog(null, "Registro Guardado");
//                                    inicioBotones();
//                                    deshabilitar();
//                                    limpiar();
//                                    frm.txtBuscar.setText("");
//                                    modC.buscar(mod, frm);
//                                    //SE CAMBIA EL MODELO PARA QUE MUESTRE TODOS LOS ITEMS
//
//                                    // EL METODO SIN PARAMETROS YA EXISTIA
//                                    ConsultasDestino ConsultaDestino = new ConsultasDestino();
//                                    DefaultComboBoxModel modelSubtipo = new DefaultComboBoxModel(ConsultaDestino.mostrarSubtipoDestino());
//                                    frm.cbxSubtipoDestino.setModel(modelSubtipo);
//                                    //frm.cbxSubtipoDestino.setModel(ModeloDestino);
//
//                                    frm.cbxSubtipoDestino.setEnabled(false);
//                                } else {
//                                    JOptionPane.showMessageDialog(null, "Error al Guardar");
//                                    limpiar();
//                                }
//                            } else {
//                                JOptionPane.showMessageDialog(null, "Debe seleccionar un Subtipo de destino");
//                            }
//
//                        } //else valida tipo destino
//                    } //else valida campos Nombre
//                } //else valida siglas
//            } //if Valida campos vacios-
//        }//if boton guardar -
//} // Cierre actionPerformed
// }
//    @Override
//        public void keyPressed(KeyEvent e
//    ) {
//
//    }
//
//    @Override
//        public void keyReleased(KeyEvent e
//    ) {
//Modificara la tabla de acuerdo al caracter que se muestre en txtbuscar
//    }
//    @Override
//        public void keyTyped(KeyEvent e
//    ) {
//    }
//
//    public void inicioBotones() {
////        frm.btnGuardar.setVisible(false);
////        frm.btnCancelar.setVisible(false);
////        frm.btnGuardarMod.setVisible(false);
////        frm.btnCancelarMod.setVisible(false);
////        frm.btnNuevo.setVisible(true);
////        frm.btnModificar.setVisible(false);
////        frm.btnEliminar.setVisible(false);
////        frm.btnLimpiar.setVisible(false);
////        frm.txtBuscar.setVisible(true);
////        frm.lblBuscar.setVisible(true);
////        frm.cbxSubtipoDestino.setEnabled(false);
//
//    }
//
//    public void ocultarBotones() {
////        frm.btnGuardar.setVisible(true);
////        frm.btnCancelar.setVisible(true);
////        frm.btnNuevo.setVisible(false);
////        frm.btnModificar.setVisible(false);
////        frm.btnEliminar.setVisible(false);
////        frm.btnLimpiar.setVisible(true);
////        frm.txtBuscar.setVisible(false);
////        frm.lblBuscar.setVisible(false);
//        //  frm.btnBuscar.setVisible(false);
//    }
//
//    public void ocultarBotonesMod() {
////        frm.btnGuardarMod.setVisible(true);
////        frm.btnCancelarMod.setVisible(true);
////        frm.btnNuevo.setVisible(false);
////        frm.btnModificar.setVisible(false);
////        frm.btnEliminar.setVisible(false);
////        frm.btnLimpiar.setVisible(false);
//        // frm.btnBuscar.setVisible(false);
//    }
//
//    public void mostrarBotonesMod() {
////        frm.btnGuardarMod.setVisible(false);
////        frm.btnCancelarMod.setVisible(false);
////        frm.btnNuevo.setVisible(true);
////        frm.btnModificar.setVisible(true);
////        frm.btnEliminar.setVisible(true);
////        frm.btnLimpiar.setVisible(true);
//        //frm.btnBuscar.setVisible(true);
//    }
//
//    public void mostrarBotones() {
////        frm.btnGuardar.setVisible(false);
////        frm.btnCancelar.setVisible(false);
////        frm.btnNuevo.setVisible(true);
////        frm.btnModificar.setVisible(true);
////        frm.btnEliminar.setVisible(true);
////        frm.btnLimpiar.setVisible(true);
//        //frm.btnBuscar.setVisible(true);
//
//    }
//
//
//    public void habilitar() {
//        frm.txtFuncionario.setEnabled(false);
//        //frm.txtNombre_destino.setEditable(true);
////        frm.txtAsunto.setEditable(true);
////        frm.cbxSubtipoDestino.setEnabled(true);
//        //Validar si se puede modificar el subtipo con solo los datos filatrados
//        //frm.cbxSubtipoDestino.setEnabled(false);
//        frm.jtActMinOfi.setVisible(false);
//        frm.txtBuscar.setVisible(false);
//        frm.lblBuscar.setVisible(false);
//    }
//
//    public void deshabilitar() {
//        frm.txtFuncionario.setEnabled(true);
//        //frm.txtNombre_destino.setEditable(false);
////        frm.txtAsunto.setEditable(false);
////        frm.cbxSubtipoDestino.setEnabled(false);
////        frm.cbxSubtipoDestino.setEnabled(false);
//        frm.jtActMinOfi.setVisible(true);
//        frm.txtBuscar.setVisible(true);
//        frm.lblBuscar.setVisible(true);
//    }
//}

