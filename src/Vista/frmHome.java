package Vista;
import Modelo.ModeloLogin;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class frmHome extends javax.swing.JFrame {

    //private JLabel boton;
    ModeloLogin modlogin;
    public int idFuncionario;
    public String ClaveFuncionario;
    public String nombreFuncionario;

    //COLOR BLANCO
    public frmHome() {
        this.getContentPane().setBackground(new Color(98, 17, 50));
        initComponents();
        System.out.println("Entro al constructor sin parametros");
        this.setExtendedState(MAXIMIZED_BOTH);
        //this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public frmHome(ModeloLogin modlogin) {

        initComponents();
        
        
        ImageIcon logoMinuta = new javax.swing.ImageIcon(getClass().getResource("/img/salida.png"));
        btnVerMinuta.setIcon(logoMinuta);
        btnVerMinuta.setBorder(BorderFactory.createEmptyBorder());
        
        
        ImageIcon logoEntrada = new javax.swing.ImageIcon(getClass().getResource("/img/entrada.png"));
        btnVerEntradas.setIcon(logoEntrada);
        btnVerEntradas.setBorder(BorderFactory.createEmptyBorder());
        
//         boton.setIcon(new ImageIcon(getClass().getResource("src\\img\\nuevo.png")));
//                 // Imagen Boton Ver Minuta        
//        boton.setIcon(new ImageIcon("src\\img\\nuevo.png"));
//         
        

        this.setLocationRelativeTo(rootPane);
        //CtrlHome ctrlhome = new Ctr
        //ctrlhome = new CtrlHome(modFuncionario, modConsultasFuncionario, this);
        //ctrlhome = new CtrlHome(this);
        this.getContentPane().setBackground(Color.decode("#FFFFFF"));
        this.modlogin = modlogin;
        System.out.println("Entro al constructor CON parametros");
        this.setExtendedState(MAXIMIZED_BOTH);
        //this.getContentPane().setBackground(Color.WHITE);
        //Imagen de fondo
        ImageIcon logo = new ImageIcon("src\\img\\logoPersonajes.png");
        ImageIcon icono = new ImageIcon(logo.getImage().getScaledInstance(700, 800, Image.SCALE_DEFAULT));
        //lblLogo.setIcon(icono);
        //Etiquetas
        lblNombre.setForeground(Color.decode("#691A31"));
        lblNombre.setText(modlogin.getNombreCompleto());
        lblPermiso.setForeground(Color.decode("#691A31"));
        lblPermiso.setText(modlogin.getNombrePermiso());



        //System.out.println("Vista.frmHome.<init>() 1111 " +ClaveFuncionario);
        //Este dato se enviara a todas las ventanas para conocer el funcionario que 
        //realiza tareas dentro del sistema (Tipo Bitacora)
        idFuncionario = modlogin.getId();
        System.out.println("Desde Login IdFuncionario -->" + modlogin.getId());
        System.out.println("Desde Login nombrePermiso -->" + modlogin.getNombrePermiso());
        nombreFuncionario = modlogin.getNombreCompleto();
        System.out.println("Vista.frmHome.<init>()2222 " + idFuncionario);

        //menuFavoritos.setVisible(false);
        subMinutarioEntrada.setVisible(false);
        //subCatalogoRemitente.setVisible(false);
        subCatalogoTipoEnvios.setVisible(false);
        
        menuCorrespondencia.setVisible(false);

        //Validacion de permisos
        if (modlogin.getPermiso() == 1) {

            //menuActualizar.setVisible(false);
        } else if (modlogin.getPermiso() == 2) {
            menuActualizar.setVisible(false);
            menuCatalogos.setVisible(false);
            //btnVerEntradas.setVisible(false);
            //btnVerMinuta.setVisible(false);
            //menuCatalogos.setVisible(false);
            //menuFavoritos.setVisible(false);

        }
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    }

    /**
     *
     * This method is called from within the constructor to initialize the form.
     *
     * WARNING: Do NOT modify this code. The content of this method is always
     *
     * regenerated by the Form Editor.
     *
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        lblPermiso = new javax.swing.JLabel();
        lblPRUEBA = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnVerMinuta = new javax.swing.JButton();
        btnVerEntradas = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMinutario = new javax.swing.JMenu();
        subMinutarioEntrada = new javax.swing.JMenuItem();
        subMinutarioSalida = new javax.swing.JMenuItem();
        menuCorrespondencia = new javax.swing.JMenu();
        subCorrespondenciaEntradas = new javax.swing.JMenuItem();
        menuCatalogos = new javax.swing.JMenu();
        subCatalogoDestinatario = new javax.swing.JMenuItem();
        subCatalogoRemitente = new javax.swing.JMenuItem();
        subCatalogoFuncionarios = new javax.swing.JMenuItem();
        subCatalogoTipoEnvios = new javax.swing.JMenuItem();
        menuActualizar = new javax.swing.JMenu();
        subActualizarNotice = new javax.swing.JMenuItem();
        subActualizarOficio = new javax.swing.JMenuItem();
        menuDocumentacion = new javax.swing.JMenu();
        subDocuOP7 = new javax.swing.JMenuItem();
        subDocuVerificacion = new javax.swing.JMenuItem();
        menuProteccion = new javax.swing.JMenu();
        subProtePension = new javax.swing.JMenuItem();
        subProteInformes = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(98, 17, 50));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombre.setFont(new java.awt.Font("Montserrat Light", 1, 12)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(0, 0, 153));
        lblNombre.setText("1");
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 20, 264, -1));

        lblPermiso.setFont(new java.awt.Font("Montserrat Light", 1, 12)); // NOI18N
        lblPermiso.setForeground(new java.awt.Color(0, 0, 153));
        lblPermiso.setText("2");
        getContentPane().add(lblPermiso, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 50, 264, -1));
        getContentPane().add(lblPRUEBA, new org.netbeans.lib.awtextra.AbsoluteConstraints(1757, 617, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPersonajes.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, -1));

        btnVerMinuta.setFont(new java.awt.Font("Montserrat Light", 1, 18)); // NOI18N
        getContentPane().add(btnVerMinuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 290, 220, 210));

        btnVerEntradas.setFont(new java.awt.Font("Montserrat Light", 1, 18)); // NOI18N
        btnVerEntradas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/entrada.png"))); // NOI18N
        getContentPane().add(btnVerEntradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 220, 210));

        menuMinutario.setText("Minutario");
        menuMinutario.setFont(new java.awt.Font("Montserrat ExtraLight", 1, 14)); // NOI18N

        subMinutarioEntrada.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        subMinutarioEntrada.setText("Entradas");
        subMinutarioEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMinutarioEntradaActionPerformed(evt);
            }
        });
        menuMinutario.add(subMinutarioEntrada);

        subMinutarioSalida.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        subMinutarioSalida.setText("Salidas");
        menuMinutario.add(subMinutarioSalida);

        jMenuBar1.add(menuMinutario);

        menuCorrespondencia.setText("Correspondencia");
        menuCorrespondencia.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N

        subCorrespondenciaEntradas.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        subCorrespondenciaEntradas.setText("Entradas");
        subCorrespondenciaEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subCorrespondenciaEntradasActionPerformed(evt);
            }
        });
        menuCorrespondencia.add(subCorrespondenciaEntradas);

        jMenuBar1.add(menuCorrespondencia);

        menuCatalogos.setText("Catalogos");
        menuCatalogos.setFont(new java.awt.Font("Montserrat ExtraLight", 1, 14)); // NOI18N

        subCatalogoDestinatario.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        subCatalogoDestinatario.setText("Destinatario");
        menuCatalogos.add(subCatalogoDestinatario);

        subCatalogoRemitente.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        subCatalogoRemitente.setText("Remitente");
        menuCatalogos.add(subCatalogoRemitente);

        subCatalogoFuncionarios.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        subCatalogoFuncionarios.setText("Funcionarios");
        subCatalogoFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subCatalogoFuncionariosActionPerformed(evt);
            }
        });
        menuCatalogos.add(subCatalogoFuncionarios);

        subCatalogoTipoEnvios.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        subCatalogoTipoEnvios.setText("Tipo Envio");
        menuCatalogos.add(subCatalogoTipoEnvios);

        jMenuBar1.add(menuCatalogos);

        menuActualizar.setText("Actualizar");
        menuActualizar.setFont(new java.awt.Font("Montserrat ExtraLight", 1, 14)); // NOI18N

        subActualizarNotice.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        subActualizarNotice.setText("Notice");
        menuActualizar.add(subActualizarNotice);

        subActualizarOficio.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        subActualizarOficio.setText("Oficio");
        menuActualizar.add(subActualizarOficio);

        jMenuBar1.add(menuActualizar);

        menuDocumentacion.setText("Documentación");
        menuDocumentacion.setFont(new java.awt.Font("Montserrat ExtraLight", 1, 14)); // NOI18N

        subDocuOP7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_7, java.awt.event.InputEvent.CTRL_MASK));
        subDocuOP7.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        subDocuOP7.setText("OP7");
        menuDocumentacion.add(subDocuOP7);

        subDocuVerificacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        subDocuVerificacion.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        subDocuVerificacion.setText("Verificación");
        subDocuVerificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subDocuVerificacionActionPerformed(evt);
            }
        });
        menuDocumentacion.add(subDocuVerificacion);

        jMenuBar1.add(menuDocumentacion);

        menuProteccion.setText("Protección");
        menuProteccion.setFont(new java.awt.Font("Montserrat ExtraLight", 1, 14)); // NOI18N

        subProtePension.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        subProtePension.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        subProtePension.setText("Pensión Alimenticia");
        menuProteccion.add(subProtePension);

        subProteInformes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        subProteInformes.setFont(new java.awt.Font("Montserrat Light", 1, 14)); // NOI18N
        subProteInformes.setText("Informes");
        subProteInformes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subProteInformesActionPerformed(evt);
            }
        });
        menuProteccion.add(subProteInformes);

        jMenuBar1.add(menuProteccion);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subMinutarioEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMinutarioEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subMinutarioEntradaActionPerformed

    private void subCatalogoFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subCatalogoFuncionariosActionPerformed

    }//GEN-LAST:event_subCatalogoFuncionariosActionPerformed

    private void subDocuVerificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subDocuVerificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subDocuVerificacionActionPerformed

    private void subProteInformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subProteInformesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subProteInformesActionPerformed

    private void subCorrespondenciaEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subCorrespondenciaEntradasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subCorrespondenciaEntradasActionPerformed

    /**
     *
     * @param args the command line arguments
     *
     */
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

            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {

            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {

            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {

            java.util.logging.Logger.getLogger(frmHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }

        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmHome().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnVerEntradas;
    public javax.swing.JButton btnVerMinuta;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPRUEBA;
    private javax.swing.JLabel lblPermiso;
    public javax.swing.JMenu menuActualizar;
    public javax.swing.JMenu menuCatalogos;
    public javax.swing.JMenu menuCorrespondencia;
    public javax.swing.JMenu menuDocumentacion;
    public javax.swing.JMenu menuMinutario;
    public javax.swing.JMenu menuProteccion;
    public javax.swing.JMenuItem subActualizarNotice;
    public javax.swing.JMenuItem subActualizarOficio;
    public javax.swing.JMenuItem subCatalogoDestinatario;
    public javax.swing.JMenuItem subCatalogoFuncionarios;
    public javax.swing.JMenuItem subCatalogoRemitente;
    public javax.swing.JMenuItem subCatalogoTipoEnvios;
    public javax.swing.JMenuItem subCorrespondenciaEntradas;
    public javax.swing.JMenuItem subDocuOP7;
    public javax.swing.JMenuItem subDocuVerificacion;
    public javax.swing.JMenuItem subMinutarioEntrada;
    public javax.swing.JMenuItem subMinutarioSalida;
    public javax.swing.JMenuItem subProteInformes;
    public javax.swing.JMenuItem subProtePension;
    // End of variables declaration//GEN-END:variables

}
