package uz.xtreme.hazlecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RestController;

@EnableCaching
@RestController
@SpringBootApplication
public class HazlecastApplication {
  public static void main(String[] args) {
    SpringApplication.run(HazlecastApplication.class, args);
  }

}
