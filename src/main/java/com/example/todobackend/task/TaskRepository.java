package com.example.todobackend.task;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends ListCrudRepository<Task, Long> {

    List<TaskView> findAllBy();

    @Query("SELECT t FROM Task t WHERE t.userId = :id")
    List<TaskView> findMyTasks(@Param("id") String id);
}
