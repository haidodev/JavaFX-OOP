package com.app.dict.crawl.linkers;

import com.app.dict.base.Model;
import com.app.dict.base.NhanVatModel;
import com.app.dict.base.ThoiKyModel;
import com.app.dict.crawl.crawlers.NhanVatCrawler;
import com.app.dict.crawl.crawlers.ThoiKyCrawler;
import com.app.dict.util.Config;
import com.google.gson.reflect.TypeToken;

import java.util.*;


public class NhanVatToThoiKy
{
    private Map<String, String> generateHashMap()
    {
        Map<String, String> hashMap = new HashMap<>();
        ThoiKyCrawler periodsCrawler = new ThoiKyCrawler();
        List<ThoiKyModel> eraList = periodsCrawler.loader(Config.THOI_KY_FILENAME,  new TypeToken<List<ThoiKyModel>>() {});

        for (ThoiKyModel era : eraList)
        {
            for (String element : era.getcacNhanVatLienQuan())
            {
                hashMap.put(element, era.getCode());
            }
        }

        return hashMap;
    }

    public void linkFigureToEra()
    {
        Map<String, String> figureToEra = generateHashMap();
        NhanVatCrawler figuresCrawler = new NhanVatCrawler();
        List<NhanVatModel> figureList = figuresCrawler
                .loader(Config.NHAN_VAT_LICH_SU_FILENAME,  new TypeToken<>() {});

        for (NhanVatModel figure : figureList)
        {
            figure.setCacThoiKyLienQuan(new HashSet<>());
            if (figureToEra.containsKey(figure.getCode()))
            {
                figure.getCacThoiKyLienQuan().add(figureToEra.get(figure.getCode()));
                System.out.println(figure.getCode());
            }
        }

        List<Model> models = new ArrayList<>();
        models.addAll(figureList);
        figuresCrawler.writeJson(Config.NHAN_VAT_LICH_SU_FILENAME, models);
    }

    public static void main(String[] args)
    {
        NhanVatToThoiKy nhanVatToThoiKy = new NhanVatToThoiKy();
        nhanVatToThoiKy.linkFigureToEra();
    }
}
