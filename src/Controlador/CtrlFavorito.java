package Controlador;

import Modelo.ConsultasFavorito;
import Modelo.ConsultasMinutarioSalida;
import Modelo.Envio;
import Modelo.ModeloFavorito;
import Modelo.ModeloSeccion;
import Modelo.Serie;
import Modelo.Subserie;
import Vista.frmFavorito;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class CtrlFavorito implements ActionListener {

    private final ModeloFavorito modFav;
    private final ConsultasFavorito modConFav;
    private final frmFavorito frmFav;
    int optionEnvio;
    int optionDestinatario;
    int numero_destino;
    int optionEnvioAsignado;
    int serieAsignada;
    int SeccionAsignada = 0;
    int subserieAsignada = 0;
    Color colorLetra = Color.decode("#691A31");
    Color blanco = Color.decode("#FFFFFF");
    Font negrita = new Font("Montserrat ExtraLight", Font.BOLD, 14);

    public CtrlFavorito(ModeloFavorito modFav, ConsultasFavorito modConFav, frmFavorito frmFav) {

        this.modFav = modFav;
        this.modConFav = modConFav;
        this.frmFav = frmFav;
        this.frmFav.jdcFechaRegistro.setVisible(false);
        this.frmFav.lblFuncionario.setVisible(false);
        this.frmFav.cbxSeccion.addActionListener(this);
        this.frmFav.cbxSerie.addActionListener(this);
        this.frmFav.cbxSubserie.addActionListener(this);
        this.frmFav.cbxTipoEnvio.addActionListener(this);
        this.frmFav.jrbNotice.addActionListener(this);
        this.frmFav.jrbOficio.addActionListener(this);
        this.frmFav.btnGuardarMinuta.addActionListener(this);
        this.frmFav.btnCancelar.addActionListener(this);

        this.frmFav.txtDestinatario.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                
                System.out.println(((JTextField) e.getSource()).getText());
                numero_destino = modConFav.compararDestinatario(frmFav.txtDestinatario.getText());
                if (numero_destino == 0) {
                    frmFav.txtDestinatario.setForeground(colorLetra);
                    frmFav.txtDestinatario.setFont(negrita);
                } else {
                }

            }

            public void focusGained(FocusEvent e) {
                // No hacemos nada
            }
        });

        FocusListener fl = null;
        this.frmFav.txtDestinatario.addFocusListener(new java.awt.event.FocusAdapter() {
        });
    }

    public void iniciar() {
//        frmFav.setTitle("Minutario Salida");
        frmFav.setLocationRelativeTo(null);
        frmFav.cbxSubserie.setVisible(false);
//        frmFav.cbxTipoEnvio.setVisible(false);
        frmFav.txtAsunto.setLineWrap(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmFav.btnGuardarMinuta) {

            //Recoge los datos del formulario y los asigna al objeto modMinSal
            modFav.setFecha_registro(frmFav.fechaRegistro);
            modFav.setSeccion(frmFav.cbxSeccion.getSelectedIndex());
            modFav.setSerie(frmFav.id_serie_a_guardar);
            modFav.setSubserie(frmFav.id_subserie_a_guardar);
            modFav.setDestinatario(numero_destino);
            modFav.setFuncionario(frmFav.idFuncionario);

            // asignar uno o dos dependiendo de si es oficio o notice
            modFav.setEnvio(frmFav.envio);
            modFav.setAsunto(frmFav.txtAsunto.getText());

            if (modFav.getAsunto().equals("")) {
                JOptionPane.showMessageDialog(null, "Campo Asunto vacio");
            } else {
                if (modFav.getSeccion() == 0) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar Seccion");
                } else {
                    if (modFav.getSerie() == 0) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar Serie");
                    } else {
//                        if (modMinSal.getSubserie() == 0) {
//                            //VALIDAR QUE LA ETIQUETA MUESTRE EL DESTINATARIO, SI NO ES ASI DEBE MOSTRAR UNA SUBSERIE
//                            JOptionPane.showMessageDialog(null, "Debe seleccionar Subserie");
//                        } else {
                        //if (modMinSal.getDestinatario() == 0) {

                        //---
                        if (modFav.getDestinatario() == 0) {
                            //JOptionPane.showMessageDialog(null, "Debe seleccionar destinatario");
                            JOptionPane.showMessageDialog(null, "Destinatario sin asignar");

                        } else {
                            //---
                            if (modConFav.compararDestinatario(frmFav.txtDestinatario.getText()) == 0) {
                                //JOptionPane.showMessageDialog(null, "Debe seleccionar destinatario");
                                JOptionPane.showMessageDialog(null, "Debe seleccionar un destinatario valido");

                            } else {
                                if (modFav.getEnvio() == 0) {
                                    JOptionPane.showMessageDialog(null, "Debe seleccionar tipo de envio");
                                } else {

                                    //---------
                                    if (modFav.getAsunto().length() > 500) {
                                        JOptionPane.showMessageDialog(null, "Campo asunto debe contener menos de 500 caracteres ");
                                    } else {
                                        //--------

                                        //------------
                                        String datosAGuardar = "\n Fecha : " + frmFav.fechaRegistro
                                                + "\n Clasificación : " + frmFav.lblClasificacionAsignada.getText()
                                                //+ "\n Destinatario : " + frmMinSal.lblDestinatarioAsignado.getText()
                                                + "\n Destinatario : " + frmFav.txtDestinatario.getText()
                                                + "\n Tipo de documento : " + frmFav.lblTipoDocumentoAsignado.getText()
                                                + "\n Tipo de envio : " + frmFav.lblTipoEnvioAsignado.getText()
                                                + "\n Asunto : " + modFav.getAsunto();

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
                                            // if (modConFav.registrar(modFav, subserieAsignada)) {
                                            if (modConFav.registrar(modFav, frmFav.id_subserie_a_guardar)) {
                                                //  um.put("OptionPane.messageForeground", colorFondo);
                                                um.put("OptionPane.messageFont", new Font("Montserrat ExtraLight", Font.BOLD, 20));
                                                //um.put("Panel.background", Color.yellow);

                                                JOptionPane.showMessageDialog(null, "     " + modConFav.mostrarLVN(modFav.getFuncionario()), "LVN ASIGNADO", JOptionPane.INFORMATION_MESSAGE);
//                                    inicioBotones();
//                                    deshabilitar();
//                                    limpiar();
//                                    modC.buscar(mod, frm);
                                                frmFav.dispose();
                                                CtrlHome.frmFav = null;
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
                                    } //Parece que es de txtAsunto
                                    //------------

                                }
                            }  //else Destino no nullo
                        } //else registrar
                    } //else destinatario
                    //} //else subserie
                } //else serie
            } //else seccion
        }// else Valida que ASunto no este vacio.    2
        //cierre frmMinSal.btnGuardarMinuta   1

        if (e.getSource() == frmFav.btnCancelar) {
            frmFav.dispose();
            CtrlHome.frmFav = null;

            //JOptionPane.showMessageDialog(null, "Registro Cancelado");
        } // Cierre frmMinSal.btnCancelar

        if (e.getSource() == frmFav.cbxSeccion) {
            System.out.println("Entro a ActionPerformed porque se selecciono CBXSeccion");
            ModeloSeccion sec = (ModeloSeccion) frmFav.cbxSeccion.getSelectedItem();
            ConsultasMinutarioSalida ConsultaMinuta = new ConsultasMinutarioSalida();
            //ConsultaMinuta.mostrarSeccion(mod, frm); 
            ConsultaMinuta.mostrarSeccion();
            DefaultComboBoxModel modelSerie = new DefaultComboBoxModel(ConsultaMinuta.mostrarSerie(sec.getId()));
            frmFav.cbxSerie.setModel(modelSerie);
            frmFav.cbxSerie.setVisible(true);
            frmFav.cbxSubserie.setVisible(false);

            frmFav.cbxSerie.setVisible(true);

            //    frmMinSal.cbxSubserie.removeAllItems();
            //  frmMinSal.txtDatos.setText("");
            frmFav.lblClasificacionAsignada.setText("");
            serieAsignada = 0;
            subserieAsignada = 0;
        } // Cierre frmMinSal.cbxSeccion

        if (e.getSource() == frmFav.cbxSerie) {
            System.out.println("Entro a ActionPerformed porque se selecciono CBXSerie");
            //Seccion sec = (ModeloSeccion) frmMinSal.cbxSeccion.getSelectedItem();
            Serie ser = (Serie) frmFav.cbxSerie.getSelectedItem();
            ConsultasMinutarioSalida ConsultaMinuta = new ConsultasMinutarioSalida();
            ConsultaMinuta.mostrarSerie(frmFav.cbxSeccion.getSelectedIndex());
            serieAsignada = ser.getId();
            //subserieAsignada = null;

            DefaultComboBoxModel modelSubserie = new DefaultComboBoxModel(ConsultaMinuta.mostrarSubserie(ser.getId()));
            frmFav.cbxSubserie.setModel(modelSubserie);
            //frmMinSal.txtDatos.setText("");
            frmFav.lblClasificacionAsignada.setText("");
            subserieAsignada = 0;
            frmFav.cbxSubserie.setVisible(false);
            //nItems guarda el numero de valores regresados por el query
            int nItems = frmFav.cbxSubserie.getItemCount();
            System.out.println("app.ComboBoxUno.cbxSerieItemStateChanged() " + nItems);
            //  Si solo regresa un valor, demuestra que esta serie no cuenta con subserie
            if (nItems == 1) {
                //Se inhabilita el combobox de subserie

                //frmMinSal.cbxSubserie.setEnabled(false);
                frmFav.cbxSubserie.setVisible(false);
                //Muestra el txtArea el valor de de la seecion y serie, ya que no cuenta con subserie
                // frmMinSal.txtDatos.setText("ModeloSeccion: " + sec.getNombre() + "\n Serie: " + ser.getNombre());
                frmFav.lblClasificacionAsignada.setText(ser.getNombre());

            } else //Si hay mas de un dato, demuestra que si cuenta que esta serie cuenta con subserie
            {
                //frmMinSal.cbxSubserie.setEnabled(true);
                frmFav.cbxSubserie.setVisible(true);
            }
        }  // Cierre frmMinSal.cbxSerie

        if (e.getSource() == frmFav.cbxSubserie) {
            System.out.println("Entro a ActionPerformed porque se selecciono CBXSubSerie");
//            ModeloSeccion sec = (ModeloSeccion) frmMinSal.cbxSeccion.getSelectedItem();
//            Serie ser = (Serie) frmMinSal.cbxSerie.getSelectedItem();
            Subserie sub = (Subserie) frmFav.cbxSubserie.getSelectedItem();

            frmFav.lblClasificacionAsignada.setText(sub.getNombre());

            //CHECAR SI SE DEBE PONER EN 0 O EN ALGUN OTRO VALOR SI ES QUE NO EXISTE UNA SERIE CON SUBSERIE
            subserieAsignada = sub.getId();

        } // Cierre frmMinSal.cbxSubserie

        if (e.getSource() == frmFav.txtDestinatario) {
            System.out.println("Entro a ActionPerformed porque se selecciono TXTDestinatario");

            System.out.println("OptionDestino en COMBODESTINATARIO -->" + optionDestinatario);
        }   // Cierre frmMinSal.cbxDestinatario

        if (e.getSource() == frmFav.jrbNotice) {
            System.out.println("Entro a ActionPerformed porque se selecciono JRBNotice");
            frmFav.cbxTipoEnvio.setVisible(true);
            optionEnvio = 1;
            ConsultasMinutarioSalida ConsultaMinuta = new ConsultasMinutarioSalida();
            DefaultComboBoxModel modelEnvio = new DefaultComboBoxModel(ConsultaMinuta.mostrarEnvio(optionEnvio));
            frmFav.cbxTipoEnvio.setModel(modelEnvio);
            Envio env = (Envio) frmFav.cbxTipoEnvio.getSelectedItem();
            System.out.println("TipoEnvio al precionar Notice" + frmFav.cbxTipoEnvio.getSelectedIndex());
            frmFav.lblTipoEnvioAsignado.setText(env.getNombre());
            frmFav.lblTipoDocumentoAsignado.setText(ConsultaMinuta.mostrarTipoDocumento(optionEnvio));
            optionEnvioAsignado = env.getId();
            System.out.println("ENV.DETID NOTICE "
                    + env.getId());
        }   // Cierre frmMinSal.jrbNotice

        if (e.getSource() == frmFav.jrbOficio) {
            System.out.println("Entro a ActionPerformed porque se selecciono JRBOficio");

            frmFav.cbxTipoEnvio.setVisible(true);
            optionEnvio = 2;
            ConsultasMinutarioSalida ConsultaMinuta = new ConsultasMinutarioSalida();
            DefaultComboBoxModel modelEnvio = new DefaultComboBoxModel(ConsultaMinuta.mostrarEnvio(optionEnvio));
            frmFav.cbxTipoEnvio.setModel(modelEnvio);
            Envio env = (Envio) frmFav.cbxTipoEnvio.getSelectedItem();
            frmFav.lblTipoEnvioAsignado.setText(env.getNombre());
            frmFav.lblTipoDocumentoAsignado.setText(ConsultaMinuta.mostrarTipoDocumento(optionEnvio));
            optionEnvioAsignado = env.getId();
            System.out.println("ENV.DETID OFICIO " + env.getId());
        } // Cierre frmMinSal.jrbOficio

        if (e.getSource() == frmFav.cbxTipoEnvio) {
            System.out.println("Entro a ActionPerformed porque se selecciono CBXTipoEnvio");
            Envio env = (Envio) frmFav.cbxTipoEnvio.getSelectedItem();
            frmFav.lblTipoEnvioAsignado.setText(env.getNombre());
            optionEnvioAsignado = env.getId();
            System.out.println("ENV.DETID COMBOBOX " + env.getId());
        } // Cierre frmMinSal.cbxTipoEnvio
    } // Cierre actionPerformed
} // Cierre clase pricipal
