package com.app.dict.crawler.interfaces;

import java.util.List;
import com.oop2.models.Model;

public interface ICrawler {
    public List<Model> crawlPages(String page);
    public void writeJson(String fileName, List<Model> models);
}