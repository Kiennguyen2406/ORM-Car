package com.example.ormcar.model;


import org.springframework.stereotype.Service;

import javax.persistence.*;


@Entity //Đánh dấu đây là Entity=>hibernate se tao bang co so du lieu
@Table (name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//danh dau tt tu tang
    private Long id;
    private String code;
    private String name;

    private double price;
    private String description;
    private String img;

    @ManyToOne //đánh dau moi quan he n-1
    @JoinColumn(name = "manufactuer_id") //dat ten cho truong khoa ngoai la manufactuer_id
    private Manufacturer manufacturer;

    public Car() {
    }

    public Car(Long id, String code, String name, double price, String description, String img, Manufacturer manufacturer) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
        this.manufacturer = manufacturer;
    }

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
