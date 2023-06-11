package com.app.dict.base;

import com.app.dict.util.Config;

import java.util.*;

public abstract class Model
{
    protected int id;
    protected String name;
    protected List<String> description;

    public Model(String ten, List<String> description)
    {
        setName(ten);
        setDescription(description);
    }

    public String getName() {
        return name;
    }

    public  abstract String toHTML();

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
}

