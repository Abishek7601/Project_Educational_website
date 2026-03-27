package com.example.MyProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MyProject.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {
    Project  findByEmail(String email);
    Project findByUsername(String username);
    Project findByUsernameAndPassword(String username, String password);
    void deleteByUsername(String username);
}
