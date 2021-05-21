package com.elane.learning.java8;

import java.util.Comparator;
import java.util.List;

public class ComparatorTest {

  public List<Book> test1(List<Book> bookList) {
    bookList.sort(Comparator.comparing((Book book) -> -book.getWordCount()).thenComparing(Book::getPublishDate));
    return bookList;
  }

  public List<Book> test2(List<Book> bookList) {
    bookList.sort(Comparator.comparing(Book::getWordCount).reversed().thenComparing(Book::getPublishDate));
    return bookList;
  }

  public List<Book> test6(List<Book> bookList) {
    bookList.sort(Comparator.comparing(Book::getPublishDate).thenComparing(book -> -book.getWordCount()));
    return bookList;
  }

  public List<Book> test7(List<Book> bookList) {
    bookList.sort(Comparator.comparing(book -> -book.getWordCount()));
    return bookList;
  }

}
