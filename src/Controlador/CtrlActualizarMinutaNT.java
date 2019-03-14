package Controlador;

import Modelo.ConsultasActualizarMinutaNT;
import Modelo.ConsultasDestino;
import Modelo.ModeloMinuta;
import Modelo.ConsultasMinuta;
import Modelo.ModeloActualizarMinutaNT;
import Modelo.ModeloDestino;
import Vista.frmActualizarMinutaNT;
import Vista.frmDestino;
//import Vista.frmActMinNTuta;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class CtrlActualizarMinutaNT implements ActionListener, MouseListener{
    
    private final ModeloActualizarMinutaNT modActMinNT;
    private final ConsultasActualizarMinutaNT modConActMinNT;
    private frmActualizarMinutaNT frmActMinNT;
    
    
        //Destinatario
//    private final ModeloActualizarMinutaNT modActMinNT = new ModeloActualizarMinutaNT();
//    private final ConsultasActualizarMinutaNT modConActMinNT = new ConsultasActualizarMinutaNT();
//    public static frmDestino frmActMinNT;
    
    public CtrlActualizarMinutaNT(ModeloActualizarMinutaNT modActMinNT, ConsultasActualizarMinutaNT modConActMinNT, frmActualizarMinutaNT frmActMinNT){
        
    //CtrlActualizarMinutaNT ctrl = new CtrlActualizarMinutaNT(modActMinNT, modConActMinNT, frmActMinNT);
        
        this.modActMinNT = modActMinNT;
        this.modConActMinNT = modConActMinNT;
        this.frmActMinNT = frmActMinNT;
        
        this.frmActMinNT.jtMinuta.addMouseListener(this);
        //this.frmActMinNT.btnBuscarDes.addActionListener(this);
        
         //sólo se permite pulsar una fila a la vez.
        this.frmActMinNT.jtMinuta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Carga los Combobox con los modelos
        ConsultasMinuta ConsultaMinuta = new ConsultasMinuta();
        
        DefaultComboBoxModel modelSecc = new DefaultComboBoxModel(ConsultaMinuta.mostrarSeccion());
        frmActMinNT.cbxSeccion.setModel(modelSecc);   
        
        DefaultComboBoxModel modelSer = new DefaultComboBoxModel(ConsultaMinuta.mostrarSerie());
        frmActMinNT.cbxSerie.setModel(modelSer);  
            
//        DefaultComboBoxModel modelSub = new DefaultComboBoxModel(ConsultaMinuta.mostrarSubserie());
//        frmActMinNT.cbxSubserie.setModel(modelSub); 
        
        DefaultComboBoxModel modelDest = new DefaultComboBoxModel(ConsultaMinuta.mostrarDestinatario());
        frmActMinNT.cbxDestinatario.setModel(modelDest); 
        
        DefaultComboBoxModel modelEnv = new DefaultComboBoxModel(ConsultaMinuta.mostrarEnvio());
        frmActMinNT.cbxTipoEnvio.setModel(modelEnv); 
    }
    
    public void iniciar(){
        frmActMinNT.setTitle("Minuta");
        frmActMinNT.setLocationRelativeTo(null);
        
//        frmActMinNTSal.cbxSubserie.setVisible(false);
//        frmActMinNTSal.txtOtroDestinatario.setVisible(false);
//        frmActMinNTSal.cbxDestinatario.setVisible(false);
//        frmActMinNTSal.cbxTipoEnvio.setVisible(false);
//        frmActMinNTSal.cbxSerie.setVisible(false);
        frmActMinNT.getContentPane().setBackground(Color.DARK_GRAY);
        frmActMinNT.jrbNotice.setBackground(Color.DARK_GRAY);
        frmActMinNT.jrbOficio.setBackground(Color.DARK_GRAY);
       // frmActMinNT.jrbOtro.setBackground(Color.DARK_GRAY);
       // frmActMinNT.jrbSre.setBackground(Color.DARK_GRAY);

        //frmActMinNT.jrbSinReferencia.setBackground(Color.DARK_GRAY);
        //frmActMinNT.jrbSeguimiento.setBackground(Color.DARK_GRAY);
       // frmActMinNT.jrbRespuesta.setBackground(Color.DARK_GRAY);
        //frmActMinNT.txtClave.set
        //frmActMinNT.txtAsunto.setLineWrap(true);

        //frmActMinNTSal.txtAsunto.setBackground(Color.GRAY);
        //frm.txtId.setVisible(false);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == frmActMinNT.jtMinuta) {

            //deshabilitar();

            if (modConActMinNT.Seleccionar(modActMinNT, frmActMinNT)) {
                                
                //LOS DATOS DEBEN SER MOSTRADOS EN EL FORMULARIO
                
                System.out.println("Salio de Seleccionar a partir de ahora se asigna al formulario");
                
                
                //frmActMinNT.lblSeccionAsignada.setText(String.valueOf(modConActMinNT.mostrarNombreFuncionario(modActMinNT.getFuncionario())));

                //frmActMinNT.txtPruebaRespuesta.setText(String.valueOf(modActMinNT.getSeccion()));
                frmActMinNT.cbxSeccion.setSelectedIndex(Integer.parseInt(String.valueOf(modActMinNT.getSeccion())));

                //frmActMinNT.lb.setSelectedIndex(Integer.parseInt(String.valueOf(modActMinNT.getSeccion())));
//                frmActMinNT.cbxSeccion.setSelectedIndex(Integer.parseInt(String.valueOf(modActMinNT.getSeccion())));
                frmActMinNT.cbxSerie.setSelectedIndex(Integer.parseInt(String.valueOf(modActMinNT.getSerie())));
//                frmActMinNT.cbxSubserie.setSelectedIndex(Integer.parseInt(String.valueOf(modActMinNT.getSubserie())));
                

                //frmActMinNT.jrbSre.setSelected(true);
                
                frmActMinNT.cbxDestinatario.setSelectedIndex(Integer.parseInt(String.valueOf(modActMinNT.getDestinatario())));
                frmActMinNT.cbxTipoEnvio.setSelectedIndex(Integer.parseInt(String.valueOf(modActMinNT.getEnvio()-1)));
                
                
                frmActMinNT.jdcFechaRegistro.setDate(modActMinNT.getFecha_registro());
//              frmActMinNT.lblFuncionario.setText(String.valueOf(modActMinNT.getFuncionario()));
                frmActMinNT.lblFuncionario.setText(String.valueOf(modConActMinNT.mostrarNombreFuncionario(modActMinNT.getFuncionario())));
                
                 frmActMinNT.txtClave.setText(String.valueOf(modActMinNT.getNumero()));
                 
                 frmActMinNT.txtAsunto.setText(String.valueOf(modActMinNT.getAsunto()));    
                
                //modActMinNT.setNumero(rs.getString("numero"));
  

//                frmActMinNT.btnModificar.setVisible(true);
//                frmActMinNT.btnEliminar.setVisible(true);
//                frmActMinNT.btnLimpiar.setVisible(false);

                //mostrarBotones();
                //JOptionPane.showMessageDialog(null, "Registro Seleccionado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al Seleccionar");
               // limpiar();
            }
        }
    }
    
        public void mousePressed(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
//                if (e.getSource() == frmActMinNT.btnBuscarDes) {
//            System.out.println("Se selecciono BuscarDes");
//
//            if (frmActMinNT == null) {
//                System.out.println("No existe un formulario Para actualizar Minuta");
//                
//                frmActMinNT = new frmActualizarMinutaNT();
//                //CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, modLogin);
//                //ctrl.iniciar();
//                frmActMinNT.setVisible(true);
//                
//                //String Valor = frmActMinNT.txtNombre_destino.getText();
//                //System.out.println("Numero de Destinatario --> " + Valor);
//                
//            } else {
//                JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
//                frmActMinNT.toFront();
//            }
//        }
        
        
    }
    
}
