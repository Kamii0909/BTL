package com.BaiTapLon;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;


//tạo Object để render bảng
class CustomRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setHorizontalAlignment(JLabel.CENTER);
        if (row %2 !=0) {
            if (isSelected){
                setBackground(new Color(0xcffdff));
                setBorder(null);
            }
            else {
                setBackground(new Color(0xd1efff));
            }
        }
        else {
            if (isSelected){
                setBackground(new Color(0xcffdff));
                setBorder(null);
            }
            else{
                setBackground(Color.WHITE);
            }

        }
        return c;
    }
}