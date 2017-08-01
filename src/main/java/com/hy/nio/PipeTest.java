package com.hy.nio;

import com.hy.utils.CloseUtil;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by huangyong on 2017/8/1
 */
public class PipeTest {
    //向sink管道写数据
    @Test
    public void test1() {
        Pipe pipe;
        Pipe.SinkChannel sinkChannel = null;
        Pipe.SourceChannel sourceChannel = null;
        FileChannel fileChannel = null;
        try {
            pipe = Pipe.open();
            sinkChannel = pipe.sink();
            fileChannel = FileChannel.open(Paths.get("d:/upload/a.txt"), StandardOpenOption.READ);

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(buffer) > 0) {
                buffer.flip();
                sinkChannel.write(buffer);
                buffer.clear();
            }

            sourceChannel = pipe.source();
            int length;
            //如果是使用控制台输出，则必须设置read的终止条件为大于0
            if ((length = sourceChannel.read(buffer)) > 0) {
                buffer.flip();
                System.out.println(new String(buffer.array(), 0, length));
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(sinkChannel, sourceChannel, fileChannel);
        }
    }
}
