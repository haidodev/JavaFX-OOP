package com.app.dict.crawl.crawlers;

import com.app.dict.base.LeHoiModel;
import com.app.dict.base.Model;
import com.app.dict.util.Config;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeHoiCrawler extends SCrawler implements ICrawler
{
    private Map<String, List<String>> map;

    public LeHoiCrawler()
    {
        map = new HashMap<>();
    }

    public List<Model> crawlPages()
    {
        List<Model> festivals;
        String page = Config.LE_HOI_WEBPAGE;
        festivals = scrapeTableFestivals(page);
        festivals.addAll(scrapeTinhFestivals(page));
        festivals.addAll(crawlLehoiInfo());
        for (Model model : festivals)
        {
            model.setId(festivals.indexOf(model) + 1);
        }

        return festivals;
    }

    private List<Model> scrapeTinhFestivals(String page)
    {
        List<Model> festivals = new ArrayList<>();
        Document doc;

        try
        {
            doc = Jsoup.connect(page)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36" +
                            " (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                    .get();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }

        Element div = doc.selectFirst("div.mw-parser-output");
        Element ul = div.select("ul").get(10);
        Elements lis = ul.select("li");

        for (Element li : lis)
        {
            String text = li.text();
            String[] texts = text.split(":", 2);
            String location = texts[0];
            String[] provinceFestivals;

            if (location.equals("Hà Tĩnh"))
            {
                provinceFestivals = texts[1].split("\\b((?<!khai\\s)(?<!kết\\s)(?<!chính\\s))(hội đền|đền|hội)\\b");
            }
            else if (location.equals("Hưng Yên"))
            {
                provinceFestivals = texts[1].split("(?<!khai\\s)(lễ hội đình - đền - chùa và hội|Hội|đền|hội|lễ hội)");
            }
            else if (location.equals("Lâm Đồng"))
            {
                provinceFestivals = new String[1];
                provinceFestivals[0] = texts[1].replaceFirst("lễ", "").trim();
            }
            else if (texts[1].contains("khai hội") || texts[1].contains("chính hội")
                    || texts[1].contains("kết hội"))
            {
                provinceFestivals = texts[1].split("\\b((?<!khai\\s)(?<!kết\\s)(?<!chính\\s))(lễ hội|Hội|hội)\\b");
            }
            else
            {
                provinceFestivals = texts[1].split("(Lễ hội|lễ hội|hội|Hội|lễ|Lễ|kỷ niệm|Kỷ niệm|giỗ|Giỗ|ngày giỗ|tết|Tết|ngày|lê hội)");
            }
            for (String provinceFestival : provinceFestivals)
            {
                provinceFestival = provinceFestival.trim();
                if (provinceFestival.equals(""))
                {
                    continue;
                }
                provinceFestival = provinceFestival.replaceAll("[,;]$", "");
                String[] parts;
                parts = provinceFestival.split("\\s+(?=năm|tháng|\\d)", 2);
                String name = parts[0];
                String locationCode = convertToCode(location);
                if (map.containsKey(locationCode))
                {
                    if (map.get(locationCode).contains(name))
                    {
                        continue;
                    }
                    else
                    {
                        map.get(locationCode).add(name);
                    }
                }
                String time = "không rõ";
                if (parts.length == 2)
                {
                    time = parts[1];
                }
                List<String> description = new ArrayList<>();
                description.add("không rõ");
                Model festival = new LeHoiModel("Lễ hội " + name, time, location, "", "", description, locationCode);
                festivals.add(festival);
            }
        }

        return festivals;
    }

    private List<Model> scrapeTableFestivals(String page)
    {
        List<Model> festivals = new ArrayList<>();
        Document doc;

        try
        {
            doc = Jsoup.connect(page)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36" +
                            " (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                    .get();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }

        Elements trs = doc.select("table.prettytable tbody tr");
        trs.remove(0);

        for (Element tr : trs)
        {
            Elements tds = tr.select("td");

            String time = tds.get(0).text();
            String location = tds.get(1).text();

            String name = tds.get(2).text();
            name = name.replaceAll("(Lễ hội|Hội|Tết|Đại hội)", "");
            name = name.trim();

            if (!location.equals("Nhiều quốc gia"))
            {
                String[] parts = location.split(",", 2);
                String province = parts[0];
                if (province.equals(""))
                {
                    break;
                }
                else
                {
                    String provinceCode = convertToCode(province);
                    if (!map.containsKey(provinceCode))
                    {
                        ArrayList<String> newA = new ArrayList<>();
                        newA.add(name);
                        map.put(provinceCode, newA);
                    }
                    else
                    {
                        map.get(provinceCode).add(name);
                    }
                }
            }
            String fistTimeHolding = tds.get(3).text();
            String historicalFigureLinked = tds.get(4).text();
            String description = tds.get(5).text().trim();
            ArrayList<String> others = new ArrayList<>();
            if (!description.equals(""))
            {
                others.add(description);
            }

            String href = "https://vi.wikipedia.org/" + tds.get(2).selectFirst("a").attr("href");
            Document doc2;

            try
            {
                doc2 = Jsoup.connect(href)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36" +
                                " (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                        .timeout(20000).get();
            }
            catch(IOException e)
            {
                throw new RuntimeException(e);
            }

            Element div = doc2.selectFirst("div.mw-parser-output");
            if (div != null)
            {
                Element p = div.selectFirst("p");
                if (p != null)
                {
                    Elements sups = p.select("sup");
                    for (Element sup : sups)
                    {
                        sup.remove();
                    }
                    others.add(p.text().replaceAll("\"", "'"));
                }
            }

            String locationCode = convertToCode(location.split(",")[0]);
            Model festival = new LeHoiModel("Lễ hội " + name, time, location, historicalFigureLinked, fistTimeHolding, others, locationCode);
            festivals.add(festival);
        }

        return festivals;
    }

    public List<Model> crawlLehoiInfo()
    {
        List<Model> festivalList = new ArrayList<>();
        String url = "http://lehoi.info/viet-nam/page";

        for (int i = 1; i <= 64; i++)
        {
            System.out.println(i);
            festivalList.addAll(crawlLeHoiInfoFestivals(url + i));
        }

        return festivalList;
    }

    public List<Model> crawlLeHoiInfoFestivals(String page)
    {
        Document doc;
        List<Model> models = new ArrayList<>();

        try
        {
            doc = Jsoup.connect(page)
                    .get();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }

        Element listview = doc.selectFirst("div.listview");
        Elements festivals = listview.select("li:has(a.thumb)");

        for (Element festival : festivals)
        {
            String href = festival.selectFirst("a.thumb").attr("href");
            String url = "http://lehoi.info" + href;
            System.out.println(url);

            Model model = crawlAFestival(url);
            if (model != null)
            {
                models.add(model);
            }
        }

        return models;
    }

    public Model crawlAFestival(String url) throws RuntimeException {
        Document doc;

        try
        {
            doc = Jsoup.connect(url)
                    .timeout(2000000).get();
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }

        String briefInfo = doc.selectFirst("h1").text();
        String[] briefInfos = briefInfo.split("tại|ở", 2);

        String name = briefInfos[0].trim();
        String location = url.replaceFirst("http://lehoi.info/", "").split("/")[0];
        if (map.containsKey(location))
        {
            if (map.get(location).contains(name))
            {
                return null;
            }
        }
        if (briefInfos.length == 2)
        {
            location = briefInfos[1].trim();
        }
        String time = "";
        Element timeContainer = doc.selectFirst("b.lehoi-time");
        if (timeContainer != null)
        {
            time = doc.selectFirst("b.lehoi-time").text().trim();
        }
        List<String> description = new ArrayList<>();
        Element details = doc.selectFirst("div.content-detail");
        String d = details.selectFirst("br").text().trim();
        if (!d.equals(""))
        {
            description.add(d);
        }
        String code = convertToCode(name);

        Model festival = new LeHoiModel(name, time, location, "", "", description, code);

        return festival;
    }

    private String convertToCode(String str)
    {
        str = str.toLowerCase();
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        return str.replaceAll(" ", "-");
    }

    public void createLeHoiJson()
    {
        String page = Config.LE_HOI_WEBPAGE;
        String festivalFilename = Config.LE_HOI_FILENAME;
        List<Model> festivals;

        festivals = crawlPages();
        writeJson(festivalFilename, festivals);
    }

    public static void main(String[] args)
    {
        LeHoiCrawler festivalsScraper = new LeHoiCrawler();
        String page = Config.LE_HOI_WEBPAGE;
        String festivalFilename = Config.LE_HOI_FILENAME;
        List<Model> festivals;

        festivals = festivalsScraper.crawlPages();
        festivalsScraper.writeJson(festivalFilename, festivals);
    }
}