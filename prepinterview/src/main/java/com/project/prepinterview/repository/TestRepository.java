package com.project.prepinterview.repository;

import com.project.prepinterview.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test,String> {
}
