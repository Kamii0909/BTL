package com.BaiTapLon;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HopDongTheoNgay extends HopDong{

    //constructor
    HopDongTheoNgay(String tenKH, String soCMND, String bienSoXe, int[] batDau, int[] ketThuc, ArrayList<HopDong> tatCaHopDong){
        super(tenKH, soCMND, bienSoXe, batDau, ketThuc, false);
        tatCaHopDong.add(this);
    }

    //method này sẽ nhận thời gian dưới 2 array int[3] = {ngày,tháng,năm} convert nó qua Date object cùng lúc tính giá trị
    //từ đó invoke method suaThoiGianHopDong ở class HopDong
    void suaThoiGian(int[] batDau, int[] ketThuc){

        //convert về object Date
        Date thoiDiemBatDau = new Date(
                batDau[2]-1900,
                batDau[1]-1,
                batDau[0]);
        Date thoiDiemKetThuc = new Date(
                ketThuc[2]-1900,
                ketThuc[1]-1,
                ketThuc[0]);

        //tính giaTri mới của hợp đồng sau khi sửa thời gian
        int giaTri = (int) (getDateDiff(thoiDiemBatDau, thoiDiemKetThuc, TimeUnit.DAYS) * 150 + 150);

        super.suaThoiGianHopDong(thoiDiemBatDau, thoiDiemKetThuc, giaTri);
    }
}
