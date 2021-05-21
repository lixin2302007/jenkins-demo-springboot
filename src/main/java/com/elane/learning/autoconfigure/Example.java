package com.elane.learning.autoconfigure;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import lombok.Data;

@Data
public class Example {
  private LocalDateTime localDateTime;
  private LocalDate localDate;
  private Date date;
  private LocalTime localTime;
}
