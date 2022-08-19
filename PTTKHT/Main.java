package com.BaiTapLon;

import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args)  {

        try{
            ArrayList<HopDong> tatCaHopDong = XuLyDuLieu.docDuLieu();
            new ManHinhChinh(tatCaHopDong);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }








    }
}