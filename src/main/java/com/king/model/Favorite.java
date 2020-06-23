package com.king.model;

public class Favorite {
    private Salon salon;

    public Favorite(Salon salon) {
        this.salon = salon;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Favorite() {
    }
}
