package com.app.dict.base;

import java.util.*;

public class ThoiKyModel extends Model
{
    private Set<String> cacNhanVatLienQuan;
    private Set<String> cacDiTichLienQuan;

    public List<String> getcacNhanVatLienQuan() {
        return new ArrayList<>(this.cacNhanVatLienQuan);
    }

    public ThoiKyModel(String name, List<String> moTa, String code, Set<String> cacNhanVatLienQuan
            , Set<String> cacDiTichLienQuan)
    {
        super(name, moTa);
        setCode(code);
        setcacNhanVatLienQuan(cacNhanVatLienQuan);
        setcacDiTichLienQuan(cacDiTichLienQuan);
    }
    public void setcacNhanVatLienQuan(Set<String> cacNhanVatLienQuan) {
        this.cacNhanVatLienQuan = cacNhanVatLienQuan;
    }

    public void setcacDiTichLienQuan(Set<String> cacDiTichLienQuan) {
        this.cacDiTichLienQuan = cacDiTichLienQuan;
    }

    @Override
    public String toHTML()
    {
        StringBuilder htmlBuilder = new StringBuilder();

        // Start the HTML structure
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body contenteditable=\"true\">");
        htmlBuilder.append("<i>").append(this.tenModel).append("</i>");
        htmlBuilder.append("<meta charset=\"UTF-8\">");
        // htmlBuilder.append("<title>").append(getName()).append("</title>");
        htmlBuilder.append("<style>");
        htmlBuilder.append("body { font-family:'lucida grande', tahoma, verdana, arial, sans-serif;font-size:14px; }");
        htmlBuilder.append("</style>");

        // Add the name as a heading
        // htmlBuilder.append("<h1>").append("NHÂN VẬT").append("</h1>");
        // htmlBuilder.append("<h1>").append(getName()).append("</h1>");

        // Add the code
        // htmlBuilder.append("<p><strong>Code:</strong> ").append(getCode()).append("</p>");

        // Add the infobox
        htmlBuilder.append("<h2>Thông tin thời kỳ</h2>");

        // Add the description
        if (this.moTa != null) {
            for (String desc : this.moTa) {
                htmlBuilder.append("<p>").append(desc).append("</p>");
            }
        }


        // Add the related figures
        if (this.cacNhanVatLienQuan != null)
        {
            htmlBuilder.append("<h2>Related Figures</h2>");
            htmlBuilder.append("<ul>");
            for (String figure : this.cacNhanVatLienQuan) {
                htmlBuilder.append("<li>").append(figure).append("</li>");
            }
            htmlBuilder.append("</ul>");
        }

        // Close the HTML structure
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        return htmlBuilder.toString();
    }

    public Set<String> getCacNhanVatLienQuan() {
        return cacNhanVatLienQuan;
    }

    public Set<String> getCacDiTichLienQuan() {
        return cacDiTichLienQuan;
    }

    @Override
    public String toString() {
        return "\n{ \"Tên thời kỳ\":\"" + this.tenModel + "\", "
                + "\n\"Miêu tả\":\"" + this.moTa + "\", "
                + "\n\"Nhân vật liên quan code\":\"" + this.cacNhanVatLienQuan + "\" }" + "\n";
    }
}