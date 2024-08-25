package com.example.ormcar.model;

import org.springframework.web.multipart.MultipartFile;

public class CarForm {
    private Long id;
    private String code;
    private String name;
    private double price;
    private String description;
    private MultipartFile img;
    private Manufacturer manufacturer; // Sử dụng ID của Manufacturer để giữ khóa ngoại

    public CarForm() {
    }

    public CarForm(Long id, String code, String name, double price, String description, MultipartFile img, Manufacturer manufacturer) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
        this.manufacturer = manufacturer;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    // Phương thức để chuyển đổi từ CarForm sang Car
}
