package Controlador;

import Modelo.ConsultasCorrespondencia;
import Modelo.ConsultasRemitente;
import Modelo.ModeloCorrespondencia;
import Modelo.ModeloLogin;
import Modelo.ModeloRemitente;
import Modelo.ModeloSEM_Admin;
import Modelo.ModeloTipoCorres;
import Vista.frmCorrespondenciaEntrada;
import Vista.frmRemitente;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class CtrlCorrespondenciaEntrada implements ActionListener, MouseListener, KeyListener {

    private final ModeloCorrespondencia mod;
    private final ConsultasCorrespondencia modC;
    private final frmCorrespondenciaEntrada frm;
    int optionDoc;
    String tipoDocumento;
    int optionDestinatario;
    int numero_remitente;
    int optionAsignado_a;
    String Asigando_a;
    Date fechaEnvio;
    Date fechaEntrega;

    Color colorLetra = Color.decode("#691A31");
    Color blanco = Color.decode("#FFFFFF");
    Font negrita = new Font("Montserrat ExtraLight", Font.BOLD, 14);
//    Color colorLetra = Color.decode("#DEC9A3");

    //Remitente
    private final ModeloRemitente modRemi = new ModeloRemitente();
    private final ConsultasRemitente modConRemi = new ConsultasRemitente();
    public static frmRemitente frmRemi;
    public static ModeloLogin modLoginRemi;

    public void iniciar() {
        frm.setTitle("Entrada de Correspondencia");
        frm.setLocationRelativeTo(null);
        frm.txtAsunto.setLineWrap(true);
    }

    public CtrlCorrespondenciaEntrada(ModeloCorrespondencia modCorres, ConsultasCorrespondencia modConCorres, frmCorrespondenciaEntrada frmCorrEnt) {

        this.mod = modCorres;
        this.modC = modConCorres;
        this.frm = frmCorrEnt;

        this.frm.cbxTipoCorres.addActionListener(this);
//        this.frm.jrbCorreo.addActionListener(this);
//        this.frm.jrbValija.addActionListener(this);
        this.frm.cbxAsignadoA.addActionListener(this);
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnCancelar.addActionListener(this);
        this.frm.btnNuevoRemi.addActionListener(this);

        this.frm.jtCorrespondencia.addMouseListener(this);
        this.frm.txtBuscar.addKeyListener(this);  //Para buscar caracter por caracter y modificar la tabla de acuerdo al resultado
        this.frm.jtCorrespondencia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //-----------
        this.frm.txtRemitente.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("Hacer la consulta y si no coincide poner en rojo");
                System.out.println(((JTextField) e.getSource()).getText());
                numero_remitente = modConCorres.compararRemitente(frm.txtRemitente.getText());
                if (numero_remitente == 0) {
                    //if(modConsSal.compararDestinatario(frmMinSal.txtDestinatario.getText())== 0){
                    frm.txtRemitente.setForeground(colorLetra);
                    frm.txtRemitente.setFont(negrita);
                    //modMinSal.setDestinatario(optionDestinatario);
                } else {
//                    frmMinSal.txtDestinatario.setForeground(colorLetra);
                    //          modMinSal.setDestinatario(optionDestinatario);
                }
            }

            public void focusGained(FocusEvent e) {
                // No hacemos nada
            }
        });
        //-------------

        FocusListener fl = null;
        this.frm.txtRemitente.addFocusListener(new java.awt.event.FocusAdapter() {
        });
    }

    // esta funcion agrega a los combobox los datos de la fila seleccionada
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == frm.jtCorrespondencia) {

            //Limpia txtBuscar
            frm.txtBuscar.setText("");
            if (modC.Seleccionar(mod, frm)) {

                ModeloSEM_Admin ModelSEMAdm = (ModeloSEM_Admin) frm.cbxAsignadoA.getSelectedItem();

                //frm.txtNumero.setText(String.valueOf(mod.getNumero()));
                frm.cbxTipoCorres.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getTipo_correspondencia())));

                //frm.txtRemitente.setText(String.valueOf(mod.getRemitente()));
                frm.txtRemitente.setText(String.valueOf(mod.getNombre_remitente()));
                frm.txtReferencia.setText(String.valueOf(mod.getReferencia()));
                frm.txtInformacionAdicional.setText(String.valueOf(mod.getInformacion()));
                //frm.jdcFechaRegistro.setDate(mod.getFecha_registro());
                frm.jdcFechaEnvio.setDate(mod.getFecha_envio());
                frm.jdcFechaEntrega.setDate(mod.getFecha_entrega());
                // frm.cbxAsignadoA.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getAsignado_a())));
                frm.cbxAsignadoA.setSelectedIndex(Integer.parseInt(String.valueOf(ModelSEMAdm.getId())));
                frm.txtAsunto.setText(String.valueOf(mod.getAsunto()));
