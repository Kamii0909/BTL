package com.BaiTapLon;

import java.util.*;
import java.util.concurrent.TimeUnit;

abstract class HopDong {

    //field giữ giá trị số hợp đồng, dùng để đánh ID cho hợp đồng
    private static int soHopDong = 1;
    //ID của hợp đồng, dùng để phân biệt các hợp đồng với nhau cũng như để sort
    private int ID;

    //khởi tạo các field
    private String tenKH;
    private String soCMND;
    private String bienSoXe;

    private Date thoiDiemBatDau;
    private Date thoiDiemKetThuc;

    private int giaTri;

    /*sau khi em đã hoàn thành phần lớn back-end thì em mới nhận ra field
    loaiHopDong này tương đối thừa, nhưng em tạm thời giữ lại để tránh
    bug không đáng có
     */
    private byte loaiHopDong;
    public final byte HOP_DONG_THEO_THANG = 0;
    public final byte HOP_DONG_THEO_NGAY = 1;


    //constructor
    protected HopDong(String tenKH, String soCMND, String bienSoXe, int[] batDau, int[] ketThuc, boolean hopDongTheoThang){

        this.tenKH = tenKH;
        this.soCMND = soCMND;
        this.bienSoXe = bienSoXe;
        ID = soHopDong++;

        /* chia trường hợp để constructor thực hiện đúng việc khởi tạo hợp đồng theo tháng hay theo ngày
        constructor phần lớn các field chung cho cả 2 loại hợp đồng để thuận tiện việc so sánh thời gian*/
        if(hopDongTheoThang == true){

            loaiHopDong = HOP_DONG_THEO_THANG;

            //khởi tạo các object Date dựa vào int[2] = {tháng, năm}
            thoiDiemBatDau = new Date(
                    batDau[1]-1900,
                    batDau[0]-1,
                    1);

            //method ngayCuoiCuaThang để đảm bảo tính chính xác, thuận tiện so sánh thời gian giữa
            //2 loại hợp đồng là theo tháng và theo ngày
            thoiDiemKetThuc = new Date(
                    ketThuc[1]-1900,
                    ketThuc[0]-1,
                    ngayCuoiCuaThang(ketThuc));

            //giảm 1000 lần so với giá trị thực tế để tránh tràn số khỏi int
            giaTri = soThangThue(batDau, ketThuc) * 3000 + 1000;
        }
        if (hopDongTheoThang == false){
            loaiHopDong = HOP_DONG_THEO_NGAY;

            //object Date theo int[3] ={ngày, tháng, năm}
            thoiDiemBatDau = new Date(
                    batDau[2]-1900,
                    batDau[1]-1,
                    batDau[0]);
            thoiDiemKetThuc = new Date(
                    ketThuc[2]-1900,
                    ketThuc[1]-1,
                    ketThuc[0]);

            //hàm getDateDiff được sử dụng để tính số ngày giữa 2 mốc thời gian
            giaTri = (int) (getDateDiff(thoiDiemBatDau, thoiDiemKetThuc, TimeUnit.DAYS) * 150 + 150);
        }
    }


    //các method getter
    String layTenKH(){
        return tenKH;
    } //tên khách hàng
    String laySoCMND(){
        return soCMND;
    }//số CMND của khách
    String layBienSoXe(){
        return bienSoXe;
    }//biển số xe được thuê
    Date layThoiDiemBatDau(){
        return thoiDiemBatDau;
    }//ngày hợp đồng bắt đầu
    Date layThoiDiemKetThuc(){
        return thoiDiemKetThuc;
    }//ngày hợp đồng kết thúc
    int getGiaTri(){
        return giaTri;
    }//giá trị của hợp đồng
    //trả về true nếu hợp đồng là hợp đồng theo tháng
    boolean hopDongTheoThang(){
        return loaiHopDong == HOP_DONG_THEO_THANG;
    }
    int getID(){
        return ID;
    } //ID của hợp đồng

    //các method setter
    void suaTenKH(String tenKH){
        this.tenKH = tenKH;
    }//tên khách hàng
    void suaSoCMND(String soCMND){
        this.soCMND =soCMND;
    } //số CMND
    void suaBienSoXe(String bienSoXe){
        this.bienSoXe = bienSoXe;
    }//biển số xe
    //method này dùng để sửa thời gian hợp đồng(từ đó thay đổi cả giá trị)
    //các tính toán liên quan tới giá trị khác nhau dựa trên loại hợp đồng được tính sẵn
    //ở các method khác
    protected void suaThoiGianHopDong(Date thoiDiemBatDau, Date thoiDiemKetThuc, int giaTri){
        this.thoiDiemKetThuc = thoiDiemKetThuc;
        this.thoiDiemBatDau = thoiDiemBatDau;
        this.giaTri = giaTri;
    }


    //các static method
    // trả về số tháng thuê của hợp đồng
    protected static int soThangThue(int[] batDau, int[] ketThuc){
        return 12 * ( ketThuc[1] - batDau[1] ) + ketThuc[0]  - batDau[0] + 1 ;
    }
    //method trả về số đơn vị timeUnit giữa date1 và date2, TimeUnit không có
    //đơn vị tháng, thế nên mới phải có thêm method soThangThue
    protected static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
    //tính ngày cuối của tháng dựa vào tháng và năm
    //lich[2] ={tháng,năm}
    protected static int ngayCuoiCuaThang(int[] lich){
        switch (lich[0]){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12: return 31;
            case 4: case 6: case 9: case 11: return 30;
            default: if (lich[1] % 4 ==0 && lich[1] %100 != 0 || lich[1] %400 == 0) {return 29;}
                    else return 28;
        }
    }
}

