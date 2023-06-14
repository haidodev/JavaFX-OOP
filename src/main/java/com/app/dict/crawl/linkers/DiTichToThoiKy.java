package com.app.dict.crawl.linkers;

import com.app.dict.base.DiaDanhModel;
import com.app.dict.base.ThoiKyModel;
import com.app.dict.base.Model;
import com.app.dict.crawl.crawlers.DiaDanhCrawler;
import com.app.dict.crawl.crawlers.ThoiKyCrawler;
import com.app.dict.util.Config;
import com.google.gson.reflect.TypeToken;
import java.util.*;

public class DiTichToThoiKy
{
    private Map<String, List<String>> generateHashMap()
    {
        Map<String, List<String>> hashMap = new HashMap<>();
        ThoiKyCrawler thoiKyCrawler = new ThoiKyCrawler();
        List<ThoiKyModel> thoiKyList = thoiKyCrawler.loader(Config.THOI_KY_FILENAME,  new TypeToken<List<ThoiKyModel>>() {});

        for (ThoiKyModel thoiKy : thoiKyList)
        {
            for (String diaDanh : thoiKy.getCacDiTichLienQuan())
            {
                if (!hashMap.containsKey(diaDanh))
                {
                    hashMap.put(diaDanh, new ArrayList<>());
                }
                hashMap.get(diaDanh).add(thoiKy.getCode());
            }
        }

        System.out.println(hashMap);
        return hashMap;
    }

    public void diaDanhToThoiKy()
    {
        Map<String, List<String>> diaDanhToThoiKy = generateHashMap();
        DiaDanhCrawler diaDanhCrawler = new DiaDanhCrawler();
        List<DiaDanhModel> diaDanhList = diaDanhCrawler
                .loader(Config.DIA_DANH_FILENAME,  new TypeToken<>() {});

        for (DiaDanhModel diaDanh : diaDanhList)
        {
            if (diaDanhToThoiKy.containsKey(diaDanh.getCode()))
            {
                Set<String> set = new HashSet<>(diaDanhToThoiKy.get(diaDanh.getCode()));
                diaDanh.setCacThoiKyLienQuan(set);
            }
        }

        List<Model> models = new ArrayList<>();
        models.addAll(diaDanhList);
        diaDanhCrawler.writeJson(Config.DIA_DANH_FILENAME, models);
    }

    public static void main(String[] args)
    {
        DiTichToThoiKy diTichToThoiKy = new DiTichToThoiKy();
        diTichToThoiKy.diaDanhToThoiKy();
    }
}
