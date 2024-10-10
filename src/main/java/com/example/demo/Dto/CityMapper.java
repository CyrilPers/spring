package com.example.demo.Dto;

import com.example.demo.Entities.City;
import com.example.demo.Entities.Departement;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public CityDto toDto(City city) {
        CityDto dto = new CityDto();
        dto.setName(city.getName());
        dto.setNbHabitant(city.getNbHabitant());
        dto.setCodeDepartment(city.getDepartement().getCode());
        dto.setNameDepartment(city.getDepartement().getName());
        return dto;
    }

    public City toEntity(CityDto dto) {
        City entity = new City();
        entity.setName(dto.getName());
        entity.setNbHabitant(dto.getNbHabitant());
        Departement dpt = new Departement();
        dpt.setCode(dto.getCodeDepartment());
        dpt.setName(dto.getNameDepartment());
        entity.setDepartement(dpt);
        return entity;
    }
}
