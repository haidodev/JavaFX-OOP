package com.oop2.models;

import java.util.*;
import com.oop2.util.Config;

public abstract class Model
{
    protected int id;
    protected String tenModel;
    protected List<String> moTa;

    public Model(String tenModel, List<String> moTa)
    {
        setTenModel(tenModel);
        setMoTa(moTa);
    }

    public String getTenModel() {
        return tenModel;
    }

    public  abstract String toHTML();

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
}

