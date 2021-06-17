package com.elane.learning;

import com.elane.learning.java8.Converter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class Java8Test {

  @Test
  public void test() {
//    Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
    // java 关键字::来引用类的静态方法
    Converter<String, Integer> converter = Integer::valueOf;
    Integer converted = converter.convert("123");
    System.out.println(converted);    // 123

    // java 关键字::来引用类的方法
    Something something = new Something();
    Converter<String, String> converter1 = something::startsWith;
    String converted1 = converter1.convert("java");
    System.out.println(converted1);

    // lambda表达式，访问局部变量，成员变量和静态变量，
    // 不声明num为final类型，因为num为隐式final类型，在编译期内不能被改变
    int num = 1;
    Converter<Integer, String> stringConverter =
        (from) -> String.valueOf(from + num);

    stringConverter.convert(2);     // 3

    // lambda 表达式，不能访问带有默认实现的接口方法

  }

  @Test
  public void test_parallelStream() {
    int max = 1000000;
    List<String> values = new ArrayList<>(max);
    for (int i = 0; i < max; i++) {
      UUID uuid = UUID.randomUUID();
      values.add(uuid.toString());
    }

    // 纳秒
    long t0 = System.nanoTime();

    long count = values.stream().sorted().count();
    System.out.println(count);

    long t1 = System.nanoTime();

    // 纳秒转微秒
    long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
    System.out.println(String.format("顺序流排序耗时: %d ms", millis));

    // 顺序流排序耗时: 899 ms

    // 纳秒
    long t00 = System.nanoTime();

    long count1 = values.parallelStream().sorted().count();
    System.out.println(count1);

    long t11 = System.nanoTime();

    // 纳秒转微秒
    long millis1 = TimeUnit.NANOSECONDS.toMillis(t11 - t00);
    System.out.println(String.format("并行流排序耗时: %d ms", millis1));

    // 并行流排序耗时: 472 ms


  }

  class Something {
    String startsWith(String s) {
      return String.valueOf(s.charAt(0));
    }
  }
}
