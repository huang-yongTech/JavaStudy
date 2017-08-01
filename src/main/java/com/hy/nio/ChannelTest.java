package com.hy.nio;

import com.hy.utils.CloseUtil;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by huangyong on 2017/7/27
 * channel测试
 */
public class ChannelTest {
    //利用通道完成文件的复制（非直接缓冲区）
    @Test
    public void test1() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("d:/upload/1.txt");
            fos = new FileOutputStream("d:/upload/2.txt");
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            //分配指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) != -1) {
                buffer.flip();//将缓冲区的界限设置为当前位置，并将当前位置重置为0，以便写操作
                outChannel.write(buffer);
                buffer.clear();//清空缓冲区，以便下一次读
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(fis, fos, inChannel, outChannel);
        }
    }

    //使用直接缓冲区完成文件的复制（内存映射文件），有一点问题，不建议使用
    @Test
    public void test2() {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("d:/upload/1.txt"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("d:/upload/3.txt"), StandardOpenOption.READ,
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            //内存映射文件
            MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
            //直接对缓冲区进行读写
            byte[] bytes = new byte[inBuffer.limit()];
            inBuffer.get(bytes);
            outBuffer.put(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(inChannel, outChannel);
        }
    }

    //通道之间的数据传输--直接缓冲区（如果读取写入的文件大或者任务繁重时，该方法性能会急剧下降）
    @Test
    public void test3() {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("d:/upload/1.txt"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("d:/upload/4.txt"), StandardOpenOption.READ,
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(inChannel, outChannel);
        }
    }
}
