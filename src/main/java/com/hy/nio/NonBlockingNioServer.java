package com.hy.nio;

import com.hy.utils.CloseUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by huangyong on 2017/8/1
 */
public class NonBlockingNioServer {
    public static void main(String[] args) {
        NonBlockingNioServer server = new NonBlockingNioServer();
        server.server();
    }

    private void server() {
        ServerSocketChannel ssChannel = null;
        SocketChannel sChannel = null;
        try {
            ssChannel = ServerSocketChannel.open();
            ssChannel.configureBlocking(false);
            ssChannel.bind(new InetSocketAddress(9990));
            Selector selector = Selector.open();
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);

            //轮询式的获取选择器上已经准备就绪的事件
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    //获取一个准备就绪的事件
                    SelectionKey selectionKey = iterator.next();
                    //判断取出的事件的状态，若为接收状态
                    if (selectionKey.isAcceptable()) {
                        sChannel = ssChannel.accept();
                        sChannel.configureBlocking(false);
                        //将通道注册到选择器上
                        sChannel.register(selector, SelectionKey.OP_READ);
                        //若为读状态
                    } else if (selectionKey.isReadable()) {
                        //获取当前选择器上“读就绪”状态的通道
                        sChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int length;
                        //如果是使用控制台输出，则必须设置read的终止条件为大于0
                        while ((length = sChannel.read(buffer)) > 0) {
                            buffer.flip();
                            System.out.println(new String(buffer.array(), 0, length));
                            buffer.clear();
                        }
                        buffer.put("你好，消息已收到".getBytes());
                        buffer.flip();
                        sChannel.write(buffer);
                        buffer.clear();
                    }
                    //每个事件处理完后，都需要删除
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(ssChannel, sChannel);
        }
    }
}
