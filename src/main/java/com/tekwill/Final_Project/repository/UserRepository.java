package com.tekwill.Final_Project.repository;

import com.tekwill.Final_Project.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {
}
