package com.example.restro.Controller;

import com.example.restro.Entity.MenuEntity;
import com.example.restro.Entity.RestroEntity;
import com.example.restro.Service.RestroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/restro/")
public class RestroController {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  RestroService restroService;

  @PostMapping("create")
  public RestroEntity createRestaurant(@RequestBody RestroEntity restro) {
    return restroService.create(restro);
  }

  @GetMapping("{id}")
  public RestroEntity getRestroById(@PathVariable("id") int id) {
    System.out.println(restroService.getRestroById(id));
    return restroService.getRestroById(id);
  }

  @GetMapping("get/{restaurantId}")
  public List<MenuEntity> getMenuByRestroId(
    @PathVariable("restaurantId") int restaurantId
  ) {
    return restroService.getMenuByRestroId(restaurantId);
  }
}