//                frm.txtFuncionario.setText(String.valueOf(modC.mostrarNombreFuncionario(mod.getFuncionario())));
//                frm.cbxTipoDocumento.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getDocumento())));
//                frm.cbxTipoEnvio.setSelectedIndex(Integer.parseInt(String.valueOf(mod.getEnvio())));
//                frm.txtAsunto2.setText(String.valueOf(modRemi.getAsunto()));
//                frm.txtResponsable.setText(String.valueOf(modConCorres.mostrarNombreFuncionarioInt(modRemi.getResponsable())));
//                if (mod.getFecha_envio() == null) {
//                    frm.txtFechaEnvio.setText("");
//                } else {
//                    frm.txtFechaEnvio.setText(String.valueOf(mod.getFecha_envio()));
//                }
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
        
                if (e.getSource() == frm.txtBuscar) {
            if (modC.buscarCaracter(mod, frm)) {
                //  limpiar();
//                inicioBotones();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Buscar");
                //   limpiar();
            }
        }

        //Fecha de registro
//        java.util.Date date = this.frmMinSal.jdcFechaRegistro.getDate();
//        long d = date.getTime();
//        java.sql.Date fecha = new java.sql.Date(d);
//
//        JOptionPane.showMessageDialog(frmMinSal, fecha);
        if (e.getSource() == frm.btnGuardar) {

            //Se guarda el numero de remitente despues de que se asigno en el metodo
            //FocusListener en txtRemitente
//            mod.setTipo_correspondencia(optionDoc);
            mod.setTipo_correspondencia(optionDoc);
            mod.setRemitente(numero_remitente);
            mod.setReferencia(frm.txtReferencia.getText());
            mod.setInformacion(frm.txtInformacionAdicional.getText());

            // Fecha de envio
            java.util.Date date;

            date = frm.jdcFechaEnvio.getDate();
            long d = date.getTime();
            java.sql.Date fecha = new java.sql.Date(d);
            fechaEnvio = fecha;
            //Agregar el valor  al modelo en el registro FechaEnvio
            mod.setFecha_envio(fechaEnvio);

            // Fecha de entrega
            date = frm.jdcFechaEntrega.getDate();
            d = date.getTime();
            java.sql.Date fecha2 = new java.sql.Date(d);
            fechaEntrega = fecha2;
            //Agregar el valor  al modelo en el registro FechaEntrega
            mod.setFecha_entrega(fechaEntrega);
            mod.setResponsable(frm.idFuncionario);
            mod.setAsignado_a(optionAsignado_a);
            mod.setAsunto(frm.txtAsunto.getText());

//            if (!frm.jrbCorreo.isSelected() && !frm.jrbValija.isSelected()) {
//                JOptionPane.showMessageDialog(null, "Debe seleccionar el tipo de correspondencia");
            if (mod.getTipo_correspondencia() == 0) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar el tipo de correspondencia");
            } else {
                if (mod.getReferencia().equals("")) {
                    JOptionPane.showMessageDialog(null, "Campo referencia vacio");
                } else {
                    if (mod.getFecha_envio().equals("")) {
                        JOptionPane.showMessageDialog(null, "Campo fecha envio vacio");
                    } else {
                        if (mod.getFecha_entrega().equals("")) {
                            JOptionPane.showMessageDialog(null, "Campo fecha entrega vacio");
                        } else {
                            if (mod.getAsunto().equals("")) {
                                JOptionPane.showMessageDialog(null, "Campo Asunto vacio");
                            } else {
                                if (mod.getAsignado_a() == 0) {
                                    JOptionPane.showMessageDialog(null, "Debe seleccionar a quien se le ha asignado la correspondencia en turno");
                                } else {
                                    //---
                                    if (modC.compararRemitente(frm.txtRemitente.getText()) == 0) {
                                        JOptionPane.showMessageDialog(null, "Debe seleccionar un remitente valido");
                                    } else {
                                        if (mod.getAsunto().length() > 500) {
                                            JOptionPane.showMessageDialog(null, "Campo asunto debe contener menos de 500 caracteres ");
                                        } else {
                                            String datosAGuardar = "\n Documento : " + tipoDocumento
                                                    + "\n Remitente : " + frm.txtRemitente.getText()
                                                    + "\n Referencia : " + frm.txtReferencia.getText()
                                                    + "\n Info. adicional : " + frm.txtInformacionAdicional.getText()
                                                    + "\n Fecha de envio : " + fechaEnvio
                                                    + "\n Fecha de entrega : " + fechaEntrega
                                                    + "\n Asignado a : " + Asigando_a
                                                    + "\n Asunto : " + mod.getAsunto();

//                                        String datosAGuardar = "\n Fecha : " + frmMinSal.fechaRegistro
//                                                + "\n Clasificación : " + frmMinSal.lblClasificacionAsignada.getText()
//                                                //+ "\n Destinatario : " + frmMinSal.lblDestinatarioAsignado.getText()
//                                                + "\n Destinatario : " + frmMinSal.txtDestinatario.getText()
//                                                + "\n Tipo de documento : " + frmMinSal.lblTipoDocumentoAsignado.getText()
//                                                + "\n Tipo de envio : " + frmMinSal.lblTipoEnvioAsignado.getText()
//                                                + "\n Asunto : " + modMinSal.getAsunto();
                                            //JScrollPane scrollPane = new JScrollPane(new JLabel(modMinSal.getAsunto()));
                                            JScrollPane scrollPane = new JScrollPane(new JLabel(datosAGuardar));
                                            scrollPane.setPreferredSize(new Dimension(800, 200));
                                            Object message = scrollPane;

                                            System.out.println("datosAGuardar" + datosAGuardar);

                                            JTextArea textArea = new JTextArea(datosAGuardar);
                                            textArea.setLineWrap(true);
                                            textArea.setWrapStyleWord(true);
                                            textArea.setMargin(new Insets(5, 5, 5, 5));
                                            scrollPane.getViewport().setView(textArea);
                                            message = scrollPane;
                                            UIManager um = new UIManager();
                                            um.put("OptionPane.messageFont", new Font("Montserrat ExtraLight", Font.PLAIN, 14));
                                            int resp = JOptionPane.showConfirmDialog(null, message, "Confirmación de información a guardar", JOptionPane.YES_NO_OPTION);
                                            System.out.println("Cero si guarda! = " + resp);
                                            if (resp == 0) {

                                                if (modC.registrar(mod)) {

//                                                um.put("OptionPane.messageForeground", colorFondo);
                                                    um.put("OptionPane.messageFont", new Font("Montserrat ExtraLight", Font.BOLD, 20));
                                                    //um.put("Panel.background", Color.yellow);
                                                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                                                    // JOptionPane.showMessageDialog(null, "     " + modConsSal.mostrarLVN(modMinSal.getFuncionario()), "LVN ASIGNADO", JOptionPane.INFORMATION_MESSAGE);
//                                    inicioBotones();
//                                    deshabilitar();
//                                    limpiar();
//                                    modC.buscar(mod, frm);
                                                    frm.dispose();
                                                    CtrlHome.frmCorrEnt = null;
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Cancelado");
                                                // limpiar();
                                            }
//                                }else { //else error al guardar
//                                    JOptionPane.showMessageDialog(null, "Debe seleccionar tipo de envio");

                                            //------------
                                            //  } //Parece que es de txtAsunto
                                            //------------
                                            //}
                                            //}  //else Destino no nullo
                                        }
                                    } //else registrar
                                } //else destinatario
                                //} //else subserie
                            } //else serie
                        } //else seccion
                    }
                }
            }
        }// else Valida que ASunto no este vacio.    2
        //cierre frmMinSal.btnGuardarMinuta   1

        if (e.getSource() == frm.btnNuevoRemi) {
            System.out.println("Se selecciono Remitente");
            if (frmRemi == null) {
                System.out.println("No existe un formulario Remitente");
                //String clavefuncionario = frmhome.ClaveFuncionario;
                int idFuncionario = frm.idFuncionario;
                frmRemi = new frmRemitente();
                //CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, clavefuncionario);
                CtrlRemitente ctrl = new CtrlRemitente(modRemi, modConRemi, frmRemi, idFuncionario);
                ctrl.iniciar();
                frmRemi.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
                frmRemi.toFront();
            }
        } // Cierre frm.btnNuevoRemi

        if (e.getSource() == frm.btnCancelar) {
            frm.dispose();
            CtrlHome.frmCorrEnt = null;

            //JOptionPane.showMessageDialog(null, "Registro Cancelado");
        } // Cierre frmMinSal.btnCancelar

