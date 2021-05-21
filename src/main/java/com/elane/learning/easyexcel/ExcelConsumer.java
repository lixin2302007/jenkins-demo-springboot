package com.elane.learning.easyexcel;

import java.util.List;

@FunctionalInterface
public interface  ExcelConsumer<E> {

  void excute(List<E> e);
}
