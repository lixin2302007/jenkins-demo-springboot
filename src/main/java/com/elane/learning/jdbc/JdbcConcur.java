package com.elane.learning.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

//一、普通查询
//
//优点：应用代码简单，数据量较小时操作速度快。
//缺点：数据量大时会出现OOM问题。
//二、流式查询
//
//优点：大数据量时不会有OOM问题。
//缺点：占用数据库时间更长，导致网络拥塞的可能性较大。
//三、游标查询
//
//优点：大数据量时不会有OOM问题，相比流式查询对数据库单次占用时间较短。
//缺点：相比流式查询，对服务端资源消耗更大，响应时间更长。
//
//作者：蚊子squirrel
//链接：https://www.jianshu.com/p/c7c5dbe63019
//来源：简书
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
@Component
public class JdbcConcur {

  public static void main(String[] args) throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection connection = DriverManager
        .getConnection(
            "jdbc:mysql://10.10.9.211:3306/test1?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CTT&characterEncoding=UTF-8",
            "root", "Pass@123");
    PreparedStatement preparedStatement =
        connection.prepareStatement("select * from user",
            ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    // 这里因为MySQL驱动实现使用Integer.MIN_VALUE来判断是否使用流的方式
    preparedStatement.setFetchSize(Integer.MIN_VALUE);

    ResultSet resultSet = preparedStatement.executeQuery();

    while (resultSet.next()) {
      System.out.println(resultSet.getString("id"));
    }
    connection.close();
  }

  @Autowired(required = false)
  private JdbcTemplate jdbcTemplate;

  public void concur() {
    jdbcTemplate.query(con -> {
      PreparedStatement preparedStatement =
          con.prepareStatement("select * from user",
              ResultSet.TYPE_FORWARD_ONLY,
              ResultSet.CONCUR_READ_ONLY);
      preparedStatement.setFetchSize(Integer.MIN_VALUE);
      preparedStatement.setFetchDirection(ResultSet.FETCH_FORWARD);
      return preparedStatement;
    }, rs -> {
      do {
        System.out.println(rs.getString("id"));
      } while (rs.next());
    });
  }
}

