package com.elane.learning.java8;

import java.util.Date;
import lombok.Data;

@Data
public class Book {
  /**
   * 文章字数
   */
  private Integer wordCount;
  /**
   * 出版日期
   */
  private Date publishDate;
}
