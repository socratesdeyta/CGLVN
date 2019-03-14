package Vista;

import Atxy2k.CustomTextField.RestrictedTextField;
import Controlador.CtrlHome;
import Formato.FormatoTabla;
import Modelo.Conexion;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class frmActualizarMinutaNotices extends javax.swing.JFrame {

    public frmActualizarMinutaNotices() {
        initComponents();
        this.setLayout(null);
        
                Color colorLetra = Color.decode("#691A31");
        Color blanco = Color.decode("#FFFFFF");
        Font negrita = new Font("Montserrat ExtraLight", Font.BOLD, 14);

         Calendar c2 = new GregorianCalendar();
         jdcFechaEnvio.setCalendar(c2);
         //jdcFechaEnvio.setEditable(false);
         
         
         // No permite la edicion del combobox de fecha
        JTextFieldDateEditor editor = (JTextFieldDateEditor) jdcFechaEnvio.getDateEditor();
        editor.setEditable(false);
        editor.setBackground(Color.yellow);
        editor.setHorizontalAlignment(SwingConstants.CENTER);
        
         
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
        lblNumero.setBounds(10, 43, 80, 25);
        lblFecha.setBounds(176, 43, 80, 25);   //+28
        lblDestinatario.setBounds(295, 43, 80, 25);   //+28
        lblSeccion.setBounds(10, 71, 80, 25);   //+28
        lblSerie.setBounds(10, 99, 800, 25);   //+28
        lblSubserie.setBounds(10, 127, 80, 25);   //+28
        lblFuncionario.setBounds(10, 155, 80, 25);   //+28
        lblTipoEnvio.setBounds(434, 155, 80, 25);   //+28
        lblAsunto.setBounds(10, 183, 80, 25);   //+28

        //Formato Celdas
        txtBuscar.setBounds(88, 15, 37, 25);
        txtNumero.setBounds(88, 43, 80, 25);
        txtFecha.setBounds(220, 43, 68, 25);
        txtFuncionario.setBounds(88, 155, 220, 25);
        txtAsunto.setBounds(88, 183, 650, 75);
        
        
        // formato celda LVN
        txtNumero.setForeground(colorLetra);
        txtNumero.setFont(negrita);


        //Formato Combobox     
        //jdcFechaRegistro.setBounds(208, 43, 68, 25);
        cbxDestinatario.setBounds(375, 43, 363, 25);
        cbxSeccion.setBounds(88, 71, 650, 25);
        cbxSerie.setBounds(88, 99, 650, 25);
        cbxSubserie.setBounds(88, 127, 650, 25);
        //cbxFuncionario.setBounds(88, 155, 220, 25);
        cbxTipoEnvio.setBounds(518, 155, 220, 25);
        
        //Formato Combobox Data   
        jdcFechaEnvio.setBounds(752, 183, 150, 25);
        
        //Formato boton 
        btnAsignarFecha.setBounds(752, 211, 150, 25);
        

        

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
        cbxTipoEnvio.setEnabled(false);

        // data fecha envio, NO visible
        jdcFechaEnvio.setVisible(false);

        // boton data fecha envio, NO visible
        btnAsignarFecha.setVisible(false);

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
        
        
        
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        // Color a las filas de la tabla (gris y blanco
        FormatoTabla formatoTabla = new FormatoTabla();
        jtActMinNt.setDefaultRenderer(Object.class, formatoTabla);
        
        //Formato a tabla
        DefaultTableModel modeloTabla = new DefaultTableModel();
        jtActMinNt.setModel(modeloTabla);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        java.sql.Connection con = conn.getConexion();
        //String nombre = txtBuscar.getText();
        String clave = txtBuscar.getText();
        String buscarLVN = "";
                if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta 
                   buscarLVN = "AND numero LIKE 'LVN-"+clave+"%'";
        }
                String sql = "SELECT numero, fecha_registro, IFNULL(t_subserie.codigo_subserie, t_serie.codigo_serie) As Clasificacion, "
                + "t_tipoenvio.nombre_tipoenvio, asunto "
                //+ "asunto "
                + "FROM control_gestion.t_documentosenviados "
                //+ "LEFT JOIN t_seccion ON t_documentosenviados.seccion=t_seccion.id_seccion "
                + "LEFT JOIN t_serie ON t_documentosenviados.serie=t_serie.id_serie "
                + "LEFT JOIN t_subserie ON t_documentosenviados.subserie=t_subserie.id_subserie "
                + "LEFT JOIN t_destino ON t_documentosenviados.destinatario=t_destino.id_destino "
                + "LEFT JOIN t_funcionarios ON t_documentosenviados.funcionario=t_funcionarios.clave "
                + "LEFT JOIN t_tipoenvio ON t_documentosenviados.envio=t_tipoenvio.id_tipoenvio "
                + "WHERE envio = 1 AND fecha_envio IS NULL "
                + buscarLVN
                + "order by numero asc;";
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            modeloTabla.addColumn("Numero");
            modeloTabla.addColumn("Fecha");
            modeloTabla.addColumn("Clasificacion");
            modeloTabla.addColumn("Tipo de envio");
            modeloTabla.addColumn("Asunto");
            int[] anchos = {70, 70, 75, 110, 567};
            for (int i = 0; i < jtActMinNt.getColumnCount(); i++) {
                jtActMinNt.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jtActMinNt = new javax.swing.JTable();
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
        jdcFechaEnvio = new com.toedter.calendar.JDateChooser();
        btnAsignarFecha = new javax.swing.JButton();
        txtNumero = new javax.swing.JTextField();
        txtAsunto = new javax.swing.JScrollPane();
        txtAsunto2 = new javax.swing.JTextPane();

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

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jtActMinNt.setAutoCreateRowSorter(true);
        jtActMinNt.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        jtActMinNt.setModel(new javax.swing.table.DefaultTableModel(
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
        jtActMinNt.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtActMinNt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtActMinNtMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtActMinNt);

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

        jdcFechaEnvio.setFont(new java.awt.Font("Montserrat ExtraLight", 0, 14)); // NOI18N

        btnAsignarFecha.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        btnAsignarFecha.setText("Asignar fecha");

        txtNumero.setBackground(new java.awt.Color(255, 255, 255));

        txtAsunto2.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        txtAsunto.setViewportView(txtAsunto2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAsunto)
                        .addGap(701, 701, 701)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAsignarFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcFechaEnvio, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBuscar)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNumero)
                                    .addComponent(lblSerie)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblSubserie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSeccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblFuncionario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(120, 120, 120)
                                        .addComponent(lblFecha)
                                        .addGap(4, 4, 4)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblDestinatario)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbxSerie, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxSeccion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxSubserie, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(70, 70, 70)
                                        .addComponent(lblTipoEnvio)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbxTipoEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumero)
                    .addComponent(lblFecha)
                    .addComponent(lblDestinatario)
                    .addComponent(cbxDestinatario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(txtFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAsunto)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jdcFechaEnvio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(btnAsignarFecha))
                    .addComponent(txtAsunto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("ActualizarMinutaOficios");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jtActMinNtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtActMinNtMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtActMinNtMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        CtrlHome.frmActMinNt = null;
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
            java.util.logging.Logger.getLogger(frmActualizarMinutaNotices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmActualizarMinutaNotices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmActualizarMinutaNotices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmActualizarMinutaNotices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new frmActualizarMinutaNotices().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAsignarFecha;
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
    public javax.swing.JComboBox<String> cbxTipoEnvio;
    public javax.swing.JScrollPane jScrollPane1;
    public com.toedter.calendar.JDateChooser jdcFechaEnvio;
    public javax.swing.JTable jtActMinNt;
    public javax.swing.JLabel lblAsunto;
    public javax.swing.JLabel lblBuscar;
    public javax.swing.JLabel lblDestinatario;
    public javax.swing.JLabel lblFecha;
    public javax.swing.JLabel lblFuncionario;
    public javax.swing.JLabel lblNumero;
    public javax.swing.JLabel lblSeccion;
    public javax.swing.JLabel lblSerie;
    public javax.swing.JLabel lblSubserie;
    public javax.swing.JLabel lblTipoEnvio;
    public javax.swing.JScrollPane txtAsunto;
    public javax.swing.JTextPane txtAsunto2;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JTextField txtFecha;
    public javax.swing.JTextField txtFuncionario;
    public javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables
}
