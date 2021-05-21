package com.elane.learning;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.elane.learning.mapstruct.Person;
import com.elane.learning.mapstruct.PersonConverterSpring;
import com.elane.learning.mapstruct.PersonDTO;
import com.elane.learning.mapstruct.User;
import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseTestConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PersonConverterSpringTest {

  //这里把转换器装配进来
  @Autowired
  private PersonConverterSpring personConverterSpring;
  @Test
  public void test() {
    Person person = new Person(1L,"zhige","zhige.me@gmail.com",new Date(),new User(1l));
    PersonDTO personDTO = personConverterSpring.domain2dto(person);

    assertNotNull(personDTO);
    assertEquals(personDTO.getId(), person.getId());
    assertEquals(personDTO.getName(), person.getName());
    assertEquals(personDTO.getBirth(), person.getBirthday());
    String format = DateFormatUtils.format(personDTO.getBirth(), "yyyy-MM-dd HH:mm:ss");
    assertEquals(personDTO.getBirthDateFormat(),format);
    assertEquals(personDTO.getBirthExpressionFormat(),format);

  }

}
