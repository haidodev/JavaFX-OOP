package com.oop2.models;

import java.util.List;
import java.util.Set;

public class PlaceModel extends Model{
    private String placeCode;
    private Set<String> relatedFigures;
    private Set<String> relatedPeriod;

    public PlaceModel(String name, List<String> description
            , String placeCode, Set<String> relatedFigures, Set<String> relatedPeriod)
    {
        super(name, description);
        setPlaceCode(placeCode);
        setRelatedFigures(relatedFigures);
        setRelatedPeriod(relatedPeriod);
    }

    public void setRelatedFigures(Set<String> relatedFigures)
    {
        this.relatedFigures = relatedFigures;
    }

    public void setPlaceCode(String placeCode)
    {
        this.placeCode = placeCode;
    }

    public void setRelatedPeriod(Set<String> relatedPeriod) {
        this.relatedPeriod = relatedPeriod;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public Set<String> getRelatedPeriod() {
        return relatedPeriod;
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
        for (String desc : this.moTa) {
            htmlBuilder.append("<p>").append(desc).append("</p>");
        }

        // Add the related figures
        htmlBuilder.append("<h2>Related Figures</h2>");
        htmlBuilder.append("<ul>");
        for (String figure : this.relatedFigures) {
            htmlBuilder.append("<li>").append(figure).append("</li>");
        }
        htmlBuilder.append("</ul>");

        // Close the HTML structure
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        return htmlBuilder.toString();
    }

    @Override
    public String toString() {
        return  "\n{ \"Id\":\"" + this.id + "\", "
                + "\n\"Địa danh\":\"" + this.tenModel + "\", "
                + "\n\"Code\":\"" + this.placeCode + "\", "
                + "\n\"Miêu tả\":\"" + this.moTa + "\", "
                + "\n\"Nhân vật liên quan code\":\"" + this.relatedFigures + "\" }" + "\n";
    }
}
