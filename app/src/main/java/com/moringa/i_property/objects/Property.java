package com.moringa.i_property.objects;

public class Property {
    private String title;
    private String size;
    private int latitude;
    private int longitude;
    private String description;
    private String location;
    private String price;

    public Property(String title, String size, int latitude, int longitude, String description, String location, String price) {
        this.title = title;
        this.size = size;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.location = location;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
