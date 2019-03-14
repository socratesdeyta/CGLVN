package Controlador;

import Modelo.ConsultasActualizarMinutaNotices;
import Modelo.ConsultasActualizarMinutaOficios;
import Modelo.ConsultasCorrespondencia;
import Modelo.ConsultasDestino;
import Modelo.ConsultasFavorito;
import Modelo.ConsultasFuncionarios;
import Modelo.ConsultasMinuta;
import Modelo.ConsultasMinutarioSalida;
import Modelo.ConsultasRemitente;
import Modelo.ConsultasVerMinuta;
import Modelo.Funcionario;
import Modelo.ModeloActualizarMinutaNotices;
import Modelo.ModeloActualizarMinutaOficios;
import Modelo.ModeloCorrespondencia;
import Modelo.ModeloDestino;
import Modelo.ModeloFavorito;
import Modelo.ModeloLogin;
import Modelo.ModeloMinuta;
import Modelo.ModeloMinutarioSalida;
import Modelo.ModeloRemitente;
import Modelo.ModeloVerMinuta;
import Vista.frmDestino;
import Vista.frmFuncionario;
import Vista.frmHome;
import Vista.frmMinuta;
import Vista.frmMinutarioSalida;
import Vista.frmActualizarMinutaNotices;
import Vista.frmActualizarMinutaOficios;
import Vista.frmCorrespondenciaEntrada;
import Vista.frmFavorito;
import Vista.frmRemitente;
import Vista.frmVerMinuta;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlHome implements ActionListener {

    private final frmHome frmhome;

    int seccion = 0;
    int serie = 0;
    int serie_a_guardar = 0;
    int subserie = 0;
    int subserie_a_guardar = 0;
    int envio = 0;
    String destinatario = "";
    String titulo = "";
    String asunto = "";

    //SE DEBEN CREAR LOS MODELOS? TODOS?
//Funcionarios
    private final Funcionario mod = new Funcionario();
    private final ConsultasFuncionarios modC = new ConsultasFuncionarios();
    public static frmFuncionario frmfun;

//Minutario Salidas
    private final ModeloMinutarioSalida modMinSal = new ModeloMinutarioSalida();
    private final ConsultasMinutarioSalida modConMinSal = new ConsultasMinutarioSalida();
    public static frmMinutarioSalida frmMinSal;

    //Correspodencia
    private final ModeloCorrespondencia modCorres = new ModeloCorrespondencia();
    private final ConsultasCorrespondencia modConCorres = new ConsultasCorrespondencia();
    public static frmCorrespondenciaEntrada frmCorrEnt;

    // favoritos
    private final ModeloFavorito modFav = new ModeloFavorito();
    private final ConsultasFavorito modConFav = new ConsultasFavorito();
    public static frmFavorito frmFav;

    //Ver Minutario
    private final ModeloVerMinuta modVerMin = new ModeloVerMinuta();
    private final ConsultasVerMinuta modConVerMin = new ConsultasVerMinuta();
    public static frmVerMinuta frmVerMin;

    //Minutario Oficios
    private final ModeloActualizarMinutaOficios modActOfi = new ModeloActualizarMinutaOficios();
    private final ConsultasActualizarMinutaOficios modConActOfi = new ConsultasActualizarMinutaOficios();
    public static frmActualizarMinutaOficios frmActMinOfi;

    //Minutario Notices
    private final ModeloActualizarMinutaNotices modActNt = new ModeloActualizarMinutaNotices();
    private final ConsultasActualizarMinutaNotices modConActNt = new ConsultasActualizarMinutaNotices();
    public static frmActualizarMinutaNotices frmActMinNt;

    //Minutario Entradas
    private final ModeloMinuta modMin = new ModeloMinuta();
    private final ConsultasMinuta modConMin = new ConsultasMinuta();
    //private frmMinuta frmMin = new frmMinuta();
    public static frmMinuta frmMin;

    //Minutario Actualizar Minuta
//    private final ModeloActualizarMinutaNT modActMinNT = new ModeloActualizarMinutaNT();
//    private final ConsultasActualizarMinutaNT modConActMinNT = new ConsultasActualizarMinutaNT();
//    public static frmActualizarMinutaNT frmActMinNT;
    //Remitente
    private final ModeloRemitente modRemi = new ModeloRemitente();
    private final ConsultasRemitente modConRemi = new ConsultasRemitente();
    public static frmRemitente frmRemi;
    public static ModeloLogin modLoginRemi;

    //Destinatario
    private final ModeloDestino modDes = new ModeloDestino();
    private final ConsultasDestino modConDes = new ConsultasDestino();
    public static frmDestino frmDes;
    public static ModeloLogin modLogin;

    public CtrlHome(frmHome frmhome) {
        this.frmhome = frmhome;
        this.frmhome.subCatalogoFuncionarios.addActionListener(this);
        this.frmhome.subCatalogoDestinatario.addActionListener(this);
        this.frmhome.subCatalogoRemitente.addActionListener(this);
        this.frmhome.subCatalogoTipoEnvios.addActionListener(this);
        this.frmhome.subCorrespondenciaEntradas.addActionListener(this);
        this.frmhome.subDocuOP7.addActionListener(this);
        this.frmhome.subDocuVerificacion.addActionListener(this);
        this.frmhome.subProtePension.addActionListener(this);
        this.frmhome.subProteInformes.addActionListener(this);
        this.frmhome.subMinutarioEntrada.addActionListener(this);
        this.frmhome.subMinutarioSalida.addActionListener(this);
        this.frmhome.subActualizarOficio.addActionListener(this);
        this.frmhome.subActualizarNotice.addActionListener(this);
        this.frmhome.btnVerMinuta.addActionListener(this);
        this.frmhome.btnVerEntradas.addActionListener(this);
    }

    public void iniciar() {
        frmhome.setTitle("Consulado de México en Las Vegas");
        frmhome.setLocationRelativeTo(null);
        frmhome.setExtendedState(MAXIMIZED_BOTH);
    }

    // SE DEBE DECLARAR NULL CUANDO SE CIERRA PARA NO DUPLICAR EL JFRAME, 
    //SE DEBE HACER EN EL FORMULARIO DE HOME EN EL BOTON CERRAR O CREAR UN BOTON DE CERRAR Y DESHABILITAR EL BOTON DE LA DERECHA
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmhome.subMinutarioSalida) {
            System.out.println("Se selecciono Minuta Salida");

            if (frmMinSal == null) {
                System.out.println("No existe un formulario Minuta Salida");
                frmMinSal = new frmMinutarioSalida(frmhome.idFuncionario, frmhome.nombreFuncionario);

                System.out.println("Funcionario id en HOME -->" + frmhome.idFuncionario);
                CtrlMinutarioSalida ctrl = new CtrlMinutarioSalida(modMinSal, modConMinSal, frmMinSal);
                ctrl.iniciar();
                frmMinSal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
                frmMinSal.toFront();

                // PENDIENTE: probar el Controlador de Minuta
            }
        }

        if (e.getSource() == frmhome.subCorrespondenciaEntradas) {
            System.out.println("Se selecciono Correspondencia Entradas");

            if (frmCorrEnt == null) {
                System.out.println("No existe un formulario CorrespondenciaEntradas");
                frmCorrEnt = new frmCorrespondenciaEntrada(frmhome.idFuncionario, frmhome.nombreFuncionario);
                CtrlCorrespondenciaEntrada ctrl = new CtrlCorrespondenciaEntrada(modCorres, modConCorres, frmCorrEnt);
                ctrl.iniciar();
                frmCorrEnt.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
                frmCorrEnt.toFront();

                // PENDIENTE: probar el Controlador de Minuta
            }
        }

        if (e.getSource() == frmhome.subCatalogoDestinatario) {
            System.out.println("Se selecciono Destinatario");

            if (frmDes == null) {
                System.out.println("No existe un formularioDestinatario");
                //String clavefuncionario = frmhome.ClaveFuncionario;
                int idFuncionario = frmhome.idFuncionario;
                frmDes = new frmDestino();
                //CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, clavefuncionario);
                CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, idFuncionario);
                ctrl.iniciar();
                frmDes.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
                frmDes.toFront();
            }
        }

        if (e.getSource() == frmhome.subCatalogoRemitente) {
            System.out.println("Se selecciono Remitente");

            if (frmRemi == null) {
                System.out.println("No existe un formulario Remitente");
                //String clavefuncionario = frmhome.ClaveFuncionario;
                int idFuncionario = frmhome.idFuncionario;
                frmRemi = new frmRemitente();
                //CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, clavefuncionario);
                CtrlRemitente ctrl = new CtrlRemitente(modRemi, modConRemi, frmRemi, idFuncionario);
                ctrl.iniciar();
                frmRemi.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
                frmRemi.toFront();
            }
        }

        if (e.getSource() == frmhome.subCatalogoFuncionarios) {
            System.out.println("Se selecciono Funcionarios");

            if (frmfun == null) {
                System.out.println("No existe un formulariofuncionario");
                frmfun = new frmFuncionario();
                CtrlFuncionario ctrl = new CtrlFuncionario(mod, modC, frmfun);
                ctrl.iniciar();
                frmfun.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
                frmfun.toFront();
            }
        }

