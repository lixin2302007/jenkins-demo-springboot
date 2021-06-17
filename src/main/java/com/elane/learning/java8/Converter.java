package com.elane.learning.java8;

@FunctionalInterface
public interface Converter<F, T> {

  T convert(F from);

  default Integer convertStringToInteger(String from) {
    return Integer.valueOf(from);
  }

}
