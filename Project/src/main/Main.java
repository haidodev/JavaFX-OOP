package main;

import com.oop2.crawlers.*;

public class Main
{
    public static void main(String[] args)
    {
        Thread thread1 = new Thread(() -> {
            PeriodsCrawler periodsCrawler = new PeriodsCrawler();
            periodsCrawler.createErasJson();
        });

        Thread thread2 = new Thread(() -> {
            FestivalsCrawler festivalsScraper = new FestivalsCrawler();
            festivalsScraper.createFestivalsJson();
        });

        Thread thread3 = new Thread(() -> {
            PlacesCrawler placesCrawler = new PlacesCrawler();
            placesCrawler.createHistoricalDestinationJson();
        });

        Thread thread4 = new Thread(() -> {
            EventsCrawler eventsCrawler = new EventsCrawler();
            eventsCrawler.createHistoricalEventsJson();
        });

        Thread thread5 = new Thread(() -> {
            FiguresCrawler figuresCrawler = new FiguresCrawler();
            figuresCrawler.createHistoricalFiguresJson();
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
}
