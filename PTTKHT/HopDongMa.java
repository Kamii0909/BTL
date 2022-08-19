package com.BaiTapLon;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/* Hợp Đồng ma dùng để lưu các dữ liệu
* hợp đồng ma không có thật, các field dữ liệu của nó là default
* hợp đồng ma được sử dụng để lưu các dữ liệu tạm thời phục vụ cho
* việc invoke method XuLyDuLieu.suaDuLieu nhằm sửa một hợp đồng có thật
* tạo ra hợp đồng ma tránh việc HopDong.soHopDong bị sai lệch, cũng như không
* ảnh hưởng tới số ID các hợp đồng hay ArrayList chứa tất cả hợp đồng*/

public class HopDongMa {

    //các field dữ liệu giống như một hợp đồng bình thường
    String tenKH;
    String soCMND;
    String bienSoXe;

    Date thoiDiemBatDau;
    Date thoiDiemKetThuc;

    int giaTri;
    byte loaiHopDong;

    final byte HOP_DONG_THEO_THANG = 0;
    final byte HOP_DONG_THEO_NGAY = 1;

    //yeu cau parameter hopDong de tranh thay doi gia tri loaiHopDong
    HopDongMa(HopDong hopDong, String tenKH, String soCMND, String bienSoXe, int[] batDau, int[] ketThuc){

        this.tenKH = tenKH;
        this.soCMND = soCMND;
        this.bienSoXe = bienSoXe;
        loaiHopDong = hopDong.hopDongTheoThang()? HOP_DONG_THEO_THANG : HOP_DONG_THEO_NGAY;

        //chia trường hợp để tạo Date object thời điểm bắt đầu, kết thúc cũng như tính giá trị
        if (loaiHopDong == HOP_DONG_THEO_NGAY){

            thoiDiemBatDau = new Date(
                    batDau[2]-1900,
                    batDau[1]-1,
                    batDau[0]);
            thoiDiemKetThuc = new Date(
                    ketThuc[2]-1900,
                    ketThuc[1]-1,
                    ketThuc[0]);

            giaTri = (int) (HopDong.getDateDiff(thoiDiemBatDau, thoiDiemKetThuc, TimeUnit.DAYS) * 150 + 150);

        }
        else {
            thoiDiemBatDau = new Date(
                    batDau[1]-1900,
                    batDau[0]-1,
                    1);

            thoiDiemKetThuc = new Date(
                    ketThuc[1]-1900,
                    ketThuc[0]-1,
                    HopDong.ngayCuoiCuaThang(ketThuc));

            giaTri = HopDong.soThangThue(batDau, ketThuc) * 3000 + 1000;
        }



    }
}
