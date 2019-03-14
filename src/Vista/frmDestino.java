package Vista;

import Atxy2k.CustomTextField.RestrictedTextField;
import Controlador.CtrlHome;
import Formato.FormatoTabla;

import Modelo.Conexion;
import Modelo.ConsultasDestino;
import Modelo.ModeloDestinoArray;
import Modelo.ModeloSubtipoDestino;
import Modelo.ModeloTipoDestino;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.event.ItemEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class frmDestino extends javax.swing.JFrame {

    public frmDestino() {
        initComponents();
        this.setLayout(null);

        //txtSiglas.setText(txtSiglas.getText().toUpperCase());
        ConsultasDestino ConsultasDestino = new ConsultasDestino();

        DefaultComboBoxModel modelTipoDes = new DefaultComboBoxModel(ConsultasDestino.mostrartipoDestino());
        cbxTipoDestino.setModel(modelTipoDes);

        DefaultComboBoxModel modelSubTipoDes = new DefaultComboBoxModel(ConsultasDestino.mostrarSubtipoDestino());
        cbxSubtipoDestino.setModel(modelSubTipoDes);


//----------------------------------------------------
        //AUTOCOMPLETAR ESTE SE OCUPARA EN EL FORMULARIO DE MINUTA
        
//        ArrayList<ModeloDestinoArray> listaDestino = ConsultasDestino.mostrarArregloDestino();
//        TextAutoCompleter textautocompleter = new TextAutoCompleter(txtBuscar);
//        for (int i = 0; i < listaDestino.size(); i++) {
//            textautocompleter.addItem(listaDestino.get(i));
//        }
//        //="" pd:="" no="" se="" olviden="" de="" hacer="" un="" @override="" en="" la="" clase="" alumno="" donde="" estan="" todos="" los="" atributos="" de="" la="" clase.="" @override="" public="" string="" tostring()="" {="" return="" this.apellido="" ;="" }="" de="" esa="" manera="" al="" escribir="" una="" letra="" en="" el="" jtextfield="" de="" busqueda,="" se="" apareran="" todas="" las="" coincidencias="" con="" respecto="" al="" apellido="" del="" alumno.="" deben="" haber="" muchas="" maneras="" mas="" eficientes="" de="" hacerlo,="" pero="" esa="" es="" mi="" manera.="" mxrck="" gracias="" por="" la="" libreria="" nuevamente.="" saludos.="">
//        // si es cero permite buscar en cualquier parte de texto
//        textautocompleter.setMode(0);
//        System.out.println("Prueba - >" + listaDestino.get(1));
//-------------------------------------------

        //Formato botones
        //btnBuscar.setBounds(170, 15, 105, 25);
        btnNuevo.setBounds(650, 15, 125, 25);
        btnModificar.setBounds(650, 50, 125, 25);
        btnEliminar.setBounds(650, 85, 125, 25);
        btnLimpiar.setBounds(650, 85, 125, 25);
        btnGuardar.setBounds(650, 15, 125, 25);
        btnCancelar.setBounds(650, 50, 125, 25);
        btnGuardarMod.setBounds(650, 15, 125, 25);
        btnCancelarMod.setBounds(650, 50, 125, 25);
        //btnBuscar.setVisible(false);

        //Imagenes en botones
        btnNuevo.setIcon(new ImageIcon("src\\img\\nuevo.png"));
        btnModificar.setIcon(new ImageIcon("src\\img\\editar.png"));
        btnEliminar.setIcon(new ImageIcon("src\\img\\eliminar.png"));
        btnLimpiar.setIcon(new ImageIcon("src\\img\\limpiar.png"));
        //btnBuscar.setIcon(new ImageIcon("src\\img\\buscar.png"));
        btnGuardar.setIcon(new ImageIcon("src\\img\\guardar.png"));
        btnCancelar.setIcon(new ImageIcon("src\\img\\cancelar.png"));
        btnGuardarMod.setIcon(new ImageIcon("src\\img\\guardar.png"));
        btnCancelarMod.setIcon(new ImageIcon("src\\img\\cancelar.png"));

        //Ubicacion etiquetas
        lblNumero.setBounds(10, 15, 80, 25);
        lblBuscar.setBounds(10, 15, 80, 25);
        lblNombre.setBounds(10, 44, 200, 25);
        lblSiglas.setBounds(10, 72, 200, 25);
        lblTipoDestino.setBounds(157, 72, 200, 25);
        lblSubtipoDestino.setBounds(345, 72, 200, 25);

        //Formato Celdas
        txtNumero.setBounds(80, 15, 80, 25);
        txtNombre_destino.setBounds(80, 44, 550, 25);
        txtSiglas.setBounds(80, 72, 60, 25);
        cbxTipoDestino.setBounds(243, 72, 90, 25);
        cbxSubtipoDestino.setBounds(450, 72, 180, 25);
        txtBuscar.setBounds(80, 15, 550, 25);

        //Color de los combobox
//        UIManager.put("JTextField.disabledBackground", new Color(244, 246, 246));
//        UIManager.put("ComboBox.disabledForeground", Color.BLACK);
        UIManager.put("JTextField.disabledBackground", Color.WHITE);
        UIManager.put("ComboBox.disabledForeground", Color.BLACK);
        UIManager.put("ComboBox.disabledBackground", Color.white);

        txtNumero.setVisible(false);
        lblNumero.setVisible(false);
        txtNumero.setEditable(false);
        txtNombre_destino.setEditable(false);
        txtSiglas.setEditable(false);
        cbxTipoDestino.setEnabled(false);
        cbxSubtipoDestino.setEnabled(false);
        btnGuardar.setVisible(false);
        btnCancelar.setVisible(false);
        btnModificar.setVisible(false);
        btnEliminar.setVisible(false);
        btnLimpiar.setVisible(false);
        btnGuardarMod.setVisible(false);
        btnCancelarMod.setVisible(false);
        cbxTipoDestino.setBackground(Color.white);
        cbxSubtipoDestino.setBackground(Color.white);
        RestrictedTextField restricted = new RestrictedTextField(txtSiglas);
        //restricted.setOnlyText(true);
        restricted.setLimit(5);
        //Prueba


        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

//--- codigo para pasar el focus
        Vector<Component> order = new Vector<>(5);
        order.add(txtNumero);
        order.add(txtNombre_destino);
        order.add(txtSiglas);
        order.add(cbxTipoDestino);
        order.add(cbxSubtipoDestino);
        order.add(btnGuardar);
        MyOFocusTraversalPolicy newPolicy;
        newPolicy = new MyOFocusTraversalPolicy(order);
        this.setFocusTraversalPolicy(newPolicy);

        //---- fin codigo para ordenar focus
        
        // Color a las filas de la tabla (gris y blanco
        FormatoTabla formatoTabla = new FormatoTabla();
        jtDestino.setDefaultRenderer(Object.class, formatoTabla);
        
        //Codigo formato tabla
        DefaultTableModel modeloTabla = new DefaultTableModel();
        jtDestino.setModel(modeloTabla);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        java.sql.Connection con = conn.getConexion();
        //String nombre = txtBuscar.getText();
        String clave = txtBuscar.getText();
        String where = "";
        //if (!"".equals(nombre)) { //Si no es null asigna el numero de campo a la consulta
        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta 
                   where = "WHERE CONCAT (siglas_destino, nombre_destino) LIKE '%" + clave + "%'";
        }
        //String sql = "SELECT numero_destino, CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario, "
        String sql = "SELECT CONCAT ('( ', siglas_destino, ' ) - ', nombre_destino) AS Destinatario, "
                + "nombre_tipodestino, nombre_subtipodestino FROM t_destino "
                + "INNER JOIN t_tipodestino ON t_destino.id_tipodestino = t_tipodestino.id_tipodestino "
                + "INNER JOIN t_subtipodestino ON t_destino.id_subtipodestino = t_subtipodestino.id_subtipodestino "
                + where
                + "order by numero_destino ASC";
        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            //modeloTabla.addColumn("Numero");
            modeloTabla.addColumn("Destinatario");
            modeloTabla.addColumn("Tipo destino");
            modeloTabla.addColumn("Subtipo destino");
            //int[] anchos = {15, 300, 50, 100};
            int[] anchos = {300, 10, 50};
            for (int i = 0; i < jtDestino.getColumnCount(); i++) {
                jtDestino.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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
        txtNumero = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        txtNombre_destino = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtSiglas = new javax.swing.JTextField();
        lblSiglas = new javax.swing.JLabel();
        cbxTipoDestino = new javax.swing.JComboBox<>();
        cbxSubtipoDestino = new javax.swing.JComboBox<>();
        lblSubtipoDestino = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtDestino = new javax.swing.JTable();
        lblTipoDestino = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGuardarMod = new javax.swing.JButton();
        btnCancelarMod = new javax.swing.JButton();
        lblBuscar = new javax.swing.JLabel();

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

        txtNumero.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        txtBuscar.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        txtNombre_destino.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre_destino.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        lblNombre.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblNombre.setText("Nombre:");

        txtSiglas.setBackground(new java.awt.Color(255, 255, 255));
        txtSiglas.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        lblSiglas.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblSiglas.setText("Siglas:");

        cbxTipoDestino.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxTipoDestino.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoDestinoItemStateChanged(evt);
            }
        });
        cbxTipoDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoDestinoActionPerformed(evt);
            }
        });

        cbxSubtipoDestino.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxSubtipoDestino.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSubtipoDestinoItemStateChanged(evt);
            }
        });

        lblSubtipoDestino.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblSubtipoDestino.setText("Subtipo Destino");

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

        jtDestino.setAutoCreateRowSorter(true);
        jtDestino.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        jtDestino.setModel(new javax.swing.table.DefaultTableModel(
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
        jtDestino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtDestinoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtDestino);

        lblTipoDestino.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblTipoDestino.setText("Tipo Destino");

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
        lblBuscar.setText("Buscar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblNumero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(lblBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSiglas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSiglas, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTipoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxTipoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSubtipoDestino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxSubtipoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 108, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardarMod)
                        .addComponent(btnCancelarMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblBuscar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNumero)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre_destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSiglas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxTipoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTipoDestino)
                        .addComponent(lblSubtipoDestino)
                        .addComponent(cbxSubtipoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblSiglas))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxTipoDestinoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoDestinoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //Cargo car = (Cargo) cbxCargo.getSelectedItem();
            ModeloTipoDestino TipoDestino = (ModeloTipoDestino) cbxTipoDestino.getSelectedItem();
        }
    }//GEN-LAST:event_cbxTipoDestinoItemStateChanged

    private void cbxSubtipoDestinoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSubtipoDestinoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Area area = (Area) cbxSubtipoDestino.getSelectedItem();
            ModeloSubtipoDestino SubtipoDestino = (ModeloSubtipoDestino) cbxSubtipoDestino.getSelectedItem();
        }
    }//GEN-LAST:event_cbxSubtipoDestinoItemStateChanged

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jtDestinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtDestinoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtDestinoMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cbxTipoDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDestinoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        CtrlHome.frmDes = null;
        //  frmInicio.fun = null;
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(frmDestino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDestino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDestino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDestino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDestino().setVisible(true);
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
    public javax.swing.JComboBox<String> cbxSubtipoDestino;
    public javax.swing.JComboBox<String> cbxTipoDestino;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtDestino;
    public javax.swing.JLabel lblBuscar;
    public javax.swing.JLabel lblNombre;
    public javax.swing.JLabel lblNumero;
    public javax.swing.JLabel lblSiglas;
    public javax.swing.JLabel lblSubtipoDestino;
    public javax.swing.JLabel lblTipoDestino;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JTextField txtNombre_destino;
    public javax.swing.JTextField txtNumero;
    public javax.swing.JTextField txtSiglas;
    // End of variables declaration//GEN-END:variables
}
