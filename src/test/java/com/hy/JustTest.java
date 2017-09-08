package com.hy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by huangyong on 2017/6/16.
 * 测试类
 */
public class JustTest {
    private Just just;

    @Before
    public void beforeTest() {
        just = new Just();
    }

    @Test
    public void factorial() throws Exception {
        assertThat("计算阶乘的数为负数", just.factorial(-1), is(2));
    }

    @Test
    public void fibonacci() throws Exception {
        assertEquals(1, just.fibonacci(2));
    }
}