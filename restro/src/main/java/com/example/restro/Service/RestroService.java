package com.example.restro.Service;

import com.example.restro.Entity.MenuEntity;
import com.example.restro.Entity.RestroEntity;
import com.example.restro.Repository.RestoRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestroService {

  @Autowired
  RestoRepository restoRepository;

  @Autowired
  EurekaClient eurekaClient;

  public RestroEntity create(RestroEntity restroEntity) {
    return restoRepository.save(restroEntity);
  }

  public RestroEntity getMenuNameByResturant(int ResturantId) {
    return restoRepository.findById(ResturantId).orElse(null);
  }

  public RestroEntity getRestroById(int id) {
    return restoRepository.findById(id).orElse(null);
  }

  public List<MenuEntity> getMenuByRestroId(int restaurantId) {
    Application application = eurekaClient.getApplication("MenuService");
    InstanceInfo instanceInfo = application.getInstances().get(0);
    String hostName = instanceInfo.getHostName();
    int port = instanceInfo.getPort();

    // Create a RestTemplate and call the API
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://" + hostName + ":" + port + "/menu/" + restaurantId;
    ResponseEntity<List<MenuEntity>> response = restTemplate.exchange(
      url,
      HttpMethod.GET,
      null,
      new ParameterizedTypeReference<List<MenuEntity>>() {}
    );
    List<MenuEntity> menuEntities = response.getBody();

    return menuEntities;
  }
}
