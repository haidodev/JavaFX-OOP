package com.app.dict.crawler.crawlers;

import com.google.gson.reflect.TypeToken;
import com.oop2.interfaces.ICrawler;
import com.oop2.models.FigureModel;
import com.oop2.models.Model;
import com.oop2.superCrawler.SCrawler;
import com.oop2.util.Config;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;


public class FiguresCrawler extends SCrawler implements ICrawler {

    public List<Model> crawlPages(String page) {
        String baseUrl = page;
        String figureURL = "/nhan-vat/an-duong-vuong";
        Document doc;

        List<Model> historicalFigureList = new ArrayList<>();

        while (figureURL != null) {
            System.out.println(baseUrl + figureURL);
            try {
                doc =  Jsoup.connect(baseUrl+figureURL).timeout(200000).get();
            } catch (IOException e ){
                throw new RuntimeException(e);
            }

            // Collecting data
            String name = doc.selectFirst("div.page-header > h2").text();

            int lastSlashIndex = figureURL.lastIndexOf("/");   // Get the text after last slash of url as historicalFigureCode
            String historicalFigureCode = figureURL.substring(lastSlashIndex + 1);

            // Get table
            List<List<String>> infobox = new ArrayList<>();
            Element table = doc.selectFirst("table.infobox"); // select the table element with class "infobox"
            if (table != null) {
                // Map<String, ArrayList<String>> infoMap = new HashMap<String, ArrayList<String>>();
                Elements rows = table.select("tr");
                for (Element row : rows) {
                    String label = row.select("th[scope=row], th[colspan=2]").text();
                    if (label.isEmpty()) {
                        continue;
                    }

                    List<String> info = new ArrayList<>();
                    info.add(label);   // Set description[0] as label

                    //  Add information to description[1..]
                    Elements tabElements = row.select("table");
                    if (tabElements.size() > 0) {
                        Elements liElements = row.select("td li");
                        if (liElements.size() > 0) {
                            // if there are li elements, get their text and concatenate with line breaks
                            for (Element li : liElements) {
                                info.add(li.text());
                            }
                        } else {
                            Elements tdElements = tabElements.select("tr td");
                            if (tdElements.size() > 0) info.add(tdElements.text());
                        }
                    } else {
                        Elements brElements = row.select("td br");
                        if (brElements.size() > 0) {
                            row = row.select("td").get(row.select("td").size()-1);;
                            String tdContent = row.select("td").html();
                            String lines[] = tdContent.split("<br>");
                            for (String line : lines) {
                                info.add(Jsoup.parse(line).text());
                            }
                        } else {
                            Element tdElement = row.selectFirst("td");
                            if (tdElement != null) info.add(tdElement.text());
                        }
                    }

                    infobox.add(info);
                }
            }


            //  Scrape description
            List<String> description = new ArrayList<String>();
            Element firstPTag = doc.selectFirst("p");
            if (firstPTag != null)  {
                String text = firstPTag.wholeText();
                description.add(text);
                Element nextPTag = firstPTag.nextElementSibling();
                while (nextPTag != null && !nextPTag.tagName().equals("h2")) {
                    text = nextPTag.wholeText();
                    if (!text.isEmpty()) description.add(text);
                    nextPTag = nextPTag.nextElementSibling();
                }
            }

            //  Search for relative figures
            Set<String> historicalFiguresLinked = new HashSet<>();
            Set<String> historicalDestinationsLinked = new HashSet<>();
            Set<String> historicalErasLinked = new HashSet<>();;
            Elements aTags = doc.select("div[itemprop=articleBody].com-content-article__body a");
            for (Element aTag : aTags) {
                String href = aTag.attr("href");
                if (href.isEmpty() || href.startsWith("#")) continue;
                String code = href.substring(href.lastIndexOf("/") + 1);
                if (href.contains("nhan-vat")) {
                    historicalFiguresLinked.add(code);
                } else if (href.contains("dia-danh")) {
                    historicalDestinationsLinked.add(code);
                } else if (href.contains("dong-lich-su")) {
                    historicalErasLinked.add(code);
                }
            }

            // Add scapred nhanvat to list
            Model nhanVat = new FigureModel(name, description, historicalFigureCode, infobox,
                    historicalFiguresLinked, historicalDestinationsLinked, historicalErasLinked);
            historicalFigureList.add(nhanVat);

            // visit next page
            Element nextPage = doc.selectFirst("a.btn.btn-sm.btn-secondary.next");
            if (nextPage != null) {
                figureURL = nextPage.attr("href");
            } else {
                figureURL = null;
                System.out.println("Next page link not found.");
            }
        }

        // Write to JSON file
        return historicalFigureList;
    }

