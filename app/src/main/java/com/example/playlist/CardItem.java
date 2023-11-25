package com.example.playlist;

public class CardItem {
    private String name;
    private String type;
    private int yearReleased;
    private double priceUSD;
    private String imageURL;

    public CardItem(String name, String type, int yearReleased, double priceUSD, String imageResource) {
        this.name = name;
        this.type = type;
        this.yearReleased = yearReleased;
        this.priceUSD = priceUSD;
        this.imageURL = imageURL;
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

    public String getImageResource() {
        return imageURL;
    }

    public void setImageResource(String imageURL) {
        this.imageURL = imageURL;
    }
}
