package com.example.ormcar.service;

import com.example.ormcar.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICarService {
    List<Car> findAll();

    void save(Car car);

    Car findById(int id);

    void remote(int id);
     List<Car> SearchByName(String name);
}
