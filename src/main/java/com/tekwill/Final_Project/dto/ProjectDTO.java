package com.tekwill.Final_Project.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProjectDTO {
    private Integer projectId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String desc;
    @NotNull
    private Date startDate;
    @NotNull
    private Date expDate;
}
