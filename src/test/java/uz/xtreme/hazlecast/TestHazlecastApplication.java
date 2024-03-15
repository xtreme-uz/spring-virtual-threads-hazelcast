package uz.xtreme.hazlecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestHazlecastApplication {

  public static void main(String[] args) {
    SpringApplication.from(HazlecastApplication::main).with(TestHazlecastApplication.class)
        .run(args);
  }

}
