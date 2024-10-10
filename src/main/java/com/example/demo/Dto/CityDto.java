package com.example.demo.Dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {
    private String name;
    private Integer nbHabitant;
    private String codeDepartment;
    private String nameDepartment;
}