//        if (e.getSource() == frmhome.subFavoritosOP7) {
//            titulo = "OP7";
//            seccion = 15;
//            serie = 6;
//            subserie = 0;
//            envio = 1;
//            asunto = "OP7 a nombre de ";
//            crearFormularioFavorito();
//        }
        if (e.getSource() == frmhome.subDocuOP7) {
            titulo = "Documentación - OP7";
            seccion = 15;
            serie = 6;
            serie_a_guardar = 110;
            subserie = 0;
            subserie_a_guardar = 0;
            destinatario = "";
            envio = 1;
            asunto = "OP7 ";

            crearFormularioFavorito();
        }

        if (e.getSource() == frmhome.subDocuVerificacion) {
            titulo = "Documentación - Solicitud de verificación";
            seccion = 15;
            serie = 4;
            serie_a_guardar = 108;
            subserie = 1;
            subserie_a_guardar = 26;
            destinatario = "";
            envio = 1;
            asunto = "Verificación ";

            crearFormularioFavorito();
        }

        if (e.getSource() == frmhome.subProtePension) {
            titulo = "Protección - Pensión Alimenticia";
            seccion = 15;
            serie = 4;
            serie_a_guardar = 108;
            subserie = 3;
            subserie_a_guardar = 28;
            destinatario = "";
            envio = 2;
            asunto = "Envio de ";

            //txtDestinatario.setText("( PME ) - DG Protección de Mexicanos en el Exterior");
            crearFormularioFavorito();
        }

        if (e.getSource() == frmhome.subProteInformes) {
            titulo = "Protección - Informes";
            seccion = 15;
            serie = 2;
            serie_a_guardar = 106;
            subserie = 0;
            subserie_a_guardar = 0;
            destinatario = "( PME ) - DG Protección de Mexicanos en el Exterior";
            envio = 1;
            asunto = "";

            crearFormularioFavorito();
        }

        if (e.getSource() == frmhome.subMinutarioEntrada) {
            System.out.println("Se selecciono Minuta Entrada");

            if (frmMin == null) {
                System.out.println("No existe un formulario Minuta");
                frmMin = new frmMinuta();
                CtrlMinuta ctrl = new CtrlMinuta(modMin, modConMin, frmMin);
                ctrl.iniciar();
                frmMin.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
                frmMin.toFront();

                // PENDIENTE: probar el Controlador de Minuta
            }
        }

