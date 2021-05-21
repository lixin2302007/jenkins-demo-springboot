package com.elane.learning.aliyun;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
public class SlowLogCountReq extends SlowLogReq{

  private String SortKey; //排序依据，取值：

//  TotalExecutionCounts：总执行次数最多
//  TotalQueryTimes：总执行时间最多
//  TotalLogicalReads：总逻辑读最多
//  TotalPhysicalReads：总物理读最多
//  说明 仅SQL Server 2008 R2实例支持本参数。
}
