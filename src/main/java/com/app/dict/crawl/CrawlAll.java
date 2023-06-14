package com.app.dict.crawl;

import com.app.dict.crawl.crawlers.DiaDanhCrawler;
import com.app.dict.crawl.crawlers.LeHoiCrawler;
import com.app.dict.crawl.crawlers.NhanVatCrawler;
import com.app.dict.crawl.crawlers.SuKienCrawler;

public class CrawlAll
{
    public void crawl()
    {
        Thread thread1 = new Thread(() -> {
            DiaDanhCrawler diaDanhCrawler = new DiaDanhCrawler();
            diaDanhCrawler.createDiaDanhJson();
        });

        Thread thread2 = new Thread(() -> {
            LeHoiCrawler leHoiCrawler = new LeHoiCrawler();
            leHoiCrawler.createLeHoiJson();
        });

        Thread thread3 = new Thread(() -> {
            DiaDanhCrawler diaDanhCrawler = new DiaDanhCrawler();
            diaDanhCrawler.createDiaDanhJson();
        });

        Thread thread4 = new Thread(() -> {
            SuKienCrawler suKienCrawler = new SuKienCrawler();
            suKienCrawler.createSuKienJson();
        });

        Thread thread5 = new Thread(() -> {
            NhanVatCrawler nhanVatCrawler = new NhanVatCrawler();
            nhanVatCrawler.createNhanVatJson();
        });

        // Start all threads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        // Wait for all threads to finish
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        CrawlAll crawlAll = new CrawlAll();
        crawlAll.crawl();
    }
}
