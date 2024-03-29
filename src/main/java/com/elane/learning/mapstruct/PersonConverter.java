package com.elane.learning.mapstruct;

import java.util.List;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

//@Mapper 只有在接口加上这个注解， MapStruct 才会去实现该接口
//    @Mapper 里有个 componentModel 属性，主要是指定实现类的类型，一般用到两个
//    default：默认，可以通过 Mappers.getMapper(Class) 方式获取实例对象
//    spring：在接口的实现类上自动添加注解 @Component，可通过 @Autowired 方式注入
//@Mapping：属性映射，若源对象属性与目标对象名字一致，会自动映射对应属性
//    source：源属性
//    target：目标属性
//    dateFormat：String 到 Date 日期之间相互转换，通过 SimpleDateFormat，该值为 SimpleDateFormat              的日期格式
//    ignore: 忽略这个字段
//@Mappings：配置多个@Mapping
//@MappingTarget 用于更新已有对象
//@InheritConfiguration 用于继承配置
//————————————————
//版权声明：本文为CSDN博主「志哥谈笑间」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/zhige_me/article/details/80699784
//一对一
@Mapper(imports = {CustomMapping.class})
public interface PersonConverter {

  PersonConverter INSTANCE = Mappers.getMapper(PersonConverter.class);
  @Mappings({
      @Mapping(source = "birthday", target = "birth"),
      @Mapping(source = "birthday", target = "birthDateFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
      @Mapping(target = "birthExpressionFormat", expression = "java(org.apache.commons.lang3.time.DateFormatUtils.format(person.getBirthday(),\"yyyy-MM-dd HH:mm:ss\"))"),
      @Mapping(source = "user.age", target = "age"),
      @Mapping(target = "ageLevel", expression = "java(CustomMapping.ageLevel(person.getUser().getAge()))"),
      @Mapping(target = "email", ignore = true)
  })
  PersonDTO domain2dto(Person person);

  List<PersonDTO> domain2dto(List<Person> people);

  //如果目标对象已经存在，更新目标对象
  @InheritConfiguration(name = "domain2dto")
  void update(Person person, @MappingTarget PersonDTO personDTO);

  // 形式如下
//  default PersonDTO personToPersonDTO(Person person) {
//    //hand-written mapping logic
//  }

  // 比如在 PersonConverter 里面加入如下
  default Boolean convert2Bool(Integer value) {
    if (value == null || value < 1) {
      return Boolean.FALSE;
    } else {
      return Boolean.TRUE;
    }
  }

  default Integer convert2Int(Boolean value) {
    if (value == null) {
      return null;
    }
    if (Boolean.TRUE.equals(value)) {
      return 1;
    }
    return 0;
  }


}
