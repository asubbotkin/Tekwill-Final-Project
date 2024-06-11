package com.tekwill.Final_Project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_info")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskModel {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "days_per_task")
    private Integer daysPerTask;

    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_project_id", referencedColumnName = "project_id")
    private ProjectModel projectModel;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
    private UserModel userModel;
}
