package com.elane.learning;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.elane.learning.mapstruct.CustomMapping;
import com.elane.learning.mapstruct.Person;
import com.elane.learning.mapstruct.PersonConverter;
import com.elane.learning.mapstruct.PersonDTO;
import com.elane.learning.mapstruct.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

public class PersonConverterTest {

  @Test
  public void test() {
    Person person = new Person(1L,"zhige","zhige.me@gmail.com", new Date(),new User(18l));
    PersonDTO personDTO = PersonConverter.INSTANCE.domain2dto(person);
    assertNotNull(personDTO);
    assertEquals(personDTO.getId(), person.getId());
    assertEquals(personDTO.getName(), person.getName());
    assertEquals(personDTO.getBirth(), person.getBirthday());
    String format = DateFormatUtils.format(personDTO.getBirth(), "yyyy-MM-dd HH:mm:ss");
    assertEquals(personDTO.getBirthDateFormat(),format);
    assertEquals(personDTO.getBirthExpressionFormat(),format);
    assertEquals(personDTO.getAgeLevel(), CustomMapping.ageLevel(18));
// 测试类 PersonConverterTest 加入
    assertTrue(PersonConverter.INSTANCE.convert2Bool(1));
    assertEquals((int)PersonConverter.INSTANCE.convert2Int(true),1);

    List<Person> people = new ArrayList<>();
    people.add(person);
    List<PersonDTO> personDTOs = PersonConverter.INSTANCE.domain2dto(people);
    assertNotNull(personDTOs);
  }

  @Test
  public void testUpd() {
    Person person = new Person(1L,"zhige","zhige.me@gmail.com",new Date(),new User(1l));
    PersonDTO personDTO = PersonConverter.INSTANCE.domain2dto(person);
    assertEquals("zhige", personDTO.getName());
    person.setName("xiaozhi");
    PersonConverter.INSTANCE.update(person, personDTO);
    assertEquals("xiaozhi", personDTO.getName());
  }
}
