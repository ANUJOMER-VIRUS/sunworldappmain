package com.monstertechno.loginsignupui;

public class Model {

    int image;
    String name,quality,number,delivery;

    public Model(int image, String name, String quality, String number, String delivery) {
        this.image = image;
        this.name = name;
        this.quality = quality;
        this.number = number;
        this.delivery = delivery;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
}
