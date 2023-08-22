package com.example.eurukaCilent2.Repository;

import com.example.eurukaCilent2.Entity.MenuEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {
  List<MenuEntity> findByRestaurantId(int restaurantId);
}
