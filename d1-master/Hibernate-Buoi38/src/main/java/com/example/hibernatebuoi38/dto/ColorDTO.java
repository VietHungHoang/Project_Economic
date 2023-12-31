package com.example.hibernatebuoi38.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColorDTO {
    private Long id;

    private String name;

    private String code;
}
