package com.example.demo.Dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private String code;
    private String name;
    private Integer nbHabitants;
}
