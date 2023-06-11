package com.app.dict.base;

import java.util.List;
import java.util.Set;

public class FigureModel extends Model
{
    private String code;
    private List<List<String>> infobox;
    private Set<String> relatedFigures;
    private Set<String> relatedPlaces;
    private Set<String> relatedPeriod;


    public String getCode() {
        return code;
    }

    public FigureModel(String name, List<String> description, String code, List<List<String>> infobox
            , Set<String> relatedFigures, Set<String> relatedPlaces, Set<String> relatedPeriod)
    {
        super(name, description);
        setCode(code);
        setInfobox(infobox);
        setRelatedFigures(relatedFigures);
        setRelatedPlaces(relatedPlaces);
        setRelatedPeriod(relatedPeriod);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setInfobox(List<List<String>> infobox) {
        this.infobox = infobox;
    }

    public void setRelatedFigures(Set<String> relatedFigures) {
        this.relatedFigures = relatedFigures;
    }

    public void setRelatedPlaces(Set<String> relatedPlaces) {
        this.relatedPlaces = relatedPlaces;
    }

    public void setRelatedPeriod(Set<String> relatedPeriod) {
        this.relatedPeriod = relatedPeriod;
    }

    public Set<String> getRelatedPeriod() {
        return relatedPeriod;
    }

    @Override
    public String toHTML() {
        StringBuilder htmlBuilder = new StringBuilder();

        // Start the HTML structure
        htmlBuilder.append("<html>");
        htmlBuilder.append("<i>").append(this.name).append("</i>");
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

        // Add the infobox
        htmlBuilder.append("<h2>Thông tin nhân vật</h2>");
        htmlBuilder.append("<table class=\"table-container\">");
        for (List<String> info : this.infobox) {
            htmlBuilder.append("<tr>");
            if (info.size() == 1) htmlBuilder.append("<th colspan=\"2\">");
            else htmlBuilder.append("<th scope=\"row\">");
            htmlBuilder.append(info.get(0)).append("</th>");
            htmlBuilder.append("<td>");
            for (int i = 1; i < info.size(); i++) {
                if (i > 1) htmlBuilder.append("<br>");
                htmlBuilder.append(info.get(i));
            }
            htmlBuilder.append("</td>");
            htmlBuilder.append("</tr>");
        }
        htmlBuilder.append("</table>");

        // Add the description
        htmlBuilder.append("<h2>Description</h2>");
        for (String desc : this.description) {
            htmlBuilder.append("<p>").append(desc).append("</p>");
        }

        // Add the related figures
        htmlBuilder.append("<h2>Related Figures</h2>");
        htmlBuilder.append("<ul>");
        for (String figure : this.relatedFigures) {
            htmlBuilder.append("<li>").append(figure).append("</li>");
        }
        htmlBuilder.append("</ul>");

        // Add the related places
        htmlBuilder.append("<h2>Related Places</h2>");
        htmlBuilder.append("<ul>");
        for (String place : this.relatedPlaces) {
            htmlBuilder.append("<li>").append(place).append("</li>");
        }
        htmlBuilder.append("</ul>");

        // Add the related time periods
        htmlBuilder.append("<h2>Related Time Periods</h2>");
        htmlBuilder.append("<ul>");
        for (String timePeriod : this.relatedPeriod) {
            htmlBuilder.append("<li>").append(timePeriod).append("</li>");
        }
        htmlBuilder.append("</ul>");

        // Close the HTML structure
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        return htmlBuilder.toString();
    }
}