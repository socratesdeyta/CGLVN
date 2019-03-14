package Vista;

import Controlador.CtrlHome;
import Modelo.ConsultasDestino;
import Modelo.ConsultasMinutarioSalida;
import Modelo.ModeloDestinoArray;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JEditorPane;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class frmMinutarioSalida extends javax.swing.JFrame {

    //public String idFuncionario;
    public int idFuncionario;
    public String nombreFuncionario;
    public Date fechaRegistro;

//    Color colorFondo = Color.decode("#691A31");
//    Color colorLetra = Color.decode("#DEC9A3");
    Color colorLetra = Color.decode("#691A31");
    Color blanco = Color.decode("#FFFFFF");
    Font negrita = new Font("Montserrat ExtraLight", Font.BOLD, 14);

    //public frmMinutarioSalida(String ClaveFuncionario, String nombreFuncionario) {
    public frmMinutarioSalida(int idFuncionario, String nombreFuncionario) {
        initComponents();
        this.setLayout(null);

        //Fondo de combobox
        //UIManager.put("ComboBox.disabledForeground", Color.BLACK);
        //UIManager.put("ComboBox.disabledBackground", Color.white);
        // localizacion de etiquetas
        lblClasificacion.setBounds(10, 15, 80, 25);
        lblClasificacionAsignada.setBounds(100, 15, 800, 25);
        lblSeccion.setBounds(10, 43, 80, 25);
        lblSerie.setBounds(10, 71, 80, 25);
        lblSubserie.setBounds(10, 99, 80, 25);
        lblDestinatario.setBounds(10, 138, 80, 25);
        lblDocumento.setBounds(10, 177, 80, 25);
        lblTipoEnvio.setBounds(260, 177, 80, 25);
        lblTipoDocumentoAsignado.setBounds(580, 177, 150, 25);
        lblTipoEnvioAsignado.setBounds(660, 177, 170, 25);
        lblReferencia.setBounds(10, 216, 80, 25);
        lblAsunto.setBounds(10, 256, 80, 25);

        // localizacion de Cajas de texto
        txtDestinatario.setBounds(100, 138, 800, 25);
        txtAsunto.setBounds(100, 255, 800, 70);
        txtAsunto2.setBounds(100, 255, 800, 70);

        // localizacion de lineas divisoras
        jspDestinatario.setBounds(10, 130, 890, 25);
        jspDocumento.setBounds(10, 169, 890, 25);
        jspReferencia.setBounds(10, 208, 890, 25);
        jspAsunto.setBounds(10, 247, 890, 25);

        // localizacion de Combobox
        cbxSeccion.setBounds(100, 43, 800, 25);
        cbxSerie.setBounds(100, 71, 800, 25);
        cbxSubserie.setBounds(100, 99, 800, 25);
        cbxTipoEnvio.setBounds(350, 177, 150, 25);

        // localizacion de Checkbox
        jrbNotice.setBounds(100, 177, 80, 25);
        jrbOficio.setBounds(180, 177, 80, 25);
        jrbSinReferencia.setBounds(100, 216, 120, 25);
        jrbSeguimiento.setBounds(230, 216, 120, 25);
        jrbRespuesta.setBounds(350, 216, 120, 25);

        // localizacion de Botones
        btnGuardarMinuta.setBounds(680, 340, 100, 25);
        btnCancelar.setBounds(800, 340, 100, 25);

        //Color de etiquetas con informacion del registro
        lblClasificacionAsignada.setForeground(colorLetra);
        lblTipoDocumentoAsignado.setForeground(colorLetra);
        lblTipoEnvioAsignado.setForeground(colorLetra);

        //negrita en etiquetas
        lblClasificacionAsignada.setFont(negrita);
        lblTipoDocumentoAsignado.setFont(negrita);
        lblTipoEnvioAsignado.setFont(negrita);

        //Color en combobox
        cbxSeccion.setBackground(blanco);
        cbxSerie.setBackground(blanco);
        cbxSubserie.setBackground(blanco);
        

        //formato etiqueta Destinatario
        txtDestinatario.setForeground(colorLetra);
        txtDestinatario.setFont(negrita);
        

        
        

        //this.getContentPane().setBackground( Color.decode("#691A31") );
        //--------------------
        final int MAX_LENGTH = 500;
        txtAsunto.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str == null || txtAsunto.getText().length() >= MAX_LENGTH) {
                    return;
                }
                super.insertString(offs, str, a);
            }
        });
        //-------------

