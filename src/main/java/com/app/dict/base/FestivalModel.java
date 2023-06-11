package com.app.dict.base;

import com.app.dict.util.Config;

import java.util.*;

public class FestivalModel extends Model
{
    private String time;
    private String location;
    private String relatedFigures;
    private String firstTimeHolding;
    private String locationCode;

    public FestivalModel(String name, String time, String location,
                         String relatedFigures, String firstTimeHolding, List<String> description, String locationCode)
    {
        super(name, description);
        setTime(time);
        setLocation(location);
        setRelatedFigures(relatedFigures);
        setFirstTimeHolding(firstTimeHolding);
        setLocationCode(locationCode);
    }

    public void setFirstTimeHolding(String firstTimeHolding)
    {
        this.firstTimeHolding = firstTimeHolding.equals("") ? Config.nullRepresentation : firstTimeHolding;
    }

    public void setRelatedFigures(String relatedFigures)
    {
        this.relatedFigures = relatedFigures.equals("") ? Config.nullRepresentation : relatedFigures;
    }

    public void setLocation(String location)
    {
        this.location = location.equals("") ? Config.nullRepresentation : location;
    }

    public void setTime(String time)
    {
        this.time = time.equals("") ? Config.nullRepresentation : time;
    }

    public void setLocationCode(String locationCode)
    {
        this.locationCode = locationCode;
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
        htmlBuilder.append("<i>").append(this.name).append("</i>");
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
        for (String desc : this.description) {
            htmlBuilder.append("<p>").append(desc).append("</p>");
        }

        // Add the related figures
        if (this.relatedFigures != null)
        {
            htmlBuilder.append("<h2>Related Figures</h2>");
            htmlBuilder.append("<ul>");
            htmlBuilder.append("<li>").append(this.relatedFigures).append("</li>");
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
        return  "{ \t\"Id\": \"" + this.id + "\", \n\t"
                + "\"Tên\": \"" + this.name + "\",\n\t"
                + "\"Thời Gian\": \"" + this.time + "\",\n\t"
                + "\"Địa điểm\": \"" + this.location + "\",\n\t"
                + "\"Nhân Vật Lịch Sử Liên Kết\": \"" + this.relatedFigures + "\",\n\t"
                + "\"Lần Đầu Tổ Chức\": \"" + this.firstTimeHolding + "\",\n\t"
                + "\"Thông Tin Khác\": \"" + this.description.toString() + "\",\n\t"
                + "\"Địa điểm code\": \"" + this.locationCode + "\" }\n";
    }
}
