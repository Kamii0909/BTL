package com.BaiTapLon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManHinhTimKiemChiPhi extends JFrame implements ActionListener {
    private JPanel panel1;
    private JTextField giaNho;
    private JTextField giaLon;
    private JPanel panelChinh;
    private JButton dongY;
    private JCheckBox cBoxGTNho;
    private JCheckBox cBoxGTLon;
    ArrayList<HopDong> tatCaHopDong;

    ManHinhTimKiemChiPhi(ArrayList<HopDong> tatCaHopDong){
        super("Tìm kiếm theo giá trị hợp đồng");
        this.tatCaHopDong = tatCaHopDong;

        dongY.addActionListener(this);

        cBoxGTNho.addActionListener(e -> giaNho.setEnabled(!isEnabled()));
        cBoxGTLon.addActionListener(e -> giaLon.setEnabled(!isEnabled()));

        setContentPane(panelChinh);
        setLocation(new Point(400,200));
        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==dongY){
            long giaNho = Long.MIN_VALUE;
            long giaLon = Long.MAX_VALUE;
            if (cBoxGTNho.isSelected()){
                giaNho = Long.parseLong(this.giaNho.getText());
            }
            if (cBoxGTLon.isSelected()){
                giaLon = Long.parseLong(this.giaLon.getText());
            }

            TimKiemDuLieu.timGiaTri(giaNho, giaLon, tatCaHopDong);
            this.dispose();
            new ManHinhKetQuaTimKiem(tatCaHopDong);
        }
    }
}
