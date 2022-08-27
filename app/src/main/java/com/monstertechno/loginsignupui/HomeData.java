package com.monstertechno.loginsignupui;

public class HomeData {
    private int image;
    private String name;
    private String product;
    private String quantity;

    public HomeData(int img20, String family) {
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

    public String getproduct() {
        return product;
    }

    public void setproduct(String product) {
        this.product = product;
    }


    public String getquantity() {
        return quantity;
    }

    public void setquantity(String quantity) {
        this.quantity = quantity;
    }


    public HomeData(int image, String name ,String product, String quantity) {
        this.image = image;
        this.name = name;
        this.product = product;
        this.quantity = quantity;
    }
}
