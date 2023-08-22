package com.example.eurukaCilent2.Controller;

import com.example.eurukaCilent2.Entity.MenuEntity;
import com.example.eurukaCilent2.Service.MenuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu/")
public class MenuController {

  @Autowired
  MenuService menuService;

  @PostMapping("create")
  MenuEntity createMenu(@RequestBody MenuEntity menuEntity) {
    return menuService.createMenu(menuEntity);
  }

  @GetMapping("{restaurantId}")
  List<MenuEntity> getMenuByRestaurantId(@PathVariable int restaurantId) {
    return menuService.findByRestaurantId(restaurantId);
  }
}
