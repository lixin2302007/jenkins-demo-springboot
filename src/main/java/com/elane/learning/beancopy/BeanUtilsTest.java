package com.elane.learning.beancopy;

import java.util.Arrays;
import java.util.List;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

//测试spring BeanUtils.copyProperties 问题
public class BeanUtilsTest {

  public static void main(String[] args) {
    A first = new A();
    first.setName("demo");
    first.setIds(Arrays.asList(1, 2, 3));

//    B second = new B();
//    BeanUtils.copyProperties(first, second);
//    for (String each : second.getIds()) {// 类型转换异常
//      System.out.println(each);
//    }

    B second1 = new B();
    final BeanCopier beanCopier = BeanCopier.create(A.class, B.class, true);
    beanCopier.copy(first, second1, new CustomConverter());

    for (String each : second1.getIds()) {// 类型转换异常
      System.out.println(each);
    }
  }

  @Data
  public static class A {
    private String name;

    private List<Integer> ids;
  }

  @Data
  public static class B {
    private String name;

    private List<String> ids;
  }
}