    public void crawlWiki()  {

//        //  Input from JSON back to Objects
//        ArrayList<NhanVatModel> nhanVatList = Utility.loader(Config.NHAN_VAT_FILENAME, new TypeToken<ArrayList<NhanVatModel>>() {});
//
//        String targetCode = "an-duong-vuong";
//        NhanVatModel targetNhanVat = null;
//
//        for (NhanVatModel nhanVat : nhanVatList) {
//            if (nhanVat.getCode().equals(targetCode)) {
//                targetNhanVat = nhanVat;
//                break; // Exit the loop once the target is found
//            }
//        }
//        String html = targetNhanVat.toHtml();
//        String filePathOutput = "./htmlOutput/NhanVat.html";
//        try (FileWriter writer = new FileWriter(filePathOutput)) {
//            // Write the JSON string to the file
//            writer.write(html);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // String baseUrl = "https://vi.wikipedia.org/wiki/";
        // String nhanVatUrl = "";
        // Document doc;


        // for (NhanVatModel nhanVat : nhanVatList) {
        //     nhanVatUrl = nhanVat.getName().replace(" ", "_");
        //     try {
        //         doc =  Jsoup.connect(baseUrl+nhanVatUrl).get();
        //     } catch (IOException e ){
        //         throw new RuntimeException(e);
        //     }
        //     Element table = doc.selectFirst("div.infobox:not([id])");
        //     if (table != null) {
        //         System.out.println("HTML contains infobox without id attribute");
        //     } else {
        //         System.out.println("HTML does not contain infobox without id attribute");
        //     }
        // }
    }

//    public void outputTxt () {
//        ArrayList<NhanVatModel> nhanVatList = Utility.loader(Config.NHAN_VAT_FILENAME, new TypeToken<ArrayList<NhanVatModel>>() {});
//        StringBuilder stringBuilder = new StringBuilder();
//        for (NhanVatModel nhanVat : nhanVatList) {
//            stringBuilder.append(Utility.removeAccentsAndToLowercase(nhanVat.getName())).append(nhanVat.toHtml()).append("\n");
//        }
//        String filePathOutput = "./txtOutput/NhanVat.txt";
//        String outputTxt = stringBuilder.toString();
//        try (FileWriter writer = new FileWriter(filePathOutput)) {
//            // Write the JSON string to the file
//            writer.write(outputTxt);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void createHistoricalFiguresJson()
    {
        List<Model> figures = crawlPages(Config.HISTORICAL_FIGURE_WEBPAGE);
        writeJson(Config.HISTORICAL_FIGURE_FILENAME, figures);
    }

    public void linkToEra()
    {

    }



    public static void main(String[] args) {
//        HistoricalFiguresCrawler test = new HistoricalFiguresCrawler();
//        List<Model> figures = test.crawlPages(Config.HISTORICAL_FIGURE_WEBPAGE);
//        test.writeJson(Config.HISTORICAL_FIGURE_FILENAME, figures);
//        test.writeHTML(Config.HISTORICAL_FIGURE_HTML, figures);

        FiguresCrawler test = new FiguresCrawler();
        List<FigureModel> myList = test.loader(Config.HISTORICAL_FIGURE_FILENAME,  new TypeToken<List<FigureModel>>() {});
        List<Model> newList = new ArrayList<>();
        newList.addAll(myList);
        test.writeHTML(Config.HISTORICAL_FIGURE_HTML, newList);
    }
}