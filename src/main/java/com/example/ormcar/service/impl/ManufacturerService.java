package com.example.ormcar.service.impl;

import com.example.ormcar.model.DTO.ICountManufacturer;
import com.example.ormcar.model.Manufacturer;

import com.example.ormcar.repository.IManufacturerRepository;

import com.example.ormcar.service.IManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService implements IManufacturerService {

    @Autowired

    private IManufacturerRepository iManufacturerRepository;

    @Override
    public Iterable<Manufacturer> findAll() {
        return iManufacturerRepository.findAll();
    }

    @Override
    public void save(Manufacturer manufacturer) {
        iManufacturerRepository.save(manufacturer);
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return iManufacturerRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iManufacturerRepository.deleteById(id);
    }

    @Override
    public void xoaNhaSanXuatTheoId(Long id) {
        iManufacturerRepository.xoaNhaSanXuatTheoId(id);
    }

    @Override
    public Iterable<ICountManufacturer> TongHopSoLuongNSX() {
        return iManufacturerRepository.TongHopSoLuongNSX();
    }


}