//        if (e.getSource() == frmhome.subCatalogoDeterminante) {
//            System.out.println("Se selecciono Determinante (ActualizarMinuta)");
//
//            if (frmActMinNT == null) {
//                System.out.println("No existe un formulario Actualizar Notice");
//                frmActMinNT = new frmActualizarMinutaNT();
//                CtrlActualizarMinutaNT ctrl = new CtrlActualizarMinutaNT(modActMinNT, modConActMinNT, frmActMinNT);
//                ctrl.iniciar();
//                frmActMinNT.setVisible(true);
//            } else {
//                JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
//                frmActMinNT.toFront();
//
//                // PENDIENTE: probar el Controlador de Minuta
//            }
//        }
        if (e.getSource() == frmhome.subActualizarOficio) {
            System.out.println("Se selecciono Actualizar oficio");

            if (frmActMinOfi == null) {
                System.out.println("No existe un formulario actualizar oficio");
                //String clavefuncionario = frmhome.ClaveFuncionario;
                int idFuncionario = frmhome.idFuncionario;
                frmActMinOfi = new frmActualizarMinutaOficios();
                //CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, clavefuncionario);
                //CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, idFuncionario);
                //CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, idFuncionario);
                CtrlActualizarMinutaOficios ctrl = new CtrlActualizarMinutaOficios(modActOfi, modConActOfi, frmActMinOfi, idFuncionario);
                ctrl.iniciar();
                frmActMinOfi.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
                frmActMinOfi.toFront();
            }
        }

        if (e.getSource() == frmhome.subActualizarNotice) {
            System.out.println("Se selecciono Actualizar notice");

            if (frmActMinNt == null) {
                System.out.println("No existe un formulario actualizar notice");
                //String clavefuncionario = frmhome.ClaveFuncionario;
                int idFuncionario = frmhome.idFuncionario;
                frmActMinNt = new frmActualizarMinutaNotices();
                //CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, clavefuncionario);
                //CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, idFuncionario);
                //CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, idFuncionario);
                CtrlActualizarMinutaNotices ctrl = new CtrlActualizarMinutaNotices(modActNt, modConActNt, frmActMinNt, idFuncionario);
                ctrl.iniciar();
                frmActMinNt.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
                frmActMinNt.toFront();
            }
        }

        if (e.getSource() == frmhome.btnVerEntradas) {
          System.out.println("Se selecciono Correspondencia Entradas");

            if (frmCorrEnt == null) {
                System.out.println("No existe un formulario CorrespondenciaEntradas");
                frmCorrEnt = new frmCorrespondenciaEntrada(frmhome.idFuncionario, frmhome.nombreFuncionario);
                CtrlCorrespondenciaEntrada ctrl = new CtrlCorrespondenciaEntrada(modCorres, modConCorres, frmCorrEnt);
                ctrl.iniciar();
                frmCorrEnt.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
                frmCorrEnt.toFront();

                // PENDIENTE: probar el Controlador de Minuta
            }
        }

        if (e.getSource()
                == frmhome.btnVerMinuta) {
            System.out.println("Se selecciono el boton Ver Minuta");

            if (frmVerMin == null) {
                System.out.println("No existe un formulario Ver minuta");
                //String clavefuncionario = frmhome.ClaveFuncionario;
                int idFuncionario = frmhome.idFuncionario;
                frmVerMin = new frmVerMinuta();
                //CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, clavefuncionario);
                //CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, idFuncionario);
                //CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, idFuncionario);
                CtrlVerMinuta ctrl = new CtrlVerMinuta(modVerMin, modConVerMin, frmVerMin, idFuncionario);
                ctrl.iniciar();
                frmVerMin.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
                frmVerMin.toFront();
            }
        }

    } //Cierre actionPerdomed

    private void crearFormularioFavorito() {
        if (frmFav == null) {
            System.out.println("No existe un formulario de favorito");
            frmFav = new frmFavorito(frmhome.idFuncionario, frmhome.nombreFuncionario, seccion, serie, serie_a_guardar, subserie, subserie_a_guardar, destinatario, envio, titulo, asunto);
            CtrlFavorito ctrl = new CtrlFavorito(modFav, modConFav, frmFav);
            ctrl.iniciar();
            frmFav.setVisible(true);
            if (subserie != 0) {
                frmFav.cbxSubserie.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
            frmFav.toFront();
        }
    }

}
