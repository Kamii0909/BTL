package com.BaiTapLon;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

/* hợp đồng trong file data sẽ được lưu dưới dạng
 * true/false___tên khách hàng___CMND___biển số___(ngày)___tháng___năm___(ngày)___tháng___năm___ID
 * nếu hợp đồng là theo tháng thì true, ngày thì false
 * hợp đồng theo tháng sẽ không có 2 field ngày
 * ngày tháng bắt đầu trước, sau đó là ngày tháng kết thúc*/

public class XuLyDuLieu {



    //method trả về ArrayList những hợp đồng được ghi trong file data.dat
    //hợp đồng trong file sẽ được ghi dưới dạng
    //các field của hợp đồng sẽ được ngăn cách với nhau bởi "___" trong file
    public static ArrayList<HopDong> docDuLieu() throws IOException {

        //khởi tạo ArrayList kết quả
        ArrayList<HopDong> result = new ArrayList<>();

        //kiểm tra xem file có tồn tại không, không thì tạo mới
        new File("data.dat").createNewFile();

        //khởi tạo reader chính
        BufferedReader docDuLieu = new BufferedReader(new FileReader("data.dat"));

        //các field dùng để tạo hợp đồng được reader đọc
        //mỗi line của file là một hợp đồng, array taoHopDong để lưu các field được lưu trong file của hợp đồng
        String[] taoHopDong;

        //2 array này phục vụ cho constructor của hợp đồng theo tháng
        int[] batDauThang = new int[2];
        int[] ketThucThang = new int[2];

        //tương tự, cho hợp đồng theo ngày
        int[] batDauNgay = new int[3];
        int[] ketThucNgay = new int[3];

        //kiểm tra xem hợp đồng theo tháng hay ngày
        boolean hopDongTheoThang;

        //s sẽ là string được đọc, sau được được split vào taoHopDong[]
        String s;

        //đọc file tới khi dòng mới là null
        while ((s = docDuLieu.readLine()) != null){
            //array String taoHopDong được tạo nhờ split String s với regex "___"
            taoHopDong = s.split("___");

            //parse string để tìm boolean đặt ở vị trí đầu, xem nó là true hay false
            hopDongTheoThang = Boolean.parseBoolean(taoHopDong[0]);

            //ID không dùng trong constructor

            //nếu là true, aka hợp đồng theo tháng
            if (hopDongTheoThang == true) {

                //các array này chứa các giá trị phục vụ cho constructor
                for (int i = 0; i < 2; i++) {
                    batDauThang[i] = parseInt(taoHopDong[i + 4]);
                    ketThucThang[i] = parseInt(taoHopDong[i + 6]);
                }

                //tạo hợp đồng theo dữ liệu đã lưu
                //HopDongTheoThang(tenKH,CMND,biển số, int[2] thời điểm bắt đầu, int[2] thời điêm kết thúc, arraylist esult là nơi chứa hợp đồng)
                HopDongTheoThang hopDong = new HopDongTheoThang(taoHopDong[1], taoHopDong[2], taoHopDong[3], batDauThang, ketThucThang, result);
            }//tương tự như trường hợp theo tháng
            if (hopDongTheoThang == false){
                //array này là int[3] ={ngày, tháng, năm}
                for (int i = 0; i < 3; i++){
                    batDauNgay[i] =parseInt(taoHopDong[i+4]);
                    ketThucNgay[i] = parseInt(taoHopDong[i+7]);
                }

                //tạo hợp đồng đặt trong arraylist result
                HopDongTheoNgay hopDong = new HopDongTheoNgay(taoHopDong[1],taoHopDong[2],taoHopDong[3],batDauNgay,ketThucNgay,result);

            }

        }
        //đóng reader
        docDuLieu.close();

        //trả về arraylist chứa các hợp đồng đã được đọc
        return result;

    }

