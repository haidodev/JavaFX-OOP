package com.app.dict.base;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public class SuKienModel extends Model {
    private String thoiGian;
    private String diaDiem;
    private String ketQua;

    private Set<String> cacNhanVatLienQuan;
    private Set<String> cacDiTichLienQuan;
    public List<String> getcacNhanVatLienQuan() {
        return new ArrayList<>(this.cacNhanVatLienQuan);
    }
    public List<String> getcacDiTichLienQuan() {
        return new ArrayList<>(this.cacDiTichLienQuan);
    }
    public SuKienModel(String tenModel, String code, List<String> moTa, String thoiGian, String diaDiem, String ketQua
            , Set<String> cacNhanVatLienQuan, Set<String> cacDiTichLienQuan) {
        super(tenModel, moTa);
        setCode(code);
        setThoiGian(thoiGian);
        setDiaDiem(diaDiem);
        setKetQua(ketQua);
        setCacNhanVatLienQuan(cacNhanVatLienQuan);
        setCacDiTichLienQuan(cacDiTichLienQuan);
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }

    public void setCacNhanVatLienQuan(Set<String> cacNhanVatLienQuan) {
        this.cacNhanVatLienQuan = cacNhanVatLienQuan;
    }

    public void setCacDiTichLienQuan(Set<String> cacDiTichLienQuan) {
        this.cacDiTichLienQuan = cacDiTichLienQuan;
    }

    @Override
    public String toHTML() {
        StringBuilder htmlBuilder = new StringBuilder();

        // Start the HTML structure
        htmlBuilder.append("<html>");
        htmlBuilder.append("<i>").append(this.tenModel).append("</i>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body contenteditable=\"true\">");
        htmlBuilder.append("<meta charset=\"UTF-8\">");
        // htmlBuilder.append("<title>").append(getName()).append("</title>");
        htmlBuilder.append("<style>");
        htmlBuilder.append("body { font-family:'lucida grande', tahoma, verdana, arial, sans-serif;font-size:14px; }");
        htmlBuilder.append("table { font-family:'lucida grande', tahoma, verdana, arial, sans-serif;font-size:14px; }");
        htmlBuilder.append(".table-container { text-align: left; }");
        htmlBuilder.append("</style>");



        // Add the name as a heading
        // htmlBuilder.append("<h1>").append("NHÂN VẬT").append("</h1>");
        // htmlBuilder.append("<h1>").append(getName()).append("</h1>");

        // Add the code
        // htmlBuilder.append("<p><strong>Code:</strong> ").append(getCode()).append("</p>");


        // Add the description
        htmlBuilder.append("<h2>Description</h2>");
        htmlBuilder.append("<p>").append(this.thoiGian).append("</p>");
        htmlBuilder.append("<p>").append(this.diaDiem).append("</p>");
        htmlBuilder.append("<p>").append(this.ketQua).append("</p>");


        // Add the related figures
        htmlBuilder.append("<h2>Related Figures</h2>");
        if (this.cacNhanVatLienQuan != null) {
            htmlBuilder.append("<ul>");
            for (String figure : this.cacNhanVatLienQuan) {
                htmlBuilder.append("<li>").append(figure).append("</li>");
            }
            htmlBuilder.append("</ul>");
        }


        // Add the related places
        htmlBuilder.append("<h2>Related Places</h2>");
        if (this.cacDiTichLienQuan != null) {
            htmlBuilder.append("<ul>");
            for (String place : this.cacDiTichLienQuan) {
                htmlBuilder.append("<li>").append(place).append("</li>");
            }
            htmlBuilder.append("</ul>");
        }


        // Close the HTML structure
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        return htmlBuilder.toString();
    }

    @Override
    public String toString() {
        return  "\n{ \"Id\":\"" + this.id + "\", "
                + "\n\"Tên\":\"" + this.tenModel + "\", "
                + "\n\"Thời gian\":\"" + this.thoiGian + "\", "
                + "\n\"Địa điểm\":\"" + this.diaDiem + "\", "
                + "\n\"kết quả\":\"" + this.ketQua + "\", "
                + "\n\"Nhân vật liên quan code\":\"" + this.cacNhanVatLienQuan + "\", "
                + "\n\"Địa điểm liên quan code\":\"" + this.cacDiTichLienQuan + "\" }" + "\n";
    }
}
