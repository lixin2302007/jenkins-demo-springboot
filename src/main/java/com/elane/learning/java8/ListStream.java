package com.elane.learning.java8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ListStream {
  private static List<String> simpleList = new ArrayList<>();
  private static List<Student> normalList = new ArrayList<>();
  static {
    simpleList.add("apple");
    simpleList.add("apple");
    simpleList.add("banana");
    simpleList.add("orange");

    normalList.add(new Student(1, "Emma", "A", 701));
    normalList.add(new Student(2, "Larissa", "S", 701));
    normalList.add(new Student(3, "Sophia", "B", 701));
    normalList.add(new Student(4, "Ashley", "B", 702));
    normalList.add(new Student(5, "May", "C", 702));
    normalList.add(new Student(6, "Hailey", "D", 702));
    normalList.add(new Student(7, "Kelly", "S", 703));
    normalList.add(new Student(8, "Amy", "A", 703));
    normalList.add(new Student(9, "Wesley", "C", 703));
  }
  public static void main(String[] args){
    // TODO
    System.out.println("----------------简单List---------------");
    simpleList.forEach(System.out::println);
    System.out.println("----------------简单List转Set---------------");
    Set<String> simpleSet = new HashSet<>(simpleList);
    simpleSet.forEach(System.out::println);

    System.out.println("----------------普通List转Map---------------");
    Map<Integer,Student> normalMap = normalList.stream().collect(Collectors.toMap(Student::getId,(b)->b));
    normalMap.forEach((id, student) -> {
      System.out.println(id + "::" + student);
    });

    List<Student> students = new ArrayList<>(normalList);
    System.out.println("----------------原数据---------------");
    students.forEach(System.out::println);
    System.out.println("----------------List<Student>转Map<String, Student>重复key只保留前者---------------");
    // 重复key处理 (s1, s2) -> s1)
    Map<Integer, Student> classStudentMap = students.stream().collect(Collectors.toMap(Student::getClassNo, s -> s, (s1, s2) -> s1));
    classStudentMap.forEach((classNo, student) -> System.out.println(classNo + "::" + student));

    System.out.println("----------------List<Student>转Map<String, List<Student>>---------------");
    // 重复key处理成一个集合
    Map<Integer, List<Student>> listMap = students.stream().collect(Collectors.toMap(Student::getClassNo, s -> {
      List<Student> l = new ArrayList<>();
      l.add(s);
      return l;
    }, (List<Student> s1, List<Student> s2) -> {
      s1.addAll(s2);
      return s1;
    }));
    listMap.forEach((learn, student) -> System.out.println(learn + "::" + student));

    students = new ArrayList<>(normalList);
    System.out.println("----------------分组---------------");
    // 根据key分组
    Map<String, List<Student>> grouping = students.stream().collect(Collectors.groupingBy(Student::getScore));
    grouping.forEach((score, student) -> System.out.println(score + "::" + student));
    System.out.println("----------------按照多个属性分组---------------");
    // 根据多个key的组合分组
    grouping = students.stream().collect(Collectors.groupingBy( e -> e.getClassNo() + "_" + e.getScore()));
    grouping.forEach((learn, student) -> System.out.println(learn + "::" + student));

    students = new ArrayList<>(normalList);
    // 先按照成绩分组，准备好数据
    grouping = students.stream().collect(Collectors.groupingBy(Student::getScore));
    System.out.println("----------------Map<String, List<Student>>排序---------------");
    Map<String, List<Student>> result = new LinkedHashMap<>();
    // Map的key有特殊处理
    grouping.entrySet().stream()
        .sorted((o1,o2) -> {
          Integer k1 = getWeight(o1.getKey());
          Integer k2 = getWeight(o2.getKey());
          return k1.compareTo(k2);
        })
        .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
    result.forEach((learn, student) -> System.out.println(learn + "::" + student));
    System.out.println("----------------");
    Map<String, List<Student>> result2 = new LinkedHashMap<>();
    // 仅仅按照key排序
    grouping.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));
    result2.forEach((learn, student) -> System.out.println(learn + "::" + student));
    System.out.println("----------------");
    Map<String, List<Student>> result3 = new LinkedHashMap<>();
    // 等价第一个，只是省去了getKey方法
    grouping.entrySet().stream()
        .sorted(Map.Entry.comparingByKey((o1,o2) -> {
          Integer k1 = getWeight(o1);
          Integer k2 = getWeight(o2);
          return k1.compareTo(k2);
        }))
        .forEachOrdered(x -> result3.put(x.getKey(), x.getValue()));
    result3.forEach((learn, student) -> System.out.println(learn + "::" + student));

    System.out.println("----------------倒序----------------");
    Map<String, List<Student>> result4 = new LinkedHashMap<>();
// 仅仅按照key排序
    grouping.entrySet().stream()
        .sorted(Map.Entry.<String, List<Student>>comparingByKey().reversed())
        .forEachOrdered(x -> result4.put(x.getKey(), x.getValue()));
    result4.forEach((learn, student) -> System.out.println(learn + "::" + student));
    System.out.println("----------------");
    Map<String, List<Student>> result5 = new LinkedHashMap<>();
// 等价第一个，只是省去了getKey方法
    grouping.entrySet().stream()
        .sorted(Map.Entry.<String, List<Student>>comparingByKey((o1,o2) -> {
          Integer k1 = getWeight(o1);
          Integer k2 = getWeight(o2);
          return k1.compareTo(k2);
        }).reversed())
        .forEachOrdered(x -> result5.put(x.getKey(), x.getValue()));
    result5.forEach((learn, student) -> System.out.println(learn + "::" + student));
  }

  /**
   * 不同成绩有不同的排序权重
   * @param score
   * @return
   */
  public static Integer getWeight(String score){
    switch (score){
      case "S": return 1;
      case "A": return 2;
      case "B": return 3;
      case "C": return 2;
      case "D": return 2;
      default:return 0;
    }
  }
}
