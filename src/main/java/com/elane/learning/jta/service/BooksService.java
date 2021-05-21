package com.elane.learning.jta.service;

import com.elane.learning.jta.datasource1.BooksMapper1;
import com.elane.learning.jta.datasource2.BooksMapper2;
import com.elane.learning.jta.model.Books;
//import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

//  @Autowired
//  private BooksMapper1 booksMapper1;
//  @Autowired
//  private BooksMapper2 booksMapper2;
//
//  @Transactional
//  public void insertBooks1AndBooks2(Books books, int rollback) {
//    booksMapper1.insert(books);
//    booksMapper2.insert(books);
//    int a = 1 / rollback;
//  }
}
