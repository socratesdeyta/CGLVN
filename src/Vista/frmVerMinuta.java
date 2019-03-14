package Vista;

import Atxy2k.CustomTextField.RestrictedTextField;
import Controlador.CtrlHome;
import Formato.FormatoTablaBitacora;
import Modelo.Conexion;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class frmVerMinuta extends javax.swing.JFrame {

    public frmVerMinuta() {
        initComponents();
        this.setLayout(null);

        Color colorLetra = Color.decode("#691A31");
        Color blanco = Color.decode("#FFFFFF");
        Font negrita = new Font("Montserrat Light", Font.BOLD, 14);

//        Calendar c2 = new GregorianCalendar();
//        jdcFechaEnvio.setCalendar(c2);
        //jdcFechaEnvio.setEditable(false);

        // No permite la ediicion del combobox de fecha
//        JTextFieldDateEditor editor = (JTextFieldDateEditor) jdcFechaEnvio.getDateEditor();
//        editor.setEditable(false);
//        editor.setBackground(Color.yellow);
//        editor.setHorizontalAlignment(SwingConstants.CENTER);

        btnNuevo.setBounds(650, 15, 125, 25);
        btnModificar.setBounds(650, 50, 125, 25);
        btnEliminar.setBounds(650, 85, 125, 25);
        btnLimpiar.setBounds(650, 85, 125, 25);
        btnGuardar.setBounds(650, 15, 125, 25);
        btnCancelar.setBounds(650, 50, 125, 25);
        btnGuardarMod.setBounds(650, 15, 125, 25);
        btnCancelarMod.setBounds(650, 50, 125, 25);

        //Imagenes en botones
        btnNuevo.setIcon(new ImageIcon("src\\img\\nuevo.png"));
        btnModificar.setIcon(new ImageIcon("src\\img\\editar.png"));
        btnEliminar.setIcon(new ImageIcon("src\\img\\eliminar.png"));
        btnLimpiar.setIcon(new ImageIcon("src\\img\\limpiar.png"));

        btnGuardar.setIcon(new ImageIcon("src\\img\\guardar.png"));
        btnCancelar.setIcon(new ImageIcon("src\\img\\cancelar.png"));
        btnGuardarMod.setIcon(new ImageIcon("src\\img\\guardar.png"));
        btnCancelarMod.setIcon(new ImageIcon("src\\img\\cancelar.png"));

        //Ubicacion etiquetas
        lblBuscar.setBounds(10, 15, 80, 25);    //posicion_horizontal, posicion_vertical, largo, ancho
        lblBuscarAsunto.setBounds(10, 15, 100, 25);
        lblNumero.setBounds(10, 43, 80, 25);
        lblFecha.setBounds(180, 43, 80, 25);   //+28
        lblDestinatario.setBounds(295, 43, 80, 25);   //+28
        lblSeccion.setBounds(10, 71, 80, 25);   //+28
        lblSerie.setBounds(10, 99, 800, 25);   //+28
        lblSubserie.setBounds(10, 127, 80, 25);   //+28
        lblFuncionario.setBounds(10, 155, 80, 25);   //+28
        lblTipoDocumento.setBounds(316, 155, 120, 25);   //+28
        lblTipoEnvio.setBounds(550, 155, 80, 25);   //+28

        //lblResponsable.setBounds(800, 71, 183, 25);   //+28

        
        lblAsunto.setBounds(10, 183, 80, 25);   //+28
        lblResponsable.setBounds(10, 260, 183, 25);   //+28
        lblFechaEnvio.setBounds(422, 260, 120, 25);   //+28

        //Formato Celdas
        txtBuscar.setBounds(95, 15, 40, 25);
        txtBuscarAsunto.setBounds(95, 15, 686, 25);
        txtNumero.setBounds(95, 43, 80, 25);
        txtFecha.setBounds(222, 43, 68, 25);
        txtFuncionario.setBounds(95, 155, 210, 25);
        //txtResponsable.setBounds(938, 71, 220, 25);
        //txtFechaEnvio.setBounds(938, 99, 68, 25);
        txtAsunto.setBounds(95, 183, 686, 75);
        txtResponsable.setBounds(144, 260, 220, 25);
        txtFechaEnvio.setBounds(518, 260, 68, 25);
        // formato celda LVN
        txtNumero.setForeground(colorLetra);
        txtNumero.setFont(negrita);

        //txtAsunto.setBounds(88, 183, 650, 75);
        //Formato Combobox     
        //jdcFechaRegistro.setBounds(208, 43, 68, 25);
        cbxDestinatario.setBounds(375, 43, 403, 25);
        cbxSeccion.setBounds(95, 71, 683, 25);
        cbxSerie.setBounds(95, 99, 683, 25);
        cbxSubserie.setBounds(95, 127, 683, 25);
        //cbxFuncionario.setBounds(88, 155, 220, 25);
        
        cbxTipoDocumento.setBounds(438, 155, 100, 25);
        cbxTipoEnvio.setBounds(634, 155, 145, 25);
        
        //jspEnvio.setBounds(10, 263, 770, 1);
        
        jtVerMin1.setBounds(10, 290, 1163, 500);
        //jtVerMin.setBounds(10, 288, 1250, 500);
        
        jblLogo.setBounds(830, 71, 307, 144);
        

        
        // localizacion de lineas divisoras
        // jSeparator2.setBounds(120, 130, 890, 25);

        //Formato Combobox Data   
        //jdcFechaEnvio.setBounds(752, 183, 150, 25);

        //Formato boton 
        //btnAsignarFecha.setBounds(752, 211, 150, 25);

        //Color de los combobox
        //UIManager.put("JTextField.disabledBackground", new Color(244, 246, 246));
        UIManager.put("JDateChooser.disabledBackground", Color.WHITE);
        UIManager.put("JTextFieldDateEditor.disabledBackground", Color.WHITE);
        UIManager.put("JTextField.disabledBackground", Color.WHITE);
        UIManager.put("ComboBox.disabledForeground", Color.BLACK);
        UIManager.put("ComboBox.disabledBackground", Color.white);

// caja de textos no editables
        txtNumero.setEditable(false);
        txtFecha.setEditable(false);
        txtFuncionario.setEditable(false);
        txtAsunto2.setEditable(false);

// Combobox no editables
        cbxDestinatario.setEnabled(false);
        cbxSeccion.setEnabled(false);
        cbxSerie.setEnabled(false);
        cbxSubserie.setEnabled(false);
        cbxTipoDocumento.setEnabled(false);
        cbxTipoEnvio.setEnabled(false);

// data fecha envio, NO visible
        //jdcFechaEnvio.setVisible(false);

// boton data fecha envio, NO visible
        //btnAsignarFecha.setVisible(false);

        lblBuscar.setVisible(false); 
        txtBuscar.setVisible(false);
        
        btnNuevo.setVisible(false);
        btnGuardar.setVisible(false);
        btnCancelar.setVisible(false);
        btnModificar.setVisible(false);
        btnEliminar.setVisible(false);
        btnLimpiar.setVisible(false);
        btnGuardarMod.setVisible(false);
        btnCancelarMod.setVisible(false);

        //txtBuscar solo puede tener 4 elementos
        RestrictedTextField restricted = new RestrictedTextField(txtBuscar);
        restricted.setLimit(4);
        //restricted.setOnlyText(true);

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        // Color a las filas de la tabla (gris y blanco
        FormatoTablaBitacora formatoTablaBitacora = new FormatoTablaBitacora();
        jtVerMin.setDefaultRenderer(Object.class, formatoTablaBitacora);

        //Formato a tabla
        DefaultTableModel modeloTabla = new DefaultTableModel();
        jtVerMin.setModel(modeloTabla);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        java.sql.Connection con = conn.getConexion();
        
        
//        String clave = txtBuscar.getText();
//        String buscarLVN = "";
//        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta 
//            // buscarLVN = "AND numero LIKE 'LVN-"+clave+"%'";
//            buscarLVN = "WHERE numero LIKE 'LVN-" + clave + "%'";
//        }
        
        String clave = txtBuscarAsunto.getText();
        String buscar = "";
        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta 
            // buscarLVN = "AND numero LIKE 'LVN-"+clave+"%'";
            buscar = "WHERE asunto LIKE '%" + clave + "%'";
        }
        
                String sql = "SELECT numero, fecha_registro, IFNULL(t_subserie.codigo_subserie, t_serie.codigo_serie) As Clasificacion, "
                + "nombre_tipodocumento, nombre_tipoenvio, asunto, fecha_envio "
                //+ "asunto "
                + "FROM control_gestion.t_documentosenviados "
                //+ "LEFT JOIN t_seccion ON t_documentosenviados.seccion=t_seccion.id_seccion "
                + "LEFT JOIN t_serie ON t_documentosenviados.serie=t_serie.id_serie "
                + "LEFT JOIN t_subserie ON t_documentosenviados.subserie=t_subserie.id_subserie "
                + "LEFT JOIN t_destino ON t_documentosenviados.destinatario=t_destino.id_destino "
                + "LEFT JOIN t_funcionarios ON t_documentosenviados.funcionario=t_funcionarios.clave "
                + "LEFT JOIN t_tipoenvio ON t_tipoenvio.id_tipoenvio = t_documentosenviados.envio "
                + "left JOIN t_tipodocumento ON t_tipodocumento.id_tipodocumento = t_tipoenvio.id_tipodocumento "
                // + "WHERE envio > 1 AND fecha_envio IS NULL "
                //+ buscarLVN
                + buscar
                + "order by numero desc;";
        
        
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            modeloTabla.addColumn("Numero");
            modeloTabla.addColumn("Fecha");
            modeloTabla.addColumn("Clasificacion");
            modeloTabla.addColumn("Documento");
            modeloTabla.addColumn("Envio");
            modeloTabla.addColumn("Asunto");
            modeloTabla.addColumn("Fecha Envio");            
            //modeloTabla.addColumn("Estado");

            //int[] anchos = {72, 72, 78, 122, 720, 80};
            int[] anchos = {72, 72, 78, 78, 120, 645, 80};
            for (int i = 0; i < jtVerMin.getColumnCount(); i++) {
                jtVerMin.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            }
            while (rs.next()) {
                Object[] filas = new Object[rsMd.getColumnCount()];
                for (int i = 0; i < rsMd.getColumnCount(); i++) {
                    filas[i] = rs.getObject(i + 1);
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

    //---------------
    public static class MyOFocusTraversalPolicy extends FocusTraversalPolicy {

        Vector<Component> order;

        public MyOFocusTraversalPolicy(Vector<Component> order) {
            this.order = new Vector<Component>(order.size());
            this.order.addAll(order);
        }

        @Override
        public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
            int idx = (order.indexOf(aComponent) + 1) % order.size();
            return order.get(idx);
        }

        @Override
        public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
            int idx = order.indexOf(aComponent) - 1;
            if (idx < 0) {
                idx = order.size() - 1;
            }
            return order.get(idx);
        }

        @Override
        public Component getFirstComponent(Container cntnr) {
            return order.get(0);
        }

        @Override
        public Component getLastComponent(Container cntnr) {
            return order.lastElement();
        }

        @Override
        public Component getDefaultComponent(Container cntnr) {
            return order.get(0);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNumero = new javax.swing.JLabel();
        lblSeccion = new javax.swing.JLabel();
        lblAsunto = new javax.swing.JLabel();
        cbxSerie = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jtVerMin1 = new javax.swing.JScrollPane();
        jtVerMin = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGuardarMod = new javax.swing.JButton();
        btnCancelarMod = new javax.swing.JButton();
        lblBuscar = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblDestinatario = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        txtFuncionario = new javax.swing.JTextField();
        lblSerie = new javax.swing.JLabel();
        lblSubserie = new javax.swing.JLabel();
        cbxSeccion = new javax.swing.JComboBox<>();
        cbxDestinatario = new javax.swing.JComboBox<>();
        lblFuncionario = new javax.swing.JLabel();
        cbxSubserie = new javax.swing.JComboBox<>();
        lblTipoEnvio = new javax.swing.JLabel();
        cbxTipoEnvio = new javax.swing.JComboBox<>();
        txtFecha = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        txtAsunto = new javax.swing.JScrollPane();
        txtAsunto2 = new javax.swing.JTextPane();
        lblResponsable = new javax.swing.JLabel();
        txtResponsable = new javax.swing.JTextField();
        lblFechaEnvio = new javax.swing.JLabel();
        txtFechaEnvio = new javax.swing.JTextField();
        jblLogo = new javax.swing.JLabel();
        lblTipoDocumento = new javax.swing.JLabel();
        cbxTipoDocumento = new javax.swing.JComboBox<>();
        txtBuscarAsunto = new javax.swing.JTextField();
        lblBuscarAsunto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Funcionarios");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblNumero.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblNumero.setText("Numero:");

        lblSeccion.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblSeccion.setText("Sección:");

        lblAsunto.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblAsunto.setText("Asunto:");

        cbxSerie.setBackground(new java.awt.Color(255, 255, 255));
        cbxSerie.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxSerie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSerieItemStateChanged(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setPreferredSize(new java.awt.Dimension(81, 23));

        btnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setPreferredSize(new java.awt.Dimension(81, 23));

        btnLimpiar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setPreferredSize(new java.awt.Dimension(81, 23));

        jtVerMin1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtVerMin1MouseClicked(evt);
            }
        });

        jtVerMin.setAutoCreateRowSorter(true);
        jtVerMin.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        jtVerMin.setModel(new javax.swing.table.DefaultTableModel(
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
        jtVerMin.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtVerMin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtVerMinMouseClicked(evt);
            }
        });
        jtVerMin1.setViewportView(jtVerMin);

        btnNuevo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setPreferredSize(new java.awt.Dimension(81, 23));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(81, 23));

        btnGuardarMod.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnGuardarMod.setText("Guardar.");

        btnCancelarMod.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelarMod.setText("Cancelar.");
        btnCancelarMod.setPreferredSize(new java.awt.Dimension(81, 23));

        lblBuscar.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblBuscar.setText("Buscar: LVN-");

        lblFecha.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblFecha.setText("Fecha:");

        lblDestinatario.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblDestinatario.setText("Destinatario:");

        txtFuncionario.setBackground(new java.awt.Color(255, 255, 255));
        txtFuncionario.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        lblSerie.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblSerie.setText("Serie:");

        lblSubserie.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblSubserie.setText("Subserie:");

        cbxSeccion.setBackground(new java.awt.Color(255, 255, 255));
        cbxSeccion.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxSeccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSeccionItemStateChanged(evt);
            }
        });
        cbxSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSeccionActionPerformed(evt);
            }
        });

        cbxDestinatario.setBackground(new java.awt.Color(255, 255, 255));
        cbxDestinatario.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxDestinatario.setForeground(new java.awt.Color(255, 255, 255));
        cbxDestinatario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDestinatarioItemStateChanged(evt);
            }
        });
        cbxDestinatario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDestinatarioActionPerformed(evt);
            }
        });

        lblFuncionario.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblFuncionario.setText("Funcionario:");

        cbxSubserie.setBackground(new java.awt.Color(255, 255, 255));
        cbxSubserie.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxSubserie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSubserieItemStateChanged(evt);
            }
        });

        lblTipoEnvio.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblTipoEnvio.setText("Tipo de envio:");

        cbxTipoEnvio.setBackground(new java.awt.Color(255, 255, 255));
        cbxTipoEnvio.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxTipoEnvio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoEnvioItemStateChanged(evt);
            }
        });

        txtFecha.setBackground(new java.awt.Color(255, 255, 255));
        txtFecha.setFont(new java.awt.Font("Montserrat ExtraLight", 0, 12)); // NOI18N

        txtNumero.setBackground(new java.awt.Color(255, 255, 255));

        txtAsunto2.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        txtAsunto.setViewportView(txtAsunto2);

        lblResponsable.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblResponsable.setText("Responsable de envio:");

        txtResponsable.setBackground(new java.awt.Color(255, 255, 255));
        txtResponsable.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        lblFechaEnvio.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblFechaEnvio.setText("Fecha de envio:");

        txtFechaEnvio.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaEnvio.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        jblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sreHorizontal.png"))); // NOI18N

        lblTipoDocumento.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblTipoDocumento.setText("Tipo de documento:");

        cbxTipoDocumento.setBackground(new java.awt.Color(255, 255, 255));
        cbxTipoDocumento.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxTipoDocumento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoDocumentoItemStateChanged(evt);
            }
        });

        lblBuscarAsunto.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblBuscarAsunto.setText("Buscar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtVerMin1, javax.swing.GroupLayout.PREFERRED_SIZE, 1168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAsunto))
                                .addGap(18, 18, 18)
                                .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(172, 172, 172)
                                .addComponent(txtFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jblLogo))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(107, 107, 107)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(120, 120, 120)
                                            .addComponent(lblFecha)
                                            .addGap(4, 4, 4)
                                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblDestinatario)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbxDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(cbxSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxSubserie, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblFuncionario)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblTipoDocumento)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblTipoEnvio)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbxTipoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNumero)
                                    .addComponent(lblSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSerie)
                                    .addComponent(lblSubserie, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBuscarAsunto)
                        .addGap(24, 24, 24)
                        .addComponent(txtBuscarAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardarMod, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarMod, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarMod)
                    .addComponent(btnCancelarMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscarAsunto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumero)
                    .addComponent(lblFecha)
                    .addComponent(lblDestinatario)
                    .addComponent(cbxDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSeccion)
                            .addComponent(cbxSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSerie)
                            .addComponent(cbxSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSubserie)
                            .addComponent(cbxSubserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFuncionario)
                            .addComponent(lblTipoEnvio)
                            .addComponent(cbxTipoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTipoDocumento)
                            .addComponent(cbxTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAsunto)))
                    .addComponent(jblLogo))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblResponsable)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFechaEnvio)
                        .addComponent(txtFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jtVerMin1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("ActualizarMinutaOficios");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtVerMin1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtVerMin1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtVerMin1MouseClicked

    private void jtVerMinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtVerMinMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtVerMinMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        CtrlHome.frmVerMin = null;
        //  frmInicio.fun = null;
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void cbxSerieItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSerieItemStateChanged
//        if (evt.getStateChange() == ItemEvent.SELECTED) {
//            // Area area = (Area) cbxSubtipoDestino.getSelectedItem();
//            ModeloSubtipoDestino SubtipoDestino = (ModeloSubtipoDestino) cbxSerie.getSelectedItem();
//        }
    }//GEN-LAST:event_cbxSerieItemStateChanged

    private void cbxSeccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSeccionItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSeccionItemStateChanged

    private void cbxSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSeccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSeccionActionPerformed

    private void cbxDestinatarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDestinatarioItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDestinatarioItemStateChanged

    private void cbxDestinatarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDestinatarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDestinatarioActionPerformed

    private void cbxSubserieItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSubserieItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSubserieItemStateChanged

    private void cbxTipoEnvioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoEnvioItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoEnvioItemStateChanged

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cbxTipoDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDocumentoItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmVerMinuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVerMinuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVerMinuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVerMinuta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new frmVerMinuta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnCancelarMod;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnGuardarMod;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnNuevo;
    public javax.swing.JComboBox<String> cbxDestinatario;
    public javax.swing.JComboBox<String> cbxSeccion;
    public javax.swing.JComboBox<String> cbxSerie;
    public javax.swing.JComboBox<String> cbxSubserie;
    public javax.swing.JComboBox<String> cbxTipoDocumento;
    public javax.swing.JComboBox<String> cbxTipoEnvio;
    public javax.swing.JLabel jblLogo;
    public javax.swing.JTable jtVerMin;
    public javax.swing.JScrollPane jtVerMin1;
    public javax.swing.JLabel lblAsunto;
    public javax.swing.JLabel lblBuscar;
    public javax.swing.JLabel lblBuscarAsunto;
    public javax.swing.JLabel lblDestinatario;
    public javax.swing.JLabel lblFecha;
    public javax.swing.JLabel lblFechaEnvio;
    public javax.swing.JLabel lblFuncionario;
    public javax.swing.JLabel lblNumero;
    public javax.swing.JLabel lblResponsable;
    public javax.swing.JLabel lblSeccion;
    public javax.swing.JLabel lblSerie;
    public javax.swing.JLabel lblSubserie;
    public javax.swing.JLabel lblTipoDocumento;
    public javax.swing.JLabel lblTipoEnvio;
    public javax.swing.JScrollPane txtAsunto;
    public javax.swing.JTextPane txtAsunto2;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JTextField txtBuscarAsunto;
    public javax.swing.JTextField txtFecha;
    public javax.swing.JTextField txtFechaEnvio;
    public javax.swing.JTextField txtFuncionario;
    public javax.swing.JTextField txtNumero;
    public javax.swing.JTextField txtResponsable;
    // End of variables declaration//GEN-END:variables
}
