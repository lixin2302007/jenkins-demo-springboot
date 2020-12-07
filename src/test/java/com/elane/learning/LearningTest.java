package com.elane.learning;

import com.elane.learning.annotations.ScanAnnotation;
import com.elane.learning.annotations.ScanClassInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@SpringBootTest
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
}
