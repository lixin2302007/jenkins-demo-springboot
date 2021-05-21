package com.elane.learning.java8;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//1、业务场景是否真的需要并行处理?
//
//2、并行处理任务是否是相对独立? 是否会引起并行间的竞态条件?
//
//3、并行处理是否依赖任务的执行顺序?
public class ParallelStreamTest {


  public static void main(String[] args) throws InterruptedException {
//    Lists.newArrayList(1, 2, 3, 4, 5).parallelStream().forEach(System.out::println);

    //只要是并行处理, 如果在流程中的操作产生了竞态条件, 就会存在线程安全问题
//    List<Integer> integerList = Lists.newArrayList();
//    List<String> strList = Lists.newArrayList();
//
//    int practicalSize = 1000000;
//
//    for (int i = 0; i < practicalSize; i++) {
//      strList.add(String.valueOf(i));
//    }
//
//    strList.parallelStream().forEach(each -> {
//      integerList.add(Integer.parseInt(each));
//    });
//
//    System.out.println("  >>> integerList 预计长度 :: " + practicalSize);
//    System.out.println("  >>> integerList 实际长度 :: " + integerList.size());

// 项目中使用了并行流的代码, 真的能够达到并行么?
    System.out.println(String.format("  >>> 电脑 CPU 并行处理线程数 :: %s, 并行流公共线程池内线程数 :: %s",
        Runtime.getRuntime().availableProcessors(),
        ForkJoinPool.commonPool().getParallelism()));
    List<String> stringList = Lists.newArrayList();
    List<String> stringList2 = Lists.newArrayList();
    for (int i = 0; i < 13; i++) stringList.add(String.valueOf(i));
    for (int i = 0; i < 3; i++) stringList2.add(String.valueOf(i));

    new Thread(() -> {
      stringList.parallelStream().forEach(each -> {
        System.out.println(Thread.currentThread().getName() + " 开始执行 :: " + each);
        try {
          Thread.sleep(6000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }, "子线程-1").start();

    Thread.sleep(1500);

    new Thread(() -> {
      stringList2.parallelStream().forEach(each -> {
        System.out.println(Thread.currentThread().getName() + " :: " + each);
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });

    }, "子线程-2").start();
  }
}
