/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formato;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author socra
 */
public class LineWrapCellRenderer extends JTextArea implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

int fontHeight = this.getFontMetrics(this.getFont()).getHeight();
int textLength = this.getText().length();
int lines = textLength / this.getColumns() +1;//+1, cause we need at least 1 row.           
int height = fontHeight * lines;            
table.setRowHeight(row, height);

//        this.setText((String)value);
        this.setWrapStyleWord(true);            
        this.setLineWrap(true);      
  //      this.setBackground(Color.YELLOW);
        return this;
    }

}