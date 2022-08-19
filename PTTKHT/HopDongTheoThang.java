package com.BaiTapLon;

import java.util.ArrayList;
import java.util.Date;

class HopDongTheoThang extends HopDong {


    //constructor
    HopDongTheoThang(String tenKH, String soCMND, String bienSoXe, int[] batDau, int[] ketThuc, ArrayList<HopDong> cacHopDong){
        super(tenKH,soCMND,bienSoXe, batDau, ketThuc, true);
        cacHopDong.add(this);
    }

    //method này sẽ nhận thời gian dưới 2 array int[2] = {tháng,năm} convert nó qua Date object cùng lúc tính giá trị
    //từ đó invoke method suaThoiGianHopDong ở class HopDong
    void suaThoiGian(int[] batDau, int[] ketThuc) {

        //convert qua object Date
        Date thoiDiemBatDau = new Date(
                batDau[1]-1900,
                batDau[0]-1,
                1);
        Date thoiDiemKetThuc = new Date(
                ketThuc[1]-1900,
                ketThuc[0]-1,
                ngayCuoiCuaThang(ketThuc));

        super.suaThoiGianHopDong(thoiDiemBatDau, thoiDiemKetThuc, soThangThue(batDau, ketThuc) * 3000 + 1000);
    }
}
