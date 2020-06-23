package com.king.model;

import javax.persistence.*;

@Entity
@Table(name = "salon")
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int salonId;
    private String salonName;
    private String salonAddress;
    private String phone;
    private String email;
    private String description;
    private String img;

    public Salon(int salonId, String salonName, String salonAddress, String phone, String email, String description, String img) {
        this.salonId = salonId;
        this.salonName = salonName;
        this.salonAddress = salonAddress;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.img = img;
    }

    public Salon() {
    }

    public int getSalonId() {
        return salonId;
    }

    public void setSalonId(int salonId) {
        this.salonId = salonId;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }

    public String getSalonAddress() {
        return salonAddress;
    }

    public void setSalonAddress(String salonAddress) {
        this.salonAddress = salonAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Salon{" +
                "salonId=" + salonId +
                ", salonName='" + salonName + '\'' +
                ", salonAddress='" + salonAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
