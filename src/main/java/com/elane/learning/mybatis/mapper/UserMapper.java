package com.elane.learning.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.elane.learning.controller.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;

public interface UserMapper extends BaseMapper<User> {

  /**
   * 导出用户功能
   */
  @Select("select * from user")
  @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = Integer.MIN_VALUE)
  @ResultType(User.class)
  void export(ResultHandler<User> handler);

  @Select("select * from user limit #{limit}")
  Cursor<User> scan(@Param("limit") int limit);
}
