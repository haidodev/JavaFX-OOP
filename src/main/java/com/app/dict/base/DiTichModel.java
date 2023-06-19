package com.app.dict.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DiTichModel extends Model{
    private Set<String> cacNhanVatLienQuan;
    public List<String> getcacNhanVatLienQuan() {
        return new ArrayList<>(this.cacNhanVatLienQuan);
    }
    public DiTichModel(String tenModel, List<String> moTa, String code, Set<String> cacNhanVatLienQuan)
    {
        super(tenModel, moTa);
        setCode(code);
        setcacNhanVatLienQuan(cacNhanVatLienQuan);
    }

    public void setcacNhanVatLienQuan(Set<String> cacNhanVatLienQuan)
    {

        this.cacNhanVatLienQuan = cacNhanVatLienQuan;
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
        if (this.moTa != null)
        {
            htmlBuilder.append("<h2>Description</h2>");
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

    @Override
    public String toString() {
        return  "\n{ \"Id\":\"" + this.id + "\", "

                + "\n\"Địa danh\":\"" + this.tenModel + "\", "
                + "\n\"Code\":\"" + this.code + "\", "
                + "\n\"Miêu tả\":\"" + this.moTa + "\", "
                + "\n\"Nhân vật liên quan code\":\"" + this.cacNhanVatLienQuan + "\" }" + "\n";
    }
}
