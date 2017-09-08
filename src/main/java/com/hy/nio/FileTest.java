package com.hy.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by huangyong on 2017/8/2
 * 测试NIO中的Files，Path，Paths等类
 */
public class FileTest {
    @Test
    public void test1() {
        Path path = Paths.get("d:/upload/file1.txt");
        if (!Files.exists(path)) {
            try {
                path = Files.createFile(path);
                Path path1 = Paths.get("d:/download");
                Files.deleteIfExists(path1);
                System.out.println("创建文件，大小为：" + Files.size(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
