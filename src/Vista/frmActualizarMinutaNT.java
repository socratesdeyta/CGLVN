package Vista;

import Controlador.CtrlHome;
import Controlador.CtrlMinuta;
import Formato.FormatoTabla;
import Modelo.Conexion;
import Modelo.ConsultasMinutarioSalida;
import com.toedter.calendar.demo.DemoTable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class frmActualizarMinutaNT extends javax.swing.JFrame {
    
    public frmActualizarMinutaNT() {
        initComponents();
        this.setLayout(null);
        
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        Formato.FormatoTabla formatoTabla = new FormatoTabla();
        
        DefaultTableModel modeloTabla = new DefaultTableModel();
        
        jtMinuta.setModel(modeloTabla);
        jtMinuta.setDefaultRenderer(Object.class, formatoTabla);
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        java.sql.Connection con = conn.getConexion();
        
        String sql = "SELECT numero, fecha_registro, "
                + "t_destino.nombre_destino, "
                //+ "t_tipoenvio.nombre_tipoenvio, asunto "
                 + "asunto "
                + "FROM control_gestion.t_documentosenviados "
                + "LEFT JOIN t_seccion ON t_documentosenviados.seccion=t_seccion.id_seccion "
                + "LEFT JOIN t_serie ON t_documentosenviados.serie=t_serie.id_serie "
                + "LEFT JOIN t_subserie ON t_documentosenviados.subserie=t_subserie.id_subserie "
                + "LEFT JOIN t_destino ON t_documentosenviados.destinatario=t_destino.id_destino "
                + "LEFT JOIN t_funcionarios ON t_documentosenviados.funcionario=t_funcionarios.clave "
                + "LEFT JOIN t_tipoenvio ON t_documentosenviados.envio=t_tipoenvio.id_tipoenvio "
                + "WHERE envio = 1 AND fecha_envio IS NULL "
                + "order by id_documentosenviados asc;";
        
        System.out.println(sql);
        
//        
//                String clave = txtClave.getText();
//        String where = "";
//        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta
//            where = "WHERE clave = 'LVN-" + clave + "'";
//        }
//        
//        String sql = "SELECT numero, fecha_registro, "
//                + "t_destino.nombre_destino, "
//                + "t_tipoenvio.nombre_tipoenvio, asunto "
//                + "FROM control_gestion.t_documentosenviados "
//                + "LEFT JOIN t_seccion ON t_documentosenviados.seccion=t_seccion.id_seccion "
//                + "LEFT JOIN t_serie ON t_documentosenviados.serie=t_serie.id_serie "
//                + "LEFT JOIN t_subserie ON t_documentosenviados.subserie=t_subserie.id_subserie "
//                + "LEFT JOIN t_destino ON t_documentosenviados.destinatario=t_destino.id_destino "
//                + "LEFT JOIN t_funcionarios ON t_documentosenviados.funcionario=t_funcionarios.clave "
//                + "LEFT JOIN t_tipoenvio ON t_documentosenviados.envio=t_tipoenvio.id_tipoenvio "
//                + where
//                + "order by id_documentosenviados asc;";
//        
//        System.out.println(sql);
        
        
        
        
        
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            
            System.out.println("---------Se ejecuta la consulta");
            
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();

            // modeloTabla.addColumn("id_funcionario");
            modeloTabla.addColumn("Numero");
            modeloTabla.addColumn("Fecha Registro");
//            modeloTabla.addColumn("Seccion");
//            modeloTabla.addColumn("Serie");
//            modeloTabla.addColumn("Subserie");
            modeloTabla.addColumn("Destinatario");
            // modeloTabla.addColumn("Registrado por");
//            modeloTabla.addColumn("Tipo envio");
            modeloTabla.addColumn("Asunto");
            //modeloTabla.addColumn("id_area");
//            modeloTabla.addColumn("Usuario");
//            modeloTabla.addColumn("Contrasenia");
//            modeloTabla.addColumn("Privilegio");
            //modeloTabla.addColumn("id_privilegio");

            int[] anchos = {70, 90, 300, 800};
            
            for (int i = 0; i < jtMinuta.getColumnCount(); i++) {
                jtMinuta.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            
            while (rs.next()) {
                
                Object[] filas = new Object[rsMd.getColumnCount()];
                for (int i = 0; i < rsMd.getColumnCount(); i++) {
                    filas[i] = rs.getObject(i + 1);
                    System.out.println("Filas (" + filas[i] + ")");
                }
                modeloTabla.addRow(filas);
            }
            
        } catch (SQLException e) {
            System.err.println(e);
            
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

//        this.idFuncionario = ClaveFuncionario;
//        this.nombreFuncionario = nombreFuncionario;
//        ConsultasMinutarioSalida ConsultaMinuta = new ConsultasMinutarioSalida();
//        DefaultComboBoxModel modelSecc = new DefaultComboBoxModel(ConsultaMinuta.mostrarSeccion());
//        cbxSeccion.setModel(modelSecc);
//        DefaultComboBoxModel modelDestinatario = new DefaultComboBoxModel(ConsultaMinuta.mostrarDestinatario());
//        cbxDestinatario.setModel(modelDestinatario);
//
//        lblFuncionario.setText(nombreFuncionario);
//        System.out.println("ID funcionario desde el frmMinutario -->" + idFuncionario);
//
//        Calendar c2 = new GregorianCalendar();
//        jdcFechaRegistro.setCalendar(c2);
//        jdcFechaRegistro.setEnabled(false);
//
//        //        Fecha de registro
//        java.util.Date date = this.jdcFechaRegistro.getDate();
//        long d = date.getTime();
//        java.sql.Date fecha = new java.sql.Date(d);
//        fechaRegistro = fecha;
        //JOptionPane.showMessageDialog(null, fechaRegistro);
//Permite solo indicar la fecha con el boton
//        JTextFieldDateEditor editor = (JTextFieldDateEditor) jdcFechaRegistro.getDateEditor();
//        editor.setEditable(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgTipoDestinatario = new javax.swing.ButtonGroup();
        bgTipoDocumento = new javax.swing.ButtonGroup();
        bgReferencia = new javax.swing.ButtonGroup();
        lblSeccion = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblDestinatario = new javax.swing.JLabel();
        lblClasificacion = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblDocumento = new javax.swing.JLabel();
        lblTipoEnvio = new javax.swing.JLabel();
        lblReposanbleEnvio = new javax.swing.JLabel();
        lblAsunto = new javax.swing.JLabel();
        cbxSeccion = new javax.swing.JComboBox<>();
        cbxSerie = new javax.swing.JComboBox<>();
        cbxSubserie = new javax.swing.JComboBox<>();
        cbxDestinatario = new javax.swing.JComboBox<>();
        cbxTipoEnvio = new javax.swing.JComboBox<>();
        btnGuardarMinuta = new javax.swing.JButton();
        jrbNotice = new javax.swing.JRadioButton();
        jrbOficio = new javax.swing.JRadioButton();
        lblTipoDocumentoAsignado = new javax.swing.JLabel();
        lblTipoEnvioAsignado = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jdcFechaRegistro = new com.toedter.calendar.JDateChooser();
        btnCancelar = new javax.swing.JButton();
        lblFuncionario = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAsunto = new javax.swing.JTextPane();
        jdcFechaRegistro1 = new com.toedter.calendar.JDateChooser();
        lblFuncionario1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtMinuta = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtClave = new javax.swing.JTextPane();
        lblNumero = new javax.swing.JLabel();
        txtPruebaRespuesta = new javax.swing.JLabel();
        lblReferencia1 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        lblReferencia2 = new javax.swing.JLabel();
        lblSerie1 = new javax.swing.JLabel();
        lblSubserie1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblSeccion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSeccion.setForeground(new java.awt.Color(255, 255, 255));
        lblSeccion.setText("Seccion");

        lblDestinatario.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        lblDestinatario.setForeground(new java.awt.Color(255, 255, 255));
        lblDestinatario.setText("Destinatario");

        lblClasificacion.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        lblClasificacion.setForeground(new java.awt.Color(255, 255, 255));
        lblClasificacion.setText("Clasificación");

        lblDocumento.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        lblDocumento.setForeground(new java.awt.Color(255, 255, 255));
        lblDocumento.setText("Documento");

        lblTipoEnvio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTipoEnvio.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoEnvio.setText("Tipo de envio");

        lblReposanbleEnvio.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        lblReposanbleEnvio.setForeground(new java.awt.Color(204, 0, 51));
        lblReposanbleEnvio.setText("Responsable Envio");

        lblAsunto.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        lblAsunto.setForeground(new java.awt.Color(255, 255, 255));
        lblAsunto.setText("Asunto");

        cbxSeccion.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        cbxSeccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSeccionItemStateChanged(evt);
            }
        });

        cbxSerie.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        cbxSerie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSerieItemStateChanged(evt);
            }
        });

        cbxSubserie.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        cbxSubserie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSubserieItemStateChanged(evt);
            }
        });

        cbxDestinatario.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N

        btnGuardarMinuta.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        btnGuardarMinuta.setText("Guardar");

        bgTipoDocumento.add(jrbNotice);
        jrbNotice.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jrbNotice.setForeground(new java.awt.Color(255, 255, 255));
        jrbNotice.setText("Notice");

        bgTipoDocumento.add(jrbOficio);
        jrbOficio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jrbOficio.setForeground(new java.awt.Color(255, 255, 255));
        jrbOficio.setText("Oficio");

        lblTipoDocumentoAsignado.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        lblTipoDocumentoAsignado.setForeground(new java.awt.Color(204, 255, 204));
        lblTipoDocumentoAsignado.setText(".");

        lblTipoEnvioAsignado.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        lblTipoEnvioAsignado.setForeground(new java.awt.Color(204, 255, 204));
        lblTipoEnvioAsignado.setText(".");

        btnCancelar.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");

        lblFuncionario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblFuncionario.setForeground(new java.awt.Color(204, 255, 204));
        lblFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFuncionario.setText(".");

        txtAsunto.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        jScrollPane2.setViewportView(txtAsunto);

        lblFuncionario1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblFuncionario1.setForeground(new java.awt.Color(204, 255, 204));
        lblFuncionario1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFuncionario1.setText(".");

        jtMinuta.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        jtMinuta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtMinuta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtMinuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMinutaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtMinuta);

        txtClave.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtClave.setForeground(new java.awt.Color(153, 0, 0));
        jScrollPane3.setViewportView(txtClave);

        lblNumero.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(255, 255, 255));
        lblNumero.setText("Numero: ");

        lblReferencia1.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        lblReferencia1.setForeground(new java.awt.Color(255, 255, 255));
        lblReferencia1.setText("Responsable registro");

        lblReferencia2.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        lblReferencia2.setForeground(new java.awt.Color(255, 255, 255));
        lblReferencia2.setText("Fecha registro");

        lblSerie1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSerie1.setForeground(new java.awt.Color(255, 255, 255));
        lblSerie1.setText("Serie");

        lblSubserie1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSubserie1.setForeground(new java.awt.Color(255, 255, 255));
        lblSubserie1.setText("Subserie");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1039, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(654, 654, 654)
                .addComponent(txtPruebaRespuesta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblNumero)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(lblReposanbleEnvio)
                                        .addGap(40, 40, 40)
                                        .addComponent(jdcFechaRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblFuncionario1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(btnGuardarMinuta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCancelar))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblReferencia1)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblTipoDocumentoAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblTipoEnvioAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblReferencia2)
                                                .addGap(29, 29, 29)
                                                .addComponent(jdcFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblDocumento)
                                                .addGap(26, 26, 26)
                                                .addComponent(jrbNotice)
                                                .addGap(18, 18, 18)
                                                .addComponent(jrbOficio)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblTipoEnvio)
                                                .addGap(18, 18, 18)
                                                .addComponent(cbxTipoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblAsunto)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.DEFAULT_SIZE, 1135, Short.MAX_VALUE)
                                .addComponent(jScrollPane1)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblSerie1)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblClasificacion)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(36, 36, 36)
                                                    .addComponent(lblSubserie1))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(lblSeccion)))))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbxSubserie, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbxSerie, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbxSeccion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblDestinatario)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbxDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumero)
                    .addComponent(lblReposanbleEnvio)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcFechaRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarMinuta)
                    .addComponent(lblFuncionario1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSeccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerie1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSubserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSubserie1))
                .addGap(17, 17, 17)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDestinatario)
                    .addComponent(cbxDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPruebaRespuesta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDocumento)
                            .addComponent(jrbNotice)
                            .addComponent(jrbOficio)
                            .addComponent(lblTipoEnvio)
                            .addComponent(cbxTipoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAsunto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblReferencia1)
                            .addComponent(lblFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblReferencia2)
                            .addComponent(jdcFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblTipoDocumentoAsignado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTipoEnvioAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxSerieItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSerieItemStateChanged

    }//GEN-LAST:event_cbxSerieItemStateChanged

    private void cbxSubserieItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSubserieItemStateChanged

    }//GEN-LAST:event_cbxSubserieItemStateChanged

    private void cbxSeccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSeccionItemStateChanged

    }//GEN-LAST:event_cbxSeccionItemStateChanged

    private void jtMinutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtMinutaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtMinutaMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//        CtrlHome.frmActMinNT = null;
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
            java.util.logging.Logger.getLogger(frmActualizarMinutaNT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmActualizarMinutaNT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmActualizarMinutaNT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmActualizarMinutaNT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmActualizarMinutaNT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgReferencia;
    private javax.swing.ButtonGroup bgTipoDestinatario;
    private javax.swing.ButtonGroup bgTipoDocumento;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnGuardarMinuta;
    public javax.swing.JComboBox<String> cbxDestinatario;
    public javax.swing.JComboBox<String> cbxSeccion;
    public javax.swing.JComboBox<String> cbxSerie;
    public javax.swing.JComboBox<String> cbxSubserie;
    public javax.swing.JComboBox<String> cbxTipoEnvio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    public com.toedter.calendar.JDateChooser jdcFechaRegistro;
    public com.toedter.calendar.JDateChooser jdcFechaRegistro1;
    public javax.swing.JRadioButton jrbNotice;
    public javax.swing.JRadioButton jrbOficio;
    public javax.swing.JTable jtMinuta;
    private javax.swing.JLabel lblAsunto;
    private javax.swing.JLabel lblClasificacion;
    private javax.swing.JLabel lblDestinatario;
    private javax.swing.JLabel lblDocumento;
    public javax.swing.JLabel lblFuncionario;
    public javax.swing.JLabel lblFuncionario1;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblReferencia1;
    private javax.swing.JLabel lblReferencia2;
    private javax.swing.JLabel lblReposanbleEnvio;
    private javax.swing.JLabel lblSeccion;
    private javax.swing.JLabel lblSerie1;
    private javax.swing.JLabel lblSubserie1;
    public javax.swing.JLabel lblTipoDocumentoAsignado;
    private javax.swing.JLabel lblTipoEnvio;
    public javax.swing.JLabel lblTipoEnvioAsignado;
    public javax.swing.JTextPane txtAsunto;
    public javax.swing.JTextPane txtClave;
    public javax.swing.JLabel txtPruebaRespuesta;
    // End of variables declaration//GEN-END:variables
}
