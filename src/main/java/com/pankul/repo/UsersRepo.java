package com.pankul.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pankul.models.UsersModel;

@Repository
public interface UsersRepo extends JpaRepository<UsersModel, Long>{

}
