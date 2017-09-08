package com.hy;

import org.junit.*;

/**
 * Created by huangyong on 2017/6/16.
 * 测试注解
 */
public class AnnotationTest {
    private int i = 0;

    public AnnotationTest() {
        System.out.println("构造方法被执行");
    }

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("before class");
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("after class");
    }

    @Before
    public void setUp() {
        System.out.println("set up");
    }

    @After
    public void tearDown() {
        System.out.println("tear down");
    }

    @Test
    public void test1() {
        i++;
        System.out.println("i的值变为：" + i);
    }

    @Test
    public void test2() {
        i++;
        System.out.println("i的值变为：" + i);
    }

    @Ignore
    public void test3() {
        System.out.println("test3");
    }
}
