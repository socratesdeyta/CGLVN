
package Formato;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;

/**
 *
 * @author socra
 */
public class MultiLineCellRenderer extends DefaultTableRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
       // setLineWrap(true);
        
        if(row % 2 ==0){
            setBackground(Color.LIGHT_GRAY);
            //setForeground(Color.BLACK);
        }else{
            setBackground(Color.WHITE);
            //setForeground(Color.BLACK);
        }
        
        if(column == 0){
            setForeground(Color.RED);
            //setFont(componente.getFont().deriveFont( Font.BOLD));
        }else{
            //setBackground(Color.WHITE);
            setForeground(Color.BLACK);
            
        }
            
    
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
