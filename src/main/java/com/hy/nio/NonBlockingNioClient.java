package com.hy.nio;

import com.hy.utils.CloseUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Created by huangyong on 2017/8/1
 * 测试NIO的非阻塞模式
 */
public class NonBlockingNioClient {
    public static void main(String[] args) {
        NonBlockingNioClient test = new NonBlockingNioClient();
        test.client();
    }

    private void client() {
        SocketChannel sChannel = null;
        try {
            sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9990));
            //切换到非阻塞模式
            sChannel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //发送数据给服务器
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                buffer.put((LocalDateTime.now().toString() + "\n" + str).getBytes());
                buffer.flip();
                sChannel.write(buffer);
                buffer.clear();
            }

            while (sChannel.read(buffer) > 0) {
                buffer.flip();
                System.out.println(new String(buffer.array(), 0, buffer.limit()));
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(sChannel);
        }
    }
}
