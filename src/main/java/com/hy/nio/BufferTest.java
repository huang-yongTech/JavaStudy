package com.hy.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * Created by huangyong on 2017/7/27
 * buffer测试
 */
public class BufferTest {
    @Test
    public void test1() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//        System.out.println(byteBuffer.capacity());
//        System.out.println(byteBuffer.limit());
//        System.out.println(byteBuffer.position());
//        System.out.println(byteBuffer.mark());
        String str = "abcdefg";
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));
//        byteBuffer.rewind();//可重复读
//        byteBuffer.clear();//清空缓冲区，但数据仍在，只是处于被遗忘状态
//        System.out.println((char)byteBuffer.get());
    }

    //mark()和reset()测试
    @Test
    public void test2(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String str = "abcdefg";
        byteBuffer.put(str.getBytes());
        byteBuffer.flip();

        byte[] bytes=new byte[byteBuffer.limit()];
        //mark()标记当前position位置，用于后面恢复
        byteBuffer.get(bytes,0,2).mark();
        //reset()重置返回之前mark()的位置
        byteBuffer.get(bytes,2,2).reset();
        System.out.println(byteBuffer.position());
        if (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.remaining());
        }
    }
}
