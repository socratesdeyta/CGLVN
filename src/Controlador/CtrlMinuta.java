package Controlador;

import Modelo.ConsultasDestino;
import Modelo.ModeloMinuta;
import Modelo.ConsultasMinuta;
import Modelo.ModeloDestino;
import Vista.frmDestino;
import Vista.frmMinuta;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class CtrlMinuta implements ActionListener, MouseListener{
    
    private final ModeloMinuta modMin;
    private final ConsultasMinuta modConMin;
    private final frmMinuta frmMin;
    
    
        //Destinatario
    private final ModeloDestino modDes = new ModeloDestino();
    private final ConsultasDestino modConDes = new ConsultasDestino();
    public static frmDestino frmDes;
    
    public CtrlMinuta(ModeloMinuta modMin, ConsultasMinuta modConMin, frmMinuta frmMin){
        this.modMin = modMin;
        this.modConMin = modConMin;
        this.frmMin = frmMin;
        
        this.frmMin.jtMinuta.addMouseListener(this);
        this.frmMin.btnBuscarDes.addActionListener(this);
        
         //sólo se permite pulsar una fila a la vez.
        this.frmMin.jtMinuta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Carga los Combobox con los modelos
        ConsultasMinuta ConsultaMinuta = new ConsultasMinuta();
        
        DefaultComboBoxModel modelSecc = new DefaultComboBoxModel(ConsultaMinuta.mostrarSeccion());
        frmMin.cbxSeccion.setModel(modelSecc);   
        
        DefaultComboBoxModel modelSer = new DefaultComboBoxModel(ConsultaMinuta.mostrarSerie());
        frmMin.cbxSerie.setModel(modelSer);  
            
//        DefaultComboBoxModel modelSub = new DefaultComboBoxModel(ConsultaMinuta.mostrarSubserie());
//        frmMin.cbxSubserie.setModel(modelSub); 
        
        DefaultComboBoxModel modelDest = new DefaultComboBoxModel(ConsultaMinuta.mostrarDestinatario());
        frmMin.cbxDestinatario.setModel(modelDest); 
        
        DefaultComboBoxModel modelEnv = new DefaultComboBoxModel(ConsultaMinuta.mostrarEnvio());
        frmMin.cbxTipoEnvio.setModel(modelEnv); 
    }
    
    public void iniciar(){
        frmMin.setTitle("Minuta");
        frmMin.setLocationRelativeTo(null);
        
//        frmMinSal.cbxSubserie.setVisible(false);
//        frmMinSal.txtOtroDestinatario.setVisible(false);
//        frmMinSal.cbxDestinatario.setVisible(false);
//        frmMinSal.cbxTipoEnvio.setVisible(false);
//        frmMinSal.cbxSerie.setVisible(false);
        frmMin.getContentPane().setBackground(Color.DARK_GRAY);
        frmMin.jrbNotice.setBackground(Color.DARK_GRAY);
        frmMin.jrbOficio.setBackground(Color.DARK_GRAY);
        frmMin.jrbOtro.setBackground(Color.DARK_GRAY);
        frmMin.jrbSre.setBackground(Color.DARK_GRAY);

        frmMin.jrbSinReferencia.setBackground(Color.DARK_GRAY);
        frmMin.jrbSeguimiento.setBackground(Color.DARK_GRAY);
        frmMin.jrbRespuesta.setBackground(Color.DARK_GRAY);
        //frmMin.txtClave.set
        //frmMin.txtAsunto.setLineWrap(true);

        //frmMinSal.txtAsunto.setBackground(Color.GRAY);
        //frm.txtId.setVisible(false);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == frmMin.jtMinuta) {

            //deshabilitar();

            if (modConMin.Seleccionar(modMin, frmMin)) {
                                
                //LOS DATOS DEBEN SER MOSTRADOS EN EL FORMULARIO
                
                System.out.println("Salio de Seleccionar a partir de ahora se asigna al formulario");

                //frmMin.txtPruebaRespuesta.setText(String.valueOf(modMin.getSeccion()));
                frmMin.cbxSeccion.setSelectedIndex(Integer.parseInt(String.valueOf(modMin.getSeccion())));

                //frmMin.lb.setSelectedIndex(Integer.parseInt(String.valueOf(modMin.getSeccion())));
//                frmMin.cbxSeccion.setSelectedIndex(Integer.parseInt(String.valueOf(modMin.getSeccion())));
                frmMin.cbxSerie.setSelectedIndex(Integer.parseInt(String.valueOf(modMin.getSerie())));
//                frmMin.cbxSubserie.setSelectedIndex(Integer.parseInt(String.valueOf(modMin.getSubserie())));
                

                frmMin.jrbSre.setSelected(true);
                
                frmMin.cbxDestinatario.setSelectedIndex(Integer.parseInt(String.valueOf(modMin.getDestinatario())));
                frmMin.cbxTipoEnvio.setSelectedIndex(Integer.parseInt(String.valueOf(modMin.getEnvio()-1)));
                
                
                frmMin.jdcFechaRegistro.setDate(modMin.getFecha_registro());
//              frmMin.lblFuncionario.setText(String.valueOf(modMin.getFuncionario()));
                frmMin.lblFuncionario.setText(String.valueOf(modConMin.mostrarNombreFuncionario(modMin.getFuncionario())));
                
                 frmMin.txtClave.setText(String.valueOf(modMin.getNumero()));
                 
                 frmMin.txtAsunto.setText(String.valueOf(modMin.getAsunto()));    
                
                //modMin.setNumero(rs.getString("numero"));
  

//                frmMin.btnModificar.setVisible(true);
//                frmMin.btnEliminar.setVisible(true);
//                frmMin.btnLimpiar.setVisible(false);

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
        
                if (e.getSource() == frmMin.btnBuscarDes) {
            System.out.println("Se selecciono BuscarDes");

            if (frmDes == null) {
                System.out.println("No existe un formularioDestinatario");
                
                frmDes = new frmDestino();
                //CtrlDestino ctrl = new CtrlDestino(modDes, modConDes, frmDes, modLogin);
                //ctrl.iniciar();
                frmDes.setVisible(true);
                
                String Valor = frmDes.txtNombre_destino.getText();
                System.out.println("Numero de Destinatario --> " + Valor);
                
            } else {
                JOptionPane.showMessageDialog(null, "La ventana ya se encuentra abierta", "CONSULMEX LAS VEGAS", JOptionPane.ERROR_MESSAGE);
                frmDes.toFront();
            }
        }
        
        
    }
    
}
