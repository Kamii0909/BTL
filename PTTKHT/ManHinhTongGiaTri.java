package com.BaiTapLon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.IntStream;

public class ManHinhTongGiaTri extends JFrame implements ActionListener {
    private JPanel PanelChinh;
    private JCheckBox cBoxTenKH;
    private JTextField tenKH;
    private JCheckBox cBoxSoCMND;
    private JTextField soCMND;
    private JCheckBox cBoxBienSoXe;
    private JTextField bienSoXe;
    private JCheckBox cBoxBatDau;
    private JComboBox ngayBD;
    private JComboBox thangBD;
    private JComboBox namBD;
    private JCheckBox cBoxKetThuc;
    private JComboBox ngayKT;
    private JComboBox thangKT;
    private JComboBox namKT;
    private JButton dongY;
    private JPanel panelChinh;
    private JCheckBox cBoxGTNho;
    private JTextField giaNho;
    private JCheckBox cBoxGTLon;
    private JTextField giaLon;
    private JPanel panel1;
    private ArrayList<HopDong> tatCaHopDong;


    ManHinhTongGiaTri(ArrayList<HopDong> tatCaHopDong){
        super("Tổng giá trị các hợp đồng");
        this.tatCaHopDong = tatCaHopDong;

        IntStream.range(0, 31).forEach(i -> ngayBD.addItem(i + 1));
        IntStream.range(0, 12).forEach(i -> thangBD.addItem(i + 1));
        IntStream.range(0, 80).forEach(i -> namBD.addItem(2030 - i)); namBD.setSelectedItem(2021);

        IntStream.range(0, 31).forEach(i -> ngayKT.addItem(i + 1));
        IntStream.range(0, 12).forEach(i -> thangKT.addItem(i + 1));
        IntStream.range(0, 80).forEach(i -> namKT.addItem(2030 - i)); namKT.setSelectedItem(2021);


        dongY.addActionListener(this);

        cBoxTenKH.addActionListener(e -> tenKH.setEnabled(!tenKH.isEnabled()));
        cBoxSoCMND.addActionListener(e -> soCMND.setEnabled(!soCMND.isEnabled()));
        cBoxBienSoXe.addActionListener(e -> bienSoXe.setEnabled(!bienSoXe.isEnabled()));

        cBoxBatDau.addActionListener(e -> {
            ngayBD.setEnabled(!ngayBD.isEnabled());
            thangBD.setEnabled(!thangBD.isEnabled());
            namBD.setEnabled(!namBD.isEnabled());
        });

        cBoxKetThuc.addActionListener(e -> {
            ngayKT.setEnabled(!ngayKT.isEnabled());
            thangKT.setEnabled(!thangKT.isEnabled());
            namKT.setEnabled(!namKT.isEnabled());
        });

        thangBD.addActionListener(this);
        namBD.addActionListener(this);
        thangKT.addActionListener(this);
        namKT.addActionListener(this);


        cBoxGTNho.addActionListener(e -> {
            giaNho.setEnabled(!giaNho.isEnabled());
            giaNho.setText("0");
        });
        cBoxGTLon.addActionListener(e -> {
            giaLon.setEnabled(!giaLon.isEnabled());
            giaLon.setText("150000");
        });



        setLocation(new Point(400,200));
        setContentPane(panel1);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == thangBD || e.getSource() == namBD){
            int ngayCuoiThang = HopDong.ngayCuoiCuaThang(new int[]{(int) thangBD.getSelectedItem(), (int) namBD.getSelectedItem()});

            while(ngayBD.getItemCount() > ngayCuoiThang){
                ngayBD.removeItemAt(ngayBD.getItemCount() - 1);
            }
            while(ngayBD.getItemCount()< ngayCuoiThang){
                ngayBD.addItem(ngayBD.getItemCount() + 1);
            }
        }
        if (e.getSource() == thangKT || e.getSource() == namKT){
            int ngayCuoiThang = HopDong.ngayCuoiCuaThang(new int[]{(int) thangKT.getSelectedItem(), (int) namKT.getSelectedItem()});

            while(ngayKT.getItemCount() > ngayCuoiThang){
                ngayKT.removeItemAt(ngayKT.getItemCount() - 1);
            }
            while(ngayKT.getItemCount()< ngayCuoiThang){
                ngayKT.addItem(ngayKT.getItemCount() + 1);
            }
        }
        if (e.getSource() == dongY){

            long giaNho = Long.MIN_VALUE;
            long giaLon = Long.MAX_VALUE;

            if (cBoxTenKH.isSelected()){
                TimKiemDuLieu.timTenKH(tenKH.getText(), tatCaHopDong);
            }
            if (cBoxSoCMND.isSelected()){
                TimKiemDuLieu.timSoCMND(soCMND.getText(), tatCaHopDong);
            }

            if (cBoxBienSoXe.isSelected()){
                TimKiemDuLieu.timBienSoXe(bienSoXe.getText(), tatCaHopDong);
            }
            if (cBoxBatDau.isSelected()){
                int namBD = (int) this.namBD.getSelectedItem() - 1900;
                int thangBD = (int) this.thangBD.getSelectedItem() - 1;
                int ngayBD = (int) this.ngayBD.getSelectedItem();
                Date thoiGianBatDau = new Date(namBD, thangBD, ngayBD);
                TimKiemDuLieu.timThoiGianBatDau(thoiGianBatDau, tatCaHopDong);
            }
            if (cBoxKetThuc.isSelected()){
                int namKT = (int) this.namKT.getSelectedItem() - 1900;
                int thangKT = (int) this.thangKT.getSelectedItem() - 1;
                int ngayKT = (int) this.ngayKT.getSelectedItem();
                Date thoiGianKetThuc = new Date(namKT, thangKT, ngayKT);
                TimKiemDuLieu.timThoiGianKetThuc(thoiGianKetThuc, tatCaHopDong);
            }
            if (cBoxGTNho.isSelected()){
                giaNho = Long.parseLong(this.giaNho.getText());
            }
            if (cBoxGTLon.isSelected()){
                giaLon = Long.parseLong(this.giaLon.getText());
            }
            TimKiemDuLieu.timGiaTri(giaNho, giaLon, tatCaHopDong);
            JOptionPane.showMessageDialog(
                    null,
                    "Tổng giá trị các hợp đồng đã chọn là " + TimKiemDuLieu.tongGiaTri(tatCaHopDong),
                    "Tổng giá trị các hợp đồng đã lọc",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
