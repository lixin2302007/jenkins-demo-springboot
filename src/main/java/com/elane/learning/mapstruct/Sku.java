package com.elane.learning.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sku {
  private Long id;
  private String code;
  private Integer price;
}