//        if (e.getSource() == frm.jrbCorreo) {
//            System.out.println("Se selecciono JRBCorreo");
//            optionDoc = 5;
//            tipoDocumento = "Correo";
//            frm.txtReferencia.setText("");
//        }   // Cierre frmMinSal.jrbNotice
//
//        if (e.getSource() == frm.jrbValija) {
//            System.out.println("Se selecciono JRBValija");
//            optionDoc = 2;
//            tipoDocumento = "Valija diplomatica";
//            frm.txtReferencia.setText("Pieza: ");
//        } // Cierre frmMinSal.jrbOficio
        if (e.getSource() == frm.cbxAsignadoA) {
            System.out.println("Se selecciono CBXAsignado_a");
            ModeloSEM_Admin ModelSEMAdm = (ModeloSEM_Admin) frm.cbxAsignadoA.getSelectedItem();
            //frm.lblTipoEnvioAsignado.setText(env.getNombre());
            optionAsignado_a = ModelSEMAdm.getId();
            System.out.println("ENV.DETID COMBOBOX " + ModelSEMAdm.getId());
            Asigando_a = ModelSEMAdm.getNombre();
            System.out.println("Asigando a : " + Asigando_a);
            //frmMinSal.lblClasificacionAsignada.setText(sub.getNombre());

        } // Cierre frmMinSal.cbxTipoEnvio

        if (e.getSource() == frm.cbxTipoCorres) {
            System.out.println("Se selecciono CBXTipoCorrespondencia");
            ModeloTipoCorres ModelTipoCorres = (ModeloTipoCorres) frm.cbxTipoCorres.getSelectedItem();
            //frm.lblTipoEnvioAsignado.setText(env.getNombre());
            optionDoc = ModelTipoCorres.getId();
            System.out.println("Numero de tipo de correpondencia " + ModelTipoCorres.getId());
            tipoDocumento = ModelTipoCorres.getNombre();
            System.out.println("Tipo Correspondencia : " + Asigando_a);
            //frmMinSal.lblClasificacionAsignada.setText(sub.getNombre());

            //   optionDoc = 5;
            //   tipoDocumento = "Correo";
        } // Cierre frmMinSal.cbxTipoEnvio

    } // Cierre actionPerformed
    
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
    
        public void limpiar() {
            
        frm.cbxTipoCorres.setSelectedIndex(0);
        frm.txtRemitente.setText(null);
        frm.txtReferencia.setText(null);
        frm.txtInformacionAdicional.setText(null);
        frm.jdcFechaEnvio.setDate(null);
        frm.jdcFechaEntrega.setDate(null);
        frm.cbxAsignadoA.setSelectedIndex(0);
        frm.txtAsunto.setText(null);
        
        //frm.txtBuscar.setText("");
    }

} // Cierre clase pricipal
