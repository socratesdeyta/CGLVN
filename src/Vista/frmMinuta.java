package Vista;

import Controlador.CtrlHome;
import Controlador.CtrlMinuta;
import Modelo.Conexion;
import Modelo.ConsultasMinutarioSalida;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class frmMinuta extends javax.swing.JFrame {


    public frmMinuta() {
        initComponents();
        this.setLayout(null);

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        DefaultTableModel modeloTabla = new DefaultTableModel();

        jtMinuta.setModel(modeloTabla);

        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        java.sql.Connection con = conn.getConexion();

        String clave = txtClave.getText();
        String where = "";
        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta
            where = "WHERE clave = 'LVN-" + clave + "'";
        }

//                String sql = "SELECT clave, CONCAT(nombre, ' ', apellidop, ' ', apellidom) As Nombre, "
//                + "mail_f, nombre_cargo, nombre_area, usuario_f, password_f, nombre_privilegio FROM control_gestion.t_funcionarios "
//                + "INNER JOIN t_cargo ON t_funcionarios.id_cargo=t_cargo.id_cargo "
//                + "INNER JOIN t_area ON t_funcionarios.id_area=t_area.id_area "
//                + "INNER JOIN t_privilegio ON t_funcionarios.id_privilegio=t_privilegio.id_privilegio "
//                + where
//                + "order by clave ASC";  


        String sql = "SELECT numero, fecha_registro, "
                //String sql = "SELECT numero, fecha_registro, codigo_seccion, codigo_serie, codigo_subserie, "
                
                //+ "CONCAT(t_seccion.codigo_seccion, ' ', t_seccion.seccion) As Seccion, "
                //+ "CONCAT(t_serie.codigo_serie, ' ', t_serie.serie) As Serie, "
                //+ "CONCAT(t_subserie.codigo_subserie, ' ', t_subserie.subserie) As Subserie, "
                //+ "CONCAT(t_destino.siglas_destino, ' ', t_destino.nombre_destino) As Destinatario, "
                + "t_destino.nombre_destino, "
                //+ "CONCAT(t_funcionarios.nombre, ' ', t_funcionarios.apellidop, ' ', t_funcionarios.apellidom) As Responsable, "
                
                //+ "t_funcionarios.usuario_f, t_tipoenvio.nombre_tipoenvio, asunto "
                + "t_tipoenvio.nombre_tipoenvio, asunto "
                + "FROM control_gestion.t_documentosenviados "
                + "LEFT JOIN t_seccion ON t_documentosenviados.seccion=t_seccion.id_seccion "
                + "LEFT JOIN t_serie ON t_documentosenviados.serie=t_serie.id_serie "
                + "LEFT JOIN t_subserie ON t_documentosenviados.subserie=t_subserie.id_subserie "
                + "LEFT JOIN t_destino ON t_documentosenviados.destinatario=t_destino.id_destino "
                + "LEFT JOIN t_funcionarios ON t_documentosenviados.funcionario=t_funcionarios.clave "
                + "LEFT JOIN t_tipoenvio ON t_documentosenviados.envio=t_tipoenvio.id_tipoenvio "
                + where
                + "order by id_documentosenviados asc;";

        System.out.println(sql);

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
            modeloTabla.addColumn("Tipo envio");
            modeloTabla.addColumn("Asunto");
            //modeloTabla.addColumn("id_area");
//            modeloTabla.addColumn("Usuario");
//            modeloTabla.addColumn("Contrasenia");
//            modeloTabla.addColumn("Privilegio");
            //modeloTabla.addColumn("id_privilegio");

            int[] anchos = {10, 10, 10, 10, 10};

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
        lblSerie = new javax.swing.JLabel();
        lblSubserie = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblDestinatario = new javax.swing.JLabel();
        lblClasificacion = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblDocumento = new javax.swing.JLabel();
        lblTipoEnvio = new javax.swing.JLabel();
        lblReferencia = new javax.swing.JLabel();
        lblAsunto = new javax.swing.JLabel();
        cbxSeccion = new javax.swing.JComboBox<>();
        cbxSerie = new javax.swing.JComboBox<>();
        cbxSubserie = new javax.swing.JComboBox<>();
        cbxDestinatario = new javax.swing.JComboBox<>();
        cbxTipoEnvio = new javax.swing.JComboBox<>();
        btnGuardarMinuta = new javax.swing.JButton();
        lblClasificacionAsignada = new javax.swing.JLabel();
        lblDestinatarioAsignado = new javax.swing.JLabel();
        jrbSre = new javax.swing.JRadioButton();
        jrbOtro = new javax.swing.JRadioButton();
        jrbNotice = new javax.swing.JRadioButton();
        jrbOficio = new javax.swing.JRadioButton();
        lblTipoDocumentoAsignado = new javax.swing.JLabel();
        lblTipoEnvioAsignado = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jrbSeguimiento = new javax.swing.JRadioButton();
        jrbRespuesta = new javax.swing.JRadioButton();
        jrbSinReferencia = new javax.swing.JRadioButton();
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
        btnBuscarDes = new javax.swing.JButton();

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

        lblSerie.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSerie.setForeground(new java.awt.Color(255, 255, 255));
        lblSerie.setText("Serie");

        lblSubserie.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSubserie.setForeground(new java.awt.Color(255, 255, 255));
        lblSubserie.setText("Subserie");

        lblDestinatario.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblDestinatario.setForeground(new java.awt.Color(255, 255, 255));
        lblDestinatario.setText("Destinatario");

        lblClasificacion.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        lblClasificacion.setForeground(new java.awt.Color(255, 255, 255));
        lblClasificacion.setText("Clasificación");

        lblDocumento.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        lblDocumento.setForeground(new java.awt.Color(255, 255, 255));
        lblDocumento.setText("Documento");

        lblTipoEnvio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTipoEnvio.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoEnvio.setText("Tipo de envio");

        lblReferencia.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblReferencia.setForeground(new java.awt.Color(255, 255, 255));
        lblReferencia.setText("Referencia");

        lblAsunto.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblAsunto.setForeground(new java.awt.Color(255, 255, 255));
        lblAsunto.setText("Asunto");

        cbxSeccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSeccionItemStateChanged(evt);
            }
        });

        cbxSerie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSerieItemStateChanged(evt);
            }
        });

        cbxSubserie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSubserieItemStateChanged(evt);
            }
        });

        btnGuardarMinuta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnGuardarMinuta.setText("Guardar");

        lblClasificacionAsignada.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        lblClasificacionAsignada.setForeground(new java.awt.Color(204, 255, 204));

        lblDestinatarioAsignado.setFont(new java.awt.Font("Arial", 3, 16)); // NOI18N
        lblDestinatarioAsignado.setForeground(new java.awt.Color(204, 255, 204));
        lblDestinatarioAsignado.setText(".");

        jrbSre.setBackground(java.awt.SystemColor.controlShadow);
        bgTipoDestinatario.add(jrbSre);
        jrbSre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jrbSre.setForeground(new java.awt.Color(255, 255, 255));
        jrbSre.setText("SRE");

        jrbOtro.setBackground(java.awt.SystemColor.controlShadow);
        bgTipoDestinatario.add(jrbOtro);
        jrbOtro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jrbOtro.setForeground(new java.awt.Color(255, 255, 255));
        jrbOtro.setText("Otro");

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

        bgReferencia.add(jrbSeguimiento);
        jrbSeguimiento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jrbSeguimiento.setText("Seguimiento");

        bgReferencia.add(jrbRespuesta);
        jrbRespuesta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jrbRespuesta.setText("Respuesta");

        bgReferencia.add(jrbSinReferencia);
        jrbSinReferencia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jrbSinReferencia.setText("Sin Referencia");

        btnCancelar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");

        lblFuncionario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblFuncionario.setForeground(new java.awt.Color(204, 255, 204));
        lblFuncionario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFuncionario.setText(".");

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
        jtMinuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtMinutaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtMinuta);

        txtClave.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtClave.setForeground(new java.awt.Color(153, 0, 0));
        jScrollPane3.setViewportView(txtClave);

        lblNumero.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(255, 255, 255));
        lblNumero.setText("Numero: ");

        btnBuscarDes.setText("Buscar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1039, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(lblNumero)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26))
                                            .addComponent(jdcFechaRegistro1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(53, 53, 53)
                                        .addComponent(txtPruebaRespuesta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(lblDocumento)
                                                    .addGap(18, 18, 18)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(jrbNotice)
                                                            .addGap(7, 7, 7)
                                                            .addComponent(jrbOficio)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(lblTipoEnvio)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(cbxTipoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(lblTipoDocumentoAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(99, 99, 99)
                                                            .addComponent(lblTipoEnvioAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblReferencia)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(109, 109, 109)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jrbSinReferencia)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jrbSeguimiento)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jrbRespuesta))
                                                    .addComponent(lblFuncionario1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addComponent(jdcFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblAsunto)
                                        .addGap(561, 561, 561))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2)
                                        .addGap(21, 21, 21)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblClasificacion)
                                .addGap(19, 19, 19)
                                .addComponent(lblClasificacionAsignada, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1039, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(lblDestinatario)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDestinatarioAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jrbOtro)
                                        .addGap(18, 18, 18)
                                        .addComponent(jrbSre)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbxDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnBuscarDes))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblSerie)
                                    .addComponent(lblSeccion)
                                    .addComponent(lblSubserie))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbxSerie, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxSeccion, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxSubserie, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnGuardarMinuta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCancelar)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClasificacionAsignada, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblSeccion)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSubserie)
                            .addComponent(cbxSubserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDestinatarioAsignado)
                            .addComponent(lblDestinatario))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrbOtro)
                            .addComponent(jrbSre)
                            .addComponent(cbxDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarDes)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardarMinuta))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSerie)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar)))))
                .addGap(7, 7, 7)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipoEnvioAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDocumento)
                                .addComponent(lblTipoDocumentoAsignado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblAsunto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrbNotice)
                            .addComponent(jrbOficio)
                            .addComponent(lblTipoEnvio)
                            .addComponent(cbxTipoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(lblReferencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrbSinReferencia)
                            .addComponent(jrbSeguimiento)
                            .addComponent(jrbRespuesta))
                        .addGap(14, 14, 14)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFuncionario))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcFechaRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFuncionario1)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(lblNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPruebaRespuesta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
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
        CtrlHome.frmMin = null;
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
            java.util.logging.Logger.getLogger(frmMinuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMinuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMinuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMinuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new frmMinuta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgReferencia;
    private javax.swing.ButtonGroup bgTipoDestinatario;
    private javax.swing.ButtonGroup bgTipoDocumento;
    public javax.swing.JButton btnBuscarDes;
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
    public com.toedter.calendar.JDateChooser jdcFechaRegistro;
    public com.toedter.calendar.JDateChooser jdcFechaRegistro1;
    public javax.swing.JRadioButton jrbNotice;
    public javax.swing.JRadioButton jrbOficio;
    public javax.swing.JRadioButton jrbOtro;
    public javax.swing.JRadioButton jrbRespuesta;
    public javax.swing.JRadioButton jrbSeguimiento;
    public javax.swing.JRadioButton jrbSinReferencia;
    public javax.swing.JRadioButton jrbSre;
    public javax.swing.JTable jtMinuta;
    private javax.swing.JLabel lblAsunto;
    private javax.swing.JLabel lblClasificacion;
    public javax.swing.JLabel lblClasificacionAsignada;
    private javax.swing.JLabel lblDestinatario;
    public javax.swing.JLabel lblDestinatarioAsignado;
    private javax.swing.JLabel lblDocumento;
    public javax.swing.JLabel lblFuncionario;
    public javax.swing.JLabel lblFuncionario1;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblReferencia;
    private javax.swing.JLabel lblSeccion;
    private javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblSubserie;
    public javax.swing.JLabel lblTipoDocumentoAsignado;
    private javax.swing.JLabel lblTipoEnvio;
    public javax.swing.JLabel lblTipoEnvioAsignado;
    public javax.swing.JTextPane txtAsunto;
    public javax.swing.JTextPane txtClave;
    public javax.swing.JLabel txtPruebaRespuesta;
    // End of variables declaration//GEN-END:variables
}
