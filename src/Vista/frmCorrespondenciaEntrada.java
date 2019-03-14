package Vista;

import Controlador.CtrlHome;
import Formato.FormatoTabla;
import Modelo.Conexion;
import Modelo.ConsultasCorrespondencia;
import Modelo.ModeloRemitenteArray;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class frmCorrespondenciaEntrada extends javax.swing.JFrame {

    //public String idFuncionario;
    public int idFuncionario;
    public String nombreFuncionario;
    public Date fechaEnvio;
    public Date fechaEntrega;

//    Color colorFondo = Color.decode("#691A31");
//    Color colorLetra = Color.decode("#DEC9A3");
    Color colorLetra = Color.decode("#691A31");
    Color blanco = Color.decode("#FFFFFF");
    Font negrita = new Font("Montserrat ExtraLight", Font.BOLD, 14);

    //public frmMinutarioSalida(String ClaveFuncionario, String nombreFuncionario) {
    public frmCorrespondenciaEntrada(int idFuncionario, String nombreFuncionario) {
        initComponents();
        this.setLayout(null);

        // localizacion de Checkbox
//        jrbCorreo.setBounds(110, 10, 80, 25);
//        jrbValija.setBounds(190, 10, 200, 25);
//        
//        jrbCorreo.setVisible(false);
//        jrbValija.setVisible(false);
        // localizacion de etiquetas
        lblDocumento.setBounds(10, 10, 80, 25);
        lblRemitente.setBounds(10, 38, 80, 25);
        lblReferencia.setBounds(10, 66, 80, 25);
        lblInformacionAdicional.setBounds(10, 94, 130, 25);
        lblFechaEnvio.setBounds(10, 122, 130, 25);
        lblFechaEntrega.setBounds(250, 122, 130, 25);
        lblAsignadoA.setBounds(497, 122, 130, 25);
        lblAsunto.setBounds(10, 150, 80, 25);
        lblBucar.setBounds(10, 223, 80, 25);

        // localizacion de Cajas de texto
        txtRemitente.setBounds(110, 38, 680, 25);
        txtReferencia.setBounds(110, 66, 712, 25);
        txtInformacionAdicional.setBounds(110, 94, 712, 25);
        txtAsunto.setBounds(110, 150, 712, 70);
        txtAsunto2.setBounds(110, 150, 712, 70);
        txtBuscar.setBounds(110, 223, 712, 25);

        //Localizacion combofechas
        jdcFechaEnvio.setBounds(110, 122, 115, 25);
        jdcFechaEntrega.setBounds(362, 122, 115, 25);

        // localizacion de Combobox
        cbxTipoCorres.setBounds(110, 10, 150, 25);
        cbxAsignadoA.setBounds(570, 122, 250, 25);

        // localizacion de Botones
        btnNuevoRemi.setBounds(795, 38, 25, 25);
        btnGuardar.setBounds(855, 38, 100, 25);
        btnCancelar.setBounds(855, 66, 100, 25);

        //Color en combobox
        cbxAsignadoA.setBackground(blanco);
        cbxTipoCorres.setBackground(blanco);

        //formato etiqueta Destinatario
        txtRemitente.setForeground(colorLetra);
        txtRemitente.setFont(negrita);

        //SE DEBE QUITAR PARA PODER AGREGAR 
//        btnGuardar.setVisible(false);
//        btnCancelar.setVisible(false);
//        btnNuevoRemi.setVisible(false);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnNuevoRemi.setEnabled(false);

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        // No permite la edicion del combobox de fecha
        JTextFieldDateEditor editor = (JTextFieldDateEditor) jdcFechaEnvio.getDateEditor();
        editor.setEditable(false);
        editor.setBackground(Color.white);
        editor.setHorizontalAlignment(SwingConstants.CENTER);

        JTextFieldDateEditor editor2 = (JTextFieldDateEditor) jdcFechaEntrega.getDateEditor();
        editor2.setEditable(false);
        editor2.setBackground(Color.white);
        editor2.setHorizontalAlignment(SwingConstants.CENTER);

        //-------------
        this.idFuncionario = idFuncionario;
        this.nombreFuncionario = nombreFuncionario;

        ConsultasCorrespondencia conCorres = new ConsultasCorrespondencia();
        DefaultComboBoxModel modelTipoCorres = new DefaultComboBoxModel(conCorres.mostrarTipoCorres());
        cbxTipoCorres.setModel(modelTipoCorres);

        //ConsultasCorrespondencia conCorres = new ConsultasCorrespondencia();
        DefaultComboBoxModel modelAsignado_a = new DefaultComboBoxModel(conCorres.mostrarAsignado_a());
        cbxAsignadoA.setModel(modelAsignado_a);

        // Autocompletar
        ArrayList<ModeloRemitenteArray> listaRemitente = conCorres.mostrarArregloRemitente();
        TextAutoCompleter textautocompleter = new TextAutoCompleter(txtRemitente);
        for (int i = 0; i < listaRemitente.size(); i++) {
            textautocompleter.addItem(listaRemitente.get(i));
            textautocompleter.setMode(0);
            System.out.println("Prueba - >" + listaRemitente.get(0));
        }

        //FORMATO TABLA
        //ubicacion tabla
        //jtCorrespondencia.setBounds(10, 290, 1163, 550);
        jtCorrespondencia1.setBounds(10, 290, 1163, 500);

        // Color a las filas de la tabla (gris y blanco
        FormatoTabla formatoTabla = new FormatoTabla();
        jtCorrespondencia.setDefaultRenderer(Object.class, formatoTabla);

        //Codigo formato tabla
        DefaultTableModel modeloTabla = new DefaultTableModel();
        jtCorrespondencia.setModel(modeloTabla);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        java.sql.Connection con = conn.getConexion();
        String clave = txtBuscar.getText();
        String where = "";
        //if (!"".equals(nombre)) { //Si no es null asigna el numero de campo a la consulta
        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta 
            //where = "WHERE CONCAT (siglas_destino, nombre_destino) LIKE '%" + clave + "%'";
            //where = "WHERE CONCAT (referencia) LIKE '%" + clave + "%'";
            where = "WHERE asunto LIKE '%" + clave + "%'";
        }
        String sql = "SELECT numero, siglas_tipoenvio, asunto, CONCAT ('( ', siglas_remitente, ' ) - ', nombre_remitente) AS remitente, "
                + " referencia, fecha_entrega FROM t_correspondencia "
                + " INNER JOIN t_tipoenvio ON t_correspondencia.documento = t_tipoenvio.id_tipoenvio "
                + " INNER JOIN t_remitente ON t_correspondencia.remitente = t_remitente.id_remitente "
                + where
                + " order by numero DESC";
                        
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            //modeloTabla.addColumn("Numero");
            modeloTabla.addColumn("No.");
            modeloTabla.addColumn("Tipo");
            modeloTabla.addColumn("Asunto");
            modeloTabla.addColumn("Remitente");
            modeloTabla.addColumn("Referencia");
            //modeloTabla.addColumn("Fecha envio");
            modeloTabla.addColumn("Fecha entrega");

            //int[] anchos = {40, 32, 350, 150, 92, 92};
            //int[] anchos = {72, 72, 78, 78, 120, 645, 80};
            int[] anchos = {50, 40, 700, 320, 120, 120};
            for (int i = 0; i < jtCorrespondencia.getColumnCount(); i++) {
                jtCorrespondencia.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Object[] filas = new Object[rsMd.getColumnCount()];
                for (int i = 0; i < rsMd.getColumnCount(); i++) {
                    filas[i] = rs.getObject(i + 1);
                    //   System.out.println("Filas (" + filas[i] + ")");
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

    }

    private frmCorrespondenciaEntrada() {
        System.out.println("Entro al constructor sin parametros");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgTipoDestinatario = new javax.swing.ButtonGroup();
        bgTipoDocumento = new javax.swing.ButtonGroup();
        bgReferencia = new javax.swing.ButtonGroup();
        lblRemitente = new javax.swing.JLabel();
        lblReferencia = new javax.swing.JLabel();
        lblAsignadoA = new javax.swing.JLabel();
        lblInformacionAdicional = new javax.swing.JLabel();
        lblDocumento = new javax.swing.JLabel();
        lblFechaEnvio = new javax.swing.JLabel();
        lblAsunto = new javax.swing.JLabel();
        cbxAsignadoA = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        txtAsunto2 = new javax.swing.JScrollPane();
        txtAsunto = new javax.swing.JTextArea();
        jdcFechaEnvio = new com.toedter.calendar.JDateChooser();
        btnCancelar = new javax.swing.JButton();
        txtRemitente = new javax.swing.JTextField();
        jdcFechaEntrega = new com.toedter.calendar.JDateChooser();
        lblFechaEntrega = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        txtInformacionAdicional = new javax.swing.JTextField();
        btnNuevoRemi = new javax.swing.JButton();
        jtCorrespondencia1 = new javax.swing.JScrollPane();
        jtCorrespondencia = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        lblBucar = new javax.swing.JLabel();
        cbxTipoCorres = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblRemitente.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblRemitente.setForeground(new java.awt.Color(0, 0, 0));
        lblRemitente.setText("Remitente");

        lblReferencia.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblReferencia.setForeground(new java.awt.Color(0, 0, 0));
        lblReferencia.setText("Referencia");

        lblAsignadoA.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblAsignadoA.setForeground(new java.awt.Color(0, 0, 0));
        lblAsignadoA.setText("Asignado a");

        lblInformacionAdicional.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblInformacionAdicional.setForeground(new java.awt.Color(0, 0, 0));
        lblInformacionAdicional.setText("Info. Adicional");

        lblDocumento.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblDocumento.setForeground(new java.awt.Color(0, 0, 0));
        lblDocumento.setText("Documento");

        lblFechaEnvio.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblFechaEnvio.setForeground(new java.awt.Color(0, 0, 0));
        lblFechaEnvio.setText("Fecha de envio");

        lblAsunto.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblAsunto.setForeground(new java.awt.Color(0, 0, 0));
        lblAsunto.setText("Asunto");

        cbxAsignadoA.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxAsignadoA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxAsignadoAItemStateChanged(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardar.setText("Guardar");

        txtAsunto.setColumns(20);
        txtAsunto.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        txtAsunto.setRows(5);
        txtAsunto2.setViewportView(txtAsunto);

        jdcFechaEnvio.setMinimumSize(new java.awt.Dimension(50, 25));
        jdcFechaEnvio.setPreferredSize(new java.awt.Dimension(138, 25));

        btnCancelar.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelar.setText("Cancelar");

        txtRemitente.setFont(new java.awt.Font("Montserrat ExtraLight", 0, 12)); // NOI18N
        txtRemitente.setForeground(new java.awt.Color(204, 255, 204));
        txtRemitente.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        txtRemitente.setSelectedTextColor(new java.awt.Color(102, 102, 102));

        jdcFechaEntrega.setPreferredSize(new java.awt.Dimension(138, 25));

        lblFechaEntrega.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblFechaEntrega.setForeground(new java.awt.Color(0, 0, 0));
        lblFechaEntrega.setText("Fecha de entrega");

        btnNuevoRemi.setText("...");
        btnNuevoRemi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoRemiActionPerformed(evt);
            }
        });

        jtCorrespondencia1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtCorrespondencia1MouseClicked(evt);
            }
        });

        jtCorrespondencia.setAutoCreateRowSorter(true);
        jtCorrespondencia.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        jtCorrespondencia.setModel(new javax.swing.table.DefaultTableModel(
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
        jtCorrespondencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtCorrespondenciaMouseClicked(evt);
            }
        });
        jtCorrespondencia1.setViewportView(jtCorrespondencia);

        lblBucar.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblBucar.setForeground(new java.awt.Color(0, 0, 0));
        lblBucar.setText("Buscar");

        cbxTipoCorres.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxTipoCorres.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoCorresItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAsunto)
                            .addComponent(lblFechaEnvio)
                            .addComponent(lblBucar)))
                    .addComponent(lblInformacionAdicional)
                    .addComponent(lblRemitente)
                    .addComponent(lblReferencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtInformacionAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAsunto2, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtRemitente, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNuevoRemi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jdcFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblFechaEntrega)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdcFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(lblAsignadoA)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxAsignadoA, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCancelar)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(127, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblDocumento)
                .addGap(24, 24, 24)
                .addComponent(cbxTipoCorres, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(940, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtCorrespondencia1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDocumento)
                    .addComponent(cbxTipoCorres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRemitente)
                    .addComponent(txtRemitente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevoRemi)
                    .addComponent(btnGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblReferencia)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInformacionAdicional)
                    .addComponent(txtInformacionAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jdcFechaEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(lblFechaEntrega)
                        .addComponent(lblFechaEnvio)
                        .addComponent(jdcFechaEnvio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxAsignadoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAsignadoA)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAsunto)
                    .addComponent(txtAsunto2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBucar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtCorrespondencia1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(205, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxAsignadoAItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxAsignadoAItemStateChanged

    }//GEN-LAST:event_cbxAsignadoAItemStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        CtrlHome.frmCorrEnt = null;
    }//GEN-LAST:event_formWindowClosing

    private void btnNuevoRemiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoRemiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoRemiActionPerformed

    private void jtCorrespondenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCorrespondenciaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtCorrespondenciaMouseClicked

    private void jtCorrespondencia1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCorrespondencia1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtCorrespondencia1MouseClicked

    private void cbxTipoCorresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoCorresItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoCorresItemStateChanged

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
                new frmCorrespondenciaEntrada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgReferencia;
    private javax.swing.ButtonGroup bgTipoDestinatario;
    private javax.swing.ButtonGroup bgTipoDocumento;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnNuevoRemi;
    public javax.swing.JComboBox<String> cbxAsignadoA;
    public javax.swing.JComboBox<String> cbxTipoCorres;
    public com.toedter.calendar.JDateChooser jdcFechaEntrega;
    public com.toedter.calendar.JDateChooser jdcFechaEnvio;
    public javax.swing.JTable jtCorrespondencia;
    public javax.swing.JScrollPane jtCorrespondencia1;
    public javax.swing.JLabel lblAsignadoA;
    public javax.swing.JLabel lblAsunto;
    public javax.swing.JLabel lblBucar;
    public javax.swing.JLabel lblDocumento;
    public javax.swing.JLabel lblFechaEntrega;
    public javax.swing.JLabel lblFechaEnvio;
    public javax.swing.JLabel lblInformacionAdicional;
    public javax.swing.JLabel lblReferencia;
    public javax.swing.JLabel lblRemitente;
    public javax.swing.JTextArea txtAsunto;
    private javax.swing.JScrollPane txtAsunto2;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JTextField txtInformacionAdicional;
    public javax.swing.JTextField txtReferencia;
    public javax.swing.JTextField txtRemitente;
    // End of variables declaration//GEN-END:variables
}
