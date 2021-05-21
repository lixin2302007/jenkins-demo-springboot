package com.elane.learning;

import com.elane.learning.jta.model.Books;
import com.elane.learning.jta.service.BooksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = LearningApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class JtaTest {

  @Autowired
  private BooksService booksService;

//  @Test
//  public void testJta() {
//    Books books = Books.builder().id(12078).title("jta test").pages(1).month(2).year(2021).price(100.00)
//        .pub("haha").build();
//    booksService.insertBooks1AndBooks2(books, 1);
//  }
//
//  @Test
//  public void testJtaRollback() {
//    Books books = Books.builder().id(12078).title("jta test").pages(1).month(2).year(2021).price(100.00)
//        .pub("haha").build();
//    booksService.insertBooks1AndBooks2(books, 0);
//  }
}
