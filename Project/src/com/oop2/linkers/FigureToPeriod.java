package com.oop2.linkers;

import com.google.gson.reflect.TypeToken;
import com.oop2.crawlers.PeriodsCrawler;
import com.oop2.crawlers.FiguresCrawler;
import com.oop2.models.PeriodModel;
import com.oop2.models.FigureModel;
import com.oop2.util.Config;
import java.util.*;
import com.oop2.models.Model;

public class FigureToPeriod
{
    private Map<String, List<String>> generateHashMap()
    {
        Map<String, List<String>> hashMap = new HashMap<>();
        PeriodsCrawler periodsCrawler = new PeriodsCrawler();
        List<PeriodModel> periodList = periodsCrawler.loader(Config.ERA_FILENAME,  new TypeToken<List<PeriodModel>>() {});

        for (PeriodModel period : periodList)
        {
            for (String place : period.getRelatedFigures())
            {
                if (!hashMap.containsKey(place))
                {
                    hashMap.put(place, new ArrayList<>());
                }
                hashMap.get(place).add(period.getCode());
            }
        }

        System.out.println(hashMap);
        return hashMap;
    }

    public void linkFigureToPeriod()
    {
        Map<String, List<String>> figureToPreriod = generateHashMap();
        FiguresCrawler figuresCrawler = new FiguresCrawler();
        List<FigureModel> figureList = figuresCrawler
                .loader(Config.HISTORICAL_FIGURE_FILENAME,  new TypeToken<>() {});

        for (FigureModel figure : figureList)
        {
            if (figureToPreriod.containsKey(figure.getCode()))
            {
                Set<String> set = new HashSet<>(figureToPreriod.get(figure.getCode()));
                figure.setRelatedPeriod(set);
            }
        }

        List<Model> models = new ArrayList<>();
        models.addAll(figureList);
        figuresCrawler.writeJson(Config.HISTORICAL_FIGURE_FILENAME, models);
    }

    public static void main(String[] args)
    {
        FigureToPeriod figureToPeriod = new FigureToPeriod();
        figureToPeriod.linkFigureToPeriod();
    }
}
