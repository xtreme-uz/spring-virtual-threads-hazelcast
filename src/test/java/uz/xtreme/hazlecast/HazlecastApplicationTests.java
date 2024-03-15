package uz.xtreme.hazlecast;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.Serializable;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest
class HazlecastApplicationTests {

  private static final GenericContainer<?> HAZELCAST = new FixedHostPortGenericContainer(
      "hazelcast/hazelcast:5.5.0-SNAPSHOT-jdk17")
      .withFixedExposedPort(5701, 5701);

  @Autowired
  ApplicationContext applicationContext;
  @Autowired
  CacheManager cacheManager;

  @BeforeAll
  static void init() {
    HAZELCAST.start();
  }

  @Test
  void contextLoads() {
    assertNotNull(applicationContext);
  }

  @Test
  void cacheManagerTest() {
    Cache cache = cacheManager.getCache("test_cache");
    assertNotNull(cache);

    TestPayload givenPayload = new TestPayload(UUID.randomUUID(), "test_content", 12L);
    cache.put("test_key", givenPayload);
    TestPayload actualPayload = cache.get("test_key", TestPayload.class);

    assertNotNull(actualPayload);
    assertThat(actualPayload).usingRecursiveComparison().isEqualTo(givenPayload);
  }

  public record TestPayload(UUID id, String content, Long contentSize) implements Serializable {

  }
}