//        jrbSre.setVisible(false);
//        jrbOtro.setVisible(false);
//        txtDestinatario.setBackground(colorFondo);
        this.idFuncionario = idFuncionario;
        this.nombreFuncionario = nombreFuncionario;

        ConsultasMinutarioSalida ConsultaMinuta = new ConsultasMinutarioSalida();
        DefaultComboBoxModel modelSecc = new DefaultComboBoxModel(ConsultaMinuta.mostrarSeccion());
        cbxSeccion.setModel(modelSecc);


        ConsultasDestino ConsultasDestino = new ConsultasDestino();
//        lblFuncionario.setForeground(colorLetra);
        lblFuncionario.setText(nombreFuncionario);

        System.out.println("ID funcionario desde el frmMinutario -->" + idFuncionario);

        Calendar c2 = new GregorianCalendar();
        jdcFechaRegistro.setCalendar(c2);
        jdcFechaRegistro.setEnabled(false);

        //        Fecha de registro
        java.util.Date date = this.jdcFechaRegistro.getDate();
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        fechaRegistro = fecha;

        // Autocompletar
        ArrayList<ModeloDestinoArray> listaDestino = ConsultasDestino.mostrarArregloDestino();
        TextAutoCompleter textautocompleter = new TextAutoCompleter(txtDestinatario);
        for (int i = 0; i < listaDestino.size(); i++) {
            textautocompleter.addItem(listaDestino.get(i));
            textautocompleter.setMode(0);
            System.out.println("Prueba - >" + listaDestino.get(0));
        }

        //JOptionPane.showMessageDialog(null, fechaRegistro);
//Permite solo indicar la fecha con el boton
//        JTextFieldDateEditor editor = (JTextFieldDateEditor) jdcFechaRegistro.getDateEditor();
//        editor.setEditable(false);
    }
    
    
        public frmMinutarioSalida(int idFuncionario, String nombreFuncionario, int id_seccion, int id_serie ) {
        initComponents();
        this.setLayout(null);

        lblClasificacion.setBounds(10, 15, 80, 25);
        lblClasificacionAsignada.setBounds(100, 15, 800, 25);
        lblSeccion.setBounds(10, 43, 80, 25);
        lblSerie.setBounds(10, 71, 80, 25);
        lblSubserie.setBounds(10, 99, 80, 25);
        lblDestinatario.setBounds(10, 138, 80, 25);
        lblDocumento.setBounds(10, 177, 80, 25);
        lblTipoEnvio.setBounds(260, 177, 80, 25);
        lblTipoDocumentoAsignado.setBounds(580, 177, 150, 25);
        lblTipoEnvioAsignado.setBounds(660, 177, 170, 25);
        lblReferencia.setBounds(10, 216, 80, 25);
        lblAsunto.setBounds(10, 256, 80, 25);

        // localizacion de Cajas de texto
        txtDestinatario.setBounds(100, 138, 800, 25);
        txtAsunto.setBounds(100, 255, 800, 70);
        txtAsunto2.setBounds(100, 255, 800, 70);

        // localizacion de lineas divisoras
        jspDestinatario.setBounds(10, 130, 890, 25);
        jspDocumento.setBounds(10, 169, 890, 25);
        jspReferencia.setBounds(10, 208, 890, 25);
        jspAsunto.setBounds(10, 247, 890, 25);

        // localizacion de Combobox
        cbxSeccion.setBounds(100, 43, 800, 25);
        cbxSerie.setBounds(100, 71, 800, 25);
        cbxSubserie.setBounds(100, 99, 800, 25);
        cbxTipoEnvio.setBounds(350, 177, 150, 25);

        // localizacion de Checkbox
        jrbNotice.setBounds(100, 177, 80, 25);
        jrbOficio.setBounds(180, 177, 80, 25);
        jrbSinReferencia.setBounds(100, 216, 120, 25);
        jrbSeguimiento.setBounds(230, 216, 120, 25);
        jrbRespuesta.setBounds(350, 216, 120, 25);

        // localizacion de Botones
        btnGuardarMinuta.setBounds(680, 340, 100, 25);
        btnCancelar.setBounds(800, 340, 100, 25);

        //Color de etiquetas con informacion del registro
        lblClasificacionAsignada.setForeground(colorLetra);
        lblTipoDocumentoAsignado.setForeground(colorLetra);
        lblTipoEnvioAsignado.setForeground(colorLetra);

        //negrita en etiquetas
        lblClasificacionAsignada.setFont(negrita);
        lblTipoDocumentoAsignado.setFont(negrita);
        lblTipoEnvioAsignado.setFont(negrita);

        //Color en combobox
        cbxSeccion.setBackground(blanco);
        cbxSerie.setBackground(blanco);
        cbxSubserie.setBackground(blanco);
        

        //formato etiqueta Destinatario
        txtDestinatario.setForeground(colorLetra);
        txtDestinatario.setFont(negrita);
        

        
        

        //this.getContentPane().setBackground( Color.decode("#691A31") );
        //--------------------
        final int MAX_LENGTH = 500;
        txtAsunto.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (str == null || txtAsunto.getText().length() >= MAX_LENGTH) {
                    return;
                }
                super.insertString(offs, str, a);
            }
        });
        //-------------

        this.idFuncionario = idFuncionario;
        this.nombreFuncionario = nombreFuncionario;

        ConsultasMinutarioSalida ConsultaMinuta = new ConsultasMinutarioSalida();
        //DefaultComboBoxModel modelSecc = new DefaultComboBoxModel(ConsultaMinuta.mostrarSeccion(id_seccion));
        DefaultComboBoxModel modelSecc = new DefaultComboBoxModel(ConsultaMinuta.mostrarSeccion());
        cbxSeccion.setModel(modelSecc);
        cbxSeccion.setSelectedIndex(id_seccion);
        
        
        //Se utiliza en favoritos
        
