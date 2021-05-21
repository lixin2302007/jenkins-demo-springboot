package com.elane.learning.jta.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Books {

  private Integer id;
  private String title;
  private Integer year;
  private Integer month;
  private Double price;
  private Integer pages;
  private String pub;
}
