package com.example.playlist;

public class CardItem {
    private String name;
    private String type;
    private int yearReleased;
    private double priceUSD;

    public CardItem(String name, String type, int yearReleased, double priceUSD) {
        this.name = name;
        this.type = type;
        this.yearReleased = yearReleased;
        this.priceUSD = priceUSD;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public double getPriceUSD() {
        return priceUSD;
    }
}
