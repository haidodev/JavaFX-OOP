package com.app.dict.crawler.crawlers;

import java.util.List;

import com.app.dict.base.Model;


public interface ICrawler {
    public List<Model> crawlPages(String page);
    public void writeJson(String fileName, List<Model> models);
}
