package com.example.eurukaCilent2.Service;

import com.example.eurukaCilent2.Entity.MenuEntity;
import com.example.eurukaCilent2.Entity.RestroEntity;
import com.example.eurukaCilent2.Repository.MenuRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MenuService {

  @Autowired
  private MenuRepository menuRepository;

  @Autowired
  private EurekaClient eurekaClient;

  public MenuEntity createMenu(MenuEntity menuEntity) {
    // Generate the menu name based on the restaurant name
    String restaurantName = getRestaurantName(menuEntity.getRestaurantId());
    String menuName = generateMenuName(
      menuEntity.getRestaurantId(),
      restaurantName
    );

    // Set the generated menu name
    menuEntity.setMenuName(menuName);

    // Save the menuEntity to the 'menutab' table
    return menuRepository.save(menuEntity);
  }

  private String getRestaurantName(int restaurantId) {
    // Use the RestroServiceClient to get the restaurant details
    Application application = eurekaClient.getApplication("eureka-restro");
    InstanceInfo instanceInfo = application.getInstances().get(0);
    String hostName = instanceInfo.getHostName();
    int port = instanceInfo.getPort();

    // Create a RestTemplate and call the API
    RestTemplate restTemplate = new RestTemplate();
    String url =
      "http://" + hostName + ":" + port + "/restro/get" + restaurantId;
    ResponseEntity<RestroEntity> response = restTemplate.getForEntity(
      url,
      RestroEntity.class
    );
    RestroEntity restroEntity = response.getBody();
    System.out.println(response.getBody());
    // Extract the restaurant name from the RestroEntity and return it
    return restroEntity.getName();
  }

  private String generateMenuName(int restaurantId, String restaurantName) {
    if (restaurantName.length() > 6) {
      return "menu_" + restaurantId;
    }
    return restaurantId + "_menu";
  }

  public List<MenuEntity> findByRestaurantId(int restaurantId) {
    return menuRepository.findByRestaurantId(restaurantId);
  }
}
