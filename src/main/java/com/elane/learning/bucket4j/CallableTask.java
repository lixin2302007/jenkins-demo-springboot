package com.elane.learning.bucket4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CallableTask implements Callable {

    private List<String> dataList;

    public CallableTask(List<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> listReturn = new ArrayList<>();
        //模拟对数据处理，然后返回
        for (int i = 0; i < dataList.size(); i++) {
            listReturn.add(dataList.get(i) + "：处理时间：" + System.currentTimeMillis() + "---:处理线程：" + Thread.currentThread());
        }
        return listReturn;
    }

}
