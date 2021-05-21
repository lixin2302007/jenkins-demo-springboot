//package com.elane.learning.easyexcel;
//
//import com.alibaba.excel.EasyExcel;
//import java.io.File;
//import java.io.FilenameFilter;
//import java.io.IOException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/easyExcel")
//public class EasyExcelController {
//
//  @Autowired
//  private SendAddressService sendAddressService;
//
//  @PostMapping(value = "/upload")
//  public void upload(@RequestParam("file")MultipartFile file) throws IOException {
//    EasyExcel.read(file.getInputStream(), SendAddressTemplate.class, new ExcelListener(p->{
//      //保存逻辑（可封装一个普通的sevice）p-为我们执行函数时传入的list
//      sendAddressService.importExcel(p);
//    })).sheet().doRead();
//
//  }
//
//  public static void main(String[] args) {
//
//    File f = new File("d:\\dev");
//
//    File[] files = f.listFiles(new FilenameFilter() {
//      @Override
//      public boolean accept(File dir, String name) {
//        return name.endsWith(".zip");
//      }
//    });
//    System.out.println(files.length);
//  }
//
//}
