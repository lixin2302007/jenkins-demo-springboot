package com.elane.learning;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptionalTest {

  private static final Logger logger = LoggerFactory.getLogger(OptionalTest.class);

  @Test
  public void whenCreateOfNullableOptional_thenOk() {
    String name = "John";
    Optional<String> opt = Optional.ofNullable(name);

    assertEquals("John", opt.get());
  }

  @Test
  public void whenCheckIfPresent_thenOk() {
    User user = new User("john@gmail.com", "1234");
//    User user = null;
    Optional<User> opt = Optional.ofNullable(user);
    assertTrue(opt.isPresent());

    assertEquals(user.getEmail(), opt.get().getEmail());

    //还接受一个Consumer(消费者) 参数
    opt.ifPresent( u -> assertEquals(user.getEmail(), u.getEmail()));
  }

  // 区分orElse 和 orElseGet 不同，对象为空时
  @Test
  public void givenEmptyValue_whenCompare_thenOk() {
    User user = null;
    logger.debug("Using orElse");
    User result = Optional.ofNullable(user).orElse(createNewUser());
    logger.debug("Using orElseGet");
    User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    //Using orElse
    //Creating New User
    //Using orElseGet
    //Creating New User
  }

  // 区分orElse 和 orElseGet 不同，对象不为空时
  @Test
  public void givenPresentValue_whenCompare_thenOk() {
    User user = new User("john@gmail.com", "1234");
    logger.info("Using orElse");
    User result = Optional.ofNullable(user).orElse(createNewUser());
    logger.info("Using orElseGet");
    User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    //Using orElse
    //Creating New User
    //Using orElseGet
    //在执行较密集的调用时，比如调用 Web 服务或数据查询，这个差异会对性能产生重大影响。
  }

  private User createNewUser() {
    logger.debug("Creating New User");
    return new User("extra@gmail.com", "1234");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenThrowException_thenOk() {
    User user = null;
    User result = Optional.ofNullable(user)
        .orElseThrow( () -> new IllegalArgumentException());
  }

  @Test
  public void whenChaining_thenOk() {
    User user = new User("anna@gmail.com", "1234");

//    String result = Optional.ofNullable(user)
//        .flatMap(u -> u.getAddress())
//        .flatMap(a -> a.getCountry())
//        .map(c -> c.getIsocode())
//        .orElse("default");

    String result = Optional.ofNullable(user)
        .map(u -> u.getAddress())
        .map(a -> a.getCountry())
        .map(c -> c.getIsocode())
        .orElse("default");

    assertEquals(result, "default");
  }

  private static class User {
    private String email;
    private String name;

    public User(String email, String name) {
      this.email = email;
      this.name = name;
    }

    public String getEmail() {
      return this.email;
    }

    public String getName() {
      return this.name;
    }

    private Address address;

//    public Optional<Address> getAddress() {
//      return Optional.ofNullable(address);
//    }

    public Address getAddress() {
      return this.address;
    }

    public void setAddress(Address address) {
      this.address = address;
    }
  }

  private static class Address {
    private Country country;

//    public Optional<Country> getCountry() {
//      return Optional.ofNullable(country);
//    }

    public Country getCountry() {
      return this.country;
    }

    public void setCountry(Country country) {
      this.country = country;
    }

  }

  private static class Country {
    private String isocode;

    public String getIsocode() {
      return this.isocode;
    }

    public void setIsocode(String isocode) {
      this.isocode = isocode;
    }
  }

}
