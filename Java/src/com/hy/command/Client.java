package com.hy.command;

/**
 * Created by huangyong on 2017/5/27.
 * 测试命令模式
 * 命令模式的组装者
 */
public class Client {
    /**
     * 装配，负责创建命令对象，并设置其接收者
     */
    public void assemble() {
        //创建接收者
        Receiver receiver = new Receiver();
        //创建命令对象，并设置接收者
        Command command = new ConcreteCommand(receiver);
        //创建调用者，把命令对象设置进去（这是命令模式的入口，真正调用命令对象执行任务的地方）
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        //执行
        invoker.runCommand();
    }
}
