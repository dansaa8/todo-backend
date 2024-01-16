package com.example.todobackend.task;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends ListCrudRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.userId = :id")
    List<TaskView> findMyTasks(@Param("id") String id);
}
