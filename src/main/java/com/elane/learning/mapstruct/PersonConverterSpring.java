package com.elane.learning.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//spring方式
@Mapper(componentModel="spring")
public interface PersonConverterSpring {

  @Mappings({
      @Mapping(source = "birthday", target = "birth"),
      @Mapping(source = "birthday", target = "birthDateFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
      @Mapping(target = "birthExpressionFormat", expression = "java(org.apache.commons.lang3.time.DateFormatUtils.format(person.getBirthday(),\"yyyy-MM-dd HH:mm:ss\"))"),
      @Mapping(source = "user.age", target = "age"),
      @Mapping(target = "email", ignore = true)
  })
  PersonDTO domain2dto(Person person);
}
