package com.tekwill.Final_Project.repository;

import com.tekwill.Final_Project.constants.TaskStatus;
import com.tekwill.Final_Project.model.TaskModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<TaskModel, Integer> {

    @Query("select tm from TaskModel tm where tm.projectModel.projectId = :projectId")
    List<TaskModel> findAllTasksInProject(Integer projectId);

    @Query(value = "select tm from TaskModel tm where tm.userModel.userId = :userId")
    List<TaskModel> findAllTasksOfUser(Integer userId);

//    @Query(value = "update TaskModel tm set tm.status = :status where tm.taskId = :taskId")
//    TaskModel taskDone(TaskStatus status, Integer taskId);
//
//    @Query("update TaskModel tm set tm.status = :status where tm.taskId = :taskId")
//    void taskInProgress(TaskStatus status, Integer taskId);

}
