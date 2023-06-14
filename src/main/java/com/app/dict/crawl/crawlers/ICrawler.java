package com.app.dict.crawl.crawlers;

import java.util.List;

import com.app.dict.base.Model;


public interface ICrawler {
    public List<Model> crawlPages();
    public void writeJson(String fileName, List<Model> models);
}
