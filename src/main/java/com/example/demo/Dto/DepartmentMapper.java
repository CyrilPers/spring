package com.example.demo.Dto;

import com.example.demo.Entities.Departement;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentDto toDto(Departement departement) {
        DepartmentDto dto = new DepartmentDto();
        dto.setCode(departement.getCode());
        dto.setName(departement.getName());
        dto.setNbHabitants(departement.getCities().stream().mapToInt(city -> city.getNbHabitant()).sum());
        return dto;
    }

    public Departement toEntity(DepartmentDto departmentDto) {
        Departement entity = new Departement();
        entity.setCode(departmentDto.getCode());
        entity.setName(departmentDto.getName());
        return entity;

    }
}