//        DefaultComboBoxModel modelSer = new DefaultComboBoxModel(ConsultaMinuta.mostrarSerie(id_seccion));
//        cbxSerie.setModel(modelSer);
//        cbxSerie.setVisible(true);
//        cbxSerie.setSelectedIndex(id_serie);


        //DefaultComboBoxModel modelDestinatario = new DefaultComboBoxModel(ConsultaMinuta.mostrarDestinatario());

        ConsultasDestino ConsultasDestino = new ConsultasDestino();

        lblFuncionario.setText(nombreFuncionario);
        System.out.println("ID funcionario desde el frmMinutario -->" + idFuncionario);
        Calendar c2 = new GregorianCalendar();
        jdcFechaRegistro.setCalendar(c2);
        jdcFechaRegistro.setEnabled(false);

        //        Fecha de registro
        java.util.Date date = this.jdcFechaRegistro.getDate();
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);
        fechaRegistro = fecha;

        // Autocompletar
        ArrayList<ModeloDestinoArray> listaDestino = ConsultasDestino.mostrarArregloDestino();
        TextAutoCompleter textautocompleter = new TextAutoCompleter(txtDestinatario);
        for (int i = 0; i < listaDestino.size(); i++) {
            textautocompleter.addItem(listaDestino.get(i));
            textautocompleter.setMode(0);
            System.out.println("Prueba - >" + listaDestino.get(0));
        }

    }
    
    
    
    
    
    

    private frmMinutarioSalida() {
        System.out.println("Entro al constructor sin parametros");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgTipoDestinatario = new javax.swing.ButtonGroup();
        bgTipoDocumento = new javax.swing.ButtonGroup();
        bgReferencia = new javax.swing.ButtonGroup();
        lblSeccion = new javax.swing.JLabel();
        lblSerie = new javax.swing.JLabel();
        lblSubserie = new javax.swing.JLabel();
        jspDestinatario = new javax.swing.JSeparator();
        lblDestinatario = new javax.swing.JLabel();
        lblClasificacion = new javax.swing.JLabel();
        jspDocumento = new javax.swing.JSeparator();
        lblDocumento = new javax.swing.JLabel();
        lblTipoEnvio = new javax.swing.JLabel();
        lblReferencia = new javax.swing.JLabel();
        lblAsunto = new javax.swing.JLabel();
        cbxSeccion = new javax.swing.JComboBox<>();
        cbxSerie = new javax.swing.JComboBox<>();
        cbxSubserie = new javax.swing.JComboBox<>();
        cbxTipoEnvio = new javax.swing.JComboBox<>();
        btnGuardarMinuta = new javax.swing.JButton();
        lblClasificacionAsignada = new javax.swing.JLabel();
        jrbNotice = new javax.swing.JRadioButton();
        jrbOficio = new javax.swing.JRadioButton();
        lblTipoDocumentoAsignado = new javax.swing.JLabel();
        lblTipoEnvioAsignado = new javax.swing.JLabel();
        jspReferencia = new javax.swing.JSeparator();
        jrbSeguimiento = new javax.swing.JRadioButton();
        jrbRespuesta = new javax.swing.JRadioButton();
        jrbSinReferencia = new javax.swing.JRadioButton();
        jspAsunto = new javax.swing.JSeparator();
        txtAsunto2 = new javax.swing.JScrollPane();
        txtAsunto = new javax.swing.JTextArea();
        jdcFechaRegistro = new com.toedter.calendar.JDateChooser();
        btnCancelar = new javax.swing.JButton();
        lblFuncionario = new javax.swing.JLabel();
        txtDestinatario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblSeccion.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblSeccion.setForeground(new java.awt.Color(0, 0, 0));
        lblSeccion.setText("Seccion");

        lblSerie.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblSerie.setForeground(new java.awt.Color(0, 0, 0));
        lblSerie.setText("Serie");

        lblSubserie.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblSubserie.setForeground(new java.awt.Color(0, 0, 0));
        lblSubserie.setText("Subserie");

        lblDestinatario.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblDestinatario.setForeground(new java.awt.Color(0, 0, 0));
        lblDestinatario.setText("Destinatario");

        lblClasificacion.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblClasificacion.setForeground(new java.awt.Color(0, 0, 0));
        lblClasificacion.setText("Clasificación");

        lblDocumento.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblDocumento.setForeground(new java.awt.Color(0, 0, 0));
        lblDocumento.setText("Documento");

        lblTipoEnvio.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblTipoEnvio.setForeground(new java.awt.Color(0, 0, 0));
        lblTipoEnvio.setText("Tipo de envio");

        lblReferencia.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblReferencia.setForeground(new java.awt.Color(0, 0, 0));
        lblReferencia.setText("Referencia");

        lblAsunto.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblAsunto.setForeground(new java.awt.Color(0, 0, 0));
        lblAsunto.setText("Asunto");

        cbxSeccion.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxSeccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSeccionItemStateChanged(evt);
            }
        });

        cbxSerie.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxSerie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSerieItemStateChanged(evt);
            }
        });

        cbxSubserie.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxSubserie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSubserieItemStateChanged(evt);
            }
        });

        cbxTipoEnvio.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        btnGuardarMinuta.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        btnGuardarMinuta.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardarMinuta.setText("Guardar");

        lblClasificacionAsignada.setFont(new java.awt.Font("Montserrat ExtraLight", 0, 12)); // NOI18N
        lblClasificacionAsignada.setForeground(new java.awt.Color(0, 0, 0));

        bgTipoDocumento.add(jrbNotice);
        jrbNotice.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        jrbNotice.setForeground(new java.awt.Color(0, 0, 0));
        jrbNotice.setText("Notice");

        bgTipoDocumento.add(jrbOficio);
        jrbOficio.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        jrbOficio.setForeground(new java.awt.Color(0, 0, 0));
        jrbOficio.setText("Oficio");

        lblTipoDocumentoAsignado.setFont(new java.awt.Font("Montserrat ExtraLight", 1, 12)); // NOI18N
        lblTipoDocumentoAsignado.setForeground(new java.awt.Color(0, 0, 0));

        lblTipoEnvioAsignado.setFont(new java.awt.Font("Montserrat ExtraLight", 1, 12)); // NOI18N
        lblTipoEnvioAsignado.setForeground(new java.awt.Color(0, 0, 0));

        bgReferencia.add(jrbSeguimiento);
        jrbSeguimiento.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        jrbSeguimiento.setForeground(new java.awt.Color(0, 0, 0));
        jrbSeguimiento.setText("Seguimiento");

        bgReferencia.add(jrbRespuesta);
        jrbRespuesta.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        jrbRespuesta.setForeground(new java.awt.Color(0, 0, 0));
        jrbRespuesta.setText("Respuesta");

        bgReferencia.add(jrbSinReferencia);
        jrbSinReferencia.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        jrbSinReferencia.setForeground(new java.awt.Color(0, 0, 0));
        jrbSinReferencia.setText("Sin Referencia");

        txtAsunto.setColumns(20);
        txtAsunto.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        txtAsunto.setRows(5);
        txtAsunto2.setViewportView(txtAsunto);

        btnCancelar.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelar.setText("Cancelar");

        lblFuncionario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblFuncionario.setForeground(new java.awt.Color(204, 255, 204));
        lblFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFuncionario.setText(".");

        txtDestinatario.setFont(new java.awt.Font("Montserrat ExtraLight", 0, 12)); // NOI18N
        txtDestinatario.setForeground(new java.awt.Color(204, 255, 204));
        txtDestinatario.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        txtDestinatario.setSelectedTextColor(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jspDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(lblFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblReferencia)
                                    .addGap(26, 26, 26)
                                    .addComponent(jrbSinReferencia)
                                    .addGap(18, 18, 18)
                                    .addComponent(jrbSeguimiento)
                                    .addGap(18, 18, 18)
                                    .addComponent(jrbRespuesta))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addComponent(lblDocumento)
                                    .addGap(32, 32, 32)
                                    .addComponent(jrbNotice)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jrbOficio)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblTipoEnvio)
                                    .addGap(18, 18, 18)
                                    .addComponent(cbxTipoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblTipoDocumentoAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblTipoEnvioAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jspDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(lblDestinatario)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lblClasificacion)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblClasificacionAsignada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblSerie)
                                            .addComponent(lblSeccion)
                                            .addComponent(lblSubserie))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(cbxSerie, 0, 697, Short.MAX_VALUE)
                                                    .addComponent(cbxSeccion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(62, 62, 62)
                                                .addComponent(cbxSubserie, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblAsunto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtAsunto2))
                                    .addComponent(jspAsunto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(129, 129, 129)
                            .addComponent(jdcFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(btnGuardarMinuta, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnCancelar))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jspReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jdcFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblClasificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblClasificacionAsignada, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblSeccion))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbxSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSerie))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxSubserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSubserie))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDestinatario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDocumento)
                    .addComponent(lblTipoDocumentoAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoEnvioAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoEnvio)
                    .addComponent(jrbOficio)
                    .addComponent(jrbNotice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblReferencia)
                    .addComponent(jrbSeguimiento)
                    .addComponent(jrbRespuesta)
                    .addComponent(jrbSinReferencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtAsunto2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardarMinuta)
                            .addComponent(btnCancelar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblAsunto))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxSerieItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSerieItemStateChanged

    }//GEN-LAST:event_cbxSerieItemStateChanged

    private void cbxSubserieItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSubserieItemStateChanged

    }//GEN-LAST:event_cbxSubserieItemStateChanged

    private void cbxSeccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSeccionItemStateChanged

    }//GEN-LAST:event_cbxSeccionItemStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        CtrlHome.frmMinSal = null;
    }//GEN-LAST:event_formWindowClosing

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMinutarioSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMinutarioSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMinutarioSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMinutarioSalida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMinutarioSalida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgReferencia;
    private javax.swing.ButtonGroup bgTipoDestinatario;
    private javax.swing.ButtonGroup bgTipoDocumento;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnGuardarMinuta;
    public javax.swing.JComboBox<String> cbxSeccion;
    public javax.swing.JComboBox<String> cbxSerie;
    public javax.swing.JComboBox<String> cbxSubserie;
    public javax.swing.JComboBox<String> cbxTipoEnvio;
    public com.toedter.calendar.JDateChooser jdcFechaRegistro;
    public javax.swing.JRadioButton jrbNotice;
    public javax.swing.JRadioButton jrbOficio;
    public javax.swing.JRadioButton jrbRespuesta;
    public javax.swing.JRadioButton jrbSeguimiento;
    public javax.swing.JRadioButton jrbSinReferencia;
    private javax.swing.JSeparator jspAsunto;
    private javax.swing.JSeparator jspDestinatario;
    private javax.swing.JSeparator jspDocumento;
    private javax.swing.JSeparator jspReferencia;
    public javax.swing.JLabel lblAsunto;
    public javax.swing.JLabel lblClasificacion;
    public javax.swing.JLabel lblClasificacionAsignada;
    public javax.swing.JLabel lblDestinatario;
    public javax.swing.JLabel lblDocumento;
    public javax.swing.JLabel lblFuncionario;
    public javax.swing.JLabel lblReferencia;
    public javax.swing.JLabel lblSeccion;
    public javax.swing.JLabel lblSerie;
    public javax.swing.JLabel lblSubserie;
    public javax.swing.JLabel lblTipoDocumentoAsignado;
    public javax.swing.JLabel lblTipoEnvio;
    public javax.swing.JLabel lblTipoEnvioAsignado;
    public javax.swing.JTextArea txtAsunto;
    private javax.swing.JScrollPane txtAsunto2;
    public javax.swing.JTextField txtDestinatario;
    // End of variables declaration//GEN-END:variables
}
