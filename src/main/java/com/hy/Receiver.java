package com.hy;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @author Mr.Chen
 * @description 用于IP多播客户端接受多播消息
 * @create 2018-07-23 15:58
 **/
public class Receiver {

    @Test
    public static void main(String[] args) {

        try {
            MulticastSocket multicastSocket = new MulticastSocket(7777);

            InetAddress inetAddress = InetAddress.getByName("230.0.0.1");


            //加入多播组

            multicastSocket.joinGroup(inetAddress);

            //通过一个无线循环来接受多播消息

            while (true){

                //开辟一个字节缓冲区

                byte[] bytes = new byte[100];

                //根据缓冲区的长度来新建一个数据包

                DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length);

                //调用多播套接字方法来接受数据报

                multicastSocket.receive(datagramPacket);

                System.out.println(new String(bytes));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
