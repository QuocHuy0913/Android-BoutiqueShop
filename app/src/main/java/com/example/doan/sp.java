package com.example.doan;

public class sp {
    long idPro;
    String resourceImage;
    String name;
    double price;
    String description;
    String category;
    long idUser;

    public sp(long idPro, String resourceImage, String name, double price, String description, String category, long idUser) {
        this.idPro = idPro;
        this.resourceImage = resourceImage;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.idUser = idUser;
    }

    public long getIdPro() {
        return idPro;
    }

    public void setIdPro(long idPro) {
        this.idPro = idPro;
    }

    public String getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(String resourceImage) {
        this.resourceImage = resourceImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
