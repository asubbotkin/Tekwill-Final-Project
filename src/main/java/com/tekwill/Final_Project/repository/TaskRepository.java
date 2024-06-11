package com.tekwill.Final_Project.repository;

import com.tekwill.Final_Project.model.TaskModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<TaskModel, Integer> {
}
