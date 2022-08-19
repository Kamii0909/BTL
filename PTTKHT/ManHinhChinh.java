package com.BaiTapLon;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;


//Class này chịu trách nhiệm hiển thị màn hình chính của project
//Class này dùng LayoutManager null
//Class này code tương đối xấu do em đã chọn sai các field static và non static
//Nếu được code lại em sẽ sửa một số field trở thành static, ví dụ như JTable bang, JScrollPane scrollPane, JPanel chuabang
//Tuy nhiên do thời gian có hạn nên class này vẫn tương đối dở dang, dù đã có thể sử dụng bình thường
public class ManHinhChinh extends JFrame implements ActionListener {

    //ArrayList tatCaHopDong sẽ chứa tất cả hợp đồng
    private ArrayList<HopDong> tatCaHopDong;
    //JButton refresh dùng để invoke method capNhatBang
    private JButton refresh;

    //Bảng và phụ kiện
    private JPanel chuaBang;
    private JScrollPane scrollPane;
    private JTable bang;

    //Menu, MenuItem
    private JMenu taoHD;
    private JMenu timKiemHD;
    private JMenu thuNhap;

    //PopUp Menu
    private static JPopupMenu popupMenu;
    private JMenuItem sua;
    private JMenuItem xoa;


    //Constructor
    ManHinhChinh(ArrayList<HopDong> tatCaHopDong){
        //khởi tạo JFrame với title: "..."
        super("Bài tập lớn: Quản lý cửa hàng cho thuê xe");

        //đưa reference của field tatCaHopDong
        this.tatCaHopDong = tatCaHopDong;

        //Các câu lệnh GUI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,510);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setResizable(false);



        //JButton "Cập nhật bảng", dùng để kích hoạt method capNhatBang
        refresh = new JButton("Cập nhật bảng"); this.add(refresh,BorderLayout.NORTH);
        refresh.setPreferredSize(new Dimension(100,25));
        refresh.setFont(new Font("Tahoma",Font.PLAIN,12));
        refresh.setFocusable(false);
        refresh.addActionListener(this);


        //Tạo Menu
        //Tạo các MenuItem thông qua method menuBar
        //Menu Tạo
        taoHD = menuBar(new String[]{"HĐ tháng","HĐ ngày"});
        taoHD.setText("Tạo");
        //Menu Tìm kiếm
        timKiemHD = menuBar(new String[]{"Theo thông tin hợp đồng", "Theo giá trị hợp đồng"});
        timKiemHD.setText("Tìm kiếm");
        //Menu Thu nhập
        thuNhap = menuBar(new String[]{"Tất cả HĐ","Các HĐ đã lọc"});
        thuNhap.setText("Thu nhập");

        //Thêm ActionListener cho các MenuItem
        taoHD.getItem(0).addActionListener(this);
        taoHD.getItem(1).addActionListener(this);
        timKiemHD.getItem(0).addActionListener(this);
        timKiemHD.getItem(1).addActionListener(this);
        thuNhap.getItem(0).addActionListener(this);
        thuNhap.getItem(1).addActionListener(this);

        //Tạo JMenuBar chứa các MenuItem
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(taoHD);
        menuBar.add(timKiemHD);
        menuBar.add(thuNhap);
        this.setJMenuBar(menuBar);


        //Tạo bảng chứa trong JScrollPane "scrollPane" chứa trong Panel "chuaBang"
        //khởi tạo Panel và cài đặt đồ họa cho Panel
        chuaBang = new JPanel();
        chuaBang.setPreferredSize(new Dimension(1000,500));
        //Tạo Layout cho Panel chuaBang
        BoxLayout verticalLayout = new BoxLayout(chuaBang,BoxLayout.Y_AXIS);
        chuaBang.setLayout(verticalLayout);



        //khởi tạo bảng thông qua method bangChinh
        // Thêm component vào Panel chuaBang
        bang = bangChinh(tatCaHopDong);
        chuaBang.add(bang);
        //khởi tạo JscrollPane và thêm bảng vào đó
        scrollPane = new JScrollPane();
        scrollPane.getViewport().add(bang);
        chuaBang.add(scrollPane);




        //Tạo popup Menu của Bảng
        popupMenu = new JPopupMenu();
        //Background của PopUpMenu
        popupMenu.setBackground(Color.WHITE);

        //Menu Item "Sửa" của PopUpMenu và các điều chỉnh đồ họa
        sua = new JMenuItem("Sửa hợp đồng"); popupMenu.add(sua);
        sua.addActionListener(this);
        sua.setFont(new Font("Tahoma", Font.PLAIN, 12));
        sua.setBackground(Color.white);

        //Menu Item "Xóa" của PopUpMenu và các điều chỉnh đồ họa
        xoa = new JMenuItem("Xóa hợp đồng"); popupMenu.add(xoa);
        xoa.addActionListener(this);
        xoa.setFont(new Font("Tahoma", Font.PLAIN, 12));
        xoa.setBackground(Color.white);

        //Chọn popupMenu làm popupMenu của bang
        bang.setComponentPopupMenu(popupMenu);

        //Thêm Component
        this.add(chuaBang, BorderLayout.CENTER);

        //hiện JFrame
        this.setVisible(true);
    }


