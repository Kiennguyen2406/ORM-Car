package com.example.ormcar.repository;

import com.example.ormcar.model.DTO.ICountManufacturer;
import com.example.ormcar.model.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IManufacturerRepository extends CrudRepository<Manufacturer,Long> {
    //query de sua du lieu
    @Modifying
//query de thuc hien nhieu thao tac
    @Transactional
    @Query(value = "CALL deleteManufacturer(:id)", nativeQuery = true)
    void xoaNhaSanXuatTheoId(@Param("id")Long id);

    @Query(nativeQuery = true, value = "CALL findNameManuAndCountIdCar()")
    Iterable<ICountManufacturer> TongHopSoLuongNSX();
}
