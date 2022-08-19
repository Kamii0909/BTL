package com.BaiTapLon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManHinhKetQuaTimKiem extends JFrame {

    ArrayList<HopDong> cacHopDong;

    ManHinhKetQuaTimKiem(ArrayList<HopDong> cacHopDong){

        this.cacHopDong = cacHopDong;
        setLocation(300,150);
        setPreferredSize(new Dimension(1000,500));

        JTable bangKetQua = ManHinhChinh.bangChinh(cacHopDong);
        JScrollPane jScrollPane = new JScrollPane(bangKetQua);

        this.add(jScrollPane);


        pack();
        setVisible(true);
    }


}
