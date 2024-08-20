package com.example.SpringBootWebTest.repository;

import com.example.SpringBootWebTest.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TaskListRepo extends JpaRepository<TaskList, Long> {
}