    //Method này khởi tạo và trả về JMenu chứa các JMenuItem
    //Tên của các JMenuItem được khởi tạo thông qua Array String[] tenItem
    private static JMenu menuBar(String[] tenItem){

        //khởi tạo JMenu result để trả về
        JMenu result = new JMenu();
        //Font chữ của JMenu result
        result.setFont(new Font("Uni Sans", Font.PLAIN, 12));

        //khởi tạo Array JMenuItem[] cacItem chứa các JMenuItem được
        //tạo với Array String[] tenItem và thêm các JMenuItem này vào JMenu result
        JMenuItem[] cacItem = new JMenuItem[tenItem.length];
        for (int i=0;i< tenItem.length;i++){
            //khởi tạo
            cacItem[i] = new JMenuItem(tenItem[i]);
            //thêm vào result
            result.add(cacItem[i]);
            //set Font
            cacItem[i].setFont(new Font("Uni Sans", Font.PLAIN, 12));
        }
        return result;
    }

    //Method này khởi tạo và trả về một JTable với thông tin đưa vào bởi ArrayList<HopDong> cacHopDong
    public static JTable bangChinh(ArrayList<HopDong> cacHopDong){

        //khởi tạo các data sẽ được đưa vào bảng
        //tạo Array chứa tên các cột
        String[] tenCacCot = {"Tên khách hàng", "Số CMND", "Biển số xe thuê", "Ngày thuê","Ngày trả", "Chi phí (VND)", "Loại hợp đồng" };

        //khởi tạo Array 2 chiều chứa giá trị các cột
        String[][] data = new String[cacHopDong.size()][7];

        //đưa dữ liệu vào array data
        for(int i =0; i<cacHopDong.size();i++){
            //tên khách hàng, số CMND và biển số xe được đưa vào 3 cột đầu
            data[i][0] = cacHopDong.get(i).layTenKH();
            data[i][1] = cacHopDong.get(i).laySoCMND();
            data[i][2] = cacHopDong.get(i).layBienSoXe();

            //thêm các dữ liệu thời gian bắt đầu, kết thúc
            // method dateSangString tương đương với method toString, chỉ là đổi format và loại bỏ các thông tin thừa
            data[i][3] = dateSangString(cacHopDong.get(i).layThoiDiemBatDau());
            data[i][4] = dateSangString(cacHopDong.get(i).layThoiDiemKetThuc());

            //dữ liệu cột giá trị, được Class NumberFormat format lại
            data[i][5] = NumberFormat.getInstance().format(cacHopDong.get(i).getGiaTri() * 1000L);

            //cột dữ liệu hợp đồng theo tháng hay ngày
            data[i][6] = cacHopDong.get(i).hopDongTheoThang() ? "Tháng" : "Ngày";
        }

        //khởi tạo JTable result để trả về
        JTable result = new JTable(data,tenCacCot);

        //Các cài đặt đồ họa, Font, chiều cao cột và độ lớn của bảng
        result.setFont( new Font("Tahoma", Font.PLAIN,12));
        result.setRowHeight(22);
        result.setPreferredSize(new Dimension(500, 400));

        //Các cài đặt đồ họa cho header
        //Lấy header
        JTableHeader header = result.getTableHeader();
        //Font, background, màu chữ, kích thước của header
        header.setFont(new Font("Tahoma", Font.BOLD, 12));
        header.setBackground(new Color(0xa1c2e6));
        header.setForeground(Color.white);
        header.setPreferredSize(new Dimension(100, 22));

        //Thay đổi Renderer của bảng để thay đổi đồ họa từng Cell
        //khởi tạo một instance của Class CustomRenderer
        CustomRenderer customRenderer = new CustomRenderer();
        //Thay đổi CellRenderer của các cột thành CustomRenderer
        for (int i=0;i<7;i++){
            result.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
        }

        //ngăn không cho user edit giá trị của bảng
        result.setDefaultEditor(Object.class,null);

        //set PopUpMenu cho bảng
        result.setComponentPopupMenu(popupMenu);

        return  result;
    }

