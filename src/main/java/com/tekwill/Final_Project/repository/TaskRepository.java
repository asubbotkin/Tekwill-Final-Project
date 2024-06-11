package com.tekwill.Final_Project.repository;

import com.tekwill.Final_Project.model.TaskModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<TaskModel, Integer> {

    @Query("select tm from TaskModel tm where tm.projectModel.projectId = :projectId")
    List<TaskModel> findAllTasksInProject(Integer projectId);

    @Query("select tm from TaskModel tm where tm.userModel.userId = :userId")
    List<TaskModel> findAllTasksOfUser(Integer userId);
}
