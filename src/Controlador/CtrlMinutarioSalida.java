package Controlador;

import Modelo.ConsultasMinutarioSalida;
import Modelo.ModeloMinutarioSalida;
import Vista.frmMinutarioSalida;
import Modelo.Envio;
import Modelo.ModeloSeccion;
import Modelo.Serie;
import Modelo.Subserie;

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

public class CtrlMinutarioSalida implements ActionListener {

    private final ModeloMinutarioSalida modMinSal;
    private final ConsultasMinutarioSalida modConsSal;
    private final frmMinutarioSalida frmMinSal;
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
//    Color colorLetra = Color.decode("#DEC9A3");

    public CtrlMinutarioSalida(ModeloMinutarioSalida modMinSal, ConsultasMinutarioSalida modConsMinSal, frmMinutarioSalida frmMinSal) {

        //UIManager.put("OptionPane.minimumSize",new Dimension(500,500)); 
        this.modMinSal = modMinSal;
        this.modConsSal = modConsMinSal;
        this.frmMinSal = frmMinSal;

        this.frmMinSal.jdcFechaRegistro.setVisible(false);
        this.frmMinSal.lblFuncionario.setVisible(false);

        this.frmMinSal.cbxSeccion.addActionListener(this);
        this.frmMinSal.cbxSerie.addActionListener(this);
        this.frmMinSal.cbxSubserie.addActionListener(this);
        //this.frmMinSal.cbxDestinatario.addActionListener(this);
        this.frmMinSal.cbxTipoEnvio.addActionListener(this);
        //this.frmMinSal.jrbSre.addActionListener(this);
        //this.frmMinSal.jrbOtro.addActionListener(this);
        this.frmMinSal.jrbNotice.addActionListener(this);
        this.frmMinSal.jrbOficio.addActionListener(this);
        this.frmMinSal.btnGuardarMinuta.addActionListener(this);
        this.frmMinSal.btnCancelar.addActionListener(this);

        this.frmMinSal.txtDestinatario.addFocusListener(new FocusListener() {

            //Color colorVerdeAgua = new Color(204, 255, 204);
            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("Hacer la consulta y si no coincide poner en rojo");
                System.out.println(((JTextField) e.getSource()).getText());
                numero_destino = modConsSal.compararDestinatario(frmMinSal.txtDestinatario.getText());
                if (numero_destino == 0) {
                    //if(modConsSal.compararDestinatario(frmMinSal.txtDestinatario.getText())== 0){
                    frmMinSal.txtDestinatario.setForeground(colorLetra);
                    frmMinSal.txtDestinatario.setFont(negrita);
                } else {

                }
            }

            public void focusGained(FocusEvent e) {
                // No hacemos nada
            }
        });

