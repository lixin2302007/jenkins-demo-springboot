package com.elane.learning.easypoi;

import static com.elane.learning.easypoi.TalentUserInputEntity.DATE_REGEXP;

import cn.afterturn.easypoi.excel.annotation.Excel;
import java.util.Date;
import javax.validation.constraints.Pattern;
import lombok.Data;

// 教育经历对象
@Data
public class EducationInputEntity {
  @Excel(name = "学校*")
  private String schoolName;

  @Excel(name = "学历*", replace = {"初中及以下_1", "中专_2", "高中_3", "大专_4", "本科_5", "硕士_6", "博士_7"})
  @Pattern(regexp = "[1234567]", message = "学历信息错误")
  private String recordStr;

  @Excel(name = "开始年份*")
  @Pattern(regexp = DATE_REGEXP, message = "[教育经历][开始年份]时间格式错误")
  private String beginTimeStr;

  @Excel(name = "毕业年份*")
  @Pattern(regexp = DATE_REGEXP, message = "[教育经历][毕业年份]时间格式错误")
  private String finishTimeStr;

  @Excel(name = "专业*")
  private String profession;
}