    //method này sẽ append một dòng dữ liệu, tương ứng một hợp đồng vào data.dat
    private static void appendDuLieu(HopDong hopDong) throws IOException{

        //string regex để thuận tiện cho việc đọc code
        String regex = "___";

        //kiểm tra file, tạo nếu cần
        new File("data.dat").createNewFile();

        //reader
        BufferedWriter bw = new BufferedWriter(new FileWriter("data.dat",true));


        if (hopDong.hopDongTheoThang() == true){

            //2 array list này để thuận tiện cho việc đọc code
            int[] batDau = { hopDong.layThoiDiemBatDau().getMonth() + 1, hopDong.layThoiDiemBatDau().getYear() + 1900};
            int[] ketThuc = { hopDong.layThoiDiemKetThuc().getMonth() + 1, hopDong.layThoiDiemKetThuc().getYear() + 1900};

            //viết đúng format của một hợp đồng như đã ghi ở bên trên
            bw.write("true" +  regex + hopDong.layTenKH()  //dòng này ghi boolean chỉ đây là hđ theo tháng và tên kh
                    + regex + hopDong.laySoCMND()  //lần lượt các dòng 2,3 là CMND và biển số xe
                    + regex + hopDong.layBienSoXe()
                    + regex + batDau[0] + regex + batDau[1] //tháng và năm bắt đầu
                    + regex + ketThuc[0] + regex + ketThuc[1] //tháng và năm kết thúc
                    + regex + hopDong.getID() + '\n');//ID của hợp đồng
        }
        else{

            //tương tự như trên, đây là dành cho hợp đồng theo ngày
            int[] batDau = {hopDong.layThoiDiemBatDau().getDate(),
                            hopDong.layThoiDiemBatDau().getMonth() + 1 ,
                            hopDong.layThoiDiemBatDau().getYear() + 1900 };

            int[] ketThuc = {hopDong.layThoiDiemKetThuc().getDate(),
                             hopDong.layThoiDiemKetThuc().getMonth() + 1,
                             hopDong.layThoiDiemKetThuc().getYear() + 1900};

            bw.write("false" + regex + hopDong.layTenKH()
                    + regex + hopDong.laySoCMND()
                    + regex + hopDong.layBienSoXe()
                    + regex + batDau[0] + regex + batDau[1] + regex + batDau[2]
                    + regex + ketThuc[0] + regex + ketThuc[1] + regex + ketThuc[2]
                    + regex + hopDong.getID() + '\n');
        }
        bw.close();

    }

    //method này sẽ overwrite data.dat bằng các hợp đồng được lưu trong ArrayList cacHopDong ở parameter
    public static void writeFileData(ArrayList<HopDong> cacHopDong) throws IOException{

        //reset file nếu đã tồn tại
        FileWriter reset = new FileWriter("data.dat", false);
        reset.close();

        //ghi lần lượt từng hợp đồng
        //lưu ý quá trình này sẽ tạo ra cacHopDong.size() BufferedWriter tương đối phí phạm
        // , tuy nhiên em không đủ thời gian optimize lại
        for (int i =0; i<cacHopDong.size();i++){
            appendDuLieu(cacHopDong.get(i));
        }

    }

    /*method này sẽ sửa dữ liệu một hợp đồng bằng với dữ liệu một
     Hợp đồng ma(hợp đồng không có thật, chỉ dùng để chứa dữ liệu)
     method này nên đi kèm ngay sau đó với writeDataFile để lưu dữ liệu đã sửa
     điều này cần việc hopDongCanSua ở trong 1 ArrayList tatCaCacHopDong*/
    public static void suaDuLieu(HopDong hopDongCanSua, HopDongMa hopDongSua){
        hopDongCanSua.suaTenKH(hopDongSua.tenKH);
        hopDongCanSua.suaSoCMND(hopDongSua.soCMND);
        hopDongCanSua.suaBienSoXe(hopDongSua.bienSoXe);
        hopDongCanSua.suaThoiGianHopDong(hopDongSua.thoiDiemBatDau, hopDongSua.thoiDiemKetThuc, hopDongSua.giaTri);
    }

    private XuLyDuLieu(){

    }
}
