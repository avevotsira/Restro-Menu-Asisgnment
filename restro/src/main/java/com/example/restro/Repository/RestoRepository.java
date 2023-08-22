package com.example.restro.Repository;

import com.example.restro.Entity.RestroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestoRepository extends JpaRepository<RestroEntity, Integer> {}
