package com.boot;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda表达式测试类
 * @author TANGXIAN
 */
public class LambdaTest {
    @Test
    public void test1(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("线程启动");
            }
        };
        runnable.run();
    }

    @Test
    public void test2(){
        Runnable runnable = ()-> System.out.println("线程启动啦");
        runnable.run();
    }

    @Test
    public void test3(){
        Consumer<String> consumer = (e)->{System.out.println("字符串为"+e);};
        consumer.accept("TANGXIAN");
        consumer.accept("zhangsan");
    }

    @Test
    public void test4(){
        Comparator<Integer> com = (x, y)->{
            System.out.println("函数接口");
            return Integer.compare(x,y);

        };
        int compare = com.compare(102,108);
        System.out.println(compare);
    }

    @Test
    public void test5(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        int compare = com.compare(102,108);
        System.out.println(compare+"");
    }
}
