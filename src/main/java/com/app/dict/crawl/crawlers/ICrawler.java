package com.app.dict.crawl.crawlers;

import com.app.dict.base.Model;

import java.util.List;


public interface ICrawler {
    public List<Model> crawlPages();
//    public void writeJson(String fileName, List<Model> models);
    public void writeJson(String fileName, List<? extends Model> models);
}
