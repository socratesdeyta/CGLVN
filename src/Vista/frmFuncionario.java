package Vista;

import Controlador.CtrlHome;
import Formato.FormatoTabla;
import Modelo.Area;
import Modelo.Cargo;
import Modelo.Conexion;
import Modelo.Permiso;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.event.ItemEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class frmFuncionario extends javax.swing.JFrame {

    public frmFuncionario() {
        initComponents();
        this.setLayout(null);

        //Formato botones
        btnBuscar.setBounds(270, 15, 105, 25);
        btnNuevo.setBounds(1000, 15, 125, 25);
        btnModificar.setBounds(1000, 50, 125, 25);
        btnEliminar.setBounds(1000, 85, 125, 25);
        btnLimpiar.setBounds(1000, 120, 125, 25);
        btnGuardar.setBounds(1000, 15, 125, 25);
        btnCancelar.setBounds(1000, 50, 125, 25);
        btnGuardarMod.setBounds(1000, 15, 125, 25);
        btnCancelarMod.setBounds(1000, 50, 125, 25);

        //Imagenes en botones
        btnNuevo.setIcon(new ImageIcon("src\\img\\nuevo.png"));
        btnModificar.setIcon(new ImageIcon("src\\img\\editar.png"));
        btnEliminar.setIcon(new ImageIcon("src\\img\\eliminar.png"));
        btnLimpiar.setIcon(new ImageIcon("src\\img\\limpiar.png"));
        btnBuscar.setIcon(new ImageIcon("src\\img\\buscar.png"));
        btnGuardar.setIcon(new ImageIcon("src\\img\\guardar.png"));
        btnCancelar.setIcon(new ImageIcon("src\\img\\cancelar.png"));
        btnGuardarMod.setIcon(new ImageIcon("src\\img\\guardar.png"));
        btnCancelarMod.setIcon(new ImageIcon("src\\img\\cancelar.png"));

        //Ubicacion etiquetas
        lblClave.setBounds(143, 15, 80, 25);
        lblNombre.setBounds(129, 44, 200, 25);
        lblPaterno.setBounds(81, 72, 200, 25);
        lblMaterno.setBounds(81, 100, 200, 25);
        lblCorreo.setBounds(69, 128, 200, 25);
        lblCargo.setBounds(470, 44, 80, 25);
        lblArea.setBounds(478, 72, 200, 25);
        lblUsuario.setBounds(461, 100, 200, 25);
        lblPassword.setBounds(640, 100, 200, 25);
        lblPermiso.setBounds(458, 128, 200, 25);

        //Formato Celdas
        txtClave.setBounds(195, 15, 80, 25);
        txtNombre.setBounds(195, 44, 250, 25);
        txtPaterno.setBounds(195, 72, 250, 25);
        txtMaterno.setBounds(195, 100, 250, 25);
        txtCorreo.setBounds(195, 128, 250, 25);

        cbxCargo.setBounds(526, 44, 310, 25);
        cbxArea.setBounds(526, 72, 310, 25);
        txtUsuario.setBounds(526, 100, 111, 25);
        txtPassword.setBounds(726, 100, 111, 25);
        cbxPermiso.setBounds(526, 128, 200, 25);

        //Color de los combobox
//        UIManager.put("JTextField.disabledBackground", new Color(244, 246, 246));
//        UIManager.put("ComboBox.disabledForeground", Color.BLACK);
        
                //Color de los combobox
        //UIManager.put("JTextField.disabledBackground", new Color(244, 246, 246));
        UIManager.put("JDateChooser.disabledBackground", Color.WHITE);
        UIManager.put("JTextFieldDateEditor.disabledBackground", Color.WHITE);
        UIManager.put("JTextField.disabledBackground", Color.WHITE);
        UIManager.put("ComboBox.disabledForeground", Color.BLACK);
        UIManager.put("ComboBox.disabledBackground", Color.white);
        
        
                // Color a las filas de la tabla (gris y blanco
        FormatoTabla formatoTabla = new FormatoTabla();
        jtFuncionarios.setDefaultRenderer(Object.class, formatoTabla);

        txtNombre.setEditable(false);
        txtPaterno.setEditable(false);
        txtMaterno.setEditable(false);
        txtCorreo.setEditable(false);
        cbxCargo.setEnabled(false);
        cbxArea.setEnabled(false);
        txtUsuario.setEditable(false);
        txtPassword.setEditable(false);
        cbxPermiso.setEditable(false);
        cbxPermiso.setEnabled(false);
        btnGuardar.setVisible(false);
        btnCancelar.setVisible(false);
        btnModificar.setVisible(false);
        btnEliminar.setVisible(false);
        btnLimpiar.setVisible(false);
        btnGuardarMod.setVisible(false);
        btnCancelarMod.setVisible(false);

        cbxCargo.setBackground(Color.white);
        cbxArea.setBackground(Color.white);
        cbxPermiso.setBackground(Color.white);

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

//--- codigo para pasar el focus
        Vector<Component> order = new Vector<>(10);
        order.add(txtClave);
        order.add(txtNombre);
        order.add(txtPaterno);
        order.add(txtMaterno);
        order.add(txtCorreo);
        order.add(cbxCargo);
        order.add(cbxArea);
        order.add(txtUsuario);
        order.add(txtPassword);
        order.add(cbxPermiso);
        order.add(btnGuardar);
        MyOFocusTraversalPolicy newPolicy;
        newPolicy = new MyOFocusTraversalPolicy(order);
        this.setFocusTraversalPolicy(newPolicy);

        //---- fin codigo para ordenar focus
        
        //Codigo formato tabla
        DefaultTableModel modeloTabla = new DefaultTableModel();
        jtFuncionarios.setModel(modeloTabla);
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        java.sql.Connection con = conn.getConexion();
        String clave = txtClave.getText();
        String where = "";
        if (!"".equals(clave)) { //Si no es null asigna el numero de campo a la consulta
            where = "WHERE clave = '" + clave + "'";
        }

        String sql = "SELECT clave, CONCAT(nombre, ' ', apellidop, ' ', apellidom) As Nombre, "
                + "mail_f, nombre_cargo, nombre_area, usuario_f, password_f, nombre_privilegio FROM control_gestion.t_funcionarios "
                + "INNER JOIN t_cargo ON t_funcionarios.id_cargo=t_cargo.id_cargo "
                + "INNER JOIN t_area ON t_funcionarios.id_area=t_area.id_area "
                + "INNER JOIN t_privilegio ON t_funcionarios.id_privilegio=t_privilegio.id_privilegio "
                + where
                + "order by clave ASC";
        System.out.println(sql);

        try {
            ps = con.prepareStatement(sql);
            System.out.println(sql);
            rs = ps.executeQuery();
            System.out.println("---------Se ejecuta la consulta");
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            modeloTabla.addColumn("Clave");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Correo electronico");
            modeloTabla.addColumn("Cargo");
            modeloTabla.addColumn("Area");
            modeloTabla.addColumn("Usuario");
            modeloTabla.addColumn("Contrasenia");
            modeloTabla.addColumn("Privilegio");
            int[] anchos = {70, 300, 250, 350, 350, 200, 150, 150,};
            for (int i = 0; i < jtFuncionarios.getColumnCount(); i++) {
                jtFuncionarios.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
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

        Cargo car = new Cargo();
        DefaultComboBoxModel modelCargo = new DefaultComboBoxModel(car.mostrarCargo());
        cbxCargo.setModel(modelCargo);
        
        Area area = new Area();
        DefaultComboBoxModel modelArea = new DefaultComboBoxModel(area.mostrarArea());
        cbxArea.setModel(modelArea);
        
        Permiso per = new Permiso();
        DefaultComboBoxModel modelPer = new DefaultComboBoxModel(per.mostrarPermiso());
        cbxPermiso.setModel(modelPer);
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

        lblClave = new javax.swing.JLabel();
        txtClave = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtPaterno = new javax.swing.JTextField();
        lblPaterno = new javax.swing.JLabel();
        txtMaterno = new javax.swing.JTextField();
        lblMaterno = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        cbxCargo = new javax.swing.JComboBox<>();
        cbxArea = new javax.swing.JComboBox<>();
        lblArea = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblPermiso = new javax.swing.JLabel();
        cbxPermiso = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtPassword = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtFuncionarios = new javax.swing.JTable();
        lblCargo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGuardarMod = new javax.swing.JButton();
        btnCancelarMod = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Funcionarios");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblClave.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblClave.setText("Clave:");

        txtClave.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        btnBuscar.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        btnBuscar.setText("Buscar");

        txtId.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        lblNombre.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblNombre.setText("Nombre");

        txtPaterno.setBackground(new java.awt.Color(255, 255, 255));
        txtPaterno.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        lblPaterno.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblPaterno.setText("Apellido Paterno");

        txtMaterno.setBackground(new java.awt.Color(255, 255, 255));
        txtMaterno.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        lblMaterno.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblMaterno.setText("Apellido Materno");

        txtCorreo.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreo.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        lblCorreo.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblCorreo.setText("Correo Electronico");

        cbxCargo.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxCargo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCargoItemStateChanged(evt);
            }
        });
        cbxCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCargoActionPerformed(evt);
            }
        });

        cbxArea.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxArea.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxAreaItemStateChanged(evt);
            }
        });

        lblArea.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblArea.setText("Área");

        lblUsuario.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblUsuario.setText("Usuario");

        lblPassword.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblPassword.setText("Contraseña");

        txtUsuario.setBackground(new java.awt.Color(255, 255, 255));
        txtUsuario.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        lblPermiso.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblPermiso.setText("Permiso");

        cbxPermiso.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        cbxPermiso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxPermisoItemStateChanged(evt);
            }
        });
        cbxPermiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPermisoActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setPreferredSize(new java.awt.Dimension(81, 23));

        btnEliminar.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setPreferredSize(new java.awt.Dimension(81, 23));

        btnLimpiar.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setPreferredSize(new java.awt.Dimension(81, 23));

        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jtFuncionarios.setAutoCreateRowSorter(true);
        jtFuncionarios.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        jtFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
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
        jtFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtFuncionariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtFuncionarios);

        lblCargo.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        lblCargo.setText("Cargo");

        btnNuevo.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setPreferredSize(new java.awt.Dimension(81, 23));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(81, 23));

        btnGuardarMod.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        btnGuardarMod.setText("Guardar.");

        btnCancelarMod.setFont(new java.awt.Font("Montserrat Light", 0, 12)); // NOI18N
        btnCancelarMod.setText("Cancelar.");
        btnCancelarMod.setPreferredSize(new java.awt.Dimension(81, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(lblClave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addGap(12, 12, 12)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(lblCorreo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblNombre)
                                            .addComponent(lblPaterno)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(70, 70, 70)
                                        .addComponent(lblMaterno)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombre)
                                    .addComponent(txtPaterno)
                                    .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblArea)
                                    .addComponent(lblCargo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblPermiso)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblUsuario)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCancelarMod, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardarMod, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(179, 179, 179)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre)
                            .addComponent(cbxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCargo))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPaterno)
                                .addComponent(lblArea)
                                .addComponent(cbxArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaterno)
                            .addComponent(txtMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsuario)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPassword)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCorreo)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPermiso)
                            .addComponent(cbxPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscar)
                                .addComponent(lblClave)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnGuardar))
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(btnGuardarMod)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelarMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxCargoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCargoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Cargo car = (Cargo) cbxCargo.getSelectedItem();
        }
    }//GEN-LAST:event_cbxCargoItemStateChanged

    private void cbxAreaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxAreaItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Area area = (Area) cbxArea.getSelectedItem();
        }
    }//GEN-LAST:event_cbxAreaItemStateChanged

    private void cbxPermisoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxPermisoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Permiso per = (Permiso) cbxPermiso.getSelectedItem();
        }
    }//GEN-LAST:event_cbxPermisoItemStateChanged

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jtFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtFuncionariosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtFuncionariosMouseClicked

    private void cbxPermisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPermisoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxPermisoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cbxCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCargoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        CtrlHome.frmfun = null;
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
            java.util.logging.Logger.getLogger(frmFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnCancelarMod;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnGuardarMod;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnNuevo;
    public javax.swing.JComboBox<String> cbxArea;
    public javax.swing.JComboBox<String> cbxCargo;
    public javax.swing.JComboBox<String> cbxPermiso;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtFuncionarios;
    public javax.swing.JLabel lblArea;
    public javax.swing.JLabel lblCargo;
    public javax.swing.JLabel lblClave;
    public javax.swing.JLabel lblCorreo;
    public javax.swing.JLabel lblMaterno;
    public javax.swing.JLabel lblNombre;
    public javax.swing.JLabel lblPassword;
    public javax.swing.JLabel lblPaterno;
    public javax.swing.JLabel lblPermiso;
    public javax.swing.JLabel lblUsuario;
    public javax.swing.JTextField txtClave;
    public javax.swing.JTextField txtCorreo;
    public javax.swing.JTextField txtId;
    public javax.swing.JTextField txtMaterno;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtPassword;
    public javax.swing.JTextField txtPaterno;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
