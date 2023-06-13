package com.app.dict.crawler.linkers;

import com.google.gson.reflect.TypeToken;
import com.oop2.crawlers.PeriodsCrawler;
import com.oop2.crawlers.FiguresCrawler;
import com.oop2.models.EraModel;
import com.oop2.models.FigureModel;
import com.oop2.util.Config;
import java.util.*;
import com.oop2.models.Model;

public class FigureToEra
{
    private Map<String, String> generateHashMap()
    {
        Map<String, String> hashMap = new HashMap<>();
        PeriodsCrawler periodsCrawler = new PeriodsCrawler();
        List<EraModel> eraList = periodsCrawler.loader(Config.ERA_FILENAME,  new TypeToken<List<EraModel>>() {});

        for (EraModel era : eraList)
        {
            for (String element : era.getRelatedFigures())
            {
                hashMap.put(element, era.getCode());
            }
        }

        return hashMap;
    }

    public void linkFigureToEra()
    {
        Map<String, String> figureToEra = generateHashMap();
        FiguresCrawler figuresCrawler = new FiguresCrawler();
        List<FigureModel> figureList = figuresCrawler
                .loader(Config.HISTORICAL_FIGURE_FILENAME,  new TypeToken<>() {});

        for (FigureModel figure : figureList)
        {
            figure.setRelatedPeriod(new HashSet<>());
            if (figureToEra.containsKey(figure.getCode()))
            {
                figure.getRelatedPeriod().add(figureToEra.get(figure.getCode()));
                System.out.println(figure.getCode());
            }
        }

        List<Model> models = new ArrayList<>();
        models.addAll(figureList);
        figuresCrawler.writeJson(Config.HISTORICAL_FIGURE_FILENAME, models);
    }

    public static void main(String[] args)
    {
        FigureToEra figureToEra = new FigureToEra();
        figureToEra.linkFigureToEra();
    }
}
