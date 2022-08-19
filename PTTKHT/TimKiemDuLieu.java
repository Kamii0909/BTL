package com.BaiTapLon;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class TimKiemDuLieu {
    private TimKiemDuLieu(){

    }

    //method de tim kiem cac hop dong co cung ten KH
    public static void timTenKH(String tuKhoa, ArrayList<HopDong> cacHopDong){

        int counter=0;

        while (cacHopDong.size()>counter){
            //kiem tra xem ten KH co trung voi tu khoa, neu trung thi them vao arraylist ketqua
            if (!cacHopDong.get(counter).layTenKH().contains(tuKhoa)) cacHopDong.remove(counter);
            else counter++;
        }
    }

    //method de tim hop dong co cung soCMND
    public static void timSoCMND(String tuKhoa, ArrayList<HopDong> cacHopDong){ ;

        int counter=0;

        while (cacHopDong.size()>counter){
            if (!cacHopDong.get(counter).laySoCMND().contains(tuKhoa)) cacHopDong.remove(counter);
            else counter++;
        }

    }

    //method de tim kiem cac hop dong co cung bien so xe
    public static void timBienSoXe(String tuKhoa, ArrayList<HopDong> cacHopDong){

        int counter=0;

        while (cacHopDong.size()>counter){
            if (!cacHopDong.get(counter).layBienSoXe().contains(tuKhoa)) cacHopDong.remove(counter);
            else counter++;
        }
    }

    //method de tim kiem cac hop dong co gia tri tu giaTriNho toi giaTriLon
    public static void timGiaTri(long giaTriNho, long giaTriLon, ArrayList<HopDong> cacHopDong){

        int counter =0;


        while (cacHopDong.size()>counter){
            if (cacHopDong.get(counter).getGiaTri() * 1000L< giaTriNho ||
                cacHopDong.get(counter).getGiaTri() * 1000L > giaTriLon){
                cacHopDong.remove(counter);
            }
            else{
                counter++;
            }
        }

    }

    //method de tim kiem cac hop dong co bat dau sau thoiGianBatDau
    public static void timThoiGianBatDau(Date thoiGianBatDau, ArrayList<HopDong> cacHopDong){

        int counter=0;

        while (cacHopDong.size()>counter){
            if (cacHopDong.get(counter).layThoiDiemBatDau().before(thoiGianBatDau)) cacHopDong.remove(counter);
            else counter++;
        }
    }

    //method de tim kiem hop dong ma thoi gian ket thuc truoc thoiGianKetThuc
    public static void timThoiGianKetThuc(Date thoiGianKetThuc, ArrayList<HopDong> cacHopDong){

        int counter=0;

        while (cacHopDong.size()>counter){
            if (cacHopDong.get(counter).layThoiDiemKetThuc().after(thoiGianKetThuc)) cacHopDong.remove(counter);
            else counter++;
        }
    }

    //method de tinh tong gia tri cac hop dong trong 1 arraylist
    public static int tongGiaTri(ArrayList<HopDong> cacHopDong){

        int tongGiaTri =0 ;

        for (int i=0; i< cacHopDong.size(); i++){
            tongGiaTri += cacHopDong.get(i).getGiaTri();
        }
        return tongGiaTri;
    }

}
