package com.BaiTapLon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.IntStream;

public class ManHinhSuaHopDong extends JFrame implements ActionListener {
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
    private JButton sua;
    private JLabel canhBao;
    private static int soLanNhapSai = 0;
    private HopDong hopDong;
    private ArrayList<HopDong> tatCaHopDong;

    ManHinhSuaHopDong(HopDong hopDong, ArrayList<HopDong> tatCaHopDong){
        this.hopDong = hopDong;
        this.tatCaHopDong = tatCaHopDong;
        setContentPane(this.PanelChinh);
        setLocation(500,250);

        sua.addActionListener(this);

        IntStream.range(0, 31).forEach(i -> ngayBD.addItem(i + 1));
        IntStream.range(0, 12).forEach(i -> thangBD.addItem(i + 1));
        IntStream.range(0, 80).forEach(i -> namBD.addItem(2030 - i));
        ngayBD.setSelectedItem(hopDong.layThoiDiemBatDau().getDate());
        thangBD.setSelectedItem(hopDong.layThoiDiemBatDau().getMonth()+1); thangBD.addActionListener(this);
        namBD.setSelectedItem(hopDong.layThoiDiemBatDau().getYear()+1900); namBD.addActionListener(this);

        IntStream.range(0, 31).forEach(i -> ngayKT.addItem(i + 1));
        IntStream.range(0, 12).forEach(i -> thangKT.addItem(i + 1));
        IntStream.range(0, 80).forEach(i -> namKT.addItem(2030 - i));
        ngayKT.setSelectedItem(hopDong.layThoiDiemKetThuc().getDate());
        thangKT.setSelectedItem(hopDong.layThoiDiemKetThuc().getMonth()+1); thangKT.addActionListener(this);
        namKT.setSelectedItem(hopDong.layThoiDiemKetThuc().getYear()+1900); namKT.addActionListener(this);

        tenKH.setText(hopDong.layTenKH());
        cBoxTenKH.addActionListener(e-> {
            tenKH.setEnabled(!tenKH.isEnabled());
            tenKH.setText(hopDong.layTenKH());
        });

        soCMND.setText(hopDong.laySoCMND());
        cBoxSoCMND.addActionListener(e -> {
            soCMND.setEnabled(!soCMND.isEnabled());
            soCMND.setText(hopDong.laySoCMND());
        });

        bienSoXe.setText(hopDong.layBienSoXe());
        cBoxBienSoXe.addActionListener(e -> {
            bienSoXe.setEnabled(!bienSoXe.isEnabled());
            bienSoXe.setText(hopDong.layBienSoXe());
        });

        cBoxBatDau.addActionListener(e -> {
            if (!hopDong.hopDongTheoThang()){
                ngayBD.setEnabled(!ngayBD.isEnabled());
            }
            ngayBD.setSelectedItem(hopDong.layThoiDiemBatDau().getDate());
            thangBD.setEnabled(!thangBD.isEnabled()); thangBD.setSelectedItem(hopDong.layThoiDiemBatDau().getMonth()+1);
            namBD.setEnabled(!namBD.isEnabled()); namBD.setSelectedItem(hopDong.layThoiDiemBatDau().getYear()+1900);
        });

        cBoxKetThuc.addActionListener(e -> {
            if (!hopDong.hopDongTheoThang()){
                ngayKT.setEnabled(!ngayKT.isEnabled());
                ngayKT.setSelectedItem(hopDong.layThoiDiemKetThuc().getDate());
            }
            thangKT.setEnabled(!thangKT.isEnabled()); thangKT.setSelectedItem(hopDong.layThoiDiemKetThuc().getMonth()+1);
            namKT.setEnabled(!namKT.isEnabled()); namKT.setSelectedItem(hopDong.layThoiDiemKetThuc().getYear()+1900);
        });


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

            if (hopDong.hopDongTheoThang()){
                ngayKT.setSelectedItem(ngayCuoiThang);
            }
            else{
                while(ngayKT.getItemCount() > ngayCuoiThang){
                    ngayKT.removeItemAt(ngayKT.getItemCount() - 1);
                }
                while(ngayKT.getItemCount()< ngayCuoiThang){
                    ngayKT.addItem(ngayKT.getItemCount() + 1);
                }
            }




        }
        if (e.getSource() == sua){

            String tenKHSua = tenKH.getText();
            String soCMNDSua = soCMND.getText();
            String bienSoXeSua = bienSoXe.getText();

            int[] batDauThang = new int[2]; int[] batDauNgay = new int[3];
            int[] ketThucThang = new int[2]; int[] ketThucNgay = new int[3];

            HopDongMa hopDongSua;

            if (hopDong.hopDongTheoThang()){
                batDauThang[0] = (int) thangBD.getSelectedItem();
                batDauThang[1] = (int) namBD.getSelectedItem();

                ketThucThang[0] = (int) thangKT.getSelectedItem();
                ketThucThang[1] = (int) namKT.getSelectedItem();

                if (kiemTraNgayThangHDThang(batDauThang, ketThucThang) && kiemTraThongTin(soCMNDSua, bienSoXeSua)){
                    hopDongSua = new HopDongMa(hopDong, tenKHSua, soCMNDSua, bienSoXeSua, batDauThang, ketThucThang);
                    XuLyDuLieu.suaDuLieu(hopDong, hopDongSua);
                    try {
                        XuLyDuLieu.writeFileData(tatCaHopDong);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    this.dispose();
                }
                else{
                    canhBao.setVisible(true);
                    canhBao.setText("Vui lòng kiểm tra lại thông tin ("+soLanNhapSai+")");
                    soLanNhapSai++;
                }
            }
            else{
                batDauNgay[0] = (int) ngayBD.getSelectedItem();
                batDauNgay[1] = (int) thangBD.getSelectedItem();
                batDauNgay[2] = (int) namBD.getSelectedItem();

                ketThucNgay[0] = (int) ngayKT.getSelectedItem();
                ketThucNgay[1] = (int) thangKT.getSelectedItem();
                ketThucNgay[2] = (int) namKT.getSelectedItem();


                if (kiemTraNgayThang(batDauNgay, ketThucNgay) && kiemTraThongTin(soCMNDSua, bienSoXeSua)){
                    hopDongSua = new HopDongMa(hopDong, tenKHSua, soCMNDSua, bienSoXeSua, batDauThang, ketThucThang);
                    XuLyDuLieu.suaDuLieu(hopDong, hopDongSua);
                    try {
                        XuLyDuLieu.writeFileData(tatCaHopDong);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    this.dispose();
                }
                else{
                    canhBao.setVisible(true);
                    canhBao.setText("Vui lòng kiểm tra lại thông tin ("+soLanNhapSai+")");
                    soLanNhapSai++;
                }

            }
        }
    }

    private static boolean kiemTraNgayThangHDThang(int[] batDau, int[] ketThuc) {
        return batDau[1]<ketThuc[1] || (batDau[1] == ketThuc[1] && batDau[0]<= ketThuc[0]);
    }

    private static boolean kiemTraNgayThang(int[] batDau, int[] ketThuc){

        boolean kiemTraNgay = batDau[1] < ketThuc[1] || (batDau[1] == ketThuc[1] && batDau[0]<= ketThuc[0]);

        return batDau[2]<ketThuc[2]||(batDau[2]==ketThuc[2] && kiemTraNgay);
    }

    private static boolean kiemTraThongTin(String soCMND, String bienSoXe){


        boolean soCMNDKT = soCMND.matches("[\\d]{9,12}");
        boolean bienSoXeKT = bienSoXe.matches("[\\d]{2}[A-Z]\\s-\\s[\\d]{4,5}")
                || bienSoXe.matches("[\\d]{2}[A-Z]-[\\d]{4,5}");

        return soCMNDKT && bienSoXeKT;
    }
}