        FocusListener fl = null;
        this.frmMinSal.txtDestinatario.addFocusListener(new java.awt.event.FocusAdapter() {

        });
    }

    public void iniciar() {
        frmMinSal.setTitle("Minutario Salida");
        frmMinSal.setLocationRelativeTo(null);
        frmMinSal.cbxSubserie.setVisible(false);
//        frmMinSal.txtOtroDestinatario.setVisible(false);
//        frmMinSal.cbxDestinatario.setVisible(false);
        frmMinSal.cbxTipoEnvio.setVisible(false);
        //--------- Si no lleva parametros no se debe mostrar CBXSERIE
        frmMinSal.cbxSerie.setVisible(false);

        frmMinSal.txtAsunto.setLineWrap(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Fecha de registro
//        java.util.Date date = this.frmMinSal.jdcFechaRegistro.getDate();
//        long d = date.getTime();
//        java.sql.Date fecha = new java.sql.Date(d);
//
//        JOptionPane.showMessageDialog(frmMinSal, fecha);
        if (e.getSource() == frmMinSal.btnGuardarMinuta) {

            //Recoge los datos del formulario y los asigna al objeto modMinSal
            modMinSal.setFecha_registro(frmMinSal.fechaRegistro);
            modMinSal.setSeccion(frmMinSal.cbxSeccion.getSelectedIndex());
            modMinSal.setSerie(serieAsignada);
            //modMinSal.setSubserie(SetNull);

            modMinSal.setSubserie(subserieAsignada);

            //------------
            //antes de que se asigne al objeto MonMinSal se deb hacer la validacion si es que el valor
            //se encuentra en la tabla, si es asi se debe mostrar en verde y tiene que buscar su numero de id.
            //Si el valor no esta en la tabla se muestra el rojo y se evita en envio de la informacion
            modMinSal.setDestinatario(numero_destino);
            //modMinSal.setDestinatario(optionDestinatario);
            modMinSal.setFuncionario(frmMinSal.idFuncionario);
            // VALIDAR PORQUE NO TOMA LOS DATOS DESDE EL Combobox 
            // modMinSal.setEnvio(frmMinSal.cbxTipoEnvio.getSelectedIndex());
            modMinSal.setEnvio(optionEnvioAsignado);
            modMinSal.setAsunto(frmMinSal.txtAsunto.getText());

            //Impresion de los datos
            System.out.println("\nFecha ->" + frmMinSal.fechaRegistro);
            System.out.println("Seccion ->" + frmMinSal.cbxSeccion.getSelectedIndex());
            System.out.println("Serie ->" + frmMinSal.cbxSerie.getSelectedIndex());
            System.out.println("SerieAsignada ->" + serieAsignada);
            System.out.println("Subserie ->" + frmMinSal.cbxSubserie.getSelectedIndex());
            System.out.println("SubSerieAsignada ->" + subserieAsignada);
            System.out.println("Numero_Destino ->" + numero_destino);
            System.out.println("Funcionario ->" + frmMinSal.idFuncionario);
            System.out.println("Tipo Envio ->" + optionEnvioAsignado);
            System.out.println("Asunto ->" + frmMinSal.txtAsunto.getText());
            System.out.println("modMinSal.getSerie() == 0 ->" + modMinSal.getSerie());

            if (modMinSal.getAsunto().equals("")) {
                JOptionPane.showMessageDialog(null, "Campo Asunto vacio");
            } else {
                if (modMinSal.getSeccion() == 0) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar Seccion");
                } else {
                    if (modMinSal.getSerie() == 0) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar Serie");
                    } else {
//                        if (modMinSal.getSubserie() == 0) {
//                            //VALIDAR QUE LA ETIQUETA MUESTRE EL DESTINATARIO, SI NO ES ASI DEBE MOSTRAR UNA SUBSERIE
//                            JOptionPane.showMessageDialog(null, "Debe seleccionar Subserie");
//                        } else {
                        //if (modMinSal.getDestinatario() == 0) {

                        //---
                        if (modMinSal.getDestinatario() == 0) {
                            //JOptionPane.showMessageDialog(null, "Debe seleccionar destinatario");
                            JOptionPane.showMessageDialog(null, "Destinatario sin asignar");

                        } else {
                            //---
                            if (modConsSal.compararDestinatario(frmMinSal.txtDestinatario.getText()) == 0) {
                                //JOptionPane.showMessageDialog(null, "Debe seleccionar destinatario");
                                JOptionPane.showMessageDialog(null, "Debe seleccionar un destinatario valido");

                            } else {
                                if (modMinSal.getEnvio() == 0) {
                                    JOptionPane.showMessageDialog(null, "Debe seleccionar tipo de envio");
                                } else {

                                    //---------
                                    if (modMinSal.getAsunto().length() > 500) {
                                        JOptionPane.showMessageDialog(null, "Campo asunto debe contener menos de 500 caracteres ");
                                    } else {
                                        //--------

                                        //------------
                                        String datosAGuardar = "\n Fecha : " + frmMinSal.fechaRegistro
                                                + "\n Clasificación : " + frmMinSal.lblClasificacionAsignada.getText()
                                                //+ "\n Destinatario : " + frmMinSal.lblDestinatarioAsignado.getText()
                                                + "\n Destinatario : " + frmMinSal.txtDestinatario.getText()
                                                + "\n Tipo de documento : " + frmMinSal.lblTipoDocumentoAsignado.getText()
                                                + "\n Tipo de envio : " + frmMinSal.lblTipoEnvioAsignado.getText()
                                                + "\n Asunto : " + modMinSal.getAsunto();

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
                                            if (modConsSal.registrar(modMinSal, subserieAsignada)) {

//                                                um.put("OptionPane.messageForeground", colorFondo);
                                                um.put("OptionPane.messageFont", new Font("Montserrat ExtraLight", Font.BOLD, 20));
                                                //um.put("Panel.background", Color.yellow);

                                                JOptionPane.showMessageDialog(null, "     " + modConsSal.mostrarLVN(modMinSal.getFuncionario()), "LVN ASIGNADO", JOptionPane.INFORMATION_MESSAGE);
//                                    inicioBotones();
//                                    deshabilitar();
//                                    limpiar();
//                                    modC.buscar(mod, frm);
                                                frmMinSal.dispose();
                                                CtrlHome.frmMinSal = null;
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

        if (e.getSource() == frmMinSal.btnCancelar) {
            frmMinSal.dispose();
            CtrlHome.frmMinSal = null;

            //JOptionPane.showMessageDialog(null, "Registro Cancelado");
        } // Cierre frmMinSal.btnCancelar

        if (e.getSource() == frmMinSal.cbxSeccion) {
            System.out.println("Entro a ActionPerformed porque se selecciono CBXSeccion");
            ModeloSeccion sec = (ModeloSeccion) frmMinSal.cbxSeccion.getSelectedItem();
            ConsultasMinutarioSalida ConsultaMinuta = new ConsultasMinutarioSalida();
            //ConsultaMinuta.mostrarSeccion(mod, frm); 
            ConsultaMinuta.mostrarSeccion();
            DefaultComboBoxModel modelSerie = new DefaultComboBoxModel(ConsultaMinuta.mostrarSerie(sec.getId()));
            frmMinSal.cbxSerie.setModel(modelSerie);
            frmMinSal.cbxSerie.setVisible(true);
            frmMinSal.cbxSubserie.setVisible(false);

            frmMinSal.cbxSerie.setVisible(true);

            //    frmMinSal.cbxSubserie.removeAllItems();
            //  frmMinSal.txtDatos.setText("");
            frmMinSal.lblClasificacionAsignada.setText("");
            serieAsignada = 0;
            subserieAsignada = 0;
        } // Cierre frmMinSal.cbxSeccion

        if (e.getSource() == frmMinSal.cbxSerie) {
            System.out.println("Entro a ActionPerformed porque se selecciono CBXSerie");
            //Seccion sec = (ModeloSeccion) frmMinSal.cbxSeccion.getSelectedItem();
            Serie ser = (Serie) frmMinSal.cbxSerie.getSelectedItem();
            ConsultasMinutarioSalida ConsultaMinuta = new ConsultasMinutarioSalida();
            ConsultaMinuta.mostrarSerie(frmMinSal.cbxSeccion.getSelectedIndex());
            serieAsignada = ser.getId();
            //subserieAsignada = null;

            DefaultComboBoxModel modelSubserie = new DefaultComboBoxModel(ConsultaMinuta.mostrarSubserie(ser.getId()));
            frmMinSal.cbxSubserie.setModel(modelSubserie);
            //frmMinSal.txtDatos.setText("");
            frmMinSal.lblClasificacionAsignada.setText("");
            subserieAsignada = 0;
            frmMinSal.cbxSubserie.setVisible(false);
            //nItems guarda el numero de valores regresados por el query
            int nItems = frmMinSal.cbxSubserie.getItemCount();
            System.out.println("app.ComboBoxUno.cbxSerieItemStateChanged() " + nItems);
            //  Si solo regresa un valor, demuestra que esta serie no cuenta con subserie
            if (nItems == 1) {
                //Se inhabilita el combobox de subserie

                //frmMinSal.cbxSubserie.setEnabled(false);
                frmMinSal.cbxSubserie.setVisible(false);
                //Muestra el txtArea el valor de de la seecion y serie, ya que no cuenta con subserie
                // frmMinSal.txtDatos.setText("ModeloSeccion: " + sec.getNombre() + "\n Serie: " + ser.getNombre());
                frmMinSal.lblClasificacionAsignada.setText(ser.getNombre());

            } else //Si hay mas de un dato, demuestra que si cuenta que esta serie cuenta con subserie
            {
                //frmMinSal.cbxSubserie.setEnabled(true);
                frmMinSal.cbxSubserie.setVisible(true);
            }
        }  // Cierre frmMinSal.cbxSerie

        if (e.getSource() == frmMinSal.cbxSubserie) {
            System.out.println("Entro a ActionPerformed porque se selecciono CBXSubSerie");
//            ModeloSeccion sec = (ModeloSeccion) frmMinSal.cbxSeccion.getSelectedItem();
//            Serie ser = (Serie) frmMinSal.cbxSerie.getSelectedItem();
            Subserie sub = (Subserie) frmMinSal.cbxSubserie.getSelectedItem();

            frmMinSal.lblClasificacionAsignada.setText(sub.getNombre());

            //CHECAR SI SE DEBE PONER EN 0 O EN ALGUN OTRO VALOR SI ES QUE NO EXISTE UNA SERIE CON SUBSERIE
            subserieAsignada = sub.getId();

        } // Cierre frmMinSal.cbxSubserie

        if (e.getSource() == frmMinSal.txtDestinatario) {
            System.out.println("Entro a ActionPerformed porque se selecciono TXTDestinatario");

            System.out.println("OptionDestino en COMBODESTINATARIO -->" + optionDestinatario);
        }   // Cierre frmMinSal.cbxDestinatario

        if (e.getSource() == frmMinSal.jrbNotice) {
            System.out.println("Entro a ActionPerformed porque se selecciono JRBNotice");
            frmMinSal.cbxTipoEnvio.setVisible(true);
            optionEnvio = 1;
            ConsultasMinutarioSalida ConsultaMinuta = new ConsultasMinutarioSalida();
            DefaultComboBoxModel modelEnvio = new DefaultComboBoxModel(ConsultaMinuta.mostrarEnvio(optionEnvio));
            frmMinSal.cbxTipoEnvio.setModel(modelEnvio);
            Envio env = (Envio) frmMinSal.cbxTipoEnvio.getSelectedItem();
            System.out.println("TipoEnvio al precionar Notice" + frmMinSal.cbxTipoEnvio.getSelectedIndex());

            frmMinSal.lblTipoEnvioAsignado.setText(env.getNombre());
            frmMinSal.lblTipoDocumentoAsignado.setText(ConsultaMinuta.mostrarTipoDocumento(optionEnvio));
            optionEnvioAsignado = env.getId();
            System.out.println("ENV.DETID NOTICE "
                    + env.getId());
        }   // Cierre frmMinSal.jrbNotice

        if (e.getSource() == frmMinSal.jrbOficio) {
            System.out.println("Entro a ActionPerformed porque se selecciono JRBOficio");

            frmMinSal.cbxTipoEnvio.setVisible(true);
            optionEnvio = 2;
            ConsultasMinutarioSalida ConsultaMinuta = new ConsultasMinutarioSalida();
            DefaultComboBoxModel modelEnvio = new DefaultComboBoxModel(ConsultaMinuta.mostrarEnvio(optionEnvio));
            frmMinSal.cbxTipoEnvio.setModel(modelEnvio);
            Envio env = (Envio) frmMinSal.cbxTipoEnvio.getSelectedItem();
            frmMinSal.lblTipoEnvioAsignado.setText(env.getNombre());
            frmMinSal.lblTipoDocumentoAsignado.setText(ConsultaMinuta.mostrarTipoDocumento(optionEnvio));
            optionEnvioAsignado = env.getId();
            System.out.println("ENV.DETID OFICIO " + env.getId());
        } // Cierre frmMinSal.jrbOficio

        if (e.getSource() == frmMinSal.cbxTipoEnvio) {
            System.out.println("Entro a ActionPerformed porque se selecciono CBXTipoEnvio");
            Envio env = (Envio) frmMinSal.cbxTipoEnvio.getSelectedItem();
            frmMinSal.lblTipoEnvioAsignado.setText(env.getNombre());
            optionEnvioAsignado = env.getId();
            System.out.println("ENV.DETID COMBOBOX " + env.getId());
        } // Cierre frmMinSal.cbxTipoEnvio
    } // Cierre actionPerformed
} // Cierre clase pricipal
