package com.hy.nio;

import com.hy.utils.CloseUtil;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by huangyong on 2017/8/1
 * 测试nio的阻塞模式（NIO既有阻塞模式也有非阻塞模式）
 */
public class BlockingNioTest {
    @Test
    public void client() {
        SocketChannel socketChannel = null;
        FileChannel fileChannel = null;
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9990));
            fileChannel = FileChannel.open(Paths.get("d:/upload/1.txt"), StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(buffer) != -1) {
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
            }

            //在阻塞模式下，如果要从服务端接收数据，则必须要添加该行代码，
            //表示客户端已经结束向服务端写数据并准备好从服务端接收返回数据
            socketChannel.shutdownOutput();

            int length;
            while ((length = socketChannel.read(buffer)) > 0) {
                buffer.flip();
                System.out.println(new String(buffer.array(), 0, length));
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(socketChannel, fileChannel);
        }
    }

    @Test
    public void server() {
        ServerSocketChannel ssChannel = null;
        FileChannel fileChannel = null;
        SocketChannel sChannel = null;
        try {
            ssChannel = ServerSocketChannel.open();
            fileChannel = FileChannel.open(Paths.get("d:/upload/11.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            ssChannel.bind(new InetSocketAddress(9990));
            sChannel = ssChannel.accept();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (sChannel.read(buffer) > 0) {
                buffer.flip();
                fileChannel.write(buffer);
                buffer.clear();
            }
            buffer.put("服务端接收数据成功！".getBytes());
            buffer.flip();
            sChannel.write(buffer);
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(ssChannel, fileChannel, sChannel);
        }
    }
}