    //Method này tương đương với toString của java.util.Date nhưng thay đổi format
    private static String dateSangString(Date thoiGian){

        int ngay = thoiGian.getDate();
        int thang = thoiGian.getMonth()+1;
        int nam = thoiGian.getYear()+1900;

        //format là dd - mm - yyyy, 18 - 5 -2021
        return ngay + " - " + thang + " - " + nam;
    }

    //Method này đọc dữ liệu được lưu ở file data.dat và tạo lại bảng mới trên nền dữ liệu đó
    public void capNhatBang(ArrayList<HopDong> tatCaHopDong){

        //khởi tạo bảng mới
        JTable bangMoi = bangChinh(tatCaHopDong);

        //Xóa bảng cũ khỏi
        chuaBang.remove(bang);
        scrollPane.remove(bang);

        //thêm bangMoi vào JPanel chuabang và JScrollPane scrollPane
        chuaBang.add(bangMoi);
        scrollPane.getViewport().add(bangMoi);

        bang = bangMoi;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //tạo hợp đồng theo tháng từ Menu Tạo, chuyển tới màn hình TaoHopDongTheoThang
        if (e.getSource() == taoHD.getItem(0)) new TaoHopDongTheoThang(tatCaHopDong);

        //tạo hợp đồng theo ngày từ Menu Tạo, chuyển tới màn hình TaoHopDongTheoNgay
        if (e.getSource() == taoHD.getItem(1)) new TaoHopDongTheoNgay(tatCaHopDong);

        //Cập nhật bảng
        if (e.getSource() == refresh){
            //đọc tất cả dữ liệu được lưu ở data.dat
            try {
                tatCaHopDong = XuLyDuLieu.docDuLieu();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //invoke method capNhatBang với dữ liệu mới
            capNhatBang(tatCaHopDong);
        }

        //Tìm kiếm hợp đồng theo thông tin hợp đồng từ Menu Tìm kiếm
        if (e.getSource() == timKiemHD.getItem(0)){
            //đọc tất cả dữ liệu được ghi để tránh TH chưa cập nhật bảng
            try {
                tatCaHopDong = XuLyDuLieu.docDuLieu();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //chuyển qua ManHinhTimKiem
            new ManHinhTimKiem(tatCaHopDong);
        }

        //Tìm kiếm hợp đồng theo giá trị, tương tự bên trên
        if (e.getSource() == timKiemHD.getItem(1)){
            try {
                tatCaHopDong = XuLyDuLieu.docDuLieu();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            new ManHinhTimKiemChiPhi(tatCaHopDong);

        }

        //Sửa hợp đồng từ Menu PopUp
        if (e.getSource() == sua){
            try {
                tatCaHopDong = XuLyDuLieu.docDuLieu();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //Chuyển về Màn hình sửa hợp đồng kèm theo số cột mà user đang select
            new ManHinhSuaHopDong(tatCaHopDong.get(bang.getSelectedRow()), tatCaHopDong);
        }

        //Xóa hợp đồng từ MenuPopUp
        if (e.getSource() == xoa){
            //xóa hợp đồng đã chọn khỏi ArrayList
            tatCaHopDong.remove(bang.getSelectedRow());
            //Cập nhật lại bảng với data mới
            try {
                XuLyDuLieu.writeFileData(tatCaHopDong);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            this.capNhatBang(tatCaHopDong);
        }

        //Tính tổng giá trị tất cả hợp đồng
        if (e.getSource() == thuNhap.getItem(0)){
            //đọc dữ liệu đã lưu
            try {
                tatCaHopDong = XuLyDuLieu.docDuLieu();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //invoke method tongGiaTri
            int tongGiaTri = TimKiemDuLieu.tongGiaTri(tatCaHopDong);
            //kết quả được đưa ra bởi JOptionPane
            JOptionPane.showMessageDialog(null, "Tổng giá trị các hợp đồng là " + tongGiaTri, "Tổng giá trị",JOptionPane.INFORMATION_MESSAGE);
        }

        //Tính tổng giá trị một số hợp đồng
        if (e.getSource() == thuNhap.getItem(1)){
            //đọc dữ liệu
            try {
                tatCaHopDong = XuLyDuLieu.docDuLieu();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //chuyển tới màn hình riêng
            new ManHinhTongGiaTri(tatCaHopDong);
        }
    }
}
