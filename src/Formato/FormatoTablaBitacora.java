package Formato;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatoTablaBitacora extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Color colorFila = Color.decode("#DEC9A3");
        Color colorLetra = Color.decode("#691A31");
        
        Color colorSeleccion = Color.decode("#5d292b");

        if (row % 2 == 0) {
            this.setBackground(colorFila);
        } else {
            this.setBackground(Color.WHITE);
        }

        if (column == 0) {
            this.setForeground(colorLetra);
        } else {
            this.setForeground(Color.BLACK);
        }
        
        try{

        if (table.getValueAt(row, 5).equals("Cancelado")) {
            this.setBackground(Color.DARK_GRAY);
            this.setForeground(Color.WHITE);
            this.setFont(this.getFont().deriveFont(Font.BOLD));
        } else {
        }
        }catch(Exception e){
        System.out.println("Cuando busca un registro la fila cambia, por eso marca error");
        }
        
        if (isSelected) {
            //this.setOpaque(true);
            this.setBackground(colorSeleccion);
                        this.setForeground(Color.WHITE);
        }
        return this;
    }
}

//import java.awt.Color;
//import java.awt.Component;
//import javax.swing.JLabel;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableCellRenderer;
//import org.jdesktop.swingx.renderer.DefaultTableRenderer;
//
//public class FormatoTabla extends DefaultTableCellRenderer {
//
//    private int columna;
//
//    public FormatoTabla() {
//
//    }
//
//    public FormatoTabla(int Colpatron) {
//        this.columna = Colpatron;
//        System.out.println("Columna -->" + columna);
//    }
//
//    @Override
//    public Component getTableCellRendererComponent(JTable table,
//            Object value,
//            boolean isSelected,
//            boolean hasFocus,
//            int row,
//            int column) {
//        
//              super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
//
//        if (table.getValueAt(row, 6).equals("Cancelado")) {
//            this.setOpaque(true);
//            this.setBackground(Color.YELLOW);
//            this.setForeground(Color.BLACK);
////            if (isSelected) {
////                //              this.setOpaque(true);
////                this.setBackground(Color.MAGENTA);
////                this.setForeground(Color.WHITE);
//            }
//
////        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
////        
////         if (table.getValueAt(row,2).equals("N")){
////          this.setOpaque(true);
////          this.setBackground(Color.YELLOW);
////          this.setForeground(Color.BLACK);
////          if (isSelected){
////                this.setOpaque(true);
////                this.setBackground(Color.MAGENTA);
////                this.setForeground(Color.WHITE); 
////          }
////        if(value instanceof String)
////        {
////            String Valor = (String) value;
////            System.out.println("Valor " +Valor);
////            int column = 8;
////            if(column==8)
////            {
////            if(Valor == "Cancelado")
////            cell.setBackground(Color.yellow);
////            cell.setForeground(Color.red);
////        }
////        }
//            Color colorFila = Color.decode("#DEC9A3");
//            Color colorLetra = Color.decode("#691A31");
//            //Color colorFila2 = Color.decode("#D4C19C");    
//            //    #D4C19C
//            System.out.println("row -->" + row);
//
////        if (table.getValueAt(row, columna).equals("LVN-0001")) {
////            this.setForeground(Color.RED);
////        } else if (table.getValueAt(row, columna).equals("B")) {
////            this.setForeground(Color.BLUE);
////        } else if (table.getValueAt(row, columna).equals("C")) {
////            this.setForeground(Color.GREEN);
////        }
//            //setLineWrap(true);
//            if (row % 2 == 0) {
//                setBackground(colorFila);
//                //setForeground(Color.BLACK);
//            } else {
//                setBackground(Color.WHITE);
//                //setBackground(colorFila);
//                //setForeground(Color.BLACK);
//            }
//
//            if (column == 0) {
//                setForeground(colorLetra);
//                //setFont(componente.getFont().deriveFont( Font.BOLD));
//            } else {
//                //setBackground(Color.WHITE);
//                setForeground(Color.BLACK);
//            }
//
//            return this;
//        }
//
//    }
