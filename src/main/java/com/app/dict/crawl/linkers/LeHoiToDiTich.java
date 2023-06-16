package com.app.dict.crawl.linkers;

import com.app.dict.base.DiTichModel;
import com.app.dict.base.LeHoiModel;
import com.app.dict.base.Model;
import com.app.dict.crawl.crawlers.DiTichCrawler;
import com.app.dict.crawl.crawlers.LeHoiCrawler;
import com.app.dict.util.Config;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeHoiToDiTich
{
    public Map<String, List<String>> generateHashMap()
    {
        Map<String, List<String>> hashMap = new HashMap<>();
        DiTichCrawler diTichCrawler = new DiTichCrawler();
        List<DiTichModel> diTichList = diTichCrawler.loader(Config.DI_TICH_FILENAME,  new TypeToken<List<DiTichModel>>() {});

        for (DiTichModel diTich : diTichList)
        {
            if (diTich.getCacLeHoiLienQuan() != null)
            {
                for (String leHoi : diTich.getCacLeHoiLienQuan())
                {
                    if (!hashMap.containsKey(leHoi))
                    {
                        hashMap.put(leHoi, new ArrayList<>());
                    }
                    hashMap.get(leHoi).add(diTich.getCode());
                }
            }
        }

        return hashMap;
    }

    public void LinkLeHoiToDiTich()
    {
        Map<String, List<String>> hashMap = generateHashMap();
        System.out.println(hashMap);

        LeHoiCrawler leHoiCrawler = new LeHoiCrawler();
        List<LeHoiModel> leHoiList = leHoiCrawler.loader(Config.LE_HOI_FILENAME, new TypeToken<List<LeHoiModel>>() {});

        for (LeHoiModel leHoi : leHoiList)
        {
            if (hashMap.containsKey(leHoi.getCode()))
            {
                leHoi.setDiTichLienQuan(hashMap.get(leHoi.getCode()));
                System.out.println(leHoi.getCode());
            }
        }

        List<Model> models = new ArrayList<>();
        models.addAll(leHoiList);
        leHoiCrawler.writeJson(Config.LE_HOI_FILENAME, models);
    }

    public static void main(String[] args)
    {
        LeHoiToDiTich leHoiToDiTich = new LeHoiToDiTich();
        leHoiToDiTich.LinkLeHoiToDiTich();
    }
}
