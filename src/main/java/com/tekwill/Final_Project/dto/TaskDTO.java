package com.tekwill.Final_Project.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskDTO {
    private Integer taskId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String desc;
    @NotNull
    private Integer daysPerTask;
    @NotEmpty
    private String status;
    @NotNull
    private Integer projectId;
    @NotNull
    private Integer userId;
}
