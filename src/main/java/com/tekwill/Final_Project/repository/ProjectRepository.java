package com.tekwill.Final_Project.repository;

import com.tekwill.Final_Project.model.ProjectModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectModel, Integer> {
}
