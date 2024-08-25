package com.example.ormcar.service;

import com.example.ormcar.model.DTO.ICountManufacturer;
import com.example.ormcar.model.Manufacturer;

import java.util.List;

public interface IManufacturerService extends IGenerateService<Manufacturer> {
    void xoaNhaSanXuatTheoId(Long id);

    Iterable<ICountManufacturer> TongHopSoLuongNSX();
}
