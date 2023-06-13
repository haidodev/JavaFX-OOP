package com.oop2.linkers;

import com.google.gson.reflect.TypeToken;
import com.oop2.crawlers.PeriodsCrawler;
import com.oop2.crawlers.PlacesCrawler;
import com.oop2.models.PeriodModel;
import com.oop2.models.PlaceModel;
import com.oop2.util.Config;
import java.util.*;
import com.oop2.models.Model;

public class PlaceToPeriod
{
    private Map<String, List<String>> generateHashMap()
    {
        Map<String, List<String>> hashMap = new HashMap<>();
        PeriodsCrawler periodsCrawler = new PeriodsCrawler();
        List<PeriodModel> periodList = periodsCrawler.loader(Config.ERA_FILENAME,  new TypeToken<List<PeriodModel>>() {});

        for (PeriodModel period : periodList)
        {
            for (String place : period.getRelatedPlaces())
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

    public void linkPlaceToPeriod()
    {
        Map<String, List<String>> placeToPeriod = generateHashMap();
        PlacesCrawler placesCrawler = new PlacesCrawler();
        List<PlaceModel> placeList = placesCrawler
                .loader(Config.HISTORICAL_DESTINATION_FILENAME,  new TypeToken<>() {});

        for (PlaceModel place : placeList)
        {
            if (placeToPeriod.containsKey(place.getPlaceCode()))
            {
                Set<String> set = new HashSet<>(placeToPeriod.get(place.getPlaceCode()));
                place.setRelatedPeriod(set);
            }
        }

        List<Model> models = new ArrayList<>();
        models.addAll(placeList);
        placesCrawler.writeJson(Config.HISTORICAL_DESTINATION_FILENAME, models);
    }

    public static void main(String[] args)
    {
        PlaceToPeriod placeToPeriod = new PlaceToPeriod();
        placeToPeriod.linkPlaceToPeriod();
    }
}
