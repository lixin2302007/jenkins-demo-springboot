package com.elane.learning.aynsc;

import com.elane.learning.utils.SpringContextHolder;
import java.io.File;
import org.springframework.aop.framework.AopContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UploadServiceImpl implements UploadService{

  @Override
  public String Upload(File file) {
    System.out.println("开始上传图片");
    // 同类下方法直接调用，会变同步
    // transformToPictures();

    //获取当前类代理，调用异步方法，方式1
//    UploadService uploadService = SpringContextHolder.getApplicationContext().getBean(UploadService.class);
//    uploadService.transformToPictures();
    //方式2，没成功 IllegalStateException: Cannot find current proxy: Set 'exposeProxy' property on Advised to 'true'
    // 原因: @EnableAsync AsyncAnnotationBeanPostProcessor 它用于给@Async生成代理，但是它仅仅是个BeanPostProcessor并不属于自动代理创建器，因此exposeProxy = true对它无效
    // 解决给@Async 方法加上@Transcation 分析看 https://blog.csdn.net/weixin_40910372/article/details/103565970
//    UploadService uploadService = (UploadService) AopContext.currentProxy();
//    uploadService.transformToPictures();
    //方式3 @see MyBeanFactoryPostProcessor
    UploadService uploadService = (UploadService) AopContext.currentProxy();
    uploadService.transformToPictures();
    return "上传成功";
  }

//  @Transactional
  @Async
  @Override
  public void transformToPictures() {
    System.out.println("开始图片转换ing");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("转换图片成功！");
  }
}
