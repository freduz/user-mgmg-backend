package com.decadis.task.usermgmt.repository;

import com.decadis.task.usermgmt.entity.Action;
import com.decadis.task.usermgmt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActionRepository extends JpaRepository<Action,Long> {
    Optional<Action> findByAction(String name);
}
