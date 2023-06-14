package com.app.dict.base;

import com.app.dict.util.Config;
import com.app.dict.util.Encode;
import com.app.dict.util.VietnameseUtil;

import java.util.ArrayList;
import java.util.List;

public class Model implements Comparable<Model>
{
    protected int id;
    protected String tenModel;
    protected List<String> moTa = new ArrayList<>();
    protected String code;
    private String modelHTML;
    public Model(String ten){
        this.tenModel = ten;
    }

    public Model(String ten, List<String> moTa)
    {
        setTenModel(ten);
        setMoTa(moTa);
    }
    public Model(String ten, String html)
    {
        setTenModel(ten);
        modelHTML = html;
    }
    public void setHTML(){
        if (modelHTML != null) return;
        modelHTML = toHTML();
    }
    public String getHTML(){
        return modelHTML;
    }
    public String getCode() {
        return code;
    }
    public void setCode(){
        if (code == null) return;
        code = Encode.encodeString(this.tenModel);
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getTenModel() {
        return tenModel;
    }

    public String toHTML(){
        return "";
    };

    public void setId(int id)
    {
        this.id = id;
    }

    public void setTenModel(String tenModel)
    {
        this.tenModel = tenModel.equals("") ? Config.nullRepresentation : tenModel;
    }

    public void setMoTa(List<String> moTa)
    {
        if (moTa == null)
        {
            moTa = new ArrayList<>();
            moTa.add(Config.nullRepresentation);
        }
        this.moTa = moTa;
    }
    public int compareTo(Model o) {

        String searchingNormalized = VietnameseUtil.generalizeVietnameseString(getTenModel());
        String oSearchingNormalized = VietnameseUtil.generalizeVietnameseString(o.getTenModel());
        return searchingNormalized.compareTo(oSearchingNormalized);
    }
}

