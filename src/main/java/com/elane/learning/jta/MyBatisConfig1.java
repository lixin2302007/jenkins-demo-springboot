package com.elane.learning.jta;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
//@MapperScan(basePackages = "com.elane.learning.jta.datasource1", sqlSessionTemplateRef = "testSqlSessionTemplate1")
public class MyBatisConfig1 {
  //创建一个bean对象，并注入到Spring容器中
//  @Bean(name = "testDataSource1")
//  public DataSource testDataSource(DBConfig1 testConfig) throws SQLException {
//    MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
//    mysqlXaDataSource.setUrl(testConfig.getUrl());
//    mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
//    mysqlXaDataSource.setPassword(testConfig.getPassword());
//    mysqlXaDataSource.setUser(testConfig.getUsername());
//    mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
//
//    //将本地事务注册到Atomikos全局事务
//    AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
//    xaDataSource.setXaDataSource(mysqlXaDataSource);
//    xaDataSource.setUniqueResourceName("testDataSource1");
//
//    xaDataSource.setMinPoolSize(testConfig.getMinPoolSize());
//    xaDataSource.setMaxPoolSize(testConfig.getMaxPoolSize());
//    xaDataSource.setMaxLifetime(testConfig.getMaxLifetime());
//    xaDataSource.setBorrowConnectionTimeout(testConfig.getBorrowConnectionTimeout());
//    xaDataSource.setLoginTimeout(testConfig.getLoginTimeout());
//    xaDataSource.setMaintenanceInterval(testConfig.getMaintenanceInterval());
//    xaDataSource.setMaxIdleTime(testConfig.getMaxIdleTime());
//    xaDataSource.setTestQuery(testConfig.getTestQuery());
//
////        Connection conn = xaDataSource.getConnection();
////        PreparedStatement ps = conn.prepareStatement("select * from student1");
////        ResultSet res = ps.executeQuery();
////        System.out.println(res);
////        conn.close();
//    return xaDataSource;
//  }
//
//  /**
//   * 功能描述:(datasource1 sql会话工厂)
//   * @param dataSource
//   * @return
//   * @throws Exception
//   */
//  @Bean(name = "testSqlSessionFactory1")
//  //@Qualifier表示查找Spring容器中名字为datasource1DataSource的对象
//  public SqlSessionFactory testSqlSessionFactory(@Qualifier("testDataSource1") DataSource dataSource)
//      throws Exception {
//    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//    bean.setDataSource(dataSource);
//    return bean.getObject();
//  }
//
//  @Bean(name = "testSqlSessionTemplate1")
//  public SqlSessionTemplate testSqlSessionTemplate(
//      @Qualifier("testSqlSessionFactory1") SqlSessionFactory sqlSessionFactory) throws Exception {
//    return new SqlSessionTemplate(sqlSessionFactory);
//  }
}
