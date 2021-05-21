package com.elane.learning.autoconfigure;

import com.elane.learning.exception.Result;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example")
public class ExampleController {

  @RequestMapping("/test")
  public Result test() {
    Example example = new Example();
    example.setDate(new Date());
    example.setLocalDate(LocalDate.now());
    example.setLocalTime(LocalTime.now());
    example.setLocalDateTime(LocalDateTime.now());
    Result<Example> result = new Result("success");
    result.setData(example);
    return result;
  }
}
