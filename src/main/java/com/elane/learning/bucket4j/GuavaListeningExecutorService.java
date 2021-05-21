package com.elane.learning.bucket4j;

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GuavaListeningExecutorService {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 10, 60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(200),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws InterruptedException {

        List<String> resultList = new ArrayList<>();
        int startNum = 0; //开始行
        int size = 50; // 50行开启一个线程
        int threadNum = dataList().size() % size > 0 ? (dataList().size() / size) + 1 : dataList().size() / size;

        // 使用Guava的ListeningExecutorService装饰线程池
        ListeningExecutorService executorService =
                MoreExecutors.listeningDecorator(executor);
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        while (startNum < threadNum * size) {
            startNum += size;
            ImmutableList<String> list = ImmutableList
                .copyOf(dataList().subList(startNum - size, dataList().size() > startNum ? startNum : dataList().size()));
            CallableTask task = new CallableTask(list);
            ListenableFuture listenableFuture = executorService.submit(task);
            //回调函数
            Futures.addCallback(listenableFuture, new FutureCallback<List<String>>() {

                @Override
                public void onSuccess(List<String> lists) {
                    countDownLatch.countDown();
                    resultList.addAll(lists);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    countDownLatch.countDown();
                }
            }, MoreExecutors.directExecutor());
        }

        //设置时间，超时了直接向下执行，不再阻塞
        countDownLatch.await(3, TimeUnit.SECONDS);

        resultList.stream().forEach(s -> System.out.println(s));
        System.out.println("------------结果处理完毕，返回完毕,使用线程数量：" + threadNum);


    }

    public static List<String> dataList() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 1201; i++) {
            datas.add(i + "-");
        }
        return datas;
    }
}
