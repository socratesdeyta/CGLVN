
package crudmvc;

//import Controlador.CtrlFuncionario;
import Controlador.CtrlLogin;
//import Modelo.ConsultasFuncionario;
import Modelo.ConsultasLogin;
//import Modelo.Funcionario;
import Modelo.ModeloLogin;
//import Vista.frmFuncionario;
import Vista.frmLogin;

public class CRUDMVC {

    public static void main(String[] args) {

        ModeloLogin modLogin = new ModeloLogin(); // getter and setters
        ConsultasLogin modConsultasLogin = new ConsultasLogin(); // sql
        frmLogin frmLogin = new frmLogin();  // Formulario
        
        CtrlLogin CtrlLogin = new CtrlLogin(modLogin, modConsultasLogin, frmLogin);  //Controlador
        CtrlLogin.iniciar();
        frmLogin.setVisible(true);
        // Funcionario mod = new Funcionario();
        // ConsultasFuncionario modC = new ConsultasFuncionario();
        // frmFuncionario frm = new frmFuncionario();

        // CtrlFuncionario ctrl = new CtrlFuncionario(mod, modC, frm);
        // ctrl.iniciar();
        // frm.setVisible(true);
    }
    
}
