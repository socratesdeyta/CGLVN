package Controlador;

import Modelo.ConsultasLogin;
import Modelo.ModeloLogin;
import Vista.frmLogin;
import Vista.frmHome;
//import Vista.frmInicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlLogin implements ActionListener {

    private final ModeloLogin modLogin;
    private final ConsultasLogin modConsultasLogin;
    private final frmLogin frmlogin;
//    private frmInicio ini;
    private frmHome home;

    public CtrlLogin(ModeloLogin modLogin, ConsultasLogin modConsultasLogin, frmLogin frmlogin) {
        this.modLogin = modLogin;
        this.modConsultasLogin = modConsultasLogin;
        this.frmlogin = frmlogin;

        this.frmlogin.btnIngresar.addActionListener(this);
    }

    public void iniciar() {
        frmlogin.setTitle("CONTROL DE GESTION -CONSULMEX LAS VEGAS-");
        frmlogin.setLocationRelativeTo(null);
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmlogin.btnIngresar) {
            modLogin.setUsuario(frmlogin.txtUsuario.getText());
            //mod.setPassword(frmlogin.txtPassword.getText());
            String myPass = String.valueOf(frmlogin.txtPassword.getPassword());
            modLogin.setPassword(myPass);
            String pass = new String(frmlogin.txtPassword.getPassword());

            if (!frmlogin.txtUsuario.getText().equals("") && !pass.equals("")) {

                if (modConsultasLogin.entrar(modLogin)) {
                    //ini.log = null;
                    //frmlogin = null;
                    frmlogin.dispose();
                    //--Llamar a 
                    //ModeloLogin modLogin = new ModeloLogin(); // getter and setters
                    //ConsultasLogin modConsultasLogin = new ConsultasLogin(); // sql
                    //frmLogin frmLogin = new frmLogin();  // 
                    //CtrlLogin CtrlLogin = new CtrlLogin(modLogin, modConsultasLogin, frmLogin);  //Controlador
                    //CtrlLogin.iniciar();
                    //frmLogin.setVisible(true);

                    if (home == null) {    
                    home = new frmHome(modLogin);
                    CtrlHome ctrlhome = new CtrlHome(home);  //Controlador
                    ctrlhome.iniciar();
                    home.setVisible(true);
                     }
                    
//                    home = new frmHome(modLogin);
//                    CtrlHome ctrlhome = new CtrlHome(home);  //Controlador
//                    ctrlhome.iniciar();
//                    home.setVisible(true);          

//                    home = new frmHome(modLogin);
//                    CtrlHome ctrlhome = new CtrlHome(home);  //Controlador
//                    ctrlhome.iniciar();
//                    home.setVisible(true);

                    //home = new frmHome(modLogin);
                    //home.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Datos incorrectos");

                } //cierre else
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar sus datos");
            }
        }
        //frmlogin.dispose();
    } //Cierre actionPerdomed
}
