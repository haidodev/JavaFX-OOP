package com.oop2.models;

import java.util.*;

public class PeriodModel extends Model
{
    private String code;
    private Set<String> relatedFigures;
    private Set<String> relatedPlaces;

    public List<String> getRelatedFigures() {
        return new ArrayList<>(this.relatedFigures);
    }

    public PeriodModel(String name, List<String> description, String code, Set<String> relatedFigures
            , Set<String> relatedPlaces)
    {
        super(name, description);
        setCode(code);
        setRelatedFigures(relatedFigures);
        setRelatedPlaces(relatedPlaces);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRelatedFigures(Set<String> relatedFigures) {
        this.relatedFigures = relatedFigures;
    }

    public void setRelatedPlaces(Set<String> relatedPlaces) {
        this.relatedPlaces = relatedPlaces;
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
        for (String desc : this.moTa) {
            htmlBuilder.append("<p>").append(desc).append("</p>");
        }

        // Add the related figures
        if (this.relatedFigures != null)
        {
            htmlBuilder.append("<h2>Related Figures</h2>");
            htmlBuilder.append("<ul>");
            for (String figure : this.relatedFigures) {
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
        return "\n{ \"Tên thời kỳ\":\"" + this.tenModel + "\", "
                + "\n\"Miêu tả\":\"" + this.moTa + "\", "
                + "\n\"Nhân vật liên quan code\":\"" + this.relatedFigures + "\" }" + "\n";
    }

    public Set<String> getRelatedPlaces() {
        return relatedPlaces;
    }
}