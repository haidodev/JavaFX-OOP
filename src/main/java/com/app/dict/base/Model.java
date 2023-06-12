package com.app.dict.base;

import com.app.dict.util.Config;
import com.app.dict.util.VietnameseUtil;

import java.util.*;

public class Model implements Comparable<Model>
{
    protected int id;
    protected String name;
    protected List<String> description;
    private String modelHTML;

    public Model(String ten, List<String> description)
    {
        setName(ten);
        setDescription(description);
    }
    public Model(String ten, String html)
    {
        setName(ten);
        modelHTML = html;
    }
    public void setHTML(){
        if (modelHTML != null) return;
        modelHTML = toHTML();
    }
    public String getHTML(){
        return modelHTML;
    }
    public String getName() {
        return name;
    }

    public String toHTML(){
        return "";
    };

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name.equals("") ? Config.nullRepresentation : name;
    }

    public void setDescription(List<String> description)
    {
        if (description == null)
        {
            description = new ArrayList<>();
            description.add(Config.nullRepresentation);
        }
        this.description = description;
    }
    public int compareTo(Model o) {

        String searchingNormalized = VietnameseUtil.generalizeVietnameseString(getName());
        String oSearchingNormalized = VietnameseUtil.generalizeVietnameseString(o.getName());
        return searchingNormalized.compareTo(oSearchingNormalized);
    }
}

