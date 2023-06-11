package com.app.dict.controllers;

import com.app.dict.base.DoiTuong;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ThoiKyController extends GeneralController implements Initializable {
    public WebView definitionView;
    public Button showTextBtn;
    private final ArrayList<DoiTuong> searchTemp = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (DoiTuong temp : duLieu.getThoiKy()) {
            thoiKyList.add(temp.getSearching());
        }
        listView.setItems(thoiKyList);
    }
    public void setThoiKyListViewItem() {
        thoiKyList.clear();
        if (searchField.getText().equals("")) {
            searchTemp.clear();
            searchTemp.addAll(duLieu.getThoiKy());
        }
        for (DoiTuong temp : searchTemp) {
            thoiKyList.add(temp.getSearching());
        }
        listView.setItems(thoiKyList);
    }
    @FXML
    public void thoiKySearchFieldAction() throws IOException {
        searchTemp.clear();
        thoiKyList.clear();
        String word = searchField.getText();
        int index = duLieu.binaryLookup(0, duLieu.getThoiKy().size() - 1, word, duLieu.getThoiKy());
        if (index < 0) {
            index = duLieu.binaryLookup(0, duLieu.getThoiKy().size() -1, word, duLieu.getThoiKy());
        }
        updateWordInListView(word, index, duLieu.getThoiKy(), searchTemp);
        setThoiKyListViewItem();
    }
    @FXML
    public void showThoiKyDetail() {
        String spelling = listView.getSelectionModel().getSelectedItem();
        if (spelling == null) {
            return;
        }
        int index = Collections.binarySearch(duLieu.getThoiKy(), new DoiTuong(spelling, null));
        String meaning = duLieu.getThoiKy().get(index).getInfo();
        System.out.println(spelling);
        definitionView.getEngine().loadContent(meaning, "text/html");
    }
    public void initThoiKyListView() {
        listView.getItems().clear();
        setThoiKyListViewItem();
    }
    private String sampleNhanVat(){
        String[][] data = {
                { "Tại vị", "257 TCN - 208 TCN / 208 TCN - 179 TCN1" },
                { "Tiền nhiệm", "Sáng lập triều đại" },
                { "Kế nhiệm", "Triều đại sụp đổ" },
                { "Thông tin chung" },
                { "Hậu duệ Hậu duệ", "Hậu duệ Mỵ Châu Mỵ Châu" },
                { "Hậu duệ" },
                { "Tên đầy đủ", "Thục Phán" },
                { "Thụy hiệu", "An Dương Vương" },
                { "Thân phụ", "Thục Chế (theo truyền thuyết)2" },
                { "Sinh", "không rõ" },
                { "Mất", "208 TCN / 179 TCN1" }
        };

        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table style=\"font-family: sans-serif; border-collapse: collapse;\">");

        for (String[] row : data) {
            htmlTable.append("<tr>");
            for (String cell : row) {
                htmlTable.append("<td style=\"padding: 8px;\">").append(cell).append("</td>");
            }
            htmlTable.append("</tr>");
        }

        htmlTable.append("</table>");
        String sample = "<html><head><meta charset=\"UTF-8\"><title>Vũ Đức Đam</title><style>body { font-family:'lucida grande', tahoma, verdana, arial, sans-serif;font-size:14px; }table { font-family:'lucida grande', tahoma, verdana, arial, sans-serif;font-size:14px; }.table-container { text-align: left; }</style></head><body><h1>NHÂN VẬT</h1><h1>Vũ Đức Đam</h1><h2>Thông tin nhân vật</h2><table class=\"table-container\"><tr><th colspan=\"2\">Vũ Đức Đam</th><td></td></tr><tr><th colspan=\"2\">Chức vụ</th><td></td></tr><tr><th colspan=\"2\">Phó Thủ tướng Chính phủ Việt Nam</th><td></td></tr><tr><th scope=\"row\">Nhiệm kỳ</th><td>13 tháng 11 năm 2013 – nay<br>4 năm, 14 ngày</td></tr><tr><th scope=\"row\">Tiền nhiệm</th><td>Nguyễn Thiện Nhân</td></tr><tr><th scope=\"row\">Kế nhiệm</th><td>đương nhiệm</td></tr><tr><th scope=\"row\">Vị trí</th><td>Việt Nam</td></tr><tr><th colspan=\"2\">Bộ trưởng, Chủ nhiệm Văn phòng Chính phủ Việt Nam</th><td></td></tr><tr><th scope=\"row\">Nhiệm kỳ</th><td>3 tháng 8 năm 2011 – 14 tháng 11 năm 2013<br>2 năm, 103 ngày</td></tr><tr><th scope=\"row\">Tiền nhiệm</th><td>Nguyễn Xuân Phúc</td></tr><tr><th scope=\"row\">Kế nhiệm</th><td>Nguyễn Văn Nên</td></tr><tr><th colspan=\"2\">Bí thư Tỉnh ủy Quảng Ninh</th><td></td></tr><tr><th scope=\"row\">Nhiệm kỳ</th><td>17 tháng 3 năm 2010 – 15 tháng 8 năm 2011<br>1 năm, 151 ngày</td></tr><tr><th scope=\"row\">Tiền nhiệm</th><td>Nguyễn Duy Hưng</td></tr><tr><th scope=\"row\">Kế nhiệm</th><td>Phạm Minh Chính</td></tr><tr><th colspan=\"2\">Chủ tịch Ủy ban Nhân dân tỉnh Quảng Ninh</th><td></td></tr><tr><th scope=\"row\">Nhiệm kỳ</th><td>5 tháng 5 năm 2008 – 6 tháng 8 năm 2010<br>2 năm, 93 ngày</td></tr><tr><th scope=\"row\">Tiền nhiệm</th><td>Vũ Nguyên Nhiệm</td></tr><tr><th scope=\"row\">Kế nhiệm</th><td>Nguyễn Văn Đọc</td></tr><tr><th colspan=\"2\">Phó Chủ tịch Ủy ban Quốc gia đổi mới giáo dục Việt Nam</th><td></td></tr><tr><th scope=\"row\">Nhiệm kỳ</th><td>25 tháng 02 năm 2014 – nay<br>3 năm, 275 ngày</td></tr><tr><th colspan=\"2\">Thông tin chung</th><td></td></tr><tr><th scope=\"row\">Đảng phái</th><td>Đảng Cộng sản Việt Nam</td></tr><tr><th scope=\"row\">Sinh</th><td>3 tháng 2, 1963 (54 tuổi)<br>Thanh Miện, Hải Dương, Việt Nam Dân chủ Cộng hòa</td></tr><tr><th scope=\"row\">Học vấn</th><td>Tiến sĩ Kinh tế</td></tr><tr><th scope=\"row\">Trang web</th><td>http://vuducdam.chinhphu.vn/</td></tr></table><h2>Description</h2><p>Vũ Đức Đam (sinh năm 1963) là một chính khách Việt Nam. Ông hiện là Phó Thủ tướng Chính phủ Việt Nam (nhiệm kì 2016-2021) theo dõi, chỉ đạo các lĩnh vực công tác: Giáo dục và đào tạo, Khoa học và công nghệ, Lao động, việc làm và các vấn đề xã hội, Thông tin và truyền thông, Văn hóa, du lịch, thể dục, thể thao, Y tế, dân số, gia đình và trẻ em, Chủ tịch Ủy ban Quốc gia phòng, chống AIDS và phòng chống tệ nạn ma túy. Trong Đảng Cộng sản Việt Nam, ông giữ chức vụ Ủy viên Ban Chấp hành Trung ương Đảng Cộng sản Việt Nam khóa XI, XII1 . Ông có bằng Tiến sĩ Kinh tế và là Phó Thủ tướng trẻ nhất tại thời điểm bổ nhiệm trong Chính phủ của Thủ tướng Nguyễn Tấn Dũng.</p><h2>Related Figures</h2><ul><li>nha-nuoc-viet-nam</li><li>vo-van-kiet</li></ul><h2>Related Places</h2><ul><li>huyen-thanh-mien</li><li>tinh-bac-ninh</li><li>tinh-quang-ninh</li><li>tinh-hai-duong</li></ul><h2>Related Time Periods</h2><ul></ul></body></html>";
        return sample;
        //return htmlTable.toString();
    }
    public void handleClickBtn(ActionEvent actionEvent) {
        String htmlContent = "<html><head><style>"
                + "body { font-family: 'Arial', sans-serif; background-color: #f2f2f2; margin: 20px; }"
                + "h1 { color: blue; font-size: 24px; margin-bottom: 10px; }"
                + "p { font-size: 14px; line-height: 1.5; margin-bottom: 10px; }"
                + "</style></head><body>"
                + "<h1>châu Mỹ</h1>"
                + "<p>Châu Mỹ là một châu lục nằm ở Tây Bán Cầu. Châu lục này bao gồm lục địa Bắc Mỹ, eo đất Trung Mỹ và lục địa Nam Mỹ</p>"
                + "</body></html>";
        String thoiGian = "Tháng 4 năm 1775";
        String diaDiem = "Quảng Nam, Việt Nam";
        String ketQua = "Chúa Trịnh đánh Đàng Trong mở rộng lãnh thổ";

        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<h1 style=\"font-family: sans-serif; color: #902929;\">Trận Cẩm Sa năm 1775</h1>");
        htmlTable.append("<table style=\"font-family: sans-serif; border-collapse: collapse;\">");
        htmlTable.append("<tr><th style=\"background-color: #f2f2f2; padding: 8px; text-align: left;\">Thời gian</th><td style=\"padding: 8px;\">").append(thoiGian).append("</td></tr>");
        htmlTable.append("<tr><th style=\"background-color: #f2f2f2; padding: 8px; text-align: left;\">Địa điểm</th><td style=\"padding: 8px;\">").append(diaDiem).append("</td></tr>");
        htmlTable.append("<tr><th style=\"background-color: #f2f2f2; padding: 8px; text-align: left;\">Kết quả</th><td style=\"padding: 8px;\">").append(ketQua).append("</td></tr>");
        htmlTable.append("</table>");



        htmlContent = htmlTable.toString();

        definitionView.getEngine().loadContent(sampleNhanVat());
    }

    public void searchFieldAction(KeyEvent keyEvent) {
    }

    public void handleClickListView(MouseEvent mouseEvent) {

    }

    public void showDefinition(MouseEvent mouseEvent) {
    }
}
