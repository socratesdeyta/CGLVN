package Formato;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;

public class FormatoTabla extends DefaultTableRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        Color colorFila = Color.decode("#DEC9A3");
            Color colorLetra = Color.decode("#691A31");
        //Color colorFila2 = Color.decode("#D4C19C");    
        //    #D4C19C
            
        //setLineWrap(true);
        
        if(row % 2 ==0){
            setBackground(colorFila);
            //setForeground(Color.BLACK);
        }else{
            setBackground(Color.WHITE);
            //setBackground(colorFila);
            //setForeground(Color.BLACK);
        }
        
        if(column == 0){
            setForeground(colorLetra);
            //setFont(componente.getFont().deriveFont( Font.BOLD));
        }else{
            //setBackground(Color.WHITE);
            setForeground(Color.BLACK);
            
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }
}