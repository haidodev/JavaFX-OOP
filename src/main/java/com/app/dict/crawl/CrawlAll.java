package com.app.dict.crawl;

import com.app.dict.crawl.crawlers.*;
import com.app.dict.crawl.linkers.*;

public class CrawlAll
{
    public void crawl()
    {
        Thread thread1 = new Thread(() -> {
            ThoiKyCrawler thoiKyCrawler = new ThoiKyCrawler();
            thoiKyCrawler.createThoiKyJson();
        });

        Thread thread2 = new Thread(() -> {
            LeHoiCrawler leHoiCrawler = new LeHoiCrawler();
            leHoiCrawler.createLeHoiJson();
        });

        Thread thread3 = new Thread(() -> {
            DiTichCrawler diaDanhCrawler = new DiTichCrawler();
            diaDanhCrawler.createDiTichJson();
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

    // Test
    public static void main(String[] args) {
        CrawlAll crawlAll = new CrawlAll();
        crawlAll.crawl();
    }
}
