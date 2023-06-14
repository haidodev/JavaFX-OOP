package com.app.dict.base;

import com.app.dict.util.Config;

import java.util.List;

public class LeHoiModel extends Model
{
    private String thoiGian;
    private String diaDiem;
    private String nhanVatLienQuan;
    private String toChucLanDau;
    private String diaDanhCode;

    public LeHoiModel(String tenModel, String thoiGian, String diaDiem,
                      String nhanVatLienQuan, String toChucLanDau, List<String> description, String diaDanhCode)
    {
        super(tenModel, description);
        setThoiGian(thoiGian);
        setDiaDiem(diaDiem);
        setNhanVatLienQuan(nhanVatLienQuan);
        setToChucLanDau(toChucLanDau);
        setDiaDanhCode(diaDanhCode);
    }

    public void setToChucLanDau(String toChucLanDau)
    {
        this.toChucLanDau = toChucLanDau.equals("") ? Config.nullRepresentation : toChucLanDau;
    }

    public void setNhanVatLienQuan(String nhanVatLienQuan)
    {
        this.nhanVatLienQuan = nhanVatLienQuan.equals("") ? Config.nullRepresentation : nhanVatLienQuan;
    }

    public void setDiaDiem(String diaDiem)
    {
        this.diaDiem = diaDiem.equals("") ? Config.nullRepresentation : diaDiem;
    }

    public void setThoiGian(String thoiGian)
    {
        this.thoiGian = thoiGian.equals("") ? Config.nullRepresentation : thoiGian;
    }

    public void setDiaDanhCode(String diaDanhCode)
    {
        this.diaDanhCode = diaDanhCode;
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
        if (this.moTa != null)
        {
            htmlBuilder.append("<h2>Thông tin thời kỳ</h2>");

            // Add the description
            for (String desc : this.moTa) {
                htmlBuilder.append("<p>").append(desc).append("</p>");
            }
        }

        // Add the related figures
        if (this.nhanVatLienQuan != null)
        {
            htmlBuilder.append("<h2>Related Figures</h2>");
            htmlBuilder.append("<ul>");
            htmlBuilder.append("<li>").append(this.nhanVatLienQuan).append("</li>");
            htmlBuilder.append("</ul>");
        }

        // Close the HTML structure
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        return htmlBuilder.toString();
    }
    @Override
    public String toString()
    {
        return  "{ \t\"id\": \"" + this.id + "\", \n\t"
                + "\"tenModel\": \"" + this.tenModel + "\",\n\t"
                + "\"thoiGian\": \"" + this.thoiGian + "\",\n\t"
                + "\"diaDiem\": \"" + this.diaDiem + "\",\n\t"
                + "\"nhanVatLienQuan\": \"" + this.nhanVatLienQuan + "\",\n\t"
                + "\"toChucLanDau\": \"" + this.toChucLanDau + "\",\n\t"
                + "\"moTa\": \"" + this.moTa.toString() + "\",\n\t"
                + "\"diaDanhCode\": \"" + this.diaDanhCode + "\" }\n";
    }
}
