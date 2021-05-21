package com.elane.learning.mapstruct;

import com.elane.learning.mapstruct.UserVo.UserConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapping extends BaseMapping<User, UserVo> {

  @Mapping(target = "gender", source = "sex")
  @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
  @Override
  UserVo sourceToTarget(User var1);

  @Mapping(target = "sex", source = "gender")
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
  @Override
  User targetToSource(UserVo var1);

  default List<UserConfig> strConfigToListUserConfig(String config) {
    return new Gson().fromJson(config, new TypeToken<List<UserConfig>>(){}.getType());
  }

  default String listUserConfigToStrConfig(List<UserConfig> list) {
    return new Gson().toJson(list);
  }
}
