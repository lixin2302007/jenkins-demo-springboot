package com.elane.learning;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.elane.learning.annotations.ScanAnnotation;
import com.elane.learning.annotations.ScanClassInterface;
import com.elane.learning.aynsc.UploadService;
import com.elane.learning.controller.User;
import com.elane.learning.easypoi.ExcelReadHelper;
import com.elane.learning.event.DemoPublisher;
import com.elane.learning.jdbc.JdbcConcur;
import com.elane.learning.jta.DBConfig1;
import com.elane.learning.mybatis.mapper.UserMapper;
import com.elane.learning.mybatis.mapper.UserService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.LogManager;
import org.apache.poi.ss.usermodel.Workbook;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest(classes = LearningApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class LearningTest {

    @Test
    public void test() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
                false);
        // 扫描带有注解的类
        provider.addIncludeFilter(new AnnotationTypeFilter(ScanAnnotation.class));
        // 接口不会被扫描，其子类会被扫描出来
        provider.addIncludeFilter(new AssignableTypeFilter(ScanClassInterface.class));

        Set<BeanDefinition> scanList = provider.findCandidateComponents("com.elane.learning");
        for (BeanDefinition beanDefinition : scanList) {
            System.out.println(beanDefinition.getBeanClassName());
        }


    }

    @Autowired
    DemoPublisher demoPublisher;

    @Test
    public void testApplicationEvent() {
        demoPublisher.publish("lisa lisa lisa");
    }

    @Test
    public void testArronlongHttpUtil() throws HttpProcessException {
      CloseableHttpClient httpClient = HCB.custom().retry(3).build();
      HttpConfig httpConfig = HttpConfig.custom().url("httpserver://localhost:8080/user/user1?id=" + 1).client(httpClient);
      String response = HttpClientUtil.get(httpConfig);
      System.out.println(response);
    }

    public static final Logger LOGGER = LoggerFactory.getLogger(LearningTest.class);

    @Test
    public void testLog4j2() {
        // 日志消息输出
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
    }

    @Test
    public void dynaCol() {
        try {
            List<ExcelExportEntity> colList = new ArrayList<ExcelExportEntity>();
            ExcelExportEntity colEntity = new ExcelExportEntity("商品名称", "title");
            colEntity.setNeedMerge(true);
            colList.add(colEntity);

            colEntity = new ExcelExportEntity("供应商", "supplier");
            colEntity.setNeedMerge(true);
            colList.add(colEntity);

            ExcelExportEntity deliColGroup = new ExcelExportEntity("得力", "deli");
            List<ExcelExportEntity> deliColList = new ArrayList<ExcelExportEntity>();
            deliColList.add(new ExcelExportEntity("市场价", "orgPrice"));
            deliColList.add(new ExcelExportEntity("专区价", "salePrice"));
            deliColGroup.setList(deliColList);
            colList.add(deliColGroup);

            ExcelExportEntity jdColGroup = new ExcelExportEntity("京东", "jd");
            List<ExcelExportEntity> jdColList = new ArrayList<ExcelExportEntity>();
            jdColList.add(new ExcelExportEntity("市场价", "orgPrice"));
            jdColList.add(new ExcelExportEntity("专区价", "salePrice"));
            jdColGroup.setList(jdColList);
            colList.add(jdColGroup);


            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < 10; i++) {
                Map<String, Object> valMap = new HashMap<String, Object>();
                valMap.put("title", "名称." + i);
                valMap.put("supplier", "供应商." + i);

                List<Map<String, Object>> deliDetailList = new ArrayList<Map<String, Object>>();
                for (int j = 0; j < 3; j++) {
                    Map<String, Object> deliValMap = new HashMap<String, Object>();
                    deliValMap.put("orgPrice", "得力.市场价." + j);
                    deliValMap.put("salePrice", "得力.专区价." + j);
                    deliDetailList.add(deliValMap);
                }
                valMap.put("deli", deliDetailList);

                List<Map<String, Object>> jdDetailList = new ArrayList<Map<String, Object>>();
                for (int j = 0; j < 2; j++) {
                    Map<String, Object> jdValMap = new HashMap<String, Object>();
                    jdValMap.put("orgPrice", "京东.市场价." + j);
                    jdValMap.put("salePrice", "京东.专区价." + j);
                    jdDetailList.add(jdValMap);
                }
                valMap.put("jd", jdDetailList);

                list.add(valMap);
            }

            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("价格分析表", "数据"), colList,
                list);
            FileOutputStream fos = new FileOutputStream("D:/价格分析表.tt.xls");
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testImport() {
        InputStream in = getClass().getResourceAsStream("/示例Excel3.xlsx");
        ExcelReadHelper readHelper = new ExcelReadHelper(in);
        // 获取 B4 单元格的数据，以下同理
        String b4 = readHelper.getValueAt("B", 4);
        String c23 = readHelper.getValueAt("C", 23);
        String c24 = readHelper.getValueAt("C", 24);
        String m4 = readHelper.getValueAt("M", 4);
        String m5 = readHelper.getValueAt("M", 5);
        Assert.assertEquals("一众科技有限公司", b4);
        Assert.assertEquals("13112345678", c23);
        Assert.assertEquals("370827198801021000", c24);
        Assert.assertEquals("XH-HZHY-20170504", m4);
        Assert.assertEquals("2017.5.4", m5);
        readHelper.close();
    }


    @Autowired
    private UserService userService;

    @Test
    public void testExport() throws IOException {
        userService.getUserListForExcel();
    }

    @Autowired
    private JdbcConcur jdbcConcur;

    @Test
    public void testJdbcTemplateConcur() {
        jdbcConcur.concur();
    }

    @Autowired
    private UserMapper userMapper;

    // mybatis 流式查询 解决java.lang.IllegalStateException: A Cursor is already closed.
    @Test
    @Transactional
    public void testCursor() {
        try(Cursor<User> cursor = userMapper.scan(10)) {
            cursor.forEach(System.out::println);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testCursor2() {
        try(
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Cursor<User> cursor = sqlSession.getMapper(UserMapper.class).scan(10)) {
            cursor.forEach(System.out::println);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private PlatformTransactionManager transactionManager;
    @Test
    public void testCursor3() {
        TransactionTemplate transactionTemplate =
            new TransactionTemplate(transactionManager);
        transactionTemplate.execute(status -> {               // 2
            try (Cursor<User> cursor = userMapper.scan(10)) {
                cursor.forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Autowired
    private UploadService uploadService;

    @Test
    public void testAnysc() {
      String upload = uploadService.Upload(null);
      Assert.assertEquals("上传成功", upload);
    }

}
