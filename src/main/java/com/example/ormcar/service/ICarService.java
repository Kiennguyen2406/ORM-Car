package com.example.ormcar.service;

import com.example.ormcar.model.Car;
import com.example.ormcar.model.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ICarService extends IGenerateService<Car>{
    Iterable<Car> findAllByManufacturer(Manufacturer manufacturer);
    Page<Car> findAll(Pageable pageable);

    Page<Car> findAllByNameContaining(Pageable pageable, String name);
}
