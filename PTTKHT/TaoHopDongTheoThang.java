package com.BaiTapLon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class TaoHopDongTheoThang extends JFrame implements ActionListener {

    int soLanNhapSai =0;

    JButton tao;
    JLabel ktra;

    JTextField tenKH;
    JTextField soCMND;
    JTextField bienSoXe;

    JComboBox<Integer> nam;
    JComboBox<Integer> thang;
    JTextField ngay;

    JComboBox<Integer> nam2;
    JComboBox<Integer> thang2;
    JTextField ngay2;

    private ArrayList<HopDong> tatCaHopDong;


    public TaoHopDongTheoThang(ArrayList<HopDong> tatCaHopDong){
        super("Tạo hợp đồng theo tháng");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(420,380);
        this.setLayout(null);
        this.setBackground(Color.GRAY);
        this.setLocationRelativeTo(null);
        this.tatCaHopDong = tatCaHopDong;

        tao = new JButton("Tạo"); this.add(tao);
        tao.setBounds(170,300,80,25);
        tao.setFocusable(false);
        tao.setFont(new Font("Tahoma",Font.PLAIN,12));
        tao.setHorizontalAlignment(JButton.CENTER);
        tao.addActionListener(this);


        JLabel chuTenKH = new JLabel("Tên khách hàng:"); this.add(chuTenKH);
        chuTenKH.setFont(new Font("Tahoma", Font.PLAIN, 12));
        chuTenKH.setBounds(20,20,120,25);

        tenKH = new JTextField(); this.add(tenKH);
        tenKH.setBounds(120,20,250,25);

        JLabel chuSoCMND = new JLabel("Số CMND:"); this.add(chuSoCMND);
        chuSoCMND.setFont(new Font("Tahoma", Font.PLAIN, 12));
        chuSoCMND.setBounds(20,60,120,25);

        soCMND = new JTextField(); this.add(soCMND);
        soCMND.setBounds(120,60,250,25);

        JLabel chuBienSoXe = new JLabel("Biển số xe: "); this.add(chuBienSoXe);
        chuBienSoXe.setFont(new Font("Tahoma", Font.PLAIN, 12));
        chuBienSoXe.setBounds(20,100,120,25);

        bienSoXe = new JTextField(); this.add(bienSoXe);
        bienSoXe.setBounds(120,100,250,25);

        Integer[] arrayNam = new Integer[80];
        for (int i=0;i<80;i++){
            arrayNam[i] = 2030-i;
        }
        nam = new JComboBox<>(arrayNam); this.add(nam);
        nam.setBounds(240,160,60,25);
        nam.setFont(new Font("Tahoma", Font.PLAIN, 12));
        nam.setFocusable(false);
        nam.setSelectedItem(arrayNam[9]);

        JLabel chuNam = new JLabel("Năm"); this.add(chuNam);
        chuNam.setBounds(247,135,60,25);
        chuNam.setFocusable(false);
        chuNam.setFont(new Font("Tahoma", Font.PLAIN, 12));


        Integer[] arrayThang = new Integer[12];
        for (int i=0;i<12;i++){
            arrayThang[i] = i+1;
        }
        thang = new JComboBox<>(arrayThang); this.add(thang);
        thang.setBounds(180,160,60,25);
        thang.setFont(new Font("Tahoma", Font.PLAIN, 12));
        thang.setFocusable(false);

        JLabel chuThang = new JLabel("Tháng"); this.add(chuThang);
        chuThang.setBounds(187,135,60,25);
        chuThang.setFocusable(false);
        chuThang.setFont(new Font("Tahoma", Font.PLAIN, 12));


        ngay = new JTextField("1"); this.add(ngay);
        ngay.setBounds(120,160,60,25);
        ngay.setFont(new Font("Tahoma", Font.PLAIN, 12));
        ngay.setEditable(false);

        JLabel chuNgay = new JLabel("Ngày"); this.add(chuNgay);
        chuNgay.setBounds(127,135,60,25);
        chuNgay.setFocusable(false);
        chuNgay.setFont(new Font("Tahoma", Font.PLAIN, 12));

        JLabel batDauHD = new JLabel("Ngày thuê: "); this.add(batDauHD);
        batDauHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
        batDauHD.setBounds(20,160,100,25);


        nam2 = new JComboBox<>(arrayNam); this.add(nam2);
        nam2.setBounds(240,220,60,25);
        nam2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        nam2.setFocusable(false);
        nam2.setSelectedItem(arrayNam[9]);
        nam2.addActionListener(this);

        JLabel chuNam2 = new JLabel("Năm"); this.add(chuNam2);
        chuNam2.setBounds(247,195,60,25);
        chuNam2.setFocusable(false);
        chuNam2.setFont(new Font("Tahoma", Font.PLAIN, 12));


        thang2 = new JComboBox<>(arrayThang); this.add(thang2);
        thang2.setBounds(180,220,60,25);
        thang2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        thang2.setFocusable(false);
        //
        thang2.addActionListener(this);

        JLabel chuThang2 = new JLabel("Tháng"); this.add(chuThang2);
        chuThang2.setBounds(187,195,60,25);
        chuThang2.setFocusable(false);
        chuThang2.setFont(new Font("Tahoma", Font.PLAIN, 12));


        ngay2 = new JTextField("31"); this.add(ngay2);
        ngay2.setBounds(120,220,60,25);
        ngay2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        ngay2.setEditable(false);

        JLabel chuNgay2 = new JLabel("Ngày"); this.add(chuNgay2);
        chuNgay2.setBounds(127,195,60,25);
        chuNgay2.setFocusable(false);
        chuNgay2.setFont(new Font("Tahoma", Font.PLAIN, 12));

        JLabel ketThucHD = new JLabel("Ngày trả: "); this.add(ketThucHD);
        ketThucHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
        ketThucHD.setBounds(20,220,100,25);


        ktra = new JLabel("Vui lòng kiểm tra lại thông tin");this.add(ktra);
        ktra.setBounds(20,260,200,25);
        ktra.setFont(new Font("Tahoma", Font.PLAIN, 12));
        ktra.setForeground(Color.red);
        ktra.setVisible(false);


        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==tao){

            String tenKH = this.tenKH.getText();
            String soCMND = this.soCMND.getText();
            String bienSoXe = this.bienSoXe.getText();
            int[] batDau = new int[2],ketThuc = new int[2];

            batDau[0] = (int) thang.getSelectedItem();
            batDau[1] = (int) nam.getSelectedItem();

            ketThuc[0] = (int) thang2.getSelectedItem();
            ketThuc[1] = (int) nam2.getSelectedItem();

            if (kiemTraNgayThang(batDau,ketThuc) && kiemTraThongTin(soCMND,bienSoXe)){

                if (bienSoXe.matches("[\\w]{3}-[\\w]{4,5}")){
                    String[] s = bienSoXe.split("-");
                    bienSoXe = s[0] + " - " + s[1];
                }
                new HopDongTheoThang(tenKH, soCMND, bienSoXe, batDau, ketThuc, tatCaHopDong);
                try {
                    XuLyDuLieu.writeFileData(tatCaHopDong);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                this.dispose();

            }
            else{
                ktra.setVisible(true);
                ktra.setText("Vui lòng kiểm tra lại thông tin(" + soLanNhapSai +")");
                soLanNhapSai++;
            }
        }

        if (e.getSource() == thang2 || e.getSource() == nam2){
            ngay2.setText(String.valueOf(HopDong.ngayCuoiCuaThang(new int[]{(int) thang2.getSelectedItem(), (int) nam2.getSelectedItem()})));
        }
    }

    private static boolean kiemTraNgayThang(int[] batDau, int[] ketThuc){
        return batDau[1]<ketThuc[1] || (batDau[1] == ketThuc[1] && batDau[0]<= ketThuc[0]);
    }

    private static boolean kiemTraThongTin(String soCMND, String bienSoXe){


        boolean soCMNDKT = soCMND.matches("[\\d]{9,12}");
        boolean bienSoXeKT = bienSoXe.matches("[\\d]{2}[A-Z]\\s-\\s[\\d]{4,5}")
                            || bienSoXe.matches("[\\d]{2}[A-Z]-[\\d]{4,5}");

        return soCMNDKT && bienSoXeKT;
    }
}
